package com.nhnacademy.jdbc.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String LOGIN_SESSION = "LOGIN_SESSION";

        HttpSession session = request.getSession();
        Object sessionValue = session.getAttribute(LOGIN_SESSION);

        if(sessionValue == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
