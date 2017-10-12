package com.demand.site.repository.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demand.site.common.entity.Report;

public interface ReportRepositoryCustom {

	Page<Report> findAllBySearchAndPageable(String search, Pageable pageable);

	Page<Report> findAllByUserIdAndPageable(long userId, Pageable pageable);
}
