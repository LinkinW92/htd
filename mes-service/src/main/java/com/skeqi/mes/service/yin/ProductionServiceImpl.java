package com.skeqi.mes.service.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.ProductionDao;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesPlanT;

@Service
public class ProductionServiceImpl implements ProductionService {
	@Autowired
	ProductionDao productionDao;

	@Override
	public List<CMesShiftsTeamT> shiftsTeamList(Map<String, Object> map) {
		return productionDao.shiftsTeamList(map);
	}

	@Transactional
	@Override
	public void addShiftsTeam(Map<String, Object> map) {
		productionDao.addShiftsTeam(map);
	}

	@Transactional
	@Override
	public void editShiftsTeam(Map<String, Object> map) {
		productionDao.editShiftsTeam(map);
	}

	@Transactional
	@Override
	public void delShift(Map<String, Object> map) {
		productionDao.delShift(map);
	}

	@Override
	public List<RMesPlanT> planList() {
		return productionDao.planList();
	}

	@Override
	public Integer maxIdByShift() {
		return productionDao.maxIdByShift();
	}

	@Transactional
	@Override
	public void addPlanTeam(Map<String, Object> map) {
		productionDao.addPlanTeam(map);
	}

	@Transactional
	@Override
	public void updatePlanTeamByShiftsTeamId(Map<String, Object> map) {
		productionDao.updatePlanTeamByShiftsTeamId(map);
	}

	@Override
	public Integer countShiftByName(Map<String, Object> map) {
		return productionDao.countShiftByName(map);
	}

	@Transactional
	@Override
	public void delPlanTeam(Map<String, Object> map) {
		productionDao.delPlanTeam(map);
	}

	@Override
	public List<CMesEmpTeamT> empTeamList(Map<String, Object> map) {
		return productionDao.empTeamList(map);
	}

	@Transactional
	@Override
	public void addEmpTeam(Map<String, Object> map) {
		productionDao.addEmpTeam(map);
	}

	@Override
	public List<CMesLineT> lineList() {
		return productionDao.lineList();
	}

	@Override
	public Integer countEmpTeamByName(Map<String, Object> map) {
		return productionDao.countEmpTeamByName(map);
	}

	@Transactional
	@Override
	public void delEmpTeam(Map<String, Object> map) {
		productionDao.delEmpTeam(map);
	}

	@Transactional
	@Override
	public void editEmpTeam(Map<String, Object> map) {
		productionDao.editEmpTeam(map);
	}

	@Transactional
	@Override
	public void addShiftEmpsTeam(HashMap<String, Object> map) {
		productionDao.addShiftEmpsTeam(map);
	}

	@Override
	public Integer getMaxIdfromSET() {
		return productionDao.getMaxIdfromSET();
	}

	@Transactional
	@Override
	public void editShiftsTeamByEmpTeamId(Map<String, Object> map) {
		productionDao.editShiftsTeamByEmpTeamId(map);
	}

	@Transactional
	@Override
	public void delShiftEmpTeam(Map<String, Object> map) {
		productionDao.delShiftEmpTeam(map);
	}

	@Override
	public List<CMesEmpT> empList(Map<String, Object> map) {
		return productionDao.empList(map);
	}

	@Override
	public Integer countEmpByEmpNo(Map<String, Object> map) {
		return productionDao.countEmpByEmpNo(map);
	}

	@Transactional
	@Override
	public void addEmp(Map<String, Object> map) {
		productionDao.addEmp(map);
	}

	@Transactional
	@Override
	public void editEmp(HashMap<String, Object> map) {
		productionDao.editEmp(map);
	}

	@Transactional
	@Override
	public void delEmp(HashMap<String, Object> map) {
		productionDao.delEmp(map);
	}

	@Override
	public List<CMesEmpTypeT> empTypeList(Map<String, Object> map) {
		return productionDao.empTypeList(map);
	}

	@Override
	public void addEmpType(HashMap<String, Object> map) {
		productionDao.addEmpType(map);
	}

	@Override
	public void editEmpType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		productionDao.editEmpType(map);
	}

	@Override
	public void delEmpType(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		productionDao.delEmpType(map);
	}

	@Override
	public List<CMesStationT> stationList(Integer lineId) {
		return productionDao.stationList(lineId);
	}

	@Override
	public CMesEmpT findById(Integer id) {
		return productionDao.findById(id);
	}

}
