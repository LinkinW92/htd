package com.skeqi.mes.mapper.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesPlanT;

public interface ProductionDao {
	/**
	 * 班次列表
	 */
	List<CMesShiftsTeamT> shiftsTeamList(Map<String, Object> map);
	/**
	 * 添加班次
	 */
	void addShiftsTeam(Map<String, Object> map);
	/**
	 * 添加班组
	 */
	void addEmpTeam(Map<String, Object> map);
	/**
	 * 修改班次
	 */
	void editShiftsTeam(Map<String, Object> map);
	/**
	 * 删除班次
	 */
	void delShift(Map<String, Object> map);
	/**
	 * 删除中间表
	 */
	void delPlanTeam(Map<String, Object> map);
	/**
	 * 计划列表
	 */
	List<RMesPlanT> planList();
	/**
	 * 查询最大的ID
	 */
	Integer maxIdByShift();
	/**
	 * 添加到班次计划中间表
	 */
	void addPlanTeam(Map<String, Object> map);
	/**
	 * 修改班次计划中间表
	 */
	void updatePlanTeamByShiftsTeamId(Map<String, Object> map);

	Integer countShiftByName(Map<String, Object> map);
	Integer countEmpTeamByName(Map<String, Object> map);
	/**
	 * 班组列表
	 */
	List<CMesEmpTeamT> empTeamList(Map<String, Object> map);
	/**
	 * 产线列表
	 */
	List<CMesLineT> lineList();
	/**
	 * 根据产线ID查询工位列表
	 */
	List<CMesStationT> stationList(@Param("lineId")Integer lineId);
	/**
	 * 删除班组
	 */
	void delEmpTeam(Map<String, Object> map);
	/**
	 * 修改班组
	 */
	void editEmpTeam(Map<String, Object> map);
	/**
	 * 添加到中间表
	 */
	void addShiftEmpsTeam(HashMap<String, Object> map);
	/**
	 * 获取班组最大ID
	 */
	Integer getMaxIdfromSET();
	/**
	 * 通过班组ID修改班次信息（中间表）
	 */
	void editShiftsTeamByEmpTeamId(Map<String, Object> map);
	/**
	 * 通过班组ID删除中间表
	 */
	void delShiftEmpTeam(Map<String, Object> map);
	/**
	 * 员工列表
	 */
	List<CMesEmpT> empList(Map<String, Object> map);
	/**
	 * 通过编号查询员工个数
	 */
	Integer countEmpByEmpNo(Map<String, Object> map);
	/**
	 * 添加员工
	 */
	void addEmp(Map<String, Object> map);
	/**
	 * 修改员工信息
	 */
	void editEmp(HashMap<String, Object> map);
	/**
	 * 删除员工
	 */
	void delEmp(HashMap<String, Object> map);
	/**
	 * 员工类型列表
	 * @param map
	 * @return
	 */
	List<CMesEmpTypeT> empTypeList(Map<String, Object> map);
	/**
	 * 添加员工类型
	 */
	void addEmpType(HashMap<String, Object> map);
	void editEmpType(Map<String, Object> map);
	void delEmpType(HashMap<String, Object> map);
	/**
	 * 根据ID查询员工信息
	 */
	CMesEmpT findById(@Param("id") Integer id);
}
