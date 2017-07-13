package com.decoders.UserStories;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStoriesOpenTradeTest {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void initialiseDatabase() {
		
		userRepository.setUid(12);
		userRepository.setUsername("abc");
		userRepository.setDesignation("admin");
		userRepository.setPassword("root");
		
		
		
		tradeRepository.setUid(12);
		tradeRepository.setPrice(90.0);
		tradeRepository.setSize(1000);
		tradeRepository.setType(Type.BUY);
		tradeRepository.setCurrpair("USD/EUR");
		tradeRepository.setLimittime(0);
		tradeRepository.setTradetype(tradetype.market);
	//	tradeRepository.setTime(Timestamp.valueOf(LocalDateTime.now()));
		service.createMarketOrder(tradeRepository);
		service.createUserRepository(userRepository);
	}
	
	@Test
	public void OpenOrderServiceWorking() {
		List<TradeRepository> tradeOrders = userService.findAllOrders();
		List<UserRepository> usersMapping = userService.findAllUsers();
		assertThat("trade order size should be ",2,equalTo(tradeOrders.size()));
		assertThat("users size should be ",2,equalTo(usersMapping.size()));
		TradeRepository tradeOrder = tradeOrders.get(tradeOrders.size()-1);
		UserRepository userMap= usersMapping.get(usersMapping.size()-1);
	
		
		
		assertThat("Uid from trades mapping to uid from users", userMap.getUid(),equalTo(tradeOrder.getUid()));
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
		
	}
	

}
