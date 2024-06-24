package br.com.projeto.politicalmanagement.utils;

import br.com.projeto.politicalmanagement.api.request.PersonLegalRequest;
import br.com.projeto.politicalmanagement.api.request.PersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.request.UserPersonLegalRequest;
import br.com.projeto.politicalmanagement.api.request.UserPersonPhysicalRequest;
import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.models.exceptions.EntityNotFoundException;
import br.com.projeto.politicalmanagement.repositories.UserRepository;
import br.com.projeto.politicalmanagement.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LogicVerifyPersonTypeLogin {

    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;

    public void setUserIdLoggedPerson(PersonPhysicalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.setUserId(user.getId());
    }

    public void setUserIdLoggedPerson(PersonLegalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.setUserId(user.getId());
    }

    public void setUserIdLoggedPerson(UserPersonLegalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.getPerson().setUserId(user.getId());
    }

    public void setUserIdLoggedPerson(UserPersonPhysicalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.getPerson().setUserId(user.getId());
    }

    public User getLoggedUser() {
        UserDetails userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("Not found User Logged"));
    }

}
