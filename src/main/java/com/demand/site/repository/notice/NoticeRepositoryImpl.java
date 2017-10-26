package com.demand.site.repository.notice;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.QNotice;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.SearchResults;

public class NoticeRepositoryImpl  extends QueryDslRepositorySupport implements NoticeRepositoryCustom{

	public NoticeRepositoryImpl() {
		super(Notice.class);
	}

	@Override
	public Page<Notice> findAllByNoticeCategoryIdAndSearchAndPageable(long noticeCategoryId, String search,
			Pageable pageable) throws Exception {
		QNotice notice = QNotice.notice;
		
		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (search != null) {
			booleanBuilder.and((notice.title.like("%" + search + "%")).or(notice.content.like("%" + search + "%")));
		}
		
		if(noticeCategoryId > 0){
			booleanBuilder.and(notice.noticeCategory.id.eq(noticeCategoryId));
		}

		SearchResults<Notice> searchResults = from(notice).where(booleanBuilder)
				.orderBy(notice.type.desc(), notice.updatedAt.desc()).offset(offset).limit(size).listResults(notice);

		long total = searchResults.getTotal();

		List<Notice> notices = total > offset ? searchResults.getResults() : Collections.<Notice>emptyList();

		return new PageImpl<Notice>(notices, pageable, total);
	}
	
	

}
