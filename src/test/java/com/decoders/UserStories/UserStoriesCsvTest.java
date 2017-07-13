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
public class UserStoriesCsvTest {

	@Autowired
	private HistoricTradesRepository historicTradesRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService service;
	
	
	@Before
	public void initialiseDatabase() {
		
		//historicTradesRepository.setBuid(2);
		historicTradesRepository.setCurrpair("sad");
		historicTradesRepository.setPrice(20000.0);
		historicTradesRepository.setSize(123);
		//historicTradesRepository.setHid(23);
		//historicTradesRepository.setTime(Timestamp.valueOf("2014-02-01 12:05:12"));
//		service.createMarketOrder(historicTradeRepository);
		service.createHistoryTransaction(historicTradesRepository);
		
		
	}
	

	@Test
	public void OpenOrderServiceWorking() {
		List<HistoricTradesRepository> history = userService.checkHistory();
		//List<UserRepository> usersMapping = userService.findAllUsers();
		//assertThat("trade order size should be ",2,equalTo(tradeOrders.size()));
		assertThat("users size should be 1",1,equalTo(history.size()));
		HistoricTradesRepository historyMap = history.get(history.size()-1);
		//UserRepository userMap= usersMapping.get(usersMapping.size()-1);
	
		
		
		assertThat("Currency pair should be sad","sad",equalTo(historyMap.getCurrpair()));
		//assertThat("Buid should be 2",2,equalTo(historyMap.getBuid()));
			
		assertThat("Price should be 20000",20000.0,equalTo(historyMap.getPrice()));
		
		assertThat("Size should be 123",123,equalTo(historyMap.getSize()));
		
		//assertThat("Suid should be 23",23,equalTo(historyMap.getSuid()));
		
		
		
			
	}
	
		

	
	
}
