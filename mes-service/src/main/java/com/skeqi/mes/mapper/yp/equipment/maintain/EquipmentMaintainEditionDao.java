package com.skeqi.mes.mapper.yp.equipment.maintain;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养版本
 *
 * @author yinp Date 2021年3月11日
 */
public interface EquipmentMaintainEditionDao {

	/**
	 * 查询版本信息集合
	 *
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> list(@Param("equipmentId") int equipmentId);

	/**
	 * 新增版本信息
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新版本信息
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除版本
	 *
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") int id);

	/**
	 * 通过版本表id删除点检项
	 *
	 * @param editionId
	 */
	public void deleteItem(@Param("editionId") int editionId);

	/**
	 * 判断版本号是否有重复项
	 *
	 * @param editionId
	 * @param edition
	 * @param id
	 * @return
	 */
	public int findCount(@Param("equipmentId") int equipmentId, @Param("edition") String edition,
			@Param("id") Integer id);

	/**
	 * 通过设备id停用该设备关联的所有版本
	 *
	 * @param equipmentId
	 */
	public void tingYong(@Param("equipmentId") int equipmentId);

	/**
	 * 启用某个版本
	 * @param id
	 */
	public int qiYong(@Param("id")int id);

	/**
	 * 查询闲置版本号
	 * @return
	 */
	public List<JSONObject> nullEditionList();

}
