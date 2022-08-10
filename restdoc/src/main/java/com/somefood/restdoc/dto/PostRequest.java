package com.somefood.restdoc.dto;

import com.somefood.restdoc.entity.Post;
import lombok.Data;

@Data
public class PostRequest {

    private String title;

    private String content;
}
