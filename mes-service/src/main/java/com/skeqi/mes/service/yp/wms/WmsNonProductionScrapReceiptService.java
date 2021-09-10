package com.skeqi.mes.service.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 非生产报废入库
 * @date 2021-07-29
 */
public interface WmsNonProductionScrapReceiptService {

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
	 * @param listNo
	 */
	public void delete(String listNo) throws Exception;

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
	public void addR(JSONObject json) throws Exception;

	/**
	 * 更新R
	 *
	 * @param json
	 * @return
	 */
	public void updateR(JSONObject json) throws Exception;

	/**
	 * 删除R
	 *
	 * @param id
	 * @return
	 */
	public void deleteR(Integer id) throws Exception;

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
	public void addD(JSONObject json) throws Exception;

	/**
	 * 更新D
	 *
	 * @param json
	 * @return
	 */
	public void updateD(JSONObject json) throws Exception;

	/**
	 * 删除D
	 *
	 * @param id
	 * @return
	 */
	public void deleteD(@Param("id") Integer id) throws Exception;

	/**
	 * 过账
	 * @param json
	 * @throws Exception
	 */
	public void guoZhang(JSONObject json) throws Exception;

	/**
	 * 分组查询库存
	 * @param json
	 * @return
	 */
	public List<JSONObject> findStockGroupByMaterial(JSONObject json);

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

}
