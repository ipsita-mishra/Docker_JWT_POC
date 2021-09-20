package com.learning.zoro.concepts.Domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "LoginUser")
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId ;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userType")
    private String userType;
    @Column(name = "email")
    private String email;
    @Column(name = "ssn")
    private Long ssn;
    @Column(name = "password")
    private String password;
}
