package jp.gr.java_conf.uzresk.springboot.demo.web.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {

    @GetMapping(path = "/loginForm")
    public String loginForm(HttpServletRequest request, Model model) {

        String serverName = request.getServerName();
        model.addAttribute("serverName", serverName);
        
        String remoteHost = request.getRemoteHost();
        model.addAttribute("remoteHost", remoteHost);

        return "login/login";
    }
}
