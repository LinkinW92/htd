package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skeqi.mes.util.aop.OptionalLog;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.CheckSnPT;
import com.skeqi.mes.pojo.api.UpdateSnPT;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.CheckAllRecipePService;
import com.skeqi.mes.service.all.CheckSnPService;
import com.skeqi.mes.service.all.UpdatePlanPService;
import com.skeqi.mes.service.all.UpdateSnPService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * @date 2020年1月13日
 * @author yinp 总成下线
 */
@RestController
@RequestMapping("api")
public class UpdateSnPController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	UpdateSnPService service;

	@Autowired
	CheckAllRecipePService pService;

	@Autowired
	EventService serviceEvent;

	@Autowired
	UpdatePlanPService moveDataPServiceImpl;

	@Autowired
	UpdatePlanPService planService;

	@Autowired
	private CheckSnPService checksnService;

	/**
	 * 入参
	 */

	/**
	 * 出参
	 */
	Integer r;
	/**
	 * 临时变量
	 */
	Integer workId;// 工单id
	Integer tempStationCount;// 工位记录条数
	Integer tempTrackingId;
	Integer tempTrackingCount;// 记录条数
	String tempTrackingEnginesn;// 产品是否返工
	String tempTrackingStatus;// 产品状态
	Integer tempTrackingProductionId;// 产品id
	String tempTrackingGearbosxn;// 经过工位下标
	String tempTrackingStationName;// 上一个工位
	Integer tempStationId;// 工位id
	String tempStationName;// 工位名称
	String tempStationIndex;// 工位下标
	String tempStationType;// 是否线外站
	String tempStationAutoornot;// 工位业务类型
	Integer tempStationLineId;// 产线id
	String tempStationEndornot;// 是否末站
	Integer tempPlanId;
	String tempDt;
	String tempReworkFlag;
	Integer tempCompletePlanCout;
	Integer tempCompleteOkPlanCount;
	Integer tempPlanNumber;
	Integer tempCahe;
	String tempOnOff;
	Integer tempRProduction;
	Integer tempWorkOrderId;
	Integer tempWorkOrderOffline;//
	Integer tempWorkOrderNumber;//
	Integer tempProductionSerialMax;// 工艺路线工位末站
	Integer tempProductionSerialMin;// 工艺路线工位首站
	Integer tempPwayStationSerialNo;// 产品工艺路线顺序
	Integer totalRecipeId; // 总配方ID
	Integer routingId; // 工艺路线ID
	String rMessage;

	public UpdateSnPController() {
		r = 0;
		// TODO Auto-generated constructor stub
	}

	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();

	@Transactional
	@RequestMapping(value = "updateSn", method = RequestMethod.POST)
	@OptionalLog(module="生产", module2="生产模拟", method="下线")
	public void updateSnP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		String snBarconde;
		String stationname;
		String lineName;
		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
//			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("updateSnP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			snBarconde = json.get("sn").toString();
			stationname = json.get("station").toString();
			lineName = json.get("line").toString();
		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg") + "生成日志出错了!");
				System.err.println("生成日志出错了");

				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main(snBarconde, stationname, lineName);
			if (jo.getString("errMsg") == null || jo.getString("errMsg").equals("")) {
				jo.put("isSuccess", true);
				jo.put("errMsg", "");
				jo.put("result", true);
				joz.put("code", "0");
			}else {
				throw new Exception(jo.getString("errMsg"));
			}

			// 事件添加
//			Map<String, Object> map = new HashMap<>();
//			map.put("NAME", lineName);
//			Map<String, Object> mapResult = serviceEvent.getLineCode(map);
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "成品");
			mapEvent.put("OBJECT_ID", snBarconde);
			mapEvent.put("EVENT", "总成下线");
//			if(mapResult != null){
//				mapEvent.put("PARAMETER1", mapResult.get("code"));
//			}
			serviceEvent.addEvent(mapEvent);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			jo.put("result", false);
