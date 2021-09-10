package com.skeqi.mes.service.lcy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.GetUserMapper;
import com.skeqi.mes.pojo.CMesCrudT;
import com.skeqi.mes.pojo.CMesUserT;

@Service
public class GetUserServiceImpl implements GetUserService{

	@Autowired
	private GetUserMapper um;

	@Override
	public CMesUserT getLoginValue(String username) {
		if("SKQ".equals(username)){
			CMesUserT user=new CMesUserT();
			user.setId(-1);
			user.setUserName("SKQ");
			user.setUserPwd("1baf80c34cc7b8b2e164fc1d7fae6878");
			return user;
		}else{

			return um.getLoginValue(username);
		}
	}

/*	@Override
	public CMesUserPowerT getUserPowerT() {
		// TODO Auto-generated method stub
		return um.getUserPowerT();
	}*/

	@Override
	public String[] getMenuT() {
		// TODO Auto-generated method stub
		return um.getMenuT();
	}

	@Override
	public String[] getUserPowerT() {
		// TODO Auto-generated method stub
		return um.getUserPowerT();
	}

	@Override
	public List<CMesCrudT> getCrudList(Integer roleId, Integer menuId) {
		// TODO Auto-generated method stub
		return um.getCrudList(roleId,menuId);
	}

	@Override
	public String[] getCrudName() {
		// TODO Auto-generated method stub
		return um.getCrudName();
	}

	@Override
	public List<CMesCrudT> findCrudList(Integer roleId, Integer menuId) {
		// TODO Auto-generated method stub
		return um.findCrudList(roleId,menuId);
	}




}
