package com.digital.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.digital.service.*;

@Controller
@RequestMapping("/powers")
public class PowersController {
	@Autowired
	private PowersService powersService;

	@RequestMapping("/savePowers")
	@ResponseBody
	public String savePowers(@RequestParam(value = "fids") String fids,
			@RequestParam(value = "editadminid") String editadminid) {
		try {
			// 从数据表powers中将待修改或设置的管理员功能权限全部删除
			powersService.delPowersByAdminid(Integer.parseInt(editadminid));
			if(!",".equals(fids)){
				if(fids.indexOf("1")<0)
					fids = fids + ",1";
				// 声明Map对象，向SQL映射文件powersMapper.xml中的addPowers方法传递参数
				Map<String, Object> params = null;
				String[] fidArray = fids.split(",");
				// 遍历fidArray数组,循环执行addPowers方法向数据表powers中添加功能权限
				for (String fid : fidArray) {
					params = new HashMap<String, Object>();
					params.put("aid", editadminid);
					params.put("fid", fid);
					powersService.addPowers(params);
				}
			}
		} catch (Exception e) {
			return "{\"success\":\"failure\",\"message\":\"保存失败\"}";
		}
		return "{\"success\":\"true\",\"message\":\"保存成功\"}";
	}

}
