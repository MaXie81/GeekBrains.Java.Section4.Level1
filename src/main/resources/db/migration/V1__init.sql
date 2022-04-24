create table users(
    id           bigserial  primary key,
    username    varchar(36) unique,
    password    varchar(80) not null,
    email       varchar(50)
);

insert into users (username, password, email) values ('bob', '1', 'bob@mail.ru');
insert into users (username, password, email) values ('max', '2', 'max@mail.ru');
insert into users (username, password) values ('tom', '3');