package com.somefood.boardproject.web.controller;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.CategoryType;
import com.somefood.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String all(@RequestParam(value = "type", required = false) CategoryType categoryType, Model model) {
        List<Board> boards = boardService.findAll(categoryType);
        model.addAttribute("boards", boards);
        return "board/board_list";
    }

    @GetMapping("/{boardId}")
    public String getBoard(@PathVariable Long boardId, Model model) {
        Board board = boardService.getBoard(boardId);
        model.addAttribute("board", board);
        return "board/board";
    }
}
