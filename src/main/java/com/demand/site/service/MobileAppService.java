package com.demand.site.service;

import java.util.List;

import com.demand.site.common.entity.MobileApp;

public interface MobileAppService {
	MobileApp getMobileAppById(long id) throws Exception;

	List<MobileApp> getMobileApps() throws Exception;
}
