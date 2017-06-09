package com.digital.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.digital.entity.*;
import com.digital.service.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RequestMapping("/oi")
@Controller
public class OrderInfoHandler {

	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	ProductInfoService productInfoService;

	/**
	 * 获取订单列表
	 */
	@RequestMapping("/getAllOrderInfo")
	@ResponseBody
	public Map<String, Object> getAllOrderInfo(OrderInfo oi, String oid,
			int page, int rows) throws JsonParseException,
			JsonMappingException, IOException {
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		if (oid != null && !"".equals(oid)) {
			oi.setId(Integer.parseInt(oid));
		}
		// 根据查询条件获取订单记录总数
		int totalCount = orderInfoService.getTotalCount(oi);
		// 根据当前页码、每页显示记录数和查询条件获取指定页显示的订单列表
		List<OrderInfo> oiList = orderInfoService.getAllOrderInfoByPage(page,
				rows, oi);
		// 向对象result中放入键值对，键为“total”,值为totalCount,
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为oiList,
		result.put("rows", oiList);
		// 通过@ResponseBody注解自动将Map<String, Object>类型result转换为JSON格式,并向前端页面发送
		return result;
	}
	
	// 根据订单id号获取要修改的订单对象, 再返回订单修改页
	@RequestMapping("/getOrderInfo")
	public ModelAndView getOrderInfo(String oid) {
		String viewName = "modifyorder";
		ModelAndView mv = new ModelAndView(viewName);
		OrderInfo oi = orderInfoService.getOrderInfoById(Integer.parseInt(oid));
		// System.out.print(oi.getOds().iterator().next().getNum());
		mv.addObject("oi", oi);
		return mv;
	}

	// 根据订单id号获取订单明细列表
	@RequestMapping("/getOrderDetails")
	@ResponseBody
	public List<OrderDetail> getOrderDetails(String oid) {
		List<OrderDetail> ods = orderInfoService.getOrderDetailByOid(Integer
				.parseInt(oid));
		for (OrderDetail od : ods) {
			od.setPid(od.getPi().getId());
			od.setPrice(od.getPi().getPrice());
			od.setTotalprice(od.getPi().getPrice() * od.getNum());
		}
		return ods;
	}

