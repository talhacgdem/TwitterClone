package com.talhacgdem.twitterclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/swagger")
public class ApiDocsController {

    @GetMapping
    public String home() {
        return "redirect:/swagger-ui.html";
    }


}
