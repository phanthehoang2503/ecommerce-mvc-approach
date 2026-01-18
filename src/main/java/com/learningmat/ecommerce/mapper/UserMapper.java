package com.learningmat.ecommerce.mapper;

import com.learningmat.ecommerce.dto.request.UserCreateRequest;
import com.learningmat.ecommerce.dto.request.UserUpdateRequest;
import com.learningmat.ecommerce.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
