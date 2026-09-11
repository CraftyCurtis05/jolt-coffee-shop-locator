package com.jolt.dao;

import java.sql.SQLException;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Configuration
public class TestingDatabaseConfig {

    // Use environment variables when provided, otherwise use local test defaults
    private static final String DB_HOST =
            Objects.requireNonNullElse(
                    System.getenv("DB_HOST"),
                    "localhost"
            );

    private static final String DB_PORT =
            Objects.requireNonNullElse(
                    System.getenv("DB_PORT"),
                    "5432"
            );

    private static final String DB_NAME =
            Objects.requireNonNullElse(
                    System.getenv("DB_NAME"),
                    "final_capstone_test"
            );

    private static final String DB_USERNAME =
            Objects.requireNonNullElse(
                    System.getenv("DB_USERNAME"),
                    "postgres"
            );

    private static final String DB_PASSWORD =
            Objects.requireNonNullElse(
                    System.getenv("DB_PASSWORD"),
                    ""
            );

    private SingleConnectionDataSource adminDataSource;
    private JdbcTemplate adminJdbcTemplate;
    private DataSource dataSource;

    @PostConstruct
    public void setup() {
        if (System.getenv("DB_HOST") == null) {
            adminDataSource = new SingleConnectionDataSource();

            adminDataSource.setUrl(
                    "jdbc:postgresql://localhost:5432/postgres"
            );
            adminDataSource.setUsername(DB_USERNAME);
            adminDataSource.setPassword(DB_PASSWORD);

            adminJdbcTemplate = new JdbcTemplate(adminDataSource);

            adminJdbcTemplate.update(
                    "DROP DATABASE IF EXISTS \"" + DB_NAME + "\""
            );

            adminJdbcTemplate.update(
                    "CREATE DATABASE \"" + DB_NAME + "\""
            );
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dataSource != null) {
            return dataSource;
        }

        SingleConnectionDataSource testDataSource =
                new SingleConnectionDataSource();

        testDataSource.setUrl(
                String.format(
                        "jdbc:postgresql://%s:%s/%s",
                        DB_HOST,
                        DB_PORT,
                        DB_NAME
                )
        );

        testDataSource.setUsername(DB_USERNAME);
        testDataSource.setPassword(DB_PASSWORD);

        // Disable auto-commit so database changes can be rolled back after each test
        testDataSource.setAutoCommit(false);

        ScriptUtils.executeSqlScript(
                testDataSource.getConnection(),
                new FileSystemResource("database/schema.sql")
        );

        ScriptUtils.executeSqlScript(
                testDataSource.getConnection(),
                new ClassPathResource("test-data.sql")
        );

        dataSource = testDataSource;

        return dataSource;
    }

    @PreDestroy
    public void cleanup() throws SQLException {
        if (adminDataSource != null) {
            adminJdbcTemplate.update(
                    "DROP DATABASE \"" + DB_NAME + "\""
            );

            adminDataSource.getConnection().close();
            adminDataSource.destroy();
        }
    }

}