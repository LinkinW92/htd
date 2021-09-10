package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.PMesStationEqStatusDAO;
import com.skeqi.mes.pojo.project.PMesStationEqStatusT;

@Service
public class PMesStationEqStatusServiceImpl implements PMesStationEqStatusService{

	@Autowired
	PMesStationEqStatusDAO dao;



	@Override
	public List<PMesStationEqStatusT> findAllEq(String name) throws ServicesException {
		// TODO Auto-generated method stub
		List<PMesStationEqStatusT> findAllEq = dao.findAllEq(name);
		for (PMesStationEqStatusT pMesStationEqStatusT : findAllEq) {
			switch (pMesStationEqStatusT.getEqStatusType()) {
				case 0:
					pMesStationEqStatusT.setEqStatusName("正常");
					break;
				case 1:
					pMesStationEqStatusT.setEqStatusName("停机");
					break;
				case 2:
					pMesStationEqStatusT.setEqStatusName("设备故障");
					break;
				case 3:
					pMesStationEqStatusT.setEqStatusName("缺料");
					break;
				case 4:
					pMesStationEqStatusT.setEqStatusName("堵料");
					break;
				case 5:
					pMesStationEqStatusT.setEqStatusName("其他");
					break;
				default:
					break;
			}
		}
		return findAllEq;
	}

	@Override
	public Integer addEq(PMesStationEqStatusT eq) throws ServicesException {
		// TODO Auto-generated method stub
		if(eq.getEqName()==null || eq.getEqName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(eq.getSt()==null || eq.getSt()==""){
			throw new ParameterNullException("工位不能为空",200);
		}else if(eq.getEqStatusType()==null){
			throw new ParameterNullException("设备状态不能为空",200);
		}else if(eq.getLineId()==null){
			throw new ParameterNullException("产线不能为空",200);
		}
		return dao.addEq(eq);
	}

	@Override
	public Integer udpateEq(PMesStationEqStatusT eq) throws ServicesException {
		// TODO Auto-generated method stub
		if(eq.getEqName()==null || eq.getEqName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(eq.getSt()==null || eq.getSt()==""){
			throw new ParameterNullException("工位不能为空",200);
		}else if(eq.getEqStatusType()==null){
			throw new ParameterNullException("设备状态不能为空",200);
		}else if(eq.getLineId()==null){
			throw new ParameterNullException("产线不能为空",200);
		}
		return dao.udpateEq(eq);
	}

	@Override
	public Integer delEq(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delEq(id);
	}
}
