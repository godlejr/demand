package com.demand.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;
import com.demand.site.common.flag.PaginationFlag;
import com.demand.site.service.ReportService;
import com.demand.site.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportService reportService;

	@EmployeeRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id,
			@PageableDefault(size = 6, sort = "sort", direction = Direction.DESC) Pageable pageable, Model model)
			throws Exception {

		User user = userService.getUserById(id);
		Page<Report> reportPage = reportService.getReportsByUserIdAndPageable(id, pageable);
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
		model.addAttribute("user", user);

		return "user/detail";
	}
}
