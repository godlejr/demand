package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBase is a Querydsl query type for Base
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QBase extends EntityPathBase<Base> {

    private static final long serialVersionUID = 1776915466L;

    public static final QBase base = new QBase("base");

    public final StringPath createdAt = createString("createdAt");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath updatedAt = createString("updatedAt");

    public QBase(String variable) {
        super(Base.class, forVariable(variable));
    }

    public QBase(Path<? extends Base> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBase(PathMetadata<?> metadata) {
        super(Base.class, metadata);
    }

}

