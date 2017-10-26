package com.demand.site.common.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1777498372L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBase _super = new QBase(this);

    public final com.demand.site.common.embeddable.QAddress address;

    public final QFile avatarFile;

    public final NumberPath<Integer> checked = createNumber("checked", Integer.class);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final QDegreeCategory degreeCategory;

    public final QEducationStatusCategory educationStatusCategory;

    public final StringPath email = createString("email");

    public final StringPath finalUniversity = createString("finalUniversity");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath joinedAt = createString("joinedAt");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath major = createString("major");

    public final StringPath name = createString("name");

    public final ListPath<Notice, QNotice> notices = this.<Notice, QNotice>createList("notices", Notice.class, QNotice.class, PathInits.DIRECT2);

    public final StringPath ntisRegistrationNo = createString("ntisRegistrationNo");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final QPositionCategory positionCategory;

    public final StringPath registrationNo = createString("registrationNo");

    public final ListPath<Report, QReport> reports = this.<Report, QReport>createList("reports", Report.class, QReport.class, PathInits.DIRECT2);

    public final StringPath resignedAt = createString("resignedAt");

    public final StringPath secondEmail = createString("secondEmail");

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.demand.site.common.embeddable.QAddress(forProperty("address")) : null;
        this.avatarFile = inits.isInitialized("avatarFile") ? new QFile(forProperty("avatarFile")) : null;
        this.degreeCategory = inits.isInitialized("degreeCategory") ? new QDegreeCategory(forProperty("degreeCategory")) : null;
        this.educationStatusCategory = inits.isInitialized("educationStatusCategory") ? new QEducationStatusCategory(forProperty("educationStatusCategory")) : null;
        this.positionCategory = inits.isInitialized("positionCategory") ? new QPositionCategory(forProperty("positionCategory")) : null;
    }

}

