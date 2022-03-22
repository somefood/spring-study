package com.somefood.boardproject.controller;

import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String all(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board/board_list";
    }

    @GetMapping("/notice")
    public String noticeBoard() {
        return "board/board_list";
    }

    @GetMapping("/question")
    public String questionBoard() {
        return "board/board_list";
    }

    @GetMapping("/free")
    public String freeBoard() {
        return "board/board_list";
    }
}
