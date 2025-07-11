package com.example.demo.Mapper;

import org.mapstruct.Mapper;

import com.example.demo.ModelDTO.NotificationsDTO;
import com.example.demo.ModelDomain.Notifications;

@Mapper(componentModel = "spring")

public interface NorificationsMapper
 {


    NotificationsDTO toDto(Notifications notifications);
}
