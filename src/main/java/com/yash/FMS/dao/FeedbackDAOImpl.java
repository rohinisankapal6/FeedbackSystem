package com.yash.FMS.dao;

import com.yash.FMS.domain.Feedback;
import com.yash.FMS.rm.FeedbackRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of {@link FeedbackDAO} using Spring's {@link JdbcTemplate}.
 * Handles database operations for {@link Feedback} entities.
 */
@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the DAO with the required {@link JdbcTemplate}.
     *
     * @param jdbcTemplate The {@link JdbcTemplate} for database access.
     */
    @Autowired
    public FeedbackDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a new feedback record in the database.
     *
     * @param feedback The {@link Feedback} object containing the data for the new feedback.
     * @return {@code true} if the feedback was created successfully, {@code false} otherwise.
     */
    @Override
    public boolean createFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (userId, categoryId, submissionDate, question1Answer, question2Answer, question3Answer, question4Answer, question5Answer, question6Answer, question7Answer) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                feedback.getUserId(),
                feedback.getCategoryId(),
                feedback.getSubmissionDate(),
                feedback.getQuestion1Answer(),
                feedback.getQuestion2Answer(),
                feedback.getQuestion3Answer(),
                feedback.getQuestion4Answer(),
                feedback.getQuestion5Answer(),
                feedback.getQuestion6Answer(),
                feedback.getQuestion7Answer()
        ) > 0;
    }

    /**
     * Retrieves a {@link Feedback} by its unique identifier.
     *
     * @param feedbackId The ID of the feedback to retrieve.
     * @return The found {@link Feedback} object, or {@code null} if not found.
     */
    @Override
    public Feedback getFeedbackById(int feedbackId) {
        String sql = "SELECT feedbackId, userId, categoryId, submissionDate, question1Answer, question2Answer, question3Answer, question4Answer, question5Answer, question6Answer, question7Answer " +
                "FROM Feedback WHERE feedbackId = ?";
        try {
            // Pass arguments correctly for queryForObject
            return jdbcTemplate.queryForObject(sql, new Object[]{feedbackId}, new FeedbackRM());
        } catch (EmptyResultDataAccessException e) {
            // Return null if no feedback is found with the given ID.
            return null;
        }
    }

    /**
     * Retrieves a list of all feedback submitted by a specific user.
     *
     * @param userId The ID of the user whose feedback is to be retrieved.
     * @return A {@link List} of {@link Feedback} objects submitted by the user, or an empty list if none exist.
     */
    @Override
    public List<Feedback> getAllFeedbackByUserId(int userId) {
        String sql = "SELECT feedbackId, userId, categoryId, submissionDate, question1Answer, question2Answer, question3Answer, question4Answer, question5Answer, question6Answer, question7Answer " +
                "FROM Feedback WHERE userId = ?";
        // Pass arguments correctly for query
        return jdbcTemplate.query(sql, new Object[]{userId}, new FeedbackRM());
    }

    /**
     * Retrieves a list of all feedback records from the database.
     *
     * @return A {@link List} of all {@link Feedback} objects, or an empty list if none exist.
     */
    @Override
    public List<Feedback> getAllFeedback() {
        String sql = "SELECT feedbackId, userId, categoryId, submissionDate, question1Answer, question2Answer, question3Answer, question4Answer, question5Answer, question6Answer, question7Answer FROM Feedback";
        return jdbcTemplate.query(sql, new FeedbackRM());
    }

    /**
     * Updates an existing feedback record in the database.
     * Note: Typically, userId should not be updated. This method updates other fields based on feedbackId.
     *
     * @param feedback The {@link Feedback} object containing the updated data (identified by feedbackId).
     * @return {@code true} if the feedback was updated successfully, {@code false} otherwise.
     */
    @Override
    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE Feedback SET categoryId = ?, submissionDate = ?, question1Answer = ?, question2Answer = ?, question3Answer = ?, question4Answer = ?, question5Answer = ?, question6Answer = ?, question7Answer = ? " +
                "WHERE feedbackId = ?";

        return jdbcTemplate.update(sql,
                feedback.getCategoryId(),
                feedback.getSubmissionDate(),
                feedback.getQuestion1Answer(),
                feedback.getQuestion2Answer(),
                feedback.getQuestion3Answer(),
                feedback.getQuestion4Answer(),
                feedback.getQuestion5Answer(),
                feedback.getQuestion6Answer(),
                feedback.getQuestion7Answer(),
                feedback.getFeedbackId()
        ) > 0;
    }

    /**
     * Deletes a feedback record from the database using its ID.
     *
     * @param feedbackId The ID of the feedback to delete.
     * @return {@code true} if the feedback was deleted successfully, {@code false} otherwise.
     */
    @Override
    public boolean deleteFeedback(int feedbackId) {
        String sql = "DELETE FROM Feedback WHERE feedbackId = ?";
        return jdbcTemplate.update(sql, feedbackId) > 0;
    }
}