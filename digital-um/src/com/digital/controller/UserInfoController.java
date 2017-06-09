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
		// ����Map���Ͷ���result,������ǰ��ҳ�淢������
		Map<String, Object> result = new HashMap<String, Object>(2);
		// ����username����ģ����ѯ�����û���¼��
		int totalCount = userInfoService.getAllUser(userName).size();
		// ����Map���Ͷ���params,��SQLӳ���ļ�userInfoMapper.xml�е�getUserListByPage�������ݲ���
		Map<String, Object> params = new HashMap<String, Object>();
		// ��Map����Ӳ�ѯ�������û�����
		params.put("userName", userName);
		// ��Map����Ӳ�ѯ��������ǰҳ��ʾ��¼����ʼλ�ã�
		params.put("start",(page - 1) * rows);
		// ��Map����Ӳ�ѯ������ÿҳ��¼����
		params.put("limit",rows);
		// ����Map�е�������ѯָ��ҳ��ʾ���û��б�
		List<UserInfo> uiList=userInfoService.getUserListByPage(params);
		//��Map���͵Ķ���result�з����ֵ�ԣ���Ϊ��total��,ֵΪtotalCount,������username����ģ����ѯ�õ����û���¼��
	    result.put("total", totalCount);
	    // �����result�з����ֵ�ԣ���Ϊ��rows��,ֵΪuiList,����ǰҳ��ʾ���û��б�
		result.put("rows", uiList);
		//ͨ��@ResponseBody,���͵�ǰ��ҳ���result�Զ�ת��JSON��ʽ
		return result;
	}
	
	// ���û�����û�	
	@RequestMapping("/setIsEnableUser")
	@ResponseBody
	public String setIsEnableUser(@RequestParam(value = "uids") String uids , @RequestParam(value = "flag") String flag){
		String str = "";
		String[] tids=uids.substring(0, uids.length() - 1).split(",");
		List<Integer> ids= new ArrayList<Integer>();
		for (String id : tids) {
			ids.add(Integer.parseInt(id));
		}
		// ����Map���Ͷ���params,��SQLӳ���ļ�userInfoMapper.xml�е�updateUserStatus�������ݲ���
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		params.put("flag", flag);
		try {			
			userInfoService.updateUserStatus(params);
			str = "{\"success\":\"true\",\"message\":\"���óɹ���\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"����ʧ�ܣ�\"}";
		}
		return str;	
	}
}
