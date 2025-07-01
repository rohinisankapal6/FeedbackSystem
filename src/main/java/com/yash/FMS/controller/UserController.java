/**
 * This package contains Spring MVC controllers responsible for handling web requests
 * related to the Feedback Management System (FMS).
 */
package com.yash.FMS.controller;

import com.yash.FMS.command.UserCommand;
import com.yash.FMS.dto.FeedbackDTO;
import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.domain.User; // Assuming User domain object exists
import com.yash.FMS.service.FeedbackService;
import com.yash.FMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller handling web requests related to user authentication, registration,
 * profile management, dashboard display, and general site navigation (home, about, contact) within the FMS.
 */
@Controller
public class UserController {

    private final UserService userService;
    private final FeedbackService feedbackService;

    /**
     * Constructs the UserController with necessary service dependencies.
     *
     * @param userService     Service for user-related operations (authentication, registration, profile).
     * @param feedbackService Service for retrieving feedback submitted by the user.
     */
    @Autowired
    public UserController(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    /**
     * Displays the user login form.
     * Handles GET requests to /login.
     *
     * @param model Spring UI Model to potentially add attributes for the view.
     * @return The view name "user/login".
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // model.addAttribute("loginClicked", true); // This attribute seems optional
        return "user/login";
    }

    /**
     * Processes the user login form submission.
     * Handles POST requests to /login.
     * Authenticates the user and stores user details in the session on success.
     *
     */
    @PostMapping("/login")
    public String loginFormSubmit(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) {
        UserDTO user = userService.authenticateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user); // Store user DTO in session
            return "redirect:/userDashboard"; // Redirect to dashboard on success
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "user/login"; // Return to login page on failure
        }
    }

    /**
     * Displays the user registration form.
     * Handles GET requests to /register.
     *
     * @param model Spring UI Model to add an empty command object for form binding.
     * @return The view name "user/register".
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userCommand", new UserCommand()); // Command object for form data
        return "user/register";
    }

    /**
     * Processes the user registration form submission.
     * Handles POST requests to /register.
     * Creates a new user account based on the submitted form data.
     *
     * @param userCommand Command object containing user details from the form.
     * @param model       Spring UI Model to add error attributes if registration fails.
     * @return Redirects to "/login" on successful registration, otherwise returns "user/register" view with an error.
     */
    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("userCommand") UserCommand userCommand, Model model) {
        // Map command object data to domain object
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setPassword(userCommand.getPassword()); // Note: Password should be hashed by the service layer
        user.setEmail(userCommand.getEmail());
        user.setFullName(userCommand.getFullName());

        boolean created = userService.createUser(user);
        if (created) {
            return "redirect:/login"; // Redirect to login page after successful registration
        } else {
            // Add error message if creation failed (e.g., username exists)
            model.addAttribute("registerError", "Registration failed. Username or email might already exist.");
            return "user/register"; // Return to registration page
        }
    }

    /**
     * Displays the application's home page.
     * Handles GET requests to /home.
     *
     * @return The view name "home".
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * Displays the application's about page.
     * Handles GET requests to /about.
     *
     * @return The view name "about".
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    /**
     * Displays the application's contact page.
     * Handles GET requests to /contact.
     *
     * @return The view name "contact".
     */
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }

    /**
     * Displays the user dashboard after successful login.
     * Handles GET requests to /userDashboard.
     * Requires an active user session. Fetches and displays the user's submitted feedback.
     *
     * @param session HttpSession to check for an active user session and retrieve user details.
     * @param model   Spring UI Model to add user details and feedback list for the view.
     * @return The view name "user/userDashboard" if logged in, otherwise redirects to "/login".
     */
    @GetMapping("/userDashboard")
    public String showUserDashboard(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        // System.out.println("user from dash "+user); // Debugging line, can be removed
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }
        model.addAttribute("user", user);
        // Fetch feedback submitted by this specific user
        List<FeedbackDTO> feedbacks = feedbackService.getAllFeedbackByUserId(user.getUserId());
        model.addAttribute("feedbackList", feedbacks);
        // System.out.println("feedback of " +user.getUserId()+feedbacks); // Debugging line, can be removed
        return "user/userDashboard";
    }

    /**
     * Displays the user's profile page.
     * Handles GET requests to /userProfile.
     * Requires an active user session. Fetches complete user details.
     *
     * @param session HttpSession to check for an active user session and retrieve user ID.
     * @param model   Spring UI Model to add full user details for the view.
     * @return The view name "user/userProfile" if logged in, otherwise redirects to "/login".
     */
    @GetMapping("/userProfile")
    public String userProfile(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Fetch potentially more detailed user info using the ID from the session DTO
        UserDTO fullUser = userService.getUserById(user.getUserId());
        model.addAttribute("user", fullUser); // Add the full user details to the model
        return "user/userProfile";
    }

    /**
     * Logs the current user out by removing their details from the session.
     * Handles GET requests to /logout.
     *
     * @param session HttpSession from which to remove the user attribute.
     * @return Redirects to the "home" page after logout.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user"); // Remove user object from session
        // session.invalidate(); // Optionally invalidate the entire session
        return "redirect:/home"; // Redirect to home page after logout (changed from returning "home" view directly)
    }
}