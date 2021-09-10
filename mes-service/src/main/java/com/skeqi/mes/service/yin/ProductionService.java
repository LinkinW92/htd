package com.skeqi.mes.service.yin;

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

public interface ProductionService {
	List<CMesShiftsTeamT> shiftsTeamList(Map<String, Object> map);
	void addShiftsTeam(Map<String, Object> map);
	void editShiftsTeam(Map<String, Object> map);
	void delShift(Map<String, Object> map);
	List<RMesPlanT> planList();
	List<CMesLineT> lineList();
	List<CMesStationT> stationList(Integer lineId);
	Integer maxIdByShift();
	void addPlanTeam(Map<String, Object> map);
	void updatePlanTeamByShiftsTeamId(Map<String, Object> map);
	Integer countShiftByName(Map<String, Object> map);
	Integer countEmpTeamByName(Map<String, Object> map);
	void delPlanTeam(Map<String, Object> map);
	List<CMesEmpTeamT> empTeamList(Map<String, Object> map);
	void addEmpTeam(Map<String, Object> map);
	void delEmpTeam(Map<String, Object> map);
	void delShiftEmpTeam(Map<String, Object> map);
	void editEmpTeam(Map<String, Object> map);
	void addShiftEmpsTeam(HashMap<String, Object> map);
	Integer getMaxIdfromSET();
	void editShiftsTeamByEmpTeamId(Map<String, Object> map);
	List<CMesEmpT> empList(Map<String, Object> map);
	Integer countEmpByEmpNo(Map<String, Object> map);
	void addEmp(Map<String, Object> map);
	void editEmp(HashMap<String, Object> map);
	void delEmp(HashMap<String, Object> map);
	List<CMesEmpTypeT> empTypeList(Map<String, Object> map);
	void addEmpType(HashMap<String, Object> map);
	void editEmpType(Map<String, Object> map);
	void delEmpType(HashMap<String, Object> map);
	/**
	 * 根据ID查询员工信息
	 */
	CMesEmpT findById(Integer id);
}
