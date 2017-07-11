
create table users(
uid int NOT NULL AUTO_INCREMENT,
username varchar(255) NOT NULL,
password varchar(255) NOT NULL,
desig varchar(255) NOT NULL,
PRIMARY KEY (uid)
);


create table limitTrades(
lid int NOT NULL AUTO_INCREMENT,
uid int NOT NULL,
size varchar(255) NOT NULL,
Type varchar(255) NOT NULL,
price double NOT NULL,
Time time,
currpair varchar(255),
PRIMARY KEY (lid)
);


create table marketTrades(
mid int NOT NULL AUTO_INCREMENT,
uid int NOT NULL,
size varchar(255) NOT NULL,
Type varchar(255) NOT NULL,
price double NOT NULL,
Time time,
currpair varchar(255),
PRIMARY KEY (mid)
);

create table finishedTrades(
fid int NOT NULL AUTO_INCREMENT,
buid int NOT NULL,
suid int NOT NULL,
mid int NOT NULL,
lid int NOT NULL,
currpair varchar(255),
size varchar(255) NOT NULL,
price double NOT NULL,
Time time,
PRIMARY KEY (fid)
);

create table historicTrades(
hid int NOT NULL AUTO_INCREMENT,
currpair varchar(255),
size varchar(255) NOT NULL,
price double NOT NULL,
Time time,
PRIMARY KEY (hid)
);