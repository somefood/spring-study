package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.board.BoardRepository;
import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.domain.category.CategoryRepository;
import com.somefood.boardproject.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findAll(CategoryType categoryType) {
        if (categoryType == null) return boardRepository.findAll();
        Category category = categoryRepository.findCategoryByType(categoryType);
        return boardRepository.findAllByCategory(category);
    }

    public Board getBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.orElse(null);
    }

    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public void removeBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        optional.ifPresent(boardRepository::delete);
    }
}
