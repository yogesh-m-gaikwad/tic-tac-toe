package com.tictactoe.gameserver.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username) throws UsernameNotFoundException;
}