package com.somefood.boardproject.web.controller;

import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
