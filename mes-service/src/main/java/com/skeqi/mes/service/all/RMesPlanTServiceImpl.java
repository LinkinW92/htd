package com.skeqi.mes.service.all;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.Exception.util.UnknownException;
import com.skeqi.mes.mapper.all.RMesPlanTDAO;
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

@Service
@Transactional
public class RMesPlanTServiceImpl implements RMesPlanTService {

	@Autowired
	private RMesPlanTDAO dao;

	@Override
	public List<RMesPlanT> findAllPlan(RMesPlanT plan) throws ServicesException{
		// TODO Auto-generated method stub
		return dao.findAllPlan(plan);
	}
	@Override
	public RMesPlanT findPlanByid(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findPlanByid(id);
	}

	@Override
	public Integer getMaxLevel()  throws ServicesException{
		// TODO Auto-generated method stub
		return dao.getMaxLevel();
	}

	@Override
	public Integer addPlan(RMesPlanT plan)  throws ServicesException{
		// TODO Auto-generated method stub

		RMesPlanT plans = new RMesPlanT();
		plans.setPlanName(plan.getPlanName());
		plans.setPlanSerialno(plan.getPlanSerialno());

		List<RMesPlanT> findAllPlan = dao.findAllPlan(plans);
		if(findAllPlan.size()>0){
			throw new NameRepeatException("名称重复",100);
		}

		RMesPlanT plan2 = new RMesPlanT();
		plan2.setPlanSerialno(plan.getPlanSerialno());
		List<RMesPlanT> findAllPlan2 = dao.findAllPlan(plan2);
		if(findAllPlan2.size()>0){
			throw new NameRepeatException("计划编号重复",100);
		}
		return dao.addPlan(plan);
	}

