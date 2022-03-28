package com.somefood.boardproject.web.controller;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.CategoryType;
import com.somefood.boardproject.service.BoardService;
import com.somefood.boardproject.web.dto.BoardAddDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/add")
    public String addBoard(Model model) {
        model.addAttribute("board", new BoardAddDto());
        return "board/addForm";
    }

    @PostMapping("/add")
    public String createBoard(@ModelAttribute BoardAddDto boardAddDto, BindingResult bindingResult) {
        log.info("{}", bindingResult);
        Board board = boardService.createBoard(boardAddDto.toEntity());
        return "redirect:/board/ + " + board.getId();
    }

    @GetMapping("/{boardId}/edit")
    public String boardEdit(@PathVariable Long boardId, Model model) {
//        Optional<Board> board = boardService.findBoard(boardId);
//        board.ifPresent(value -> model.addAttribute("board", value));
        return "board/editForm";
    }

//    @PostMapping("/{boardId}/edit")
//    public String boardUpdate(@PathVariable Long boardId, @ModelAttribute BoardDto boardDto) {
//        Long id = boardService.update(boardId, boardDto);
//        return "redirect:/board/" + id;
//    }

    @GetMapping("/{boardId}/delete")
    public String boardRemove(@PathVariable Long boardId) {
        boardService.removeBoard(boardId);
        return "redirect:/board";
    }
}
