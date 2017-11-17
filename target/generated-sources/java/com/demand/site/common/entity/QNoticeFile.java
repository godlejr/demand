package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNoticeFile is a Querydsl query type for NoticeFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNoticeFile extends EntityPathBase<NoticeFile> {

    private static final long serialVersionUID = 1001575149L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeFile noticeFile = new QNoticeFile("noticeFile");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final QFile file;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QNotice notice;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QNoticeFile(String variable) {
        this(NoticeFile.class, forVariable(variable), INITS);
    }

    public QNoticeFile(Path<? extends NoticeFile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNoticeFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNoticeFile(PathMetadata<?> metadata, PathInits inits) {
        this(NoticeFile.class, metadata, inits);
    }

    public QNoticeFile(Class<? extends NoticeFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file"), inits.get("file")) : null;
        this.notice = inits.isInitialized("notice") ? new QNotice(forProperty("notice"), inits.get("notice")) : null;
    }

}

