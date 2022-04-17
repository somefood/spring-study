package com.somefood.board.web.controller;

import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.user.Account;
import com.somefood.board.service.CommentService;
import com.somefood.board.service.UserService;
import com.somefood.board.web.dto.UserDto;
import com.somefood.board.web.dto.ajax.CheckOwnDto;
import com.somefood.board.web.dto.ajax.CheckUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CommentService commentService;

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

    @GetMapping("/check-login")
    public HttpEntity<CheckUserDto> checkLogin(Authentication authentication) {
        CheckUserDto checkUserDto = new CheckUserDto();
        HttpStatus status;
        if (authentication == null) {
            checkUserDto.setLogin(false);
            status = HttpStatus.BAD_REQUEST;
        } else {
            checkUserDto.setLogin(true);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(checkUserDto, status);
    }

    @PostMapping("/check-own")
    public HttpEntity<CheckOwnDto> checkOwn(@RequestParam Long id, Authentication authentication) {
        log.info("check own id: {}", id);
        Comment comment = commentService.findComment(id);
        CheckOwnDto checkOwnDto = new CheckOwnDto();
        HttpStatus status;
        if (authentication == null) {
            checkOwnDto.setOwn(false);
            status = HttpStatus.BAD_REQUEST;
        } else {
            Account account = (Account) authentication.getPrincipal();
            String currentUsername = account.getUsername();
            String commentUsername = comment.getWriter().getUsername();
            log.info("checkown currnet {} comment {}", currentUsername, commentUsername);
            if (commentUsername.equals(currentUsername)) {
                checkOwnDto.setOwn(true);
                status = HttpStatus.OK;
            } else {
                checkOwnDto.setOwn(false);
                status = HttpStatus.BAD_REQUEST;
            }
        }

        return new ResponseEntity<>(checkOwnDto, status);
    }
}
