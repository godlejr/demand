package com.demand.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demand.site.common.annotation.EmployeeRequired;
import com.demand.site.service.FileService;

@Controller
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@EmployeeRequired
	@RequestMapping(value = "/{id}/delete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void deleteAvatar(@PathVariable("id") long id) throws Exception {
		fileService.deleteFile(id);
	}

}
