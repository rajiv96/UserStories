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

//////////
@Transactional
public int createHistoryTransaction(HistoricTradesRepository m){
	final String sql="insert into historicTrades (currpair,size,price,Time) values(?,?,?,?)";
	
	
	/*historicTrades (buid,suid,currpair,size,price,Time) values (2,23,'sad',123,20000,'2014-02-01 12:05:12') */
	
	
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        //statement.setInt(1, m.getBuid());
		        //statement.setInt(2, m.getSuid());
		        statement.setString(1, m.getCurrpair());
		        statement.setInt(2, m.getSize());
		       statement.setDouble(3, m.getPrice());
		        statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
		        //statement.setString(6,"market");
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	
}

@Transactional
public int createCancelledOrder(CancelledTradeRepository m){
	final String sql="insert into cancelledTrade(uid,size,Type,currpair,price,Limittime,Time,tradetype) values(?,?,?,?,?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        statement.setInt(1, m.getUid());
		        statement.setLong(2, m.getSize());
		        statement.setString(3, m.getType());
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
	
}

//findcancelled
@Transactional(readOnly=true)
public List<CancelledTradeRepository> findcancelled() {
return jdbcTemplate.query("select uid,size,Type,price,Time,Limittime,currpair,tradetype from cancelledTrade",new AuditCancelled());

}



class AuditCancelled implements RowMapper<CancelledTradeRepository>
{
	@Override
	public CancelledTradeRepository mapRow(ResultSet rs,int rowNum) throws SQLException
	{
		CancelledTradeRepository ftr=new CancelledTradeRepository();
		ftr.setTradetype(rs.getString("tradetype"));
		ftr.setCurrpair(rs.getString("currpair"));
		//ftr.setId(rs.getInt("id"));
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




//findfinished
@Transactional(readOnly=true)
public List<FinishedTradeRepository> findfinished() {
return jdbcTemplate.query("select fid,buid,suid,mid,lid,currpair,size,price,Time from finishedTrades",new AuditRowMapper());

}


@Transactional
public int createFinished(FinishedTradeRepository m){
	final String sql="insert into finishedTrades(buid,suid,mid,lid,size,currpair,price,Time) values(?,?,?,?,?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        statement.setInt(1, m.getBuid());
		        statement.setInt(2, m.getSuid());
		        statement.setInt(3, m.getMid());
		        statement.setInt(4, m.getLid());
		        statement.setInt(5, m.getSize());
		        //System.out.println(m.getLimittime());
		        statement.setString(6,m.getCurrpair());
		        statement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));   
		        statement.setDouble(7,m.getPrice());
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	
}





///////////
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
	List<UserRepository> t= jdbcTemplate.query("select * from users where username =? AND password =?",new Object[]{m.getUsername(),Security.getMD5(m.getPassword())},new User1RowMapper());
	if(t.isEmpty())
		return 0;
	else
	{final String sql="insert into trade(uid,size,Type,currpair,price,Limittime,Time,tradetype) values(?,?,?,?,?,?,?,?)";
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


@Transactional(readOnly=true)
public List<HistoricTradesRepository> checkHistory() {

return jdbcTemplate.query("select currpair,size,price,Time from historicTrades",new HistoryMapping());

//historicTrades(buid,suid,currpair,size,price,Time) values(?,?,?,?)

}


@Transactional
public String convertCsvtodb() throws IOException {
	
	Scanner scanner  = new Scanner(new File("/home/java/Desktop/UserStories/src/main/java/com/decoders/UserStories/data.csv"));
String temp;
Scanner dataScanner = null;
int index = 0;
int flag = 0;
List<HistoricTradesRepository> recordList = new ArrayList<>();
List<HistoricTradesRepository> badRecordList = new ArrayList<>();
scanner.nextLine();
while (scanner.hasNextLine()) {
	dataScanner = new Scanner(scanner.nextLine());
	dataScanner.useDelimiter(",");
	
	HistoricTradesRepository record = new HistoricTradesRepository();
	while (dataScanner.hasNext() || index < 4) {
//		flag = 0;
//		if(data==null|| data.equals(""))
//		{
//			record = null;
//			break;
//		}
		if(!dataScanner.hasNext() && index == 3) {
			flag = -1;
			record.setTime(null);
		}
		else {
			String data = dataScanner.next();
			System.out.println("Hello" + data);

		if (index == 0 ){
			if(data == null || data.equals("")){
				record.setCurrpair(null);
				flag = -1;
			}
			else
			record.setCurrpair(data);
		}
		else if(index==1 )
		{
			if( data == null || data.equals("")){
				flag = -1;
				record.setSize(-1);
			}
			else	
			record.setSize(Integer.parseInt(data));
		}
		else if(index==2 )
		{
			if( data == null || data.equals("")){
				flag = -1;
				record.setPrice(-1);
			}
			else
			record.setPrice(Double.parseDouble(data));
		}
		else if (index == 3 )
		{
			
//			if(Timestamp.valueOf(data).toString() == "\n"){
//				flag = -1;
//				record.setTime(null);
//				System.out.println("hello");
//			}
				temp=data.replace('_',' ');
			record.setTime(Timestamp.valueOf(temp));
			//record.setDate_time(data);	
		}
		else {
		System.out.println("invalid data::" + data);
		//flag = -1;
			
		}
		}
		index++;
	}
	index = 0;
	System.out.printf("%d", flag);
	if(flag != -1) {
//		flag = 0;
//		System.out.println(flag + "");
		recordList.add(record);
	}
	else
		badRecordList.add(record);
	flag = 0;
}//end of while
scanner.close();

for( int c=0;c<recordList.size();c++)
{
	final String sql="insert into historicTrades(currpair,size,price,Time) values(?,?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	final int i=c;
	System.out.println(i+" ");
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		      
//			  	statement.setInt(1, recordList.get(i).getBuid());
//		       // else continue;
//		        statement.setInt(2, recordList.get(i).getSuid());
		        statement.setString(1, recordList.get(i).getCurrpair());
		        statement.setInt(2, recordList.get(i).getSize());
		        statement.setDouble(3, recordList.get(i).getPrice());
		        statement.setTimestamp(4,recordList.get(i).getTime());   
		     
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
}
for( int c=0;c<badRecordList.size();c++)
{
	final String sql="insert into auditlog(currpair,size,price,Time,filename) values(?,?,?,?,'data.csv')";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	final int i=c;
	System.out.println(i+" ");
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		      
//			  	statement.setInt(1, recordList.get(i).getBuid());
//		       // else continue;
//		        statement.setInt(2, recordList.get(i).getSuid());
		        statement.setString(1, badRecordList.get(i).getCurrpair());
		        statement.setInt(2,  badRecordList.get(i).getSize());
		        statement.setDouble(3,  badRecordList.get(i).getPrice());
		        statement.setTimestamp(4, badRecordList.get(i).getTime());   
		     
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
return "CSV to DB done";
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
@Transactional

public int createUserRepository(UserRepository m){
	
	final String sql="insert into users(username,password,desig) values(?,?,?)";
	//jdbcTemplate.update(sql,m.getUid(),m.getSize(),m.getType().toString(),m.getCurrpair(),m.getPrice(),LocalTime.now());
	GeneratedKeyHolder holder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			
			
			  PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        statement.setString(1, m.getUsername());
		        statement.setString(2, Security.getMD5(m.getPassword()));
		        statement.setString(3, m.getDesignation());
		      
		       
		        return statement;
		}
	}, holder);

	long primaryKey = holder.getKey().longValue();
	return (int) primaryKey;
	//final String sql2="select mid from marketTrades(uid,size,Type,currpair,price,Time) where uid=m.getUid() & size=m.getSize()";
	
	//return m.getMid();
}
@Transactional(readOnly=true)
public String auditCTR() {
	jdbcTemplate.query("select * from cancelledTrade", new Audit2RowMapper());
	return "AuditCTR ";
}

