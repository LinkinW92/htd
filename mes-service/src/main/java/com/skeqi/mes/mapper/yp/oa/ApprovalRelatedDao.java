package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ApprovalRelatedDao {

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
	 * 查询模板
	 * @return
	 */
	public List<JSONObject> findTemplateAll();

}
