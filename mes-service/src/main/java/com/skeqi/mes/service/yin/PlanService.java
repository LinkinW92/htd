package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesPlanPrintT;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

public interface PlanService {
	public List<CMesRecipeT> findRecipe(String id);
	public List<PMesPlanT> planList(Map<String, Object> map);
	public List<RMesPlanT> rplanList(Map<String, Object> map);
	public List<CMesLineT> lineList();
	public List<CMesProductionT> productionList();
	public void addPlan(Map<String, Object> map);
	public List<RMesPlanT> findPlanByPlanSerialno(String serialno);
	public void addPlanForEver(RMesPlanT plan);
	public Integer getMaxLevel();
	public List<RMesPlanT> getPlanByLevel(Integer level);
	public void updateAllLevelByPlanId(@Param("levels")Integer levels);
	public void deletePlanByPlanId(@Param("planId")String planId);
	public void updateLevelByPlanId(String planSerialno,Integer levels);
	public void updateFlagByPlanId(@Param("planId")String planId,@Param("flag")Integer flag);
	public void updateBarCodeFlagByPlanId(@Param("planId")Integer planId,@Param("flag")Integer flag);
	public RMesPlanT getPlanById(String planId);
	public void addbarCode(Map<String, Object> map);
	public List<RMesPlanPrintT> getPlanPrintById(String planId);
	public List<CMesRecipeDatilT> getRecipeDatil(Map<String, Object> map);
	public void deleteBomDetail(Map<String, Object> map);
	public List<CMesRecipeT> recipeList();
	public List<CMesStationT> stationList();
	public void addRecipeDetail(Map<String, Object> map);
	public Integer findMaxStepNoByRecipeId(int recipeId);
	public void editRecipeDetail(Map<String, Object> map);
	public void updateStepNoOrderBy(Map<String, Object> map);
	public void updateStepNoOrderBy2(Map<String, Object> map);
	public List<CMesRecipeDatilT> getRecipeDatilByStepNo(Map<String, Object> map);
	public List<CMesRecipeTypeT> recipeTypeList(Map<String, Object> map);
	void addBarCodeToPPlanPrint(Map<String, Object> map);
	void delBarCode(Map<String, Object> map);
	public List<RMesPlanPrintT> getMaxPlanPrintById(Map<String, Object> map);
	public List<CMesProductionT> getProductionByType(Map<String, Object> map);
	public List<RMesPlanT> findPlanByPlanName(String planSerialno);
	public List<RMesPlanPrintT> getPlanPrintBySn(Map<String, Object> map2);
	public List<CMesProductionT> productionLists(Map<String, Object> map1);
	public int getMaxSerialNo(Map<String, Object> map);
	public List<RMesWorkorderDetailT> workorderDetailList(Map<String, Object> map);
	public void addPlanDetail(Map<String, Object> map);
	public void editPlanDetail(Map<String, Object> map);
	public void delPlanDetail(Map<String, Object> map);
	public Integer getMaxLevelNoByPlanId(Map<String, Object> map);
	public void editOtherPlanDetailLevelNo(Map<String, Object> map);
	public void editLevelNo(Map<String, Object> map);
	public List<PMesPlanPrintT> pMesPlanPrintList(Map<String, Object> map);
	public void updateBarCodeFlagByWorkOrderId(Integer planId, String flag);
	public List<RMesWorkorderDetailT> findWorkOrderByProductionId(Map<String, Object> map);
}
