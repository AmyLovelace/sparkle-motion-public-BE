package com.sparklemotion.services;

import com.sparklemotion.entities.User;

public interface UserService {

    boolean save (User user);

    Boolean existsByUsername(String username);

    boolean createUserIfNotExists(User user);
}
