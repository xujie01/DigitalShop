package com.digital.dao;

import java.util.List;
import com.digital.entity.*;

public interface UserInfoDAO {
	public List<UserInfo> search(UserInfo cond);

}
