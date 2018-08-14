package person.zeng.security.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class SessionController {
  @InitBinder
  public void initSession(HttpServletRequest request) {
    request.getSession(true).setAttribute("useName", "admin1");
  }
}
