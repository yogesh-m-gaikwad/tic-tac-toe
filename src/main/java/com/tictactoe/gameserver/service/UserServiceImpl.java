package com.tictactoe.gameserver.service;

import com.tictactoe.gameserver.domain.User;
import com.tictactoe.gameserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String input) {
        User user = null;

        if (input.contains("@"))
            user = userRepository.findByEmail(input);
        else
            user = userRepository.findByUsername(input);

        if (user == null)
            throw new BadCredentialsException("Bad credentials");

        new AccountStatusUserDetailsChecker().check(user);

        return user;
    }

}