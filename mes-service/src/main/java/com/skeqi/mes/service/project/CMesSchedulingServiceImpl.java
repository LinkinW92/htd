package com.skeqi.mes.service.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesSchedulingDAO;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesAndonPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.pojo.project.Scheduling;

@Service
@Transactional
public class CMesSchedulingServiceImpl implements CMesSchedulingService{

	@Autowired
	CMesSchedulingDAO dao;

	@Override
	public Integer addSchebuling(Integer shiftId,Integer teamId,Integer number,String dt,Integer lineId) throws ServicesException {
		// TODO Auto-generated method stub
		//Integer findSchedulingByDt = mapper.findSchedulingByDt(dt, shiftId);
/*		if(findSchedulingByDt>0){
			throw new ParameterNullException("此天数已存在该班次",200);
		}
*/
		CMesScheduling du = new CMesScheduling();
		du.setShiftId(shiftId);
		du.setDt(dt);
		du.setTeamId(teamId);
		du.setLineId(lineId);
		du.setPlanNumber(number);
		return dao.addScheduling(du);
	}

	@Override
	public Set<Map<String,Object>> findAllSchebuling(Integer lineId) throws ServicesException {

		return dao.findAllSchebuling(lineId,null);
	}

	@Override
	public Integer updateScheduling(String id,Integer teamId,Integer number,Integer lineId) throws ServicesException {
	//	SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		// TODO Auto-generated method stub
		if(id==null || id==""){
			throw new ParameterNullException("Id不能为空",200);
		}else if(teamId==null || teamId==0){
			throw new ParameterNullException("班组Id不能为空",200);
		}
		else if(number==null || number==0){
			throw new ParameterNullException("数量不能为空",200);
		}
		Integer findWorkLine = dao.findWorkLine(lineId);
		if(findWorkLine>0){
			throw new ParameterNullException("工单中的产线与修改后的产线不一样",200);
		}
		String[] split = id.split(",");
		for (String string : split) {
			CMesScheduling du = new CMesScheduling();
			du.setTeamId(teamId);
			du.setId(Integer.parseInt(string));
			du.setPlanNumber(number);
			du.setLineId(lineId);
			Integer findSumWorkNumber = dao.findSumWorkNumber(Integer.parseInt(string));
			if(findSumWorkNumber>number){
				throw new ParameterNullException("修改后的计划数量小于工单的计划数量总数",200);
			}
			dao.updateScheduling(du);
		}
		return 0;
	}

	@Override
	public Integer addWork(Integer planId, Integer number, Integer scheId)
			throws ServicesException {
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		if(planId==null || planId==0){
			throw new ParameterNullException("计划Id不能为空",200);
		}else if(number==null || number==0){
			throw new ParameterNullException("计划数量不能为空",200);
		}else if(scheId==null || scheId==0){
			throw new ParameterNullException("排班不能为空",200);
		}
	/*	Integer findPlan = mapper.findPlan(planId);   //查询计划的剩余数量

		if(findPlan<number){
			throw new ParameterNullException("计划数量不足",200);
		}*/
		 Integer findSumWorkNumber = dao.findSumWorkNumber(scheId);   //当前排班的工单计划总数
		 if(findSumWorkNumber==null){
			 findSumWorkNumber=0;
		 }
		 Map<String, Object> findSchePlanNumber = dao.findSchePlanNumber(scheId);   //排班计划数量和产线id
		 Integer planNumber  = (Integer)findSchePlanNumber.get("planNumber");
		 if(findSumWorkNumber+number>planNumber){
			 throw new ParameterNullException("工单计划数量大于排班计划数量",200);
		 }
		Map<String, Object> findProductMark = dao.findProductMark(planId);   //查询计划的产品标记和完成数量、产线id
		CMesWorkorderT work = new CMesWorkorderT();
		work.setPlanId(planId);
		work.setWorkName(s.format(new Date()));
		work.setNumber(number);   //计划数量
		String productMark = null;
		if(findProductMark.get("productMark")!=null){
			Integer findRepeatPromark = dao.findRepeatPromark(scheId, findProductMark.get("productMark").toString());
			if(findRepeatPromark>0){
				 throw new ParameterNullException("此产品标记已存在此排班",200);
			}
			productMark = findProductMark.get("productMark").toString();
		}
		work.setProductMark(productMark);   //产品标记
		work.setSurplusNumber((Integer)findProductMark.get("complete"));  //完成数量
		work.setScheId(scheId);
		//System.err.println("sdasd"+(Integer)findSchePlanNumber.get("lineId"));
//		Object object = findSchePlanNumber.get("lineId");
//		Object object2 = findProductMark.get("lineId");
//		System.err.println("findPlan==="+object);
//		System.err.println("object2==="+object2);
//		if(!object.equals(object2)){
//			System.err.println("该计划的产线和排班的产线不一样");
//			throw new ParameterNullException("该计划的产线和排班的产线不一样",200);
//		}
		return dao.addWorkOrder(work);
	}

