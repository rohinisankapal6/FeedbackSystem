package com.yash.FMS.rm;

import com.yash.FMS.domain.Feedback;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FeedbackRM implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(rs.getInt("feedbackId"));
        feedback.setUserId(rs.getInt("userId"));
        feedback.setCategoryId(rs.getInt("categoryId"));
        feedback.setSubmissionDate(rs.getTimestamp("submissionDate"));
        feedback.setQuestion1Answer(rs.getInt("question1Answer"));
        feedback.setQuestion2Answer(rs.getInt("question2Answer"));
        feedback.setQuestion3Answer(rs.getInt("question3Answer"));
        feedback.setQuestion4Answer(rs.getInt("question4Answer"));
        feedback.setQuestion5Answer(rs.getInt("question5Answer"));
        feedback.setQuestion6Answer(rs.getInt("question6Answer"));
        feedback.setQuestion7Answer(rs.getInt("question7Answer"));

        return feedback;
    }
}