//			joz.put("code", "17");
			jo.put("errMsg", e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
//			joz.put("r", r);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	public synchronized void main(String snBarcondes, String stationnames, String lineNames) {
		synchronized (this) {
			String snBarconde = snBarcondes;
			String stationname = stationnames;
			String lineName = lineNames;
			UpdateSnPT updateSnPT = service.find1(stationname, lineName);
			// 判断工位是否存在
			tempStationCount = Integer.parseInt(updateSnPT.getTempStationCount());
			if (tempStationCount > 0) {
				UpdateSnPT dx = service.find2(stationname, lineName);
				tempStationId = Integer.parseInt(dx.getTempStationId());
				if (dx.getTempStationType().equals("1")) {
					// 线外站

					// try {
					/**
					 * 调用MoveDataP 执行末站
					 */
					moveDataPServiceImpl.mainData(snBarconde);
					// } catch (Exception e) {
					// e.printStackTrace();
					// jo.put("msg", "调用MoveDataP出错了");
					// throw new Exception();
					// }

					dx.setSnBarconde(snBarconde);
					service.insert1(dx);
					service.delete1(dx.getTempStationName(), snBarconde, lineName);
					// 删除临时物料表
					service.delete2(snBarconde);
					// 删除临时螺栓表
					service.delete3(snBarconde);
					// 删除临时气密性表
					service.delete4(snBarconde);
					return;
				}
				// 线内站
				// 判断总成是否上线
				UpdateSnPT a = service.find3(snBarconde);
				tempTrackingCount = Integer.parseInt(a.getTempTrackingCount());
				if (tempTrackingCount > 0) {
					// **查询工艺路线ID和配方ID***//
					RMesPlanT findAllPlan = service.findAllPlan(snBarconde);
					if (findAllPlan != null) {
						totalRecipeId = findAllPlan.getTotalRecipeId();
						routingId = findAllPlan.getRoutingId();
					} else {
						CheckSnPT checkSnPT = checksnService.queryCheckSnPTByLineAndStation1(lineName, stationname);
						tempStationId = checkSnPT.getTempStationId();
						Integer tempStationLineId2 = checkSnPT.getTempStationLineId();
						JSONObject jo = null;
						JSONObject checkProduction = pService.checkProduction(snBarconde,
								checkSnPT.getTempStationId().toString(), jo);
						routingId = pService.findDefaultRouting(tempStationLineId2,
								checkProduction.getJSONObject("result").getInteger("checkProductionId"));
						totalRecipeId = pService.findDefaultTotal(tempStationLineId2,
								checkProduction.getJSONObject("result").getInteger("checkProductionId"));
					}

					// 获取上线表数据
					UpdateSnPT b = service.find4(snBarconde);
					tempTrackingId = Integer.parseInt(b.getTempTrackingId());
					tempTrackingStationName = b.getTempTrackingStationName();
					tempTrackingEnginesn = b.getTempTrackingEnginesn();
					tempTrackingGearbosxn = b.getTempTrackingGearbosxn();
					tempTrackingStatus = b.getTempTrackingStatus();
					tempTrackingProductionId = Integer.parseInt(b.getTempTrackingProductionId());
					tempStationLineId = Integer.parseInt(b.getTempStationLineId());
					tempPlanId = Integer.parseInt(b.getTempPlanId());
					tempDt = b.getTempDt();
					tempReworkFlag = b.getTempReworkFlag();
					if (b.getTempWorkOrderId() != null && b.getTempWorkOrderId() != "") {
						tempWorkOrderId = Integer.parseInt(b.getTempWorkOrderId());
					} else {
						tempWorkOrderId = 0;
					}

					// 调用存储过程
					// C_MES_CHECK_PRODUCTION_P(SN_BARCONDE,TEMP_CAHE,TEMP_ON_OFF,TEMP_R_PRODUCTION,TEMP_STATION_ID
					// );

					JSONObject json2 = new JSONObject();
//				json2 = pService.checkProduction(snBarconde, dx.getTempStationId(), json2);
					json2 = pService.checkProduction1(snBarconde, tempTrackingProductionId.toString(), json2);
					JSONObject json22 = new JSONObject();
					json22 = json2.getJSONObject("result");
					if (json22.getString("r").equals("0")) {
						json2 = json2.getJSONObject("data");
						tempCahe = Integer.parseInt(json22.getString("checkProductionId"));
						tempOnOff = json22.getString("onOff");
					} else {
						jo.put("errMsg", "调用checkProduction出错了");
						return;
					}

					if (dx.getTempStationType() != null && !dx.getTempStationType().equals("")
							&& dx.getTempStationType().equals("0")) {
						// **查询工艺路线的首站和末站**//
						UpdateSnPT c = service.find5(routingId);
						tempProductionSerialMax = Integer.parseInt(c.getTempProductionSerialMax());
						tempProductionSerialMin = Integer.parseInt(c.getTempProductionSerialMin());

						// **查询此工位是第几站***//
						UpdateSnPT d = service.find6(routingId, dx.getTempStationId());
						tempPwayStationSerialNo = Integer.parseInt(d.getTempPwayStationSerialNo());

						if (tempTrackingStatus.equals("OK")) {

							// 更新步序完成情况状态
							Map<String, Object> map1 = new HashMap<>();
							map1.put("SN", snBarconde);
							map1.put("ST", dx.getTempStationId());
							service.updateStationSerialFlag(map1);

							if (tempPwayStationSerialNo == tempProductionSerialMax) {
								// 末站

								/**
								 * 调用MoveDataPController
								 */
								moveDataPServiceImpl.mainData(snBarconde);

								Integer resultInsert3 = service.insert3(snBarconde, dx.getTempStationName(),
										tempStationLineId.toString());

								// 插入物料成品
								Map<String, Object> map = new HashMap<>();
								map.put("SN", snBarconde);
								map.put("productionId", tempTrackingProductionId);
								map.put("workorderId", tempWorkOrderId);
								service.insertMaterialInstance(map);

								if (tempOnOff.equals("0")) {

									if (tempTrackingEnginesn.equals("5")) {
										// 表示在翻修站返修

										UpdateSnPT adx = new UpdateSnPT();
										adx.setTempDt(tempDt);
										adx.setStationname(stationname);
										adx.setSnBarconde(snBarconde);
										adx.setTempTrackingEnginesn(tempTrackingEnginesn);
										adx.setTempTrackingGearbosxn(tempTrackingGearbosxn);
										adx.setTempTrackingStatus(tempTrackingStatus);
										adx.setTempPlanId(tempPlanId.toString());
										adx.setTempReworkFlag(tempReworkFlag);
										adx.setTempTrackingProductionId(tempTrackingProductionId.toString());
										adx.setTempStationLineId(tempStationLineId.toString());
										adx.setTempWorkOrderId(tempWorkOrderId.toString());

										Integer resultInsert1 = service.insert1(adx);

									} else {

										// 更新计划
										// 查询计划的数量、完成数量、合格数量
										UpdateSnPT adx = service.find7(tempPlanId.toString());
										tempPlanNumber = Integer.parseInt(adx.getTempPlanNumber());
										tempCompletePlanCout = Integer.parseInt(adx.getTempCompletePlanCout());
										tempCompleteOkPlanCount = Integer.parseInt(adx.getTempCompleteOkPlanCount());

										// 当完成数量与订单数量相同时，工单完成
										if (tempPlanNumber == (tempCompletePlanCout + 1)) {
											tempWorkOrderOffline = service.findOfflineNumber(tempWorkOrderId);
											Integer resultupdate2 = service.update2(tempWorkOrderOffline.toString(),
													tempCompletePlanCout.toString(), tempCompleteOkPlanCount.toString(),
													tempWorkOrderId.toString());
//										Integer resultupdate3 = service.update3(tempCompletePlanCout.toString(), tempCompleteOkPlanCount.toString(), tempPlanId.toString());

											UpdateSnPT bdx = new UpdateSnPT();
											bdx.setTempDt(tempDt);
											bdx.setStationname(stationname);
											bdx.setSnBarconde(snBarconde);
											bdx.setTempTrackingEnginesn(tempTrackingEnginesn);
											bdx.setTempTrackingGearbosxn(tempTrackingGearbosxn);
											bdx.setTempTrackingStatus(tempTrackingStatus);
											bdx.setTempPlanId(tempPlanId.toString());
											bdx.setTempReworkFlag(tempReworkFlag);
											bdx.setTempTrackingProductionId(tempTrackingProductionId.toString());
											bdx.setTempStationLineId(tempStationLineId.toString());
											bdx.setTempWorkOrderId(tempWorkOrderId.toString());

											planService.insertAll(tempPlanId, tempStationLineId);

											workId = Integer.parseInt(service.find8(snBarconde).getWorkId());
//										Integer resultdelete6 = service.delete6(tempPlanId.toString());
											Integer resultdelete7 = service.delete7(tempPlanId.toString());
											Integer resultdelete8 = service.delete8(tempPlanId);

										} else {
											Integer resultupdate6 = service.update4(tempCompletePlanCout.toString(),
													tempCompleteOkPlanCount.toString(), tempPlanId.toString());
											UpdateSnPT dx9 = service.find9(tempWorkOrderId.toString());
											if (dx9 == null) {
												tempWorkOrderNumber = 0;
												tempWorkOrderOffline = 0;
											} else {
												tempWorkOrderNumber = Integer.parseInt(dx9.getTempWorkOrderNumber());
												tempWorkOrderOffline = Integer.parseInt(dx9.getTempWorkOrderOffline());
											}

//										planService.insertPrint(snBarconde);
											UpdatePlanPController MoveOrder = new UpdatePlanPController();
											MoveOrder.runOrderId = tempWorkOrderId.toString();
											MoveOrder.runPrintCode = snBarconde;

											if (tempWorkOrderNumber == (tempWorkOrderOffline + 1)) {
												service.update1(dx.getTempStationName(), tempTrackingStatus,
														tempStationIndex, snBarconde);
											}
											service.update6(tempWorkOrderOffline.toString(),
													tempWorkOrderId.toString());

										}
										UpdateSnPT insertDx6 = new UpdateSnPT();
										insertDx6.setTempDt(tempDt);
										insertDx6.setStationname(stationname);
										insertDx6.setSnBarconde(snBarconde);
										insertDx6.setTempTrackingEnginesn(tempTrackingEnginesn);
										insertDx6.setTempTrackingGearbosxn(tempTrackingGearbosxn);
										insertDx6.setTempTrackingStatus(tempTrackingStatus);
										insertDx6.setTempPlanId(tempPlanId.toString());
										insertDx6.setTempReworkFlag(tempReworkFlag);
										insertDx6.setTempTrackingProductionId(tempTrackingProductionId.toString());
										insertDx6.setTempStationLineId(tempStationLineId.toString());
										insertDx6.setTempWorkOrderId(tempWorkOrderId.toString());
										// 更新永久运行时表
										Integer resultinsert6 = service.insert6(insertDx6);
									}

								} else {
									UpdateSnPT insertDx7 = new UpdateSnPT();
									insertDx7.setTempDt(tempDt);
									insertDx7.setStationname(stationname);
									insertDx7.setSnBarconde(snBarconde);
									insertDx7.setTempTrackingEnginesn(tempTrackingEnginesn);
									insertDx7.setTempTrackingGearbosxn(tempTrackingGearbosxn);
									insertDx7.setTempTrackingStatus(tempTrackingStatus);
									insertDx7.setTempPlanId(tempPlanId.toString());
									insertDx7.setTempReworkFlag(tempReworkFlag);
									insertDx7.setTempTrackingProductionId(tempTrackingProductionId.toString());
									insertDx7.setTempStationLineId(tempStationLineId.toString());
									insertDx7.setTempWorkOrderId(tempWorkOrderId.toString());
									Integer resultinsert7 = service.insert7(insertDx7);
								}
								// 删除步序缓存
								Integer resultdelete9 = service.delete9(dx.getTempStationName(), snBarconde, lineName);
								// 删除临时运行时表
								Integer resultdelete10 = service.delete10(snBarconde);
								// 删除临时物料表
								Integer resultdelete11 = service.delete11(snBarconde);
								// 删除临时螺栓表
								Integer resultdelete12 = service.delete12(snBarconde);
								// 删除临时气密性表
								Integer resultdelete13 = service.delete13(snBarconde);
								return;
							}
							// 首站
							if (tempProductionSerialMin == tempPwayStationSerialNo) {
								// 更新运行时表
								Integer resultupdate1 = service.update1(dx.getTempStationName(), tempTrackingStatus,
										tempStationIndex, snBarconde);

								// 跟新过站信息
								Integer resultinsert2 = service.insert2(snBarconde, dx.getTempStationName(),
										tempStationLineId.toString());

								// 删除步序缓存
								Integer resultdelete5 = service.delete5(dx.getTempStationName(), snBarconde, lineName);
								return;
							}

							// 中间站
							if (tempProductionSerialMax > tempPwayStationSerialNo
									&& tempPwayStationSerialNo > tempProductionSerialMin) {
								// 更新过站信息
								Integer resultinsert8 = service.insert8(snBarconde, dx.getTempStationName(),
										tempStationLineId.toString());
								// 更新运行时表
								Integer resultupdate7 = service.update7(dx.getTempStationName(), tempTrackingStatus,
										tempStationIndex, snBarconde);
								// 删除步序缓存
								Integer resultdelete14 = service.delete14(dx.getTempStationName(), snBarconde,
										lineName);
								return;
							}

						} else {

							if (tempTrackingEnginesn.equals("3")) {

								UpdateSnPT findDx10 = service.find10(routingId);
								tempProductionSerialMax = Integer.parseInt(findDx10.getTempProductionSerialMax());
								tempProductionSerialMin = Integer.parseInt(findDx10.getTempProductionSerialMin());
								// 是否有工艺路线
								tempPwayStationSerialNo = Integer.parseInt(service
										.find11(routingId, tempStationId.toString()).getTempPwayStationSerialNo());

								if (tempProductionSerialMin == tempPwayStationSerialNo) {
									// 更新运行时表
									Integer resultupdate8 = service.update8(dx.getTempStationName(), tempStationIndex,
											snBarconde);
									// 更新过站信息
									Integer resultinsert9 = service.insert9(snBarconde, dx.getTempStationName(),
											tempStationLineId.toString());
									// 删除步序缓存
									Integer resultdelete15 = service.delete15(dx.getTempStationName(), snBarconde,
											lineName);
									return;
								}

							} else {

								// 更新过站信息
								Integer resultInsert3 = service.insert10(snBarconde, dx.getTempStationName(),
										tempTrackingStatus, tempStationLineId.toString());
								Integer resultupdate9 = service.update9(dx.getTempStationName(), tempStationIndex,
										snBarconde);
								Integer resultdelete16 = service.delete16(dx.getTempStationName(), snBarconde,
										lineName);
								return;
							}

						}

					}
				} else {
					// 该总成没有上线
					r = 12;
					return;
				}
			} else {
				// 不存在传入的工位
				r = 11;
				return;
			}
		}
	}

}
