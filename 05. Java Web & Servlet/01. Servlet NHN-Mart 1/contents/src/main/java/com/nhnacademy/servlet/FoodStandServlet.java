package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodStandServlet extends HttpServlet {
    private final ArrayList<Food> foods = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // context-param에 설정된 식품 정보를 읽어서 식품 객체를 생성
//        foods.add(new Food("양파", Integer.parseInt(getServletContext().getInitParameter("양파"))));
//        foods.add(new Food("계란", Integer.parseInt(getServletContext().getInitParameter("계란"))));
//        foods.add(new Food("파", Integer.parseInt(getServletContext().getInitParameter("파"))));
//        foods.add(new Food("사과", Integer.parseInt(getServletContext().getInitParameter("사과"))));

        // ServletContext attribute 로 설정
        ServletContext servletContext = getServletConfig().getServletContext();
//        servletContext.setAttribute("양파", new Food("양파", Integer.parseInt(getServletContext().getInitParameter("양파"))));
//        servletContext.setAttribute("계란", new Food("계란", Integer.parseInt(getServletContext().getInitParameter("계란"))));
//        servletContext.setAttribute("파", new Food("파", Integer.parseInt(getServletContext().getInitParameter("파"))));
//        servletContext.setAttribute("사과", new Food("사과", Integer.parseInt(getServletContext().getInitParameter("사과"))));
        servletContext.setAttribute("양파", new Food("양파", 1000));
        servletContext.setAttribute("계란", new Food("계란", 2000));
        servletContext.setAttribute("파", new Food("파", 500));
        servletContext.setAttribute("사과", new Food("사과", 2000));

        // 응답 화면에 상품 목록으로 이동할 수 있는 링크 제공
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FoodStand</title>");
            out.println("</head>");

            out.println("<body>");

            out.println("<a href='http://localhost:8080/foods'>상품목록</a> <br />");
            out.println("<a href='http://localhost:8080/'>back</a> <br />");

            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
