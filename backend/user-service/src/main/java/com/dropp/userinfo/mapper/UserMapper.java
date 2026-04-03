package com.dropp.userinfo.mapper;

import com.dropp.userinfo.dto.UserDto;
import com.dropp.userinfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDtoToUser(UserDto userDto);

    UserDto mapUserToUserDto(User user);
}
