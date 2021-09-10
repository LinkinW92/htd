package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface ApprovalRelatedService {

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
	public void add(JSONObject json);

	/**
	 * 查询模板
	 * @return
	 */
	public List<JSONObject> findTemplateAll();

}
