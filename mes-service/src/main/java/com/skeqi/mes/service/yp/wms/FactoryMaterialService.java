package com.skeqi.mes.service.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 工厂物料
 * @date 2021-07-06
 */
public interface FactoryMaterialService {

	/**
	 * 查询集合
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public void delete(Integer id) throws Exception;

	/**
	 * 查询所有物料
	 *
	 * @return
	 */
	public List<JSONObject> findMaterialAll();

	/**
	 * 查询所有工厂
	 *
	 * @return
	 */
	public List<JSONObject> findFactoryAll();

}
