package com.fayaman.userblog.graphql_utilities;

import com.fayaman.userblog.model.User;
import com.fayaman.userblog.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDataFetcher implements DataFetcher<User> {
    private final UserService userService;

    @Autowired
    UserDataFetcher(UserService userService){
        this.userService = userService;
    }

    @Override
    public User get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        return userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
    }
}
