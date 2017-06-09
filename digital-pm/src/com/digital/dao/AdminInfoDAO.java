package com.digital.dao;

import java.util.List;

import com.digital.entity.*;

public interface AdminInfoDAO {
	public List<AdminInfo> search(AdminInfo cond);
}
