package com.skeqi.mes.mapper.yin;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ReportDao {

	@Select("SELECT sn FROM p_mes_tracking_t order by id desc limit 1")
	String firstSn();
	/**
	 * 鎬绘垚鐘舵�佽〃锛堟案涔咃級
	 */
	List<PTrackingT> ptrackingList(Map<String, Object> map);

	@Select("select PRODUCTION_NAME from c_mes_production_t where id = #{production_id}")
	String getProductionName(int production_id);
	/**
	 * 鎬绘垚鐘舵�佽〃锛堜复鏃讹級
	 */
	List<RTrackingT> rtrackingList(Map<String, Object> map);
	/**
	 * 铻烘爴淇℃伅
	 */
	List<PMesBoltT> boltList(Map<String, Object> map);
	/**
	 * 鐗╂枡淇℃伅
	 */
	List<PMesKeypartT> keypartList(Map<String, Object> map);
	/**
	 * 姘斿瘑鎬т俊鎭�
	 */
	List<PMesLeakageT> leakageList(Map<String, Object> map);

	List<RMesBolt> rboltList(Map<String, Object> map);
	/**
	 * 鐗╂枡淇℃伅
	 */
	List<RMesKeypart> rkeypartList(Map<String, Object> map);
	/**
	 * 姘斿瘑鎬т俊鎭�
	 */
	List<RMesLeakage> rleakageList(Map<String, Object> map);


	/**
	 * 閰嶆柟鏄庣粏
	 */
	List<CMesRecipeDatilT> recipeDatilLists(Map<String, Object> map);
	/**
	 * 鑾峰彇浜у搧鏍￠獙瑙勫垯
	 */
	List<CMesProductionT> getProductionVr();
	/**
	 * 娣诲姞閰嶆柟鏄庣粏
	 */
	void saveRecipeDatil(Map<String, Object> map);

	List<CMesRecipeT> getRecipeIdByName(Map<String, Object> map);

	List<CMesStationT> getStationByName(Map<String, Object> map);
	List<CMesProductionT> getProductionByName(Map<String, Object> map);
	List<CMesProductionT> getProductionByPackPn(Map<String, Object> map);
	/**
	 * 閫氳繃宸ヤ綅鍚嶇О鏌ヨ璁板綍涓暟
	 */
	int countStation(String stationName);
	/**
	 * 鏌ヨPACKPN鏄惁瀛樺湪
	 */
	int countPackPn(String packPn);
	/**
	 * 鏌ヨ浜у搧鍚嶇О鏄惁瀛樺湪
	 */
	int countProductionName(String productionName);
	/**
	 * 鍏宠仈浜у搧閰嶆柟宸ヤ綅
	 */
	void saveProductionRecipe(Map<String, Object> map);
	/**
	 * 閫氳繃宸ヤ綅鍜屼骇鍝� 鍦ㄥ叧鑱旇〃鏌ヨ閰嶆柟ID
	 */
	List<CMesProductionRecipeT> productionRecipe(Map<String, Object> map);
//	/**
//	 * 閫氳繃浜у搧鍚嶇О鏌ヨID
//	 */
//	List<CMesProductionT> findProductionIdByName(Map<String, Object> map);
//	/**
//	 * 閫氳繃宸ヤ綅鍚嶇О鏌ヨID
//	 */
//	List<CMesStationT> findStationIdByName(Map<String, Object> map);
	/**
	 * 鍒涘缓閰嶆柟
	 */
	void createRecipe(Map<String, Object> map);
	/**
	 * 閫氳繃閰嶆柟ID绉婚櫎閰嶆柟鏄庣粏
	 */
	void removeRecipeDatilByRecipeId(Map<String, Object> map);
	/**
	 * 娣诲姞浜у搧
	 */
	void createProduction(Map<String, Object> map);
	/**
	 * 閫氳繃閰嶆柟绫诲瀷鍚嶇О鏌ヨ閰嶆柟淇℃伅
	 */
	List<CMesRecipeTypeT> getRecipeTypeNameByStepCategory(Map<String, Object> map);
	/**
	 * 鎵嬪姩涓婁紶鏁版嵁鍒楄〃
	 */
	List<RUploadDataT> uploadDataList(Map<String, Object> map);
	List<RMesBolt> boltListR(Map<String, Object> map);
	List<RMesKeypart> keypartListR(Map<String, Object> map);
	List<RMesLeakage> leakageListR(Map<String, Object> map);

	List<RAsmElectricDetection> rAsmElectricDetectionList(Map<String, Object> map);
	List<PChargedischarge> pChargedischargeList(Map<String, Object> map);
	List<RAsmEol> rAsmEolList(Map<String, Object> map);
	List<CMesXXT> getXXT(Map<String, Object> map);

	String getSn(String materialName);
	String getPSn(String materialName);

	List<PMesEolT> findAllEol(String sn);
	List<PMesDischargeT> findAllDischargeT(String sn);

	List<PMesWeightT> findAllWeight(@Param("sn") String sn);

	List<PMesStationPassT> findAllStationPass(@Param("sn")String sn);
}
