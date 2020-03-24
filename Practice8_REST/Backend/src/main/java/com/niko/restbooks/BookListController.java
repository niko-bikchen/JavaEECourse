package com.niko.restbooks;

import org.springframework.web.bind.annotation.GetMapping;

public class BookListController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
