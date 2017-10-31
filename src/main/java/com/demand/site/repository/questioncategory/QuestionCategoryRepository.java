package com.demand.site.repository.questioncategory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.QuestionCategory;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {

}
