package com.pm.patient_service.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
    @RequestMapping("/")
    public String welcome(){
        return "Welcome to Patient Service";
    }
}
