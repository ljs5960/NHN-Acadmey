package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/*
단순 물품명, 가격, 재고 정보만 출력하는 페이지
로그인 필요 X
 */
@Slf4j
@WebServlet(name = "foodStandServlet", urlPatterns = "/foodstand")
public class FoodStandServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // context-param에 설정된 식품 정보를 읽어서 식품 객체를 생성
        // ServletContext attribute 로 설정
        ServletContext servletContext = getServletConfig().getServletContext();
        servletContext.setAttribute("양파", new Food("양파", 1000));
        servletContext.setAttribute("계란", new Food("계란", 2000));
        servletContext.setAttribute("파", new Food("파", 500));
        servletContext.setAttribute("사과", new Food("사과", 2000));

        // 응답 화면에 상품 목록으로 이동할 수 있는 링크 제공
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        RequestDispatcher rd = req.getRequestDispatcher("/foodstand.jsp");
        rd.forward(req, resp);
    }
}
