package com.demand.site.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;
import com.demand.site.service.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@EmployeeRequired
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String report(Model model) throws Exception {

		return "reports/new";
	}

	@EmployeeRequired
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public void report(@RequestParam("isNotification") boolean isNotification, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(name = "files", required = false) MultipartFile[] files, HttpSession httpSession)
			throws Exception {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			reportService.saveReport(user, isNotification, title, content, files);
		}
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String reports(@PathVariable long id, Model model) throws Exception {
		Map<String, Report> reportMap = reportService.getPrevPresentNextReportMapsById(id);

		model.addAttribute("report", reportMap.get("report"));
		model.addAttribute("prevReport", reportMap.get("prevReport"));
		model.addAttribute("nextReport", reportMap.get("nextReport"));

		return "reports/detail";
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable long id, Model model, HttpSession httpSession) throws Exception {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			reportService.deleteReport(user, id);
		}
		return "redirect:/reports";
	}
}
