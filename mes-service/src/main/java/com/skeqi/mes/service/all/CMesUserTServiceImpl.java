package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.user.req.UserReq;
import com.skeqi.mes.util.Rjson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.mapper.all.CMesUserTDAO;
import com.skeqi.mes.pojo.CMesUserT;

@Service
@Transactional
public class CMesUserTServiceImpl implements CMesUserTService {

	@Autowired
	private CMesUserTDAO dao;

	@Override
	public List<CMesUserT> findAllUser(CMesUserT user)  throws ServicesException{
		// TODO Auto-generated method stub
		return dao.findAllUser(user);
	}

	@Override
	public CMesUserT findByidUser(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findByidUser(id);
	}

	@Override
	public Integer addUser(CMesUserT user)  throws ServicesException{
		// TODO Auto-generated method stub
		if(user.getUserName()==null || user.getUserName()==""){
			throw new ParameterNullException("name不能为空",200);
		}else if(user.getUserPwd()==null || user.getUserPwd()==""){
			throw new ParameterNullException("密码不能为空",200);
		}else if(user.getRoleId()==null || user.getRoleId()==0){
			throw new ParameterNullException("角色id不能为空",200);
		}

		List<CMesUserT> findAllUser = dao.findUserByName(user);   //查询name是否重复
		if(findAllUser.size()>0){
			throw new NameRepeatException("名称重复",100);
		}

		user.setUserNumber(dao.findMaxNumber()+1);
		return dao.addUser(user);
	}

	@Override
	public Integer updateUser(CMesUserT user)  throws ServicesException{
		// TODO Auto-generated method stub
		if(user.getUserName()==null || user.getUserName()==""){
			throw new ParameterNullException("name不能为空",200);
		}else if(user.getUserPwd()==null || user.getUserPwd()==""){
			throw new ParameterNullException("密码不能为空",200);
		}else if(user.getRoleId()==null || user.getRoleId()==0){
			throw new ParameterNullException("角色id不能为空",200);
		}

		CMesUserT findByidUser = dao.findByidUser(user.getId());  //根据id查询原name
		if(!findByidUser.getUserName().equals(user.getUserName())){
			CMesUserT users  = new CMesUserT();
			users.setUserName(user.getUserName());
			List<CMesUserT> findAllUser = dao.findAllUser(users);  //查询新的name是否存在
			if(findAllUser.size()>0){
				throw new NameRepeatException("名称重复",100);
			}
		}

		return dao.updateUser(user);
	}

	@Override
	public Integer delUser(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delUser(id);
	}

	@Override
	public void updatePwd(String pwd, Integer id) {
		dao.updatePwd(pwd,id);

	}

	@Override
	public Rjson resetPwd(Map<String, Object> map) {
		CMesUserT user  = new CMesUserT();
		user.setUserName(map.get("userName").toString());
		List<CMesUserT> userList = dao.findUserByName(user);
		if(userList == null || userList.size() <= 0) {
			return Rjson.error("用户不存在！");
		}

		user = userList.get(0);
		if(!user.getUserPwd().equals(Encryption.getPassWord(map.get("userName").toString(), map.get("oldPassword").toString()))) {
			return Rjson.error("原密码不正确！");
		}

		String newEncryptionPwd = Encryption.getPassWord(map.get("userName").toString(), map.get("newPassword").toString());
		dao.updatePwd(newEncryptionPwd, user.getId());

		return Rjson.success();
	}

	//查询所有部门
	@Override
	public List<JSONObject> findDepartment() {
		return dao.findDepartment();
	}

	/**
	 * 查询直属主管
	 * @param userId
	 * @return
	 */
	@Override
	public JSONObject findReportsTo(Integer userId) {
		//通过用户id查询所在部门用户
		List<JSONObject> list = dao.QueryUserSDepartmentUserByuserId(userId);
		//查询用户直属主管
		JSONObject reportsTo = dao.findReportsTo(userId);

		JSONObject json = new JSONObject();
		json.put("list", list);
		json.put("reportsTo", reportsTo);

		return json;
	}

	/**
	 * 修改直属主管
	 * @param userId
	 * @param reportsToId
	 */
	@Override
	public void updateReportsTo(Integer userId, Integer reportsToId) {
		dao.updateReportsTo(userId, reportsToId);
	}


}
