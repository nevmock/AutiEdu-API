create table autiedu.user_question
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    user_id     binary(16)                               not null,
    question_id binary(16)                               not null,
    is_unlocked tinyint(1)                               not null,
    is_finished tinyint(1)                               not null,
    constraint user_question_ibfk_1
        foreign key (user_id) references autiedu.users (id),
    constraint user_question_ibfk_2
        foreign key (question_id) references autiedu.question (id)
);

create index question_id
    on autiedu.user_question (question_id);

create index user_id
    on autiedu.user_question (user_id);

