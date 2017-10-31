package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuestionCategory is a Querydsl query type for QuestionCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestionCategory extends EntityPathBase<QuestionCategory> {

    private static final long serialVersionUID = 158111421L;

    public static final QQuestionCategory questionCategory = new QQuestionCategory("questionCategory");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<Question, QQuestion> questions = this.<Question, QQuestion>createList("questions", Question.class, QQuestion.class, PathInits.DIRECT2);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QQuestionCategory(String variable) {
        super(QuestionCategory.class, forVariable(variable));
    }

    public QQuestionCategory(Path<? extends QuestionCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionCategory(PathMetadata<?> metadata) {
        super(QuestionCategory.class, metadata);
    }

}

