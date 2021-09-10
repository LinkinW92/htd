package com.skeqi.mes.service.all;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.CheckAllRecipePDao;
import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.CheckClientLoginPT;
import com.skeqi.mes.pojo.api.CheckPlanSnPT;
import com.skeqi.mes.pojo.api.CheckProductionPT;

@Service
public class CheckAllRecipePServiceImpl implements CheckAllRecipePService {

	@Autowired
	private CheckAllRecipePDao checkAllRecipePDao;


	@Override
	public JSONObject checkClientLogin(String username, String password, String stationEmp, String lineEmpName,
			JSONObject jo) {
		Integer tempEmpCount = checkAllRecipePDao.queryEmpByNameAndVr(username,password);
		JSONObject data = new JSONObject();
		if(tempEmpCount>0) {

			CheckClientLoginPT checkClientLogin1= checkAllRecipePDao.queryCheckClientLogin1(username,password);

			CheckClientLoginPT checkClientLogin2= checkAllRecipePDao.queryCheckClientLogin2(lineEmpName,stationEmp);

			if(checkClientLogin1.getTempEmpStationId().indexOf(checkClientLogin2.getTempStId())>0
					&&checkClientLogin1.getTempEmpLineId()==checkClientLogin2.getTempLineId()) {


				Integer tempWorktimeCount = checkAllRecipePDao.queryTempWorkingCountByName(stationEmp);
				if(tempWorktimeCount>0) {

					CheckClientLoginPT checkClientLogin3= checkAllRecipePDao.queryCheckClientLogin3(stationEmp);

					if(checkClientLogin3.getTempWorktimeDtoff()!=null) {

						checkAllRecipePDao.insertEmpWorktime(checkClientLogin1.getTempEmpId(),stationEmp);
						jo.put("code", "0");
						jo.put("msg","");
						data.put("r", "0");
						data.put("rEmptype", checkClientLogin1.getTempEmpType());
						jo.put("data", data);
						return jo;

					}else {

						if(checkClientLogin3.getTempWorktimeEmpid()==checkClientLogin1.getTempEmpId()) {
							jo.put("code", "0");
							jo.put("msg","");
							data.put("r", "0");
							data.put("rEmptype", checkClientLogin1.getTempEmpType());
							jo.put("data", data);
							return jo;
						}else {
							jo.put("code", "0");
							jo.put("msg","");
							data.put("r", "0");
							data.put("rEmptype", "0");
							jo.put("data", data);
							return jo;
						}

					}
				}else {

					checkAllRecipePDao.insertEmpWorktime(checkClientLogin1.getTempEmpId(),stationEmp);
					jo.put("code", "0");
					jo.put("msg","");
					data.put("r", "0");
					data.put("rEmptype", checkClientLogin1.getTempEmpType());
					jo.put("data", data);
					return jo;

				}
			}else {

				jo.put("code", "0");
				jo.put("msg","");
				data.put("r", "0");
				data.put("rEmptype", "0");
				jo.put("data", data);
				return jo;
			}
		}else {

			jo.put("code", "61");
			jo.put("msg","产品工位路线涉及工位没有配置配方");
			data.put("r", "61");
			data.put("rEmptype", "0");
			jo.put("data", data);
			return jo;
		}


	}


