package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.PersonLegalRequest;
import br.com.projeto.politicalmanagement.api.request.UserAddPersonRequest;
import br.com.projeto.politicalmanagement.api.request.UserPersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonLegalResponse;
import br.com.projeto.politicalmanagement.api.response.PersonResponse;
import br.com.projeto.politicalmanagement.api.response.UserPersonLegalResponse;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.models.Person;
import br.com.projeto.politicalmanagement.models.PersonLegal;
import br.com.projeto.politicalmanagement.models.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersonLegalMapper {


    User add(UserAddPersonRequest userPersonPhysicalRequest);

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    @Mapping(target = "person", expression = "java( ((PersonLegal) toEntityRequest(model.getPerson())))")
    void update(@MappingTarget User entity, UserPersonLegalRequest model);

    @InheritConfiguration
    @Mapping(target = "person", expression = "java( toEntityRequest(userPersonPhysicalRequest.getPerson()))")
    User create(UserPersonLegalRequest userPersonPhysicalRequest);


    PersonLegal toEntityRequest(PersonLegalRequest model);


    @Mapping(target = "person", expression = "java(toPersonLegalResponse((PersonLegal)entity.getPerson()))")
    UserPersonLegalResponse toResponseUserLegalPhysical(User entity);

    PersonLegalResponse toPersonLegalResponse(PersonLegal person);

}
