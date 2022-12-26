package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
    @WebInitParam(name = "excludedUrls", value = "/ /login /login.html")
})
public class LoginCheckFilter implements Filter {
    private Set<String> excludedUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.stream(urls.split(" "))
            .map(String::trim)
            .collect(Collectors.toSet());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        // 세션이 없을 경우
        if (Objects.isNull(session)) {
            // 제외 URL의 경우 Pass
            if (excludedUrls.contains(((HttpServletRequest) request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                // 아닐경우 /loginForm 리다이렉트
                ((HttpServletResponse) response).sendRedirect("/login.html");
            }
        } else {
            // 세션이 있을 경우
            chain.doFilter(request, response);
        }
    }
}
