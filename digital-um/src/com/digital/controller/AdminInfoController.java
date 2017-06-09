package com.digital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.digital.pojo.*;
import com.digital.service.*;
import com.digital.util.JsonFactory;

@Controller
@RequestMapping("/admininfo")
@SessionAttributes(value = { "admin" })
public class AdminInfoController {
	@Autowired
	private AdminInfoService adminInfoService;

	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(AdminInfo ai, ModelMap model) {
		// ��¼��֤
		AdminInfo admininfo = adminInfoService.getAdminInfoByCond(ai);
		if (admininfo != null && admininfo.getName() != null) {
			// ��֤ͨ�������ж��Ƿ���Ϊ�ù���Ա���书��Ȩ��
			if (adminInfoService.getAdminInfoFunctions(admininfo.getId())
					.getFs().size() > 0) {
				// ��֤ͨ�����ѷ��书��Ȩ�ޣ���admininfo�������model��
				model.put("admin", admininfo);
				// ��JSON��ʽ��ҳ�淢�ͳɹ���Ϣ
				return "{\"success\":\"true\",\"message\":\"��¼�ɹ�\"}";
			} else {
				return "{\"success\":\"false\",\"message\":\"��û��Ȩ�ޣ�����ϵ��������Ա����Ȩ�ޣ�\"}";
			}
		} else
			return "{\"success\":\"false\",\"message\":\"��¼ʧ��\"}";
	}

	// ��ʾ����Ա�б�
	@RequestMapping("/adminlist")
	@ResponseBody
	public List<AdminInfo> adminlist() {
		List<AdminInfo> aiList = adminInfoService.getAllAdminInfo();
		return aiList;
	}

	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeNode> getTree(
			@RequestParam(value = "adminid") String adminid) {
		// ���ݹ���Աid�Ż�ȡAdminInfo���󼰹�����Functions���󼯺�
		AdminInfo admininfo = adminInfoService.getAdminInfoFunctions(Integer
				.parseInt(adminid));
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Functions> functionsList = new ArrayList<Functions>();
		// ��ȡAdminInfo���������Functions���󼯺�
		Set<Functions> functionsSet = admininfo.getFs();
		// ��Functions���󼯺ϵ����ʹ�Set<Functions>ת��ΪList<Functions>����
		for (Functions functions : functionsSet) {
			functionsList.add(functions);
		}
		// ��List<Functions>���͵�Functions���󼯺�����
		Collections.sort(functionsList);
		// ��������Functions���󼯺�ת����List<TreeNode>���͵��б�nodes��
		for (Functions f : functionsList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(f.getId());
			treeNode.setFid(f.getParentid());
			treeNode.setText(f.getName());
			nodes.add(treeNode);
		}
		// �����Զ��幤����JsonFactory��buildtree������Ϊnodes�б��и�TreeNodeԪ���е�children��ֵ(���ýڵ�������ӽڵ�)
		List<TreeNode> treeNodes = JsonFactory.buildtree(nodes, 0);
		// ��JSON��ʽ��ҳ�淵�ذ�tree���������
		return treeNodes;
	}

	// ��ȡָ��id�ŵĹ���Ա�ѷ���Ĺ���Ȩ��
	@RequestMapping("/getFidByAdminId")
	@ResponseBody
	public String getFidByAdminId(
			@RequestParam(value = "adminid") String adminid) {
		// ���ݹ���Աid�Ż�ȡ����Ա���󼰹�����Functinos���󼯺�
		AdminInfo admininfo = adminInfoService.getAdminInfoFunctions(Integer
				.parseInt(adminid));
		// �������sb,���ڱ���ù���Ա�ѷ���Ĺ���������Ҷ�ӽڵ�Ĺ���id��
		StringBuffer sb = new StringBuffer();
		// ��ȡ�����Ĺ��ܼ��ϣ����ѷ���Ĺ���Ȩ�ޣ�
		Set<Functions> fsSet = admininfo.getFs();
		// �����ù��ܼ��ϣ�������Ҷ�ӽڵ�Ĺ���id����ӵ�����sb�У����ԡ�,���ŷָ�
		for (Functions f : fsSet) {
			if (f.isIsleaf())
				sb.append(f.getId()).append(",");
		}
		if (sb.length() > 0)
			return sb.substring(0, sb.length() - 1).toString();
		else
			return "";
	}

	// ��������Ա
	@RequestMapping(value = "/addAdminInfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdminInfo(AdminInfo ai) {
		try {
			ai.setRole(2);
			adminInfoService.addAdminInfo(ai);
			return "{\"success\":\"true\",\"message\":\"�����ɹ�\"}";
		} catch (Exception e) {
			return "{\"success\":\"false\",\"message\":\"����ʧ��\"}";
		}
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	@ResponseBody
	public String loginout(SessionStatus status) {
		// @SessionAttributes���
		status.setComplete();
		return "{\"success\":\"true\",\"message\":\"ע���ɹ�\"}";
	}

}
