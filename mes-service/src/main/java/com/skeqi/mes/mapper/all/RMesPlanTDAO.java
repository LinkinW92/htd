package com.skeqi.mes.mapper.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.pojo.Routing;

public interface RMesPlanTDAO {

	//查询所有计划
	public List<RMesPlanT> findAllPlan(RMesPlanT plan);

	//查询所有工艺路线
	public List<CMesRoutingT> findAllRouting(CMesRoutingT r);

	//根据总配方id查询下面的工位的产线
	public List<CMesStationT> findAllStation(Integer id);

	//根据id查询
	public RMesPlanT findPlanByid(Integer id);


	//获取最大的优先级
	public Integer getMaxLevel();

	//添加
	public Integer addPlan(RMesPlanT plan);

	// 根据计划id 查询优先级
	public Integer getPlanLevelByPlanId(@Param("planId")Integer planId);

	// 根据计划id 修改计划状态
	public Integer updateBarCodeFlagByPlanId(@Param("flag")Integer flag,@Param("planId")Integer planId,@Param("planLevel")Integer planLevel);

	// 开始时修改计划优先级
	public Integer updatePlanLevelWhenBegin(@Param("planLevel")Integer planLevel);

	// 关闭完成时修改计划优先级
	public Integer updatePlanLevelWhenClose(@Param("planLevel")Integer planLevel);

	//修改计划状态
	public Integer updateStatus(@Param("id")Integer id,@Param("status")Integer status);

	//查询所有工单
	public List<RMesWorkorderDetailT> findAllWorkOrder(RMesWorkorderDetailT work);

	//查询工单根据id
	public RMesWorkorderDetailT findWorkOrderByid(Integer id);

	//根据计划id查询工单总数
	public Integer findWorkNumber(Integer id);

	//根据计划id查询工单的最大优先级
	public Integer findWorkMaxLevel(Integer id);

	//添加工单
	public Integer addWorkorder(RMesWorkorderDetailT work);

	//删除工单
	public Integer delWorkorder(Integer id);

	//修改工单优先级
	public Integer updateWorkLevel(Map<String,Object> map);   //包含修改前后的工单优先级、产线id

	//修改工单
	public Integer updateWork(RMesWorkorderDetailT work);

	//完成工单列表
	public List<CMesWorkBarcodeT> findOKWorkOrder(Map<String,Object> map);

	//强制关闭工单列表
	public List<PMesPlanT> findOKWorkOrderR(Map<String,Object> map);

	//查询工单条码(R表)
	public List<CMesWorkBarcodeT> findWorkBarcode(Map<String,Object> map);

	//查询工单条码(R+P表)
	public List<CMesWorkBarcodeT> findWorkBarcodeRP(Map<String,Object> map);

	//查询工单条码(P表)
	public List<CMesWorkBarcodeT> findPorkBarcode(Map<String,Object> map);

	//根据工单id查询总成表(R表)
	public RMesTrackingT findRTrack(@Param("workId")Integer workId,@Param("sn")String sn);

	//根据工单id查询总成表(R表)
	public PMesTrackingT findPTrack(@Param("workId")Integer workId,@Param("sn")String sn);

	//根据产品ID获取总配方
	public List<CMesTotalRecipeT> findAllTotal(Integer id);

	//根据计划id查询完成数量是否大于计划数量
	@Select("SELECT count(*) from r_mes_plan_t  where  id= #{id} and  PLAN_NUMBER > COMPLETE_NUMBER")
	public Integer findPlanNumByCountNum(Integer planId);

	public Integer findPrintById(Integer id);

	public Integer getBeginCountById(Integer id);


}
