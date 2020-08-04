package com.fayaman.userblog.repositories;

import com.fayaman.userblog.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
    List<User> findByIdIn(List<String> ids);
}
