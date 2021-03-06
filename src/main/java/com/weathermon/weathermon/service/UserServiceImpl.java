package com.weathermon.weathermon.service;

import com.weathermon.weathermon.model.User;
import com.weathermon.weathermon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> createAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User read(int id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean update(User user, int id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
