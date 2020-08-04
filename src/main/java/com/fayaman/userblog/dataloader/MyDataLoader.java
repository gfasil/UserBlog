package com.fayaman.userblog.dataloader;

import com.fayaman.userblog.model.Article;
import com.fayaman.userblog.model.User;
import com.fayaman.userblog.repositories.ArticleRepository;
import com.fayaman.userblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class MyDataLoader {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    @Autowired
    MyDataLoader(UserRepository userRepository, ArticleRepository articleRepository){
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }
  //    @PostConstruct
    private void generateData(){
        List<User> users = new ArrayList<>();
        users.add(User.builder().name("Igor").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Ellen").createdAt(new Date()).age(24).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("John").createdAt(new Date()).age(53).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users.add(User.builder().name("Nazar").createdAt(new Date()).age(14).articlesIds(new ArrayList<>()).articlesIds(new ArrayList<>()).build());
        users = (ArrayList<User>) userRepository.saveAll(users);
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder().title("Java 8 Lambdas").minutesRead(8).authorId(users.get(0).getId().toString()).build());
        articles.add(Article.builder().title("GraphQL Getting Started").minutesRead(10).authorId(users.get(1).getId().toString()).build());
        articles.add(Article.builder().title("Spring Boot + WebSockets").minutesRead(6).authorId(users.get(3).getId().toString()).build());
        articles = (ArrayList<Article>) articleRepository.saveAll(articles);
        User me = users.get(0);
        users.get(0).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
        users.get(0).setArticlesIds(Arrays.asList(articles.get(1).getId().toHexString()));
        users.get(1).setArticlesIds(Arrays.asList(articles.get(2).getId().toHexString()));
        users.get(3).setArticlesIds(Arrays.asList(articles.get(0).getId().toHexString()));
        userRepository.saveAll(users);
        List<String> myFriendsIds = new ArrayList<>();
        for (int i = 1; i<users.size(); i++){
            myFriendsIds.add(users.get(i).getId().toHexString());
        }
        me.setFriendsIds(myFriendsIds);
        userRepository.save(me);
    }
}
