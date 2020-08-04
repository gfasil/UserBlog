package com.fayaman.userblog.repositories;

import com.fayaman.userblog.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
}