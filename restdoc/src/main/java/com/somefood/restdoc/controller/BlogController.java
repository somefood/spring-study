package com.somefood.restdoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping("/blog")
    public String blogGet(@RequestParam String name, @RequestParam String id){
        return name + "의 블로그입니다. " + id;
    }
}
