package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 销售退货入库单
 *
 * @author yinp
 * @date 2021年7月27日
 */
public interface WmsSalesReturnReceiptDao {

	/**
	 * 查询
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
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 更新状态
	 *
	 * @param json
	 * @return
	 */
	public int updateStatus(JSONObject json);

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
	 *
	 * @param id
	 * @return
	 */
	public int deleteR(@Param("id") Integer id);

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
	 *
	 * @param id
	 * @return
	 */
	public int deleteD(@Param("id") Integer id);

	/**
	 * 通过行id删除D
	 *
	 * @param id
	 * @return
	 */
	public int deleteDByRId(@Param("rId") Integer rId);

	/**
	 * 查询销售退货单
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> findSalesReturn(JSONObject json);

	/**
	 * 通过单件码或者包装id查询最进一次库存交易记录
	 *
	 * @param packageId
	 * @param unitCode
	 * @return
	 */
	public JSONObject queryInventoryTransactions(@Param("packageId") String packageId,
			@Param("unitCode") String unitCode);

	/**
	 * 通过订单号查询销售订单
	 *
	 * @param orderNumber
	 * @return
	 */
	public List<JSONObject> findSalesOrderByOrderNumber(@Param("orderNumber") String orderNumber);

	/**
	 * 查询过账数量
	 * @param salesReturnListNo
	 * @param materialId
	 * @return
	 */
	public int queryPostingQuantity(@Param("salesReturnListNo") String salesReturnListNo,
			@Param("materialNo") String materialNo);

	/**
	 * 查询需要过账的详细信息
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> queryTheDetailsToBePosted(@Param("listNo")String listNo);

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);
	
}
