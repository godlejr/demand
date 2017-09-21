package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReport is a Querydsl query type for Report
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReport extends EntityPathBase<Report> {

    private static final long serialVersionUID = -1319536595L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReport report = new QReport("report");

    public final QBase _super = new QBase(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Integer> hits = createNumber("hits", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<ReportFile, QReportFile> reportFiles = this.<ReportFile, QReportFile>createList("reportFiles", ReportFile.class, QReportFile.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final QUser user;

    public QReport(String variable) {
        this(Report.class, forVariable(variable), INITS);
    }

    public QReport(Path<? extends Report> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReport(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReport(PathMetadata<?> metadata, PathInits inits) {
        this(Report.class, metadata, inits);
    }

    public QReport(Class<? extends Report> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

