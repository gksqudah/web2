package kr.icia.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.icia.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java 인터페이스 의존성 주입
	
//	//회원가입 쿼리 테스트 메서드
//	@Test
//	public void memberJoin() throws Exception{
//		MemberVO member = new MemberVO();
//		
//		member.setid("test24");			//회원 id
//		member.setPw("test");			//회원 비밀번호
//		member.setMemberName("test");		//회원 이름
//		member.setMemberMail("test");		//회원 메일
//		member.setMemberAddr1("test");		//회원 우편번호
//		member.setMemberAddr2("test");		//회원 주소
//		member.setMemberAddr3("test");		//회원 상세주소
//		
//		membermapper.memberJoin(member);			//쿼리 메서드 실행
//		
//	}
	
	// 로그인 테스트
    @Test
    public void memberLogin() throws Exception{
        
        MemberVO member = new MemberVO();    // MemberVO 변수 선언 및 초기화
        
        /* 올바른 아이디 비번 입력경우 */
        member.setUserId("asdasd");
        member.setUserPw("asdASD123");
        
        /* 올바른 않은 아이디 비번 입력경우 */
        //member.setMemberId("test1123");
        //member.setMemberPw("test1321321");
        
        membermapper.memberLogin(member);
        System.out.println("결과 값 : " + membermapper.memberLogin(member));
        
    }
	
	
	
}
