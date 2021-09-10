package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;

public interface CMesReportService {

	public Map<Integer,Integer> Reportyield(@Param("id")String id,@Param("lineName")String lineName) throws Exception;

	public Map<Integer,Integer> ReportyieldTwo(@Param("id")String id,@Param("lineName")String lineName)throws Exception;


	public Integer findYield(String name)throws Exception;


	public JSONArray findOEE(Integer lineId,String startTime,String endTime)throws Exception;

	public List<Map<String,Object>> ProductionNumberByLine(String startTime,String endTime) throws Exception;

	public List<Map<String,Object>> ProNumByLineName(String lineName);
}
