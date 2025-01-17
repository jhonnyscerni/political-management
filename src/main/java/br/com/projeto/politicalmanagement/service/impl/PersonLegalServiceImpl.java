package br.com.projeto.politicalmanagement.service.impl;

import br.com.projeto.politicalmanagement.api.mapper.PersonLegalMapper;
import br.com.projeto.politicalmanagement.api.request.PersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonLegalResponse;
import br.com.projeto.politicalmanagement.integration.client.GoogleClient;
import br.com.projeto.politicalmanagement.integration.client.dto.GoogleDTO;
import br.com.projeto.politicalmanagement.models.PersonLegal;
import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.models.exceptions.EntityInUseException;
import br.com.projeto.politicalmanagement.models.exceptions.EntityNotFoundException;
import br.com.projeto.politicalmanagement.repositories.PersonLegalRepository;
import br.com.projeto.politicalmanagement.repositories.UserRepository;
import br.com.projeto.politicalmanagement.service.PersonLegalService;
import br.com.projeto.politicalmanagement.utils.LogicVerifyPersonTypeLogin;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PersonLegalServiceImpl implements PersonLegalService {

    private final PersonLegalRepository personLegalRepository;
    private final PersonLegalMapper personLegalMapper;

    private final UserRepository userRepository;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;

    private final GoogleClient googleClient;

    private static final String MSG_OBJECT_IN_USE
        = "Person Legal %d cannot be removed as it is in use";


    @Override
    public PersonLegal buscarOuFalhar(UUID personlegalId) {
        log.debug("GET UUID personlegalId received {} ", personlegalId.toString());
        return personLegalRepository.findById(personlegalId)
            .orElseThrow(() -> new EntityNotFoundException("There is no record of person Legal", personlegalId));
    }

    @Override
    public PersonLegalResponse create(PersonLegalRequest personLegalRequest) {
        log.debug("POST PersonLegalRequest personLegalRequest {} ", personLegalRequest.toString());
        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(personLegalRequest);

        PersonLegal personLegal = personLegalMapper.create(personLegalRequest);
        //buscar Latitude e Longitudo do Endereço
        GoogleDTO googleDTO = googleClient.getLatLong(personLegal.getAddress());
        personLegal.getAddress().setLng(googleDTO.getResults().get(0).getGeometry().getLocation().getLng());
        personLegal.getAddress().setLat(googleDTO.getResults().get(0).getGeometry().getLocation().getLat());

        personLegalRepository.save(personLegal);
        log.debug("POST create personLegal saved {} ", personLegal.getId());
        log.info("User create successfully personLegal {} ", personLegal.getId());
        return personLegalMapper.toResponse(personLegal);
    }

    @Override
    public List<PersonLegalResponse> findAll() {
        log.debug("GET PersonLegalResponse findAll");
        return personLegalRepository.findAll().stream().map(personLegalMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonLegalResponse update(UUID personLegalId, PersonLegalRequest personLegalRequest) {
        log.debug("PUT UUID personLegalId received {} ", personLegalId.toString());
        log.debug("PUT PersonLegalRequest personLegalRequest received {} ", personLegalRequest.toString());

        PersonLegal personLegal = buscarOuFalhar(personLegalId);
        UUID userId = personLegal.getUserId();

        personLegalMapper.update(personLegal, personLegalRequest);
        personLegal.setUserId(userId);

        //buscar Latitude e Longitudo do Endereço
        GoogleDTO googleDTO = googleClient.getLatLong(personLegal.getAddress());
        personLegal.getAddress().setLng(googleDTO.getResults().get(0).getGeometry().getLocation().getLng());
        personLegal.getAddress().setLat(googleDTO.getResults().get(0).getGeometry().getLocation().getLat());

        log.debug("PUT update personLegalId saved {} ", personLegal.getId());
        log.info("Person Legal update successfully personLegalId {} ", personLegal.getId());
        return personLegalMapper.toResponse(personLegalRepository.save(personLegal));
    }

    @Override
    public void delete(UUID personLegalId) {
        try {
            Optional<User> user = userRepository.findByPersonIdUserDto(personLegalId);
            if (user.isPresent()) {
                userRepository.delete(user.get());
            } else {
                personLegalRepository.deleteById(personLegalId);
            }

        } catch (EmptyResultDataAccessException e) {
            log.warn("Person Legal {} not found", personLegalId);
            throw new EntityNotFoundException("Person Legal not found");

        } catch (DataIntegrityViolationException e) {
            log.warn("Person Legal {} cannot be removed as it is in use", personLegalId);
            throw new EntityInUseException(
                String.format(MSG_OBJECT_IN_USE, personLegalId));
        }
    }

    @Override
    public PersonLegalResponse findByIdResponse(UUID empresaId) {
        PersonLegal personLegal = buscarOuFalhar(empresaId);
        return personLegalMapper.toResponse(personLegal);
    }

    @Override
    public List<PersonLegalResponse> findAllMy(UUID userId) {
        log.debug("GET List Physical My ");
        return personLegalRepository.findAllMy(userId).stream().map(personLegalMapper::toResponse)
            .collect(Collectors.toList());
    }

}
