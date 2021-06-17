package kr.icia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.icia.domain.MemberVO;
import kr.icia.service.CommonService;
import kr.icia.service.MemberService;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonController {
	
	@Autowired
	private MemberService memberservice;
	@Autowired
	private CommonService common;

	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {

		log.info("access Denied : " + auth);

		model.addAttribute("msg", "Access Denied");
	}

	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {

		log.info("error: " + error);
		log.info("logout: " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}
	}

	@GetMapping("/customLogout")
	public void logoutGET() {

		log.info("custom logout");
	}

	@PostMapping("/customLogout")
	public void logoutPost() {

		log.info("post custom logout");
	}
	
	
	
	
	/* 로그인 */

//	@RequestMapping(value = "customLogin", method = RequestMethod.POST)
//	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
//
////	        System.out.println("login 메서드 진입");
////	        System.out.println("전달된 데이터 : " + member);
//
//		HttpSession session = request.getSession();
//		MemberVO vo = memberservice.memberLogin(member);
//
//	      if(vo == null) {                                // 일치하지 않는 아이디, 비밀번호 입력 경우
//	            
//	            int result = 0;
//	            rttr.addFlashAttribute("result", result);
//	            return "redirect:/customLogin";
//	            
//	        }
//	        
//	        session.setAttribute("member", vo);             // 일치하는 아이디, 비밀번호 경우 (로그인 성공)
//	        
//	        return "redirect:/";
//	        
//	        
//	}

}
