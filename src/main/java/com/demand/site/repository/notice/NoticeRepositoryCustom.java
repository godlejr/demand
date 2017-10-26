package com.demand.site.repository.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demand.site.common.entity.Notice;

public interface NoticeRepositoryCustom {
	Page<Notice> findAllByNoticeCategoryIdAndSearchAndPageable(long noticeCategoryId, String search, Pageable pageable)
			throws Exception;
}
