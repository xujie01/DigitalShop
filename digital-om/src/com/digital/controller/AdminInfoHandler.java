package com.digital.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digital.entity.ProductInfo;
import com.digital.entity.AdminInfo;
import com.digital.service.AdminInfoService;

@RequestMapping("/admininfo")
@Controller
public class AdminInfoHandler {

	// 使用@Autowired注解注入UserInfoServiceImpl实例
	@Autowired
	AdminInfoService adminInfoService;

	@RequestMapping("/login")
	public String login(AdminInfo ai) {
		List<AdminInfo> aiList=adminInfoService.login(ai);
		if (aiList.size() > 0) {
			// 登录成功，转发到到index.jsp
			return "redirect:/ordermanager.jsp";
		} else {
			// 登录失败，重定向到login.jsp
			return "redirect:/login.jsp";
		}
	}
}
