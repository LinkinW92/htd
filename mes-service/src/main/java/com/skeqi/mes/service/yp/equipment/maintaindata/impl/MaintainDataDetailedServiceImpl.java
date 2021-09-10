package com.skeqi.mes.service.yp.equipment.maintaindata.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.maintaindata.MaintainDataDetailedDao;
import com.skeqi.mes.service.yp.equipment.maintaindata.MaintainDataDetailedService;

/**
 * 保养记录详情
 *
 * @date2021年3月12日
 * @author yinp
 */
@Service
public class MaintainDataDetailedServiceImpl implements MaintainDataDetailedService {

	@Autowired
	MaintainDataDetailedDao dao;

	/**
	 * 查询保养记录详情
	 *
	 * @param checkDataId
	 * @return
	 */
	@Override
	public List<JSONObject> list(int checkDataId) {
		// TODO Auto-generated method stub
		return dao.list(checkDataId);
	}

	/**
	 * 更新保养记录详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

}
