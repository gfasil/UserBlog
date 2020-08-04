package com.fayaman.userblog.service;

import com.fayaman.userblog.model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAllUserArticles(List<String> articleIds);
}
