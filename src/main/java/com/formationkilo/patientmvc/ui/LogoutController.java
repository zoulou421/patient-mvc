package com.formationkilo.patientmvc.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/custom-logout")
    public String logout(){
        return "logout-success";
    }
}
