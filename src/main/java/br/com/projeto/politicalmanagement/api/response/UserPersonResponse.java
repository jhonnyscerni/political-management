package br.com.projeto.politicalmanagement.api.response;

import java.util.UUID;
import lombok.Data;

@Data
public class UserPersonResponse {

    private UUID id;

    private String username;

    private String password;

}
