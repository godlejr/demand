package com.demand.site.repository.news;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.demand.site.common.entity.News;
import com.demand.site.common.entity.Notice;
import com.demand.site.common.entity.QNews;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.SearchResults;

public class NewsRepositoryImpl  extends QueryDslRepositorySupport implements NewsRepositoryCustom{

	public NewsRepositoryImpl() {
		super(News.class);
	}

	@Override
	public Page<News> findAllByPageable(Pageable pageable) throws Exception {
		QNews news = QNews.news;
		
		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		SearchResults<News> searchResults = from(news).where(booleanBuilder)
				.orderBy(news.createdAt.desc()).offset(offset).limit(size).listResults(news);

		long total = searchResults.getTotal();

		List<News> newsList = total > offset ? searchResults.getResults() : Collections.<News>emptyList();

		return new PageImpl<News>(newsList, pageable, total);
	}

}
