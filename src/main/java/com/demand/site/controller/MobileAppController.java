package com.demand.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demand.site.common.entity.MobileApp;
import com.demand.site.service.MobileAppService;

@Controller 
@RequestMapping("/mobileApps")
public class MobileAppController {
	
	@Autowired
	private MobileAppService mobileAppService;
	
	@RequestMapping(value = "/{id}/privacy", method = RequestMethod.GET)
	public String privacy(@PathVariable long id, Model model) throws Exception {
		
		MobileApp mobileApp = mobileAppService.getMobileAppById(id);
		model.addAttribute("mobileApp", mobileApp);
		
		return "mobileApp/privacy";
	}
	
	@RequestMapping(value = "/{id}/agreement", method = RequestMethod.GET)
	public String agreement(@PathVariable long id, Model model) throws Exception {
		
		MobileApp mobileApp = mobileAppService.getMobileAppById(id);
		model.addAttribute("mobileApp", mobileApp);
		
		return "mobileApp/agreement";
	}
}
