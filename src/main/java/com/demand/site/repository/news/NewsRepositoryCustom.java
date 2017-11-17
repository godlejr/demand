package com.demand.site.repository.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demand.site.common.entity.News;

public interface NewsRepositoryCustom {
	Page<News> findAllByPageable(Pageable pageable) throws Exception;

}
