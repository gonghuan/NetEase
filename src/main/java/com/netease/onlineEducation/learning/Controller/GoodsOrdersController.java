package com.netease.onlineEducation.learning.Controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.onlineEducation.learning.Model.GoodsOrdersDto;
import com.netease.onlineEducation.learning.Model.User;
import com.netease.onlineEducation.learning.Service.GoodsOrdersService;

@Controller
@RequestMapping("orders")
public class GoodsOrdersController {
	@Resource
	private GoodsOrdersService goodsOrdersService;
	
	@RequestMapping("getAllOrdersByUserId")
	public @ResponseBody List<GoodsOrdersDto> getAllOrdersByUserId(HttpServletRequest request){
		int userId = ((User)request.getSession().getAttribute("user")).getId();
		return goodsOrdersService.getAllGoodsAndOrdersByUserId(userId);
	}
	
	@RequestMapping("getAllGoodsAndOrders")
	public @ResponseBody List<GoodsOrdersDto> getAllGoodsAndOrders(){
		return goodsOrdersService.getAllGoodsAndOrders();
	}
}
