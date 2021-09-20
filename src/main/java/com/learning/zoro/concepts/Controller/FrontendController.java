package com.learning.zoro.concepts.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontendController {
    @GetMapping(value={"/","/home"})
    public String viewHome(Model model) {
        return "homepage";
    }
    @GetMapping("/data")
    public String viewUserData(Model model) {
        return "data";
    }
    @GetMapping("/logout")
    public String logoutUser(Model model) {
        return "homepage";
    }
}
