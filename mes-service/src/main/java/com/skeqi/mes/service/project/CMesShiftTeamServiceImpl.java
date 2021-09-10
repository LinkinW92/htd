package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesShiftTeamDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.project.CMesFaultTypeT;

@Service
public class CMesShiftTeamServiceImpl implements CMesShiftTeamService{

	@Autowired
	CMesShiftTeamDAO dao;

	@Override
	public List<CMesShiftsTeamT> findAllShift(Map<String, Object> map) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllShift(map);
	}

	@Override
	public Integer addShift(CMesShiftsTeamT shift) throws ServicesException {
		// TODO Auto-generated method stub
		if(shift.getName()==null || shift.getName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(shift.getStartTime()==null || shift.getStartTime()==""){
			throw new ParameterNullException("开始时间不能为空",200);
		}else if(shift.getEndTime()==null || shift.getEndTime()==""){
			throw new ParameterNullException("结束时间不能为空",200);
		}else if(shift.getPlanTime()==null || shift.getPlanTime()==""){
			throw new ParameterNullException("计划生产时间不能为空",200);
		}
		Integer findLineName = dao.findLineName(shift.getLineName());
		if(findLineName==null){
			throw new ParameterNullException("产线名称不存在",200);
		}
		shift.setLineId(findLineName);
		return dao.addShift(shift);
	}

	@Override
	public Integer delShift(Integer id) throws ServicesException {
		if(id==null ){
			throw new ParameterNullException("id不能为空",200);
		}
		// TODO Auto-generated method stub
		return dao.delShift(id);
	}

	@Override
	public Integer updateShift(CMesShiftsTeamT shift) throws ServicesException {
		// TODO Auto-generated method stub
		if(shift.getName()==null || shift.getName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(shift.getStartTime()==null || shift.getStartTime()==""){
			throw new ParameterNullException("开始时间不能为空",200);
		}else if(shift.getEndTime()==null || shift.getEndTime()==""){
			throw new ParameterNullException("结束时间不能为空",200);
		}else if(shift.getPlanTime()==null || shift.getPlanTime()==""){
			throw new ParameterNullException("计划生产时间不能为空",200);
		}
		Integer findLineName = dao.findLineName(shift.getLineName());
		if(findLineName==null){
			throw new ParameterNullException("产线名称不存在",200);
		}
		shift.setLineId(findLineName);
		return dao.updateShift(shift);
	}

	@Override
	public List<CMesLineT> findAllLine( ) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLine();
	}

	@Override
	public Integer findLineName(String name) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findLineName(name);
	}

	@Override
	public Integer findByName(String name,Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findByName( name,lineId);
	}

	@Override
	public Integer findByLineName(String lineName) {
		// TODO Auto-generated method stub
		return dao.findByLineName( lineName) ;
	}

	@Override
	public List<CMesLineT> findAllLineL(Integer paibanStatus, Integer andengStatus) {
		// TODO Auto-generated method stub
		return dao.findAllLinel(paibanStatus, andengStatus);
	}

	@Override
	public List<CMesLineT> findShiftLineName() {
		// TODO Auto-generated method stub
		return dao.findShiftLineName();
	}





}
