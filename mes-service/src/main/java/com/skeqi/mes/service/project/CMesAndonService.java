package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.pojo.project.InsertInfo;

public interface CMesAndonService {

	public Integer addFault(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("faultType")Integer faultType)  throws Exception;

	public Integer responseFault(@Param("lineName")String lineName,@Param("stationName")String stationName) throws Exception;

	public Integer SolveFault(@Param("lineName")String lineName,@Param("stationName")String stationName) throws Exception ;

	public List<CMesAndonFault> findAllAndon(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("status")Integer status,String lossType,String startDate, String  endDate) throws ServicesException;

	public Integer updateFault(CMesAndonFault fault) throws ServicesException;

	public Integer insertInfo(InsertInfo info)throws Exception;

	public List<InsertInfo> findNowInfo(@Param("lineName")String lineName,@Param("stationName")String stationName)throws ServicesException;

	public  List<Map<String,Object>> findPareto(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("starttime")String startDt,@Param("endtime")String endtime) throws Exception;

	public List<InsertInfo> findInfo(@Param("lineName")String lineName,@Param("stationName")String stationName)throws ServicesException;

	public List<CMesLossTypeT> findAllLoss()throws ServicesException;

	public List<CMesAndonFault> findNowAndon(@Param("lineName")String lineName,@Param("stationName")String stationName,String lossType, @Param("startDate")String startDate, @Param("endDate")String endDate)throws ServicesException;

	public List<Map<String,Object>> findEmp()throws ServicesException;

	public Integer delAndon(@Param("id")Integer id)throws ServicesException;

	public JSONArray findStationStatus(String lineName) throws ServicesException;

	public JSONArray findCount(String lineName)throws ServicesException;

	public List<Map<String,Object>> findParetors(@Param("lineName")String lineName,@Param("stationName")String stationName,@Param("starttime")String startDt,@Param("endtime")String endtime,Integer lossType) throws Exception;

	public List<CMesLossReasonT> findLossReason(Integer id) throws ServicesException;

	public JSONObject findNowNumber(String lineName) throws Exception;

	public Integer updateStationResponse(String lineName)throws ServicesException;

	//当前故障列表710重写版
	public List<JSONObject> findStationStatus710(String lineName)throws ServicesException;

	public void closePlan();

}
