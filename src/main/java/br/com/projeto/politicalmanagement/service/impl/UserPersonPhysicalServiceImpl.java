package br.com.projeto.politicalmanagement.service.impl;


import br.com.projeto.politicalmanagement.api.mapper.UserPersonPhysicalMapper;
import br.com.projeto.politicalmanagement.api.request.UserPersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.filter.UserPersonPhysicalFilter;
import br.com.projeto.politicalmanagement.models.PersonPhysical;
import br.com.projeto.politicalmanagement.models.Role;
import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.models.enums.RoleType;
import br.com.projeto.politicalmanagement.repositories.UserRepository;
import br.com.projeto.politicalmanagement.service.RoleService;
import br.com.projeto.politicalmanagement.service.UserPersonPhysicalService;
import br.com.projeto.politicalmanagement.service.UserService;
import br.com.projeto.politicalmanagement.utils.LogicVerifyPersonTypeLogin;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserPersonPhysicalServiceImpl implements UserPersonPhysicalService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final UserPersonPhysicalMapper userPersonPhysicalMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;

    //private final UserEventPublisher userEventPublisher;

    @Override
    @Transactional
    public UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST registerUser userDto received {} ", userPersonPhysicalRequest.toString());

        userService.existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(userPersonPhysicalRequest);

        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role pessoas = roleService.findByRoleName(RoleType.ROLE_PESSOAS.name());
        userPersonPhysicalRequest.getRoles().add(pessoas);

        User user = userPersonPhysicalMapper.create(userPersonPhysicalRequest);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userPersonPhysicalMapper.toResponse(user);

    }

    @Override
    @Transactional
    public UserResponse createUserEvent(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        UserResponse userResponse = create(userPersonPhysicalRequest);
        //userEventPublisher.publishUserEvent(userResponse.convertToUserEventResponse(), ActionType.CREATE);
        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("PUT UUID id received {} ", id.toString());
        log.debug("PUT UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());
        User user = userService.buscarOuFalhar(id);
        UUID userId = ((PersonPhysical) user.getPerson()).getUserId();

        userService.existsByUserName(user, userPersonPhysicalRequest.getUsername());


        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userPersonPhysicalRequest);

        userPersonPhysicalMapper.update(user, userPersonPhysicalRequest);

        PersonPhysical personPhysical = (PersonPhysical) user.getPerson();
        personPhysical.setUserId(userId);
        user.setPerson(personPhysical);

        User save = userRepository.save(user);
        log.debug("PUT update userId saved {} ", user.getId());
        log.info("User update successfully userId {} ", user.getId());
        return userPersonPhysicalMapper.toResponse(save);
    }

    @Override
    @Transactional
    public UserResponse updateUserEvent(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        UserResponse userResponse = update(id, userPersonPhysicalRequest);
        //userEventPublisher.publishUserEvent(userResponse.convertToUserEventResponse(), ActionType.UPDATE);
        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(UUID personId) {
        Optional<User> userOptional = userRepository.findByPersonIdUserDto(personId);

        if (userOptional.isPresent()) {
            return userPersonPhysicalMapper.toResponse(userOptional.get());
        }
        log.debug("GET UUID personId received {} ", userOptional);
        return userPersonPhysicalMapper.toResponse(new User());
    }

    @Override
    @Transactional
    public Page<UserResponse> search(UserPersonPhysicalFilter filter, Pageable pageable) {
        log.debug("GET UserFilter filter received {} ", filter.toString());
        List<User> users = userRepository.findAllUserPersonPhysical(filter, null);
        return new PageImpl<>(
            users, pageable, users.size()).map(userPersonPhysicalMapper::toResponse);

    }

    @Override
    public Page<UserResponse> searchMy(UserPersonPhysicalFilter filter, Pageable pageable) {
        List<User> users = userRepository.findAllUserPersonPhysical(filter, logicVerifyPersonTypeLogin.getLoggedUser().getId());
        return new PageImpl<>(
            users, pageable, users.size()).map(userPersonPhysicalMapper::toResponse);

    }

    @Override
    @Transactional
    public void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("Verify password {} ", user.getId());
        if (!user.getPassword().equals(userPersonPhysicalRequest.getPassword())) {
            userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        }
    }

}
