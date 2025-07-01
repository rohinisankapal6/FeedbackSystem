package com.yash.FMS.dao;

import com.yash.FMS.domain.Admin;
import com.yash.FMS.rm.AdminRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link AdminDAO} using Spring's {@link JdbcTemplate}.
 * Handles database operations for {@link Admin} entities.
 */
@Repository
public class AdminDAOImpl implements AdminDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the DAO with the required {@link JdbcTemplate}.
     *
     * @param jdbcTemplate The {@link JdbcTemplate} for database access.
     */
    @Autowired
    public AdminDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves an {@link Admin} by their username.
     *
     * @param username The username of the admin to find.
     * @return The found {@link Admin} object, or {@code null} if not found.
     */
    @Override
    public Admin getAdminByUsername(String username) {
        String sql = "SELECT adminId, username, password FROM admins WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, new AdminRM());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}