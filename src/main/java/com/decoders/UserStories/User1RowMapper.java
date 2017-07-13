package com.decoders.UserStories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

class User1RowMapper implements RowMapper<UserRepository>
{
	@Override
	public UserRepository mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserRepository order = new UserRepository();
		order.setUid(rs.getInt("uid"));
		order.setUsername(rs.getString("username"));
		order.setPassword(rs.getString("uid"));
		//order.set(Type.valueOf(rs.getString("Type")));
		order.setDesignation(rs.getString("desig"));
		//order.setTime(rs.getTimestamp("Time"));
	//	order.setTradetype(tradetype.valueOf(rs.getString("tradetype")));
	//	order.setLimittime(rs.getInt("limittime"));
		return order;
	}
}