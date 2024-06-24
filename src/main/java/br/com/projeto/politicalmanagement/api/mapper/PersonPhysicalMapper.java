package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.PersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonPhysicalResponse;
import br.com.projeto.politicalmanagement.models.PersonPhysical;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonPhysicalMapper {

    PersonPhysical create(PersonPhysicalRequest userRequest);

    void update(@MappingTarget PersonPhysical entity, PersonPhysicalRequest model);


    PersonPhysicalResponse toResponse(PersonPhysical entity);

    PersonPhysical toEntity(PersonPhysicalResponse model);

}
