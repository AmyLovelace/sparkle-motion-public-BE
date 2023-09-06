package com.sparklemotion.mockdata;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class JwtUserDetailsServiceMockData {

    public static UserDetails createTestUser(String username, String password, boolean enabled) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, password, enabled, true, true, true, authorities);
    }
}
