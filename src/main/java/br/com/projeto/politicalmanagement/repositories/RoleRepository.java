package br.com.projeto.politicalmanagement.repositories;

import br.com.projeto.politicalmanagement.models.Role;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(String name);

    //@Query("select r from Role r left join fetch r.permissions p")
    //List<Role> findAll();
}
