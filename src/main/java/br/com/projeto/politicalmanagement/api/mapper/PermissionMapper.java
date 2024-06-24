package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.PermissionRequest;
import br.com.projeto.politicalmanagement.api.response.PermissionResponse;
import br.com.projeto.politicalmanagement.models.Permission;
import br.com.projeto.politicalmanagement.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends ModelMapper<Permission, PermissionResponse> {

    Permission create(PermissionRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget Permission entity, PermissionRequest model);
}
