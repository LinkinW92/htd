package com.skeqi.mes.service.lcy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.GetSomeYieldMapper;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;
@Service
public class GetSomeYieldServiceImpl implements GetSomeYieldService{

	@Autowired
	private GetSomeYieldMapper gsym ;

	@Override
	public int getMonthYeild(String getStartYearAndMonth, String getEndYearAndMonth,Integer getValue,Integer lineId) {

		return gsym.getMonthYeild(getStartYearAndMonth,getEndYearAndMonth,getValue,lineId);
	}
	@Override
	public Integer getEveryDayYeild(String getStartTime, String getEndTime, Integer getValue,Integer lineId) {
		return gsym.getEveryDayYeild(getStartTime,getEndTime,getValue,lineId);
	}
	@Override
	public int getHourQualified(String getStartDate, String getEndDate,Integer getValue,Integer lineId) {
		return gsym.getHourQualified(getStartDate,getEndDate,getValue,lineId);
	}


	@Override
	public Integer getPMesTrackingTList(String getStartDate, String getEndDate,Integer queryNumber,Integer lineId) {
		return gsym.getPMesTrackingTList(getStartDate,getEndDate,queryNumber,lineId);
	}
	@Override
	public List<String> getProductST(String getLine) {
		return gsym.getProductST(getLine);
	}
	@Override
	public double someBoltProduct(String st, String getStartDate, String getEndDate) {
		return gsym.someBoltProduct(st,getStartDate,getEndDate);
	}
	@Override
	public List<PMesStationPassEqStatusT> getStationTimeStatistics(String getStartTime, String getEndTime,
			String pageValue,String stName) {
		return gsym.getStationTimeStatistics(getStartTime,getEndTime,pageValue,stName);
	}

	//获取工位信息
	@Override
	public List<String> getStList() {
		return gsym.getStList();
	}


	@Override
	public Integer getSomeDate(Integer nowId, Integer nextId) {
		return gsym.getSomeDate(nowId,nextId);
	}
	//获取产线
	@Override
	public List<CMesLineT> getLine() {
		return gsym.getLine();
	}
	@Override
	public Integer addSomeDate(String dt, String date) {
		// TODO Auto-generated method stub
		return gsym.addSomeDate(dt,date);
	}
	@Override
	public int getHourQualified2(String getStartDate, String getEndDate, Integer getValue,Integer lineId) {
		return gsym.getHourQualified2(getStartDate,getEndDate,getValue,lineId);
	}
	@Override
	public Integer getTime(String getStartDate, String getEndDate, Integer lineId) {
		// TODO Auto-generated method stub
		return gsym.getTime(getStartDate,getEndDate,lineId);
	}
	@Override
	public List<CMesShiftsTeamT> getShiftsTeamList(Integer lineId) {
		// TODO Auto-generated method stub
		return gsym.getShiftsTeamList(lineId);
	}
	@Override
	public Integer getMonthLeakage(String getStartYearAndMonth, String getEndYearAndMonth, Integer value,
			Integer lineId) {
		// TODO Auto-generated method stub
		return  gsym.getMonthLeakage(getStartYearAndMonth,getEndYearAndMonth,value,lineId);
	}



}
