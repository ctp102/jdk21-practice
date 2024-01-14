package com.demo.jdk21practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name) {
        // arrow, inline : jdk 14
        String message = switch(name) {
            case "jhcho" -> "jhcho님 안녕";
            default -> "who are you?";
        };

        return message;
    }

}