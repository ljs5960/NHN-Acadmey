package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 응답에 상품 목록 출력
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Foods</title>");
            out.println("</head>");

            out.println("<body>");

            out.println("<ul>");
            out.println("<li> 양파 (1,000원): " + getServletContext().getInitParameter("onion") + "개 </li>");
            out.println("<li> 계란 (2,000원): " + getServletContext().getInitParameter("egg") + "판 </li>");
            out.println("<li> 파 (500원): " + getServletContext().getInitParameter("greenOnion") + "개 </li>");
            out.println("<li> 사과 (2,000원): " + getServletContext().getInitParameter("apple") + "개 </li>");
            out.println("</ul>");


            out.println("<br />");

            // 원하는 상품을 선택해서 장바구니에 담을 수 있는 form 구성
            out.println("<form method='post' action='/cart'>");
            out.println("양파 갯수 : <input type='number' name='onion'><br />");
            out.println("계란 갯수 : <input type='number' name='egg'><br />");
            out.println("파 갯수 : <input type='number' name='greenOnion'><br />");
            out.println("사과 갯수 : <input type='number' name='apple'><br />");
            out.println("<input type='submit'>");
            out.println("</form>");

            out.println("<br />");

            out.println("<a href='http://localhost:8080/init'>back</a>");

            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
