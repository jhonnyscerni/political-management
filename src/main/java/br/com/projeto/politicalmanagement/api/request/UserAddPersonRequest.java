package br.com.projeto.politicalmanagement.api.request;

import br.com.projeto.politicalmanagement.models.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserAddPersonRequest {

    private String username;

    private String password;

    Set<Role> roles = new HashSet<>();

}
