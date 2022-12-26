package com.nhnacademy.edu.jdbc1.service.login;

import com.nhnacademy.edu.jdbc1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private final UserRepository userRepository;

    public UserLoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean matches(String id, String password) {
        String value = userRepository.findById(id);

        return password.equals(value);
    }
}
