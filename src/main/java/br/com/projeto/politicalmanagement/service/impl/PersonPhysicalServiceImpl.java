package br.com.projeto.politicalmanagement.service.impl;

import br.com.projeto.politicalmanagement.api.mapper.PersonPhysicalMapper;
import br.com.projeto.politicalmanagement.api.request.PersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.PersonPhysicalResponse;
import br.com.projeto.politicalmanagement.integration.client.GoogleClient;
import br.com.projeto.politicalmanagement.integration.client.dto.GoogleDTO;
import br.com.projeto.politicalmanagement.integration.feign.google.GeocodeResponse;
import br.com.projeto.politicalmanagement.integration.feign.google.GoogleMapsClient;
import br.com.projeto.politicalmanagement.models.PersonPhysical;
import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.models.exceptions.EntityInUseException;
import br.com.projeto.politicalmanagement.models.exceptions.EntityNotFoundException;
import br.com.projeto.politicalmanagement.repositories.PersonPhysicalRepository;
import br.com.projeto.politicalmanagement.repositories.UserRepository;
import br.com.projeto.politicalmanagement.service.PersonPhysicalService;
import br.com.projeto.politicalmanagement.utils.GoogleMapsConstants;
import br.com.projeto.politicalmanagement.utils.LogicVerifyPersonTypeLogin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class PersonPhysicalServiceImpl implements PersonPhysicalService {

    private final PersonPhysicalRepository personPhysicalRepository;

    private final PersonPhysicalMapper personPhysicalMapper;

    private final UserRepository userRepository;


    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;

    //private final GoogleClient googleClient;
    private final GoogleMapsClient googleMapsClient;

    private static final String MSG_OBJECT_IN_USE
            = "Person Physical %d cannot be removed as it is in use";


    @Override
    public PersonPhysical buscarOuFalhar(UUID personphysicalId) {
        log.debug("GET UUID personphysicalId received {} ", personphysicalId.toString());
        return personPhysicalRepository.findById(personphysicalId)
                .orElseThrow(() -> new EntityNotFoundException("There is no record of person physical", personphysicalId));
    }

    @Override
    public PersonPhysicalResponse create(UUID id, PersonPhysicalRequest personPhysicalRequest) {
        log.debug("POST UUID roleRequest {} ", id.toString());
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());

        personPhysicalRequest.setUserId(id);
        PersonPhysical personPhysical = personPhysicalMapper.create(personPhysicalRequest);
        setLongLatGoogleMaps(personPhysical);

        personPhysicalRepository.save(personPhysical);
        log.debug("POST create PersonPhysical saved set Gold Father in Company {} ", personPhysical.getName());
        log.info("PersonPhysical create successfully PersonPhysical {} ", personPhysical.getId());

        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    @Transactional
    public PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest) {
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());

        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(personPhysicalRequest);

        PersonPhysical personPhysical = personPhysicalMapper.create(personPhysicalRequest);

        setLongLatGoogleMaps(personPhysical);

        //personPhysical.setGodfather(padrinho);
        personPhysicalRepository.save(personPhysical);
        log.debug("POST create personPhysicalRequest id saved {} ", personPhysicalRequest.getId());
        log.info("PersonPhysical create successfully personPhysicalRequest id {} ", personPhysicalRequest.getId());
        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public List<PersonPhysicalResponse> findAll() {
        log.debug("GET PersonPhysicalResponse findAll");
        return personPhysicalRepository.findAll().stream().map(personPhysicalMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonPhysicalResponse update(UUID personphisicalId, PersonPhysicalRequest personPhysicalRequest) {
        log.debug("PUT UUID personphisicalId received {} ", personphisicalId.toString());
        log.debug("PUT PersonPhysicalRequest personPhysicalRequest received {} ", personPhysicalRequest.toString());
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);
        UUID userId = personPhysical.getUserId();

        personPhysicalMapper.update(personPhysical, personPhysicalRequest);
        personPhysical.setUserId(userId);

        setLongLatGoogleMaps(personPhysical);
        PersonPhysical save = personPhysicalRepository.save(personPhysical);

        log.debug("PUT update personphisicalId saved {} ", personphisicalId);
        log.info("PersonPhysical update successfully personphisicalId {} ", personphisicalId);
        return personPhysicalMapper.toResponse(save);
    }

    @Override
    public void delete(UUID id) {
        try {

            Optional<User> user = userRepository.findByPersonIdUserDto(id);
            if (user.isPresent()) {
                userRepository.delete(user.get());
            } else {
                personPhysicalRepository.deleteById(id);
            }

        } catch (EmptyResultDataAccessException e) {
            log.warn("Person Physical {} not found", id);
            throw new EntityNotFoundException("Person Physical not found");

        } catch (DataIntegrityViolationException e) {
            log.warn("Person Physical {} cannot be removed as it is in use", id);
            throw new EntityInUseException(
                    String.format(MSG_OBJECT_IN_USE, id));
        }
    }

    @Override
    public PersonPhysicalResponse findByIdResponse(UUID personphisicalId) {
        log.debug("GET findByIdResponse UUID personphisicalId {} ", personphisicalId);
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);
        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public List<PersonPhysicalResponse> findAllMy(UUID userId) {
        log.debug("GET List Physical My ");
        return personPhysicalRepository.findAllMy(userId).stream().map(personPhysicalMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Long countPersonPhysical(UUID aLong) {
        return personPhysicalRepository.countPersonPhysical(aLong);
    }

    @Override
    public Long countPersonPhysicalVoteIsConquistado(UUID aLong) {
        return personPhysicalRepository.countPersonIsVoteIsConquistado(aLong);
    }

    @Override
    public Long countPersonPhysicalVoteIsAConquistar(UUID aLong) {
        return personPhysicalRepository.countPersonVoteIsAConquistar(aLong);
    }

    @Override
    public Long countPersonPhysicalVoteIsPerdido(UUID aLong) {
        return personPhysicalRepository.countPersonVoteIsPerdido(aLong);
    }

    private void setLongLatGoogleMaps(PersonPhysical personPhysical) {
        //Av. Rodolfo Chermont, 236 Marambaia, Belem PA, Apt 1301 Coaraci
        GeocodeResponse geocode = googleMapsClient.geocode(
                personPhysical.getAddress().getStreet() + ", " +
                        personPhysical.getAddress().getNumber() + " " +
                        personPhysical.getAddress().getDistrict() + ", " +
                        personPhysical.getAddress().getNameCity() + " " +
                        personPhysical.getAddress().getState(),
                GoogleMapsConstants.API_KEY);
        personPhysical.getAddress().setLng(geocode.getResults().get(0).getGeometry().getLocation().getLng());
        personPhysical.getAddress().setLat(geocode.getResults().get(0).getGeometry().getLocation().getLat());
    }


}
