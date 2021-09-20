package com.learning.zoro.concepts.Domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String firstName,String password) {
        this.username = firstName;
        this.password = password;
    }
}
