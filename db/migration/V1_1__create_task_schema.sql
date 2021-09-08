create table task
(
    id          varchar(36) not null,
    name        varchar(20),
    description varchar(255),
    user_id     varchar(36),
    primary key (id),
    constraint fk_user_id foreign key (user_id) references user (id)
);