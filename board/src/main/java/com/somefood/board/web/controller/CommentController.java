package com.somefood.board.web.controller;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.service.BoardService;
import com.somefood.board.service.CommentService;
import com.somefood.board.web.dto.CommentDto;
import com.somefood.board.web.dto.ajax.CommentDeleteDto;
import com.somefood.board.web.dto.ajax.CommentUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @ResponseBody
    @PostMapping("/update/{commentId}")
    public CommentUpdateDto commentEdit(@PathVariable Long commentId, CommentDto commentDto) {
        log.info("comment update {} {}", commentId, commentDto);
        commentService.update(commentId, commentDto);
        return new CommentUpdateDto(true);
    }

    @GetMapping("/delete/{commentId}")
    @ResponseBody
    public CommentDeleteDto commentAdd(@PathVariable Long commentId) {

        Comment comment = commentService.findComment(commentId);
        Board board = comment.getBoard();
        commentService.removeComment(commentId);

        return new CommentDeleteDto(true);
    }

    @PostMapping("/{boardId}")
    public String commentAdd(@PathVariable Long boardId, @ModelAttribute CommentDto commentDto) {

        log.info("comment={}", commentDto);
        Optional<Board> optional = boardService.findBoard(boardId);
        Board board = optional.orElseGet(null);
        commentService.createComment(commentDto, board);

        return "redirect:/board/" + boardId;
    }
}
