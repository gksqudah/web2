package kr.icia.mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.icia.domain.BoardVO;
import kr.icia.domain.Criteria;
import kr.icia.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// java ������ �̿��ϴ� ���
// @ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
//	@Test
//	public void testInsert() {
//		
//		BoardVO board = new BoardVO();
//		board.setTitle("���� �ۼ��ϴ� ��");
//		board.setContent("���� �ۼ��ϴ� ����");
//		board.setWriter("newbie");
//		
//		mapper.insert(board);
//		
//		log.info(board);
//	
//	}
	
//	@Test
//	public void testInsertSelectKey() {
//		
//		BoardVO board = new BoardVO();
//		board.setTitle("���� �ۼ��ϴ� �� select key");
//		board.setContent("���� �ۼ��ϴ� ���� select key");
//		board.setWriter("newbie");
//		
//		mapper.insert(board);
//		
//		log.info(board);
//	
//	}
	
//	@Test
//	public void testRead() {
//		
//		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
//		BoardVO board = mapper.read(5L);
//		
//		log.info(board);
//		
//	}
	
//	@Test
//	public void testDelete() {
//		
//		log.info("DELETE COUNT : " + mapper.delete(3L));
//	}
	
	
//	@Test
//	public void testUpdate() {
//		
//		BoardVO board = new BoardVO();
//		// ������ �����ϴ� ��ȣ���� Ȯ���� ��
//		board.setBno(5L);
//		board.setTitle("������ ����");
//		board.setContent("������ ����");
//		board.setWriter("user00");
//		
//		int count = mapper.update(board);
//		log.info("UPDATE COUNT: " + count);
//		
//	}
	
	
//	@Test
//	public void testPaging() {
//		
//		Criteria cri = new Criteria();
//		// 10���� 3������
//		cri.setPageNum(1);
//		cri.setAmount(10);
//		
//		List<BoardVO> list = mapper.getListWithPaging(cri);
//		
//		list.forEach(board -> log.info(board.getBno()));
//	}
	
	
	@Test
	public void testSearch() {
		
		Criteria cri = new Criteria();
		cri.setKeyword("����");
		cri.setType("TCW");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
	
}
