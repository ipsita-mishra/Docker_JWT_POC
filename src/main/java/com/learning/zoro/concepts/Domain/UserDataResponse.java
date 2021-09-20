package com.learning.zoro.concepts.Domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserDataResponse {
    private Long userId ;
    private String userName;
    private String userType;
    private String email;
    private Long ssn;
}
