package me.somefood.swaggertest.repository;

import me.somefood.swaggertest.entity.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();

    Post findById(Long postId);

    Post save(Post post);

    Long update(Post post);

    void delete(Post post);
}
