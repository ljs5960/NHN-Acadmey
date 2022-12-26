package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)) {
            // 세션이 없을 경우 로그인 /login.html로 리다이렉트
            resp.sendRedirect("/loginForm.html");
        } else {
            // 세션이 있을 경우 /init 페이지로 리다이렉트
            resp.sendRedirect("/init");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String id = getServletConfig().getInitParameter("id");
        String pwd = getServletConfig().getInitParameter("pwd");

        String inputId = req.getParameter("id");
        String inputPwd = req.getParameter("pwd");

        if ((id.equals(inputId)) && pwd.equals(inputPwd)) {
            // id pwd 일치할 경우 세션 생성 후 /login 리다이렉트
            HttpSession session = req.getSession();
            session.setAttribute("id", inputId);
            resp.sendRedirect("/login");
        } else {
            // 불일치시 /login.html로 리다이렉트
            resp.sendRedirect("/loginForm.html");
        }
    }
}
