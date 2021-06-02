package kr.icia.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.icia.domain.BoardAttachVO;
import kr.icia.mapper.BoardAttachMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	@Setter(onMethod_ = { @Autowired })
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -1);

		String str = sdf.format(cal.getTime());

		return str.replace("-", File.separator);
	}

	@Scheduled(cron = "0 0 3 * * *")
	public void checkFiles() throws Exception {

		log.warn("File Check Task run.................");
		log.warn(new Date());
		// file list in database
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		// 데이터베이스에서 가져온 객체는 BoardAttachVO. 나중 비교를 위해 java.nio.Paths 목록으로 변환.

		// ready for check file in directory with database file list
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());

		// 이미지 파일의 경우 섬네일 파일 목록에 필요하므로,
		// 별도로 처리하여 해당 일의 예상 파일 목록 완성.
		// image file has thumnail file
		fileList.stream().filter(vo -> vo.isFileType() == true)
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));

		log.warn("===========================================");

		fileListPaths.forEach(p -> log.warn(p));

		// 실제 파일에 있는 폴더와 데이터베이스에 없는 파일들을 찾아서 목록으로 준비하여 removeFiles 변수에 담아서 처리.
		// 최종적으로 삭제 대상이 되는 파일 삭제.
		// files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();

		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

		log.warn("-----------------------------------------");
		for (File file : removeFiles) {

			log.warn(file.getAbsolutePath());

			file.delete();

		}
	}
}