package com.netease.onlineEducation.learning.Controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netease.onlineEducation.learning.Model.Goods;
import com.netease.onlineEducation.learning.Service.GoodsService;

@Controller
public class IndexController {
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping(value="login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="show")
	public ModelMap show(@RequestParam("id") int id, @RequestParam("price") String price, ModelMap modelMap){
		modelMap.addAttribute("id", id);
		Goods goods = goodsService.getGoodsById(id);
		modelMap.addAttribute("goods", goods);
		modelMap.addAttribute("price", price);
		return modelMap;
	}
	
	@RequestMapping(value="cart")
	public String cart(){
		return "cart";
	}
	
	@RequestMapping(value="account")
	public String account(){
		return "account";
	}
	
	@RequestMapping("publish")
	public String publish(){
		return "publish";
	}
	
	@RequestMapping("publish/{goodsId}")
	public ModelAndView publish(@PathVariable("goodsId") int goodsId){
		ModelAndView modelAndView = new ModelAndView("publish");
		Goods goods = goodsService.getGoodsById(goodsId);
		modelAndView.getModelMap().addAttribute("goods", goods);
		return modelAndView;
	}
}
