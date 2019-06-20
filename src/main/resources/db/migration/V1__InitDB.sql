create table category
(
    id         int8         not null,
    is_deleted boolean,
    name       varchar(255) not null,
    primary key (id)
);
create table commentary
(
    id         int8         not null,
    is_deleted boolean,
    image_url  varchar(512),
    rating     bigint default '0',
    text       varchar(255) not null,
    updated    timestamp,
    parent_id  int8,
    post_id    int8,
    "user_id"  int8,
    primary key (id)
);
create table hibernate_sequences
(
    sequence_name          varchar(255) not null,
    sequence_next_hi_value int8,
    primary key (sequence_name)
);
create table post
(
    id          int8 not null,
    is_deleted  boolean,
    image_url   varchar(512),
    rating      bigint default '0',
    title       varchar(255),
    updated     timestamp,
    category_id int8,
    user_id     int8,
    primary key (id)
);
create table translations
(
    id         int8         not null,
    is_deleted boolean,
    chanel     varchar(1),
    en         varchar(255),
    key        varchar(255) not null,
    kk         varchar(255),
    ru         varchar(255),
    primary key (id)
);
create table "user"
(
    id         int8         not null,
    is_deleted boolean,
    login      varchar(255) not null,
    name       varchar(255) not null,
    password   varchar(255) not null,
    updated    timestamp,
    primary key (id)
);
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