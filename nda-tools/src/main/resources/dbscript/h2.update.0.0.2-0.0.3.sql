create table FILE_ITEM (
    ID varchar(64),
    NAME varchar(500),
    PATH varchar(1000),
    TYPE varchar(10),
    SIZE BIGINT,
    primary key (ID)
);

commit;