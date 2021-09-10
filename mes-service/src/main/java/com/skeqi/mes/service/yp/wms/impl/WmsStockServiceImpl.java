package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsStockDao;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 库存
 * @date 2021-07-12
 */
@Service
public class WmsStockServiceImpl implements WmsStockService {

	@Autowired
	WmsStockDao dao;

	/**
	 * 查询
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		if(dao.add(json)!=1) {
			throw new Exception("新增库存失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void delete(JSONObject json) throws Exception {
		if(dao.delete(json)!=1) {
			throw new Exception("删除库存失败");
		}
	}

	/**
	 * 更新
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if(dao.update(json)!=1) {
			throw new Exception("更新库存失败");
		}
	}

	/**
	 * 通过单件码查询库存
	 * @return
	 */
	@Override
	public JSONObject findStockByUnitCodeAndPackingId(String unitCode,String packingId) {
		return dao.findStockByUnitCodeAndPackingId(unitCode, packingId);
	}

	/**
	 * 通过工厂id跟库位id查询库存
	 * @param factoryId
	 * @param locationId
	 * @return
	 */
	@Override
	public List<JSONObject> findStockByFactoryIdAndLocationId(Integer factoryId, Integer locationId) {
		return dao.findStockByFactoryIdAndLocationId(factoryId, locationId);
	}

}
