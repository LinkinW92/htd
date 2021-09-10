package com.skeqi.mes.service.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 职位
 * @author yinp
 * @date 2021年5月20日
 *
 */
public interface PositionService {

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
	 * @return
	 */
	public void delete(int id) throws Exception;
}
