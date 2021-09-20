package com.learning.zoro.concepts.Domain;

import lombok.Data;
@Data
public class CreateUserRequest {
    private String userName;
    private String email;
    private Long ssn;
    private String userType;
    private String password;
}
