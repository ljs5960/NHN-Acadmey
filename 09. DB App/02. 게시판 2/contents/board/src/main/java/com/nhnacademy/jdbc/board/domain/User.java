package com.nhnacademy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final Long id_pk;
    private final String id;
    private final String password;
    private final Boolean isAdmin;
}
