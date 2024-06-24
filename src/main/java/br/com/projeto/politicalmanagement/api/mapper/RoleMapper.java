package br.com.projeto.politicalmanagement.api.mapper;

import br.com.projeto.politicalmanagement.api.request.RoleRequest;
import br.com.projeto.politicalmanagement.api.response.RoleResponse;
import br.com.projeto.politicalmanagement.models.Role;
import br.com.projeto.politicalmanagement.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper extends ModelMapper<Role, RoleResponse> {

    Role create(RoleRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget Role entity, RoleRequest model);
}
