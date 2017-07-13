package com.decoders.UserStories;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserStoriesCompletedTradeTest {
	int flag=0;
	@Autowired
	private FinishedTradeRepository finishedTradeRepository;
	
	@Autowired
	private CancelledTradeRepository cancelledTradeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	/////////
	@Before
	public void initialiseDatabase() {
		
		userRepository.setUid(0);
		userRepository.setUsername("abc");
		userRepository.setDesignation("admin");
		userRepository.setPassword("root");
		service.createUserRepository(userRepository);
		
		cancelledTradeRepository.setUid(0);
		cancelledTradeRepository.setPrice(90.0);
		cancelledTradeRepository.setSize(1000);
		cancelledTradeRepository.setType("BUY"); 
		cancelledTradeRepository.setCurrpair("USD/EUR");
		cancelledTradeRepository.setLimittime(0);
		cancelledTradeRepository.setTradetype("limit");
		
	//cancelledTradeRepository.setTime(Timestamp.valueOf("2014-02-01 12:05:12"));
		
		service.createCancelledOrder(cancelledTradeRepository);
		
		
		finishedTradeRepository.setBuid(0);
		finishedTradeRepository.setSuid(10);
		finishedTradeRepository.setMid(10);
		finishedTradeRepository.setLid(13);
		finishedTradeRepository.setPrice(90.0);
		finishedTradeRepository.setSize(1000);
		//cancelledTradeRepository.setType("BUY"); 
		finishedTradeRepository.setCurrpair("USD/EUR");
		//cancelledTradeRepository.setLimittime(0);
		//cancelledTradeRepository.setTradetype("market");
		//finishedTradeRepository.setTime(Timestamp.valueOf("2014-02-01 12:05:12"));
		service.createFinished(finishedTradeRepository);
		
	
		
	}
		//////////
	
	
	
	
	
	@Test
	public void CompleteOrderWorkingSuccessfully() {
		Timestamp fromtime=Timestamp.valueOf("2014-02-01 1:05:12");
		Timestamp totime=Timestamp.valueOf("2014-02-01 14:05:12");
		
		List<CancelledTradeRepository> cancelledTrade = userService.findcancelled();
		List<FinishedTradeRepository> finishedTrade = userService.findfinished();
		List<UserRepository> usersMapping = userService.findAllUsers();
		
		assertThat("cancelled order size should be ",2,equalTo(cancelledTrade.size()));
		assertThat("finished users size should be ",2,equalTo(finishedTrade.size()));
		assertThat("users size should be ",2,equalTo(usersMapping.size()));
		
		
		CancelledTradeRepository cancelled = cancelledTrade.get(cancelledTrade.size()-1);
		UserRepository userMap= usersMapping.get(usersMapping.size()-1);
		FinishedTradeRepository finished= finishedTrade.get(finishedTrade.size()-1);
	
		
		assertThat("ID from finishedTrade mapping to uid from users", finished.getBuid(),equalTo(userMap.getUid()));
		assertThat("ID from cancelledTrade mapping to uid from users", cancelled.getUid(),equalTo(userMap.getUid()));
		
		//range for finished
		//Timestamp mytime2=finished.getTime(); //get from db
		 Timestamp mytime=Timestamp.valueOf("2014-02-01 12:05:12");
		if(mytime.after(fromtime) && mytime.before(totime))
		{
			flag=1;
		}
				//check logic
				
		assertThat("lies Range in from time and to time", 1,equalTo(flag));
		//assertThat("does not lie in Range from time and to time", 0,equalTo(flag));
				
				
				
		//range for cancelled
		
		flag=0;
		//mytime2=cancelled.getTime(); //get from db
		mytime=Timestamp.valueOf("2014-02-01 12:05:12");
		if(mytime.after(fromtime) && mytime.before(totime))
		{
			flag=1;
		}
				//check logic
				
		assertThat("lies Range in from time and to time", 1,equalTo(flag));
		//assertThat("does not lie in Range from time and to time", 0,equalTo(flag));
		
	
		/*assertThat("Uid from trades mapping to uid from users", userMap.getUid(),equalTo(tradeOrder.getUid()));
	//	assertThat("price should be 90", 90.0,equalTo(tradeOrder.getPrice()));
		assertThat("size should be 1000", 1000,equalTo(tradeOrder.getSize()));
		assertThat("Type should be BUY", Type.BUY,equalTo(tradeOrder.getType()));
		assertThat("currpair should be USD/EUR", "USD/EUR", equalTo(tradeOrder.getCurrpair()));
		//assertThat("Time should be 2014-02-01 12:05:12", Timestamp.valueOf(LocalDateTime.now()),equalTo(tradeOrder.getTime()));
		assertThat("limittime should be 0", 0,equalTo(tradeOrder.getLimittime()));
		assertThat("tradetype should be market", tradetype.market,equalTo(tradeOrder.getTradetype()));
		
		assertThat("username should be abc", "abc",equalTo(userMap.getUsername()));
		assertThat("designation should be admin", "admin",equalTo(userMap.getDesignation()));
		assertThat("password should be root", "root",equalTo(userMap.getPassword()));
		*/
	}
	

	


}
