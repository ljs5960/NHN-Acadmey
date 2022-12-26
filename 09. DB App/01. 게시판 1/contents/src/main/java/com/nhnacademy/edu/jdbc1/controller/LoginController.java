package com.nhnacademy.edu.jdbc1.controller;

import com.nhnacademy.edu.jdbc1.service.login.UserLoginService;
import com.nhnacademy.edu.jdbc1.service.login.UserLoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {
    private final String LOGIN_SESSION = "LOGIN_SESSION";

    private final UserLoginService userLoginService;

    public LoginController(UserLoginServiceImpl userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("password") String password) {
        if (Objects.equals(id, "")) {
            return "redirect:/login";
        }

        if (Objects.equals(password, "")) {
            return "redirect:/login";
        }

        if (userLoginService.matches(id, password)) {
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(LOGIN_SESSION);
        return "redirect:/login";
    }
}
