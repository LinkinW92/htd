package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 来料检验
 * @date 2021-07-16
 */
public interface WmsIncomingInspectionDao {

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
	 * 查询行
	 * @param json
	 * @return
	 */
	public List<JSONObject> listRow(JSONObject json);

	/**
	 * 查询详情
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 更新详情
	 * @param json
	 * @return
	 */
	public int updateD(JSONObject json);

	/**
	 * 查询检验完成的数据
	 * @param id
	 * @return
	 */
	public List<JSONObject> queryVerificationComplete(@Param("listNo")String listNo);

	/**
	 * 通过单据号查询表头
	 * @param listNo
	 * @return
	 */
	public JSONObject findHByListNo(@Param("listNo")String listNo);

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);

}
