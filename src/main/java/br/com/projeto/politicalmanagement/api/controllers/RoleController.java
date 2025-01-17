package br.com.projeto.politicalmanagement.api.controllers;

import br.com.projeto.politicalmanagement.api.request.RoleRequest;
import br.com.projeto.politicalmanagement.api.response.RoleResponse;
import br.com.projeto.politicalmanagement.service.RoleService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> list() {
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> findById(@PathVariable UUID roleId) {
        return ResponseEntity.ok().body(roleService.findByIdRoleResponse(roleId));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@RequestBody @Valid RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleRequest));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponse> update(@PathVariable UUID roleId,
        @RequestBody @Valid RoleRequest roleRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.update(roleId, roleRequest));
    }


    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> delete(@PathVariable UUID roleId) {
        roleService.delete(roleId);
        return ResponseEntity.noContent().build();
    }
}
