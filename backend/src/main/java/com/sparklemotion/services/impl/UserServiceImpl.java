package com.sparklemotion.services.impl;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.exceptions.ValidationException;
import com.sparklemotion.entities.User;
import com.sparklemotion.repositories.UserRepository;
import com.sparklemotion.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean save(User user) {
        this.userRepository.save(user);
        return true;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean createUserIfNotExists(User user) {
        String username = user.getUsername();

        if (existsByUsername(username)) {
            log.error(Error.USER_EXISTS.getMessage());
            throw new ValidationException(Error.USER_EXISTS.getMessage());
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);

        return save(user);
    }
}
