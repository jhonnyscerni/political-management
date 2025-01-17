package br.com.projeto.politicalmanagement.service;

import br.com.projeto.politicalmanagement.api.request.UserAddPersonRequest;
import br.com.projeto.politicalmanagement.api.request.UserRequest;
import br.com.projeto.politicalmanagement.api.response.UserPersonLegalResponse;
import br.com.projeto.politicalmanagement.api.response.UserPersonPhysicalResponse;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.filter.UserFilter;
import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.models.enums.PersonType;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {


    void disassociateRole(UUID userId, UUID roleId);

    void connectRole(UUID userId, UUID roleId);

    User buscarOuFalhar(UUID usuarioId);

    UserResponse findById(UUID id);

    User buscarOuFalharPorEmail(String email);

    Page<UserResponse> search(UserFilter filter, Pageable pageable);

    Page<UserResponse> searchMy(UserFilter filter, Pageable pageable);


    void delete(UUID id);

    @Transactional
    UserResponse resetPassword(String email);

    UserResponse update(UUID userId, UserRequest userRequest);

    void existsByUserName(User cliente, String username);

    void passwordNotEquals(User user, UserRequest userPersonPhysicalRequest);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest, PersonType personType);

    UserPersonPhysicalResponse findByIdPersonPhysical(UUID userId);

    UserPersonLegalResponse findByIdPersonLegal(UUID userId);
}
