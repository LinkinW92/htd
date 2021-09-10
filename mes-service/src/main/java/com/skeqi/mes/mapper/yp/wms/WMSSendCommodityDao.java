package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 来料入库
 * @date 2021-07-14
 */
public interface WMSSendCommodityDao {

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
	 * 更新
	 * @param json
	 * @return
	 */
	public int updateTop(JSONObject json);

	/**
	 * 更新状态
	 * @param json
	 * @return
	 */
	public int updateStatus(JSONObject json);

	/**
	 * 查询行
	 * @param json
	 * @return
	 */
	public List<JSONObject> listRow(JSONObject json);

	/**
	 * 新增行
	 * @param json
	 * @return
	 */
	public int addRow(JSONObject json);

	/**
	 * 更新行
	 * @param json
	 * @return
	 */
	public int updateRow(JSONObject json);

	/**
	 * 删除行
	 * @param json
	 * @return
	 */
	public int deleteRow(@Param("id")Integer id);

	/**
	 * 查询详情
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 新增详情
	 * @param json
	 * @return
	 */
	public int addD(JSONObject json);

	/**
	 * 更新详情
	 * @param json
	 * @return
	 */
	public int updateD(JSONObject json);

	/**
	 * 删除详情
	 * @param json
	 * @return
	 */
	public int deleteD(@Param("id")Integer id);

	/**
	 * 通过行ID删除详情
	 * @param json
	 * @return
	 */
	public int deleteDByRId(@Param("rId")Integer rId);

	/**
	 * 查询需要过账的数据
	 * @param id
	 * @return
	 */
	public List<JSONObject> queryTheDataToBePosted(@Param("listNo")String listNo);

	/**
	 * 获取总实收数量
	 * @param purchaseOrderNo
	 * @param materialNo
	 * @return
	 */
	public int getTheTotalQuantityReceived(@Param("purchaseOrderNo")String purchaseOrderNo,@Param("materialNo")String materialNo);

	/**
	 * 获取订单应收数量
	 * @param purchaseOrderNo
	 * @param materialNo
	 * @return
	 */
	public int getOrderReceivableQuantity(@Param("purchaseOrderNo")String purchaseOrderNo,@Param("materialNo")String materialNo);

	/**
	 * 查询R表跟D表
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);
	
	/**
	 * 查询已收数
	 * @param purchaseOrderNo
	 * @return
	 */
	public List<JSONObject> queryReceivedData(@Param("purchaseOrderNo")String purchaseOrderNo);
	
	/**
	 * 通过id查询用户
	 * @param id
	 * @return
	 */
	public JSONObject findUserById(@Param("id")Integer id);

}
