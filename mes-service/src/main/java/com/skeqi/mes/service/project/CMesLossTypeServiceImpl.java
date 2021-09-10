package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesLossTypeDAO;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

@Service
public class CMesLossTypeServiceImpl implements CMesLossTypeService{

	@Autowired
	CMesLossTypeDAO dao;

	@Override
	public List<CMesLossTypeT> findAllLoss(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLoss(name);
	}

	@Override
	public Integer addLoss(String name, String note) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		return dao.addLoss(name, note);
	}

	@Override
	public Integer updateLoss(String name, String note, Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.updateLoss(name, note, id);
	}

	@Override
	public Integer delLoss(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delLoss(id);
	}

	@Override
	public List<CMesLossReasonT> findReason() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findReason();
	}

	@Override
	public Integer addReason(String reasonNo,Integer lossId, String name, String note) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(lossId==null || lossId==0){
			throw new ParameterNullException("损失类型id不能为空",200);
		}else if(reasonNo==null || reasonNo==""){
			throw new ParameterNullException("编号不能为空",200);
		}
		Integer findByName = dao.findByName(name);
		if(findByName>0){
			throw new ParameterNullException("名称已存在",200);
		}
		Integer findByNo = dao.findByNo(reasonNo);
		if(findByNo>0){
			throw new ParameterNullException("编号已存在",200);
		}

		return dao.addReason(reasonNo,lossId, name, note);
	}

	@Override
	public Integer updateReason(String reasonNo,Integer lossId, String name, String note, Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(lossId==null || lossId==0){
			throw new ParameterNullException("损失类型id不能为空",200);
		}else if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasonNo==null || reasonNo==""){
			throw new ParameterNullException("编号不能为空",200);
		}
		Integer findByName = dao.findByName1(name, id);
		if(findByName>0){
			throw new ParameterNullException("名称已存在",200);
		}
		Integer findByNo = dao.findByNo1(reasonNo,id);
		if(findByNo>0){
			throw new ParameterNullException("编号已存在",200);
		}

		return dao.updateReason(reasonNo,lossId, name, note, id);
	}

	@Override
	public Integer delReason(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delReason(id);
	}

	@Override
	public String findReasonNameByNO(String id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findReasonNameByNO(id);
	}


}
