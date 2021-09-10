 package com.skeqi.mes.mapper.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.pojo.project.InsertInfo;

@Component
@MapperScan
@Service("CMesAndonDao")
public interface CMesAndonDAO {

	/**
	 * 创建故障
	* @author FQZ
	* @date 2020年4月20日上午8:38:10
	 */
	@Insert("insert into c_mes_andon_fault_t(LINE_NAME,STATION_NAME,ESTABLISH_DT,LOSS_TYPE,STATUS,fault_level_id) values(#{lineName},#{stationName},now(),#{faultType},0,1)")
	public Integer addFault(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("faultType")String faultType);

	//查询此故障是否存在
	@Select("select count(*) from c_mes_andon_fault_t where LINE_NAME=#{lineName} and STATION_NAME=#{stationName} and STATUS!=2")
	public Integer findFault(@Param("lineName")String lineName,@Param("stationName")String stationName);

	//查询此故障是否存在
		@Select("select count(*) from c_mes_andon_fault_t where LINE_NAME=#{lineName} and STATION_NAME=#{stationName} and STATUS=1")
		public Integer findResFault(@Param("lineName")String lineName,@Param("stationName")String stationName);

	/**
	 * 查询是否存在此故障类型
	* @author FQZ
	* @date 2020年4月21日上午11:29:56
	 */
	@Select("select min(id) from  c_mes_fault_type_t where TYPE_NAME=#{typeName}")
	public Integer findFaultType(@Param("typeName")String typeName);

	@Select("select id from  c_mes_loss_type_t where LOSS_NAME=#{typeName}")
	public Integer findLossType(@Param("typeName")String typeName);

	/**
	 * 响应故障
	* @author FQZ
	* @date 2020年4月20日上午9:01:14
	 */
	@Update("update c_mes_andon_fault_t set RESPONSE_DT=now(),STATUS=1 where LINE_NAME=#{lineName} and STATION_NAME=#{stationName} and STATUS=0")
	public Integer responseFault(@Param("lineName")String lineName,@Param("stationName")String stationName);

	/**
	 * 解决故障
	* @author FQZ
	* @date 2020年4月20日下午1:44:47
	 */
	@Update("update c_mes_andon_fault_t set SOLVE_DT=now(),STATUS=2 where LINE_NAME=#{lineName} and STATION_NAME=#{stationName} and STATUS=1")
	public Integer SolveFault(@Param("lineName")String lineName,@Param("stationName")String stationName);

	/**
	 * 查询故障列表
	* @author FQZ
	 * @param endDate
	 * @param startDate
	* @date 2020年4月20日下午1:57:42
	 */
	@Select("<script>" +
			"select tool.TOOL_NAME,andon.*,reason.NAME as reasonName,type.TYPE_NAME as faultName,loss.LOSS_NAME as lossName ,`level`.`level` as faultLevel, `level`.`explain` as faultLevelExplain "
			+ "FROM c_mes_andon_fault_t andon LEFT JOIN c_mes_loss_reason_t reason on 	andon.REASON = reason.NAME "
			+ "LEFT JOIN  c_mes_fault_type_t type on type.ID = andon.FAULT_TYPE   LEFT JOIN c_mes_loss_type_t loss on loss.id = andon.LOSS_TYPE "
			+ " LEFT JOIN c_mes_tool_manage_t tool on andon.TOOL_ID = tool.id join  c_alarm_problem_level `level` on andon.fault_level_id = `level`.id where 1=1"
			+"<if test=\"lineName!='' and lineName!=null \"> and andon.LINE_NAME=#{lineName} </if>"
	        + " <if test=\"startDate!='' and startDate!=null   and endDate!=null   and endDate!='' \" >  and   andon.ESTABLISH_DT    between  #{startDate} and  #{endDate}</if>"
			+ "<if test=\"stationName!='' and stationName!=null \"> and andon.STATION_NAME=#{stationName} </if> "
			+ "<if test=\"lossType!=0 and lossType!=null \"> and andon.LOSS_TYPE=#{lossType} </if> "
            +" and andon.status=#{status} "
	        + " ORDER BY andon.ESTABLISH_DT desc </script>")
	public List<CMesAndonFault> findAllAndon(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("status")Integer status,@Param("lossType")String lossType,@Param("startDate") String startDate, @Param("endDate")String endDate);

