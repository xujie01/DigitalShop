package com.digital.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

import com.digital.entity.CartItemBean;
import com.digital.entity.ProductInfo;
import com.digital.service.ProductInfoService;
import com.opensymphony.xwork2.ActionSupport;
//import com.sun.org.apache.bcel.internal.generic.NEW;

public class CartAction extends ActionSupport implements SessionAware{
	private Integer productInfoId;
	private int quantity;
	public Integer getProductInfoId() {
		return productInfoId;
	}
	public void setProductInfoId(Integer productInfoId) {
		this.productInfoId = productInfoId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	//调用商品信息的业务逻辑层
	ProductInfoService productInfoService;
	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}
	//将商品信息添加至购物车
	public String addtoshopcart(){
		//从Session中取出购物车，放入Map对象的cart中
		Map cart=(Map)session.get("cart");
		//获取当前要添加至购物车的商品
		ProductInfo productInfo=productInfoService.getProductInfoByPiId(productInfoId);
		//如果购物车不存在，则创建购物车（实例化HashMap类），并存入到session中
		if (cart==null) {
			cart=new HashMap();
			session.put("cart", cart);
		}
		//如果存在购物车，则判断要添加的商品是否已在购物车中
		CartItemBean cartItem=(CartItemBean)cart.get(productInfo.getId());
		if (cartItem!=null) {
			//如果商品在购物车中，只要更新其数量
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}else {
			//否则，创建一个商品条目至购物车中
			cart.put(productInfo.getId(), new CartItemBean(productInfo, 1));
		}
		//页面跳转到cart.jsp，x显示购物车
		return "shopCart";
	}
	
	public String updateSelectedQuantity(){
		//从session取出购物车，放入Map对象cart中
		Map cart=(Map)session.get("cart");
		CartItemBean cartItem=(CartItemBean)cart.get(productInfoId);
		cartItem.setQuantity(quantity);
		return "shopCart";
	}
	
	
	public String deleteSelectedOrders(){
		//从session取出购物车，放入Map对象cart中
		Map cart=(Map)session.get("cart");
		cart.remove(productInfoId);
		return "shopCart";
	}
	
	public String clearCart(){
		//从session取出购物车，放入Map对象cart中
		Map cart=(Map)session.get("cart");
		cart.clear();
		return "shopCart";
	}

	
	
	
}
