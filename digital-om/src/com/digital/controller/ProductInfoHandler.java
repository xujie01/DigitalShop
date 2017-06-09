package com.digital.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.digital.entity.*;
import com.digital.service.*;

@RequestMapping("/pi")
@Controller
public class ProductInfoHandler {
	@Autowired
	ProductInfoService productInfoService;

	@ResponseBody
	@RequestMapping("/getOnSaleProduct")
	public List<ProductInfo> getOnSaleProduct() {
		List<ProductInfo> piList = productInfoService.getOnSaleProduct();
		return piList;
	}

	@ResponseBody
	@RequestMapping("/getPriceById")
	public String getPriceById(String pid) {
		if (pid != null && !"".equals(pid)) {
			ProductInfo pi = productInfoService.getProductInfoById(Integer.parseInt(pid));
			return pi.getPrice() + "";
		}else{
			return "";
		}
	}

}
