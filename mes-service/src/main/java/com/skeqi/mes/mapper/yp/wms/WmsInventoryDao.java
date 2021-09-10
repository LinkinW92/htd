package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 盘点
 * @date 2021-08-17
 */
public interface WmsInventoryDao {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 通过单据号查询
	 *
	 * @param json
	 * @return
	 */
	public JSONObject findByListNo(@Param("listNo")String listNo);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 查询R
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

	/**
	 * 新增R
	 *
	 * @param json
	 * @return
	 */
	public int addR(JSONObject json);

	/**
	 * 更新R
	 *
	 * @param json
	 * @return
	 */
	public int updateR(JSONObject json);

	/**
	 * 删除R
	 * @param id
	 * @return
	 */
	public int deleteR(@Param("id")Integer id);

	/**
	 * 查询D
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 新增D
	 *
	 * @param json
	 * @return
	 */
	public int addD(JSONObject json);

	/**
	 * 更新D
	 *
	 * @param json
	 * @return
	 */
	public int updateD(JSONObject json);

	/**
	 * 删除D
	 * @param id
	 * @return
	 */
	public int deleteD(@Param("id")Integer id);

	/**
	 * 通过行ID删除D
	 * @param id
	 * @return
	 */
	public int deleteDByRId(@Param("rId")Integer rId);

	/**
	 * 查询R跟D
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);

	/**
	 * 查询需要过账的数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findInventory(@Param("listNo")String listNo);

}
