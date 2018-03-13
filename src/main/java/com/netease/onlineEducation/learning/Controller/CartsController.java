package com.netease.onlineEducation.learning.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Model.Orders;
import com.netease.onlineEducation.learning.Model.User;
import com.netease.onlineEducation.learning.Service.CartService;
import com.netease.onlineEducation.learning.Service.OrdersService;

@Controller
@RequestMapping("cart")
public class CartsController {
	@Resource
	private CartService cartService;
	
	@Resource
	private OrdersService ordersService;
	
	@RequestMapping("getCart")
	public @ResponseBody List<Cart> getCartsByUserId(HttpServletRequest request){
		int userId = ((User)request.getSession().getAttribute("user")).getId();
		List<Cart> list = cartService.getCartByUserId(userId);
		JSONArray jsonArray = new JSONArray(list.size());
		for(Cart cart : list){
			int id = cart.getId();
			double price = cart.getPrice();
			int amount = cart.getAmount();
			String goodsName = cart.getGoodsname();
			int userid = cart.getUserid();
			int goodsId = cart.getGoodsid();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("price", price);
			jsonObject.put("amount", amount);
			jsonObject.put("goodsName", goodsName);
			jsonObject.put("userId", userid);
			jsonObject.put("goodsId", goodsId);
			jsonArray.add(jsonObject);
		}
		return list;
	}
	
	@RequestMapping("deleteCart/{id}")
	public @ResponseBody boolean delectCartsById(@PathVariable("id") int id){
		try{
			cartService.deleteCartById(id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	@RequestMapping("buyFromCart")
	@Transactional(noRollbackFor=Exception.class)
	public @ResponseBody boolean buyFromCart(@RequestParam("cartArray") String cartArray){
		JSONArray jsonArray = JSONArray.parseArray(cartArray);
		try{
			for(int i = 0; i < jsonArray.size(); i++){
				JSONObject json = jsonArray.getJSONObject(i);
				int id = json.getIntValue("id");
				int userId = json.getIntValue("userid");
				int goodsId = json.getIntValue("goodsid");
				int amount = json.getIntValue("amount");
				double price = json.getDoubleValue("price");
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String time = sdf.format(date);
				Orders orders = new Orders(id, userId, goodsId, price, amount, time);
				cartService.deleteCartById(id);
				ordersService.insertOrders(orders);
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
}
