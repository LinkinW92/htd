package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherStationDao;
import com.skeqi.mes.service.yp.wms.other.OtherStationService;

/**
 * 工位
 * @author yinp
 * @date 2021年8月19日
 */
@Service
public class OtherStationServiceImpl implements OtherStationService {

	@Autowired
	OtherStationDao dao;

	/**
	 * 通过产线查询工位
	 * @param lineId
	 * @return
	 */
	@Override
	public List<JSONObject> findStationByLineId(Integer lineId) {
		return dao.findStationByLineId(lineId);
	}

}
