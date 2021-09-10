package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 费用化领用
 * @date 2021-08-20
 */
public interface WmsExpenseCollectionService {

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
	 * 删除R
	 * 
	 * @param json
	 * @return
	 */
	public void deleteR(Integer id) throws Exception;

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
	 * @param json
	 * @return
	 */
	public void deleteD(Integer id) throws Exception;
	
	/**
	 * 查询R跟D表数据
	 * 
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);
	
	/**
	 * 过账
	 * @param json
	 * @throws Exception
	 */
	public void guoZhang(JSONObject json) throws Exception;

}
