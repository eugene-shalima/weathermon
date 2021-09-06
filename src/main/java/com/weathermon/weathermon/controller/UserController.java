package com.weathermon.weathermon.controller;

import com.weathermon.weathermon.model.User;
import com.weathermon.weathermon.service.UserService;
import com.weathermon.weathermon.userutil.TestUsersCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users_count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readUsersCount() {
        long usersCount = userService.count();
        System.out.println("users_count: " + usersCount);

        return new ResponseEntity<>(usersCount, HttpStatus.OK);
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> read() {
        System.out.println("read");
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add_user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAll(@RequestBody List<User> users) {
        if (users != null && !users.isEmpty()) {
            userService.createAll(users);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/add_test_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTestUsers(@RequestParam(required = false, defaultValue = "0") Integer count) {
        List<User> createdUsers = TestUsersCreator.createUsers(count);
        boolean createdAllSuccessfully = count == createdUsers.size();
        if (count > 100000) {
            Integer createdUsersCount = createdUsers.size();
            return createdAllSuccessfully
                    ? new ResponseEntity<>(createdUsersCount.toString(), HttpStatus.OK)
                    : new ResponseEntity<>(createdUsersCount.toString(), HttpStatus.I_AM_A_TEAPOT);
        }
        return createdAllSuccessfully
                ? new ResponseEntity<>(createdUsers, HttpStatus.OK)
                : new ResponseEntity<>(createdUsers, HttpStatus.I_AM_A_TEAPOT);
    }

}
