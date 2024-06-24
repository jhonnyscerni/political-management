package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.UserRequest;
import br.com.projeto.politicalmanagement.api.response.PersonResponse;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.models.Person;
import br.com.projeto.politicalmanagement.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    void update(@MappingTarget User user, UserRequest userRequest);
}
