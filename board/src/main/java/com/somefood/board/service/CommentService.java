package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.comment.CommentRepository;
import com.somefood.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findComment(Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional
    public Comment createComment(CommentDto commentDto, Board board) {
        Comment comment = commentDto.toEntity();
        comment.setBoard(board);
        return commentRepository.save(comment);
    }

    @Transactional
    public void removeComment(Long id) {
        Comment comment = findComment(id);
        commentRepository.delete(comment);
    }

    @Transactional
    public void update(Long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.update(commentDto);
    }
}