	/**
	 * 查询当前故障列表
	* @author FQZ
	* @date 2020年4月20日下午1:57:42
	 */
	@Select("<script>" +
			"select andon.*,reason.NAME as reasonName,type.TYPE_NAME as faultName,loss.LOSS_NAME as lossName,`level`.`level` AS faultLevel,`level`.`explain` AS faultLevelExplain  "
			+ "FROM c_mes_andon_fault_t andon LEFT JOIN c_mes_loss_reason_t reason on 	andon.REASON = reason.NAME   LEFT JOIN  c_mes_fault_type_t type on type.ID = andon.FAULT_TYPE   LEFT JOIN c_mes_loss_type_t loss on loss.id = andon.LOSS_TYPE JOIN c_alarm_problem_level `level` ON andon.fault_level_id  = `level`.id  where 1=1"
	        + "<if test=\"lineName!='' and lineName!=null \"> and andon.LINE_NAME=#{lineName} </if>"
	        + " <if test=\"startDate!='' and startDate!=null   and endDate!=null   and endDate!='' \" >  and   andon.ESTABLISH_DT    between  #{startDate} and  #{endDate}</if>"
			+ "<if test=\"stationName!='' and stationName!=null \"> and andon.STATION_NAME=#{stationName} </if> "
			+ "<if test=\"lossType!=0 and lossType!=null \"> and andon.LOSS_TYPE=#{lossType} </if> "
            +" and andon.status not in (2) and andon.TOOL_IDS is null order by ESTABLISH_DT desc   "
	        + "</script>")
	public List<CMesAndonFault> findNowAndon(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("lossType")String lossType,@Param("startDate") String startDate, @Param("endDate")String endDate);


	/**
	 * 故障信息更新
	* @author FQZ
	* @date 2020年4月20日下午2:25:56
	 */
	@Update("update c_mes_andon_fault_t set LOSS_TYPE=#{lossType},EMP=#{emp},NOTE=#{note},TOOL_ID=#{toolId},REASON=#{reason} where ID=#{id}")
	public Integer updateFault(CMesAndonFault fault);

	/**
	 * 根据损失类型名称查询是否存在此ID
	* @author FQZ
	* @date 2020年4月21日下午1:34:20
	 */
	@Select("select id from c_mes_loss_type_t where LOSS_NAME=#{lossName}")
	public Integer findLossName(@Param("lossName")String lossName);

	/**
	 * 生产计数
	* @author FQZ
	* @date 2020年4月20日下午4:04:35
	 */
	@Insert("insert into c_mes_andon_info_t(LINE_NAME,STATION_NAME,DT,SN,PRODUCT_MARK,COUNT_TYPE,WORK_ID,pattern) "
			+ "values(#{lineName},#{stationName},now(),#{sn,jdbcType=VARCHAR},#{productMark},#{countType},#{workId},#{pattern})")
	public Integer insertInfo(InsertInfo info);


	/**
	 * 查询此sn是否重复
	* @author FQZ
	* @date 2020年5月8日下午3:13:33
	 */
	@Select("select count(*) from c_mes_andon_info_t where sn=#{name} and LINE_NAME = #{lineName}")
	public Integer findInfoName(@Param("name")String name,@Param("lineName")String lineName);


