package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单模板类型
 *
 * @author yinp
 * @date 2021年5月27日
 */
public interface FormTemplateTypeService {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list();

	/**
	 * 新增
	 *
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @return
	 */
	public void delete(int id, String name) throws Exception;

	public void asd(JSONObject json);

}
