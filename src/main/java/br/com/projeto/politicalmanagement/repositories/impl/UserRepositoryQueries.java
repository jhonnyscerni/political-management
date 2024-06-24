package br.com.projeto.politicalmanagement.repositories.impl;

import br.com.projeto.politicalmanagement.filter.UserPersonLegalFilter;
import br.com.projeto.politicalmanagement.filter.UserPersonPhysicalFilter;
import br.com.projeto.politicalmanagement.models.User;
import java.util.List;
import java.util.UUID;

public interface UserRepositoryQueries {

    List<User> findAllUserPersonPhysical(UserPersonPhysicalFilter filter, UUID user);

    List<User> findAllUserPersonLegal(UserPersonLegalFilter filter, UUID user);
}
