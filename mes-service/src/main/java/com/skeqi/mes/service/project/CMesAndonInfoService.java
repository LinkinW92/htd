package com.skeqi.mes.service.project;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.InsertInfo;

public interface CMesAndonInfoService {

	public List<InsertInfo> findAllInfo(Integer workId,String lineName,String stationName,String sn,String startDate,String endDate) throws ServicesException;

	public Integer delAndonInfo(Integer id) throws ServicesException;

	public Integer InsertAndonInfo(String stationName,String dt,String sn,Integer countType,Integer workId,Integer number) throws ServicesException;

	public String findByLineName(String lineId);
}
