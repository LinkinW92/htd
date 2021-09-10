package com.skeqi.mes.service.yp.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 职级
 *
 * @author yinp
 * @data 2021年7月13日
 */
public interface RankService {

	/**
	 * 查询
	 *
	 * @return
	 */
	public List<JSONObject> list();

	/**
	 * 新增
	 *
	 * @param json
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @param json
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void delete(@Param("id") Integer id);

}
