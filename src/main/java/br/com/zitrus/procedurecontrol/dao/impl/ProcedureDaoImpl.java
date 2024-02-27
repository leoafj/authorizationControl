package br.com.zitrus.procedurecontrol.dao.impl;

import br.com.zitrus.procedurecontrol.dao.ProcedureDao;
import br.com.zitrus.procedurecontrol.model.Proceduresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureDaoImpl implements ProcedureDao {

    private final Connection connection;

    public ProcedureDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Proceduresql> getAllProcedure() {
        ArrayList<Proceduresql> proceduresqls = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM proceduresql");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Proceduresql proceduresql = new Proceduresql(resultSet.getLong("id"), resultSet.getString("name"));
                proceduresqls.add(proceduresql);
            }
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException("Procedures not found", e);
        }
        return proceduresqls;
    }

    public Proceduresql findProcedureById(Long id) {
        Proceduresql proceduresql = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name FROM proceduresql WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    proceduresql = new Proceduresql(resultSet.getLong("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o procedimento por ID", e);
        }

        return proceduresql;
    }

}
