package com.somefood.board.controller;

import com.somefood.board.domain.Board;
import com.somefood.board.domain.Category;
import com.somefood.board.service.BoardService;
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

    @PostMapping
    public String boardAdd(Board board) {
        Board saved = boardService.addBoard(board);
        return "redirect:board/";
    }

    @DeleteMapping("/{boardId}")
    @ResponseBody
    public String boardRemove(@PathVariable Long boardId) {
        boardService.removeBoard(boardId);
        return "ok";
    }
}
