package com.digital.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.digital.entity.ProductInfo;
import com.digital.entity.Type;
import com.digital.service.ProductInfoService;
import com.digital.service.TypeService;
import com.opensymphony.xwork2.ActionSupport;

public class ProductInfoAction extends ActionSupport implements RequestAware,SessionAware,ServletRequestAware,ServletResponseAware{
	
	ProductInfoService productInfoService;
	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}
	
	TypeService typeService;
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	
	Map<String, Object> request;
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;		
	}
	HttpServletResponse resp;
	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.resp=resp;
	}
	HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req=req;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	public String list() throws Exception{
		List<Type> typeList=typeService.getAllWithDistinctBrand();
		if (typeList.size()>0) {
			request.put("typeList", typeList);
		}
		
		List<ProductInfo> piList=productInfoService.getAllProductInfo();
		if (piList.size()>0) {
			request.put("piList", piList);
		}
		
		//添加浏览历史信息
		Cookie[] cookies=req.getCookies();
		Cookie cookie=null;
		String ids="";
		if (cookies!=null) {
			boolean flag=true;
			for (Cookie c: cookies) {
				if ("BrowsingSample".equals(c.getName())) {
					ids="("+ c.getValue().substring(0, c.getValue().toString().length()-1) +")";
					break;
				}
			}
		}
		if (!"".equals(ids)) {
			List<ProductInfo> browsePiList=productInfoService.getBrowsingProductInfo(ids);
			if (browsePiList.size()>0) {
				//为了使多个页面取得这样的浏览过的信息，我们用Session存储浏览过的商品列表
				session.put("browsePiList", browsePiList);
			}
		}
		
		return "index";
	}
	
	ProductInfo pi;	
	public ProductInfo getPi() {
		return pi;
	}
	public void setPi(ProductInfo pi) {
		this.pi = pi;
	}

	public String show() throws Exception{
		ProductInfo detialProductInfo=productInfoService.getProductInfoByPiId(pi.getId());
		request.put("detialProductInfo", detialProductInfo);		
		
		//处理cookies，用来保存浏览排行榜的信息，暂未处理，后面讲解
		Cookie[] cookies=req.getCookies();
		Cookie cookie=null;
		String ids="";
		if (cookies!=null) {
			boolean flag=true;
			for (Cookie c : cookies) {
				if ("BrowsingSample".equals(c.getName())) {
					flag=false;
					cookie=c;
					break;
				}
			}
			if (flag) {
				//如果Cookies中不存在BrowsingSample，则创建
				cookie=new Cookie("BrowsingSample","");
				cookie.setMaxAge(24*60*60);
			}
		}
		if ("".equals(cookie.getValue()) || String.valueOf(pi.getId()).indexOf(cookie.getValue())<0 ) {
			ids += cookie.getValue()+pi.getId()+",";
			cookie.setValue(ids);
		}
		resp.addCookie(cookie);		
		
		return "show";
	}
	
	
}
