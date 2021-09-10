package com.skeqi.mes.service.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产领用
 * @date 2021-08-9
 */
public interface WmsProductionRequisitionService {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 * @param json
	 * @return
	 */
	public void delete(String listNo) throws Exception;

	/**
	 * 查询R跟D表
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

	/**
	 * 新增D
	 * @param json
	 * @return
	 */
	public void addD(JSONObject json) throws Exception;

	/**
	 * 删除行表
	 * @param id
	 * @return
	 */
	public void deleteR(Integer id) throws Exception;

	/**
	 * 删除详情表
	 * @param id
	 * @return
	 */
	public void deleteD(Integer id) throws Exception;

	/**
	 * 过账
	 * @param json
	 * @throws Exception
	 */
	public void guozhang(JSONObject json) throws Exception;
}
