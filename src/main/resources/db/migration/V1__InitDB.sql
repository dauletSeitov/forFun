create table category
(
    id         bigint         not null          unique,
    is_deleted boolean        default false,
    name       varchar(255) not null,
    primary key (id)
);
create table commentary
(
    id         bigint    not null               unique,
    is_deleted boolean        default false,
    image_url  varchar(512),
    rating     bigint default '0',
    text       varchar(255) not null,
    updated    timestamp,
    parent_id  bigint,
    post_id    bigint,
    "user_id"  bigint,
    primary key (id)
);
create table hibernate_sequences
(
    sequence_name          varchar(255) not null,
    sequence_next_hi_value bigint,
    primary key (sequence_name)
);
create table post
(
    id          bigint    not null          unique,
    is_deleted  boolean        default false,
    image_url   varchar(512),
    rating      bigint default '0',
    title       varchar(255),
    updated     timestamp,
    created     timestamp,
    category_id bigint,
    user_id     bigint,
    primary key (id)
);
create table translations
(
    id         bigint         not null          unique,
    is_deleted boolean        default false,
    chanel     varchar(1),
    en         varchar(255),
    key        varchar(255) not null,
    kk         varchar(255),
    ru         varchar(255),
    primary key (id)
);
create table "user"
(
    id         bigint         not null          unique,
    is_deleted boolean        default false,
    login      varchar(255) not null,
    name       varchar(255) not null,
    password   varchar(255) not null,
    updated    timestamp,
    primary key (id)
);

create table user_post_vote_history
(
    id            bigint not null
        constraint user_post_vote_history_pkey
            primary key,
    is_deleted    boolean        default false,
    is_down_voted boolean,
    is_up_voted   boolean,
    post_id       bigint
        constraint fk2nx5kjegmoalmy1xn4ab58k0u
            references post,
    user_id       bigint
        constraint fkrst3nnbkigbh0n6qdb5lrxfa6
            references "user"
);

create table property(

     id             bigint          not null unique,
     is_deleted     boolean         default false,
     code           varchar(30),
     name           varchar(255),
     value          varchar(255)
);


CREATE UNIQUE INDEX property_not_deleted
    ON property( (CASE WHEN IS_DELETED = false
                       THEN code
                   ELSE NULL
        END) );

alter table translations
    add constraint UK_sj8hdux3oddcc3g4ridhmu9jc unique (key);
alter table "user"
    add constraint UK_ew1hvam8uwaknuaellwhqchhb unique (login);
alter table commentary
    add constraint FKfiq3wbxgdhom8ewyh4onq9c41 foreign key (parent_id) references commentary;
alter table commentary
    add constraint FK7u0oo6wyaothqx4xcjh4ex0u6 foreign key (post_id) references post;
alter table commentary
    add constraint FKnbelkrdwcbruk4rwm2nxvm1g0 foreign key ("user_id") references "user";
alter table post
    add constraint FKg6l1ydp1pwkmyj166teiuov1b foreign key (category_id) references category;
alter table post
    add constraint FK51aeb5le008k8klgnyfaalmn foreign key (user_id) references "user";
alter table user_post_vote_history
    owner to postgres;