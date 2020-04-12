package com.niko.restbooks.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class BasicController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
