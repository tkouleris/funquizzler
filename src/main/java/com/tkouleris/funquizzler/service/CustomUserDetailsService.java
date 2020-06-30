package com.tkouleris.funquizzler.service;

import com.tkouleris.funquizzler.dao.UserRepository;
import com.tkouleris.funquizzler.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository R_user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = R_user.findByUsername(username);

        return new CustomUserDetails(user);
    }

}
