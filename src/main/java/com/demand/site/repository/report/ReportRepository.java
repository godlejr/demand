package com.demand.site.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {

	Report findFirstByIdLessThanOrderByIdDesc(long id) throws Exception;

	Report findFirstByIdGreaterThanOrderByIdAsc(long id) throws Exception;

}
