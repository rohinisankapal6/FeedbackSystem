package com.yash.FMS.dao;

import com.yash.FMS.domain.Feedback;

import java.util.List;


public interface FeedbackDAO {



    boolean createFeedback(Feedback feedback);
    Feedback getFeedbackById(int feedbackId);
    List<Feedback> getAllFeedbackByUserId(int userId);
    List<Feedback> getAllFeedback();
    boolean updateFeedback(Feedback feedback);
    boolean deleteFeedback(int feedbackId);
}
