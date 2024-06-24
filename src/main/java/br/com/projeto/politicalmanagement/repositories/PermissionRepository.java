package br.com.projeto.politicalmanagement.repositories;

import br.com.projeto.politicalmanagement.models.Permission;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionRepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {

}
