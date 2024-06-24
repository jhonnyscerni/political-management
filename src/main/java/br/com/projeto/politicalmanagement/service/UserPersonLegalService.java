package br.com.projeto.politicalmanagement.service;

import br.com.projeto.politicalmanagement.api.request.UserPersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.filter.UserPersonLegalFilter;
import br.com.projeto.politicalmanagement.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPersonLegalService {

    UserResponse findByPersonLegalIdUserUserPersonLegalResponse(UUID personId);

    UserResponse create(UserPersonLegalRequest userPersonLegalRequest);

    UserResponse update(UUID id, UserPersonLegalRequest userPersonLegalRequest);

    void passwordNotEquals(User user, UserPersonLegalRequest userPersonLegalRequest);

    Page<UserResponse> search(UserPersonLegalFilter filter, Pageable pageable);

    Page<UserResponse> searchMy(UserPersonLegalFilter filter, Pageable pageable);

    UserResponse createUserEvent(UserPersonLegalRequest userPersonLegalRequest);

    UserResponse updateUserEvent(UUID id, UserPersonLegalRequest userPersonLegalRequest);
}
