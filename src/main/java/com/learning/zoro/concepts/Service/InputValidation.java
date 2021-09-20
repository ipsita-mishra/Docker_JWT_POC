package com.learning.zoro.concepts.Service;

import com.learning.zoro.concepts.Constant.BankingServiceValidationException;
import com.learning.zoro.concepts.Domain.LoginUser;
import com.learning.zoro.concepts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InputValidation {

    @Autowired
    private UserRepository userRepository;
    private final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

    public Boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public Boolean isUserExists(String userName) {
        LoginUser loginUser = userRepository.findByUserName(userName);
        if (loginUser == null)
            return false;
        else
            return true;
    }
}
