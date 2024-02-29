package br.com.zitrus.procedurecontrol.dao.impl;

import br.com.zitrus.procedurecontrol.dao.AuthorizationDao;
import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import br.com.zitrus.procedurecontrol.model.Authorization;
import br.com.zitrus.procedurecontrol.model.Proceduresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationDaoImpl implements AuthorizationDao {

    private final Connection connection;

    public AuthorizationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void createAuthorization(Authorization authorization, boolean isAllowed) {
        try {
            String sql = createAuthorizationSql();

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, authorization.getProceduresql().getId());
                preparedStatement.setString(2, authorization.getName());
                preparedStatement.setInt(3, authorization.getAge());
                preparedStatement.setString(4, authorization.getGender().getValue());
                preparedStatement.setString(5, isAllowed ? AuthorizationEnum.ALLOWED.getValue() : AuthorizationEnum.NOT_ALLOWED.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Invalid Authorization", e);
        }
    }

    private String createAuthorizationSql() {
        return "INSERT INTO authorization " +
                "(procedureid, name, age, gender, allowed) " +
                "VALUES " +
                "(?, ?, ?, ?, ?)";
    }

    public List<Authorization> findAll() {
        ArrayList<Authorization> authorizations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, procedureid, name, age, gender, allowed FROM authorization");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Authorization authorization = createAuthorizationFromResultSet(resultSet);
                authorizations.add(authorization);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorizations;
    }

    public Authorization findAuthorizationById(Long id) {
        Authorization authorization = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, procedureid, name, age, gender, allowed FROM authorization WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                authorization = createAuthorizationFromResultSet(resultSet);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authorization;
    }

    public void updateAuthorization(Authorization authorization, boolean isAllowed) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateAuthorizationSql());
            preparedStatement.setLong(1, authorization.getId());
            preparedStatement.setLong(2, authorization.getProceduresql().getId());
            preparedStatement.setString(3, authorization.getName());
            preparedStatement.setInt(4, authorization.getAge());
            preparedStatement.setString(5, authorization.getGender().getValue());
            preparedStatement.setString(6, isAllowed ? AuthorizationEnum.ALLOWED.getValue() : AuthorizationEnum.NOT_ALLOWED.getValue());
            preparedStatement.setLong(7, authorization.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String updateAuthorizationSql() {
        return "UPDATE authorization " +
                "SET id = ?, " +
                "procedureid = ?, " +
                "name = ?, " +
                "age = ?, " +
                "gender = ?, " +
                "allowed = ? " +
                "WHERE id = ? ";
    }

    private Authorization createAuthorizationFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long procedureId = resultSet.getLong("procedureid");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        GenderEnum gender = GenderEnum.fromValue(resultSet.getString("gender"));
        AuthorizationEnum authorizationEnum = AuthorizationEnum.fromValue(resultSet.getString("allowed"));

        ProcedureDaoImpl procedureDAO = new ProcedureDaoImpl(connection);
        Proceduresql proceduresql = procedureDAO.findProcedureById(procedureId);

        return new Authorization(id, name, proceduresql, authorizationEnum, age, gender);
    }

    public void deleteAuthorization(Long authorizationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM authorization WHERE id = ?");
            preparedStatement.setLong(1, authorizationId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelAuthorization(Long authorizationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE authorization SET allowed = ? WHERE id = ?");
            preparedStatement.setString(1, AuthorizationEnum.CANCELLED.getValue());
            preparedStatement.setLong(2, authorizationId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
