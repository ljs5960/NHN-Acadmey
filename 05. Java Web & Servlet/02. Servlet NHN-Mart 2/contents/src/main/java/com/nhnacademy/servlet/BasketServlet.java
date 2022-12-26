package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
FoodServlet에서 선택한 정보 출력
 */
@WebServlet(name = "basketServlet", urlPatterns = "/basket")
public class BasketServlet extends HttpServlet {
    Map<String, Integer> basket = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 응답에 장바구니에 담긴 상품 목록과 전체 금액 표시
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

//        resp.sendRedirect("basket.jsp");
        RequestDispatcher rd = req.getRequestDispatcher("basket.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 장바구니에 담은 갯수 검증 (재고보다 적은지)
        int onionStock = Integer.parseInt(getServletContext().getInitParameter("onion"));
        int eggStock = Integer.parseInt(getServletContext().getInitParameter("egg"));
        int greenOnionStock = Integer.parseInt(getServletContext().getInitParameter("greenOnion"));
        int appleStock = Integer.parseInt(getServletContext().getInitParameter("apple"));

        basket.put("onion", Integer.parseInt(req.getParameter("onion")));
        basket.put("egg", Integer.parseInt(req.getParameter("egg")));
        basket.put("greenOnion", Integer.parseInt(req.getParameter("greenOnion")));
        basket.put("apple", Integer.parseInt(req.getParameter("apple")));

        if (onionStock < (basket.get("onion")) ||
            (eggStock < basket.get("egg")) ||
            (greenOnionStock < basket.get("greenOnion")) ||
            (appleStock < basket.get("apple"))) {
            // 재고보다 많게 선택했을 경우(비정상)
            // mountExceptionError.jsp 페이지로 리다이렉트
            RequestDispatcher rd = req.getRequestDispatcher("amountExceptionError.jsp");
            rd.forward(req, resp);
        } else {
            // 재고보다 적게 선택했을 경우(정상)
            // 장바구니에 담은 수량만큼 상품매대에서 제거 처리
            getServletContext().setAttribute("onion", onionStock - (basket.get("onion")));
            getServletContext().setAttribute("egg", eggStock - (basket.get("egg")));
            getServletContext().setAttribute("greenOnion", greenOnionStock - (basket.get("greenOnion")));
            getServletContext().setAttribute("apple", appleStock - (basket.get("apple")));

            // /pay로 리다이렉트

            resp.sendRedirect("/basket");
        }
    }
}
