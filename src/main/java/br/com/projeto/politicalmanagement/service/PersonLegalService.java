package br.com.projeto.politicalmanagement.service;

import br.com.projeto.politicalmanagement.api.request.PersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonLegalResponse;
import br.com.projeto.politicalmanagement.models.PersonLegal;
import java.util.List;
import java.util.UUID;

public interface PersonLegalService {

    PersonLegal buscarOuFalhar(UUID personlegalId);

    PersonLegalResponse create(PersonLegalRequest personLegalRequest);

    List<PersonLegalResponse> findAll();

    PersonLegalResponse update(UUID personLegalId, PersonLegalRequest personLegalRequest);

    void delete(UUID personLegalId);

    PersonLegalResponse findByIdResponse(UUID personLegalId);

    List<PersonLegalResponse> findAllMy(UUID userId);
}
