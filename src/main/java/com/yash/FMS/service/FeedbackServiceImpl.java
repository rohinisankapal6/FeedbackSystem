/**
 * This package contains the service layer implementations for the Feedback Management System (FMS).
 * Services orchestrate business logic, interact with DAOs (Data Access Objects),
 * and often handle data transformations (e.g., between domain objects and DTOs).
 */
package com.yash.FMS.service;

import com.yash.FMS.dao.CategoryDAO;
import com.yash.FMS.dao.FeedbackDAO;
import com.yash.FMS.domain.Category;
import com.yash.FMS.domain.Feedback;
import com.yash.FMS.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // For more concise list conversion

/**
 * Implementation of the FeedbackService interface.
 * This class provides the business logic for feedback-related operations,
 * interacting with the FeedbackDAO and CategoryDAO to persist, retrieve, and manage feedback data.
 * It handles operations like feedback creation, retrieval (by ID, by user, all), update, and deletion.
 * It also converts Feedback domain objects into FeedbackDTOs, enriching them with category names.
 */
@Service // Marks this class as a Spring service component
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDAO feedbackDAO; // DAO for feedback data operations
    private final CategoryDAO categoryDAO; // DAO for retrieving category names

    /**
     * Constructs the FeedbackServiceImpl with necessary DAO dependencies.
     * Spring's @Autowired annotation handles the dependency injection.
     *
     * @param feedbackDAO The DAO implementation for accessing feedback data.
     * @param categoryDAO The DAO implementation for accessing category data (used for enrichment).
     */
    @Autowired
    public FeedbackServiceImpl(FeedbackDAO feedbackDAO, CategoryDAO categoryDAO) {
        this.feedbackDAO = feedbackDAO;
        this.categoryDAO = categoryDAO;
    }

    /**
     * Creates a new feedback entry in the system.
     * Delegates the creation operation directly to the FeedbackDAO.
     *
     * @param feedback The Feedback domain object containing the details of the feedback to create.
     * @return true if the feedback was created successfully, false otherwise.
     */
    @Override
    public boolean createFeedback(Feedback feedback) {
        // Potentially add validation logic here before calling DAO
        return feedbackDAO.createFeedback(feedback);
    }

    /**
     * Retrieves a specific feedback entry by its unique ID.
     * Fetches the Feedback domain object from the DAO and converts it to a FeedbackDTO.
     *
     * @param feedbackId The ID of the feedback entry to retrieve.
     * @return A FeedbackDTO representing the found feedback (including category name),
     *         or null if no feedback exists with that ID.
     */
    @Override
    public FeedbackDTO getFeedbackById(int feedbackId) {
        Feedback feedback = feedbackDAO.getFeedbackById(feedbackId);
        return feedback == null ? null : convertToDTO(feedback); // Convert if found, else null
    }

    /**
     * Retrieves all feedback entries submitted by a specific user.
     * Fetches a list of Feedback domain objects from the DAO for the given user ID
     * and converts each one to a FeedbackDTO.
     *
     * @param userId The ID of the user whose feedback entries are to be retrieved.
     * @return A List of FeedbackDTO objects representing the user's feedback.
     *         Returns an empty list if the user has not submitted any feedback.
     */
    @Override
    public List<FeedbackDTO> getAllFeedbackByUserId(int userId) {
        List<Feedback> feedbackList = feedbackDAO.getAllFeedbackByUserId(userId);
        // Convert list of Feedback domain objects to list of FeedbackDTOs using streams
        return feedbackList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        /* // Original loop-based conversion:
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDTOs.add(convertToDTO(feedback));
        }
        return feedbackDTOs;
        */
    }

    /**
     * Retrieves all feedback entries submitted in the system.
     * Fetches a list of all Feedback domain objects from the DAO
     * and converts each one to a FeedbackDTO. Primarily used for administrative purposes.
     *
     * @return A List of all FeedbackDTO objects in the system.
     *         Returns an empty list if no feedback has been submitted.
     */
    @Override
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedbackList = feedbackDAO.getAllFeedback();
        // Convert list of Feedback domain objects to list of FeedbackDTOs using streams
        return feedbackList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        /* // Original loop-based conversion:
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDTOs.add(convertToDTO(feedback));
        }
        return feedbackDTOs;
        */
    }

    /**
     * Updates the details of an existing feedback entry.
     * Delegates the update operation directly to the FeedbackDAO.
     *
     * @param feedback The Feedback domain object containing the updated details (must include the feedback ID).
     * @return true if the feedback was updated successfully, false otherwise (e.g., feedback not found).
     */
    @Override
    public boolean updateFeedback(Feedback feedback) {
        // Consider adding validation or ownership check logic here before calling DAO
        return feedbackDAO.updateFeedback(feedback);
    }

    /**
     * Deletes a feedback entry from the system based on its ID.
     * Delegates the deletion operation directly to the FeedbackDAO.
     *
     * @param feedbackId The ID of the feedback entry to delete.
     * @return true if the feedback was deleted successfully, false otherwise (e.g., feedback not found).
     */
    @Override
    public boolean deleteFeedback(int feedbackId) {
        // Consider adding related data cleanup logic if necessary
        return feedbackDAO.deleteFeedback(feedbackId);
    }

    /**
     * Private helper method to convert a Feedback domain object into a FeedbackDTO.
     * This method maps the fields from the domain object to the DTO and additionally
     * fetches the corresponding category name using the CategoryDAO based on the category ID
     * stored in the feedback object.
     *
     * @param feedback The Feedback domain object to convert.
     * @return The corresponding FeedbackDTO object, enriched with the category name.
     */
    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        feedbackDTO.setUserId(feedback.getUserId());
        feedbackDTO.setCategoryId(feedback.getCategoryId());
        feedbackDTO.setSubmissionDate(feedback.getSubmissionDate());
        feedbackDTO.setQuestion1Answer(feedback.getQuestion1Answer());
        feedbackDTO.setQuestion2Answer(feedback.getQuestion2Answer());
        feedbackDTO.setQuestion3Answer(feedback.getQuestion3Answer());
        feedbackDTO.setQuestion4Answer(feedback.getQuestion4Answer());
        feedbackDTO.setQuestion5Answer(feedback.getQuestion5Answer());
        feedbackDTO.setQuestion6Answer(feedback.getQuestion6Answer());
        feedbackDTO.setQuestion7Answer(feedback.getQuestion7Answer());


        // Enrich DTO with category name by fetching it from CategoryDAO
        Category category = categoryDAO.getCategoryById(feedback.getCategoryId());
        if (category != null) {
            feedbackDTO.setCategoryName(category.getCategoryName());
        } else {
            // Handle cases where the category might have been deleted or ID is invalid
            feedbackDTO.setCategoryName("Unknown Category");
        }

        return feedbackDTO;
    }
}