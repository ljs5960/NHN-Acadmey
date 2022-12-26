package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/*
물품, 가격, 재고 정보 표시 + 장바구니에 담을 갯수 선택하는 페이지
로그인 필요 O
 */
@Slf4j
@WebServlet(name = "foodServlet", urlPatterns = "/food")
public class FoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 응답에 상품 목록 출력
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        RequestDispatcher rd = req.getRequestDispatcher("/food.jsp");
        rd.forward(req, resp);
    }
}