	@Override
	public JSONObject checkAllRecipe(String snBarcode,Integer productionId, Integer productionLine, String stationType,
			Integer stationId, JSONObject jo) {

		JSONObject data = new JSONObject();
		//***//
		List<CheckAllRecipePT> checkAllRecipeList = checkAllRecipePDao.queryProductionWayList(snBarcode,productionLine,productionId);
		//如果此sn不在计划中则查询默认工艺路线
		if(checkAllRecipeList.size()==0){
			Integer findDefaultRouting = checkAllRecipePDao.findDefaultRouting(productionLine, productionId);
			Integer findDefaultTotal = checkAllRecipePDao.findDefaultTotal(productionLine, productionId);
			List<CheckAllRecipePT> findStId = checkAllRecipePDao.findStId(findDefaultRouting);

			for (CheckAllRecipePT checkAllRecipePT : findStId) {
				CheckAllRecipePT pt = new CheckAllRecipePT();
				if(findDefaultRouting!=null){
					pt.setRoutingId(findDefaultRouting);
				}else{
					pt.setRoutingId(0);
				}

				if(findDefaultTotal!=null){
					pt.setTotalRecipeId(findDefaultTotal);
				}else{
					pt.setTotalRecipeId(0);
				}
				pt.setStId(checkAllRecipePT.getStId());
				checkAllRecipeList.add(pt);
			}
		}
		if("0".equals(stationType)) {  //线内站

			for (CheckAllRecipePT checkAllRecipePT : checkAllRecipeList) {
				//***///
				Integer tempRecipeCount =  checkAllRecipePDao.queryCountId(checkAllRecipePT.getTotalRecipeId(),productionId,checkAllRecipePT.getStId());

				if(tempRecipeCount == 0) {

					jo.put("isSuccess", false);
					jo.put("errMsg", "产品工位路线涉及工位没有配置配方");
					data.put("r", "35");
					jo.put("result", data);
					return jo;
				}else {

					jo.put("isSuccess", true);
					jo.put("errMsg", "");
					data.put("r", "0");
					jo.put("result", data);
					//**工艺id   配方id    计划id//
					jo.put("routingId", checkAllRecipeList.get(0).getRoutingId());
					jo.put("totalRecipeId", checkAllRecipeList.get(0).getTotalRecipeId());
					jo.put("planId", checkAllRecipeList.get(0).getId());
				}



			}


		}else {
			Integer tempRecipeCount =  checkAllRecipePDao.queryCountId(checkAllRecipeList.get(0).getTotalRecipeId(),productionId,stationId);
			if(tempRecipeCount == 0) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "产品工位路线涉及工位没有配置配方");
				data.put("r", "35");
				jo.put("data", data);
				return jo;
			}else {
				jo.put("isSuccess", true);
				jo.put("errMsg", "");
				data.put("r", "0");
				jo.put("data", data);
				jo.put("routingId", checkAllRecipeList.get(0).getRoutingId());
				jo.put("totalRecipeId", checkAllRecipeList.get(0).getTotalRecipeId());
				jo.put("planId", checkAllRecipeList.get(0).getId());
			}


		}

		return jo;
	}


	@Override
	public JSONObject checkPlanSn(String snPlan,JSONObject jo) {
		JSONObject data = new JSONObject();

		Integer tempPlanPrintSnCount = checkAllRecipePDao.queryTempPlanPrintSnCount(snPlan);

		if(tempPlanPrintSnCount>0) {

			CheckPlanSnPT checkPlanSnPT1 = checkAllRecipePDao.queryCheckPlanSnPT1(snPlan);

			CheckPlanSnPT checkPlanSnPT2 = checkAllRecipePDao.queryCheckPlanSnPT2(checkPlanSnPT1.getTempWorkOrderId());

			String tempPlanStatus = checkAllRecipePDao.queryTempPlanStatus(checkPlanSnPT1.getTempPlanId());

			if("1".equals(tempPlanStatus)) {

				Integer tempPlanLevelBefore = checkAllRecipePDao.queryTempPlanLevelBefore(checkPlanSnPT2.getTempPlanLevel(), checkPlanSnPT1.getTempLineId());

				if(tempPlanLevelBefore>0) {

					CheckPlanSnPT checkPlanSnPT3 = checkAllRecipePDao.queryCheckPlanSnPT3(checkPlanSnPT1.getTempWorkOrderId(),checkPlanSnPT2.getTempPlanLevel(),checkPlanSnPT1.getTempLineId());
						if(checkPlanSnPT3.getTempPlanNumber()==checkPlanSnPT3.getTempPlanOnlineNumber()||"2".equals(checkPlanSnPT3.getTempWorkerOrderStatus())) {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
							data.put("r", "0");
							data.put("planId", checkPlanSnPT2.getTempPlanId());
							data.put("workOrderId", checkPlanSnPT1.getTempWorkOrderId());
							jo.put("result", data);
							return jo;
						}else {
							jo.put("isSuccess", false);
							jo.put("errMsg", "计划优先级不足");
							data.put("r", "31");
							jo.put("result", data);
							return jo;
						}
				}else {

					if("1".equals(checkPlanSnPT2.getTempWorkerOrderStatus())) {

						jo.put("isSuccess", true);
						jo.put("errMsg", "");
						data.put("r", "0");
						data.put("planId", checkPlanSnPT1.getTempPlanId());
						data.put("workOrderId", checkPlanSnPT1.getTempWorkOrderId());
						jo.put("result", data);
						return jo;

					}else {

						jo.put("isSuccess", false);
						jo.put("errMsg", "此条码所在计划异常");
						data.put("r", "32");
						jo.put("result", data);
						return jo;
					}
				}
			}else {

				jo.put("isSuccess", false);
				jo.put("errMsg", "此条码所在计划异常");
				data.put("r", "32");
				jo.put("result", data);
				return jo;


			}


		}else {
			jo.put("isSuccess", false);
			jo.put("errMsg", "此条码不在计划中");
			data.put("r", "30");
			jo.put("result", data);
			return jo;


		}
	}

	@Override
	public JSONObject checkWorkorderSn(String snPlan,JSONObject jo) {
		JSONObject data = new JSONObject();

		Integer tempPlanPrintSnCount = checkAllRecipePDao.queryTempPlanPrintSnCount(snPlan);

		if(tempPlanPrintSnCount>0) {

			CheckPlanSnPT checkPlanSnPT1 = checkAllRecipePDao.queryCheckPlanSnPT1(snPlan);

			CheckPlanSnPT checkPlanSnPT2 = checkAllRecipePDao.queryCheckWorkorderSnPT2(checkPlanSnPT1.getTempWorkOrderId());

			String tempPlanStatus = checkAllRecipePDao.queryTempWorkorderStatus(checkPlanSnPT1.getTempWorkOrderId());

			if("1".equals(tempPlanStatus)) {

				Integer tempPlanLevelBefore = checkAllRecipePDao.queryTempPlanLevelBefore(checkPlanSnPT2.getTempPlanLevel(), checkPlanSnPT1.getTempLineId());

				if(tempPlanLevelBefore>0) {

					CheckPlanSnPT checkPlanSnPT3 = checkAllRecipePDao.queryCheckWorkorderSnPT3(checkPlanSnPT1.getTempWorkOrderId(),checkPlanSnPT2.getTempPlanLevel(),checkPlanSnPT1.getTempLineId());
					if(checkPlanSnPT3.getTempPlanNumber()==checkPlanSnPT3.getTempPlanOnlineNumber()||"2".equals(checkPlanSnPT3.getTempWorkerOrderStatus())) {
						jo.put("isSuccess", true);
						jo.put("errMsg", "");
						data.put("r", "0");
						data.put("planId", checkPlanSnPT2.getTempPlanId());
						data.put("workOrderId", checkPlanSnPT1.getTempWorkOrderId());
						jo.put("result", data);
						return jo;
					}else {
						jo.put("isSuccess", false);
						jo.put("errMsg", "计划优先级不足");
						data.put("r", "31");
						jo.put("result", data);
						return jo;
					}
				}else {

					if("1".equals(checkPlanSnPT2.getTempWorkerOrderStatus())) {

						jo.put("isSuccess", true);
						jo.put("errMsg", "");
						data.put("r", "0");
						data.put("planId", checkPlanSnPT1.getTempPlanId());
						data.put("workOrderId", checkPlanSnPT1.getTempWorkOrderId());
						jo.put("result", data);
						return jo;

					}else {

						jo.put("isSuccess", false);
						jo.put("errMsg", "此条码所在计划异常");
						data.put("r", "32");
						jo.put("result", data);
						return jo;
					}
				}
			}else {

				jo.put("isSuccess", false);
				jo.put("errMsg", "此条码所在计划异常");
				data.put("r", "32");
				jo.put("result", data);
				return jo;


			}


		}else {
			jo.put("isSuccess", false);
			jo.put("errMsg", "此条码不在计划中");
			data.put("r", "30");
			jo.put("result", data);
			return jo;


		}
	}


	@Override
	public JSONObject checkProduction(String snProduction, String sId, JSONObject jo) {
		JSONObject data = new JSONObject();
		jo = new JSONObject();
		List<CheckProductionPT> list = checkAllRecipePDao.queryCheckProductionList(sId);
		Pattern pattern = null;
		Matcher matcher = null;
		int num = 0;
		if(list.size()==0){
			jo.put("isSuccess", false);
			jo.put("errMsg", "系统中不存在该产品");
			data.put("r", "5");
			jo.put("result", data);
			return jo;
		}
		for (CheckProductionPT checkProductionPT : list) {

			pattern = Pattern.compile(checkProductionPT.getProductionVr());
			matcher = pattern.matcher(snProduction);
			if(matcher.find()) {
				if(num==0) {
					num+=1;
					jo.put("isSuccess", true);
					jo.put("errMsg", "");
					data.put("checkProductionId", checkProductionPT.getId());
					data.put("onOff", checkProductionPT.getProductionSte());
					data.put("r", "0");
					jo.put("result", data);
				}else {
					jo.put("isSuccess", false);
					jo.put("errMsg", "系统中出现多种符合其规则的条码");
					data.put("r", "204");
					jo.put("result", data);
					return jo;
				}
			}
		}
		if(num==0)
		{
			jo.put("isSuccess", false);
			jo.put("errMsg", "系统中不存在该产品");
			data.put("r", "5");
			jo.put("result", data);
		}
		return jo;

	}

	@Override
	public JSONObject checkProduction1(String snProduction, String pId, JSONObject jo) {
		JSONObject data = new JSONObject();
		jo = new JSONObject();
		List<CheckProductionPT> list = checkAllRecipePDao.queryCheckProduction(pId);
		Pattern pattern = null;
		Matcher matcher = null;
		int num = 0;
		if(list.size()==0){
			jo.put("isSuccess", false);
			jo.put("errMsg", "系统中不存在该产品");
			data.put("r", "5");
			jo.put("result", data);
			return jo;
		}
		for (CheckProductionPT checkProductionPT : list) {

			pattern = Pattern.compile(checkProductionPT.getProductionVr());
			matcher = pattern.matcher(snProduction);
			if(matcher.find()) {
				if(num==0) {
					num+=1;
					jo.put("isSuccess", true);
					jo.put("errMsg", "");
					data.put("checkProductionId", checkProductionPT.getId());
					data.put("onOff", checkProductionPT.getProductionSte());
					data.put("r", "0");
					jo.put("result", data);
				}else {
					jo.put("isSuccess", false);
					jo.put("errMsg", "系统中出现多种符合其规则的条码");
					data.put("r", "204");
					jo.put("result", data);
					return jo;
				}
			}
		}
		if(num==0)
		{
			jo.put("isSuccess", false);
			jo.put("errMsg", "系统中不存在该产品");
			data.put("r", "5");
			jo.put("result", data);
		}
		return jo;

	}


	@Override
	public Integer findDefaultTotal(Integer lineId, Integer pid) {
		// TODO Auto-generated method stub
		return checkAllRecipePDao.findDefaultTotal(lineId,pid);
	}


	@Override
	public Integer findDefaultRouting(Integer lineId, Integer pid) {
		// TODO Auto-generated method stub
		return checkAllRecipePDao.findDefaultRouting(lineId,pid);
	}


	@Override
	public Integer findLineId(String lineName) {
		// TODO Auto-generated method stub
		return checkAllRecipePDao.findLineId(lineName);
	}


}
