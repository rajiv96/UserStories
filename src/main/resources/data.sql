insert into users (username,password,desig) values ('user','1234','admin');
insert into limitTrades (uid,size,Type,price,Time,currpair) values (4,2,'BUY',2304,'2014-02-01 12:05:12','USD/ABC');
insert into marketTrades (uid,size,Type,price,Time,currpair) values (4,3,'BUY',2304,'2014-02-01 12:05:12','USD/ABC');
insert into finishedTrades (buid,suid,mid,lid,currpair,size,price,Time) values (1,2,3,4,'USD',324,2000,'2014-02-01 12:05:12');
insert into historicTrades (currpair,size,price,Time) values ('sad',123,20000,'2014-02-01 12:05:12');

