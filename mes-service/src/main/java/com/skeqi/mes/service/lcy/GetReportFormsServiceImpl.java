package com.skeqi.mes.service.lcy;

import java.util.Date;
import java.util.List;

import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.GetReportFormsMapper;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStationPassT;

@Service
public class GetReportFormsServiceImpl implements GetReportFormsService {
	/**
	 * 工位完成统计
	 */
	@Autowired
	private GetReportFormsMapper grfm;

	//初始化产线
	@Override
	public List<String> getInItStationNumberStatistics() {
		return grfm.getInItStationNumberStatistics();
	}


	//获取当天当产线的所有工位
	@Override
	public List<String> getStationPassSt(String getLine) {
		// TODO Auto-generated method stub
		return grfm.getStationPassSt(getLine);
	}
	//获取当天当产线当工位的产量
	@Override
	public Integer getStationPassProductNumber(String getStartTime, String getEndTime, String st, String getLine) {
		// TODO Auto-generated method stub
		return grfm.getStationPassProductNumber(getStartTime,getEndTime,st,getLine);
	}

	/**
	 * 班次数量统计
	 */
	//获取产线列表
	@Override
	public List<String> getInItShiftNumberStatistics() {
		// TODO Auto-generated method stub
		return grfm.getInItShiftNumberStatistics();
	}


	//获取产线的排版列表
	@Override
	public List<CMesShiftsTeamT> shiftsTeamList(Integer getLine,String dt) {
		// TODO Auto-generated method stub
		return grfm.shiftsTeamList(getLine, dt);
	}


	//获取产量
	@Override
	public Integer getShiftNumberStatistics(Date getStartTime, Date getEndTime, Integer getLine) {
		// TODO Auto-generated method stub

		return grfm.getShiftNumberStatistics(getStartTime,getEndTime,getLine);
	}


	@Override
	public List<PMesStationPassT> getStationPassProductNumberL(String startTime, String endTime, String st,
			Integer lineId) {
		// TODO Auto-generated method stub
		return grfm.getStationPassProductNumberL(startTime,endTime,st,lineId);
	}


	@Override
	public List<CMesShiftsTeamT> shiftsTeamIdAndNameAndTime(CMesShiftsTeamT cMesShiftsTeamT) {
		return grfm.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
	}
}
