package kr.icia.mapper;

import java.util.List;

import kr.icia.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);

	public void delete(String uuid);

	// 게시물의 번호를 이용해서 boardAttachVO 타입으로 변환하는 메서드 findByBno
	public List<BoardAttachVO> findByBno(Long bno);
	
	// 첨부파일 삭제처리 메소드
	public void deleteAll(Long bno);
	
	public List<BoardAttachVO> getOldFiles();

}
