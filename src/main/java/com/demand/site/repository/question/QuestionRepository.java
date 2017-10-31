package com.demand.site.repository.question;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demand.site.common.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

	Question findByIdAndPassword(long id, String password);

}
