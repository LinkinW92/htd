package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;

public interface CMesShiftTeamService {



	public List<CMesShiftsTeamT> findAllShift(Map<String, Object> map) throws ServicesException;

	//添加
	public Integer addShift(CMesShiftsTeamT shift)throws ServicesException;

	//删除
	public Integer delShift(@Param("id")Integer id)throws ServicesException;

	//修改
	public Integer updateShift(CMesShiftsTeamT shift) throws ServicesException;

	public List<CMesLineT> findAllLine()throws ServicesException;

	public Integer findLineName(@Param("name")String name)throws ServicesException;

	public Integer findByName(String name, Integer lineId);

	//根据产线名查询产线ID
	public Integer findByLineName(String lineName);

	public List<CMesLineT> findAllLineL(Integer paibanStatus, Integer andengStatus);

	public List<CMesLineT> findShiftLineName();
}

