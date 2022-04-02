package com.somefood.board.web.controller;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.service.BoardService;
import com.somefood.board.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String boardList(Model model, @PageableDefault Pageable pageable) {

        log.info("[GET] boardPage");

        Page<Board> findBoards = boardService.findPage(pageable);

        model.addAttribute("boards", findBoards);

        return "board/board_list";
    }

    @GetMapping("/{boardId}")
    public String boardDetails(@PathVariable Long boardId, Model model) {
        Optional<Board> board = boardService.findBoard(boardId);
        board.ifPresent(value -> model.addAttribute("board", value));
        return "board/board";
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
        return "redirect:/board/" + saved.getId();
    }

    @GetMapping("/{boardId}/edit")
    public String boardEdit(@PathVariable Long boardId, Model model) {
        Optional<Board> board = boardService.findBoard(boardId);
        board.ifPresent(value -> model.addAttribute("board", value));
        return "board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String boardUpdate(@PathVariable Long boardId, @ModelAttribute BoardDto boardDto) {
        Long id = boardService.update(boardId, boardDto);
        return "redirect:/board/" + id;
    }

    @GetMapping("/{boardId}/delete")
    public String boardRemove(@PathVariable Long boardId) {
        boardService.removeBoard(boardId);
        return "redirect:/board";
    }
}
