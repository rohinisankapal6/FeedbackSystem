/**
 * This package contains Spring MVC controllers responsible for handling web requests
 * related to the Feedback Management System (FMS).
 */
package com.yash.FMS.controller;

import com.yash.FMS.command.FeedbackCommand;
import com.yash.FMS.domain.Feedback; // Assuming Feedback domain object exists
import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.dto.FeedbackDTO;
import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.service.CategoryService;
import com.yash.FMS.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Controller handling web requests related to user feedback within the FMS.
 * This includes submitting, viewing, and editing feedback entries.
 * All operations under the "/feedback" path require a valid user session.
 */
@Controller
@RequestMapping("/feedback") // Base path for all feedback-related actions
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final CategoryService categoryService;

    /**
     * Constructs the FeedbackController with necessary service dependencies.
     *
     * @param feedbackService Service for feedback-related operations.
     * @param categoryService Service for category-related operations (needed for category selection).
     */
    @Autowired
    public FeedbackController(FeedbackService feedbackService, CategoryService categoryService) {
        this.feedbackService = feedbackService;
        this.categoryService = categoryService;
    }

    /**
     * Displays the form for submitting new feedback.
     * Handles GET requests to /feedback/submit.
     * Requires an active user session. Populates the form with available categories.
     *
     * @param model   Spring UI Model to add attributes (categories, command object) for the view.
     * @param session HttpSession to check for an active user session and retrieve user info.
     * @return The view name "feedback/submitFeedback" if logged in, otherwise redirects to "/login".
     */
    @GetMapping("/submit")
    public String GetFeedbackForm(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        // System.out.println("get"); // Debugging line, can be removed
        if (user == null) {
            // System.out.println("if user is null"); // Debugging line, can be removed
            return "redirect:/login"; // Redirect if not logged in
        }
        // System.out.println(user); // Debugging line, can be removed

        // Fetch categories to populate dropdown/selection in the form
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        // Add an empty command object for form binding
        model.addAttribute("feedbackCommand", new FeedbackCommand());
        // Re-setting the user attribute might be redundant if already set during login
        // session.setAttribute("user", user);
        return "feedback/submitFeedback"; // Return the view for submitting feedback
    }

    /**
     * Processes the submission of the feedback form.
     * Handles POST requests to /feedback/submit.
     * Requires an active user session. Creates and saves the new feedback entry.
     *
     * @param feedbackCommand Command object containing feedback data from the form.
     * @param session         HttpSession to check for an active user session and retrieve the user ID.
     * @return Redirects to "/userDashboard" after successful submission, otherwise redirects to "/login".
     */
    @PostMapping("/submit")
    public String SubmitFeedbackForm(@ModelAttribute("feedbackCommand") FeedbackCommand feedbackCommand, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        // System.out.println("user"+user); // Debugging line, can be removed
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Map data from command object to domain object
        Feedback feedback = new Feedback();
        // System.out.println("user id "+user.getUserId()); // Debugging line, can be removed
        feedback.setUserId(user.getUserId()); // Associate feedback with the logged-in user
        feedback.setCategoryId(feedbackCommand.getCategoryId());
        feedback.setQuestion1Answer(feedbackCommand.getQuestion1Answer());
        feedback.setQuestion2Answer(feedbackCommand.getQuestion2Answer());
        feedback.setQuestion3Answer(feedbackCommand.getQuestion3Answer());
        feedback.setQuestion4Answer(feedbackCommand.getQuestion4Answer());
        feedback.setQuestion5Answer(feedbackCommand.getQuestion5Answer());
        feedback.setQuestion6Answer(feedbackCommand.getQuestion6Answer());
        feedback.setQuestion7Answer(feedbackCommand.getQuestion7Answer());

        feedback.setSubmissionDate(new Date()); // Set the current date as submission date

        // Call service to persist the feedback
        feedbackService.createFeedback(feedback);
        // Redirect to user dashboard upon successful submission
        return "redirect:/userDashboard";
    }

    /**
     * Displays a specific feedback entry submitted by the logged-in user.
     * Handles GET requests to /feedback/view/{feedbackId}.
     * Requires an active user session and verifies that the user owns the feedback.
     *
     * @param feedbackId The ID of the feedback to view (from path variable).
     * @param model      Spring UI Model to add the feedback details for the view.
     * @param session    HttpSession to check for an active user session and verify ownership.
     * @return The view name "feedback/viewFeedback" if found and owned, "accessDenied" if not owned,
     *         or redirects to "/login" if not logged in.
     */
    @GetMapping("/view/{feedbackId}")
    public String viewFeedback(@PathVariable("feedbackId") int feedbackId, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Fetch feedback DTO using the service
        FeedbackDTO feedback = feedbackService.getFeedbackById(feedbackId);

        // Check if feedback exists and belongs to the current user
        if (feedback == null || feedback.getUserId() != user.getUserId()) {
            // Return an access denied view or redirect (depending on application flow)
            return "accessDenied";
        }
        // Add feedback details to the model
        model.addAttribute("feedback", feedback);
        return "feedback/viewFeedback"; // Return the view displaying the feedback
    }

    /**
     * Displays the form for editing an existing feedback entry.
     * Handles GET requests to /feedback/edit/{feedbackId}.
     * Requires an active user session and verifies that the user owns the feedback.
     * Populates the form with existing feedback data and available categories.
     *
     * @param feedbackId The ID of the feedback to edit (from path variable).
     * @param model      Spring UI Model to add attributes (categories, command object) for the view.
     * @param session    HttpSession to check for an active user session and verify ownership.
     * @return The view name "feedback/editFeedback" if found and owned, "accessDenied" if not owned,
     *         or redirects to "/login" if not logged in.
     */
    @GetMapping("/edit/{feedbackId}")
    public String editFeedbackForm(@PathVariable("feedbackId") int feedbackId, Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Fetch existing feedback DTO
        FeedbackDTO feedback = feedbackService.getFeedbackById(feedbackId);
        // Check ownership
        if (feedback == null || feedback.getUserId() != user.getUserId()) {
            return "accessDenied"; // Return access denied view
        }

        // Fetch categories for dropdown
        List<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        // Create and populate a command object with existing data for form binding
        FeedbackCommand feedbackCommand = new FeedbackCommand();
        feedbackCommand.setFeedbackId(feedback.getFeedbackId());
        feedbackCommand.setCategoryId(feedback.getCategoryId());
        feedbackCommand.setQuestion1Answer(feedback.getQuestion1Answer());
        feedbackCommand.setQuestion2Answer(feedback.getQuestion2Answer());
        feedbackCommand.setQuestion3Answer(feedback.getQuestion3Answer());
        feedbackCommand.setQuestion4Answer(feedback.getQuestion4Answer());
        feedbackCommand.setQuestion5Answer(feedback.getQuestion5Answer());
        feedbackCommand.setQuestion6Answer(feedback.getQuestion6Answer());
        feedbackCommand.setQuestion7Answer(feedback.getQuestion7Answer());

        model.addAttribute("feedbackCommand", feedbackCommand);
        return "feedback/editFeedback"; // Return the view for editing feedback
    }

    /**
     * Processes the submission of the edited feedback form.
     * Handles POST requests to /feedback/edit.
     * Requires an active user session. Updates the existing feedback entry.
     * Note: Ownership check should ideally happen again here or be implicitly handled by the service layer.
     *
     * @param feedbackCommand Command object containing updated feedback data from the form.
     * @param session         HttpSession to check for an active user session and retrieve user ID.
     * @return Redirects to the view page ("/feedback/view/{feedbackId}") after successful update,
     *         or redirects to "/login" if not logged in.
     */
    @PostMapping("/edit")
    public String updateFeedback(@ModelAttribute("feedbackCommand") FeedbackCommand feedbackCommand, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Map data from command object to domain object for update
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackCommand.getFeedbackId()); // Important: Set the ID for update
        feedback.setUserId(user.getUserId()); // Ensure the user ID remains associated
        feedback.setCategoryId(feedbackCommand.getCategoryId());
        feedback.setQuestion1Answer(feedbackCommand.getQuestion1Answer());
        feedback.setQuestion2Answer(feedbackCommand.getQuestion2Answer());
        feedback.setQuestion3Answer(feedbackCommand.getQuestion3Answer());
        feedback.setQuestion4Answer(feedbackCommand.getQuestion4Answer());
        feedback.setQuestion5Answer(feedbackCommand.getQuestion5Answer());
        feedback.setQuestion6Answer(feedbackCommand.getQuestion6Answer());
        feedback.setQuestion7Answer(feedbackCommand.getQuestion7Answer());

        // Optionally update submission date, or keep the original one (depends on requirements)
        // feedback.setSubmissionDate(new Date()); // Setting current date might imply last updated date

        // Call service to update the feedback
        // The service layer should ideally handle ownership checks before updating.
        feedbackService.updateFeedback(feedback);

        // Redirect back to the view page for the updated feedback
        return "redirect:/feedback/view/" + feedbackCommand.getFeedbackId();
    }
}