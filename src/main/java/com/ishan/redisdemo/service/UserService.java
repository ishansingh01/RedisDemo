package com.ishan.redisdemo.service;


import com.ishan.redisdemo.entity.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService{

    private final Map<Long, User> fakeDb = new HashMap<>();

    public UserService(){
        User user1 = new User(1L, "user1", "user1@gmail.com");
        User user2 = new User(2L, "user2", "user2@gmail.com");
        User user3 = new User(3L, "user3", "user3@gmail.com");
        fakeDb.put(user1.getId(), user1);
        fakeDb.put(user2.getId(), user2);
        fakeDb.put(user3.getId(), user3);
    }

    @CachePut(value = "users", key = "#user.id")
    public User save(User user){
        System.out.println("Save user into fake DB....");
        System.out.println("user id :"+user.getId());
        fakeDb.put(user.getId(), user);
        return user;
    }

    @Cacheable(value = "users", key="#id")
    public User getUser(Long id){
        System.out.println("Getting user from fake DB....");
        System.out.println("user id :"+id);
        return fakeDb.get(id);
    }

    public List<User> getAllUser() {
        List<User> userArrayList = new ArrayList<>(fakeDb.values());
        return userArrayList;
    }
}

