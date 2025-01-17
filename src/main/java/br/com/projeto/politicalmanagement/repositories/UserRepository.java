package br.com.projeto.politicalmanagement.repositories;

import br.com.projeto.politicalmanagement.models.User;
import br.com.projeto.politicalmanagement.repositories.impl.UserRepositoryQueries;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>, UserRepositoryQueries {

    @EntityGraph(attributePaths = {"person", "roles", "roles.permissions"}, type = EntityGraphType.FETCH)
    Optional<User> findById(UUID userId);

    @Query("select u from User u join fetch u.person p join fetch u.roles r join fetch r.permissions per where u.username =:username")
    Optional<User> findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);

    Optional<User> findByPersonEmail(String email);

    @Query("select u from User u join fetch u.person p join PersonPhysical pf on pf.id = p.id join fetch u.roles r join fetch r.permissions per where u.id =:userId")
    Optional<User> findByIdPersonPhysical(@Param("userId") UUID userId);

    @Query("select u from User u join fetch u.person p join PersonLegal pf on pf.id = p.id join fetch u.roles r join fetch r.permissions per where u.id =:userId")
    Optional<User> findByIdPersonLegal(@Param("userId") UUID userId);

    @Query("select u from User u join fetch u.person p where p.id =:personId")
    Optional<User> findByPersonIdUserDto(@Param("personId") UUID personId);
}
