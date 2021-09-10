package com.skeqi.mes.service.all;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesStationT;

public interface CMesStationTService {

	public List<CMesStationT> findAllStation(CMesStationT c) throws ServicesException;

	public List<CMesStationT> findStationByListId(Integer listId) throws ServicesException;

	/**
	 * 查询所有工位Index、Name
	 * @return
	 */
	public List<CMesStationT> findStationAll();

	public CMesStationT findStationByid(Integer id) throws ServicesException;

	public Integer addStation(CMesStationT c) throws ServicesException;

	public Integer updateStation(CMesStationT c) throws ServicesException;
	public Integer updateStationold(CMesStationT c) throws ServicesException;

	public Integer delStation(Integer id) throws ServicesException;

	public List<CMesStationT> findStationNameAndId(CMesStationT c);

}
