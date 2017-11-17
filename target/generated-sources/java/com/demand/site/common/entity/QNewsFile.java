package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNewsFile is a Querydsl query type for NewsFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNewsFile extends EntityPathBase<NewsFile> {

    private static final long serialVersionUID = 762157992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNewsFile newsFile = new QNewsFile("newsFile");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final QFile file;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QNews news;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QNewsFile(String variable) {
        this(NewsFile.class, forVariable(variable), INITS);
    }

    public QNewsFile(Path<? extends NewsFile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNewsFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNewsFile(PathMetadata<?> metadata, PathInits inits) {
        this(NewsFile.class, metadata, inits);
    }

    public QNewsFile(Class<? extends NewsFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file"), inits.get("file")) : null;
        this.news = inits.isInitialized("news") ? new QNews(forProperty("news"), inits.get("news")) : null;
    }

}

