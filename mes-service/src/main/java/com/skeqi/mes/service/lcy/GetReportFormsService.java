package com.skeqi.mes.service.lcy;

import java.util.Date;
import java.util.List;

import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassT;

public interface GetReportFormsService {


	/**
	 * 工位完成统计
	 * @return
	 */
	//初始化产线
	public List<String> getInItStationNumberStatistics();

	//获取当天当产线的所有工位
	public List<String> getStationPassSt(String getLine);
	//获取当天当产线当工位的产量
	public Integer getStationPassProductNumber(String getStartTime, String getEndTime, String st, String getLine);

	public List<CMesShiftsTeamT> shiftsTeamIdAndNameAndTime(CMesShiftsTeamT cMesShiftsTeamT);

	/**
	 * 班次数量统计
	 * @return
	 */
	//初始化产线
	public List<String> getInItShiftNumberStatistics();

	//获取产线的排版列表
	public List<CMesShiftsTeamT> shiftsTeamList(Integer getLine, String dt);

	//获取产量
	public Integer getShiftNumberStatistics(Date getStartTime, Date getEndTime, Integer getLine);


	//工位完成数量产量
	public List<PMesStationPassT> getStationPassProductNumberL(String startTime, String endTime, String st, Integer lineId);


}
