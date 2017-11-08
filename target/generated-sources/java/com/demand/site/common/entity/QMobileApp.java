package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMobileApp is a Querydsl query type for MobileApp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMobileApp extends EntityPathBase<MobileApp> {

    private static final long serialVersionUID = -1796283258L;

    public static final QMobileApp mobileApp = new QMobileApp("mobileApp");

    public final QBase _super = new QBase(this);

    public final StringPath agreement = createString("agreement");

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final StringPath downloadUrl = createString("downloadUrl");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath intro = createString("intro");

    public final StringPath name = createString("name");

    public final StringPath privacy = createString("privacy");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QMobileApp(String variable) {
        super(MobileApp.class, forVariable(variable));
    }

    public QMobileApp(Path<? extends MobileApp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMobileApp(PathMetadata<?> metadata) {
        super(MobileApp.class, metadata);
    }

}

