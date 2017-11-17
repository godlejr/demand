package com.demand.site.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> ,NewsRepositoryCustom{

	News findFirstByIdLessThanOrderByIdDesc(long id);

	News findFirstByIdGreaterThanOrderByIdAsc(long id);


}
