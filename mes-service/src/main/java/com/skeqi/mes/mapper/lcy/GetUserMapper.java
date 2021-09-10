package com.skeqi.mes.mapper.lcy;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCrudT;
import com.skeqi.mes.pojo.CMesUserT;

public interface GetUserMapper {


	//登陆
	CMesUserT getLoginValue(@Param("username")String username);

	String[] getUserPowerT();

	//获取权限列表
	String[] getMenuT();

	//获取增删改权限
	List<CMesCrudT> getCrudList(@Param("roleId")Integer roleId,@Param("menuId") Integer menuId);

	String[] getCrudName();

	//根据角色id和模板id查询按钮权限
	List<CMesCrudT> findCrudList(@Param("roleId")Integer roleId,@Param("menuId") Integer menuId);


}
