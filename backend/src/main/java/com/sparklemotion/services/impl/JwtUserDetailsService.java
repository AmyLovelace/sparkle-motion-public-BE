package com.sparklemotion.services.impl;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.exceptions.ServiceException;
import com.sparklemotion.entities.User;
import com.sparklemotion.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);

            if (Objects.isNull(user)) {
                throw new ServiceException(Error.USER_NOT_FOUND.getMessage());
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        } catch (ServiceException e) {
            log.error(Error.USER_NOT_FOUND.getMessage());
            throw new UsernameNotFoundException(Error.USER_NOT_FOUND.getMessage());
        }
    }}
