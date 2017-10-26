package com.demand.site.repository.notice;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.demand.site.common.entity.Notice;

public class NoticeRepositoryImpl  extends QueryDslRepositorySupport implements NoticeRepositoryCustom{

	public NoticeRepositoryImpl() {
		super(Notice.class);
	}
	
	

}
