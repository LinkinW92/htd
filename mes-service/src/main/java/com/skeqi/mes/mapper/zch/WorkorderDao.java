package com.skeqi.mes.mapper.zch;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WorkorderDao {

	/**
	 * 获取工单列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findWorkorderList(Map<String, Object> map);

	/**
	 * 完成工单列表
	* @author FQZ
	* @date 2021-1-278:35:40
	 */
	List<Map<String, Object>> findPWorkorderList(Map<String, Object> map);

	/**
	 * 添加工单
	 * @param map
	 * @return
	 */
	Integer addWorkorder(Map<String, Object> map);

	/**
	 * 更新产线其他工单优先级
	 * @param map
	 * @return
	 */
	Integer updateLevelNoByLineId(Map<String, Object> map);

	/**
	 * 修改工单
	 * @param map
	 * @return
	 */
	Integer updateWorkorder(Map<String, Object> map);

	/**
	 * 获取产线最大优先级值
	 * @param map
	 * @return
	 */
	Integer getMaxLevelNoByLine(Map<String, Object> map);

	/**
	 * 获取原优先级和状态
	 * @param map
	 * @return
	 */
	Map<String, Object> getLevelNoStatusOldByID(Map<String, Object> map);

	/**
	 * 删除工单
	 * @param map
	 */
	Integer deleteWorkorder(Map<String, Object> map);

	/**
	 * 根据id获取工单
	 * @param map
	 * @return
	 */
	Map<String, Object> getByID(Map<String, Object> map);

	/**
	 * 根据工单内部id或者工单编号查工单
	 * @param map
	 * @return
	 */
    List<Map<String, Object>> findWorkorderByIdOrWID(Map<String, Object> map);

    /**
     * 获取编号规则
     * @param map
     * @return
     */
	Map<String, Object> getCodeRuleByID(Map<String, Object> map);

	/**
	 * 更新最后工单编号
	 */
	void updateLastCode(Map<String, Object> map);

	/**
	 * 根据工单编号获取数量
	 * @param map
	 * @return
	 */
	Integer getCountByWorkorderId(Map<String, Object> map);

	//转移工单
	void insertWork(Integer id);
	void deleteWork(Integer id);

	//转移条码表
	void insertPrint(Integer id);
	void deletePrint(Integer id);

	//查询工单条码
	List<Map<String,Object>> findWorkSn(@Param("id")Integer id);

    List<Map<String, Object>> findListAll();

	Integer insertBolt(@Param("id") Integer id);

	Integer insertKeypart(@Param("id") Integer id);

	Integer insertLeakage(@Param("id") Integer id);

	Integer deleteBolt(@Param("id") Integer id);

	Integer deleteKeypart(@Param("id") Integer id);

	Integer deleteLeakage(@Param("id") Integer id);

	List<Map<String, Object>> findRecipeDetailList(Map<String, Object> map);

}
