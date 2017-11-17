package com.demand.site.repository.question;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.demand.site.common.entity.QQuestion;
import com.demand.site.common.entity.Question;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.SearchResults;

public class QuestionRepositoryImpl extends QueryDslRepositorySupport implements QuestionRepositoryCustom {

	public QuestionRepositoryImpl() {
		super(Question.class);
	}

	@Override
	public Page<Question> findAllByQuestionCategoryIdAndPageable(String search, long questionCategoryId,
			Pageable pageable) throws Exception {
		QQuestion question = QQuestion.question;

		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (search != null) {
			booleanBuilder.and((question.title.like("%" + search + "%")).or(question.content.like("%" + search + "%"))
					.or(question.writer.like("%" + search + "%")));
		}

		if (questionCategoryId > 0) {
			booleanBuilder.and(question.questionCategory.id.eq(questionCategoryId));
		}

		SearchResults<Question> searchResults = from(question).where(booleanBuilder).orderBy(question.createdAt.desc())
				.offset(offset).limit(size).listResults(question);

		long total = searchResults.getTotal();

		List<Question> questions = total > offset ? searchResults.getResults() : Collections.<Question>emptyList();

		return new PageImpl<Question>(questions, pageable, total);
	}

	@Override
	public Page<Question> findAllByQuestionCategoryIdAndPageableAndIdNot(long id, long questionCategoryId, Pageable pageable)
			throws Exception {
		QQuestion question = QQuestion.question;

		int offset = pageable.getOffset();
		int size = pageable.getPageSize();

		SearchResults<Question> searchResults = from(question)
				.where(question.questionCategory.id.eq(questionCategoryId).and(question.id.ne(id)).and(question.type.ne(1))).orderBy(question.updatedAt.desc())
				.offset(offset).limit(size).listResults(question);

		long total = searchResults.getTotal();

		List<Question> questions = total > offset ? searchResults.getResults() : Collections.<Question>emptyList();

		return new PageImpl<Question>(questions, pageable, total);
	}

}
