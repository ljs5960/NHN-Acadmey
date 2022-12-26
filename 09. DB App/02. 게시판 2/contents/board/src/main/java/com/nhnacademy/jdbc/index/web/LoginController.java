package com.nhnacademy.jdbc.index.web;

import com.nhnacademy.jdbc.board.service.UserLoginService;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class LoginController {
    private final String LOGIN_SESSION = "LOGIN_SESSION";

    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = LOGIN_SESSION, required = false) String session) {
        if (Objects.nonNull(session)) {
            return "post/postList";
        }
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id")String id,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (session.getAttribute(LOGIN_SESSION) != null) {
            session.removeAttribute(LOGIN_SESSION);
        }

        if (userLoginService.matches(id, password)) {
            session.setAttribute(LOGIN_SESSION, id);
            return "redirect:/board";
        }

        return "redirect:/login";
    }
}
