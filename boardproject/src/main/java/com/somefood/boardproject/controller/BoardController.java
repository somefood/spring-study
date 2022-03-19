package com.somefood.boardproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/board")
public class BoardController {

    @GetMapping
    public String all() {
        return "board_list";
    }

    @GetMapping("/notice")
    public String noticeBoard() {
        return "board_list";
    }

    @GetMapping("/question")
    public String questionBoard() {
        return "board_list";
    }

    @GetMapping("/free")
    public String freeBoard() {
        return "board_list";
    }
}
