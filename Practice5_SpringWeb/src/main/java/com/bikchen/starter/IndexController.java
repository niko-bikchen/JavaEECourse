package com.bikchen.starter;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    @RequestMapping({"/book-list", ""})
    public String bookList() {
        return "book-list";
    }
}
