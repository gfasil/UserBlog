package com.fayaman.userblog.service;

import com.fayaman.userblog.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findOneById(ObjectId id);
    List<User> findByIdIn(List<String> ids);
}
