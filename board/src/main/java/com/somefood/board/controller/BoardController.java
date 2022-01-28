package com.somefood.board.controller;

import com.somefood.board.domain.Board;
import com.somefood.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

//@RestController("/board")
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/{boardId}")
    public String board(@PathVariable Long boardId, Model model) {
        Optional<Board> board = boardRepository.findById(boardId);
        board.ifPresent(value -> model.addAttribute("board", value));
        return "board/item";
    }

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/items";
    }

    @PostMapping
    public String createBoard(Board board) {
        Board saved = boardRepository.save(board);
        return "redirect:board/";
    }

    @DeleteMapping("/{boardId}")
    @ResponseBody
    public String deleteBoard(@PathVariable Long boardId) {
        boardRepository.remove(boardId);
        return "ok";
    }

    @PostConstruct
    public void init() {
        boardRepository.save(new Board("게시글1", "내용1"));
        boardRepository.save(new Board("게시글2", "내용2"));
    }
}
