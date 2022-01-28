package com.somefood.board.controller;

import com.somefood.board.domain.Member;
import com.somefood.board.web.SessionConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String mainPage(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        if (loginMember == null) {
            return "redirect:/login";
        }

        model.addAttribute("member", loginMember);

        return "redirect:/board";
    }
}
