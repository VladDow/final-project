package com.github.vladdow.client.controller;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @GetMapping("/")
    public String entryPoint(Model model) {
        return "index";
    }

    @PostMapping("/")
    public String balanceInquiry(Model model) {
        return "balanceView";
    }

}
