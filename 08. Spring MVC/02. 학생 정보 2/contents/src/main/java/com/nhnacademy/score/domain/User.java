package com.nhnacademy.score.domain;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String pwd;

    public User(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public static User create(String id, String pwd) {
        return new User(id, pwd);
    }
}
