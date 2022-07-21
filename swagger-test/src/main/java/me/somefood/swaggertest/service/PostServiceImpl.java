package me.somefood.swaggertest.service;

import me.somefood.swaggertest.dto.request.PostRequestDto;
import me.somefood.swaggertest.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Override
    public List<Post> findAllPost() {
        return null;
    }

    @Override
    public Post findPost(Long postId) {
        return null;
    }

    @Override
    public Post createPost(PostRequestDto postRequestDto) {
        return null;
    }

    @Override
    public Post updatePost(Long PostId, PostRequestDto postRequestDto) {
        return null;
    }

    @Override
    public void deletePost(Long PostId) {

    }
}
