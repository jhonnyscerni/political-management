package br.com.projeto.politicalmanagement.api.response;

import br.com.projeto.politicalmanagement.models.enums.UserStatus;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String password;

    private UserStatus userStatus;

    private PersonResponse person;

    private Set<RoleResponse> roles = new HashSet<>();

}
