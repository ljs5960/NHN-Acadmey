package com.nhnacademy.edu.jdbc1.domain;

import java.util.Date;

public class User {
    private final Long id;
    private final String userName;
    private final String password;
    private final Date createdAt;

    public User(Long id, String userName, String password, Date createdAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createdAt = createdAt;
    }
}
