package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 采购退货
 * @date 2021-08-24
 */
public interface WmsPurchaseReturnService {
	
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
	 * @param json
	 * @return
	 */
	public void delete(String listNo) throws Exception;
	
	/**
	 * 查询采购订单
	 * 
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findPurchaseOrderH(String orderNumber);

	/**
	 * 查询R跟D表
	 * 
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

	/**
	 * 新增D
	 * 
	 * @param json
	 */
	public void addD(JSONObject json) throws Exception;

	/**
	 * 删除D
	 * @param id
	 * @throws Exception
	 */
	public void deleteD(Integer id) throws Exception;

	/**
	 * 删除R
	 * @param id
	 * @throws Exception
	 */
	public void deleteR(Integer id) throws Exception;

	/**
	 * 过账
	 * @param json
	 * @throws Exception
	 */
	public void guoZhang(JSONObject json) throws Exception;

}
