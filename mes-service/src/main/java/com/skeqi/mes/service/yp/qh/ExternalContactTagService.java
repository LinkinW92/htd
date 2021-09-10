package com.skeqi.mes.service.yp.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 外部联系人标签
 *
 * @author yinp
 * @data 2021年6月8日
 */
public interface ExternalContactTagService {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list(Integer typeId);

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

	/**
	 * 查询标签类型
	 * @return
	 */
	public List<JSONObject> findType();

}
