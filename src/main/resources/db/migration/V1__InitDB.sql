
create table "user"
(
    id         bigint       not null
        constraint user_pkey
            primary key,
    is_deleted boolean      default  false,
    login      varchar(255) not null unique,
    name       varchar(255) not null,
    password   varchar(255) not null,
    updated    timestamp,
    photo_url  varchar(255),
    email      varchar(64),
    phone      varchar(15),
    state      varchar(30)  not null,
    incorrect_attempt       integer  default 0,
    locked_time             timestamp
);

CREATE UNIQUE INDEX user_not_deleted
    ON "user"( (CASE WHEN IS_DELETED = false
                           THEN login
                       ELSE NULL
        END) );



create table category
(
    id         bigint       not null
        constraint category_pkey
            primary key,
    is_deleted boolean default false,
    name       varchar(255) not null
);

CREATE UNIQUE INDEX category_not_deleted
    ON category( (CASE WHEN IS_DELETED = false
                         THEN name
                     ELSE NULL
        END) );




create table post
(
    id          bigint not null
        constraint post_pkey
            primary key,
    is_deleted  boolean default false,
    image_url   varchar(512),
    rating      bigint  default '0' not null,
    title       varchar(255),
    updated     timestamp,
    created     timestamp,
    category_id bigint
        constraint post_category_fk
            references category,
    user_id     bigint
        constraint post_user_fk
            references "user"
);



create table commentary
(
    id         bigint       not null
        constraint commentary_pkey
            primary key,
    is_deleted boolean default false,
    image_url  varchar(512),
    rating     bigint  default '0' not null,
    text       varchar(255) not null,
    updated    timestamp,
    parent_id  bigint
        constraint commentary_commentary_fk
            references commentary,
    post_id    bigint
        constraint commentary_post_fk
            references post,
    user_id    bigint
        constraint commentary_user_fk
            references "user"
);



create table hibernate_sequences
(
    sequence_name          varchar(255) not null
        constraint hibernate_sequences_pkey
            primary key,
    sequence_next_hi_value bigint
);



create table property
(
    id         bigint not null
        constraint property_id_key
            primary key,
    is_deleted boolean default false,
    code       varchar(30),
    name       varchar(255),
    value      varchar(255)
);

CREATE UNIQUE INDEX property_not_deleted
    ON property( (CASE WHEN IS_DELETED = false
                           THEN code
                       ELSE NULL
        END) );



create table translations
(
    id         bigint       not null
        constraint translations_pkey
            primary key,
    is_deleted boolean default false,
    chanel     varchar(1),
    en         varchar(255),
    key        varchar(255) not null unique,
    kk         varchar(255),
    ru         varchar(255)
);




create table user_post_vote_history
(
    id            bigint not null
        constraint user_post_vote_history_pkey
            primary key,
    is_deleted    boolean default false,
    is_down_voted boolean,
    is_up_voted   boolean,
    post_id       bigint
        constraint user_post_vote_history_post_fk
            references post,
    user_id       bigint
        constraint user_post_vote_history_user_fk
            references "user"
);





create table user_commentary_vote_history
(
    id            bigint not null
        constraint user_comment_vote_history_pkey
            primary key,
    is_deleted    boolean default false,
    is_down_voted boolean,
    is_up_voted   boolean,
    commentary_id bigint
        constraint user_comment_vote_history_comment_fk
            references post,
    user_id       bigint
        constraint user_comment_vote_history_user_fk
            references "user"
);

