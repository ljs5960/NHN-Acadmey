package com.nhnacademy.jdbc.board.service.impl;

import com.nhnacademy.jdbc.board.domain.User;
import com.nhnacademy.jdbc.board.mapper.UserMapper;
import com.nhnacademy.jdbc.board.service.UserLoginService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private final UserMapper userMapper;

    public UserLoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(String id) {
        return userMapper.selectUser(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
            .map(user -> user.getPassword().equals(password))
            .orElse(false);
    }
}
