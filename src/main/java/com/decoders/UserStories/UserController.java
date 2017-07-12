package com.decoders.UserStories;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

@RequestMapping(value="/canceltrade/{id}",method=RequestMethod.GET)
public String cancelOrder(@PathVariable("id") int t){
return sv.cancelOrder(t);
}

@RequestMapping(value="/csvtodb",method=RequestMethod.GET)
public String convertCsvtodb() throws IOException{
return sv.convertCsvtodb();
}
//
//@RequestMapping(value="/tradeaudit",method=RequestMethod.GET)
//public String auditOrder(){
//return sv.auditOrder();
//}

}