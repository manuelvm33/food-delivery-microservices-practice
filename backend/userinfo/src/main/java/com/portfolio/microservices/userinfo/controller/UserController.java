package com.portfolio.microservices.userinfo.controller;

import com.portfolio.microservices.userinfo.dto.UserDto;
import com.portfolio.microservices.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable Long userId){
        UserDto fetchedUser = userService.getUserById(userId);
        return ResponseEntity.ok(fetchedUser);
    }
}
