                          package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.Exception.util.UnknownException;
import com.skeqi.mes.mapper.all.ProduceDAO;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesNoticeT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;

@Service
@Transactional
public class ProduceServiceImpl implements ProduceService {

	@Autowired
	private ProduceDAO dao;

	@Override
	public List<CMesShiftsTeamT> findAllShift(CMesShiftsTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllShift(t);
	}

	@Override
	public CMesShiftsTeamT findShiftByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findShiftByid(id);
	}

	@Override
	public Integer addShift(CMesShiftsTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getName()==null || t.getName()==""){
			throw new ParameterNullException("班次列表不能为空",200);
		}else if(t.getEndTime()==null || t.getEndTime()==""){
			throw new ParameterNullException("结束时间不能为空",200);
		}else if(t.getStartTime()==null || t.getStartTime()==""){
			throw new ParameterNullException("开始时间不能为空",200);
		}

		List<CMesShiftsTeamT> findAllShift = dao.findAllShift(t);
		if(findAllShift.size()>0){
			throw new NameRepeatException("班次名称重复",100);
		}
		return dao.addShift(t);
	}

	@Override
	public Integer updateShift(CMesShiftsTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getName()==null || t.getName()==""){
			throw new ParameterNullException("班次列表不能为空",200);
		}else if(t.getEndTime()==null || t.getEndTime()==""){
			throw new ParameterNullException("结束时间不能为空",200);
		}else if(t.getStartTime()==null || t.getStartTime()==""){
			throw new ParameterNullException("开始时间不能为空",200);
		}

		CMesShiftsTeamT findShiftByid = dao.findShiftByid(t.getId());
		if(!findShiftByid.getName().equals(t.getName())){
			CMesShiftsTeamT c = new CMesShiftsTeamT();
			c.setName(t.getName());
			List<CMesShiftsTeamT> findAllShift = dao.findAllShift(c);
			if(findAllShift.size()>0){
				throw new NameRepeatException("班次名称重复",100);
			}
		}
		return dao.updateShift(t);
	}

	@Override
	public Integer delShift(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delShift(id);
	}

	@Override
	public List<CMesEmpTeamT> findAllTeam(CMesEmpTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllTeam(t);
	}

	@Override
	public CMesEmpTeamT findTeamByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findTeamByid(id);
	}

	@Override
	public Integer addTeam(CMesEmpTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getName()==null || t.getName()==""){
			throw new ParameterNullException("班组name不能为空",200);
		}else if(t.getEmps()==null || t.getEmps()==""){
			throw new ParameterNullException("员工号不能为空",200);
		}

		List<CMesEmpTeamT> findAllTeam = dao.findAllTeam(t);
		if(findAllTeam.size()>0){
			throw new NameRepeatException("班组名称重复",100);
		}
		Integer addTeam = dao.addTeam(t);
		Integer maxTeamId = dao.getMaxTeamId();  //最大额班组id
		Integer addShiftTeam = dao.addShiftTeam(maxTeamId,t.getShiftsTeamId());
		if(addTeam<=0 || addShiftTeam<=0){
			throw new UnknownException("未知错误",300);
		}
		return 1;
	}

	@Override
	public Integer updateTeam(CMesEmpTeamT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getName()==null || t.getName()==""){
			throw new ParameterNullException("班组name不能为空",200);
		}else if(t.getEmps()==null || t.getEmps()==""){
			throw new ParameterNullException("员工号不能为空",200);
		}

		CMesEmpTeamT findTeamByid = dao.findTeamByid(t.getId());
		if(!findTeamByid.getName().equals(t.getName())){
			CMesEmpTeamT s = new CMesEmpTeamT();
			s.setName(t.getName());
			List<CMesEmpTeamT> findAllTeam = dao.findAllTeam(s);
			if(findAllTeam.size()>0){
				throw new NameRepeatException("班组名称重复",100);
			}
		}

		Integer updateTeam = dao.updateTeam(t);  //修改
		Integer delShiftTeam = dao.delShiftTeam(t.getId());  //根据班组id删除之前的中间表
		Integer addShiftTeam = dao.addShiftTeam(t.getId(), t.getShiftsTeamId());  //重新添加
		if(updateTeam<=0 || delShiftTeam<=0 || addShiftTeam<=0){
			throw new UnknownException("未知错误",300);
		}
		return 1;
	}

	@Override
	public Integer delTeam(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		Integer delTeam = dao.delTeam(id);
		Integer delShiftTeam = dao.delShiftTeam(id);
		if(delTeam<=0 || delShiftTeam<=0){
			throw new UnknownException("未知错误",300);
		}
		return 1;
	}

	@Override
	public List<CMesNoticeT> findAllNotice(CMesNoticeT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllNotice(t);
	}

	@Override
	public CMesNoticeT findNoticeByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findNoticeByid(id);
	}

	@Override
	public List<String> findTeams(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findTeams(id);
	}

	@Override
	public Integer addNotice(CMesNoticeT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getHead()==null || t.getHead()==""){
			throw new ParameterNullException("标题不能为空",200);
		}else if(t.getNoticeContent()==null || t.getNoticeContent()==""){
			throw new ParameterNullException("内容不能为空",200);
		}else if(t.getContacts()==null || t.getContacts()==""){
			throw new ParameterNullException("负责人不能为空",200);
		}
		return dao.addNotice(t);
	}

	@Override
	public Integer updateNotice(CMesNoticeT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getHead()==null || t.getHead()==""){
			throw new ParameterNullException("标题不能为空",200);
		}else if(t.getNoticeContent()==null || t.getNoticeContent()==""){
			throw new ParameterNullException("内容不能为空",200);
		}else if(t.getContacts()==null || t.getContacts()==""){
			throw new ParameterNullException("负责人不能为空",200);
		}

		return dao.updateNotice(t);
	}

	@Override
	public Integer delNotice(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delNotice(id);
	}

	@Override
	public List<CMesEmpTypeT> findAllEmpType(CMesEmpTypeT c) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllEmpType(c);
	}

	@Override
	public CMesEmpTypeT findEmpTypeByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findEmpTypeByid(id);
	}

	@Override
	public Integer addEmpType(CMesEmpTypeT t) throws ServicesException {
		// TODO Auto-generated method stub
		List<CMesEmpTypeT> findAllEmpType = dao.findAllEmpType(t);
		if(t.getEmpType()==null || t.getEmpType()==""){
			throw new ParameterNullException("员工类型不能为空",200);
		}else if(findAllEmpType.size()>0){
			throw new NameRepeatException("员工类型重复",100);
		}
		return dao.addEmpType(t);
	}

	@Override
	public Integer updateEmpType(CMesEmpTypeT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmpType()==null || t.getEmpType()==""){
			throw new ParameterNullException("员工类型不能为空",200);
		}

		CMesEmpTypeT findEmpTypeByid = dao.findEmpTypeByid(t.getId());
		if(!findEmpTypeByid.getEmpType().equals(t.getEmpType())){
			CMesEmpTypeT emp = new CMesEmpTypeT();
			emp.setEmpType(t.getEmpType());
			List<CMesEmpTypeT> findAllEmpType = dao.findAllEmpType(emp);
			if(findAllEmpType.size()>0){
				throw new NameRepeatException("员工类型重复",100);
			}
		}

		return dao.updateEmpType(t);
	}

	@Override
	public Integer delEmpType(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delEmpType(id);
	}

	@Override
	public List<CMesEmpT> findAllEmp(CMesEmpT c) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllEmp(c);
	}

	@Override
	public CMesEmpT findEmpByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findEmpByid(id);
	}

	@Override
	public Integer addEmp(CMesEmpT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmpName()==null || t.getEmpName()==""){
			throw new ParameterNullException("员工名称不能为空",200);
		}else if(t.getEmpNo()==null || t.getEmpNo()==""){
			throw new ParameterNullException("员工编号不能为空",200);
		}else if(t.getEmpTp()==null || t.getEmpTp()==""){
			throw new ParameterNullException("员工电话不能为空",200);
		}else if(t.getDt()==null){
			throw new ParameterNullException("入场时间不能为空",200);
		}else if(t.getEmpDepartment()==null || t.getEmpDepartment()==""){
			throw new ParameterNullException("所属部门不能为空",200);
		}
		CMesEmpT emp = new CMesEmpT();
		emp.setEmpNo(t.getEmpNo());
		List<CMesEmpT> findAllEmp = dao.findAllEmp(emp);
		if(findAllEmp.size()>0){
			throw new NameRepeatException("员工编号重复",100);
		}
		return dao.addEmp(t);
	}

	@Override
	public Integer updateEmp(CMesEmpT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmpName()==null || t.getEmpName()==""){
			throw new ParameterNullException("员工名称不能为空",200);
		}else if(t.getEmpNo()==null || t.getEmpNo()==""){
			throw new ParameterNullException("员工编号不能为空",200);
		}else if(t.getEmpTp()==null || t.getEmpTp()==""){
			throw new ParameterNullException("员工电话不能为空",200);
		}else if(t.getDt()==null){
			throw new ParameterNullException("入场时间不能为空",200);
		}else if(t.getEmpDepartment()==null || t.getEmpDepartment()==""){
			throw new ParameterNullException("所属部门不能为空",200);
		}

		CMesEmpT findEmpByid = dao.findEmpByid(t.getId());
		if(!findEmpByid.getEmpNo().equals(t.getEmpNo())){
			CMesEmpT emp = new CMesEmpT();
			emp.setEmpNo(t.getEmpNo());
			List<CMesEmpT> findAllEmp = dao.findAllEmp(emp);
			if(findAllEmp.size()>0){
				throw new NameRepeatException("员工编号重复",100);
			}
		}
		return dao.updateEmp(t);
	}

	@Override
	public Integer delEmp(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delEmp(id);
	}


}
