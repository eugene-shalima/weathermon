package com.weathermon.weathermon.userutil;

import com.weathermon.weathermon.model.User;
import com.weathermon.weathermon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TestUsersCreator {

    private static final String DEFAULT_NAME = "TestUser";

    private static UserService userService;

    @Autowired
    private TestUsersCreator(UserService userService) {
        TestUsersCreator.userService = userService;
    }

    public static List<User> createUsers(int count) {
        List<User> users = createUserList(count);
        if (users == null) {
            return Collections.emptyList();
        }
        return userService.createAll(users);
    }

    private static List<User> createUserList(int count) {
        if (count <= 0) {
            return null;
        }
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User u = new User();
            u.setName(DEFAULT_NAME + i);
            u.setEmail(u.getName().toLowerCase() + "@gmail.com");
            users.add(u);
        }
        return users;
    }

}
