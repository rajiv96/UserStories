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
public int createMarketOrder(MarketOrderRepository m){
	final String sql="insert into marketTrades(uid,size,Type,currpair,price,Time) values(?,?,?,?,?,?)";
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
		        statement.setDouble(5, m.getPrice());
		        statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	//final String sql2="select mid from marketTrades(uid,size,Type,currpair,price,Time) where uid=m.getUid() & size=m.getSize()";
	
	//return m.getMid();
}

@Transactional
public int createLimitOrder(LimitOrderRepository m){
	final String sql="insert into marketTrades(uid,size,Type,currpair,price,Time) values(?,?,?,?,?,?)";
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
		        statement.setDouble(5, m.getPrice());
		        statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	//final String sql2="select mid from marketTrades(uid,size,Type,currpair,price,Time) where uid=m.getUid() & size=m.getSize()";
	
	//return m.getMid();
}
}
