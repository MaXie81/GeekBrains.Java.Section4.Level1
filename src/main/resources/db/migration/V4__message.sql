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

insert into messages(author_id, recipient_id, body, created_at) values(1, 2, 'Боб-Макс: Привет!', current_timestamp() -6.5);
insert into messages(author_id, recipient_id, body, created_at) values(2, 1, 'Макс-Боб: Привет!', current_timestamp() -5.5);
insert into messages(author_id, recipient_id, body, created_at) values(1, 2, 'Боб-Макс: Как дела?', current_timestamp() -4.5);
insert into messages(author_id, recipient_id, body, created_at) values(2, 1, 'Макс-Боб: Норм! Как сам?', current_timestamp() -3.5);
insert into messages(author_id, recipient_id, body, created_at) values(1, 2, 'Боб-Макс: Что у тебя нового?', current_timestamp() -2.5);
insert into messages(author_id, recipient_id, body, created_at) values(2, 1, 'Макс-Боб: Да без изменений! Как у тебя с обучением?', current_timestamp() -1.5);
insert into messages(author_id, recipient_id, body, created_at) values(1, 2, 'Боб-Макс: Учусь. )', current_timestamp() -0.5);

insert into messages(author_id, recipient_id, body, created_at) values(1, 3, 'Боб-Том: Привет, Том!', current_timestamp() -3.3);
insert into messages(author_id, recipient_id, body, created_at) values(3, 1, 'Том-Боб: Привет.', current_timestamp() -2.3);
insert into messages(author_id, recipient_id, body, created_at) values(1, 3, 'Боб-Том: Что хмурый такой7', current_timestamp() -1.3);
insert into messages(author_id, recipient_id, body, created_at) values(3, 1, 'Том-Боб: Задолбался.', current_timestamp() -0.3);

insert into messages(author_id, recipient_id, body, created_at) values(3, 2, 'Том-Макс: Здорово.', current_timestamp() -3.1);
insert into messages(author_id, recipient_id, body, created_at) values(2, 3, 'Макс-Том: Привет, Боб!', current_timestamp() -2.1);
insert into messages(author_id, recipient_id, body, created_at) values(3, 2, 'Том-Макс: Ты домашку сделал?', current_timestamp() -1.1);
insert into messages(author_id, recipient_id, body, created_at) values(2, 3, 'Макс-Том: В процессе!', current_timestamp() -0.1);