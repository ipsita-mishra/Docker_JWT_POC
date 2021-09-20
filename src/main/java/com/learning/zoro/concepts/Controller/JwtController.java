package com.learning.zoro.concepts.Controller;

import com.learning.zoro.concepts.Constant.ErrorMessage;
import com.learning.zoro.concepts.Domain.*;
import com.learning.zoro.concepts.Service.UserServiceImpl;
import com.learning.zoro.concepts.Utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtility jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Token : "+token);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception(ErrorMessage.USERDISABLED_ERROR_MSG, e);
        } catch (BadCredentialsException e) {
            throw new Exception(ErrorMessage.AUTHENTICATION_ERROR_MSG, e);
        }
    }
}