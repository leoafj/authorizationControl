package br.com.zitrus.procedurecontrol.dao;

import br.com.zitrus.procedurecontrol.model.Proceduresql;

import java.util.List;

public interface ProcedureDao {

    List<Proceduresql> getAllProcedure();

    Proceduresql findProcedureById(Long id);
}
