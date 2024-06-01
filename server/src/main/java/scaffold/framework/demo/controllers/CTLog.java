package scaffold.framework.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CTLog {
    @GetMapping("/logout")
    public String getMethodName(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/equipe/login";
    }

}
