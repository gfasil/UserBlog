package com.fayaman.userblog.repositories;

import com.fayaman.userblog.model.Article;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, ObjectId> {
}
