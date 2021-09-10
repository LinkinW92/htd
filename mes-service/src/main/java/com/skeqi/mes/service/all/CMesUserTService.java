package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.util.Rjson;

public interface CMesUserTService {

	public List<CMesUserT> findAllUser(CMesUserT user) throws ServicesException;

	public CMesUserT findByidUser(Integer id) throws ServicesException;

	public Integer addUser(CMesUserT user) throws ServicesException;

	public Integer updateUser(CMesUserT user) throws ServicesException;

	public Integer delUser(Integer id) throws ServicesException;

	public void updatePwd(String pwd, Integer id);

	public Rjson resetPwd(Map<String, Object> map);

	//查询所有部门
	public List<JSONObject> findDepartment();

	/**
	 * 查询直属主管
	 * @param userId
	 * @return
	 */
	public JSONObject findReportsTo(Integer userId);

	/**
	 * 修改直属主管
	 * @param userId
	 * @param reportsToId
	 */
	public void updateReportsTo(Integer userId,Integer reportsToId);
}
