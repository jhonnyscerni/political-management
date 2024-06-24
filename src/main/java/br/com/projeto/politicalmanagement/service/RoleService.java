package br.com.projeto.politicalmanagement.service;


import br.com.projeto.politicalmanagement.api.request.RoleRequest;
import br.com.projeto.politicalmanagement.api.response.RoleResponse;
import br.com.projeto.politicalmanagement.models.Role;
import java.util.List;
import java.util.UUID;

public interface RoleService {

    Role findByRoleName(String roleType);

    Role buscarOuFalhar(UUID permisionId);

    List<RoleResponse> findAll();

    RoleResponse findByIdRoleResponse(UUID id);

    RoleResponse create(RoleRequest userRequest);

    RoleResponse update(UUID id, RoleRequest roleRequest);

    void delete(UUID id);

    void connectPermission(UUID roleId, UUID permissionId);

    void disassociatePermission(UUID roleId, UUID permissionId);
}
