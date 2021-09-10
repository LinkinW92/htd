package com.skeqi.mes.service.lcy;
import java.util.List;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;

public interface GetSomeYieldService {
	//得到每月有多少产品
	int getMonthYeild(String getStartYearAndMonth, String getEndYearAndMonth,Integer getValue,Integer lineId);
	//日产品列表
	Integer getEveryDayYeild(String getStartTime, String getEndTime,Integer getValue,Integer lineId);
	//得到每小时的产量
	int getHourQualified(String getStartDate, String getEndDate,Integer getValue,Integer lineId);


	//得到一个时间段里所有产品的列表
	Integer getPMesTrackingTList(String getStartDate, String getEndDate,Integer queryNumber,Integer lineId);
	//获取产线名称
	List<String> getProductST(String getLine);

	//获得拧紧产品
	double someBoltProduct(String st, String getStartDate, String getEndDate);

	//查询工位时间信息

	List<PMesStationPassEqStatusT> getStationTimeStatistics(String getStartTime, String getEndTime, String pageValue,String stName);

	//获取工位信息
	List<String> getStList();
	Integer getSomeDate(Integer nowId, Integer nextId);
	//获取产线
	List<CMesLineT> getLine();
	//查询时间
	Integer addSomeDate(String dt, String date);
	int getHourQualified2(String getStartDate, String getEndDate, Integer getValue,Integer lineId);
	Integer getTime(String getStartDate, String getEndDate, Integer lineId);
	List<CMesShiftsTeamT> getShiftsTeamList(Integer lineId);
	Integer getMonthLeakage(String getStartYearAndMonth, String getEndYearAndMonth,Integer value, Integer lineId);







}
