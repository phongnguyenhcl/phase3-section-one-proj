package com.simplilearn.phase2endproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.phase2endproject.model.User;
import com.simplilearn.phase2endproject.service.UserService;

/**
 * 
 * @author Phong Van Nguyen
 *
 */
@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * direct to the homepage of the application
	 * 
	 * @return the homepage view
	 */
	@RequestMapping("/")
	public String viewHomePage() {
		return "index";
	}

	/**
	 * retrieve all current users and direct to the list of users page
	 * 
	 * @param model
	 * @return list of users view
	 */
	@RequestMapping("/users")
	public String viewListOfUserHomePage(Model model) {
		List<User> listOfAllUser = userService.listAll();
		model.addAttribute("listOfUsers", listOfAllUser);

		return "users";
	}

	/**
	 * direct to the registration form page
	 * 
	 * @param model
	 * @return registration page view
	 */
	@RequestMapping("/register-user")
	public String registerUser(Model model) {
		User newUser = new User();
		model.addAttribute("newUser", newUser);

		return "register_user";
	}

	/**
	 * save new user to the database and direct to success page
	 * 
	 * @param newUser
	 * @return register_success page view
	 */
	@PostMapping("/save-user")
	public String saveUser(@ModelAttribute("newUser") User newUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		newUser.setPassword(encoder.encode(newUser.getPassword()));
		userService.saveUser(newUser);

		return "register_success";
	}

	/**
	 * retrieve the user to be modified and direct to edit form
	 * 
	 * @param id
	 * @return edit form
	 */
	@RequestMapping("/edit/{id}")
	public ModelAndView editUserForm(@PathVariable(name = "id") int id) {
		ModelAndView mv = new ModelAndView("edit_customer");
		User user = userService.get(id);
		mv.addObject("user", user);

		return mv;
	}

	/**
	 * update user and redirect to homepage
	 * 
	 * @param updatedUser
	 * @return homepagelok
	 */
	@PostMapping("/update-user")
	public String updateUser(@ModelAttribute("user") User updatedUser) {
		userService.updateUserHobby(updatedUser.getId(), updatedUser.getHobby());
		logger.info("UPDATE (hobby) to " + updatedUser.getHobby());
		return "redirect:/";
	}

}
