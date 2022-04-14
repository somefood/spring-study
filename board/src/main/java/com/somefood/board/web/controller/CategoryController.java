package com.somefood.board.web.controller;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.service.BoardService;
import com.somefood.board.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/category")
public class CategoryController {

    private final BoardService boardService;
    private final CategoryService categoryService;

    @GetMapping
    public String boardList(Model model, @PageableDefault Pageable pageable) {

        log.info("[GET] boardPage");
        log.info("[GET] pageable {}", pageable);

        Page<Board> findBoards = boardService.findPage(pageable);

        model.addAttribute("boards", findBoards);

        return "board/boardList";
    }

    @GetMapping("/{type}")
    public String boardListByCategory(@PathVariable CategoryType type, @PageableDefault Pageable pageable, Model model) {

        log.info("[GET] boardPage {}", type);
        log.info("[GET] pageable {}", pageable);

        Page<Board> findBoardByCategory = boardService.findPage(type, pageable);

        model.addAttribute("boards", findBoardByCategory);

        return "board/boardList";
    }
}
