package com.digital.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.digital.entity.ProductInfo;
import com.digital.entity.Type;
import com.digital.service.TypeService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TypeAction extends ActionSupport implements RequestAware,
		ServletRequestAware, ServletResponseAware, SessionAware {
	Map<String, Object> request;
	Map<String, Object> session;
	HttpServletRequest req;
	HttpServletResponse resp;
	
	String page;
	String rows;
	Type type;	

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Autowired
	TypeService typeService;
	
	@Action(value = "/getAllType")
	public void getAllType() throws Exception {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();		
		List<Type> typeList = typeService.getAll();
		// 将列表转为json格式
		String jsonTypeList = JSON.toJSONString(typeList);
		// 输出json字符串
		out.println(jsonTypeList);
	}
	
	/**
	 * 显示产品类型列表
	 * @throws Exception
	 */
	@Action(value = "/typeList")
	public void typeList() throws Exception {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int pageIndex = 1;
		// 获取从datagrid控件中传递来的页码
		if (page != null) {
			pageIndex = Integer.parseInt(page);
		}
		// 获取从datagrid控件中传递来的所选的每页显示记录
		int pageSize = Integer.parseInt(rows);
		// 获取商品总记录数
		int totalCount = typeService.getTotalCount();
		// 根据页码和页大小获取当前页商品列表
		List<Type> typeList = typeService.getByPage(pageIndex,pageSize);
		// 将商品列表转为json格式
		String jsonTypeList = JSON.toJSONString(typeList);
		// 输出json字符串
		out.println("{\"total\":" + totalCount + ",\"rows\":" + jsonTypeList + "}");
	}	
	
	/**
	 * 新增产品类型
	 */
	@Action(value = "/addType")
	public void addType() throws Exception {		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		int result = typeService.addType(type);
		String str = "";
		if (result > 0)
			str = "{\"success\":\"true\",\"message\":\"添加成功！\"}";
		else
			str = "{\"success\":\"false\",\"message\":\"添加失败！\"}";
		out.write(str);
	}
	
	/**
	 * 修改产品类型
	 */
	@Action(value = "/updateType")
	public void updateType() throws Exception {		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		Type editType=typeService.getById(type.getId());	
		editType.setName(type.getName());
		String str = "";
		try {
			typeService.updateType(editType);
			str = "{\"success\":\"true\",\"message\":\"修改成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"修改失败！\"}";
		}
		out.write(str);		
	}
	
}
