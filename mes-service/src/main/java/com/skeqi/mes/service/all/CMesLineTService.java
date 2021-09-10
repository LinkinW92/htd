package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationLine;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.qh.CMesEndStocks;

public interface CMesLineTService {

	public List<CMesLineT> findAllLine(CMesLineT line) throws ServicesException;

	public CMesLineT findLineByid(Integer id) throws ServicesException;

	public Integer addLine(CMesLineT line) throws Exception;

	public Integer updateLine(CMesLineT line) throws Exception;

	public Integer delLine(Integer id) throws Exception;

	public Integer updateStatus(Integer id, Integer status) throws ServicesException;

	public List<CMesStationLine> findStationByLid(Integer id);

	//
	// //添加排班
	// public Integer addSchebuling(Integer shiftId,Integer teamId,Integer
	// number,String dt,Integer lineId, Integer planrealNumber) throws
	// ServicesException;

	public int addSchebuling(CMesScheduling du);

	// 根据产线id查询计划数量

	// 查询所有产品
	public List<Integer> findAllPro();

	public CMesEndStocks findByName(String proName, String lineName, String stationName, Integer lineRegion) throws Exception;

	public List<Map<String, Object>> findAll();

	public Integer delBoard(Integer id);

	public void addBoard(String boardName, String boardType, String lineName);

	public void updateBoard(String boardName, String boardType, String lineName, Integer id);

	// 根据产线名称或产线编码查询
	public Map<String, Object> getLineByName(CMesLineT line);

	public CMesLineT getLineByLineIdAndName(CMesLineT line);

}
