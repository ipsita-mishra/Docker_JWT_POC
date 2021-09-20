package com.learning.zoro.concepts.Service;

import com.learning.zoro.concepts.Domain.UserDataRequest;
import com.learning.zoro.concepts.Domain.LoginUser;
import com.learning.zoro.concepts.Domain.CreateUserRequest;
import com.learning.zoro.concepts.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public LoginUser createUser(CreateUserRequest createUserRequest) {
        LoginUser user = new LoginUser();
        user.setUserName(createUserRequest.getUserName());
        user.setEmail(createUserRequest.getEmail());
        user.setUserType(createUserRequest.getUserType());
        user.setPassword(bcryptEncoder.encode(createUserRequest.getPassword()));
        user.setSsn(createUserRequest.getSsn());
        return userRepository.save(user);
    }

    public LoginUser fetchUserDetails(UserDataRequest user) {
        LoginUser loginUser = userRepository.findByUserName(user.getUserName());
        return loginUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
    }


}

