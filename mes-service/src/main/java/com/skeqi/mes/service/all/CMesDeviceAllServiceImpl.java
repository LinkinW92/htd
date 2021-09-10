package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesDeviceAllTDAO;
import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;

@Service
@Transactional
public class CMesDeviceAllServiceImpl implements CMesDeviceAllService {

	@Autowired
	private CMesDeviceAllTDAO dao;

	@Override
	public List<CMesDeviceSpotT> findAllSpot(CMesDeviceSpotT spot)  throws ServicesException{
		// TODO Auto-generated method stub
		return dao.findAllSpot(spot);
	}

	@Override
	public CMesDeviceSpotT findSpotByid(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findSpotByid(id);
	}

	@Override
	public Integer addSpot(CMesDeviceSpotT spot)  throws ServicesException{
		// TODO Auto-generated method stub
		if(spot.getDeviceName()==null || spot.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(spot.getEmp()==null || spot.getEmp()==""){
			throw new ParameterNullException("点检人不能为空",200);
		}
		return dao.addSpot(spot);
	}

	@Override
	public Integer updateSpot(CMesDeviceSpotT spot)  throws ServicesException{
		// TODO Auto-generated method stub
		if(spot.getDeviceName()==null || spot.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(spot.getEmp()==null || spot.getEmp()==""){
			throw new ParameterNullException("点检人不能为空",200);
		}
		return dao.updateSpot(spot);
	}

	@Override
	public Integer delSppot(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delSppot(id);
	}

	@Override
	public List<CMesDeviceRepairT> findAllRepair(CMesDeviceRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRepair(t);
	}

	@Override
	public CMesDeviceRepairT findRepairByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findRepairByid(id);
	}

	@Override
	public Integer addRepair(CMesDeviceRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getDeviceName()==null || t.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("负责人不能为空",200);
		}else if(t.getRepairPerson()==null || t.getRepairPerson()==""){
			throw new ParameterNullException("维修人不能为空",200);
		}else if(t.getReason()==null || t.getReason()==null){
			throw new ParameterNullException("原因不能为空",200);
		}

		return dao.addRepair(t);
	}

	@Override
	public Integer updateRepair(CMesDeviceRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getDeviceName()==null || t.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("负责人不能为空",200);
		}else if(t.getRepairPerson()==null || t.getRepairPerson()==""){
			throw new ParameterNullException("维修人不能为空",200);
		}else if(t.getReason()==null || t.getReason()==null){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateRepair(t);
	}

	@Override
	public Integer delRepair(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delRepair(id);
	}

	@Override
	public List<CMesDeviceUpkeep> findAllKeep(CMesDeviceUpkeep kepp) throws ServicesException {
		// TODO Auto-generated method stub
		List<CMesDeviceUpkeep> findAllKeep = dao.findAllKeep(kepp);
		for (CMesDeviceUpkeep cMesDeviceUpkeep : findAllKeep) {
			Integer findSurplusTime = dao.findSurplusTime(cMesDeviceUpkeep.getId());
			if(findSurplusTime<=1){
				cMesDeviceUpkeep.setSurplusMaintain(1);
			}else{
				cMesDeviceUpkeep.setSurplusMaintain(0);
			}
		}
		return findAllKeep;
	}

	@Override
	public CMesDeviceUpkeep findKeepByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findKeepByid(id);
	}

	@Override
	public Integer findSurplusTime(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findSurplusTime(id);
	}

	@Override
	public Integer addKeep(CMesDeviceUpkeep keep) throws ServicesException {
		// TODO Auto-generated method stub
		if(keep.getDeviceName()==null || keep.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(keep.getEmp()==null || keep.getEmp()==""){
			throw new ParameterNullException("负责员工不能为空",200);
		}else if(keep.getUpkeepPerson()==null || keep.getUpkeepPerson()==""){
			throw new ParameterNullException("保养人不能为空",200);
		}else if(keep.getMaintainCycle()==null || keep.getMaintainCycle()==0){
			throw new ParameterNullException("维修周期不能为空",200);
		}

		return dao.addKeep(keep);
	}

	@Override
	public Integer updateKeep(CMesDeviceUpkeep keep) throws ServicesException {
		// TODO Auto-generated method stub
		if(keep.getDeviceName()==null || keep.getDeviceName()==""){
			throw new ParameterNullException("设备名称不能为空",200);
		}else if(keep.getEmp()==null || keep.getEmp()==""){
			throw new ParameterNullException("负责员工不能为空",200);
		}else if(keep.getUpkeepPerson()==null || keep.getUpkeepPerson()==""){
			throw new ParameterNullException("保养人不能为空",200);
		}else if(keep.getMaintainCycle()==null || keep.getMaintainCycle()==0){
			throw new ParameterNullException("维修周期不能为空",200);
		}
		return dao.updateKeep(keep);
	}

	@Override
	public Integer updateKeppDate(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}

		return dao.updateKeppDate(id);
	}

}
