package br.com.projeto.politicalmanagement.filter;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserFilter {

    private String username;

    private String email;

    private UUID userId;

//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoInicio;
//
//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoFim;

}
