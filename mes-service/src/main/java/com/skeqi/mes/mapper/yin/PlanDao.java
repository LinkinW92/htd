package com.skeqi.mes.mapper.yin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

@Repository
public interface PlanDao {
	public List<CMesRecipeT> findRecipe(@Param("id")String id);
	/**
	 * PMesPlanT工单列表（永久保存）
	 */
	public List<PMesPlanT> planList(Map<String, Object> map);
	/**
	 * RMesPlanT工单列表（临时保存）
	 */
	public List<RMesPlanT> rplanList(Map<String, Object> map);

	/**
	 * 产线列表
	 */
	public List<CMesLineT> lineList();

	/**
	 * 产品信息
	 */
	public List<CMesProductionT> productionList();

	/**
	 * 添加计划到临时表
	 */
	public void addPlan(Map<String, Object> map);
	/**
	 * 添加计划到永久表
	 */
	public void addPlanForEver(RMesPlanT plan);

	/**
	 * 查询临时表中最大的优先级，用于自动生成优先级
	 */
	public Integer getMaxLevel();

	/**
	 * 通过优先级查询产线
	 */
	public List<RMesPlanT> getPlanByLevel(Integer level);
	/**
	 * 通过产线ID修改该产线优先级
	 */
	public void updateLevelByPlanId(@Param("planSerialno")String planSerialno,@Param("levels")Integer levels);
	public List<RMesPlanT> findPlanByPlanSerialno(String serialno);
	public void updateAllLevelByPlanId(@Param("levels")Integer levels);

	/**
	 * 将强制关闭订单和完成订单从临时计划表移除
	 */
	public void deletePlanByPlanId(@Param("planSerialno")String planId);
	public void updateFlagByPlanId(@Param("planId")String planId,@Param("flag")Integer flag);
	/**
	 * 生成条码后 改变条码生成状态
	 */
	public void updateBarCodeFlagByPlanId(@Param("planId")Integer planId,@Param("flag")Integer flag);
	/**
	 * 通过ID查询临时计划
	 */
	public RMesPlanT getPlanById(String planId);
	/**
	 * 生成条码 保存到数据库
	 */
	public void addbarCode(Map<String, Object> map);

	/**
	 * 查询条码
	 */
	public List<RMesPlanPrintT> getPlanPrintById(String planId);

	public List<RMesPlanPrintT> getMaxPlanPrintById(Map<String, Object> map);

	/**
	 * 配方明细管理查询
	 */
	public List<CMesRecipeDatilT> getRecipeDatil(Map<String, Object> map);

	public void deleteBomDetail(Map<String, Object> map);
	/**
	 * 配方列表
	 * @return
	 */
	public List<CMesRecipeT> recipeList();

	/**
	 * 工位列表
	 */
	public List<CMesStationT> stationList();//TRIPOD.C_MES_STATION_T
	/**
	 * 添加配方明细
	 */
	public void addRecipeDetail(Map<String, Object> map);
	/**
	 * 按配方 查询最大的工序id（用于工序排序）
	 */
	public Integer findMaxStepNoByRecipeId(int recipeId);
	/**
	 * 修改配方明细
	 */
	public void editRecipeDetail(Map<String, Object> map);
	/**
	 * 删除一条记录后 将比删除记录的 工序 全部-1
	 */
	public void updateStepNoOrderBy(Map<String, Object> map);
	/**
	 * 删除一条记录后 将比删除记录的 工序 全部+1
	 */
	public void updateStepNoOrderBy2(Map<String, Object> map);
	/**
	 * 按工序查找RecipeDatil
	 */
	public List<CMesRecipeDatilT> getRecipeDatilByStepNo(Map<String, Object> map);
	/**
	 * 配方类型信息
	 */
	public List<CMesRecipeTypeT> recipeTypeList(Map<String, Object> map);

	void addBarCodeToPPlanPrint(Map<String, Object> map);
	void delBarCode(Map<String, Object> map);
	/**
	 * 通过产品类型查询产品信息
	 */
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
	public void updateBarCodeFlagByWorkOrderId(@Param(value = "planId")Integer planId, @Param(value = "flag")String flag);
	public List<RMesWorkorderDetailT> findWorkOrderByProductionId(Map<String, Object> map);

}
