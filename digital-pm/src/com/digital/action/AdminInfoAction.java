package com.digital.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.digital.entity.AdminInfo;
import com.digital.service.AdminInfoService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class AdminInfoAction extends ActionSupport implements ServletResponseAware {
	AdminInfo ai;

	public AdminInfo getAi() {
		return ai;
	}

	public void setAi(AdminInfo ai) {
		this.ai = ai;
	}

	@Autowired
	AdminInfoService adminInfoService;

	HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}

	// 使用@Action注解与@Result实现Action的Struts配置
	@Action(value = "/doAdminLogin")
	public void doAdminLogin() throws Exception {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		List<AdminInfo> aiList = adminInfoService.login(ai);
		String str = "";
		if (aiList.size() > 0) {
			str = "{\"success\":\"true\",\"message\":\"登录成功！\"}";
		} else {
			str = "{\"success\":\"false\",\"message\":\"登录失败！\"}";
		}
		out.write(str);
	}

}
