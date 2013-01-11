create table account (
	user_name varchar(40) not null,
	password varchar(50) not null,
	constraint pk_account primary key (user_name)
);

