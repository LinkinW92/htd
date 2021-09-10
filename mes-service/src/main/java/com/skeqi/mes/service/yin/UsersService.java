package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesInterface;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTrayT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.PMesStaionT;
import com.skeqi.mes.pojo.PMesStationPassLineT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.pojo.Routing;

public interface UsersService {

	/**
	 * 查询用户
	 * @return
	 */
	List<CMesUserT> findUserList(CMesUserT dx);
	void toeditstatus(String id,String status);
	void upkeypart(Map<String, Object> map);

	void upbolt(Map<String, Object> map);

	void upleakage(Map<String, Object> map);
	List<CMesUserT> userList(Map<String, Object> map);
	void addUser(Map<String, Object> map);
	int getMaxNumber();
	void delUser(Map<String, Object> map);
	List<RoleT> roleList(Map<String, Object> map);
	void updateUser(Map<String, Object> map);
	void addRole(Map<String, Object> map);
	List<CMesMenuT> menuList(Map<String, Object> map);
	void addRoleAndMenu(Map<String, Object> map);
	Integer finMaxIdByRole();
	List<CMesRoleMenuT> roleAndMenuList(Map<String, Object> map);
	void editRole(Map<String, Object> map);
	void removeRoleAndMenuByRoleId(Map<String, Object> map);
	void removeRoleByRoleId(Map<String, Object> map);
	List<CMesStationT> stationList(Map<String, Object> map);
	void addLine(Map<String, Object> map);
	List<CMesLineT> lineList(Map<String, Object> map);
	void editLine(Map<String, Object> map);
	void delStation(Map<String, Object> map);
	void delLine(Map<String, Object> map);
	void addStation(Map<String, Object> map);
	void editStation(Map<String, Object> map);
	Integer countLineByLineName(Map<String, Object> map);
	Integer countStationByStationName(Map<String, Object> map);
	List<CMesProductionT> productionList(Map<String, Object> map);
	List<Routing> GetRoute(Map<String, Object> map);
	void DeleteRoute_Routing_ByproductName(Map<String, Object> map);
	void DeleteRoute_Production_ByproductName(Map<String, Object> map);
	List<CMesTrayT> carrierList(Map<String, Object> map);
	void addCarrier(Map<String, Object> map);
	Integer countCarrierByName(Map<String, Object> map);
	void editCarrier(Map<String, Object> map);
	void delCarrier(Map<String, Object> map);
	void relieveDataForBoltP(Map<String, Object> map);
	void relieveDataForKeypartP(Map<String, Object> map);
	void relieveDataForLeakageP(Map<String, Object> map);
	List<CMesStationT>  findStationByLine(Map<String, Object> map);
	List<CMesLineT>  findAllLine();
	int findIDByName(Map<String, Object> map);
	List<RMesBolt> getBoltById(Map<String, Object> map);
	List<RMesKeypart> getKeypartById(Map<String, Object> map);
	List<RMesLeakage> getLeakageById(Map<String, Object> map);
	void addBolt(Map<String, Object> map);
	void addKeypart(Map<String, Object> map);
	void addLeakage(Map<String, Object> map);
	int selectIDByName(Map<String, Object> map);
	void addMenuCrud(Map<String, Object> map);
	void delMenuCrudByRoleId(Map<String, Object> map);
	List<CMesMenuCrudT> listMenuCrudByRoleId(Map<String, Object> map);
	int getCountByID(Map<String, Object> map);
	void deleteByID(Map<String, Object> map);
	int countUserByRoleId(Map<String, Object> map);
	int countRegisterByStationId(Map<String, Object> map);
	int countMaterialListDetailByStationId(Map<String, Object> map);
	int countDeviceByStationId(Map<String, Object> map);
	int countMaterialMsgByStationId(Map<String, Object> map);
	int countBoltByStationId(Map<String, Object> map);
	int countLeakageByStationId(Map<String, Object> map);
	int countOtherByStationId(Map<String, Object> map);
	int countProductionRecipeByStationId(Map<String, Object> map);
	int countRegisterByLineId(Map<String, Object> map);
	int countTrayByLineId(Map<String, Object> map);
	int countProductionProcessByLineId(Map<String, Object> map);
	int countMaterialListByLineId(Map<String, Object> map);
	int countManufactureParametersByLineId(Map<String, Object> map);
	int countAssemblyTypeByLineId(Map<String, Object> map);
	int countDeviceByLineId(Map<String, Object> map);
	int countLabelManagerByLineId(Map<String, Object> map);
	int countPlanByLineId(Map<String, Object> map);
	int countShiftsTeamByLineId(Map<String, Object> map);
	int countEmpByLineId(Map<String, Object> map);
	int countBarCodeByLineId(Map<String, Object> map);
	int countRoleByName(Map<String, Object> map);
	int countStationByLineId(Map<String, Object> map);
	int countIndexById(Map<String, Object> map);
	List<PMesStationPassLineT> GetAllLine();
	List<PMesStaionT> GetAllLineByStation(Map<String, Object> map);
	List<PMesStationPassT> GetAllMsgByLineandStation(Map<String, Object> map);
	int GetLineIDByName(Map<String, Object> map);
	List<CMesInterface> interfaceList(Map<String, Object> map);
	Integer countInterfaceByName(Map<String, Object> map);
	void addInterface(Map<String, Object> map);
	void delInterface(Map<String, Object> map);
	void editInterface(Map<String, Object> map);
	void delBolt(Map<String, Object> map);
	void delKeypart(Map<String, Object> map);
	void delLeakage(Map<String, Object> map);
	void addToReasonsBolt(Map<String, Object> map);
	void addToReasonsKeypart(Map<String, Object> map);
	void addToReasonsLeakage(Map<String, Object> map);

}
