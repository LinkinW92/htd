package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherWmsMaterialDao;
import com.skeqi.mes.service.yp.wms.other.OtherWmsMaterialService;

/**
 * @author yinp
 * @explain 物料
 * @date 2021-07-16
 */
@Service
public class OtherWmsMaterialServiceImpl implements OtherWmsMaterialService {

	@Autowired
	OtherWmsMaterialDao dao;

	/**
	 * 通过工厂id跟物料id查询工厂物料
	 * @param id
	 * @param factoryId
	 * @return
	 */
	@Override
	public JSONObject findFactoryMaterialByIdAndFactoryId(Integer id, Integer factoryId) {
		return dao.findFactoryMaterialByIdAndFactoryId(id, factoryId);
	}
	
	/**
	 * 通过物料id查询物料
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject findMaterialById(Integer id) {
		return dao.findMaterialById(id);
	}
	
	/**
	 * 通过物料编号查询物料
	 * @param materialNo
	 * @return
	 */
	@Override
	public JSONObject findMaterialByNo(String materialNo) {
		return dao.findMaterialByNo(materialNo);
	}

	/**
	 * 查询所有物料
	 * @return
	 */
	@Override
	public List<JSONObject> findMaterialAll() {
		return dao.findMaterialAll();
	}

}
