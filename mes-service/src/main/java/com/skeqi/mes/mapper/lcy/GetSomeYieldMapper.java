package com.skeqi.mes.mapper.lcy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;



public interface GetSomeYieldMapper {
	//得到每月的产量
	int getMonthYeild(@Param("getStartYearAndMonth")String getStartYearAndMonth,@Param("getEndYearAndMonth")String getEndYearAndMonth,@Param("getValue") Integer getValue,@Param("lineId")Integer lineId);
	//得到产品的列表
	int getEveryDayYeild(@Param("getStartTime")String getStartTime,@Param("getEndTime") String getEndTime,@Param("getValue") Integer getValue,@Param("lineId")Integer lineId);


	//得到每小时的产量
	int getHourQualified(@Param("getStartDate")String getStartDate, @Param("getEndDate")String getEndDate,@Param("getValue")Integer getValue,@Param("lineId")Integer lineId);


	//获得这个时间段的所有产品的列表
	int getPMesTrackingTList(@Param("getStartDate")String getStartDate, @Param("getEndDate")String getEndDate,@Param("queryNumber") Integer queryNumber,@Param("lineId")Integer lineId);

	//获取产线
	List<String> getProductST(@Param("getLine")String getLine);

	//获得拧紧合格率
	double someBoltProduct(@Param("st")String st,@Param("getStartDate")String getStartDate, @Param("getEndDate")String getEndDate);

	//工位时间信息
	List<PMesStationPassEqStatusT> getStationTimeStatistics(@Param("getStartTime")String getStartTime, @Param("getEndTime")String getEndTime, @Param("pageValue")String pageValue,@Param("stName")String stName);

	//获取工位信息
	List<String> getStList();

	//获取时间
	Integer getSomeDate(@Param("nowId")Integer nowId, @Param("nextId")Integer nextId);

	//获取产线
	List<CMesLineT> getLine();
	//获取时间
	Integer addSomeDate(@Param("dt")String dt,@Param("date")String date);

	int getHourQualified2(@Param("getStartDate")String getStartDate, @Param("getEndDate")String getEndDate,@Param("getValue")Integer getValue,@Param("lineId")Integer lineId);
	Integer getTime(@Param("getStartDate")String getStartDate, @Param("getEndDate")String getEndDate,@Param("lineId") Integer lineId);
	List<CMesShiftsTeamT> getShiftsTeamList(@Param("lineId")Integer lineId);

	Integer getMonthLeakage(@Param("getStartYearAndMonth")String getStartYearAndMonth,@Param("getEndYearAndMonth")String getEndYearAndMonth,@Param("getValue") Integer getValue,@Param("lineId")Integer lineId);


}
