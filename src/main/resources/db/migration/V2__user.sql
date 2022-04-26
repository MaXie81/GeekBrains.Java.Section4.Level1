create table users(
    id           bigserial      primary key,
    login       varchar(36)     unique,
    password    varchar(80)     not null,
    nickname    varchar(100)    not null,
    email       varchar(50)
);

insert into users (login, password, nickname, email) values ('bob', '1', 'Вася Пупкин', 'bob@mail.ru');
insert into users (login, password, nickname, email) values ('max', '2', 'Ночной Странник', 'max@mail.ru');
insert into users (login, password, nickname) values ('tom', '3', 'Домашний Кот');