	/**
	 * 获取当天的生产信息
	* @author FQZ
	* @date 2020年4月21日下午1:41:41
	 */
	@Select("<script>" +
			"select * from c_mes_andon_info_t where 1=1"+
			 "<if test=\"lineName!='' and lineName!=null \"> and LINE_NAME=#{lineName} </if>"
				+ "<if test=\"stationName!='' and stationName!=null \"> and STATION_NAME=#{stationName} </if>"
			    + "and dt BETWEEN CONCAT(CURDATE(),' 00:00:00') AND CONCAT(CURDATE(),' 23:59:59')"
		        + "</script>")
	public List<InsertInfo> findNowInfo(@Param("lineName")String lineName,@Param("stationName")String stationName);


	/**
	 * 获取历史的生产信息
	* @author FQZ
	* @date 2020年4月21日下午1:41:41
	 */
	@Select("<script>" +
			"select * from c_mes_andon_info_t where 1=1"+
			 "<if test=\"lineName!='' and lineName!=null \"> and LINE_NAME=#{lineName} </if>"
				+ "<if test=\"stationName!='' and stationName!=null \"> and STATION_NAME=#{stationName} </if>"
				+ "</script>")
	public List<InsertInfo> findInfo(@Param("lineName")String lineName,@Param("stationName")String stationName);

	/**
	 * 统计
	* @author FQZ
	* @date 2020年4月22日下午2:11:59
	 */
	@Select("<script>"
			+ "SELECT fault.LOSS_TYPE as lossType, type.LOSS_NAME as lossName,COUNT(*) as num "
			+ "FROM c_mes_loss_type_t type,c_mes_andon_fault_t fault "
			+ "WHERE fault.LOSS_TYPE = type.ID  "
			+ "<if test=\"starttime!='' and starttime!=null and endtime!='' and endtime!=null\"> and fault.ESTABLISH_DT BETWEEN #{starttime} AND #{endtime} </if>"
			+ " <if test=\"lineName!='' and lineName!=null \"> and fault.LINE_NAME=#{lineName} </if>"
			+ "<if test=\"stationName!='' and stationName!=null \"> and fault.STATION_NAME=#{stationName} </if>"
			+ "GROUP BY fault.LOSS_TYPE"
			+ "</script>")
	public List<Map<String,Object>> findPareto(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("starttime")String startDt,@Param("endtime")String endtime);

	@Select("<script>"
			+ "SELECT fault.LOSS_TYPE as lossType, type.LOSS_NAME as lossName,fault.REASON AS reason,COUNT(*) as num "
			+ "FROM c_mes_loss_type_t type,c_mes_andon_fault_t fault "
			+ "WHERE fault.LOSS_TYPE = type.ID  "
			+ " <if test=\"lineName!='' and lineName!=null \"> and fault.LINE_NAME=#{lineName} </if>"
//			+ " <if test=\"lossType!='0' and lossType!=null \"> and fault.LOSS_TYPE=#{lossType} </if>"
			+ "<if test=\"stationName!='' and stationName!=null \"> and fault.STATION_NAME=#{stationName} </if>"
			+ "<if test=\"starttime!='' and starttime!=null and endtime!='' and endtime!=null\"> and fault.ESTABLISH_DT BETWEEN #{starttime} AND #{endtime} </if>"
			+ "GROUP BY fault.LOSS_TYPE"
			+ "</script>")
	public List<Map<String,Object>> findParetor(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("starttime")String startDt,@Param("endtime")String endtime);




	@Select("<script>"
			+ "SELECT reason.NAME AS reason,fault.REASON as rid,fault.LOSS_TYPE as lossType,COUNT(*) as num "
			+ "FROM c_mes_andon_fault_t fault,c_mes_loss_reason_t reason "
			+ "WHERE fault.REASON = reason.NAME "
			+ " <if test=\"lineName!='' and lineName!=null \"> and fault.LINE_NAME=#{lineName} </if>"
			+ " <if test=\"lossType!='0' and lossType!=null \"> and fault.LOSS_TYPE=#{lossType} </if>"
			+ "<if test=\"stationName!='' and stationName!=null \"> and fault.STATION_NAME=#{stationName} </if>"
			+ "<if test=\"starttime!='' and starttime!=null and endtime!='' and endtime!=null\"> and fault.ESTABLISH_DT BETWEEN #{starttime} AND #{endtime} </if>"
			+ "GROUP BY fault.REASON"
			+ "</script>")
	public List<Map<String,Object>> findParetors(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("starttime")String startDt,@Param("endtime")String endtime,@Param("lossType")Integer lossType);


