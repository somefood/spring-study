package com.somefood.restdoc.repository;

import com.somefood.restdoc.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
