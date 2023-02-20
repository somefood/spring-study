package me.seokju.mybatisstudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.seokju.mybatisstudy.dao.MemberDao;
import me.seokju.mybatisstudy.vo.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberDao memberDao;

    @GetMapping("/members")
    public List<Member> getMemberList() {
        return memberDao.findAll();
    }
}
