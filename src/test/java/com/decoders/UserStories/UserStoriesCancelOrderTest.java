package com.decoders.UserStories;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStoriesCancelOrderTest {

	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	private UserService service;
	
	private int CancelledOrderId;
	@Before
	public void initialiseDatabase() {
		
		
		tradeRepository.setUid(12);
		tradeRepository.setPrice(90.0);
		tradeRepository.setSize(1000);
		tradeRepository.setType(Type.BUY);
		tradeRepository.setCurrpair("USD/EUR");
		tradeRepository.setLimittime(5);
		tradeRepository.setTradetype(tradetype.limit);
	//	tradeRepository.setTime(Timestamp.valueOf(LocalDateTime.now()));
		service.createLimitOrder(tradeRepository);
		List<TradeRepository> tradeOrders = service.findAllOrders();
		CancelledOrderId = tradeOrders.size();
		service.cancelOrder(tradeOrders.size());
	}
	
	@Test
	public void CancelMarketOrderCompletesSuccessfully() {
		List<TradeRepository> tradeOrders = service.findAllOrders();
		assertThat("limit order size should be ",1,equalTo(tradeOrders.size()));
//		for (int index = 0 ; index < tradeOrders.size(); index++){
//			TradeRepository tradeOrder = tradeOrders.get(index);
//			assertThat("ID should be ", CancelledOrderId ,equalTo(tradeOrder.getId()));
//		}
	 TradeRepository tradeOrder = tradeOrders.get(tradeOrders.size() - 1);
		assertThat("ID should not be",CancelledOrderId,not(equalTo(tradeOrder.getId())));
	}
}
