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

//ʹ��@Controllerע����Spring������ע��UserInfoActionʵ��
//ʹ��@Scope("prototype")ָ��ԭ��ģʽ
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

	// ʹ��@Autowiredע��ע��UserInfoServiceImplʵ��
	@Autowired
	UserInfoService userInfoService;

	// ʹ��@Actionע����@Resultʵ��Action��Struts����
	@Action(value = "/doLogin", results = {
			@Result(name = "index", type="dispatcher", location = "/index.jsp"),
			@Result(name = "login", type="redirect", location = "/login.jsp") })
	public String doLogin() throws Exception {
		List<UserInfo> uiList = userInfoService.login(ui);
		if (uiList.size() > 0) {
			// ��¼�ɹ���ת������index.jsp
			return "index";
		} else {
			// ��¼ʧ�ܣ��ض���login.jsp
			return "login";
		}

	}

}
