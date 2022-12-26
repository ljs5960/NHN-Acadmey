package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "changeLangServlet", urlPatterns = "/changeLang", initParams = {
    @WebInitParam(name = "locale", value = "kr")
})
public class ChangeLangServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession();
        String locale = (String) session.getAttribute("locale");
        log.info(locale);

        if (Objects.equals(locale, "kr")) {
            session.setAttribute("locale", "en");
        } else {
            session.setAttribute("locale", "kr");
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/home");
    }
}
