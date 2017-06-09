package com.digital.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.digital.dao.TypeDAO;
import com.digital.entity.ProductInfo;
import com.digital.entity.Type;
import com.digital.service.TypeService;

public class TypeServiceImpl implements TypeService {

	TypeDAO typeDAO;
	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}
	
	@Override
	public List<Type> getAllWithDistinctBrand() {
		List<Type> typeList=typeDAO.getAll();
		for (Type type : typeList) {
			List<String> brands=new ArrayList<String>();
			Object[] pis=type.getPis().toArray();
			for (int i = 0; i < pis.length; i++) {
				ProductInfo pi=(ProductInfo)pis[i];
				if(brands.contains(pi.getBrand())){
					type.getPis().remove(pi);					
				}else {
					brands.add(pi.getBrand());
				}
			}
		}		
		return typeList;
	}

}
