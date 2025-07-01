/**
 * This package contains Spring MVC controllers responsible for handling web requests
 * related to the Feedback Management System (FMS).
 */
package com.yash.FMS.controller;

import com.yash.FMS.command.CategoryCommand;
import com.yash.FMS.dto.CategoryDTO;
import com.yash.FMS.dto.UserDTO;
import com.yash.FMS.domain.Category; // Assuming Category domain object exists
import com.yash.FMS.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Controller handling web requests related to feedback categories within the FMS.
 * This includes creating new categories and listing existing ones.
 * All operations under the "/category" path require a valid user session.
 */
@Controller
@RequestMapping("/category") // Base path for all category-related actions
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Constructs the CategoryController with the necessary CategoryService dependency.
     *
     * @param categoryService Service for category-related operations.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Displays the form for creating a new feedback category.
     * Handles GET requests to /category/create.
     * Requires an active user session.
     *
     * @param model   Spring UI Model to add attributes for the view (e.g., the command object).
     * @param session HttpSession to check for an active user session.
     * @return The view name "category/createCategory" if logged in, otherwise redirects to "/login".
     */
    @GetMapping("/create")
    public String createCategoryForm(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }
        // Add an empty command object to bind form data
        model.addAttribute("categoryCommand", new CategoryCommand());
        return "category/createCategory"; // Return the view for creating a category
    }

    /**
     * Processes the submission of the new category form.
     * Handles POST requests to /category/create.
     * Requires an active user session. Creates and saves the new category.
     *
     * @param categoryCommand Command object containing the category name from the form.
     * @param session         HttpSession to check for an active user session.
     * @return Redirects to "/category/list" after successful creation, otherwise redirects to "/login".
     */
    @PostMapping("/create")
    public String createCategorySubmit(@ModelAttribute("categoryCommand") CategoryCommand categoryCommand, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Create a domain object from the command object data
        Category category = new Category();
        category.setCategoryName(categoryCommand.getCategoryName());
        // Call the service to persist the new category
        categoryService.createCategory(category);
        // Redirect to the list view after successful creation
        return "redirect:/category/list";
    }

    /**
     * Displays a list of all existing feedback categories.
     * Handles GET requests to /category/list.
     * Requires an active user session.
     *
     * @param model   Spring UI Model to add the list of categories for the view.
     * @param session HttpSession to check for an active user session.
     * @return The view name "category/listCategories" if logged in, otherwise redirects to "/login".
     */
    @GetMapping("/list")
    public String listCategories(Model model, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }

        // Fetch all categories using the service
        List<CategoryDTO> categories = categoryService.getAllCategories();
        // Add the list of categories to the model for rendering in the view
        model.addAttribute("categories", categories);
        return "category/listCategories"; // Return the view displaying the list
    }
}