package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.web.dto.BoardDto;
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
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board createBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        Category category = categoryRepository.findByType(boardDto.getCategoryType());
        board.setCategory(category);
        return boardRepository.save(board);
    }

    @Transactional
    public Long update(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        board.update(boardDto.getTitle(), boardDto.getContent());

        return id;
    }

    public Optional<Board> findBoard(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findBoardList(CategoryType categoryType) {
        if (categoryType == null) return boardRepository.findAll();
        Category category = categoryService.findCategoryByType(categoryType);
        return boardRepository.findAllByCategory(category);
    }

    @Transactional
    public Board modifyBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public void removeBoard(Long id) {
        Board board = findBoard(id).orElseGet(null);
        boardRepository.delete(board);
    }

//    public Board findByName(String name) {}
}
