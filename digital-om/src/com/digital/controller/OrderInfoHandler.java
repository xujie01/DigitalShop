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
	 * ��ȡ�����б�
	 */
	@RequestMapping("/getAllOrderInfo")
	@ResponseBody
	public Map<String, Object> getAllOrderInfo(OrderInfo oi, String oid,
			int page, int rows) throws JsonParseException,
			JsonMappingException, IOException {
		// ����Map���Ͷ���result,������ǰ��ҳ�淢������
		Map<String, Object> result = new HashMap<String, Object>(2);
		if (oid != null && !"".equals(oid)) {
			oi.setId(Integer.parseInt(oid));
		}
		// ���ݲ�ѯ������ȡ������¼����
		int totalCount = orderInfoService.getTotalCount(oi);
		// ���ݵ�ǰҳ�롢ÿҳ��ʾ��¼���Ͳ�ѯ������ȡָ��ҳ��ʾ�Ķ����б�
		List<OrderInfo> oiList = orderInfoService.getAllOrderInfoByPage(page,
				rows, oi);
		// �����result�з����ֵ�ԣ���Ϊ��total��,ֵΪtotalCount,
		result.put("total", totalCount);
		// �����result�з����ֵ�ԣ���Ϊ��rows��,ֵΪoiList,
		result.put("rows", oiList);
		// ͨ��@ResponseBodyע���Զ���Map<String, Object>����resultת��ΪJSON��ʽ,����ǰ��ҳ�淢��
		return result;
	}
	
	// ���ݶ���id�Ż�ȡҪ�޸ĵĶ�������, �ٷ��ض����޸�ҳ
	@RequestMapping("/getOrderInfo")
	public ModelAndView getOrderInfo(String oid) {
		String viewName = "modifyorder";
		ModelAndView mv = new ModelAndView(viewName);
		OrderInfo oi = orderInfoService.getOrderInfoById(Integer.parseInt(oid));
		// System.out.print(oi.getOds().iterator().next().getNum());
		mv.addObject("oi", oi);
		return mv;
	}

	// ���ݶ���id�Ż�ȡ������ϸ�б�
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
	 * �ύ����
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/commitOrder")
	public String commitOrder(String inserted, String orderinfo)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			// ����ObjectMapper����,ʵ��JavaBean��JSON��ת��
			ObjectMapper mapper = new ObjectMapper();
			// ��������ʱ������JSON�ַ����д��ڵ�Java����ʵ��û�е�����
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// ��json�ַ���orderinfoת����JavaBean���󣨶�������
			OrderInfo oi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
			oi.setUi(userInfoService.getUserInfoById(oi.getUid()));
			// ��json�ַ���ת����List<OrderDetail>���ϣ������ӱ�
			List<OrderDetail> odList = mapper.readValue(inserted,
					new TypeReference<ArrayList<OrderDetail>>() {
					});
			ProductInfo pi = null;
			double orderPrice = 0;
			// �������ӱ������������Ը�ֵ
			for (OrderDetail od : odList) {
				pi = productInfoService.getProductInfoById(od.getPid());
				orderPrice += pi.getPrice() * od.getNum();
				od.setOi(oi);
				od.setPi(pi);
				oi.getOds().add(od);
			}
			// �������ӱ���󼯺���ӵ��������������
			oi.setOrderprice(orderPrice);
			// ���涩������,�������涩���ӱ��¼
			if (orderInfoService.addOrder(oi) > 0)
				return "success";
			else
				return "failure";
		} catch (Exception e) {
			return "failure";
		}
	}

	/**
	 * �ύ�����޸�
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
			// ����ObjectMapper����,ʵ��JavaBean��JSON��ת��
			ObjectMapper mapper = new ObjectMapper();
			// ��������ʱ������JSON�ַ����д��ڵ�Java����ʵ��û�е�����
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// ��json�ַ���orderinfoת����JavaBean����
			OrderInfo tempoi = mapper.readValue(orderinfo, OrderInfo[].class)[0];
			// �����������
			OrderInfo oi = orderInfoService.getOrderInfoById(tempoi.getId());
			oi.setUi(userInfoService.getUserInfoById(tempoi.getUid()));
			oi.setStatus(tempoi.getStatus());
			oi.setOrdertime(tempoi.getOrdertime());
			oi.setOrderprice(tempoi.getOrderprice());
			// ɾ���Ķ�����ϸ
			if (deleted != null) {
				// ��json�ַ���deletedת����List<OrderDetail>���ϣ�ɾ���Ķ�����ϸ��
				deletedOdList = mapper.readValue(deleted,
						new TypeReference<ArrayList<OrderDetail>>() {
						});
				for (OrderDetail dod : deletedOdList) {
					Set<OrderDetail> odset = oi.getOds();
					Iterator<OrderDetail> itor = odset.iterator();
					// ����delods, ������ʱ����Ҫ�Ӷ�������oi���Ƴ��Ĺ����Ķ�����ϸ���󼯺�
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
			// �޸ĵĶ�����ϸ
			if (updated != null) {
				// ��json�ַ���updatedת����List<OrderDetail>���ϣ��޸ĵĶ�����ϸ��
				updatedOdList = mapper.readValue(updated,
						new TypeReference<ArrayList<OrderDetail>>() {
						});
				for (OrderDetail uod : updatedOdList) {
					Set<OrderDetail> odset = oi.getOds();
					Iterator<OrderDetail> itor = odset.iterator();
					// ����removeods, ������ʱ����Ҫ�Ӷ�������oi���Ƴ��Ĺ����Ķ�����ϸ���󼯺�
					List<OrderDetail> removeods = new ArrayList<OrderDetail>();
					// ����addods, ������ʱ����Ҫ��ӵ���������oi�еĹ����Ķ�����ϸ���󼯺�
					List<OrderDetail> addods = new ArrayList<OrderDetail>();
					while (itor.hasNext()) {
						OrderDetail odd = itor.next();
						if (uod.getId() == odd.getId()) {
							// ��Ҫ�Ƴ����޸�ǰ�Ķ�����ϸ������ӵ�removeods��
							removeods.add(odd);  
							uod.setPi(productInfoService.getProductInfoById(uod.getPid()));
							// ���޸ĺ�Ķ�����ϸ������ӵ�addods��
							addods.add(uod);
						}
					}
					// �Ӷ�������oi�����Ķ�����ϸ�������Ƴ�removeods�����еĶ���
					for (OrderDetail removeod : removeods) {
						oi.getOds().remove(removeod);
					}
					// �򶩵�����oi�����Ķ�����ϸ���������addods�����еĶ�����ϸ����
					for (OrderDetail addod : addods) {
						oi.getOds().add(addod);
					}
				}
			}
			// �����Ķ�����ϸ
			if (inserted != null){
				// ��json�ַ���insertedת����List<OrderDetail>���ϣ������Ķ�����ϸ��
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
					// �򶩵�����oi����������ϸ��������������Ķ�����ϸ����
					oi.getOds().add(iod);
				}
			}
			// ����ж϶�������oi�����Ķ�����ϸ�������Ƿ��м�¼�����û���򽫶�������ͬʱɾ��
			if(oi.getOds().size()==0){
				orderInfoService.deleteOrder(oi);
			}else{ 
				// ����,�޸Ķ���
				orderInfoService.modifyOrder(oi);
			}			
		} catch (Exception e) {
			return "failure";
		}
		return "success";

	}

	/**
	 * ɾ������
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
				// ѭ��ɾ��������¼
				OrderInfo oi = orderInfoService.getOrderInfoById(Integer
						.parseInt(id));
				orderInfoService.deleteOrder(oi);
			}
			str = "{\"success\":\"true\",\"message\":\"ɾ���ɹ���\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"ɾ��ʧ�ܣ�\"}";
		}
		return str;
	}

}
