package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDegreeCategory is a Querydsl query type for DegreeCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDegreeCategory extends EntityPathBase<DegreeCategory> {

    private static final long serialVersionUID = 2060918243L;

    public static final QDegreeCategory degreeCategory = new QDegreeCategory("degreeCategory");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QDegreeCategory(String variable) {
        super(DegreeCategory.class, forVariable(variable));
    }

    public QDegreeCategory(Path<? extends DegreeCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDegreeCategory(PathMetadata<?> metadata) {
        super(DegreeCategory.class, metadata);
    }

}

