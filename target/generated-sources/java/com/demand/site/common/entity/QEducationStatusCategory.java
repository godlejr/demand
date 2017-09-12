package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEducationStatusCategory is a Querydsl query type for EducationStatusCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEducationStatusCategory extends EntityPathBase<EducationStatusCategory> {

    private static final long serialVersionUID = -485713153L;

    public static final QEducationStatusCategory educationStatusCategory = new QEducationStatusCategory("educationStatusCategory");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QEducationStatusCategory(String variable) {
        super(EducationStatusCategory.class, forVariable(variable));
    }

    public QEducationStatusCategory(Path<? extends EducationStatusCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEducationStatusCategory(PathMetadata<?> metadata) {
        super(EducationStatusCategory.class, metadata);
    }

}

