package br.com.projeto.politicalmanagement.service;

import br.com.projeto.politicalmanagement.api.request.UserPersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.filter.UserPersonPhysicalFilter;
import br.com.projeto.politicalmanagement.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPersonPhysicalService {

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse update(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(UUID personId);

    Page<UserResponse> search(UserPersonPhysicalFilter filter, Pageable pageable);

    Page<UserResponse> searchMy(UserPersonPhysicalFilter filter, Pageable pageable);

    UserResponse createUserEvent(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse updateUserEvent(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest);
}
