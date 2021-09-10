package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.project.AndonSteelPlatform;

/**
 * 钢平台数据
 */
public interface AndonSteelPlatformDao {

	/**R表
	 * 查询
	 * @param limit
	 * @return
	 */
	public List<AndonSteelPlatform> findRAndonSteelPlatform(@Param("limit")boolean limit);

	/**
	 * 查询P表
	 * @param json
	 * @return
	 */
	public List<AndonSteelPlatform> findPAndonSteelPlatform(JSONObject json);

	/**
	 * 新增R表
	 * @param dx
	 * @return
	 */
	public Integer addRAndonSteelPlatform(AndonSteelPlatform  dx);

	/**
	 * 新增P表
	 * @param dx
	 * @return
	 */
	public Integer addPAndonSteelPlatform(AndonSteelPlatform  dx);

	/**
	 * 删除R表
	 * @param id
	 * @return
	 */
	public Integer deletePAndonSteelPlatform(@Param("id")Integer id);

	/**
	 * 通过产品编号查询产品信息
	 * @param productNo
	 * @return
	 */
	public JSONObject findProductByProductNo(@Param("productNo")String productNo);

}
