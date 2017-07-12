//package com.decoders.UserStories;
//
//	import java.io.File;
//	import java.io.IOException;
//	import java.sql.Connection;
//	import java.sql.PreparedStatement;
//	import java.sql.SQLException;
//	import java.sql.Statement;
//	import java.sql.Time;
//	import java.sql.Timestamp;
//	import java.util.ArrayList;
//	import java.util.Arrays;
//	import java.util.List;
//	import java.util.Scanner;
//
//	import javax.sql.DataSource;
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.jdbc.core.JdbcTemplate;
//	import org.springframework.jdbc.core.PreparedStatementCreator;
//	import org.springframework.jdbc.support.GeneratedKeyHolder;
//	import org.springframework.jdbc.support.KeyHolder;
//	import org.springframework.transaction.annotation.Transactional;
//
//	import ch.qos.logback.core.net.SyslogOutputStream;
//
//	public class CSVScanner {
//		
//		
//		@Autowired
//		private JdbcTemplate jdbcTemplate;
//		
//		private DataSource datasource;
//		  
//		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
//		    this.jdbcTemplate = jdbcTemplate;  
//		}  
//		/*
//		public DataSource getDatasource() {
//			return datasource;
//		}
//
//
//
//		public void setDatasource(DataSource datasource) {
//			this.datasource = datasource;
//		}
//
//	*/
//
//		public JdbcTemplate getJdbcTemplate() {
//			return jdbcTemplate;
//		}
//
//
//
//		
//		/*	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//			this.jdbcTemplate = new JdbcTemplate(datasource);
//		}
//
//
//	*/
////end of class
//
//			
//			// final String sql="insert into trade_history(tradeid,currency_pair,price,lot_size,date_time) values (?,?,?,?,?)";
//			/* jdbcTemplate.update(sql);*/
//		
//	//jdbcTemplate.update(sql,1,record.getCurrency_pair(),record.getPrice(),record.getLot_size(),record.getDate_time());
//			
//				//jdbcTemplate.update(sql);
//			//this.getJdbcTemplate().update(sql);
//		
//		
//			
////			KeyHolder holder=new GeneratedKeyHolder(); 
////			jdbcTemplate.update(new PreparedStatementCreator(){
////				@Override
////				public PreparedStatement createPreparedStatement(Connection conn)throws SQLException{
////					PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
////					statement.setInt(1,1);
////					statement.setString(2,record.getCurrency_pair());
////					statement.setInt(4,record.getLot_size());
////					statement.setDouble(3,record.getPrice());
////					statement.setTimestamp(5,record.getDate_time());
////					return statement;
//					/* tradeid,currency_pair,price,lot_size,date_time*/
//			//	}
//			//}
//			
////			},holder);
//			
//			
////			int  tradeid=holder.getKey().intValue();
////			return tradeid;
//		//
//
//
//
