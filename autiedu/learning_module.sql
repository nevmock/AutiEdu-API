create table autiedu.learning_module
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    name        varchar(100)                             not null,
    description text                                     not null,
    method      varchar(100)                             not null
);

