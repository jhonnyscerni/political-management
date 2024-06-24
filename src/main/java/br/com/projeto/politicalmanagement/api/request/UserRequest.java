package br.com.projeto.politicalmanagement.api.request;

import br.com.projeto.politicalmanagement.models.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserRequest {

    private UUID id;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

}
