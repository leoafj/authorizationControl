package br.com.zitrus.procedurecontrol.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {

    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/zitrus", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to database");
        }
    }
}
