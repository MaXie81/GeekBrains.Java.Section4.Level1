create table messages(
    id                  bigserial       primary key,
    author_id           bigint          not null,
    type_code           varchar(10)     not null,
    answer_author_id    bigint,
    like_count          bigint          not null        default 0,
    body                varchar(255),
    created_at          timestamp                       default current_timestamp,
    foreign key(author_id)        references users(id),
    foreign key(answer_author_id) references users(id)
);