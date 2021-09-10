package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 退料入库
 * @date 2021-07-26
 */
public interface WmsReturnStockInService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);
	
	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public void update(JSONObject json);
	
	/**
	 * 查询行
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

	/**
	 * 查询详情
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 过账
	 *
	 * @param json
	 * @throws Exception
	 */
	public void guoZhang(JSONObject json) throws Exception;

	/**
	 * 拒帐
	 *
	 * @param json
	 * @throws Exception
	 */
	public void juZhang(JSONObject json) throws Exception;

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

}
