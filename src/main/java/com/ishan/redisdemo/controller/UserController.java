package com.ishan.redisdemo.controller;


import com.ishan.redisdemo.entity.User;
import com.ishan.redisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return user!=null? ResponseEntity.ok(user):ResponseEntity.notFound().build();
    }

    @GetMapping("/allUser")
    public List<User> getAll(){
        return userService.getAllUser();
    }
}
