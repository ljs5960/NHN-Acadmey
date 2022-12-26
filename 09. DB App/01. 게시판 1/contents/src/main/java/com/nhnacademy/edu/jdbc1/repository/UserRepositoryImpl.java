package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.config.DatabaseConfig;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    DatabaseConfig databaseConfig = new DatabaseConfig();
    DataSource dataSource = databaseConfig.dataSource();

    @Override
    public String findById(String id) {
        String password = "";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT password FROM JdbcUsers WHERE username=?")) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                password = rs.getString("password");
            }

            return password;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
