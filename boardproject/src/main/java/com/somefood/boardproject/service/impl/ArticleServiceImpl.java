package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Article;
import com.somefood.boardproject.repository.ArticleRepository;
import com.somefood.boardproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Article getArticle(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void removeArticle(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        optional.ifPresent(articleRepository::delete);
    }
}
