package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.domain.user.Account;
import com.somefood.board.domain.user.UserRepository;
import com.somefood.board.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board createBoard(BoardDto boardDto, Authentication authentication) {
        Board board = boardRepository.save(boardDto.toEntity());
        Category category = categoryRepository.findByType(boardDto.getCategoryType());
        Account account = (Account) authentication.getPrincipal();
        board.setCategory(category);
        board.setWriter(account);
        return board;
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

    public Page<Board> findPage(Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        log.info("pageSize={}", pageSize);
        log.info("currentPage={}", currentPage);
        log.info("startItem={}", startItem);

        Page<Board> all = boardRepository.findAll(pageable);
        return all;
    }

    public Page<Board> findPage(CategoryType type, Pageable pageable) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        log.info("pageSize={}", pageSize);
        log.info("currentPage={}", currentPage);
        log.info("startItem={}", startItem);

        Category findCategory = categoryService.findCategoryByType(type);

        return boardRepository.findAllByCategory(findCategory, pageable);
    }

    @Transactional
    public Board modifyBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public CategoryType removeBoard(Long id) {
        Board board = findBoard(id).orElseGet(null);
        Category category = board.getCategory();
        boardRepository.delete(board);
        return category.getType();
    }

//    public Board findByName(String name) {}
}
