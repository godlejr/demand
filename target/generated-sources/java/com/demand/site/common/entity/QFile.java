package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1777042101L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final StringPath ext = createString("ext");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QNews news;

    public final ListPath<NewsFile, QNewsFile> newsFiles = this.<NewsFile, QNewsFile>createList("newsFiles", NewsFile.class, QNewsFile.class, PathInits.DIRECT2);

    public final ListPath<NoticeFile, QNoticeFile> noticeFiles = this.<NoticeFile, QNoticeFile>createList("noticeFiles", NoticeFile.class, QNoticeFile.class, PathInits.DIRECT2);

    public final StringPath originalName = createString("originalName");

    public final ListPath<ReportFile, QReportFile> reportFiles = this.<ReportFile, QReportFile>createList("reportFiles", ReportFile.class, QReportFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final StringPath storageName = createString("storageName");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFile(PathMetadata<?> metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news"), inits.get("news")) : null;
    }

}

