package com.somefood.boardproject.repository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertTest() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUemail("hihi@" + i);
            MemberRole role = new MemberRole();
            if (i <= 80) {
                role.setRoleName("BASIC");
            } else if (i <= 90) {
                role.setRoleName("MANAGER");
            } else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }
    }

    @Test
    public void testMember() {
        Optional<Member> result = memberRepository.findById(85L);
        result.ifPresent(member -> log.info("member " + member));
    }
}