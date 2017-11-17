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
import com.demand.site.common.entity.File;
import com.demand.site.common.entity.News;
import com.demand.site.common.entity.Report;
import com.demand.site.common.entity.User;
import com.demand.site.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") long id, Model model, HttpSession httpSession) throws Exception {
		Map<String, News> reportMap = newsService.getPrevPresentNextNewsMapsById(id);

		model.addAttribute("news", reportMap.get("news"));
		model.addAttribute("prevNews", reportMap.get("prevNews"));
		model.addAttribute("nextNews", reportMap.get("nextNews"));
		return "news/detail";
	}

	@EmployeeRequired
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String news(Model model, HttpSession httpSession) throws Exception {

		return "news/new";
	}

	@EmployeeRequired
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public void news(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam(name = "files", required = false) MultipartFile[] files,
			@RequestParam("avatarId") long avatarId, HttpSession httpSession) throws Exception {
		User user = (User) httpSession.getAttribute("user");

		if (user != null) {
			newsService.saveNews(user, title, content, files, avatarId);
		}
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model, HttpSession httpSession) throws Exception {
		News news = newsService.getNewsById(id);
		model.addAttribute("news", news);

		return "news/edit";
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public void edit(@PathVariable("id") long id, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(name = "deletedFileStorageNames", required = false) String[] deletedFileStorageNames,
			@RequestParam(name = "files", required = false) MultipartFile[] files,
			@RequestParam("avatarId") long avatarId, HttpSession httpSession) throws Exception {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			newsService.updateNews(id, user, title, content, files, deletedFileStorageNames, avatarId);
		}
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String news(@PathVariable("id") long id, HttpSession httpSession) throws Exception {
		User user = (User) httpSession.getAttribute("user");
		if (user != null) {
			newsService.deleteNews(user, id);
		}

		return "redirect:/news";
	}

	@EmployeeRequired
	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	@ResponseBody
	public File avatar(@RequestParam("file") MultipartFile file, Model model) throws Exception {
		return newsService.saveNewsAvatar(file);
	}

	@EmployeeRequired
	@RequestMapping(value = "/{id}/avatar/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteAvatar(@PathVariable("id") long id, @RequestParam("fileId") long fileId) throws Exception {

		newsService.deleteNewsAvatar(id, fileId);
	}

}
