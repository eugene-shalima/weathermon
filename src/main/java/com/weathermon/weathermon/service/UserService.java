package com.weathermon.weathermon.service;

import com.weathermon.weathermon.model.User;

import java.util.List;

public interface UserService {

    long count();

    void create(User user);

    List<User> createAll(List<User> users);

    List<User> readAll();

    User read(int id);

    boolean update(User user, int id);

    boolean delete(int id);

}
