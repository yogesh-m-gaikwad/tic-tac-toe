package com.tictactoe.gameserver.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * CustomPrincipal class for to allow username or email based login.
 *
 * @author Yogesh Gaikwad
 * @since 1.0.0
 */
@Component
public class CustomPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String email;

    public CustomPrincipal() {
    }

    public CustomPrincipal(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
