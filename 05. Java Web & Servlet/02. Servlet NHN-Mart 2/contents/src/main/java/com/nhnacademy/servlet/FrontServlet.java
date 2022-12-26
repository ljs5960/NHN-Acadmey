//package com.nhnacademy.servlet;
//
//import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@WebServlet(name = "frontServlet", urlPatterns = "*.do")
//public class FrontServlet extends HttpServlet {
//    private static final String REDIRECT_PREFIX = "redirect:";
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp)
//        throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//
//        try {
//            String processingServletPath = resolveServlet(req.getServletPath());
//
//        } catch (Exception e) {
//            log.error("", e);
//            RequestDispatcher rd = req.getRequestDispatcher("/amountExceptionError.jsp");
//            rd.forward(req, resp);
//        }
//    }
//}
