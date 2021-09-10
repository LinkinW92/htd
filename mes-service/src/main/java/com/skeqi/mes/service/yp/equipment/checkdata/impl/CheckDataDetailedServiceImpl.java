package com.skeqi.mes.service.yp.equipment.checkdata.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.checkdata.CheckDataDetailedDao;
import com.skeqi.mes.service.yp.equipment.checkdata.CheckDataDetailedService;

/**
 * 点检记录详情
 *
 * @date2021年3月11日
 * @author yinp
 */
@Service
public class CheckDataDetailedServiceImpl implements CheckDataDetailedService {

	@Autowired
	CheckDataDetailedDao dao;

	/**
	 * 查询点检记录详情
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
	 * 更新点检记录详情
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
