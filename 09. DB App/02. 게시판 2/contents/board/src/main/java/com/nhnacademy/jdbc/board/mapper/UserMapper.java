package com.nhnacademy.jdbc.board.mapper;

import com.nhnacademy.jdbc.board.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUser(String id);
}
