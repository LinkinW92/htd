package com.skeqi.mes.mapper.yp.equipment.spotcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检配置
 *
 * @author yinp Date 2021年3月6日
 */
public interface EquipmentCheckConfigDao {

	/**
	 * 查询点检配置数据集合
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	public List<JSONObject> findLineAndEquipment();

	/**
	 * 通过设备id查询设备存在配置的数量
	 * @param equipmentId
	 * @return
	 */
	public int findConfigByEquipmentId(@Param("equipmentId")int equipmentId);

	/**
	 * 新增点检配置
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新点检配置
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除点检配置
	 *
	 * @param json
	 * @return
	 */
	public int delete(@Param("id") int id) throws Exception;

	/**
	 * 查询名称重复数量
	 *
	 * @param name
	 * @param id
	 * @return
	 */
	public int findNameCount(@Param("name") String name, @Param("id") Integer id);

	/**
	 * 查询编号重复数量
	 *
	 * @param code
	 * @param id
	 * @return
	 */
	public int findCodeCount(@Param("code") String name, @Param("id") Integer id);

	/**
	 * 查询版本号
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> findEdition(@Param("equipmentId")int equipmentId);

	/**
	 * 更新版本号
	 * @param edition
	 * @param equipmentId
	 * @return
	 */
	public int updateEdition(JSONObject json);

	/**
	 * 查询版本名数量
	 * @param edition
	 * @param equipmentId
	 * @return
	 */
	public int findEditionCount(@Param("edition")String edition,@Param("equipmentId")int equipmentId);

	/**
	 * 新增版本号
	 * @param json
	 * @return
	 */
	public int addEdition(JSONObject json);

	/**
	 * 新增点检项
	 * @param sql
	 * @return
	 */
	public int addItems(@Param("sql")String sql);
}
