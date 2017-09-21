package com.demand.site.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.entity.User;
import com.demand.site.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {

		return "main/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "main/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model, HttpSession session) throws Exception {
		String email = user.getEmail();
		String password = user.getPassword();
		String page = "main/login";

		user = userService.getUserByEmailAndPassword(email, password);

		if (user == null) {
			model.addAttribute("message", "아이디와 비밀번호를 확인하세요");
		} else {
			session.setAttribute("user", user);
			page = "redirect:/";
		}
		return page;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@EmployeeRequired
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String report(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "reports/list";
	}
}
