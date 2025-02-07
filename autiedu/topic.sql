create table autiedu.topic
(
    id                 binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    name               varchar(100)                             not null,
    description        text                                     not null,
    method             varchar(100)                             not null,
    level              int                                      not null,
    learning_module_id binary(16)                               not null,
    constraint topic_ibfk_1
        foreign key (learning_module_id) references autiedu.learning_module (id)
);

create index learning_module_id
    on autiedu.topic (learning_module_id);

