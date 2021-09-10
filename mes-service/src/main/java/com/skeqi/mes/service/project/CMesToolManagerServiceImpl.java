package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesToolManagerDAO;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.project.CMesToolManager;

@Service
public class CMesToolManagerServiceImpl implements CMesToolManagerService{

	@Autowired
	CMesToolManagerDAO dao;

	@Override
	public List<CMesToolManager> findAllTool(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllTool(name);
	}

	@Override
	public Integer addTool(CMesToolManager tool) throws ServicesException {
		// TODO Auto-generated method stub
		if(tool.getToolNo()==null || tool.getToolNo()==""){
			throw new ParameterNullException("编号不能为空",200);
		}else if(tool.getToolName()==null || tool.getToolName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		if(dao.findDeviceNo(tool.getToolNo())>0){
			throw new ParameterNullException("编号不能重复",200);
		}
//		else if(tool.getEstimateLife()==null){
//			throw new ParameterNullException("预计寿命不能为空",200);
//		}else if(tool.getMaintainCycle()==null){
//			throw new ParameterNullException("维护周期不能为空",200);
//		}else if(tool.getFirstUse()==null){
//			throw new ParameterNullException("初次使用时间不能为空",200);
//		}else if(tool.getLineId()==null){
//			throw new ParameterNullException("初次使用时间不能为空",200);
//		}else if(tool.getStationId()==null){
//			throw new ParameterNullException("初次使用时间不能为空",200);
//		}

		return dao.addTool(tool);
	}

	@Override
	public Integer updateTool(CMesToolManager tool) throws ServicesException {
		// TODO Auto-generated method stub
		if(tool.getToolNo()==null || tool.getToolNo()==""){
			throw new ParameterNullException("编号不能为空",200);
		}else if(tool.getToolName()==null || tool.getToolName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		if(dao.findDeviceNoById(tool.getToolNo(), tool.getId())>0){
			throw new ParameterNullException("编号不能重复",200);
		}
//		else if(tool.getEstimateLife()==null){
//			throw new ParameterNullException("预计寿命不能为空",200);
//		}else if(tool.getMaintainCycle()==null){
//			throw new ParameterNullException("维护周期不能为空",200);
//		}else if(tool.getLineId()==null){
//			throw new ParameterNullException("初次使用时间不能为空",200);
//		}else if(tool.getStationId()==null){
//			throw new ParameterNullException("初次使用时间不能为空",200);
//		}
		return dao.updateTool(tool);
	}

	@Override
	public Integer maintain(Integer id) throws ServicesException {
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.maintain(id);
	}

	@Override
	public Integer delTool(Integer id) throws ServicesException {
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		// TODO Auto-generated method stub
		return dao.delTool(id);
	}

	@Override
	public Integer UseTool(Integer id, Integer num) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(num==null || num==0){
			throw new ParameterNullException("使用次数不能为空",200);
		}
		return dao.UseTool(id, num);
	}

	@Override
	public List<CMesStationT> findStation(Integer lineId) throws ServicesException {
		// TODO Auto-generated method stub
		if(lineId==null || lineId==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findStation(lineId);
	}

}
