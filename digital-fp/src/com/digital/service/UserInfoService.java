package com.digital.service;

import java.util.List;
import com.digital.entity.*;

public interface UserInfoService {
	public List<UserInfo> login(UserInfo cond);
}
