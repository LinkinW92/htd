package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.mapper.yp.wms.FactoryMaterialDao;
import com.skeqi.mes.service.yp.wms.FactoryMaterialService;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 工厂物料
 * @date 2021-07-06
 */
@Service
public class FactoryMaterialServiceImpl implements FactoryMaterialService {

	@Autowired
	FactoryMaterialDao dao;

	/**
	 * 查询集合
	 *
	 * @param json
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
		Integer materialId = json.getInteger("materialId");
		Integer factoryId = json.getInteger("factoryId");
		if (dao.findMaterialIdCount(factoryId, materialId) > 0) {
			throw new Exception("该物料已被此工厂创建");
		}

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新
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

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Integer id) throws Exception {
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 查询所有物料
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findMaterialAll() {
		return dao.findMaterialAll();
	}

	/**
	 * 查询所有工厂
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findFactoryAll() {
		return dao.findFactoryAll();
	}

}
