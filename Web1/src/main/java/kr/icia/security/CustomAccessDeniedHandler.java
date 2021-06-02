package kr.icia.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;


//접근 제한이 된 경우 쿠키나 세션에 특정 작업을 하는 등의 다양한 처리를 구현하기 위하여,
//AccessDeniedHandler 인터페이스 직접 구현.
@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, 
      HttpServletResponse response, AccessDeniedException accessException)
      throws IOException, ServletException {

    log.error("Access Denied Handler");

    log.error("Redirect....");

    response.sendRedirect("/accessError");

  }

}
