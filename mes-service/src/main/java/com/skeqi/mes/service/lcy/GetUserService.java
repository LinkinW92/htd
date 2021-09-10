package com.skeqi.mes.service.lcy;


import java.util.List;

import com.skeqi.mes.pojo.CMesCrudT;
import com.skeqi.mes.pojo.CMesUserT;

public interface GetUserService {
	//登陆
	CMesUserT getLoginValue(String username);

	//获取权限级别
	String[] getUserPowerT();

	//获取权限列表
	String[] getMenuT();
	//获取增删改权限
	List<CMesCrudT> getCrudList(Integer roleId,Integer menuId);

	String[] getCrudName();

	List<CMesCrudT> findCrudList(Integer roleId,Integer menuId);


}
