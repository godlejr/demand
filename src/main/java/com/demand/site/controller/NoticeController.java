package com.demand.site.controller;

import java.util.List;

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
import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.NoticeCategory;
import com.demand.site.common.entity.User;
import com.demand.site.service.NoticeCategoryService;
import com.demand.site.service.NoticeService;

@Controller
@RequestMapping("/notices")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private NoticeCategoryService noticeCategoryService;

	@EmployeeRequired
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String notice(Model model) throws Exception {
		List<NoticeCategory> noticeCategories = noticeCategoryService.getNoticeCategories();

		model.addAttribute("noticeCategories", noticeCategories);

		return "notice/new";
	}

	@EmployeeRequired
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public void notice(@RequestParam("isNotification") boolean isNotification,
			@RequestParam("noticeCategoryId") long noticeCategoryId, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(name = "files", required = false) MultipartFile[] files, HttpSession httpSession)
			throws Exception {

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			noticeService.saveNotice(user, isNotification, noticeCategoryId, title, content, files);
		}
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) throws Exception {
		Notice notice = noticeService.getNoticeById(id);
		List<NoticeCategory> noticeCategories = noticeCategoryService.getNoticeCategories();

		
		model.addAttribute("notice", notice);
		model.addAttribute("noticeCategories", noticeCategories);


		return "notice/edit";
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public void edit(@PathVariable long id, @RequestParam("isNotification") boolean isNotification,
			@RequestParam("noticeCategoryId") long noticeCategoryId, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(name = "deletedFileStorageNames", required = false) String[] deletedFileStorageNames,
			@RequestParam(name = "files", required = false) MultipartFile[] files, HttpSession httpSession)
			throws Exception {

		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			noticeService.editNotice(user, id, isNotification, noticeCategoryId, title, content,
					deletedFileStorageNames, files);
		}
	}

}
