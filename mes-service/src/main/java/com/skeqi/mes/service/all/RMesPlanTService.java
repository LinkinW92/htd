package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.pojo.Routing;

public interface RMesPlanTService {
	//查询计划下是否有条码
	public Integer findPrintById(Integer id);

	//查询产线下是否有计划已开始
	public Integer getBeginCountById(Integer id);

	//查询所有计划
	public List<RMesPlanT> findAllPlan(RMesPlanT plan) throws ServicesException;

	//查询所有工艺路线
	public List<CMesRoutingT> findAllRouting(CMesRoutingT r)  throws ServicesException;

	//根据总配方id查询下面的工位的产线
	public List<CMesStationT> findAllStation(Integer id)throws ServicesException;

	//根据id查询
	public RMesPlanT findPlanByid(Integer id) throws ServicesException;

	//获取最大的优先级
	public Integer getMaxLevel() throws ServicesException;

	//添加
	public Integer addPlan(RMesPlanT plan) throws ServicesException;

	// 根据计划id 修改计划状态
	public Integer updateBarCodeFlagByPlanId(Integer flag,Integer planId);

	//修改计划状态
	public Integer updateStatus(Integer id,Integer status) throws ServicesException;

	//查询所有工单
	public List<RMesWorkorderDetailT> findAllWorkOrder(RMesWorkorderDetailT work) throws ServicesException;

	//查询工单根据id
	public RMesWorkorderDetailT findWorkOrderByid(Integer id) throws ServicesException;

	//根据计划id查询工单总数
	public Integer findWorkNumber(Integer id) throws ServicesException;

	//根据计划id查询工单的最大优先级
	public Integer findWorkMaxLevel(Integer id) throws ServicesException;

	//添加工单
	public Integer addWorkorder(RMesWorkorderDetailT work) throws ServicesException;

	//删除工单
	public Integer delWorkorder(Integer id) throws ServicesException;

	//修改工单
	public Integer updateWork(RMesWorkorderDetailT work) throws ServicesException;

	//完成工单、强制完成工单
	public List<CMesWorkBarcodeT> findOKWorkOrder(Map<String,Object> map) throws ServicesException;

	//查询工单条码(R表)
	public List<CMesWorkBarcodeT> findWorkBarcode(Map<String,Object> map) throws ServicesException;

	//查询工单条码(R表)
	public List<CMesWorkBarcodeT> findWorkBarcodeP(Map<String,Object> map) throws ServicesException;

	//根据工单id查询总成表(R表)
	public Map<String,Object> findTrack(@Param("workId")Integer workId,@Param("sn")String sn) throws ServicesException;

	//根据产品ID获取总配方
	public List<CMesTotalRecipeT> findAllTotal(Integer id)throws ServicesException;

	public Integer findPlanNumByCountNum(Integer planId);

	//强制关闭工单
	List<PMesPlanT> findOKWorkOrderR(Map<String, Object> map) throws ServicesException;

	//查询工单条码(R+P表)
	List<CMesWorkBarcodeT> findWorkBarcodeRP(Map<String, Object> map) throws ServicesException;


}
