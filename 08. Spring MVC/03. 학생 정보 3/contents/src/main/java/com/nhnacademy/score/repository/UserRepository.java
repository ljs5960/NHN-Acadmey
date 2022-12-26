package com.nhnacademy.score.repository;

import com.nhnacademy.score.domain.User;

public interface UserRepository {
    boolean exists(String id);

    boolean matches(String id, String pwd);

    User getUser(String id);

    User addUser(String id, String pwd);
}
