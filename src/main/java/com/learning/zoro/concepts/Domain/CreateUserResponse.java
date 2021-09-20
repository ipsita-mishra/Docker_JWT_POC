package com.learning.zoro.concepts.Domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateUserResponse {
    private String name;
    private Long userId;
    private String message;
}
