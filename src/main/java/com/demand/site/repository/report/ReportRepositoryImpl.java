package com.demand.site.repository.report;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.demand.site.common.entity.QReport;
import com.demand.site.common.entity.Report;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.SearchResults;

public class ReportRepositoryImpl extends QueryDslRepositorySupport implements ReportRepositoryCustom {

	public ReportRepositoryImpl() {
		super(Report.class);
	}

	@Override
	public Page<Report> findAllBySearchAndPageable(String search, Pageable pageable) {

		QReport report = QReport.report;

		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (search != null) {
			booleanBuilder.and((report.title.like("%" + search + "%")).or(report.content.like("%" + search + "%")).or(report.user.name.like("%" + search + "%")));
		}

		SearchResults<Report> searchResults = from(report).where(booleanBuilder)
				.orderBy(report.type.desc(), report.updatedAt.desc()).offset(offset).limit(size).listResults(report);

		long total = searchResults.getTotal();

		List<Report> reports = total > offset ? searchResults.getResults() : Collections.<Report>emptyList();

		return new PageImpl<Report>(reports, pageable, total);
	}

	@Override
	public Page<Report> findAllByUserIdAndPageable(long userId, Pageable pageable) {
		QReport report = QReport.report;

		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(report.user.id.eq(userId));
		
		SearchResults<Report> searchResults = from(report).where(booleanBuilder)
				.orderBy(report.updatedAt.desc()).offset(offset).limit(size).listResults(report);

		long total = searchResults.getTotal();

		List<Report> reports = total > offset ? searchResults.getResults() : Collections.<Report>emptyList();

		return new PageImpl<Report>(reports, pageable, total);
	}

}
