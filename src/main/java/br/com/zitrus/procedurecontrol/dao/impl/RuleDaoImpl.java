package br.com.zitrus.procedurecontrol.dao;

import br.com.zitrus.procedurecontrol.model.Authorization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RuleDAO {

    private final Connection connection;

    public RuleDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean validateAuthorization(Authorization authorization) {
        boolean hasRule = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(validateAuthorizationSql());
            preparedStatement.setLong(1, authorization.getProceduresql().getId());
            preparedStatement.setInt(2, authorization.getAge());
            preparedStatement.setString(3, authorization.getGender().getValue());

            ResultSet resultSet = preparedStatement.executeQuery();
            hasRule = resultSet.next();

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new RuntimeException("Invalid authorization: " + e.getMessage());
        }

        return hasRule;
    }

    private String validateAuthorizationSql() {
        return "SELECT 1 FROM rule WHERE procedureid = ? " +
                "AND age = ? " +
                "AND gender = ?";
    }
}
