package kr.icia.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.icia.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// java ������ �̿��ϴ� ���
// @ContextConfiguration(classes= {org.kzerock.config.RootConfig.class//})
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		
//		log.info(mapper);
//	}
	
	
	
	// 실제 게시물 5개 할당
	private Long[] bnoArr = { 443L, 442L, 347L, 346L, 345L };

	
	@Test
	public void testCreat() {
		
		IntStream.rangeClosed(1, 10).forEach(i -> {
			
			ReplyVO vo = new ReplyVO();
			
			// 게시물 1개당 2개의 댓글 자동 등록.
			// 정수형을 스트림 형태로 전환. 범위 1~10
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("테스트" + i);
			vo.setReplyer("replyer" + i);
			
			mapper.insert(vo);
		});
		
	}
	
	
	
	
	
//	@Test
//	public void testRead() {
//		
//		Long targetRno = 5L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		log.info(vo);
//	}
	
	
//	@Test
//	public void testDelete() {
//		
//		Long targetRno = 4L;
//		
//		mapper.delete(targetRno);
//	}
	
//	@Test
//	public void testUpdate() {
//		
//		Long targetRno = 10L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		vo.setReply("Update Reply ");
//		
//		int count = mapper.update(vo);
//		
//		log.info("UPDATE COUNT: " + count);
//	}

	
	
//	@Test
//	public void testList() {
//		
//		Criteria cri = new Criteria();
//		
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
//		
//		replies.forEach(reply -> log.info(reply));
//	}
	
	
//	@Test
//	public void testList2() {
//		
//		Criteria cri = new Criteria(1, 10);
//		
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, 443L);
//		
//		replies.forEach(reply -> log.info(reply));
//		
//	}

}
