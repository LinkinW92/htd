package com.skeqi.mes.service.project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesAndonPlanDAO;
import com.skeqi.mes.mapper.project.CMesSchedulingDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesWorkorderT;

@Service
public class CMesAndonPlanServiceImpl implements CMesAndonPlanService {

	@Autowired
	CMesAndonPlanDAO dao;

	@Autowired
	CMesSchedulingDAO cMesSchedulingDAO;

	@Autowired
	CMesAndonServiceImpl andonService;

	@Override
	public List<RMesPlanT> findAndonPlan(String name, String lineName,String systemDate) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAndonPlan(name, lineName,systemDate);
	}

	@Override
	public Integer addAndonPlan(RMesPlanT plan) throws ServicesException {
		// String planName = plan.getPlanName();
		Integer lineId = plan.getLineId();// 产线
		String dt = plan.getDt();// 计划执行时间
		Integer productionId = plan.getProductionId();// 产品id
		Integer i = 0;
		if (!StringUtils.isEmpty(dt)) {
			System.err.println("dt==" + dt);
			Integer q = dao.findLineDtByPro(lineId, dt, productionId);
			if (q > 0) {
				throw new ParameterNullException("同一时间同一产线，不能有同样的产品", 200);
			}
			Integer planNumber = plan.getPlanNumber();
			String productMark = plan.getProductMark();
			i = dao.addAndonImportPlan(plan);// 添加计划
			Integer pid = plan.getId();// 计划id
			CMesShiftsTeamT findByShiftId = cMesSchedulingDAO.findByShifAll(lineId);
			String lineName = dao.findByLineId(lineId);
			CMesTeamT team = cMesSchedulingDAO.findByTeamAll();
			if (StringUtils.isEmpty(findByShiftId)) {
				throw new ParameterNullException("班次不存在此产线,排版添加失败", 200);
			} else {
				String workName = dt + " - " + lineName + " - " + findByShiftId.getName();
				Integer s = cMesSchedulingDAO.findByScheduling(lineId, findByShiftId.getId(), dt);
				if (s > 0) {
					Integer scheId = cMesSchedulingDAO.findByScheduling2(lineId, findByShiftId.getId(), dt);
					CMesWorkorderT work = new CMesWorkorderT();
					work.setPlanId(pid);
					work.setWorkName(workName);
					work.setNumber(planNumber);
					work.setProductMark(productMark);
					work.setCompleteNumber(0);
					work.setScheId(scheId);
					cMesSchedulingDAO.addWork(work);
				} else {
					CMesScheduling du = new CMesScheduling();
					du.setShiftId(findByShiftId.getId());
					du.setDt(dt);
					du.setTeamId(team.getId());
					du.setLineId(lineId);
					du.setPlanNumber(planNumber);
					cMesSchedulingDAO.addScheduling(du);
					Integer scheId = du.getId();
					CMesWorkorderT work = new CMesWorkorderT();
					work.setPlanId(pid);
					work.setWorkName(workName);
					work.setNumber(planNumber);
					work.setProductMark(productMark);
					work.setCompleteNumber(0);
					work.setScheId(scheId);
					cMesSchedulingDAO.addWork(work);
				}
			}
		} else {
			plan.setDt(null);
			dao.addAndonImportPlan(plan);// 添加计划
		}

		return i;
	}

	@Override
	public Integer updateAndonPlan(RMesPlanT plan) throws ServicesException {

		if (plan.getPlanName() == null || plan.getPlanName() == "") {
			throw new ParameterNullException("计划名称不能为空", 200);
		} else if (plan.getPlanNumber() == null) {
			throw new ParameterNullException("计划数量不能为空", 200);
		} else if (plan.getProductionId() == null) {
			throw new ParameterNullException("产品id不能为空", 200);
		} else if (plan.getLineId() == null) {
			throw new ParameterNullException("产线id不能为空", 200);
		} else if (plan.getProductMark() == null || plan.getProductMark() == "") {
			throw new ParameterNullException("产品标记不能为空", 200);
		}
	    dao.updateWorkNumber(plan.getId(), plan.getPlanNumber());
		return dao.updateAndonPlan(plan);
	}

	@Override
	public Integer delAndonPlan(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("ID不能为空", 200);
		}
	   	Integer scheId=	cMesSchedulingDAO.findScheIdByWorkId(id);//查询排班id
	    cMesSchedulingDAO.delScheById(scheId);//删除排班表
		List<Integer> wid = cMesSchedulingDAO.findByPid(id);
		for (Integer a : wid) {
			if(!a.equals(null)) {
				cMesSchedulingDAO.delWork(a);//删除工单
				cMesSchedulingDAO.delAndon(a);//删除计数
			}

		}
		return dao.delAndonPlan(id);//删除计划
	}


	@Override
	public Integer updateStatus(Integer id, Integer status) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("ID不能为空", 200);
		} else if (status == null) {
			throw new ParameterNullException("状态不能为空", 200);
		}
		RMesPlanT findAndonPlanByid = dao.findAndonPlanByid(id);
		if (status == 1 && findAndonPlanByid.getActualStartTime() == null) {
			dao.updateActualStartTime(id);
		} else if (status == 4 && findAndonPlanByid.getActualEndTime() == null) {
			dao.updateActualEndTime(id);
		}
		return dao.updateStatus(id, status);
	}

	@Override
	public List<RMesPlanT> findcompleteAndonPlan(String name, String lineName,String systemDate) {
		// TODO Auto-generated method stub
		return dao.findcompleteAndonPlan(name, lineName,systemDate);
	}

	@Override
	public Integer findLineByName(String name) {
		// TODO Auto-generated method stub
		return dao.findLineByName(name);
	}

	@Override
	public CMesProductionT findProByName(String name) {
		// TODO Auto-generated method stub
		return dao.findProByName(name);
	}

	@Override
	public Integer addAndonImportPlan(RMesPlanT plan) throws ServicesException {
		return dao.addAndonImportPlan(plan);// 添加计划
	}

	@Override
	public List<String> findProducMark(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		String findProductmark = dao.findProductmark(id);
		String[] split = findProductmark.split(",");
		for (String string : split) {
			list.add(string);
		}
		return list;
	}

	@Override
	public Integer findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName(name);
	}

	@Override
	public Integer updateStatusl(RMesPlanT plan) {
		// TODO Auto-generated method stub
		return dao.updateStatusl(plan);
	}

	@Override
	public Integer findByNamel(String planName, Integer id) {
		// TODO Auto-generated method stub

		return dao.findByNamel(planName, id);
	}

	@Override
	public String findByLineId(Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findByLineId(lineId);
	}

	@Override
	public CMesLineT findLineByProType(Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findLineByProType(lineId);
	}

	@Override
	public Integer findLineDtByPro(Integer lineId, String dt, Integer pid) {
		// TODO Auto-generated method stub
		return dao.findLineDtByPro(lineId, dt, pid);
	}

	@Override
	public JSONObject findKanBanYield(String lineName) throws Exception {

		if(lineName==null || lineName.equals("")) {

			JSONObject returnDx = new JSONObject();

			List<JSONObject> dxList = new ArrayList<JSONObject>();

			List<JSONObject> list = dao.findAllLine();
			for (JSONObject line : list) {

				JSONObject dx = new JSONObject();

				//查询排版id
				Integer schedulingId = andonService.finScheId(line.getInteger("id"), 1);

				dx.put("lineName", line.getString("name"));//产线名称

				if(schedulingId==null || schedulingId==0) {
//					throw new Exception("没有排班");
					dx.put("msg","没有排班");
				}else {
					CMesScheduling scheduling = dao.findScheduling(schedulingId);

					//查询产线总计划
					JSONObject LineTotalPlan = dao.findLineTotalPlan(line.getInteger("id"), scheduling.getDt());


					dx.put("totalPlanNumber", LineTotalPlan.getString("totalPlanNumber"));//总计划数量
					dx.put("totalCompleteNumber", LineTotalPlan.getString("totalCompleteNumber"));//总生产数量

					//查询最近一次下线产品的简称
					String ProductAbbreviation = dao.findRecentlyOfflineProductAbbreviation(line.getInteger("id"), scheduling.getDt());

					dx.put("ProductAbbreviation", ProductAbbreviation);//产品简称

					//查询每个产品型号的总产量
					List<JSONObject> ProductModelTotalPlan = dao.findProductModelTotalPlan(line.getInteger("id"), scheduling.getDt());

					dx.put("ProductModelTotalPlan", ProductModelTotalPlan);//产品型号的总产量
					dx.put("msg",null);
				}

				dxList.add(dx);
			}

			returnDx.put("list", dxList);
			return returnDx;

		}else{
			JSONObject dx = new JSONObject();

			CMesLineT line = dao.findLine(lineName);

			if(line==null || line.getId()==null) {
				throw new Exception("产线不存在");
			}
			//查询排版id
			Integer schedulingId = andonService.finScheId(line.getId(), 1);
			if(schedulingId==null || schedulingId==0) {
				throw new Exception("没有排班");
			}
			CMesScheduling scheduling = dao.findScheduling(schedulingId);

			//查询产线总计划
			JSONObject LineTotalPlan = dao.findLineTotalPlan(line.getId(), scheduling.getDt());

			dx.put("lineName", LineTotalPlan.getString("lineName"));//产线名称
			dx.put("totalPlanNumber", LineTotalPlan.getString("totalPlanNumber"));//总计划数量
			dx.put("totalCompleteNumber", LineTotalPlan.getString("totalCompleteNumber"));//总生产数量

			//查询最近一次下线产品的简称
			String ProductAbbreviation = dao.findRecentlyOfflineProductAbbreviation(line.getId(), scheduling.getDt());

			dx.put("ProductAbbreviation", ProductAbbreviation);//产品简称

			//查询每个产品型号的总产量
			List<JSONObject> ProductModelTotalPlan = dao.findProductModelTotalPlan(line.getId(), scheduling.getDt());

			dx.put("ProductModelTotalPlan", ProductModelTotalPlan);//产品型号的总产量
			return dx;
		}


	}


}
