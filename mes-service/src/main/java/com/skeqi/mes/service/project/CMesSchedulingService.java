package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesAndonPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.pojo.project.Scheduling;

public interface CMesSchedulingService {

	//添加排班
	public Integer addSchebuling(Integer shiftId,Integer teamId,Integer number,String dt,Integer lineId) throws ServicesException;

	//显示排班列表
	public 	Set<Map<String, Object>>  findAllSchebuling(Integer lineId) throws ServicesException;

	//批量修改
	public Integer updateScheduling(String integer,Integer teamId,Integer number,Integer lineId) throws ServicesException;


	//添加工单
	public Integer addWork(Integer planId,Integer number,Integer scheId) throws ServicesException;


	//删除工单
	public Integer delWork(Integer id) throws ServicesException;

	//修改工单
	public Integer updateWork(Integer id,Integer planNumber) throws ServicesException;


	//工单列表
	public List<CMesWorkorderT> findAllWork(@Param("id")Integer id,@Param("planId")Integer planId) throws ServicesException;


	//删除排班
	public Integer delSche(Integer id) throws ServicesException;

	//批量修改2
	public Integer update(Integer lineId, Integer shiftId, String dt, Integer teamId, Integer planNumber, Integer planrealNumber);

	//根据id修改工单id
	public void updateWorkL(Integer id, Integer i);

	public Integer findByShiftId(String shift, Integer lineId);

	public Integer findByTeamId(String team);

	public Integer findByLineId(String line);

	public Integer findByScheduling(Integer lineId, Integer shiftId, String dt);

	public Integer findByPlanId(String planName);

	public Integer addWork2(Integer planId, Integer planNumber, Integer id, String workName, Integer planrealNumber, String productionMark) throws ServicesException;

	public int findByScheduling2(Integer lineId, Integer shiftId, String dt);

	public Integer updateWork2(Integer id, Integer planNumber);

	public Integer delScheL(Integer lineId, Integer shiftId, String dt);

	public Integer findPlan(Integer planId);

	public Integer  delScheById(int id);

	public Integer findwork(Integer id,Integer pid);

	public Integer delWorkByPlanId(Integer pid);

	public List<Integer> findByWorkId(int id);

	public List<CMesWorkorderT> findAllWorkL(int id, Integer planId);

	public List<CMesWorkorderT> findByWorkAll();

	public Set<Map<String, Object>> findPidByScheduling(Integer id);

	//查询排版
	public List<CMesSchedulingL> findAllSchebulingl(@Param("startTime")String startTime,@Param("endTime") String endTime);

	public CMesAndonPlanT findByPlan(Integer pid);

	public CMesShiftsTeamT findByShifAll(Integer lineId);

	public CMesTeamT findByTeamAll();

	public Integer addScheduling(CMesScheduling du);

	public Integer addWork3(CMesWorkorderT work);

	public List<RMesPlanT> findSchePlan(String time, String lineName);


}
