package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPositionCategory is a Querydsl query type for PositionCategory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPositionCategory extends EntityPathBase<PositionCategory> {

    private static final long serialVersionUID = 364445952L;

    public static final QPositionCategory positionCategory = new QPositionCategory("positionCategory");

    public final QBase _super = new QBase(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public final ListPath<User, QUser> users = this.<User, QUser>createList("users", User.class, QUser.class, PathInits.DIRECT2);

    public QPositionCategory(String variable) {
        super(PositionCategory.class, forVariable(variable));
    }

    public QPositionCategory(Path<? extends PositionCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPositionCategory(PathMetadata<?> metadata) {
        super(PositionCategory.class, metadata);
    }

}

