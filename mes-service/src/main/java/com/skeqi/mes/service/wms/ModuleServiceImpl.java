package com.skeqi.mes.service.wms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.ModuleDao;
import com.skeqi.mes.util.Rjson;

/**
 * 模组Controller
 * @author 73414
 */
@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleDao dao;

	@Override
	public Rjson goOnline(String sn) {
		try {
			//查询计划打印表
			JSONObject planPrintJson = dao.findPlanPrint(sn);
			if(planPrintJson==null
					||planPrintJson.getInteger("id")==null
					||planPrintJson.getInteger("id")==0) {
				throw new Exception("sn不存在");
			}

			//查询计划表
			JSONObject planJson = dao.findPlan(planPrintJson.getInteger("planId"));
			if(planJson==null
					||planJson.getInteger("id")==null
					||planJson.getInteger("id")==0) {
				throw new Exception("计划不存在");
			}
			planJson.put("onlineNumber",planJson.getInteger("onlineNumber"));

			//更新计划表数据
			Integer res = dao.updatePlan(planJson);
			if(res!=1) {
				throw new Exception("更新计划表数据失败");
			}

			//查询工单表数据
			JSONObject jsonWorkorderDetail = dao.findWorkorderDetail(planPrintJson.getInteger("workOrderId"));
			if(jsonWorkorderDetail==null
					|| jsonWorkorderDetail.getInteger("id")==null
					|| jsonWorkorderDetail.getInteger("id")==0) {
				throw new Exception("工单不存在或者已关闭");
			}

			//工单上线数量+1
			jsonWorkorderDetail.put("onlineNumber", jsonWorkorderDetail.getInteger("onlineNumber")+1);

			//更新工单表数据
			res = dao.updateWorkorderDetail(jsonWorkorderDetail);
			if(res!=1) {
				throw new Exception("更新工单表数据失败");
			}

			return Rjson.success();
		} catch (Exception e) {
			return Rjson.error(e.getMessage());
		}
	}

	@Override
	public Rjson offline(String sn,String result) {
		try {
			Integer res = 0;

			//查询计划打印表
			JSONObject planPrintJson = dao.findPlanPrint(sn);

			//查询计划表
			JSONObject planJson = dao.findPlan(planPrintJson.getInteger("planId"));
			if(planJson==null
					||planJson.getInteger("id")==null
					||planJson.getInteger("id")==0) {
				throw new Exception("计划不存在");
			}

			//判断OK还是NG
			if(result.equals("OK")) {
				planJson.put("okNumber", planJson.getInteger("okNumber")+1);
			}else {
				planJson.put("ngNumber", planJson.getInteger("ngNumber")+1);
			}

			//完成数量+1
			planJson.put("completeNumber", planJson.getInteger("completeNumber")+1);
			//剩余数量-1
			planJson.put("remaindNumber", planJson.getInteger("remaindNumber")+1);

			//更新下线计划表数据
			res = dao.updatePlan(planJson);
			if(res!=1) {
				throw new Exception("更新下线数据失败");
			}

			//查询工单表数据
			JSONObject jsonWorkorderDetail = dao.findWorkorderDetail(planPrintJson.getInteger("workOrderId"));
			if(jsonWorkorderDetail==null
					|| jsonWorkorderDetail.getInteger("id")==null
					|| jsonWorkorderDetail.getInteger("id")==0) {
				throw new Exception("工单不存在或者已关闭");
			}

			//工单下线数量+1
			jsonWorkorderDetail.put("offlineNumber", jsonWorkorderDetail.getInteger("offlineNumber")+1);

			//判断工单完成数量
			if(jsonWorkorderDetail.getInteger("onlineNumber")>=jsonWorkorderDetail.getInteger("orderNumber")) {
				//工单完成后修改状态为关闭（4）
				jsonWorkorderDetail.put("status", 4);
			}

			//更新工单表数据
			res = dao.updateWorkorderDetail(jsonWorkorderDetail);
			if(res!=1) {
				throw new Exception("更新工单表数据失败");
			}

			//如果计划完成
			if(planJson.getInteger("remaindNumber")<=0) {

				//新增计划P表
				res = dao.addPPlan(planJson);
				//删除计划R表
				res = dao.deletePlan(planJson.getInteger("id"));

				//计划打印
				List<JSONObject> planPrintJsonList = dao.findPlanPrintList(planPrintJson.getInteger("planId"));
				for (JSONObject jsonObject : planPrintJsonList) {
					res = dao.addPPlanPrint(jsonObject);
					res = dao.deletePlanPrint(jsonObject.getInteger("id"));
				}

				//工单详情
				List<JSONObject> jsonWorkorderDetailList = dao.findWorkorderDetailList(planPrintJson.getInteger("planId"));
				for (JSONObject jsonObject : jsonWorkorderDetailList) {
					res = dao.addPWorkorderDetail(jsonObject);
					res = dao.deleteWorkorderDetail(jsonObject.getInteger("id"));
				}

			}

			return Rjson.success();
		} catch (Exception e) {
			return Rjson.error(e.getMessage());
		}
	}

}
