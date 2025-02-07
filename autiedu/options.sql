create table autiedu.options
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    question_id binary(16)                               not null,
    text        text                                     not null,
    constraint options_ibfk_1
        foreign key (question_id) references autiedu.question (id)
);

create index question_id
    on autiedu.options (question_id);

