package com.example.demo.Mapper;


import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDomain.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserDTO toDto(User user);
    List<UserDTO> toDtoList(List<User> users);

    User toEntity(UserDTO dto);
}