package com.decoders.UserStories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
@Autowired
private JdbcTemplate jdbcTemplate;

@Transactional
public int createMarketOrder(TradeRepository m){
	final String sql="insert into trade(uid,size,Type,currpair,Time,tradetype) values(?,?,?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        statement.setInt(1, m.getUid());
		        statement.setLong(2, m.getSize());
		        statement.setString(3, m.getType().toString());
		        statement.setString(4, m.getCurrpair());
		       // statement.setDouble(5, m.getPrice());
		        statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
		        statement.setString(6,"market");
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	//final String sql2="select mid from marketTrades(uid,size,Type,currpair,price,Time) where uid=m.getUid() & size=m.getSize()";
	
	//return m.getMid();
}

@Transactional
public int createLimitOrder(TradeRepository m){
	final String sql="insert into trade(uid,size,Type,currpair,price,Limittime,Time,tradetype) values(?,?,?,?,?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        statement.setInt(1, m.getUid());
		        statement.setLong(2, m.getSize());
		        statement.setString(3, m.getType().name());
		        statement.setString(4, m.getCurrpair());
		        statement.setDouble(5, m.getPrice());
		        System.out.println(m.getLimittime());
		        statement.setInt(6,m.getLimittime());
		        statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));   
		        statement.setString(8, "limit");
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	//final String sql2="select mid from marketTrades(uid,size,Type,currpair,price,Time) where uid=m.getUid() & size=m.getSize()";
	
	//return m.getMid();
}

public String cancelOrder(int c) {
	final String sql1= "insert into cancelledTrade (uid,size,Type,price,Time,Limittime,currpair,tradetype) select uid,size,Type,price,Time,Limittime,currpair,tradetype from trade where id = ?";
	jdbcTemplate.update(sql1,c);
	final String sql="DELETE FROM trade WHERE id = ?";
	int count=jdbcTemplate.update(sql,c);
	if (count==0)
		return "Nothing to delete";
	else
		return "Successfully deleted";
//	return (int) primaryKey;
	// TODO Auto-generated method stub

}
}
