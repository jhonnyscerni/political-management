package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.PersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonLegalResponse;
import br.com.projeto.politicalmanagement.models.PersonLegal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonLegalMapper {

    PersonLegal create(PersonLegalRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget PersonLegal entity, PersonLegalRequest model);

    PersonLegalResponse toResponse(PersonLegal entity);

    PersonLegal toEntity(PersonLegalResponse model);
}
