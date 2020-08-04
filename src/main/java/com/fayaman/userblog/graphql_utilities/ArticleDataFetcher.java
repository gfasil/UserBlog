package com.fayaman.userblog.graphql_utilities;

import com.fayaman.userblog.model.Article;
import com.fayaman.userblog.model.User;
import com.fayaman.userblog.service.ArticleService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDataFetcher implements DataFetcher<List<Article>> {

    private final ArticleService articleService;

    @Autowired
   public  ArticleDataFetcher(ArticleService articleService){
        this.articleService = articleService;
    }

    @Override
    public List<Article> get(DataFetchingEnvironment env) {
        User user = env.getSource();
        List<String> articleIds = new ArrayList<>();
        if(user!=null){
            articleIds = user.getArticlesIds();
        }
        List<Article> articles = articleService.findAllUserArticles(articleIds);
        return articles;
    }
}
