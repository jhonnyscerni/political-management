package br.com.projeto.politicalmanagement.api.response;

import java.util.UUID;
import lombok.Data;

@Data
public class PermissionResponse {

    private UUID id;

    private String name;

    private String description;

}
