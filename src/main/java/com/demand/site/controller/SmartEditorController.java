package com.demand.site.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demand.site.service.SmartEditorService;

@Controller
@RequestMapping("/smarteditor")
public class SmartEditorController {

	@Autowired
	private SmartEditorService smartEditorService;

	@RequestMapping(value = "/uploader", method = RequestMethod.POST)
	@ResponseBody
	public String fileUploader(@RequestParam("file") MultipartFile file, @RequestParam("fileFlag") int fileFlag) throws Exception {
		String fileName = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();

		return smartEditorService.getFileUrlByUploadingImage(fileFlag, inputStream, fileName);
	}

}
