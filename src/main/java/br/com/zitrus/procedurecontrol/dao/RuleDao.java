package br.com.zitrus.procedurecontrol.dao;

import br.com.zitrus.procedurecontrol.model.Authorization;

public interface RuleDao {

    boolean validateAuthorization(Authorization authorization);
}
