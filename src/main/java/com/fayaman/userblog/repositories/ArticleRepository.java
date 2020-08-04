package com.fayaman.userblog.repositories;

import com.fayaman.userblog.model.Article;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, ObjectId> {
   @NotNull
   public  List<Article> findAll();

   List<Article> findByIdIn(List<String> articleIds);
}
