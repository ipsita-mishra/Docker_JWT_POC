package com.learning.zoro.concepts.Controller;

import com.learning.zoro.concepts.Constant.BankingServiceValidationException;
import com.learning.zoro.concepts.Constant.ErrorMessage;
import com.learning.zoro.concepts.Domain.*;
import com.learning.zoro.concepts.Service.InputValidation;
import com.learning.zoro.concepts.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class BankController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InputValidation inputValidation;

    @Autowired
    private ErrorMessage errorMessage;

    @PostMapping(value = "/fetch",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDataResponse> fetchUser(@Valid @RequestBody UserDataRequest user) throws Exception {
        LoginUser loginUser = userService.fetchUserDetails(user);
        UserDataResponse userDataResponse = new UserDataResponse();
        userDataResponse.setUserId(loginUser.getUserId());
        userDataResponse.setUserName(loginUser.getUserName());
        userDataResponse.setUserType(loginUser.getUserType());
        userDataResponse.setSsn(loginUser.getSsn());
        userDataResponse.setEmail(loginUser.getEmail());
        return ResponseEntity.created(URI.create(String.format("/UserDetails/%s",loginUser.getUserName()))).body(userDataResponse);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<CreateUserResponse> createNewUser(@Valid @RequestBody CreateUserRequest user) throws Exception {
        if(inputValidation.isUserExists(user.getUserName())){
            throw new BankingServiceValidationException(errorMessage.USER_EXITS_ERROR_MSG);
        }
        String email = user.getEmail();
        if(email.isEmpty() || !inputValidation.isValidEmail(email)){
            throw new BankingServiceValidationException(errorMessage.EMAIL_ERROR_MSG);
        }
        LoginUser loginUser = userService.createUser(user);
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUserId(loginUser.getUserId());
        createUserResponse.setName(loginUser.getUserName());
        createUserResponse.setMessage("User Created Successfully");
        return ResponseEntity.created(URI.create(String.format("/CreateUserResponse/%s",loginUser.getUserName()))).body(createUserResponse);
    }
}
