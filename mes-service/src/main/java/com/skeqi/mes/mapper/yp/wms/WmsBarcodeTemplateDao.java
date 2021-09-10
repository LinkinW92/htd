package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 条码模板
 * @date 2021-07-14
 */
public interface WmsBarcodeTemplateDao {

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
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 通过名称查询数量
	 * @param name
	 * @param id
	 * @return
	 */
	public int findCountByName(@Param("name")String name,@Param("id")Integer id);

	/**
	 * 通过标题查询数量
	 * @param title
	 * @param id
	 * @return
	 */
	public int findCountByTitle(@Param("title")String title,@Param("id")Integer id);

	/**
	 * 查询详情
	 * @param json
	 * @return
	 */
	public List<JSONObject> listDetailed(JSONObject json);

	/**
	 * 新增详情
	 * @param json
	 * @return
	 */
	public int addDetailed(JSONObject json);

	/**
	 * 删除详情
	 * @param templateId
	 * @return
	 */
	public int deleteDetailed(@Param("templateId")Integer templateId);

}