	/**
	 * 提交订单
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/commitOrder")
	public String commitOrder(String inserted, String orderinfo)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			// 创建ObjectMapper对象,实现JavaBean和JSON的转换
			ObjectMapper mapper = new ObjectMapper();
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 将json字符串orderinfo转换成JavaBean对象（订单主表）
			OrderInfo oi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
			oi.setUi(userInfoService.getUserInfoById(oi.getUid()));
			// 将json字符串转换成List<OrderDetail>集合（订单子表）
			List<OrderDetail> odList = mapper.readValue(inserted,
					new TypeReference<ArrayList<OrderDetail>>() {
					});
			ProductInfo pi = null;
			double orderPrice = 0;
			// 给订单子表对象的其他属性赋值
			for (OrderDetail od : odList) {
				pi = productInfoService.getProductInfoById(od.getPid());
				orderPrice += pi.getPrice() * od.getNum();
				od.setOi(oi);
				od.setPi(pi);
				oi.getOds().add(od);
			}
			// 将订单子表对象集合添加到订单主表对象中
			oi.setOrderprice(orderPrice);
			// 保存订单主表,级联保存订单子表记录
			if (orderInfoService.addOrder(oi) > 0)
				return "success";
			else
				return "failure";
		} catch (Exception e) {
			return "failure";
		}
	}

	/**
	 * 提交订单修改
	 * 
	 */
	@RequestMapping(value = "/commitModifyOrder")
	@ResponseBody
	public String commitModifyOrder(String orderinfo, String inserted,
			String updated, String deleted) throws JsonParseException,
			JsonMappingException, IOException {
		try {
			List<OrderDetail> insertedOdList = null;
			List<OrderDetail> updatedOdList = null;
			List<OrderDetail> deletedOdList = null;
			// 创建ObjectMapper对象,实现JavaBean和JSON的转换
			ObjectMapper mapper = new ObjectMapper();
			// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 将json字符串orderinfo转换成JavaBean对象
			OrderInfo tempoi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
			// 订单主表对象
			OrderInfo oi = orderInfoService.getOrderInfoById(tempoi.getId());
			oi.setUi(userInfoService.getUserInfoById(tempoi.getUid()));
			oi.setStatus(tempoi.getStatus());
			oi.setOrdertime(tempoi.getOrdertime());
			oi.setOrderprice(tempoi.getOrderprice());
			// 删除的订单明细
			if (deleted != null) {
				// 将json字符串deleted转换成List<OrderDetail>集合（删除的订单明细）
				deletedOdList = mapper.readValue(deleted,
						new TypeReference<ArrayList<OrderDetail>>() {
						});
				for (OrderDetail dod : deletedOdList) {
					Set<OrderDetail> odset = oi.getOds();
					Iterator<OrderDetail> itor = odset.iterator();
					// 定义delods, 用于临时保存要从订单对象oi中移除的关联的订单明细对象集合
					List<OrderDetail> delods = new ArrayList<OrderDetail>();
					while (itor.hasNext()) {						
						OrderDetail odd = itor.next();
						if (dod.getId() == odd.getId()) {
							orderInfoService.deleteOrderDetail(odd);
							delods.add(odd);
						}
					}
					for (OrderDetail delod : delods) {
						oi.getOds().remove(delod);
					}
				}
			}
			// 修改的订单明细
			if (updated != null) {
				// 将json字符串updated转换成List<OrderDetail>集合（修改的订单明细）
				updatedOdList = mapper.readValue(updated,
						new TypeReference<ArrayList<OrderDetail>>() {
						});
				for (OrderDetail uod : updatedOdList) {
					Set<OrderDetail> odset = oi.getOds();
					Iterator<OrderDetail> itor = odset.iterator();
					// 定义removeods, 用于临时保存要从订单对象oi中移除的关联的订单明细对象集合
					List<OrderDetail> removeods = new ArrayList<OrderDetail>();
					// 定义addods, 用于临时保存要添加到订单对象oi中的关联的订单明细对象集合
					List<OrderDetail> addods = new ArrayList<OrderDetail>();
					while (itor.hasNext()) {
						OrderDetail odd = itor.next();
						if (uod.getId() == odd.getId()) {
							// 将要移除的修改前的订单明细对象添加到removeods中
							removeods.add(odd);  
							uod.setPi(productInfoService.getProductInfoById(uod.getPid()));
							// 将修改后的订单明细对象添加到addods中
							addods.add(uod);
						}
					}
					// 从订单对象oi关联的订单明细集合中移除removeods集合中的对象
					for (OrderDetail removeod : removeods) {
						oi.getOds().remove(removeod);
					}
					// 向订单对象oi关联的订单明细集合中添加addods集合中的订单明细对象
					for (OrderDetail addod : addods) {
						oi.getOds().add(addod);
					}
				}
			}
			// 新增的订单明细
			if (inserted != null){
				// 将json字符串inserted转换成List<OrderDetail>集合（新增的订单明细）
				insertedOdList = mapper.readValue(inserted,
						new TypeReference<ArrayList<OrderDetail>>() {
						});
				ProductInfo pi = null;
				double orderPrice = 0;
				for (OrderDetail iod : insertedOdList) {
					pi = productInfoService.getProductInfoById(iod.getPid());
					orderPrice += pi.getPrice() * iod.getNum();
					iod.setOi(oi);
					iod.setPi(pi);
					// 向订单对象oi关联订单明细集合中添加新增的订单明细对象
					oi.getOds().add(iod);
				}
			}
			// 最后判断订单对象oi关联的订单明细集合中是否有记录，如果没有则将订单主表同时删除
			if(oi.getOds().size()==0){
				orderInfoService.deleteOrder(oi);
			}else{ 
				// 否则,修改订单
				orderInfoService.modifyOrder(oi);
			}			
		} catch (Exception e) {
			return "failure";
		}
		return "success";

	}

	/**
	 * 删除订单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOrder")
	public String deleteOrder(String oids) {
		String str = "";
		try {
			oids = oids.substring(0, oids.length() - 1);
			String[] ids = oids.split(",");
			for (String id : ids) {
				// 循环删除订单记录
				OrderInfo oi = orderInfoService.getOrderInfoById(Integer
						.parseInt(id));
				orderInfoService.deleteOrder(oi);
			}
			str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
		}
		return str;
	}

}
