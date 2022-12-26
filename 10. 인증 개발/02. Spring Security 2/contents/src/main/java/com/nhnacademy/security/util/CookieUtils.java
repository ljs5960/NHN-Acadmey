package com.nhnacademy.security.util;

import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
    private CookieUtils() {
        throw new IllegalStateException("Utility class");
    }
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (Objects.isNull(cookies)) {
            return null;
        }

        return Arrays.stream(cookies)
            .filter(c -> c.getName().equals(cookieName))
            .map(Cookie::getValue)
            .findFirst()
            .orElse(null);
    }
}
