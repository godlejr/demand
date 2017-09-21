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

    public static final QFile file = new QFile("file");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final StringPath ext = createString("ext");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath originalName = createString("originalName");

    public final ListPath<ReportFile, QReportFile> reportFiles = this.<ReportFile, QReportFile>createList("reportFiles", ReportFile.class, QReportFile.class, PathInits.DIRECT2);

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final StringPath storageName = createString("storageName");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata<?> metadata) {
        super(File.class, metadata);
    }

}

