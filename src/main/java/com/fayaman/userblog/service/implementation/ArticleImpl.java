package com.fayaman.userblog.service.implementation;

import com.fayaman.userblog.model.Article;
import com.fayaman.userblog.repositories.ArticleRepository;
import com.fayaman.userblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Override
    public List<Article> findAllUserArticles(List<String> articleIds) {
        return articleRepository.findByIdIn(articleIds);
    }
}
