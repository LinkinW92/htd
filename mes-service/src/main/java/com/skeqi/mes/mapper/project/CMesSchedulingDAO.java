package com.skeqi.mes.mapper.project;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesAndonPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.pojo.project.Scheduling;

@MapperScan
@Component
public interface CMesSchedulingDAO {

	// 添加排班表
/*	@Insert("insert into c_mes_scheduling_t(DT,SHIFT_ID,TEAM_ID,LINE_ID,PLAN_NUMBER,ACTUAl_NUMBER)  "
			+ "values(#{dt},#{shiftId},#{teamId},#{lineId},#{planNumber},0)")*/
	public Integer addScheduling(CMesScheduling sche);

	@Select("SELECT  count(*) FROM `c_mes_scheduling_t` where DATE_FORMAT(dt,'%Y-%m-%d')=#{dt} and SHIFT_ID=#{id}")
	public Integer findSchedulingByDt(@Param("dt") String dt, @Param("id") Integer id);

	// 工单列表
//	@Select("<script>SELECT works.*,plan.PLAN_NAME as planName FROM `c_mes_workorder_t` works "
//			+ "left join r_mes_plan_t plan on works.PLAN_ID=plan.ID where  works.SCHE_ID=#{id} "
//			+ "<if test=\"planId!='' and planId!=null \"> and PLAN_ID = #{planId}</if></script>")
	public List<CMesWorkorderT> findAllWork(@Param("id") Integer id, @Param("planId") Integer planId);

	// 添加工单表
	@Insert("insert into c_mes_workorder_t(PLAN_ID,WORK_NAME,NUMBER,PRODUCT_MARK,SURPLUS_NUMBER,COMPLETE_NUMBER,SCHE_ID) "
			+ "values(#{planId},#{workName},#{number},#{productMark,jdbcType=VARCHAR},#{surplusNumber},#{completeNumber},#{scheId})")
	public Integer addWorkOrder(CMesWorkorderT work);

	// 修改工单的计划
	@Update("update c_mes_workorder_t set  NUMBER=#{planNumber} where ID=#{id}")
	public Integer updateWorkNumber(@Param("id") Integer id, @Param("planNumber") Integer planNumber);

	// 删除工单
	@Delete("delete from c_mes_workorder_t where ID=#{id}")
	public Integer delWork(Integer id);

	// 获取最大的工单id
	@Select("select max(id) from c_mes_workorder_t")
	public Integer findMaxWorkId();

	// 查询计划的剩余数量
	@Select("select PLAN_NUMBER-COMPLETE_NUMBER from r_mes_plan_t where ID=#{id}")
	public Integer findPlan(Integer id);

	//排版列表
	public Set<Map<String, Object>> findAllSchebuling(@Param("lineId") Integer lineId,@Param("dt") String dt);

	//班次列表
	@Select("select * from c_mes_shifts_team_t where ID=#{id}")
	public CMesShiftsTeamT findShift(Integer id);

	// 查询当天的生产总数
	@Select("SELECT sum(ACTUAl_NUMBER) FROM c_mes_scheduling_t where DATE_FORMAT(dt,'%Y-%m-%d')=#{dt}")
	public String findCount(@Param("dt") String dt);

