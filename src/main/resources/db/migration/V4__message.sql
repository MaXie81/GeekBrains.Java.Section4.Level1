create table messages(
    id                  bigserial       primary key,
    author_id           bigint          not null,
    recipient_id        bigint,
    answer_message_id   bigint,
    root_message_id     bigint,
    like_count          bigint          not null        default 0,
    body                varchar(255),
    created_at          timestamp                       default current_timestamp,
    foreign key(author_id)          references users(id),
    foreign key(recipient_id)       references users(id),
    foreign key(answer_message_id)  references messages(id),
    foreign key(root_message_id)    references messages(id)
);

insert into messages(author_id, recipient_id, body) values(1, 2, 'Привет!');
insert into messages(author_id, recipient_id, body) values(2, 1, 'Привет!');
insert into messages(author_id, recipient_id, body) values(1, 2, 'Как дела?');
insert into messages(author_id, recipient_id, body) values(2, 1, 'Норм! Как сам?');