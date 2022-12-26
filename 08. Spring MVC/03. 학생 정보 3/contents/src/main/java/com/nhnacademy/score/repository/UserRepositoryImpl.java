package com.nhnacademy.score.repository;

import com.nhnacademy.score.domain.User;
import com.nhnacademy.score.exception.UserAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String pwd) {
        return Optional.ofNullable(getUser(id))
            .map(user->user.getPwd().equals(pwd))
            .orElse(false);
    }

    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }

    @Override
    public User addUser(String id, String pwd) {
        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = User.create(id, pwd);
        userMap.put(id, user);

        return user;
    }
}
