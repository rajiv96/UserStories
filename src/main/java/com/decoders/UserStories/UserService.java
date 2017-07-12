package com.decoders.UserStories;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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

@Transactional
public String convertCsvtodb() throws IOException {
	
	Scanner scanner  = new Scanner(new File("/home/java/Downloads/UserStories/src/main/java/com/decoders/UserStories/data.csv"));
String temp;
Scanner dataScanner = null;
int index = 0;
List<HistoricTradesRepository> recordList = new ArrayList<>();
scanner.nextLine();
while (scanner.hasNextLine()) {
	dataScanner = new Scanner(scanner.nextLine());
	dataScanner.useDelimiter(",");
	HistoricTradesRepository record = new HistoricTradesRepository();

	while (dataScanner.hasNext()) {
		String data = dataScanner.next();
		if(data==null|| data.equals(""))
		{
			record = null;
			break;
		}
		else if (index == 0 && data!=null)
			record.setBuid(Integer.parseInt(data));
		else if (index == 1 && data!=null)
			record.setSuid(Integer.parseInt(data));
		else if (index == 2 && data!=null)
			record.setCurrpair(data);
		else if(index==3 && data!=null)
		{
			record.setSize(Integer.parseInt(data));
		}
		else if(index==4 && data!=null)
		{
			record.setPrice(Double.parseDouble(data));
		}
		else if (index == 5 && data!=null)
		{
			temp=data.replace('_',' ');
			
			record.setTime(Timestamp.valueOf(temp));
			//record.setDate_time(data);	
		}
		else
			System.out.println("invalid data::" + data);
		
		index++;
	}
	index = 0;
	if(record!=null)
	recordList.add(record);
	
}//end of while
scanner.close();

for( int c=0;c<recordList.size();c++)
{
	final String sql="insert into historicTrades(buid,suid,currpair,size,price,Time) values(?,?,?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	final int i=c;
	System.out.println(i+" ");
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		      
			  	statement.setInt(1, recordList.get(i).getBuid());
		       // else continue;
		        statement.setInt(2, recordList.get(i).getSuid());
		        statement.setString(3, recordList.get(i).getCurrpair());
		        statement.setInt(4, recordList.get(i).getSize());
		        statement.setDouble(5, recordList.get(i).getPrice());
		        statement.setTimestamp(6,recordList.get(i).getTime());   
		     
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
}

//return recordList;
//Iterator<HistoricTradesRepository> i = recordList.iterator();
//while(i.hasNext())
//{
//	
//}
return "Sahi hai";
}//end of fn
class AuditRowMapper implements RowMapper<FinishedTradeRepository>
{
	@Override
	public FinishedTradeRepository mapRow(ResultSet rs,int rowNum) throws SQLException
	{
		FinishedTradeRepository ftr=new FinishedTradeRepository();
		ftr.setBuid(rs.getInt("buid"));
		ftr.setCurrpair(rs.getString("currpair"));
		ftr.setFid(rs.getInt("fid"));
		ftr.setLid(rs.getInt("lid"));
		ftr.setMid(rs.getInt("mid"));
		ftr.setPrice(rs.getDouble("price"));
		ftr.setSize(rs.getInt("size"));
		ftr.setTime(rs.getTimestamp("Time"));
		
		return ftr ;
		
	}
}
class Audit2RowMapper implements RowMapper<CancelledTradeRepository>
{
	@Override
	public CancelledTradeRepository mapRow(ResultSet rs,int rowNum) throws SQLException
	{
		CancelledTradeRepository ftr=new CancelledTradeRepository();
		ftr.setTradetype(rs.getString("tradetype"));
		ftr.setCurrpair(rs.getString("currpair"));
		ftr.setId(rs.getInt("id"));
		ftr.setUid(rs.getInt("uid"));
		ftr.setType(rs.getString("Type"));
		ftr.setLimittime(rs.getInt("Limittime"));
		
		//ftr.setFid(rs.getInt("fid"));
		//ftr.setLid(rs.getInt("lid"));
		//ftr.setMid(rs.getInt("mid"));
		ftr.setPrice(rs.getDouble("price"));
		ftr.setSize(rs.getInt("size"));
		ftr.setTime(rs.getTimestamp("Time"));
		
		return ftr ;
		
	}
}
@Transactional(readOnly=true)
public String auditCTR() {
	jdbcTemplate.query("select * from cancelledTrade", new Audit2RowMapper());
	return "AuditCTR ";
}

@Transactional(readOnly=true)
public String auditFTR() {
	 jdbcTemplate.query("select * from finishedTrades", new AuditRowMapper());
	 return "AuditFTR ";
//	final String sql="select * from finishedTrades";
//	 Statement stmt = con.createStatement();
////     ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
//     System.out.println("id  name    job");
//     
//     while (rs.next()) {
//        int id = rs.getInt("id");
//        String name = rs.getString("name");
//        String job = rs.getString("job");
//        System.out.println(id+"   "+name+"    "+job);
//	// TODO Auto-generated method stub
//	return null;
}
@Transactional(readOnly=true)
public List<TradeRepository> findAllOrders() {
return jdbcTemplate.query("select uid,size,Type,currpair,Time,Limittime,tradetype,price from trade",new UserRowMapper());

}

}

class UserRowMapper implements RowMapper<TradeRepository>
{
	@Override
	public TradeRepository mapRow(ResultSet rs, int rowNum) throws SQLException {
		TradeRepository order = new TradeRepository();
		order.setCurrpair(rs.getString("currpair"));
		order.setPrice(rs.getDouble("price"));
		order.setUid(rs.getInt("uid"));
		order.setType(Type.valueOf(rs.getString("Type")));
		order.setSize(rs.getInt("size"));
		order.setTime(rs.getTimestamp("Time"));
		order.setTradetype(tradetype.valueOf(rs.getString("tradetype")));
		order.setLimittime(rs.getInt("limittime"));
		return order;
	}
	
}

