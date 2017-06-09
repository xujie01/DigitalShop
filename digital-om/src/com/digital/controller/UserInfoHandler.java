package com.digital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.entity.ProductInfo;
import com.digital.entity.UserInfo;
import com.digital.service.UserInfoService;

@RequestMapping("/userinfo")
@Controller
public class UserInfoHandler {

	// 使用@Autowired注解注入UserInfoServiceImpl实例
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/login")
	public String login(UserInfo ui) {
		List<UserInfo> uiList=userInfoService.login(ui);
		if (uiList.size() > 0) {
			// 登录成功，转发到到index.jsp
			return "index";
		} else {
			// 登录失败，重定向到login.jsp
			return "redirect:/login.jsp";
		}
	}
	
	@ResponseBody
	@RequestMapping("/getValidUser")
	public List<UserInfo> getValidUser() {
		UserInfo ui=new UserInfo();
		ui.setId(0);
		ui.setUserName("请选择");
		//List<UserInfo> tempUiList=new ArrayList<UserInfo>();
		//tempUiList.add(ui);
		List<UserInfo> uiList = userInfoService.getValidUser();
		uiList.add(0, ui);
		return uiList;
	}
}
