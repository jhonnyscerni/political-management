package br.com.projeto.politicalmanagement.api.controllers;

import br.com.projeto.politicalmanagement.api.request.UserPersonLegalRequest;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.filter.UserPersonLegalFilter;
import br.com.projeto.politicalmanagement.service.UserPersonLegalService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users/person-legal")
public class UserPersonLegalController {

    private final UserPersonLegalService userPersonLegalService;


    @GetMapping
    public ResponseEntity<Page<UserResponse>> search(UserPersonLegalFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(userPersonLegalService.search(filter, pageable));
    }

    @GetMapping("/my")
    public ResponseEntity<Page<UserResponse>> searchMy(UserPersonLegalFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(userPersonLegalService.searchMy(filter, pageable));
    }


    @GetMapping("/{personLegalId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable UUID personLegalId) {
        return ResponseEntity.ok().body(userPersonLegalService.findByPersonLegalIdUserUserPersonLegalResponse(personLegalId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserPersonLegalRequest userPersonLegalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPersonLegalService.createUserEvent(userPersonLegalRequest));
    }

    @PutMapping("/{userPersonLegalId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userPersonLegalId,
        @RequestBody @Valid UserPersonLegalRequest userPersonLegalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userPersonLegalService.updateUserEvent(userPersonLegalId, userPersonLegalRequest));
    }

}
