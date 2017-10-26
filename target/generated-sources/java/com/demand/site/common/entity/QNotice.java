package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = -1424705071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotice notice = new QNotice("notice");

    public final QBase _super = new QBase(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QNoticeCategory noticeCategory;

    public final ListPath<NoticeFile, QNoticeFile> noticeFiles = this.<NoticeFile, QNoticeFile>createList("noticeFiles", NoticeFile.class, QNoticeFile.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final QUser user;

    public QNotice(String variable) {
        this(Notice.class, forVariable(variable), INITS);
    }

    public QNotice(Path<? extends Notice> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotice(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotice(PathMetadata<?> metadata, PathInits inits) {
        this(Notice.class, metadata, inits);
    }

    public QNotice(Class<? extends Notice> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.noticeCategory = inits.isInitialized("noticeCategory") ? new QNoticeCategory(forProperty("noticeCategory")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

