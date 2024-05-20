package com.example.be.mapper;

import com.example.be.dto.UserDto;
import com.example.be.models.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface UserMapper {


    UserDto toDTO(User user);

    User toEntity(UserDto userDto);

}
