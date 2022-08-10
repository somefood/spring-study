package com.somefood.restdoc.controller;

import com.somefood.restdoc.dto.PostResponse;
import com.somefood.restdoc.service.PostService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void create() throws Exception {
        final PostResponse postResponse = new PostResponse(1L, "title", "content");
        when(postService.create(any())).thenReturn(postResponse);

        this.mockMvc.perform(post("/posts") // 1
                        .content("{\"title\": \"title\", \n\"content\": \"content\"}") // 2
                        .contentType(MediaType.APPLICATION_JSON)) // 3
                .andExpect(status().isCreated()) // 4
                .andDo(document("post-create", // 5
                        requestFields( // 6
                                fieldWithPath("title").description("Post 제목"), // 7
                                fieldWithPath("content").description("Post 내용").optional() // 8
                        )
                ));
    }

    @Test
    void findAll() throws Exception {
        List<PostResponse> postResponses = Lists.newArrayList(
                new PostResponse(1L, "title1", "content1"),
                new PostResponse(2L, "title2", "content2")
        );

        when(postService.findAll()).thenReturn(postResponses);

        this.mockMvc.perform(get("/posts")
                        .accept(MediaType.APPLICATION_JSON)) // 1
                .andExpect(status().isOk())
                .andDo(document("post-get-all",
                        responseFields( // 2
                                fieldWithPath("[].id").description("Post Id"), // 3
                                fieldWithPath("[].title").description("Post 제목"),
                                fieldWithPath("[].content").description("Post 내용")
                        )
                ));
    }

//    @Test
//    void findById() throws Exception {
//        final PostResponse postResponse = new PostResponse(1L, "title", "content");
//        when(postService.findById(anyLong())).thenReturn(postResponse);
//
//        this.mockMvc.perform(get("/post/{postId}", postResponse.getId()) // 4
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("post-get-one",
//                        pathParameters( // 5
//                                parameterWithName("postId").description("Post Id") // 6
//                        ),
//                        responseFields(
//                                fieldWithPath("id").description("Post Id"),
//                                fieldWithPath("title").description("Post 제목"),
//                                fieldWithPath("content").description("Post 내용")
//                        )
//                ));
//    }

//    @Test
//    void update() throws Exception {
//        this.mockMvc.perform(put("/post/{postId}", 1L)
//                        .content("{\"title\": \"turtle\", \n\"content\": \"context\"}")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("post-update",
//                        pathParameters(
//                                parameterWithName("postId").description("Post Id")
//                        ),
//                        requestFields(
//                                fieldWithPath("title").description("Post 제목"),
//                                fieldWithPath("content").description("Post 내용")
//                        )
//                ));
//    }
//
//    @Test
//    void remove() throws Exception {
//        this.mockMvc.perform(delete("/post/{postId}", 1L))
//                .andExpect(status().isNoContent())
//                .andDo(document("post-delete",
//                        pathParameters(
//                                parameterWithName("postId").description("Post Id")
//                        )
//                ));
//    }
}