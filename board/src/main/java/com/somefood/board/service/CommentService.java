package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.comment.CommentRepository;
import com.somefood.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(CommentDto commentDto, Board board) {
        Comment comment = commentDto.toEntity();
        comment.setBoard(board);
        return commentRepository.save(comment);
    }
}
