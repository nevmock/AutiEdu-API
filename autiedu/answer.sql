create table autiedu.answer
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    question_id binary(16)                               not null,
    option_id   binary(16)                               not null,
    constraint answer_ibfk_1
        foreign key (question_id) references autiedu.question (id),
    constraint answer_ibfk_2
        foreign key (option_id) references autiedu.options (id)
);

create index option_id
    on autiedu.answer (option_id);

create index question_id
    on autiedu.answer (question_id);