	@Select("select ESTABLISH_DT,SOLVE_DT from  c_mes_andon_fault_t where LOSS_TYPE=#{id} and SOLVE_DT is not null and ESTABLISH_DT is not null")
	public List<CMesAndonFault> findDt(Integer id);

	@Select("select ESTABLISH_DT,SOLVE_DT from  c_mes_andon_fault_t where REASON=#{reason} and SOLVE_DT is not null and ESTABLISH_DT is not null")
	public List<CMesAndonFault> findDts(String id);
	/**
	 *
	* @author FQZ
	* @date 2020年4月22日下午2:12:04
	 */
	@Select("select * from  c_mes_loss_type_t ")
	public List<CMesLossTypeT> findAllLoss();

	/**
	 * 员工列表
	* @author FQZ
	* @date 2020年4月22日下午2:13:08
	 */
	@Select("select id as id,EMP_NAME as eName from c_mes_emp_t")
	public List<Map<String,Object>> findEmp();

	@Delete("delete from c_mes_andon_fault_t where ID=#{id}")
	public Integer delAndon(@Param("id")Integer id);

	//查询工位
	@Select("<script>" +
			"select * from c_mes_station_t where 1=1"
			+ "<if test=\"lineId!=0 and lineId!=null \"> and LINE_ID=#{lineId} </if>"
			+ "</script>")
	public List<CMesStationT> findStation(@Param("lineId")Integer lineId);


	//查询故障时间
	@Select("select ESTABLISH_DT from c_mes_andon_fault_t where id=#{id}")
	public String findEDt(Integer id);

	//查询响应时间
	@Select("select RESPONSE_DT from c_mes_andon_fault_t where id=#{id}")
	public String findRDt(Integer id);


	//根据产线和工位查询解决时间
	@Select("select SOLVE_DT from c_mes_andon_fault_t where id=( SELECT  max(id) FROM `c_mes_andon_fault_t` where LINE_NAME=#{lineName}  and STATION_NAME=#{staName} and `STATUS`=2)")
	public String findSDt(@Param("lineName")String lineName,@Param("staName")String staName);


	//根据产品id查询属于哪条计划
	@Select("select min(id) from r_mes_plan_t plan where plan.PRODUCTION_ID=#{id} and plan.PLAN_NUMBER>plan.COMPLETE_NUMBER")
	public Integer findMinPlanId(Integer id);


	//修改计划的完成数量
	@Update("update r_mes_plan_t set COMPLETE_NUMBER=COMPLETE_NUMBER+1")
	public Integer updateCompleteNumber(Integer id);

	//根据产线查询故障数量
	@Select("<script>"
			+ "select count(*) from c_mes_andon_fault_t fault,c_mes_station_t station where station.STATION_NAME=fault.STATION_NAME and STATUS=#{status}"
			+ "<if test=\"lineName!='' and lineName!=null \"> and LINE_NAME=#{lineName} </if>"
			+ "</script>")
	public Integer findAndonInfo(@Param("lineName")String lineName,@Param("status")Integer status);


	//查询由中间表添加的故障
	@Select("SELECT  DISTINCT STATION_NAME FROM c_mes_andon_fault_t where  LINE_NAME = #{lineName} and TOOL_IDS is not null")
	public List<String> findStationName(@Param("lineName")String lineName);

