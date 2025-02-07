create table autiedu.question
(
    id                 binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    topic_id           binary(16)                               not null,
    level              int                                      not null,
    media_type         enum ('video', 'image')                  not null,
    src                varchar(255)                             not null,
    is_multiple_option tinyint(1)                               not null,
    text               varchar(255)                             not null,
    constraint question_ibfk_1
        foreign key (topic_id) references autiedu.topic (id)
);

create index topic_id
    on autiedu.question (topic_id);

