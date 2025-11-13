package com.portfolio.microservices.userinfo.service;

import com.portfolio.microservices.userinfo.dto.UserDto;
import com.portfolio.microservices.userinfo.entity.User;
import com.portfolio.microservices.userinfo.mapper.UserMapper;
import com.portfolio.microservices.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserDto addUser(UserDto userDto){
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));
        return UserMapper.INSTANCE.mapUserToUserDto(savedUser);
    }
    public UserDto getUserById(Long userId){
        User userFetched = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.INSTANCE.mapUserToUserDto(userFetched);
    }
}