@Transactional(readOnly=true)
public String auditFTR() {
	
	List<CancelledTradeRepository> l1= jdbcTemplate.query("select id,uid,size,Type,price,Time,Limittime,currpair,tradetype from cancelledTrade",new Audit2RowMapper());
	List<FinishedTradeRepository> l2= jdbcTemplate.query("select fid,buid,suid,mid,lid,currpair,size,price,Time from finishedTrades",new AuditRowMapper());
	return "Audit enteries <br><br> "
			+ "Cancelled Trades:<br><br>"+l1.toString()+"\n"+"<br><br>Finished Trades:<br><br>"+l2.toString();
	
	
	
	
//	jdbcTemplate.query("select * from finishedTrades", new AuditRowMapper());
	// return "AuditFTR ";
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
@Transactional(readOnly=true)
public List<UserRepository> findAllUsers() {

return jdbcTemplate.query("select username,password,desig from users",new UserMapping());

}

public String openTrades(String username) {
	// TODO Auto-generated method stub
	
	 List<TradeRepository> l=jdbcTemplate.query("select * from trade where uid=select uid from users where username = ?", new Object[]{username},new UserRowMapper());
	 if (l.isEmpty()==false)
		 return l.toString();
	 else return "No open trades";
}

public String closedTrades(String s,Timestamp time1,Timestamp time2) {
	// TODO Auto-generated method stub
	
	List<CancelledTradeRepository> l1= jdbcTemplate.query("select id,uid,size,Type,price,Time,Limittime,currpair,tradetype from cancelledTrade where uid=select uid from users where username = ? AND Time BETWEEN ? AND ?", new Object[]{s,time1,time2},new Audit2RowMapper());
	List<FinishedTradeRepository> l2= jdbcTemplate.query("select fid,buid,suid,mid,lid,currpair,size,price,Time from finishedTrades where buid=select uid from users where username = ? AND Time BETWEEN ? AND ?", new Object[]{s,time1,time2}, new AuditRowMapper());
	return "Cancelled Trades:"+l1.toString()+"\n"+"Finished Trades:"+l2.toString();
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

class UserMapping implements RowMapper<UserRepository>
{
	@Override
	public UserRepository mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserRepository addedUser = new UserRepository();
		addedUser.setUsername(rs.getString("username"));
		addedUser.setPassword(rs.getString("password"));
		addedUser.setDesignation(rs.getString("desig"));   
		return addedUser;
	}
	
}


class  HistoryMapping implements RowMapper<HistoricTradesRepository>
{
	@Override
	public HistoricTradesRepository mapRow(ResultSet rs, int rowNum) throws SQLException {
		HistoricTradesRepository addedUser = new HistoricTradesRepository();
		//addedUser.setBuid(rs.getInt("buid")); 
		addedUser.setCurrpair(rs.getString("currpair")); 
		addedUser.setPrice(rs.getDouble("price")); 
		//addedUser.setSuid(rs.getInt("suid"));
		addedUser.setSize(rs.getInt("size"));
		
		
		 
//		addedUser.setPassword(rs.getString("password"));
//		addedUser.setDesignation(rs.getString("desig"));   
		
		/*



size int(255) NOT NULL,
price double(255) NOT NULL,
Time timestamp, */
		return addedUser;
	}
	
}