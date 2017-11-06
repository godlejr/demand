package com.demand.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demand.site.common.entity.MobileApp;
import com.demand.site.repository.mobileapp.MobileAppRepository;
import com.demand.site.service.MobileAppService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MobileAppServiceImpl implements MobileAppService {

	@Autowired
	private MobileAppRepository mobileAppRepository;

	@Override
	public MobileApp getMobileAppById(long id) throws Exception {
		return mobileAppRepository.findOne(id);
	}

	@Override
	public List<MobileApp> getMobileApps() throws Exception {
		return mobileAppRepository.findAll();
	}

}
