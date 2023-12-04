package com.company.taskmanagementsystem.mapper;

import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.model.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegisterRequest registerRequest);
}
