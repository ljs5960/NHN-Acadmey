package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketServlet extends HttpServlet {
    Map<String, Integer> basket = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 응답에 장바구니에 담긴 상품 목록과 전체 금액 표시
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Basket</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("장바구니 화면");
            out.println("<ul>");
            out.println("<li> 양파 (1,000원): " + getServletContext().getInitParameter("onion") + "개 </li>");
            out.println("<li> 계란 (2,000원): " + getServletContext().getInitParameter("egg") + "판 </li>");
            out.println("<li> 파 (500원): " + getServletContext().getInitParameter("greenOnion") + "개 </li>");
            out.println("<li> 사과 (2,000원): " + getServletContext().getInitParameter("apple") + "개 </li>");
            out.println("</ul>");
            out.println("<a href='http://localhost:8080/cart'>장바구니</a>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 상품 수량보다 더 많은 주문을 하지 않았는 지 검증
        int onionStock = Integer.parseInt(getServletContext().getInitParameter("onion"));
        int eggStock = Integer.parseInt(getServletContext().getInitParameter("egg"));
        int greenOnionStock = Integer.parseInt(getServletContext().getInitParameter("greenOnion"));
        int appleStock = Integer.parseInt(getServletContext().getInitParameter("apple"));

        basket.put("onion", Integer.parseInt(req.getParameter("onion")));
        basket.put("onion", Integer.parseInt(req.getParameter("egg")));
        basket.put("onion", Integer.parseInt(req.getParameter("greenOnion")));
        basket.put("onion", Integer.parseInt(req.getParameter("apple")));

        if (onionStock < (basket.get("onion")) ||
            (eggStock < basket.get("egg")) ||
            (greenOnionStock < basket.get("greenOnion")) ||
            (appleStock < basket.get("apple"))) {
            // 많을 경우 /foods로 다시 리다이렉트
            resp.sendRedirect("/foods");
        } else {
            // 장바구니에 담은 수량만큼 상품매대에서 제거 처리
            getServletContext().setAttribute("onion", onionStock - (basket.get("onion")));
            getServletContext().setAttribute("egg", eggStock - (basket.get("egg")));
            getServletContext().setAttribute("greenOnion", greenOnionStock - (basket.get("greenOnion")));
            getServletContext().setAttribute("apple", appleStock - (basket.get("apple")));

            // /cart로 리다이렉트
            resp.sendRedirect("/cart");
        }
    }
}
