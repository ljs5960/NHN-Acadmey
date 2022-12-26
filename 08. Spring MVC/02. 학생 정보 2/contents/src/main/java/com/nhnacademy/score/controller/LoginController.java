package com.nhnacademy.score.controller;

import com.nhnacademy.score.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = "LOGIN_SESSION", required = false)String session) {
        if (Objects.nonNull(session)) {
            return "index";
        }
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("pwd") String pwd,
                        HttpSession session) {
        if (session.getAttribute("LOGIN_SESSION") != null) {
            session.removeAttribute("LOGIN_SESSION");
        }

        if (userRepository.matches(id, pwd)) {
            session.setAttribute("LOGIN_SESSION", id);
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGIN_SESSION");
        return "redirect:/login";
    }
}
