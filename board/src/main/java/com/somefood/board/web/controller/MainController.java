package com.somefood.board.web.controller;

import com.somefood.board.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class MainController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "main";
    }
}
