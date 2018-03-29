package com.netease.onlineEducation.learning.Controller;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.netease.onlineEducation.learning.Model.Cart;
import com.netease.onlineEducation.learning.Model.Goods;
import com.netease.onlineEducation.learning.Model.User;
import com.netease.onlineEducation.learning.Service.CartService;
import com.netease.onlineEducation.learning.Service.GoodsService;

@Controller
@RequestMapping(value="goods")
public class GoodsController {
	@Resource
	private GoodsService goodsService;
	@Resource
	private CartService cartService;
	
	@RequestMapping(value="listAllGoods")
	public @ResponseBody List<Goods> listAllGoods(){
		return goodsService.getAllGoods();
	}
	
	@RequestMapping(value="addIntoCart")
	public @ResponseBody String addIntoCart(@RequestParam("goodsid") String goodsid, @RequestParam("goodsName") String goodsName,
			@RequestParam("amount") String Amount, @RequestParam("price") String Price, HttpServletRequest request) throws UnsupportedEncodingException{
		User user = (User)request.getSession().getAttribute("user");
		int userId = user.getId();
		int goodsId = Integer.parseInt(goodsid);
		double price = Double.parseDouble(Price);
		int amount = Integer.parseInt(Amount);
		Cart findCart = cartService.getCartByIdAndPrice(goodsId, price);
		if(findCart == null){
			Cart cart = new Cart(userId, goodsId, price, amount, goodsName);
			try{
				goodsService.insertCart(cart);
				return "ok";
			}catch(Exception e){
				e.printStackTrace();
				return "no";
			}
		}else{
			amount += findCart.getAmount();
			try{
				cartService.updateCart(goodsId, price, amount);
				return "ok";
			}catch(Exception e){
				e.printStackTrace();
				return "no";
			}
		}
		
	}
	
	@RequestMapping(value="publishGoods")
	public @ResponseBody boolean publishGoods(HttpServletRequest request){
		String name = request.getParameter("name");
		String abstracts = request.getParameter("abstracts");
		String image = request.getParameter("imageUrl");
		String info = request.getParameter("info");
		String Price = request.getParameter("price");
		double price = Double.parseDouble(Price);
		String goodsId = request.getParameter("id");
		if(goodsId == null){
			int ownerId = ((User)request.getSession().getAttribute("user")).getId();
			Goods goods = new Goods(name, abstracts, image, price, info, ownerId);
			try{
				goodsService.insertGoods(goods);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}else{
			int id = Integer.parseInt(goodsId);
			Goods goods = new Goods(id, name, abstracts, image, price, info);
			try{
				goodsService.updateById(goods);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
	}
	
	@RequestMapping(value="uploadImg")
	public @ResponseBody String uploadImg(@RequestParam("file") MultipartFile myfile, HttpServletRequest request){
		if(myfile.isEmpty()){
			return "false";
		}else{
			String originalFileName = myfile.getOriginalFilename();
			String baseFileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
			String type = originalFileName.substring(originalFileName.lastIndexOf("."));
			String finalName = baseFileName + "_" + System.currentTimeMillis() + type;
			try{
				String contextRoot = request.getSession().getServletContext().getRealPath("/");
				contextRoot = contextRoot.substring(0, contextRoot.lastIndexOf("webapps"));
				contextRoot = contextRoot + "images" + File.separator;
				File targetFile = new File(contextRoot, finalName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				myfile.transferTo(targetFile);
				String imgPath = "/file/" + finalName;
				return imgPath;
			}catch(Exception e){
				return "false";
			}
		}
	}
	
	@RequestMapping(value="deleteById")
	public @ResponseBody boolean deleteById(@RequestParam("id") int id){
		try{
			goodsService.deleteById(id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
