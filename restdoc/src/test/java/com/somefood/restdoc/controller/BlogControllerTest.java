package com.somefood.restdoc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BlogControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    public void get() throws Exception {
//
//        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("name", "칩");
//        info.add("id", "chip");
//
//        mockMvc.perform(get("/blog")       // 1, 2
//                        .params(info))              // 3
//                .andExpect(status().isOk())     // 4
//                .andExpect(content().string("칩의 블로그입니다. chip"))
//                .andDo(print());                // 5
//    }

}