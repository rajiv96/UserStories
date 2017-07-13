insert into users (username,password,desig) values ('user','1234','admin');
--insert into limitTrades (uid,size,Type,price,Time,Timelimit,currpair) values (4,2,'BUY',2304,'2014-02-01 12:05:12',5,'USD/ABC');
--insert into marketTrades (uid,size,Type,price,Time,currpair) values (4,3,'BUY',2304,'2014-02-01 12:05:12','USD/ABC');
insert into finishedTrades (buid,suid,mid,lid,currpair,size,price,Time) values (1,2,3,4,'USD',324,2000,'2014-02-01 12:05:12');
insert into historicTrades (buid,suid,currpair,size,price,Time) values (2,23,'sad',123,20000,'2014-02-01 12:05:12');
insert into trade (uid,size,Type,price,Time,Limittime,currpair,tradetype) values (1,3,'BUY',2304,'2014-02-01 12:05:12',5,'USD/ABC','limit');
insert into cancelledTrade (uid,size,Type,price,Time,Limittime,currpair,tradetype) values (2,3,'SELL',123,'2014-02-01 12:05:12',5,'USD/ABC','limit');
