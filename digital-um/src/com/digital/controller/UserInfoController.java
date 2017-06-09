package com.digital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.pojo.UserInfo;
import com.digital.service.UserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/login")
	public String login(UserInfo ui){
		UserInfo u=userInfoService.getUserInfoByCond(ui);
		if(u!=null && u.getUserName()!=null){
			return "index";
		}else{
			return "redirect:/login.jsp";
		}		
	}
	
	@RequestMapping("/userlist")
	@ResponseBody
	public Map<String, Object> userlist(int page, int rows, String userName ){
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 根据username参数模糊查询所有用户记录数
		int totalCount = userInfoService.getAllUser(userName).size();
		// 创建Map类型对象params,向SQL映射文件userInfoMapper.xml中的getUserListByPage方法传递参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 向Map中添加查询条件（用户名）
		params.put("userName", userName);
		// 向Map中添加查询条件（当前页显示记录的起始位置）
		params.put("start",(page - 1) * rows);
		// 向Map中添加查询条件（每页记录数）
		params.put("limit",rows);
		// 根据Map中的条件查询指定页显示的用户列表
		List<UserInfo> uiList=userInfoService.getUserListByPage(params);
		//向Map类型的对象result中放入键值对，键为“total”,值为totalCount,即根据username参数模糊查询得到的用户记录数
	    result.put("total", totalCount);
	    // 向对象result中放入键值对，键为“rows”,值为uiList,即当前页显示的用户列表
		result.put("rows", uiList);
		//通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}
	
	// 启用或禁用用户	
	@RequestMapping("/setIsEnableUser")
	@ResponseBody
	public String setIsEnableUser(@RequestParam(value = "uids") String uids , @RequestParam(value = "flag") String flag){
		String str = "";
		String[] tids=uids.substring(0, uids.length() - 1).split(",");
		List<Integer> ids= new ArrayList<Integer>();
		for (String id : tids) {
			ids.add(Integer.parseInt(id));
		}
		// 创建Map类型对象params,向SQL映射文件userInfoMapper.xml中的updateUserStatus方法传递参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		params.put("flag", flag);
		try {			
			userInfoService.updateUserStatus(params);
			str = "{\"success\":\"true\",\"message\":\"设置成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"设置失败！\"}";
		}
		return str;	
	}
}
