package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherLineEdgeLibraryDao;
import com.skeqi.mes.service.yp.wms.other.OtherLineEdgeLibraryService;

/**
 * 线边库
 * @author yinp
 * @date 2021年8月19日
 */
@Service
public class OtherLineEdgeLibraryServiceImpl implements OtherLineEdgeLibraryService {

	@Autowired
	OtherLineEdgeLibraryDao dao;

	/**
	 * 查询集合
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findList(JSONObject json) {
		return dao.findList(json);
	}

}
