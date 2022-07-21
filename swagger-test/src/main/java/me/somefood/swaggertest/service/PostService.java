package me.somefood.swaggertest.service;

import me.somefood.swaggertest.dto.request.PostRequestDto;
import me.somefood.swaggertest.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAllPost();

    Post findPost(Long postId);

    Post createPost(PostRequestDto postRequestDto);

    Post updatePost(Long PostId, PostRequestDto postRequestDto);

    void deletePost(Long PostId);
}
