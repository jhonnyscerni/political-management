package br.com.projeto.politicalmanagement.api.controllers;

import br.com.projeto.politicalmanagement.api.request.LoginRequest;
import br.com.projeto.politicalmanagement.api.request.PersonPhysicalRequest;
import br.com.projeto.politicalmanagement.api.response.JwtResponse;
import br.com.projeto.politicalmanagement.api.response.PersonPhysicalResponse;
import br.com.projeto.politicalmanagement.api.response.UserResponse;
import br.com.projeto.politicalmanagement.security.jwt.JwtProvider;
import br.com.projeto.politicalmanagement.service.PersonPhysicalService;
import br.com.projeto.politicalmanagement.service.UserService;

import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final PersonPhysicalService personPhysicalService;


    @PostMapping(value = "/persongoldfather/{id}")
    public ResponseEntity<PersonPhysicalResponse> registerPersonPhisicalGoldFatherPersonPhysical(@PathVariable UUID id, @RequestBody PersonPhysicalRequest personPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personPhysicalService.create(id, personPhysicalRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<UserResponse> resetPassword(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.resetPassword(email));
    }
}
