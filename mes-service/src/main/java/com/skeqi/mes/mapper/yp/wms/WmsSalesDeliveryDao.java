package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 销售出库
 * @date 2021-08-23
 */
public interface WmsSalesDeliveryDao {
	
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
	public int add(JSONObject json);
	
	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);
	
	/**
	 * 查询R
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);
	
	/**
	 * 新增R
	 * @param json
	 * @return
	 */
	public int addR(JSONObject json);
	
	/**
	 * 更新R
	 * @param json
	 * @return
	 */
	public int updateR(JSONObject json);
	
	/**
	 * 更新R
	 * @param json
	 * @return
	 */
	public int deleteR(@Param("id")Integer id);
	
	/**
	 * 查询D
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);
	
	/**
	 * 新增D
	 * @param json
	 * @return
	 */
	public int addD(JSONObject json);
	
	/**
	 * 删除D
	 * @param json
	 * @return
	 */
	public int deleteD(@Param("id")Integer id);
	
	/**
	 * 通过行ID删除D
	 * @param rId
	 * @return
	 */
	public int deleteDByRId(@Param("rId")Integer rId);
	
	/**
	 * 查询销售订单
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findSalesOrderH(@Param("orderNumber")String orderNumber);
	
	/**
	 * 查询销售订单R
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findSalesOrderR(@Param("orderNumber")String orderNumber);
	
	/**
	 * 查询R跟D表
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);
	
	/**
	 * 查询单据已出库数量
	 * @param orderNumber
	 * @return
	 */
	public List<JSONObject> queryTheIssuedQuantityOfDocuments(@Param("orderNumber")String orderNumber);
	
	/**
	 * 查询过账数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> queryPostingData(@Param("listNo")String listNo);
	
}