	@Override
	public Integer updateStatus(Integer id, Integer status)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(status==null || status==0){
			throw new ParameterNullException("状态不能为空",200);
		}
		return dao.updateStatus(id, status);
	}

	@Override
	public List<RMesWorkorderDetailT> findAllWorkOrder(RMesWorkorderDetailT work) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllWorkOrder(work);
	}

	@Override
	public Integer findWorkNumber(Integer id) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.findWorkNumber(id);
	}

	@Override
	public Integer findWorkMaxLevel(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findWorkMaxLevel(id);
	}

	@Override
	public Integer addWorkorder(RMesWorkorderDetailT work) throws ServicesException {
		// TODO Auto-generated method stub
		if(work.getProductionId()==null || work.getProductionId()==""){
			throw new ParameterNullException("工单编号不能为空",200);
		}else if(work.getOrderNumber()==null){
			throw new ParameterNullException("工单数量不能为空",200);
		}

		RMesWorkorderDetailT w = new RMesWorkorderDetailT();
		w.setProductionId(work.getProductionId());
		List<RMesWorkorderDetailT> findAllWorkOrder = dao.findAllWorkOrder(w);   //查询工单编号是否重复
		if(findAllWorkOrder.size()>0){
			throw new NameRepeatException("工单编号重复",100);
		}

		Integer findWorkNumber = dao.findWorkNumber(work.getPlanId());   //根据计划id查询已有工单总数
		RMesPlanT findPlanByid = dao.findPlanByid(work.getPlanId());   //查询计划里的数量

		work.setLineId(findPlanByid.getLineId());

		if(findPlanByid.getPlanNumber()==null) {
			findPlanByid.setPlanNumber(0);
		}

		if(findWorkNumber==null) {
			findWorkNumber=0;
		}
		if(findWorkNumber+work.getOrderNumber()>findPlanByid.getPlanNumber()){
			throw new ParameterNullException("工单数量大于计划里的数量",200);
		}

		Integer findWorkMaxLevel = dao.findWorkMaxLevel(work.getPlanId());
		if(findWorkMaxLevel==null) {
			findWorkMaxLevel=0;
		}
		work.setLevelNo(findWorkMaxLevel+1);

		return dao.addWorkorder(work);
	}

	@Override
	public Integer delWorkorder(Integer id) throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delWorkorder(id);
	}

	@Override
	public Integer updateWork(RMesWorkorderDetailT work) throws ServicesException {
		// TODO Auto-generated method stub
		if(work.getProductionId()==null || work.getProductionId()==""){
			throw new ParameterNullException("工单编号不能为空",200);
		}else if(work.getOrderNumber()==null){
			throw new ParameterNullException("工单数量不能为空",200);
		}
		Integer wid = work.getId();//工单id
		RMesWorkorderDetailT findWorkOrderByid = dao.findWorkOrderByid(wid);  //查询之前的工单编号
		if(!findWorkOrderByid.getProductionId().equals(work.getProductionId())){  //如果不同
			RMesWorkorderDetailT w = new RMesWorkorderDetailT();
			w.setProductionId(work.getProductionId());
			List<RMesWorkorderDetailT> findAllWorkOrder = dao.findAllWorkOrder(w);
			if(findAllWorkOrder.size()>0){
				throw new NameRepeatException("工单编号重复",100);
			}
		}
		Integer findWorkNumber = dao.findWorkNumber(work.getPlanId());   //根据计划id查询已有工单总数
		RMesPlanT findPlanByid = dao.findPlanByid(work.getPlanId());   //查询计划里的数量

		if(findPlanByid.getPlanNumber()==null) {
			findPlanByid.setPlanNumber(0);
		}

		if(findWorkNumber==null) {
			findWorkNumber=0;
		}

		if(work.getOrderNumber()>findPlanByid.getPlanNumber()){
			throw new ParameterNullException("工单数量大于计划里的数量",200);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lineId", work.getLineId());
		Integer nLevelNo = work.getLevelNo();  //修改后的优先级
		Integer oLevelNo = findWorkOrderByid.getLevelNo();   //修改后的优先级
		if (nLevelNo<oLevelNo) {
			map.put("flag", 0);
		}else if (nLevelNo>oLevelNo) {
			map.put("flag", 1);
		}else {
			map.put("flag", 2);
		}
		map.put("nLevelNo", nLevelNo);
		map.put("oLevelNo", oLevelNo);
		Integer findWorkMaxLevel = dao.findWorkMaxLevel(work.getPlanId());  //最大的优先级
		if(findWorkMaxLevel==null) {
			findWorkMaxLevel=0;
		}
		if(nLevelNo>findWorkMaxLevel){
			map.put("flag", 3);
		}

		Integer updateWorkLevel = dao.updateWorkLevel(map);  //修改所有工单优先级
		if(updateWorkLevel==0){
			throw new UnknownException("未知错误",300);
		}
		return dao.updateWork(work);
	}

	@Override
	public RMesWorkorderDetailT findWorkOrderByid(Integer id) throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findWorkOrderByid(id);
	}

	/**
	 * 完成工单  map包括计划编号、开始时间、结束时间、flag   可以为空
	 */
	@Override
	public List<CMesWorkBarcodeT> findOKWorkOrder(Map<String, Object> map) throws ServicesException {


		Date date1=null;
		Date date2=null;

		//处理时间格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (map.get("startTime")!=null  && !map.get("startTime").equals("")) {
				date1   =   formatter.parse(map.get("startTime").toString());
			}
			if (map.get("endTime")!=null &&  !map.get("endTime").equals("")) {
				date2   =   formatter.parse(map.get("endTime").toString());
			}
			map.remove("startTime");
			map.remove("endTime");
			map.put("date1", date1);
			map.put("date2", date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.findOKWorkOrder(map);
	}

	/**
	 * 强制关闭工单  map包括计划编号、开始时间、结束时间、flag   可以为空
	 */
	@Override
	public List<PMesPlanT> findOKWorkOrderR(Map<String, Object> map) throws ServicesException {


		Date date1=null;
		Date date2=null;

		//处理时间格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (map.get("startTime")!=null  && !map.get("startTime").equals("")) {
				date1   =   formatter.parse(map.get("startTime").toString());
			}
			if (map.get("endTime")!=null &&  !map.get("endTime").equals("")) {
				date2   =   formatter.parse(map.get("endTime").toString());
			}
			map.remove("startTime");
			map.remove("endTime");
			map.put("date1", date1);
			map.put("date2", date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.findOKWorkOrderR(map);
	}

	/**
	 * map包括  sn、计划编号、工单id
	 */
	@Override
	public List<CMesWorkBarcodeT> findWorkBarcode(Map<String, Object> map) throws ServicesException {
		// TODO Auto-generated method stub
		//首先查询R表
		List<CMesWorkBarcodeT> findWorkBarcode = dao.findWorkBarcode(map);

		return findWorkBarcode;
	}

	/**
	 * 查询工单条码  R+P表
	 * map包括  sn、计划编号、工单id
	 */
	@Override
	public List<CMesWorkBarcodeT> findWorkBarcodeRP(Map<String, Object> map) throws ServicesException {
		// TODO Auto-generated method stub
		//首先查询R+P表
		List<CMesWorkBarcodeT> findWorkBarcode = dao.findWorkBarcodeRP(map);

		return findWorkBarcode;
	}

	/**
	 * 查询sn的状态  返回map
	 *  	map.put("sn", findR.getSn());  //sn
			map.put("dt",findR.getDt());  //开始时间
			map.put("OFFLINE_DT",null);  //下线时间
			map.put("status", 0);  //完成状态
			map.put("st",findR.getSt()) ;;//当前工位
	 */
	@Override
	public Map<String, Object> findTrack(Integer workId, String sn) throws ServicesException {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<>();
		RMesTrackingT findRTrack = dao.findRTrack(workId, sn);
		if(findRTrack!=null){
			map.put("sn", findRTrack.getSn());  //sn
			map.put("dt",findRTrack.getDt());  //开始时间
			map.put("OFFLINE_DT",null);  //下线时间
			map.put("status", 0);  //完成状态
			map.put("st",findRTrack.getSt()) ;;//当前工位
		}else{
			PMesTrackingT findP = dao.findPTrack(workId, sn);
			if(findP!=null){
				map.put("sn", findP.getSn());  //sn
				map.put("dt",findP.getDt());  //开始时间
				map.put("offlinedt",findP.getOffLineDt());  //下线时间
				map.put("status", 1);  //完成状态
				map.put("st",null) ;;//当前工位
			}else{
				map.put("msg",0);
				return map;
			}
		}
		return map;
	}

	@Override
	public List<CMesRoutingT> findAllRouting(CMesRoutingT r) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRouting(r);
	}

	@Override
	public List<CMesStationT> findAllStation(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllStation(id);
	}

	@Override
	public List<CMesTotalRecipeT> findAllTotal(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllTotal(id);
	}

	@Override
	public Integer updateBarCodeFlagByPlanId(Integer flag, Integer planId) {
		// TODO Auto-generated method stub
		// 获取优先级
		Integer planLevel = dao.getPlanLevelByPlanId(planId);
		switch (flag) {
		case 1:
			dao.updatePlanLevelWhenBegin(planLevel);
			planLevel = 1;
			break;
		case 3:
		case 4:
			dao.updatePlanLevelWhenClose(planLevel);
			break;
		}
		return dao.updateBarCodeFlagByPlanId(flag, planId, planLevel);
	}

	@Override
	public Integer findPlanNumByCountNum(Integer planId) {
		// TODO Auto-generated method stub
		return dao.findPlanNumByCountNum( planId);
	}

	@Override
	public List<CMesWorkBarcodeT> findWorkBarcodeP(Map<String, Object> map) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findPorkBarcode(map);
	}

	@Override
	public Integer findPrintById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findPrintById(id);
	}

	@Override
	public Integer getBeginCountById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getBeginCountById(id);
	}



}
