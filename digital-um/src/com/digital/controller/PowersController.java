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
			// �����ݱ�powers�н����޸Ļ����õĹ���Ա����Ȩ��ȫ��ɾ��
			powersService.delPowersByAdminid(Integer.parseInt(editadminid));
			if(!",".equals(fids)){
				if(fids.indexOf("1")<0)
					fids = fids + ",1";
				// ����Map������SQLӳ���ļ�powersMapper.xml�е�addPowers�������ݲ���
				Map<String, Object> params = null;
				String[] fidArray = fids.split(",");
				// ����fidArray����,ѭ��ִ��addPowers���������ݱ�powers����ӹ���Ȩ��
				for (String fid : fidArray) {
					params = new HashMap<String, Object>();
					params.put("aid", editadminid);
					params.put("fid", fid);
					powersService.addPowers(params);
				}
			}
		} catch (Exception e) {
			return "{\"success\":\"failure\",\"message\":\"����ʧ��\"}";
		}
		return "{\"success\":\"true\",\"message\":\"����ɹ�\"}";
	}

}
