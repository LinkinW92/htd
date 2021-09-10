package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.PlanDao;
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

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	PlanDao planDao;

	@Override
	public List<PMesPlanT> planList(Map<String, Object> map){
		return planDao.planList(map);
	}

	@Override
	public List<RMesPlanT> rplanList(Map<String, Object> map) {
		return planDao.rplanList(map);
	}

	@Override
	public List<CMesLineT> lineList() {
		return planDao.lineList();
	}

	@Override
	public List<CMesProductionT> productionList() {
		return planDao.productionList();
	}

	@Transactional
	@Override
	public void addPlan(Map<String, Object> map) {
		planDao.addPlan(map);
	}

	@Override
	public Integer getMaxLevel() {
		return planDao.getMaxLevel();
	}

	@Override
	public List<RMesPlanT> getPlanByLevel(Integer level) {
		return planDao.getPlanByLevel(level);
	}

	@Transactional
	@Override
	public void updateLevelByPlanId(String planSerialno,Integer levels) {
		planDao.updateLevelByPlanId(planSerialno,levels);
	}

	@Transactional
	@Override
	public void updateFlagByPlanId(String planId, Integer flag) {
		planDao.updateFlagByPlanId(planId, flag);
	}

	@Transactional
	@Override
	public void updateAllLevelByPlanId(Integer levels) {
		planDao.updateAllLevelByPlanId(levels);
	}

	@Transactional
	@Override
	public void deletePlanByPlanId(String planId) {
		planDao.deletePlanByPlanId(planId);
	}

	@Transactional
	@Override
	public void addPlanForEver(RMesPlanT plan) {
		planDao.addPlanForEver(plan);
	}

	@Override
	public RMesPlanT getPlanById(String planId) {
		return planDao.getPlanById(planId);
	}

	@Transactional
	@Override
	public void addbarCode(Map<String, Object> map) {
		planDao.addbarCode(map);
	}

	@Transactional
	@Override
	public void updateBarCodeFlagByPlanId(Integer planId, Integer flag) {
		planDao.updateBarCodeFlagByPlanId(planId, flag);
	}

	@Override
	public List<RMesPlanPrintT> getPlanPrintById(String planId) {
		return planDao.getPlanPrintById(planId);
	}

	@Override
	public List<CMesRecipeDatilT> getRecipeDatil(Map<String, Object> map) {
		return planDao.getRecipeDatil(map);
	}

	@Transactional
	@Override
	public void deleteBomDetail(Map<String, Object> map) {
		planDao.deleteBomDetail(map);
	}

	@Override
	public List<CMesRecipeT> recipeList() {
		return planDao.recipeList();
	}

	@Override
	public List<CMesStationT> stationList() {
		return planDao.stationList();
	}

	@Transactional
	@Override
	public void addRecipeDetail(Map<String, Object> map) {
		planDao.addRecipeDetail(map);
	}

	@Override
	public Integer findMaxStepNoByRecipeId(int recipeId) {
		return planDao.findMaxStepNoByRecipeId(recipeId);
	}

	@Transactional
	@Override
	public void editRecipeDetail(Map<String, Object> map) {
		planDao.editRecipeDetail(map);
	}

	@Transactional
	@Override
	public void updateStepNoOrderBy(Map<String, Object> map) {
		planDao.updateStepNoOrderBy(map);
	}

	@Override
	public List<CMesRecipeDatilT> getRecipeDatilByStepNo(Map<String, Object> map) {
		return planDao.getRecipeDatilByStepNo(map);
	}

	@Transactional
	@Override
	public void updateStepNoOrderBy2(Map<String, Object> map) {
		planDao.updateStepNoOrderBy2(map);
	}

	@Override
	public List<RMesPlanT> findPlanByPlanSerialno(String serialno) {
		return planDao.findPlanByPlanSerialno(serialno);
	}

	@Override
	public List<CMesRecipeTypeT> recipeTypeList(Map<String, Object> map) {
		return planDao.recipeTypeList(map);
	}

	@Transactional
	@Override
	public void addBarCodeToPPlanPrint(Map<String, Object> map) {
		planDao.addBarCodeToPPlanPrint(map);
	}

	@Transactional
	@Override
	public void delBarCode(Map<String, Object> map) {
		planDao.delBarCode(map);
	}

	@Override
	public List<RMesPlanPrintT> getMaxPlanPrintById(Map<String, Object> map) {
		return planDao.getMaxPlanPrintById(map);
	}

	@Override
	public List<CMesProductionT> getProductionByType(Map<String, Object> map) {
		return planDao.getProductionByType(map);
	}

	@Override
	public List<RMesPlanT> findPlanByPlanName(String planSerialno) {
		return planDao.findPlanByPlanName(planSerialno);
	}

	@Override
	public List<RMesPlanPrintT> getPlanPrintBySn(Map<String, Object> map2) {
		return planDao.getPlanPrintBySn(map2);
	}

	@Override
	public List<CMesProductionT> productionLists(Map<String, Object> map1) {
		return planDao.productionLists(map1);
	}

	@Override
	public int getMaxSerialNo(Map<String, Object> map) {
		return planDao.getMaxSerialNo(map);
	}

	@Override
	public List<RMesWorkorderDetailT> workorderDetailList(Map<String, Object> map) {
		return planDao.workorderDetailList(map);
	}
	@Transactional
	@Override
	public void addPlanDetail(Map<String, Object> map) {
		planDao.addPlanDetail(map);
	}
	@Transactional
	@Override
	public void editPlanDetail(Map<String, Object> map) {
		planDao.editPlanDetail(map);
	}
	@Transactional
	@Override
	public void delPlanDetail(Map<String, Object> map) {
		planDao.delPlanDetail(map);
	}

	@Override
	public Integer getMaxLevelNoByPlanId(Map<String, Object> map) {
		return planDao.getMaxLevelNoByPlanId(map);
	}
	@Transactional
	@Override
	public void editOtherPlanDetailLevelNo(Map<String, Object> map) {
		planDao.editOtherPlanDetailLevelNo(map);
	}

	@Override
	public void editLevelNo(Map<String, Object> map) {
		planDao.editLevelNo(map);
	}

	@Override
	public List<PMesPlanPrintT> pMesPlanPrintList(Map<String, Object> map) {
		return planDao.pMesPlanPrintList(map);
	}

	@Override
	public void updateBarCodeFlagByWorkOrderId(Integer planId, String flag) {
		planDao.updateBarCodeFlagByWorkOrderId(planId,flag);
	}

	@Override
	public List<RMesWorkorderDetailT> findWorkOrderByProductionId(Map<String, Object> map) {
		return planDao.findWorkOrderByProductionId(map);
	}

	@Override
	public List<CMesRecipeT> findRecipe(String id) {
		// TODO Auto-generated method stub
		return planDao.findRecipe(id);
	}


}
