package com.nhnacademy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FoodStand extends HttpServlet {
    private final ArrayList<Food> foods = new ArrayList<>();

    public void add(Food onion1) {
        foods.add(onion1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // context-param에 설정된 식품 정보를 읽어서 식품 객체를 생성
        foods.add(new Food("양파", Integer.parseInt(getServletContext().getInitParameter("양파"))));
        foods.add(new Food("계란", Integer.parseInt(getServletContext().getInitParameter("계란"))));
        foods.add(new Food("파", Integer.parseInt(getServletContext().getInitParameter("파"))));
        foods.add(new Food("사과", Integer.parseInt(getServletContext().getInitParameter("사과"))));

        // ServletContext attribute 로 설정
        ServletContext servletContext = getServletConfig().getServletContext();
        servletContext.setAttribute("foods", foods);

        // 응답 화면에 상품 목록으로 이동할 수 있는 링크 제공
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>FoodStand</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<a href='http://localhost:8080/foods'>foodlist</a> <br />");

        out.println("</body>");
        out.println("</html>");
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }
}
