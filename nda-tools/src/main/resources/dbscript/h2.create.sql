create table KEY_VALUE (
    KEY_ varchar(64),
    MIN_VALUE integer not null,
    MAX_VALUE integer not null,
    CURRENT_VALUE integer not null,
    RECYCLE boolean not null,
    primary key (KEY_)
);

create table MAC_ADDRESS (
    MAC varchar(64),
    WRITE_TIME TIMESTAMP,
    primary key (MAC)
);