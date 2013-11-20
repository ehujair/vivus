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
    CODE varchar(100),
    WRITE_TIME TIMESTAMP,
    primary key (MAC)
);

create table FILE_ITEM (
    ID varchar(64),
    NAME varchar(500),
    PATH varchar(1000),
    TYPE varchar(10),
    SIZE BIGINT,
    primary key (ID)
);