	// 查询在一段时间内的生产总数//
	@Select("select count(*) from c_mes_andon_info_t where DATE_FORMAT(dt,'%Y-%m-%d %H:%i:%s') BETWEEN #{startTime} and #{endTime}")
	public String findCountS(@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 根据时间查询排班id
	@Select("SELECT max(sch.ID) FROM c_mes_scheduling_t sch,c_mes_shifts_team_t team "
			+ " WHERE sch.LINE_ID=#{lineId} and sch.SHIFT_ID = team.ID AND DATE_FORMAT( sch.DT, '%Y-%m-%d' )= #{startTime} "
			+ " and #{endTime} BETWEEN team.START_TIME and team.END_TIME ")
	public Integer findMaxId(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("lineId") Integer lineId);

	@Select("select team.START_TIME from c_mes_scheduling_t sch,c_mes_shifts_team_t team where sch.SHIFT_ID = team.ID and sch.ID=#{id}")
	public String findStartTime(Integer id);

	@Select("select team.END_TIME from c_mes_scheduling_t sch,c_mes_shifts_team_t team where sch.SHIFT_ID = team.ID and sch.ID=#{id}")
	public String findEndTime(Integer id);

	// 根据时间查询排班id(跨天)
	@Select("SELECT max(sch.ID) FROM c_mes_scheduling_t sch ,c_mes_shifts_team_t team  "
			+ " WHERE sch.LINE_ID=#{lineId} and sch.SHIFT_ID = team.ID and DATE_FORMAT( sch.DT, '%Y-%m-%d' )= #{startTime} and team.JUMP_TIME=1")
	public Integer findMaxIdDay(@Param("startTime") String startTime,@Param("lineId") Integer lineId);


	// 批量修改具体班次
	@Update("update c_mes_scheduling_t set TEAM_ID=#{teamId},LINE_ID=#{lineId},PLAN_NUMBER=#{planNumber} where ID=#{id}")
	public Integer updateScheduling(CMesScheduling sche);

	// 批量修改工单
	@Update("update c_mes_workorder_t set PLAN_ID=#{planId},WORK_NAME=#{workName},NUMBER=#{number} where ID=#{id}")
	public Integer updateWork(CMesWorkorderT work);

	// 根据具体班次查询工单id
	@Select("select WORK_ID from c_mes_scheduling_t where ID=#{id}")
	public Integer findWorkId(Integer id);

	// 查询实际生产数量是否为空
	@Select("select PLAN_NUMBER from c_mes_scheduling_t where ID=#{id}")
	public Integer findNumberCount(@Param("id") Integer id);

	@Update("update c_mes_scheduling_t set PLAN_NUMBER=#{number} where ID=#{id} ")
	public Integer updatePlanNumber(@Param("id") Integer id, @Param("number") Integer number);

	// 修改实际产量
	@Update("update c_mes_scheduling_t set PLAN_NUMBER=PLAN_NUMBER+1 where  ID=#{id}")
	public Integer updateRealNumber(Integer id);

	// 修改计划完成数量
	@Update("update r_mes_plan_t set COMPLETE_NUMBER=COMPLETE_NUMBER+1 where ID = (select work.PLAN_ID from c_mes_scheduling_t sch,c_mes_workorder_t work where sch.WORK_ID=work.ID and sch.ID =#{id})")
	public Integer updatePlan(@Param("id") Integer id);

	// 查询排版的计划数量
	@Select("select PLAN_NUMBER as planNumber,LINE_ID as lineId from c_mes_scheduling_t where ID=#{id}")
	public Map<String, Object> findSchePlanNumber(Integer id);

	// 查询此排班的工单计划数量
	@Select("select ifnull(sum(NUMBER),0) from c_mes_workorder_t where SCHE_ID=#{id}")
	public Integer findSumWorkNumber(Integer id);

	// 查询计划的产品标记和完成数量、产线id
	@Select("select PRODUCT_MARK as productMark,COMPLETE_NUMBER as complete,LINE_ID as lineId from r_mes_plan_t where ID=#{id}")
	public Map<String, Object> findProductMark(Integer id);

	// 查询此产品标记是否重复
	@Select("select count(*) from c_mes_workorder_t where SCHE_ID=#{id} and PRODUCT_MARK=#{mark}")
	public Integer findRepeatPromark(@Param("id") Integer id, @Param("mark") String mark);

	// 修改后的产线是否存在于工单中
	@Select("SELECT count(*) FROM `c_mes_workorder_t` work ,r_mes_plan_t plan where plan.ID=work.PLAN_ID and plan.LINE_ID!=#{id}")
	public Integer findWorkLine(Integer id);

	// 工单数量+1
	@Update("update c_mes_workorder_t set COMPLETE_NUMBER=IFNULL(COMPLETE_NUMBER,0)+#{number} where ID=#{id}")
	public Integer addWorkNumber(@Param("id") Integer id, @Param("number") Integer number);

	// 查询工单的计划和实际数量
	@Select("select NUMBER as number,COMPLETE_NUMBER as completeNumber from c_mes_workorder_t where ID=#{id}")
	public Map<String, Integer> findNumberByWorkId(Integer id);

	// 排班数量+1
	@Update("update c_mes_scheduling_t set ACTUAl_NUMBER=IFNULL(ACTUAl_NUMBER,0)+#{number} where ID=#{id}")
	public Integer addScheNumber(@Param("id") Integer id, @Param("number") Integer number);

	// 查询排班的计划和实际数量
	@Select("select PLAN_NUMBER as planNumber,ACTUAl_NUMBER as actualNumber from c_mes_scheduling_t where Id=#{id}")
	public Map<String, Integer> findNumberByScheById(Integer id);

	// 计划完成数量+1
	@Update("update r_mes_plan_t set COMPLETE_NUMBER=IFNULL(COMPLETE_NUMBER,0)+#{number} where ID=#{id}")
	public Integer addPlaNumber(@Param("id") Integer id, @Param("number") Integer number);

	// 根据计划id查询计数数量以及完成数量
	@Select("select PLAN_NUMBER as planNumber,COMPLETE_NUMBER as completeNumber from r_mes_plan_t where ID=#{id}")
	public Map<String, Integer> findNumberByPlanId(Integer id);

	// 查询排班下的所有工单完成数量
	@Select("select ifnull(sum(COMPLETE_NUMBER),0)  from c_mes_workorder_t where SCHE_ID=#{id}")
	public Integer findSumWorkActualNumber(Integer id);

	// 根据工单id查询完成数量
	@Select("select COMPLETE_NUMBER as number,SCHE_ID as id,PRODUCT_MARK as productMark,PLAN_ID as planId from c_mes_workorder_t where ID=#{id}")
	public Map<String, Object> findCompleteByWorkId(Integer id);

	// 排班完成数量减去删除的工单数量
	@Update("update c_mes_scheduling_t set ACTUAl_NUMBER=ACTUAl_NUMBER-#{number} where ID=#{id}")
	public Integer updateScheComplete(@Param("number") Integer number, @Param("id") Integer id);

	// 查询排版下面的所有工单数量
	@Select("select ifnull(sum(NUMBER),0) from c_mes_workorder_t where  id!=#{id}  and  SCHE_ID=(select SCHE_ID from c_mes_workorder_t where id=#{id})")
	public Integer findSumByWorkId(Integer id);

	// 根据工单id查询排班计划数量
	@Select("select PLAN_NUMBER from c_mes_scheduling_t where ID=(select  SCHE_ID from c_mes_workorder_t where id=#{id} )")
	public Integer findPlanNumberByWork(Integer id);

	// 根据排班id查询所有的计划name
	@Select("select plan.PLAN_NAME from c_mes_workorder_t works,r_mes_plan_t plan where plan.ID=works.PLAN_ID and  works.SCHE_ID=#{id}")
	public List<String> findPlanNameByscheId(Integer id);

	// 根据排班id删除工单
	@Delete("delete from  c_mes_workorder_t where SCHE_ID=#{id}")
	public Integer delWorkByScheId(@Param("id") Integer id);

	// 根据工单id删除工单
	@Delete("delete from  c_mes_workorder_t where id=#{id}")
	public Integer delWorkByPlanId(@Param("id") Integer id);

	// 根据排班id删除排班
	@Delete("delete from  c_mes_scheduling_t where ID=#{id}")
	public Integer delScheById(Integer id);


	// 修改工单的计划
	@Update("update c_mes_workorder_t set  id=#{i} where ID=#{id}")
	public void updateWorkL(@Param("id") Integer id, @Param("i") Integer i);

	// 根据班次名称查询班次ID
	@Select("select id from c_mes_shifts_team_t where name=#{shift} and LINE_ID=#{lineId} ")
	public Integer findByShiftId(@Param("shift") String shift, @Param("lineId") Integer lineId);

	// 根据班组名称查询班组ID
	@Select("select id from c_mes_team_t where name=#{team} ")
	public Integer findByTeamId(@Param("team") String team);

	// 根据产线名称查询产线Id
	@Select("select id from c_mes_line_t where name=#{line} ")
	public Integer findByLineId(@Param("line") String line);

	// 根据产线id，班次id，时间查询数据库是否有这条数据
	@Select("SELECT  count(*) from  c_mes_scheduling_t  where  1=1  and SHIFT_ID=#{shiftId}  and LINE_ID=#{lineId} and DT=#{dt}")
	public Integer findByScheduling(@Param("lineId") Integer lineId, @Param("shiftId") Integer shiftId,
			@Param("dt") String dt);

	// 根据计划名称查询计划id
	@Select(" select id  from  r_mes_plan_t where PLAN_NAME=#{planName}")
	public Integer findByPlanId(@Param("planName") String planName);

	// 根据产线id,班次id，时间，班组id修改排版表
	@Update("update c_mes_scheduling_t  set  TEAM_ID=#{teamId} ,PLAN_NUMBER=#{planNumber},ACTUAl_NUMBER=#{planrealNumber} where SHIFT_ID=#{shiftId} and DT=#{dt} and LINE_ID=#{lineId}")
	public Integer update(@Param("lineId") Integer lineId, @Param("shiftId") Integer shiftId, @Param("dt") String dt,
			@Param("teamId") Integer teamId, @Param("planNumber") Integer planNumber,
			@Param("planrealNumber") Integer planrealNumber);

	// 添加工单表
	@Insert(" insert into c_mes_workorder_t(PLAN_ID,NUMBER,SCHE_ID) VALUES(#{planId},#{planNumber},#{id})   ")
	public void addWork2(@Param("planId") Integer planId, @Param("planNumber") Integer planNumber,@Param("id") Integer id);

	// 根据产线id,班次id，时间，班组id查询当前排版表id
	@Select("SELECT  ID from  c_mes_scheduling_t  where  1=1  and SHIFT_ID=#{shiftId}  and LINE_ID=#{lineId} and DT=#{dt}")
	public Integer findByScheduling2(@Param("lineId") Integer lineId, @Param("shiftId") Integer shiftId,
			@Param("dt") String dt);

	// 根据排版id修改
	@Update("update c_mes_workorder_t set  NUMBER=#{planNumber} where SCHE_ID=#{id}")
	public Integer updateWork2(@Param("id") Integer id, @Param("planNumber") Integer planNumber);

	// 根据产线id,班次id，时间，班组id删除排版表
	@Delete("delete from   c_mes_scheduling_t   where  SHIFT_ID=#{shiftId}  and LINE_ID=#{lineId}  and DT=#{dt}")
	public Integer delScheL(@Param("lineId") Integer lineId, @Param("shiftId") Integer shiftId, @Param("dt") String dt);

	// 根据计划id和排版表id查询当前工单id
	@Select("select   id   from  c_mes_workorder_t   where PLAN_ID=#{pid}  and SCHE_ID =#{id}")
	public Integer findwork(@Param("id") Integer id, @Param("pid") Integer pid);

	// 跟据排版表id查询当前排版表id
	@Select("	select  id  from  c_mes_workorder_t   where SCHE_ID =#{id} ")
	public List<Integer> findByWorkId(@Param("id") int id);

	// 根据排版表id和计划id查询是否有这条数据
	public List<CMesWorkorderT> findAllWorkL(@Param("id") int id, @Param("planId") Integer planId);

	// 查询所有工单数据
	@Select("	select  *  from  c_mes_workorder_t   ")
	public List<CMesWorkorderT> findByWorkAll();

	// 根据计划id查询排版数据
	public Set<Map<String, Object>> findPidByScheduling(Integer id);

	// 根据工单ID删除计数表
	@Delete("DELETE from c_mes_andon_info_t where WORK_ID=#{id}")
	public Integer delAndon(@Param("id") Integer id);

	// 跟据排版id查询工单id
	@Select("SELECT id  from  c_mes_workorder_t where SCHE_ID=#{id}")
	public List<Integer> findBySchId(@Param("id") Integer id);

	// 根据计划ID查询工单id
	@Select("SELECT id  from  c_mes_workorder_t where PLAN_ID=#{pid}")
	public List<Integer> findByPid(@Param("pid") Integer pid);

	// 排版`列表
	public List<CMesSchedulingL> findAllSchebulingl(@Param("startTime")String startTime, @Param("endTime")String endTime);

	//添加工单表
	@Insert("	insert into c_mes_workorder_t(PLAN_ID,WORK_NAME,NUMBER,PRODUCT_MARK,SURPLUS_NUMBER,COMPLETE_NUMBER,SCHE_ID)"
			+ " VALUES(#{planId},#{workName},#{number},#{productMark},0,#{completeNumber},#{scheId}) ")
	public Integer addWork(CMesWorkorderT cMesWorkorderT);

	//根据计划id查询计划信息
	@Select("select  PRODUCT_MARK   AS   productionMark     from  r_mes_plan_t where id=#{pid}")
	public CMesAndonPlanT findByPlan(@Param("pid")Integer pid);

	//查询班次信息
	@Select("select * from c_mes_shifts_team_t where LINE_ID=#{lineId}  ORDER BY id  LIMIT 1")
	public CMesShiftsTeamT findByShifAll(@Param("lineId")Integer lineId);

	//查询班组信息
	@Select("select * from c_mes_team_t where 1=1   ORDER BY id  LIMIT 1")
	public CMesTeamT findByTeamAll();


	//根据产品编码查询产品标记（产品规则）
	@Select("select PRODUCTION_VR from c_mes_production_t where  PRODUCTION_TYPE=#{name}")
	public String findProVr(@Param("name")String name);

	//获取计划数据
	@Select("<script>" +
			"SELECT andon.*,line.NAME as lineName,pro.PRODUCTION_TYPE as productionName FROM "
			+ "r_mes_plan_t andon	LEFT JOIN c_mes_line_t line ON andon.LINE_ID = line.ID "
			+ " LEFT JOIN c_mes_production_t pro ON andon.PRODUCTION_ID = pro.ID  where  andon.COMPLETE_FLAG not in (3,4)  and andon.DT=#{time}  "
			+ "<if test=\"lineName!='' and lineName!=null \"> and line.NAME=#{lineName} </if>"
			+ " 	   </script>")
	public List<RMesPlanT> findSchePlan(@Param("time")String time,@Param("lineName") String lineName);

	//根据id查询工单表获取排班id
	@Select("SELECT SCHE_ID  FROM c_mes_workorder_t where PLAN_ID=#{id} ")
	public Integer findScheIdByWorkId(@Param("id")Integer id);


}
