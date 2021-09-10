package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 条码模板
 * @date 2021-07-14
 */
public interface WmsBarcodeTemplateService {

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
	 * 查询详情
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> listDetailed(JSONObject json);

	/**
	 * 更新详情
	 *
	 * @param json
	 * @return
	 */
	public void updateDetailed(JSONObject json) throws Exception;

}
