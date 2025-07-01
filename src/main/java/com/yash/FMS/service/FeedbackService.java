package com.yash.FMS.service;

import com.yash.FMS.dto.FeedbackDTO;
import com.yash.FMS.domain.Feedback;

import java.util.List;

/**
 * Service interface for managing Feedback-related operations.
 */
public interface FeedbackService {

    /**
     * Creates a new feedback entry.
     * @param feedback The Feedback object to create.
     * @return True if the feedback was created successfully, false otherwise.
     */
    boolean createFeedback(Feedback feedback);

    /**
     * Retrieves a feedback entry by its ID.
     * @param feedbackId The ID of the feedback to retrieve.
     * @return A FeedbackDTO if found, null otherwise.
     */
    FeedbackDTO getFeedbackById(int feedbackId);


    List<FeedbackDTO> getAllFeedbackByUserId(int userId);


    List<FeedbackDTO> getAllFeedback();


    boolean updateFeedback(Feedback feedback);


    boolean deleteFeedback(int feedbackId);
}
