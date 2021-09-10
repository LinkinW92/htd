package com.skeqi.mes.mapper.yp.equipment.spotcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检项
 *
 * @Date 2021年3月4日
 * @author yinp
 */
public interface EquipmentCheckItemsDao {

	/**
	 * 查询配置项集合
	 *
	 * @param editionId
	 * @return
	 */
	public List<JSONObject> list(@Param("editionId") int editionId);

	/**
	 * 新增点检项
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新点检项
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除点检项
	 *
	 * @param json
	 * @return
	 */
	public int delete(@Param("id") int id) throws Exception;

	/**
	 * 查询点检项名称是否存在重复
	 * @param name
	 * @param id
	 * @return
	 */
	public int findCount(@Param("name")String name,@Param("editionId")int editionId,@Param("id")Integer id);

}
