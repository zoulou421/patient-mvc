package com.formationkilo.patientmvc.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
