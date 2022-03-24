package com.somefood.board.web.controller;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.Category;
import com.somefood.board.service.BoardService;
import com.somefood.board.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boardList(Model model) {
        List<Board> boards = boardService.findBoardList();
        model.addAttribute("boards", boards);
        return "board/items";
    }

    @GetMapping("/{boardId}")
    public String boardDetails(@PathVariable Long boardId, Model model) {
        Optional<Board> board = boardService.findBoard(boardId);
        board.ifPresent(value -> model.addAttribute("board", value));
        return "board/item";
    }

    @GetMapping("/add-form")
    public String boardCreate(Model model) {
        model.addAttribute("board", new BoardDto());
        return "board/addForm";
    }

    @PostMapping("/add-form")
    public String boardAdd(@ModelAttribute BoardDto boardDto) {
        log.info(boardDto.getTitle(), boardDto.getContent(), boardDto.getCategoryType());
        Board saved = boardService.createBoard(boardDto);
        return "redirect:/board";
    }

    @DeleteMapping("/{boardId}")
    @ResponseBody
    public String boardRemove(@PathVariable Long boardId) {
        boardService.removeBoard(boardId);
        return "ok";
    }
}
