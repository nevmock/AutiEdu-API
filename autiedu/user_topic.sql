create table autiedu.user_topic
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    user_id     binary(16)                               not null,
    topic_id    binary(16)                               not null,
    is_unlocked tinyint(1)                               not null,
    is_finished tinyint(1)                               not null,
    constraint user_topic_ibfk_1
        foreign key (user_id) references autiedu.users (id),
    constraint user_topic_ibfk_2
        foreign key (topic_id) references autiedu.topic (id)
);

create index topic_id
    on autiedu.user_topic (topic_id);

create index user_id
    on autiedu.user_topic (user_id);

