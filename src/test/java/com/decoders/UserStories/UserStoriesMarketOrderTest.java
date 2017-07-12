package com.decoders.UserStories;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStoriesMarketOrderTest {
	
	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService service;
	
	@Before
	public void initialiseDatabase() {
		
		
		tradeRepository.setUid(12);
	//	tradeRepository.setPrice(90.0);
		tradeRepository.setSize(1000);
		tradeRepository.setType(Type.BUY);
		tradeRepository.setCurrpair("USD/EUR");
		tradeRepository.setLimittime(0);
		tradeRepository.setTradetype(tradetype.market);
	//	tradeRepository.setTime(Timestamp.valueOf(LocalDateTime.now()));
		service.createMarketOrder(tradeRepository);
	}

	@Test
	public void insertSingleMarketOrderCompletesSuccessfully() {
		List<TradeRepository> tradeOrders = userService.findAllOrders();
		assertThat("limit order size should be ",2,equalTo(tradeOrders.size()));
		TradeRepository tradeOrder = tradeOrders.get(tradeOrders.size()-1);
		assertThat("UID should be 12", 12,equalTo(tradeOrder.getUid()));
	//	assertThat("price should be 90", 90.0,equalTo(tradeOrder.getPrice()));
		assertThat("size should be 1000", 1000,equalTo(tradeOrder.getSize()));
		assertThat("Type should be BUY", Type.BUY,equalTo(tradeOrder.getType()));
		assertThat("currpair should be USD/EUR", "USD/EUR", equalTo(tradeOrder.getCurrpair()));
		//assertThat("Time should be 2014-02-01 12:05:12", Timestamp.valueOf(LocalDateTime.now()),equalTo(tradeOrder.getTime()));
		assertThat("limittime should be 0", 0,equalTo(tradeOrder.getLimittime()));
		assertThat("tradetype should be market", tradetype.market,equalTo(tradeOrder.getTradetype()));
	}
	}
