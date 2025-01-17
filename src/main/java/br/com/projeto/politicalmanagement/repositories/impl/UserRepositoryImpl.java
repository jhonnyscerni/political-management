package br.com.projeto.politicalmanagement.repositories.impl;

import br.com.projeto.politicalmanagement.filter.UserPersonLegalFilter;
import br.com.projeto.politicalmanagement.filter.UserPersonPhysicalFilter;
import br.com.projeto.politicalmanagement.models.User;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepositoryQueries {

    private final EntityManager em;

    public List<User> findAllUserPersonPhysical(UserPersonPhysicalFilter filter, UUID id) {

        StringBuilder query = new StringBuilder();

        query.append("Select u from User u join fetch u.person p join PersonPhysical pf on pf.id=p.id WHERE 1=1");
        return getUsers(query, filter.getUsername(), filter.getEmail(), id);

    }

    @Override
    public List<User> findAllUserPersonLegal(UserPersonLegalFilter filter, UUID id) {
        StringBuilder query = new StringBuilder();

        query.append("Select u from User u join fetch u.person p join PersonLegal pf on pf.id=p.id WHERE 1=1");
        return getUsers(query, filter.getUsername(), filter.getEmail(), id);
    }

    private List<User> getUsers(StringBuilder query, String username, String email, UUID id) {
        if (username != null) {
            query.append(" and u.username like '%").append(username).append("%'");
        }

        if (id != null) {
            query.append(" and pf.userId = '").append(id).append("'");
        }

        if (email != null) {
            query.append(" and p.email = :email");
        }

        TypedQuery<User> q = em.createQuery(query.toString(), User.class);

        if (email != null) {
            q.setParameter("email", email);
        }

        return q.getResultList();
    }
}
