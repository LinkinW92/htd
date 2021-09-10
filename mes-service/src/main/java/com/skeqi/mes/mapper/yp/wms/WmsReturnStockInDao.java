package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 退料入库
 * @date 2021-07-26
 */
public interface WmsReturnStockInDao {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);
	
	/**
	 * 更新状态
	 * @param json
	 * @return
	 */
	public void updateState(@Param("listNo")String listNo,@Param("status")Integer status);

	/**
	 * 查询行
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

	/**
	 * 查询详情
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 查询需要过账的信息
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> queryPostingInformation(@Param("listNo")String listNo);

	/**
	 * 通过条码查询线边库库存
	 * @param materialCode
	 * @return
	 */
	public JSONObject findLslStockByMaterialCode(@Param("materialCode")String materialCode);

	/**
	 * 更新线边库库存
	 * @param json
	 * @return
	 */
	public int updateLslStock(JSONObject json);

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);

}
