package com.yash.FMS.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Configures the root Spring application context.
 * Sets up non-web related beans like DataSource, JdbcTemplate, Services, and DAOs.
 * Uses {@link Configuration @Configuration} to define beans and {@link ComponentScan @ComponentScan}
 * to discover components in specified packages.
 */
@Configuration
@ComponentScan(basePackages = {"com.yash.FMS.dao", "com.yash.FMS.service", "com.yash.FMS.controller"})
public class SpringRootConfig {

    @Bean
    public BasicDataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC Driver
        ds.setUrl("jdbc:mysql://deployazureapp.mysql.database.azure.com:3306/feedboxmanagement?useSSL=true&requireSSL=true"); // Azure DB URL with SSL
        ds.setUsername("adminsql@deployazureapp"); // Azure DB Username (adjust if necessary)
        ds.setPassword("Greenms@2233"); // Azure DB Password (consider using environment variables)
        ds.setMaxTotal(10); // Max pool size (adjust as needed)
        ds.setInitialSize(2); // Initial pool size (adjust as needed)
        ds.setTestOnBorrow(true); // Validate connection on borrow
        ds.setValidationQuery("SELECT 1"); // Optional: Query to validate connections
        ds.setDefaultAutoCommit(true); // Default auto-commit behavior
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