	@Override
	public Integer delWork(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null  || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		Map<String, Object> map = dao.findCompleteByWorkId(id);
		dao.delAndon(id);
		dao.updateScheComplete((Integer)map.get("number"),(Integer)map.get("id"));
		return dao.delWork(id);
	}

	@Override
	public Integer updateWork(Integer id, Integer planNumber) throws ServicesException {
		// TODO Auto-generated method stub
//		Integer findSumByWorkId = mapper.findSumByWorkId(id);   //查询除此工单以外的其他工单总数
//		Integer findPlanNumberByWork = mapper.findPlanNumberByWork(id);   //查询计划的计划数量
//		if(findSumByWorkId+planNumber>findPlanNumberByWork){
//			throw new ParameterNullException("更改后的计划数量总数大于排班的计划数量",200);
//		}
		return dao.updateWorkNumber(id, planNumber);
	}

	@Override
	public List<CMesWorkorderT> findAllWork(Integer id,Integer planId) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null  || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findAllWork(id,planId);
	}

	@Override
	public Integer delSche(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
	List<Integer>wid=	dao.findBySchId(id);//删除计数表数据
	for (Integer integer : wid) {
//	System.err.println("wid==="+wid);
		dao.delAndon(integer);//删除计数表
	}
	dao.delWorkByScheId(id);
	//	mapper.delScheById(id);
		return null;
	}

	@Override
	public Integer update(Integer lineId, Integer shiftId, String dt, Integer teamId, Integer planNumber, Integer planrealNumber) {
		// TODO Auto-generated method stub
		return dao.update( lineId,  shiftId,  dt,  teamId,  planNumber,  planrealNumber);
	}

	@Override
	public void updateWorkL(Integer id, Integer i) {
		dao.updateWorkL( id,  i);
	}

	@Override
	public Integer findByShiftId(String shift,Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findByShiftId( shift,lineId) ;
	}

	@Override
	public Integer findByTeamId(String team) {
		// TODO Auto-generated method stub
		return dao.findByTeamId(team);
	}

	@Override
	public Integer findByLineId(String line) {
		// TODO Auto-generated method stub
		return dao.findByLineId( line);
	}

	@Override
	public Integer findByScheduling(Integer lineId, Integer shiftId, String dt) {
		// TODO Auto-generated method stub
		return dao.findByScheduling( lineId, shiftId,  dt);
	}

	@Override
	public Integer findByPlanId(String planName) {
		// TODO Auto-generated method stub
		return dao.findByPlanId( planName);
	}

	@Override
	public Integer addWork2(Integer planId, Integer number, Integer scheId,String workName,Integer planrealNumber,String productionMark)
			throws ServicesException {
	/*	SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		if(planId==null || planId==0){
			throw new ParameterNullException("计划Id不能为空",200);
		}else if(number==null || number==0){
			throw new ParameterNullException("计划数量不能为空",200);
		}else if(scheId==null || scheId==0){
			throw new ParameterNullException("排班不能为空",200);
		}*/
		Integer findPlan = dao.findPlan(planId);   //查询计划的剩余数量
/*		System.err.println("findPlan===="+findPlan);
		System.err.println("number===="+number);*/
		if(findPlan<number){
			throw new ParameterNullException("计划数量不足",200);
		}
		 Integer findSumWorkNumber = dao.findSumWorkNumber(scheId);   //当前排班的工单计划总数
		 if(findSumWorkNumber==null){
			 findSumWorkNumber=0;
		 }
	//	 Map<String, Object> findSchePlanNumber = mapper.findSchePlanNumber(scheId);   //排班计划数量和产线id
	//	 Integer planNumber  = (Integer)findSchePlanNumber.get("planNumber");
//		 Integer sum=findSumWorkNumber+number;
//		 System.err.println("planNumber===="+planNumber);
//		 System.err.println("sum===="+sum);
/*		 if(sum>planNumber){
			 throw new ParameterNullException("工单计划数量大于排班计划数量",200);
		 }*/
		Map<String, Object> findProductMark = dao.findProductMark(planId);   //查询计划的产品标记和完成数量、产线id
		CMesWorkorderT work = new CMesWorkorderT();
		work.setPlanId(planId);
		//work.setWorkName(s.format(new Date()));
		work.setWorkName(workName);
        work.setCompleteNumber(planrealNumber);//完成数量
		work.setNumber(number);   //计划数量
//		String productMark = null;
//		if(findProductMark.get("productMark")!=null){
	//		Integer findRepeatPromark = mapper.findRepeatPromark(scheId, findProductMark.get("productMark").toString());
//			if(findRepeatPromark>0){
//				 throw new ParameterNullException("此产品标记已存在此排班",200);
//			}
//			productMark = findProductMark.get("productMark").toString();
//		}
		work.setProductMark(productionMark);   //产品标记
		work.setSurplusNumber((Integer)findProductMark.get("complete"));  //完成数量
		work.setScheId(scheId);

	//	System.err.println("sdasd"+(Integer)findSchePlanNumber.get("lineId"));
	//	Object object = findSchePlanNumber.get("lineId");
	//	Object object2 = findProductMark.get("lineId");
	//	System.err.println("findPlan==="+object);
	//	System.err.println("object2==="+object2);
/*		if(!object.equals(object2)){
			System.err.println("该计划的产线和排班的产线不一样");
			throw new ParameterNullException("该计划的产线和排班的产线不一样",200);
		}*/
		return dao.addWorkOrder(work);
	}

	@Override
	public int findByScheduling2(Integer lineId, Integer shiftId, String dt) {
		// TODO Auto-generated method stub
		return dao.findByScheduling2( lineId, shiftId,  dt);
	}

	@Override
	public Integer updateWork2(Integer id, Integer planNumber) {
		// TODO Auto-generated method stub
		return dao.updateWork2( id,  planNumber);
	}

	@Override
	public Integer delScheL(Integer lineId, Integer shiftId, String dt) {
		// TODO Auto-generated method stub
		return dao.delScheL(lineId,shiftId,dt);
	}

	@Override
	public Integer findPlan(Integer planId) {
		// TODO Auto-generated method stub
		return dao.findPlan(planId);
	}

	@Override
	public Integer delScheById(int id) {
		// TODO Auto-generated method stub
	return	dao.delScheById(id);
	}

	@Override
	public Integer findwork(Integer id,Integer pid) {
		// TODO Auto-generated method stub
		return dao.findwork( id,pid);
	}

	@Override
	public Integer delWorkByPlanId(Integer pid) {
		// TODO Auto-generated method stub
/*      List<Integer>wid=mapper.findByPid(pid);
      for (Integer integer : wid) {
    	  System.err.println("pid==="+wid);
		mapper.delAndon(integer);
	}*/
		return dao.delWorkByPlanId( pid);
	}

	@Override
	public List<Integer> findByWorkId(int id) {
		// TODO Auto-generated method stub
		return dao.findByWorkId( id);
	}

	@Override
	public List<CMesWorkorderT> findAllWorkL(int id, Integer planId) {
		// TODO Auto-generated method stub
		return dao. findAllWorkL(id,  planId);
	}

	@Override
	public List<CMesWorkorderT> findByWorkAll() {
		// TODO Auto-generated method stub
		return dao.findByWorkAll();
	}

	@Override
	public Set<Map<String, Object>> findPidByScheduling(Integer id) {
		// TODO Auto-generated method stub
		return dao.findPidByScheduling( id);
	}

	@Override
	public List<CMesSchedulingL> findAllSchebulingl(String startTime,String endTime) {
		// TODO Auto-generated method stub
		return dao.findAllSchebulingl(startTime,endTime);
	}

	@Override
	public CMesAndonPlanT findByPlan(Integer pid) {
		// TODO Auto-generated method stub
		return dao.findByPlan(pid);
	}

	@Override
	public CMesShiftsTeamT findByShifAll(Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findByShifAll(lineId);
	}

	@Override
	public CMesTeamT findByTeamAll() {
		// TODO Auto-generated method stub
		return dao.findByTeamAll();
	}

	@Override
	public Integer addScheduling(CMesScheduling du) {
		// TODO Auto-generated method stub
		return dao.addScheduling(du);
	}

	@Override
	public Integer addWork3(CMesWorkorderT work) {
		// TODO Auto-generated method stub
		return dao.addWork(work);
	}

	@Override
	public List<RMesPlanT> findSchePlan(String time,String lineName) {
		// TODO Auto-generated method stub
		return dao.findSchePlan(time,lineName);
	}

}
