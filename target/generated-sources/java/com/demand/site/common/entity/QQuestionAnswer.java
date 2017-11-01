package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QQuestionAnswer is a Querydsl query type for QuestionAnswer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuestionAnswer extends EntityPathBase<QuestionAnswer> {

    private static final long serialVersionUID = 906853693L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionAnswer questionAnswer = new QQuestionAnswer("questionAnswer");

    public final QBase _super = new QBase(this);

    public final QAnswer answer;

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QQuestion question;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QQuestionAnswer(String variable) {
        this(QuestionAnswer.class, forVariable(variable), INITS);
    }

    public QQuestionAnswer(Path<? extends QuestionAnswer> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestionAnswer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuestionAnswer(PathMetadata<?> metadata, PathInits inits) {
        this(QuestionAnswer.class, metadata, inits);
    }

    public QQuestionAnswer(Class<? extends QuestionAnswer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.answer = inits.isInitialized("answer") ? new QAnswer(forProperty("answer"), inits.get("answer")) : null;
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