	//根据工位查询由中间表插入数据中故障的状态
	@Select("select status from c_mes_andon_fault_t  where LINE_NAME=#{lineName} and STATION_NAME=#{staName} and TOOL_IDS is not null")
	public List<Integer> findDeviceStataus(@Param("lineName")String lineName,@Param("staName")String staName);


	//根据损失类型筛选原因
	@Select("select * from c_mes_loss_reason_t where LOSS_ID=#{id}")
	public List<CMesLossReasonT> findLossReason(Integer id);

	//查询产线是否存在
	@Select("select count(*) from c_mes_line_t where NAME=#{name}")
	public Integer findLineName(@Param("name")String name);

	//查询工位是否存在
	@Select("select count(*) from c_mes_station_t where STATION_NAME=#{name}")
	public Integer findStationNameByName(@Param("name")String name);


	//查询在当前时间内的班次
	@Select("select * from c_mes_shifts_team_t shifts join c_mes_scheduling_t scheduling on shifts.ID = scheduling.SHIFT_ID where scheduling.ID = #{schedulingId}")
	public CMesShiftsTeamT findNowNumber(@Param("schedulingId")Integer schedulingId);

	//当前时间加十分钟
	@Select("select DATE_FORMAT(ADDDATE(#{dt}, INTERVAL 10 MINUTE),'%Y-%m-%d %H:%i:%s') as dt from dual")
	public String findNowAddMinute(@Param("dt")String dt);

	//查询每十分钟内的产量
	@Select("SELECT count(*) FROM `c_mes_andon_info_t` where "
//			+ " pattern = 1 and"
			+ " LINE_NAME = #{lineName} and DT BETWEEN #{startTime} AND #{endTime}")
	public Integer findTenMinuteNumber(@Param("lineName")String lineName,@Param("startTime")String startTime,@Param("endTime")String endTime);

	//查询当前产线所有处于故障状态的自动站
	@Select("select id from c_mes_andon_fault_t where TOOL_IDS is not null and STATUS=0 and LINE_NAME=#{name}")
	public List<Integer> findStationResponse(@Param("name")String name);

	//修改自动站点的状态为响应
	@Update("update c_mes_andon_fault_t set STATUS=1,RESPONSE_DT=now() where ID=#{id}")
	public Integer updateStationResponse(Integer id);

	//根据产线名查询id
	@Select("SELECT id FROM `c_mes_line_t` where NAME=#{name}")
	public Integer findLineId(@Param("name")String name);

	//根据产线名查询id
	@Select("SELECT COUNT_TYPE FROM `c_mes_line_t` where NAME=#{name}")
	public Integer findLineType(String name);

	//根据工位名查询工位下标
	@Select("SELECT STATION_INDEX from c_mes_station_t where STATION_NAME=#{string} ")
	public Integer findByIndex(@Param("string")String string);

	//根据工单名称查询产品标记
	@Select("select pro.PRODUCTION_VR from c_mes_workorder_t woker,r_mes_plan_t plan,c_mes_production_t pro where woker.PLAN_ID=plan.ID and plan.PRODUCTION_ID=pro.ID and woker.ID=#{id}")
	public String findProVr(@Param("id")Integer id);


	//根据产线名称查询产品标记
	@Select("select REGION from c_mes_line_t where id=#{id}")
	public Integer findRegion(@Param("id")Integer id);

	@Select("select PRODUCTION_VR from c_mes_production_t where PRODUCTION_TYPE = SUBSTRING_INDEX(#{sn}, '|', 1 )")
	public String findProductSign(@Param("sn")String sn);

	@Select("select code_digit from c_mes_production_t where PRODUCTION_TYPE = #{productTyp}")
	public Integer findProductCodeDigit(@Param("productTyp")String productTyp);

	@Update("update r_mes_plan_t set COMPLETE_FLAG = 3 where date_sub(curdate(), interval 3 day) >= date(DT)")
	public void closePlan();

}
