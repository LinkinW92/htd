package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.UsersDao;
import com.skeqi.mes.pojo.CMesInterface;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTrayT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PMesStaionT;
import com.skeqi.mes.pojo.PMesStationPassLineT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.pojo.PMesTesttimeT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.pojo.Routing;
@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersDao usersDao;

	@Override
	public List<CMesUserT> findUserList(CMesUserT dx) {
		// TODO Auto-generated method stub
		return usersDao.findUserList(dx);
	}

	@Override
	public List<CMesUserT> userList(Map<String, Object> map) {
		return usersDao.userList(map);
	}

	@Transactional
	@Override
	public void addUser(Map<String, Object> map) {
		usersDao.addUser(map);

	}

	@Override
	public int getMaxNumber() {
		return usersDao.getMaxNumber();
	}

	@Transactional
	@Override
	public void delUser(Map<String, Object> map) {
		usersDao.delUser(map);
	}

	@Override
	public List<RoleT> roleList(Map<String, Object> map) {
		return usersDao.roleList(map);
	}

	@Transactional
	@Override
	public void updateUser(Map<String, Object> map) {
		usersDao.updateUser(map);
	}

	@Transactional
	@Override
	public void addRole(Map<String, Object> map) {
		usersDao.addRole(map);
	}

	@Override
	public List<CMesMenuT> menuList(Map<String, Object> map) {
		return usersDao.menuList(map);
	}

	@Transactional
	@Override
	public void addRoleAndMenu(Map<String, Object> map) {
		usersDao.addRoleAndMenu(map);
	}

	@Override
	public Integer finMaxIdByRole() {
		return usersDao.finMaxIdByRole();
	}

	@Override
	public List<CMesRoleMenuT> roleAndMenuList(Map<String, Object> map) {
		return usersDao.roleAndMenuList(map);
	}

	@Transactional
	@Override
	public void editRole(Map<String, Object> map) {
		usersDao.editRole(map);
	}

	@Transactional
	@Override
	public void removeRoleAndMenuByRoleId(Map<String, Object> map) {
		usersDao.removeRoleAndMenuByRoleId(map);
	}

	@Transactional
	@Override
	public void removeRoleByRoleId(Map<String, Object> map) {
		usersDao.removeRoleByRoleId(map);
	}

	@Override
	public List<CMesStationT> stationList(Map<String, Object> map) {
		return usersDao.stationList(map);
	}

	@Transactional
	@Override
	public void addLine(Map<String, Object> map) {
		usersDao.addLine(map);
	}

	@Override
	public List<CMesLineT> lineList(Map<String, Object> map) {
		return usersDao.lineList(map);
	}

	@Transactional
	@Override
	public void editLine(Map<String, Object> map) {
		usersDao.editLine(map);
	}

	@Transactional
	@Override
	public void delStation(Map<String, Object> map) {
		usersDao.delStation(map);
	}

	@Transactional
	@Override
	public void delLine(Map<String, Object> map) {
		usersDao.delLine(map);
	}

	@Transactional
	@Override
	public void addStation(Map<String, Object> map) {
		usersDao.addStation(map);
	}

	@Transactional
	@Override
	public void editStation(Map<String, Object> map) {
		usersDao.editStation(map);
	}

	@Override
	public Integer countLineByLineName(Map<String, Object> map) {
		return usersDao.countLineByLineName(map);
	}

	@Override
	public Integer countStationByStationName(Map<String, Object> map) {
		return usersDao.countStationByStationName(map);
	}

	@Override
	public List<CMesProductionT> productionList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.productionList(map);
	}

	@Transactional
	@Override
	public List<Routing> GetRoute(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.GetRoute(map);
	}

	@Transactional
	@Override
	public void DeleteRoute_Routing_ByproductName(Map<String, Object> map) {
		usersDao.DeleteRoute_Routing_ByproductName(map);
	}

	@Transactional
	@Override
	public void DeleteRoute_Production_ByproductName(Map<String, Object> map) {
		usersDao.DeleteRoute_Production_ByproductName(map);
	}

	@Override
	public List<CMesTrayT> carrierList(Map<String, Object> map) {
		return usersDao.carrierList(map);
	}

	@Transactional
	@Override
	public void addCarrier(Map<String, Object> map) {
		usersDao.addCarrier(map);
	}

	@Override
	public Integer countCarrierByName(Map<String, Object> map) {
		return usersDao.countCarrierByName(map);
	}

	@Transactional
	@Override
	public void editCarrier(Map<String, Object> map) {
		usersDao.editCarrier(map);
	}

	@Transactional
	@Override
	public void delCarrier(Map<String, Object> map) {
		usersDao.delCarrier(map);
	}

	@Transactional
	@Override
	public void relieveDataForBoltP(Map<String, Object> map) {
		usersDao.relieveDataForBoltP(map);
	}

	@Transactional
	@Override
	public void relieveDataForKeypartP(Map<String, Object> map) {
		usersDao.relieveDataForKeypartP(map);
	}

	@Transactional
	@Override
	public void relieveDataForLeakageP(Map<String, Object> map) {
		usersDao.relieveDataForLeakageP(map);
	}

	@Transactional
	@Override
	public List<CMesStationT> findStationByLine(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.findStationByLine(map);
	}

	@Transactional
	@Override
	public List<CMesLineT> findAllLine() {
		// TODO Auto-generated method stub
		return usersDao.findAllLine();
	}

	@Transactional
	@Override
	public int findIDByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.findIDByName(map);
	}

	@Override
	public List<RMesBolt> getBoltById(Map<String, Object> map) {
		return usersDao.getBoltById(map);
	}

	@Override
	public List<RMesKeypart> getKeypartById(Map<String, Object> map) {
		return usersDao.getKeypartById(map);
	}

	@Override
	public List<RMesLeakage> getLeakageById(Map<String, Object> map) {
		return usersDao.getLeakageById(map);
	}

	@Transactional
	@Override
	public void addBolt(Map<String, Object> map) {
		usersDao.addBolt(map);
	}

	@Transactional
	@Override
	public void addKeypart(Map<String, Object> map) {
		usersDao.addKeypart(map);
	}

	@Transactional
	@Override
	public void addLeakage(Map<String, Object> map) {
		usersDao.addLeakage(map);
	}

	@Transactional
	@Override
	public int selectIDByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.selectIDByName(map);
	}

	@Transactional
	@Override
	public void addMenuCrud(Map<String, Object> map) {
		usersDao.addMenuCrud(map);
	}

	@Transactional
	@Override
	public void delMenuCrudByRoleId(Map<String, Object> map) {
		usersDao.delMenuCrudByRoleId(map);
	}

	@Override
	public List<CMesMenuCrudT> listMenuCrudByRoleId(Map<String, Object> map) {
		return usersDao.listMenuCrudByRoleId(map);
	}

	@Transactional
	@Override
	public int getCountByID(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.getCountByID(map);
	}

	@Transactional
	@Override
	public void deleteByID(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countUserByRoleId(Map<String, Object> map) {
		return usersDao.countUserByRoleId(map);
	}

	@Override
	public int countRegisterByStationId(Map<String, Object> map) {
		return usersDao.countRegisterByStationId(map);
	}

	@Override
	public int countMaterialListDetailByStationId(Map<String, Object> map) {
		return usersDao.countMaterialListDetailByStationId(map);
	}

	@Override
	public int countDeviceByStationId(Map<String, Object> map) {
		return usersDao.countDeviceByStationId(map);
	}

	@Override
	public int countMaterialMsgByStationId(Map<String, Object> map) {
		return usersDao.countMaterialMsgByStationId(map);
	}

	@Override
	public int countBoltByStationId(Map<String, Object> map) {
		return usersDao.countBoltByStationId(map);
	}

	@Override
	public int countLeakageByStationId(Map<String, Object> map) {
		return usersDao.countLeakageByStationId(map);
	}

	@Override
	public int countOtherByStationId(Map<String, Object> map) {
		return usersDao.countOtherByStationId(map);
	}

	@Override
	public int countProductionRecipeByStationId(Map<String, Object> map) {
		return usersDao.countProductionRecipeByStationId(map);
	}

	@Override
	public int countRegisterByLineId(Map<String, Object> map) {
		return usersDao.countRegisterByLineId(map);
	}

	@Override
	public int countTrayByLineId(Map<String, Object> map) {
		return usersDao.countRegisterByLineId(map);
	}

	@Override
	public int countProductionProcessByLineId(Map<String, Object> map) {
		return usersDao.countProductionProcessByLineId(map);
	}

	@Override
	public int countMaterialListByLineId(Map<String, Object> map) {
		return usersDao.countMaterialListByLineId(map);
	}

	@Override
	public int countManufactureParametersByLineId(Map<String, Object> map) {
		return usersDao.countManufactureParametersByLineId(map);
	}

	@Override
	public int countAssemblyTypeByLineId(Map<String, Object> map) {
		return usersDao.countAssemblyTypeByLineId(map);
	}
	@Override
	public int countDeviceByLineId(Map<String, Object> map) {
		return usersDao.countDeviceByLineId(map);
	}
	@Override
	public int countLabelManagerByLineId(Map<String, Object> map) {
		return usersDao.countLabelManagerByLineId(map);
	}
	@Override
	public int countPlanByLineId(Map<String, Object> map) {
		return usersDao.countPlanByLineId(map);
	}
	@Override
	public int countShiftsTeamByLineId(Map<String, Object> map) {
		return usersDao.countShiftsTeamByLineId(map);
	}
	@Override
	public int countEmpByLineId(Map<String, Object> map) {
		return usersDao.countEmpByLineId(map);
	}
	@Override
	public int countBarCodeByLineId(Map<String, Object> map) {
		return usersDao.countBarCodeByLineId(map);
	}

	@Override
	public int countRoleByName(Map<String, Object> map) {
		return usersDao.countRoleByName(map);
	}

	@Override
	public int countStationByLineId(Map<String, Object> map) {
		return usersDao.countStationByLineId(map);
	}

	@Override
	public int countIndexById(Map<String, Object> map) {
		return usersDao.countIndexById(map);
	}

	@Transactional
	@Override
	public List<PMesStationPassLineT> GetAllLine() {
		// TODO Auto-generated method stub
		return usersDao.GetAllLine();
	}

	@Transactional
	@Override
	public List<PMesStaionT> GetAllLineByStation(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.GetAllLineByStation( map);
	}

	@Transactional
	@Override
	public List<PMesStationPassT> GetAllMsgByLineandStation(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.GetAllMsgByLineandStation(map);
	}

	@Override
	public int GetLineIDByName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return usersDao.GetLineIDByName(map);
	}

	@Override
	public List<CMesInterface> interfaceList(Map<String, Object> map) {
		return usersDao.interfaceList(map);
	}

	@Override
	public Integer countInterfaceByName(Map<String, Object> map) {
		return usersDao.countInterfaceByName(map);
	}

	@Override
	public void addInterface(Map<String, Object> map) {
		usersDao.addInterface(map);
	}

	@Override
	public void delInterface(Map<String, Object> map) {
		usersDao.delInterface(map);
	}

	@Override
	public void editInterface(Map<String, Object> map) {
		usersDao.editInterface(map);
	}

	@Override
	public void delBolt(Map<String, Object> map) {
		usersDao.delBolt(map);
	}

	@Override
	public void delKeypart(Map<String, Object> map) {
		usersDao.delKeypart(map);
	}

	@Override
	public void delLeakage(Map<String, Object> map) {
		usersDao.delLeakage(map);
	}


	@Override
	public void addToReasonsBolt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.addToReasonsBolt(map);
	}

	@Override
	public void addToReasonsKeypart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.addToReasonsKeypart(map);
	}

	@Override
	public void addToReasonsLeakage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.addToReasonsLeakage(map);
	}

	@Override
	public void upkeypart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.upkeypart(map);
	}

	@Override
	public void upbolt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.upbolt(map);
	}

	@Override
	public void upleakage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		usersDao.upleakage(map);
	}

	@Override
	public void toeditstatus(String id, String status) {
		// TODO Auto-generated method stub
		usersDao.toeditstatus(id, status);
	}

}
