package com.yash.FMS.controller;

import com.yash.FMS.command.AdminCommand; // Data binding object for admin login form
import com.yash.FMS.dto.AdminDTO;         // Data Transfer Object for Admin data
import com.yash.FMS.dto.FeedbackDTO;     // Data Transfer Object for Feedback data
import com.yash.FMS.dto.UserDTO;           // Data Transfer Object for User data
import com.yash.FMS.service.AdminService;   // Service layer for Admin operations
import com.yash.FMS.service.FeedbackService;// Service layer for Feedback operations
import com.yash.FMS.service.UserService;     // Service layer for User operations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Marks class as a Spring MVC Controller
import org.springframework.ui.Model;             // Interface for adding attributes to the model
import org.springframework.web.bind.annotation.GetMapping;      // Maps HTTP GET requests
import org.springframework.web.bind.annotation.ModelAttribute; // Binds request parameters or form data to an object
import org.springframework.web.bind.annotation.PathVariable;   // Binds URI template variables
import org.springframework.web.bind.annotation.PostMapping;    // Maps HTTP POST requests

import javax.servlet.http.HttpSession; // Interface for managing user sessions
import java.util.List;                  // Utility class for Lists


/**
 * Handles web requests related to the Admin user interface.
 * Manages admin login, dashboard access, user management (view/delete),
 * and feedback management (view/delete). Uses HttpSession for managing admin sessions.
 */
@Controller // Indicates this class handles web requests
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final FeedbackService feedbackService;

    /**
     * Constructs the AdminController with required service dependencies.
     * @param adminService Service for admin-related operations.
     * @param userService Service for user-related operations.
     * @param feedbackService Service for feedback-related operations.
     */
    @Autowired // Enables automatic dependency injection
    public AdminController(AdminService adminService, UserService userService, FeedbackService feedbackService) {
        this.adminService = adminService;
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    /**
     * Displays the admin login page.
     * @param model Model object to add attributes for the view.
     * @return The logical view name "adminLogin".
     */
    @GetMapping("/adminLogin")
    public String adminLogin(Model model) {
        // Adds an empty AdminCommand object to bind form data
        model.addAttribute("adminCommand", new AdminCommand());
        return "adminLogin"; // Renders adminLogin.jsp (or configured view)
    }

    /**
     * Processes the admin login form submission.
     * Authenticates the admin and manages the session.
     * @param adminCommand Object containing submitted username and password.
     * @param session HttpSession object to store admin details upon successful login.
     * @param model Model object to add error messages if login fails.
     * @return Redirects to admin dashboard on success, or back to login page on failure.
     */
    @PostMapping("/adminLogin")
    public String adminLoginSubmit(@ModelAttribute("adminCommand") AdminCommand adminCommand, HttpSession session, Model model) {
        AdminDTO admin = adminService.authenticateAdmin(adminCommand.getUsername(), adminCommand.getPassword());
        if (admin != null) {
            // Store admin details in session
            session.setAttribute("admin", admin);
            return "redirect:/adminDashboard"; // Redirect to prevent form resubmission
        } else {
            // Add error message and return to login page
            model.addAttribute("loginError", "Invalid username or password.");
            return "adminLogin";
        }
    }

    /**
     * Displays the admin dashboard page. Requires an active admin session.
     * @param session HttpSession to check for logged-in admin.
     * @param model Model object to add admin details for the view.
     * @return The logical view name "adminDashboard" or redirects to login if not authenticated.
     */
    @GetMapping("/adminDashboard")
    public String adminDashboard(HttpSession session, Model model) {
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null) {
            // Redirect to login if no admin session exists
            return "redirect:/adminLogin";
        }
        model.addAttribute("admin", admin); // Add admin info for display
        return "adminDashboard"; // Renders adminDashboard.jsp
    }

    /**
     * Displays the user management page with a list of all users.
     * Requires an active admin session.
     * @param session HttpSession to check for logged-in admin.
     * @param model Model object to add the list of users for the view.
     * @return The logical view name "manageUsers" or redirects to login if not authenticated.
     */
    @GetMapping("/manageUsers")
    public String manageUsers(HttpSession session, Model model) {
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/adminLogin";
        }

        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users); // Add user list for display
        return "manageUsers"; // Renders manageUsers.jsp
    }

    /**
     * Handles the deletion of a specific user by their ID.
     * Requires an active admin session.
     * @param userId The ID of the user to delete, extracted from the URL path.
     * @param session HttpSession to check for logged-in admin.
     * @return Redirects back to the manage users page or to login if not authenticated.
     */
    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") int userId, HttpSession session) {
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/adminLogin";
        }

        userService.deleteUser(userId); // Perform deletion via service layer
        return "redirect:/manageUsers"; // Redirect back to the user list
    }

    /**
     * Displays the feedback management page with a list of all feedback entries.
     * Requires an active admin session.
     * @param session HttpSession to check for logged-in admin.
     * @param model Model object to add the list of feedback for the view.
     * @return The logical view name "manageFeedback" or redirects to login if not authenticated.
     */
    @GetMapping("/manageFeedback")
    public String manageFeedback(HttpSession session, Model model) {
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null) {
            // Note: Potential typo in original code "/admin/login", corrected to "/adminLogin"
            return "redirect:/adminLogin";
        }

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedback();
        model.addAttribute("feedbackList", feedbackList); // Add feedback list for display
        return "manageFeedback"; // Renders manageFeedback.jsp
    }

    /**
     * Handles the deletion of a specific feedback entry by its ID.
     * Requires an active admin session.
     * @param feedbackId The ID of the feedback to delete, extracted from the URL path.
     * @param session HttpSession to check for logged-in admin.
     * @return Redirects back to the manage feedback page or to login if not authenticated.
     */
    @GetMapping("/deleteFeedback/{feedbackId}")
    public String deleteFeedback(@PathVariable("feedbackId") int feedbackId, HttpSession session) {
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/adminLogin";
        }

        feedbackService.deleteFeedback(feedbackId); // Perform deletion via service layer
        return "redirect:/manageFeedback"; // Redirect back to the feedback list
    }

}