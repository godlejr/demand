package com.demand.site.repository.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice,Long> ,NoticeRepositoryCustom {

	

}
