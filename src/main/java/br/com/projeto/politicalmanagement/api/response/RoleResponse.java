package br.com.projeto.politicalmanagement.api.response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class RoleResponse {

    private UUID id;

    private String name;

    private List<PermissionResponse> permissions = new ArrayList<>();

}