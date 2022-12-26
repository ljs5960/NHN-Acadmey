package com.nhnacademy.jdbc.board.service;


import com.nhnacademy.jdbc.board.domain.User;

public interface UserLoginService {
    User getUser(String id);
    boolean matches(String id, String password);
}
