package com.somefood.board.web.controller;

import com.somefood.board.service.UserService;
import com.somefood.board.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        log.info("login controller");
        return "user/login";
    }

    @GetMapping("/register")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/register";
    }

    @PostMapping("/register")
    public String joinUser(@ModelAttribute UserDto userDto) {
        log.info("join user");
        userService.join(userDto);
        return "user/welcome";
    }
}
