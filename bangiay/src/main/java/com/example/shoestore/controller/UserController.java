package com.example.shoestore.controller;

import com.example.shoestore.dto.request.UserCreateRequest;
import com.example.shoestore.dto.request.UserUpdateRequest;
import com.example.shoestore.entity.User;
import com.example.shoestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users") // Đường dẫn chung cho tất cả các API trong controller này
public class UserController {
    @Autowired
    public UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreateRequest request){// Anotatoin @RequestBody để map dữ liệu từ request body vào đối tượng UserCreateRequest
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {//map "/{userId}" vao  String userId
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

}
