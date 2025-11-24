package com.umidov.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    // all
    @GetMapping({"/","/home"})
    public String showHome(){
        return "permitAll {/,home}";
    }

    // login
    @GetMapping("/news")
    public String news(){
        return "authenticated {/news}";
    }

    // login + Roles(ADMIN)
    @GetMapping("/admin_panel")
    public String adminPanel(){
        return "admin panel {/admin_panel}";
    }

}
