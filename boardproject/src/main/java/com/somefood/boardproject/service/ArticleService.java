package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.Article;

public interface ArticleService {

    Article getArticle(Long id);

    Article createArticle(Article article);

    void removeArticle(Long id);
}
