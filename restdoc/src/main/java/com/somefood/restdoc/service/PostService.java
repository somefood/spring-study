package com.somefood.restdoc.service;

import com.somefood.restdoc.dto.PostRequest;
import com.somefood.restdoc.dto.PostResponse;
import com.somefood.restdoc.entity.Post;
import com.somefood.restdoc.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostResponse create(PostRequest postRequest) {
        Post post = Post.builder().title(postRequest.getTitle()).content(postRequest.getContent()).build();
        Post savedPost = postRepository.save(post);
        return new PostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    public PostResponse findById(Long postId) {
        Optional<Post> optional = postRepository.findById(postId);
        Post post = optional.orElseThrow(() -> {
            throw new IllegalArgumentException("찾는 포스트 없습니다.");
        });
        return new PostResponse(postId, post.getTitle(), post.getContent());
    }

    @Transactional
    public void update(Long postId, PostRequest postRequest) {
        Optional<Post> optional = postRepository.findById(postId);
        Post post = optional.orElseThrow(() -> {
            throw new IllegalArgumentException("찾는 포스트 없습니다.");
        });

        post.update(postRequest);
    }

    public void delete(Long postId) {
        Optional<Post> optional = postRepository.findById(postId);
        optional.ifPresent(postRepository::delete);
    }

    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(p -> new PostResponse(p.getId(), p.getTitle(), p.getContent()))
                .collect(Collectors.toList());
    }
}
