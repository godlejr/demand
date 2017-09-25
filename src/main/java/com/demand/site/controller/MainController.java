package com.demand.site.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.PaginationFlag;
import com.demand.site.service.ReportService;
import com.demand.site.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReportService reportService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) throws Exception  {

		return "main/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) throws Exception  {
		User user = new User();
		model.addAttribute("user", user);

		return "main/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST )
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
	public String logout(HttpSession session) throws Exception  {
		session.invalidate();
		return "redirect:/";
	}

	@EmployeeRequired
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String report(@RequestParam(name = "search", required = false) String search,
			@PageableDefault(size = 15, sort = "sort", direction = Direction.DESC) Pageable pageable, Model model)
			throws Exception {
		Page<Report> reportPage = reportService.getReportsBySearchAndPageable(search, pageable);
		int currentPageNo = reportPage.getNumber();
		int totalPageNo = reportPage.getTotalPages();
		int startPageNo = ((currentPageNo) / PaginationFlag.PAGE_VIEW_SIZE) * PaginationFlag.PAGE_VIEW_SIZE + 1;

		int endPageNo = startPageNo + PaginationFlag.PAGE_VIEW_SIZE - 1;

		if (endPageNo > totalPageNo) {
			endPageNo = totalPageNo;
		}

		model.addAttribute("reportPage", reportPage);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		return "reports/list";
	}
}
