package com.skeqi.mes.service.gmg;

import com.skeqi.mes.pojo.chenj.srm.req.CMesUserTReq;
import com.skeqi.mes.util.Rjson;
import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;

public interface UserService {

	public Integer GetUserInfoByCondition(@Param("userName")String username,@Param("userPwd")String userpwd);


	public CMesUserT GetAllUserInfo();


	public CMesUserT findByName(String userName);


	public String findByStatus(String userName);


	public void updateStatus(String loginStatus, String userName);

	/**
	 * 采购员查询
	 * @param req
	 * @return
	 */
	Rjson findUserList(CMesUserTReq req);

}
