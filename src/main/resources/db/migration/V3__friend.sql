create table user_friends(
    user_id     bigint      not null,
    friend_id   bigint      not null,
    foreign key(user_id)        references users (id),
    foreign key(friend_id)         references users (id)
);

insert into user_friends (user_id, friend_id) values (1, 2);
insert into user_friends (user_id, friend_id) values (1, 3);
insert into user_friends (user_id, friend_id) values (2, 1);