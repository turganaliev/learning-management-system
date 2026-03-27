package com.turganaliev.learning_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodByeController {
    @GetMapping("/goodbye")
    public String goodbye() {
        return "GoodBye World!";
    }
}
