create table autiedu.answer
(
    id          binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    question_id        binary(16)                            not null,
    option_id binary(16)                                     not null
);

