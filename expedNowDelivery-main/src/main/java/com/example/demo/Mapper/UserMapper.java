package com.example.demo.Mapper;


import com.example.demo.ModelDomain.User;
import com.example.demo.ModelDTO.UserDTO;
import com.example.demo.ModelDTO.UserSaveDTO;
import com.example.demo.ModelDTO.UserUpdated;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring") 
public interface UserMapper

{

    UserDTO toDto(User user);
    
    List<UserDTO> toDtoList(List<User> users);

    User toEntity(UserDTO  userDTO);

    User toEntity(UserSaveDTO userSaveDto);

    User toEntity(UserUpdated userUpdated);



    
}


