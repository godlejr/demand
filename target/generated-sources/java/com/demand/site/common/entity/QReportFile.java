package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QReportFile is a Querydsl query type for ReportFile
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReportFile extends EntityPathBase<ReportFile> {

    private static final long serialVersionUID = -92732599L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportFile reportFile = new QReportFile("reportFile");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final QFile file;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QReport report;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QReportFile(String variable) {
        this(ReportFile.class, forVariable(variable), INITS);
    }

    public QReportFile(Path<? extends ReportFile> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReportFile(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QReportFile(PathMetadata<?> metadata, PathInits inits) {
        this(ReportFile.class, metadata, inits);
    }

    public QReportFile(Class<? extends ReportFile> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file")) : null;
        this.report = inits.isInitialized("report") ? new QReport(forProperty("report"), inits.get("report")) : null;
    }

}

