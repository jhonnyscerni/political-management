package br.com.projeto.politicalmanagement.api.response;

import br.com.projeto.politicalmanagement.models.enums.VoteEnum;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonLegalResponse {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cnpj;

    private UUID userId;

}
