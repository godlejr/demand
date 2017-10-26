package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNoticeCategory is a Querydsl query type for NoticeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNoticeCategory extends EntityPathBase<NoticeCategory> {

    private static final long serialVersionUID = -569935377L;

    public static final QNoticeCategory noticeCategory = new QNoticeCategory("noticeCategory");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<Notice, QNotice> notices = this.<Notice, QNotice>createList("notices", Notice.class, QNotice.class, PathInits.DIRECT2);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QNoticeCategory(String variable) {
        super(NoticeCategory.class, forVariable(variable));
    }

    public QNoticeCategory(Path<? extends NoticeCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeCategory(PathMetadata<?> metadata) {
        super(NoticeCategory.class, metadata);
    }

}

