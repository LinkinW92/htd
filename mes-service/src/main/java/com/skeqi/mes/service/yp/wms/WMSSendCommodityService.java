package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 来料入库
 * @date 2021-07-14
 */
public interface WMSSendCommodityService {

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
	 * @param id
	 */
	public void delete(Integer id) throws Exception;
	
	/**
	 * 新增行
	 * 
	 * @param json
	 * @return
	 */
	public void addRow(JSONObject json) throws Exception;

	/**
	 * 删除行
	 *
	 * @param id
	 */
	public void deleteRow(Integer id) throws Exception;

	/**
	 * 新增详情
	 *
	 * @param json
	 * @return
	 */
	public void addD(JSONObject json) throws Exception;

	/**
	 * 更新详情
	 *
	 * @param json
	 * @return
	 */
	public void updateD(JSONObject json) throws Exception;

	/**
	 * 删除详情
	 *
	 * @param json
	 * @return
	 */
	public void deleteD(Integer id,Integer rowId) throws Exception;

	/**
	 * 过账
	 *
	 * @param listNo
	 * @param userId
	 */
	public void guoZhang(String listNo,Integer userId) throws Exception;

	/**
	 * 拒账
	 *
	 * @param listNo
	 * @param userId
	 */
	public void juZhang(String listNo,Integer userId) throws Exception;

	/**
	 * 查询R表跟D表
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

}
