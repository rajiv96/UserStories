drop table users if exists;
drop table limitTrades if exists;
drop table marketTrades if exists;
drop table finishedTrades if exists;
drop table historicTrades if exists;

create table users(
uid int(255) NOT NULL AUTO_INCREMENT,
username varchar(255) NOT NULL,
password varchar(255) NOT NULL,
desig varchar(255) NOT NULL,
PRIMARY KEY (uid)
);


create table limitTrades(
lid int(255) NOT NULL AUTO_INCREMENT,
uid int(255) NOT NULL,
size int(255) NOT NULL,
Type varchar(255) NOT NULL,
price double(255) NOT NULL,
Time timestamp,
Timelimit int(255) NOT NULL,
currpair varchar(255),
PRIMARY KEY (lid)
);


create table marketTrades(
mid int(255) NOT NULL AUTO_INCREMENT,
uid int(255) NOT NULL,
size int(255) NOT NULL,
Type varchar(255) NOT NULL,
price double(255) NOT NULL,
currpair varchar(255),
Time timestamp,
PRIMARY KEY (mid)
);

create table finishedTrades(
fid int(255) NOT NULL AUTO_INCREMENT,
buid int(255) NOT NULL,
suid int(255) NOT NULL,
mid int(255) NOT NULL,
lid int(255) NOT NULL,
currpair varchar(255),
size int(255) NOT NULL,
price double(255) NOT NULL,
Time timestamp,
PRIMARY KEY (fid)
);

create table historicTrades(
hid int(255) NOT NULL AUTO_INCREMENT,
currpair varchar(255),
size int(255) NOT NULL,
price double(255) NOT NULL,
Time timestamp,
PRIMARY KEY (hid)
);