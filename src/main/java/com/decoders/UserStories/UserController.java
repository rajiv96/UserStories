package com.decoders.UserStories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
@Autowired
UserService sv;

@RequestMapping(value="/postmarkettrade",method=RequestMethod.POST)
public int createMarketOrder(@RequestBody TradeRepository m){
return sv.createMarketOrder(m);
}

@RequestMapping(value="/postlimittrade",method=RequestMethod.POST)
public int createLimitOrder(@RequestBody TradeRepository l){
	System.out.println(l.getLimittime());
return sv.createLimitOrder(l);
}

@RequestMapping(value="/canceltrade",method=RequestMethod.POST)
public String cancelOrder(@RequestBody CancelTrade trade){
return sv.cancelOrder(trade);
}

}