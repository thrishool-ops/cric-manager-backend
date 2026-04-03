package com.cricket.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the Cricket Tournament Manager API! \n\n" +
               "The backend is running successfully. \n\n" +
               "Please visit the frontend at http://localhost:5173 to use the application, \n" +
               "or access the API directly at http://localhost:8080/api/tournaments";
    }
}
