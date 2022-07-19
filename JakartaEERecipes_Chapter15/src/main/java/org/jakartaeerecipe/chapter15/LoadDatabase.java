package org.jakartaeerecipe.chapter15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DataSourceDefinition(
        name = "java:global/JakartaEERecipes/acmedb",
        className = "org.apache.derby.jdbc.ClientDataSource",
        serverName="localhost",
        databaseName="acme",
        user = "acmeuser",
        password = ""
)
@Singleton
@Startup
public class LoadDatabase {

    @Resource(lookup="java:global/JakartaEERecipes/acmedb")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {

        Map<String, String> parameters= new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);


        executeUpdate(dataSource, "CREATE TABLE caller_store(name VARCHAR(64) PRIMARY KEY, password VARCHAR(255))");
        executeUpdate(dataSource, "CREATE TABLE caller_groups(caller_name VARCHAR(64), group_name VARCHAR(64))");

        executeUpdate(dataSource, "INSERT INTO caller_store VALUES('juneau', '" + passwordHash.generate("eerecipes".toCharArray()) + "')");


        executeUpdate(dataSource, "INSERT INTO caller_groups VALUES('juneau', 'group1')");
        executeUpdate(dataSource, "INSERT INTO caller_groups VALUES('juneau', 'group2')");

    }

    @PreDestroy
    public void destroy() {
        try {
            executeUpdate(dataSource, "DROP TABLE IF EXISTS caller_store");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS caller_groups");
        } catch (Exception e) {
            // silently ignore, concerns in-memory database
        }
    }

    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // do nothing
        }
    }

}
