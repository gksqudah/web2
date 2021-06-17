package kr.icia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.icia.domain.MemberVO;
import kr.icia.mapper.MemberMapper;
import kr.icia.security.domain.CustomUser;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

		log.warn("Load User By UserName : " + memberId);

		// userName means userid
		// userName means userid
		// MemberMapper를 이용해서 MemberVO를 조회하고,
		// MemberVO의 인스턴스를 얻을 수 있다면 CustomUser 타입의 객체로 변환해서 반환.
		MemberVO vo = memberMapper.read(memberId);

		log.warn("queried by member mapper: " + vo);

		return vo == null ? null : new CustomUser(vo);
	} 

}
