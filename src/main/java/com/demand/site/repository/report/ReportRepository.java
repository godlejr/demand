package com.demand.site.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom{



	
}
