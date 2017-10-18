package com.demand.site.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.dto.ErrorMessage;
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
	public String index(Locale locale, Model model) throws Exception {

		return "main/index";
	}
	
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String company(Model model) throws Exception {
			
		return "main/company";
	}
	
	@RequestMapping(value = "/serviceDesign", method = RequestMethod.GET)
	public String serviceDesign(Model model) throws Exception {
		return "main/serviceDesign";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(required = false) boolean isJoined) throws Exception {
		User user = new User();
		model.addAttribute("user", user);

		if (isJoined) {
			model.addAttribute("message", "회원가입이 완료되었습니다.");
			model.addAttribute("isJoined", isJoined);
		}

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
			int checked = user.getChecked();
			if(checked == 2 ){
				session.setAttribute("user", user);
				page = "redirect:/";
			}else{
				model.addAttribute("message", "관리자의 승인이 필요합니다");
			}
		}
		return page;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model, String message) throws Exception {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("message", message);
		return "main/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public Object join(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) throws Exception {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();

		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				ErrorMessage errorMessage = new ErrorMessage();
				errorMessage.setMessage(fieldError.getDefaultMessage());
				errorMessage.setField(fieldError.getField());

				errorMessages.add(errorMessage);
			}
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("errorMessages", errorMessages);

			return result;
		} else {
			userService.saveUserWithCheck(user, 1); // 1:신청, 2:승인

			return true;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	public boolean emailCheck(@RequestParam("email") String email) throws Exception {
		return userService.isEmailExist(email);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
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
		model.addAttribute("search", search);
		return "reports/list";
	}
}
