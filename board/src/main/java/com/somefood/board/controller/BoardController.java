package com.somefood.board.controller;

import com.somefood.board.domain.Board;
import com.somefood.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

//@RestController("/board")
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "board/item";
    }

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardRepository.findAll();
        System.out.println(boards);
        model.addAttribute("boards", boards);
        return "board/items";
    }

    @PostMapping
    public void createBoard() {}

    @PostConstruct
    public void init() {
        boardRepository.save(new Board("게시글1", "내용1"));
        boardRepository.save(new Board("게시글2", "내용2"));
    }
}
