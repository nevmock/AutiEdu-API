create table autiedu.users
(
    id               binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    email            varchar(100)                             not null,
    password         varchar(100)                             not null,
    role             enum ('admin', 'user')                   null,
    name             varchar(100)                             not null,
    class_name       varchar(100)                             not null,
    phone_number     varchar(100)                             not null,
    is_enabled_music tinyint(1) default 1                     null,
    token            varchar(100)                             null,
    token_expired_at bigint                                   null,
    constraint token
        unique (token)
);

