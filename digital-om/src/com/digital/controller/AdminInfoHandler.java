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

	// ʹ��@Autowiredע��ע��UserInfoServiceImplʵ��
	@Autowired
	AdminInfoService adminInfoService;

	@RequestMapping("/login")
	public String login(AdminInfo ai) {
		List<AdminInfo> aiList=adminInfoService.login(ai);
		if (aiList.size() > 0) {
			// ��¼�ɹ���ת������index.jsp
			return "redirect:/ordermanager.jsp";
		} else {
			// ��¼ʧ�ܣ��ض���login.jsp
			return "redirect:/login.jsp";
		}
	}
}
