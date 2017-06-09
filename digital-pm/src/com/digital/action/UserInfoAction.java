package com.digital.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.digital.entity.UserInfo;
import com.digital.service.UserInfoService;
import com.opensymphony.xwork2.ActionSupport;

//使用@Controller注解在Spring容器中注册UserInfoAction实例
//使用@Scope("prototype")指定原型模式
@Controller
@Scope("prototype")
public class UserInfoAction extends ActionSupport {

	UserInfo ui;

	public UserInfo getUi() {
		return ui;
	}

	public void setUi(UserInfo ui) {
		this.ui = ui;
	}

	// 使用@Autowired注解注入UserInfoServiceImpl实例
	@Autowired
	UserInfoService userInfoService;

	// 使用@Action注解与@Result实现Action的Struts配置
	@Action(value = "/doLogin", results = {
			@Result(name = "index", type="dispatcher", location = "/index.jsp"),
			@Result(name = "login", type="redirect", location = "/login.jsp") })
	public String doLogin() throws Exception {
		List<UserInfo> uiList = userInfoService.login(ui);
		if (uiList.size() > 0) {
			// 登录成功，转发到到index.jsp
			return "index";
		} else {
			// 登录失败，重定向到login.jsp
			return "login";
		}

	}

}
