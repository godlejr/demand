package com.demand.site.repository.newsfile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.NewsFile;

public interface NewsFileRepository extends JpaRepository<NewsFile, Long> {

}
