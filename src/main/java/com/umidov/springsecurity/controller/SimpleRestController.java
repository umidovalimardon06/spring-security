package com.umidov.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {

    @GetMapping("/home")
    public String home() { return "home"; }

    @GetMapping("/welcome")
    public String welcome() { return "welcome"; }

    @GetMapping("/ali")
    public String ali() { return "ali user"; }

    @GetMapping("/umar")
    public String admin() { return "umar admin"; }

    @GetMapping("/deny")
    public String deny() { return "DENY 404"; }

}
