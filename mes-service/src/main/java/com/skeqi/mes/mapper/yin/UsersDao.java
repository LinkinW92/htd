package com.skeqi.mes.mapper.yin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
import com.skeqi.mes.pojo.PMesTesttimeT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.pojo.Routing;

@Repository
public interface UsersDao {

	/**
	 * 查询用户
	 * @return
	 */
	List<CMesUserT> findUserList(CMesUserT dx);

	//开班
	void toeditstatus(@Param("id")String id,@Param("status")String status);

	void upkeypart(Map<String, Object> map);

	void upbolt(Map<String, Object> map);

	void upleakage(Map<String, Object> map);

	/**
	 * 鐢ㄦ埛鍒楄〃
	 */
	List<CMesUserT> userList(Map<String, Object> map);
	/**
	 * 鑾峰彇鐢ㄦ埛琛ㄦ渶澶х紪鍙�
	 */
	int getMaxNumber();
	/**
	 * 娣诲姞鐢ㄦ埛淇℃伅
	 */
	void addUser(Map<String, Object> map);
	/**
	 * 鍒犻櫎鐢ㄦ埛
	 */
	void delUser(Map<String, Object> map);
	/**
	 * 瑙掕壊鍒楄〃
	 */
	List<RoleT> roleList(Map<String, Object> map);
	/**
	 * 鐢ㄦ埛淇℃伅淇敼
	 */
	void updateUser(Map<String, Object> map);

	/**
	 * 娣诲姞瑙掕壊
	 */
	void addRole(Map<String, Object> map);
	/**
	 * 娣诲姞瑙掕壊鑿滃崟
	 */
	void addRoleAndMenu(Map<String, Object> map);
	/**
	 * 鏌ヨ鑿滃崟鍒楄〃
	 */
	List<CMesMenuT> menuList(Map<String, Object> map);

	Integer finMaxIdByRole();

	List<CMesRoleMenuT> roleAndMenuList(Map<String, Object> map);
	/**
	 * 淇敼瑙掕壊淇℃伅
	 */
	void editRole(Map<String, Object> map);
	/**
	 * 鍒犻櫎瑙掕壊鑿滃崟琛�
	 */
	void removeRoleAndMenuByRoleId(Map<String, Object> map);
	/**
	 * 鍒犻櫎瑙掕壊琛�
	 */
	void removeRoleByRoleId(Map<String, Object> map);
	/**
	 * 鏌ヨ宸ヤ綅鍒楄〃淇℃伅
	 */
	List<CMesStationT> stationList(Map<String, Object> map);
	/**
	 * 娣诲姞浜х嚎
	 */
	void addLine(Map<String, Object> map);
	/**
	 * 浜х嚎鍒楄〃
	 */
	List<CMesLineT> lineList(Map<String, Object> map);
	/**
	 * 淇敼浜х嚎淇℃伅
	 */
	void editLine(Map<String, Object> map);
	/**
	 * 鍒犻櫎宸ヤ綅
	 */
	void delStation(Map<String, Object> map);
	/**
	 * 鍒犻櫎浜х嚎
	 */
	void delLine(Map<String, Object> map);
	/**
	 * 娣诲姞宸ヤ綅
	 */
	void addStation(Map<String, Object> map);
	/**
	 * 淇敼宸ヤ綅淇℃伅
	 */
	void editStation(Map<String, Object> map);
	/**
	 * 娣诲姞浜х嚎涔嬪墠楠岃瘉鏄惁瀛樺湪
	 */
	Integer countLineByLineName(Map<String, Object> map);
	/**
	 * 娣诲姞宸ヤ綅涔嬪墠楠岃瘉鏄惁瀛樺湪
	 */
	Integer countStationByStationName(Map<String, Object> map);

	List<CMesProductionT> productionList(Map<String, Object> map);


	List<Routing> GetRoute(Map<String, Object> map);

	void DeleteRoute_Routing_ByproductName(Map<String, Object> map);

	void DeleteRoute_Production_ByproductName(Map<String, Object> map);
	/**
	 * 载具列表
	 */
	List<CMesTrayT> carrierList(Map<String, Object> map);
	/**
	 * 添加载具
	 * @param map
	 */
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
	/**
	 * 通过角色ID查询用户数量
	 */
	int countUserByRoleId(Map<String, Object> map);
	/**
	 * 通过工位ID查询程序注册
	 */
	int countRegisterByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询料单部件
	 */
	int countMaterialListDetailByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询设备
	 */
	int countDeviceByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询物料信息
	 */
	int countMaterialMsgByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询螺栓信息
	 */
	int countBoltByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询气密性信息
	 */
	int countLeakageByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询其他信息
	 */
	int countOtherByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询产品配方中间表
	 */
	int countProductionRecipeByStationId(Map<String, Object> map);
	/**
	 * 通过工位ID查询生成条码
	 */
	int countBarCodeByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询程序注册
	 */
	int countRegisterByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询载具格式
	 */
	int countTrayByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询工艺配置
	 */
	int countProductionProcessByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询料单
	 */
	int countMaterialListByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询制造参数清单
	 */
	int countManufactureParametersByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询总成类型管理
	 */
	int countAssemblyTypeByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询设备管理
	 */
	int countDeviceByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询标签管理
	 */
	int countLabelManagerByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询计划
	 */
	int countPlanByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询班次
	 */
	int countShiftsTeamByLineId(Map<String, Object> map);
	/**
	 * 通过产线ID查询员工
	 */
	int countEmpByLineId(Map<String, Object> map);
	int countRoleByName(Map<String, Object> map);
	int countStationByLineId(Map<String, Object> map);
	int countIndexById(Map<String, Object> map);
	List<PMesStationPassLineT> GetAllLine();
	List<PMesStaionT> GetAllLineByStation(Map<String, Object> map);
	List<PMesStationPassT> GetAllMsgByLineandStation(Map<String, Object> map);
	List<PMesTesttimeT> GetAllStationPassTime(Map<String, Object> map);
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
