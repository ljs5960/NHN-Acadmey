package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
    @WebInitParam(name="id", value = "test"),
    @WebInitParam(name="pwd", value = "12345")
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        // 세션이 없을 경우
        if (Objects.isNull(session)) {
             // /login.html로 리다이렉트
            resp.sendRedirect("/login.html");
        } else {
            // 세션이 있을 경우 /home 로 리다이렉트
            resp.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String id = getServletConfig().getInitParameter("id");
        String pwd = getServletConfig().getInitParameter("pwd");

        String inputId = req.getParameter("id");
        String inputPwd = req.getParameter("pwd");

        // id pwd 일치할 경우
        if ((id.equals(inputId)) && pwd.equals(inputPwd)) {
            // 세션 생성 후 /home 리다이렉트
            HttpSession session = req.getSession();
            session.setAttribute("id", inputId);
            resp.sendRedirect("/login");
        } else {
            // 불일치시 /login.html로 리다이렉트
            resp.sendRedirect("/login.html");
        }
    }
}
