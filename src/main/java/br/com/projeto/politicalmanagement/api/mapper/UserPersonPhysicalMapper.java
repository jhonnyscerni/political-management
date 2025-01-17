package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.PersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.request.UserAddPersonRequest;
import br.com.projeto.politicalmanagement.api.request.UserPersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonPhysicalResponse;
import br.com.projeto.politicalmanagement.api.response.PersonResponse;
import br.com.projeto.politicalmanagement.api.response.UserPersonPhysicalResponse;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.models.Person;
import br.com.projeto.politicalmanagement.models.PersonPhysical;
import br.com.projeto.politicalmanagement.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersonPhysicalMapper {

    @Mapping(target = "person", expression = "java( ((PersonPhysical) toEntityRequest(model.getPerson())))")
    void update(@MappingTarget User entity, UserPersonPhysicalRequest model);

    @Mapping(target = "person", expression = "java( toEntityRequest(userPersonPhysicalRequest.getPerson()))")
    User create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    User add(UserAddPersonRequest userPersonPhysicalRequest);

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    @Mapping(target = "userId", expression = "java(model.getUserId())")
    PersonPhysical toEntityRequest(PersonPhysicalRequest model);

    // USER Person Physical
    @Mapping(target = "person", expression = "java(toPersonPhysicalResponse((PersonPhysical)entity.getPerson()))")
    UserPersonPhysicalResponse toResponseUserPersonPhysical(User entity);

    PersonPhysicalResponse toPersonPhysicalResponse(PersonPhysical person);

}
