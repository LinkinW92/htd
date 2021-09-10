package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产入库
 * @date 2021-07-20
 */
public interface WmsProductionWarehousingService {

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
	 * @throws Exception
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 查询R
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

	/**
	 * 查询D
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 过账
	 *
	 * @param listNo
	 * @param id
	 * @throws Exception
	 */
	public void guoZhang(String listNo, Integer id,Integer userId) throws Exception;

	/**
	 * 拒账
	 *
	 * @param listNo
	 * @param id
	 * @throws Exception
	 */
	public void juZhang(String listNo, Integer id,Integer userId) throws Exception;

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);
	
	/**
	 * 查询所有产线
	 * @return
	 */
	public List<JSONObject> findLineAll();

}
