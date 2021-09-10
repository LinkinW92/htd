package com.skeqi.mes.mapper.gmg;


import com.skeqi.mes.pojo.chenj.srm.req.CMesUserTReq;
import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;

import java.util.List;

public interface UserDao {


	/**
	 * 按条件获取指定用户信息
	 * **/
	public  Integer  GetUserInfoByCondition(@Param("userName")String username,@Param("userPwd")String userpwd);

	/**
	 * 获取所有的用户信息
	 * **/
	public CMesUserT GetAllUserInfo();

	public CMesUserT findByName(String userName);

	public String findByStatus(@Param("userName")String userName);

	public void updateStatus(@Param("loginStatus")String loginStatus, @Param("userName")String userName);

	List<CMesUserTReq> findUserList(CMesUserTReq req);

	int insertSelective(CMesUserTReq req);

	CMesUserT selectBySupplierCode(CMesUserTReq req);

	int updateByPrimaryKeySelective(CMesUserTReq req);

	int updateBatchSelective(List<CMesUserTReq> list);

	List<CMesUserTReq> findUpdateData();

}
