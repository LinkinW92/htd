package com.skeqi.mes.mapper.lcy;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassT;

public interface GetReportFormsMapper {



	/**
	 * 工位完成数量统计
	 * @return
	 */

	//初始化产线
	List<String> getInItStationNumberStatistics();
	//获取当天当产线的所有工位
	List<String> getStationPassSt(@Param("getLine")String getLine);
	//获取当天当产线当工位的产量
	Integer getStationPassProductNumber(@Param("getStartTime")String getStartTime, @Param("getEndTime")String getEndTime, @Param("st")String st, @Param("getLine")String getLine);

	/**
	 * 班次数量统计
	 * @return
	 */
	//初始化产线列表
	List<String> getInItShiftNumberStatistics();

	List<CMesShiftsTeamT> shiftsTeamList(@Param("getLine")Integer getLine,@Param("dt") String dt);

	List<CMesShiftsTeamT> shiftsTeamIdAndNameAndTime(CMesShiftsTeamT cMesShiftsTeamT);


	//获取产量
	Integer getShiftNumberStatistics(@Param("getStartTime")Date getStartTime,  @Param("getEndTime")Date getEndTime,  @Param("getLine")Integer getLine);


	List<PMesStationPassT> getStationPassProductNumberL(@Param("startTime")String startTime,@Param("endTime") String endTime, @Param("st") String st, @Param("lineId")Integer lineId);




}
