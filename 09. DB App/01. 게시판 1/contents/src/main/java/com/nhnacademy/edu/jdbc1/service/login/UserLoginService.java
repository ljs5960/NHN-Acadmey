package com.nhnacademy.edu.jdbc1.service.login;

public interface UserLoginService {
    boolean matches(String id, String password);
}
