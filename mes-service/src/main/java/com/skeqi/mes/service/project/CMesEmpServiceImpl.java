package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesEmpDAO;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class CMesEmpServiceImpl implements CMesEmpService{

	@Autowired
	CMesEmpDAO dao;

	@Override
	public List<CMesEmpT> findAllEmp(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllEmp(name);
	}

	@Override
	public List<CMesEmpT> findStationNameById(CMesEmpT cMesEmpT) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findStationNameById(cMesEmpT);
	}

	@Override
	public Integer addEmp(CMesEmpT emp) throws ServicesException {
		// TODO Auto-generated method stub
		if(emp.getEmpName()==null || emp.getEmpName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(emp.getEmpNo()==null || emp.getEmpNo()==""){
			throw new ParameterNullException("编号不能为空",200);
		}else if(emp.getEmpTp()==null || emp.getEmpTp()==""){
			throw new ParameterNullException("电话不能为空",200);
		}else if(emp.getStationId()==null){
			throw new ParameterNullException("工位ID不能为空",200);
		}else if(emp.getEmpTeamId()==null){
			throw new ParameterNullException("班组ID不能为空",200);
		}else if(emp.getLineId()==null){
			throw new ParameterNullException("产线ID不能为空",200);
		}else if(emp.getEmpType()==null){
			throw new ParameterNullException("员工类型ID不能为空",200);
		}else if(emp.getpassword()==null){
			throw new ParameterNullException("员工密码不能为空",200);
		}
		String password = emp.getpassword();
		String md5PW = DigestUtils.md5DigestAsHex(password.getBytes());
		emp.setpassword(md5PW);
		return dao.addEmp(emp);
	}

	@Override
	public Integer updateEmp(CMesEmpT emp) throws ServicesException {
		// TODO Auto-generated method stub
		if(emp.getEmpName()==null || emp.getEmpName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(emp.getEmpNo()==null || emp.getEmpNo()==""){
			throw new ParameterNullException("编号不能为空",200);
		}else if(emp.getEmpTp()==null || emp.getEmpTp()==""){
			throw new ParameterNullException("电话不能为空",200);
		}else if(emp.getId()==null){
			throw new ParameterNullException("ID不能为空",200);
		}else if(emp.getStationId()==null){
			throw new ParameterNullException("工位ID不能为空",200);
		}else if(emp.getEmpTeamId()==null){
			throw new ParameterNullException("班组ID不能为空",200);
		}else if(emp.getLineId()==null){
			throw new ParameterNullException("产线ID不能为空",200);
		}else if(emp.getEmpType()==null){
			throw new ParameterNullException("员工类型ID不能为空",200);
		}

		return dao.updateEmp(emp);
	}

	@Override
	public Integer delEmp(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null){
			throw new ParameterNullException("ID不能为空",200);
		}
		return dao.delEmp(id);
	}

	@Override
	public List<CMesEmpTypeT> findsEmpType() {
		// TODO Auto-generated method stub
		return dao.findsEmpType();
	}

	@Override
	public List<CMesStationT> findStation() {
		// TODO Auto-generated method stub
		return dao.findStation();
	}

	@Override
	public Integer findEmpTypeName(String typeName) {
		// TODO Auto-generated method stub
		return dao.findEmpTypeName(typeName);
	}

	@Override
	public Integer findTeamName(String name) {
		// TODO Auto-generated method stub
		return dao.findTeamName(name);
	}

	@Override
	public Integer findStationName(String name) {
		// TODO Auto-generated method stub
		return dao.findStationName(name);
	}

	@Override
	public Integer findEmpByName(String empNos) {
		// TODO Auto-generated method stub
		return dao.findEmpByName( empNos);
	}

}
