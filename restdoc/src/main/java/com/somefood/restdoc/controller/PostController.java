package com.somefood.restdoc.controller;

import com.somefood.restdoc.dto.PostRequest;
import com.somefood.restdoc.dto.PostResponse;
import com.somefood.restdoc.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody final PostRequest postRequest) {
        final PostResponse postResponse = postService.create(postRequest);
        return ResponseEntity.created(URI.create("/posts/" + postResponse.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findById(@PathVariable final Long postId) {
        return ResponseEntity.ok(postService.findById(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable final Long postId, @RequestBody PostRequest postRequest) {
        postService.update(postId, postRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable final Long postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
