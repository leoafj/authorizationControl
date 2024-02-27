package br.com.zitrus.procedurecontrol.dao;

import br.com.zitrus.procedurecontrol.model.Authorization;

import java.util.List;

public interface AuthorizationDao {

    void createAuthorization(Authorization authorization, boolean isAllowed);

    void updateAuthorization(Authorization authorization, boolean isAllowed);

    List<Authorization> findAll();

    Authorization findAuthorizationById(Long id);

    void deleteAuthorization(Long authorizationId);

    void cancelAuthorization(Long authorizationId);
}
