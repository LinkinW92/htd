package com.skeqi.mes.service.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.all.InitializeDataPController;
import com.skeqi.mes.controller.all.UpdatePlanPController;
import com.skeqi.mes.mapper.all.CheckAllRecipePDao;
import com.skeqi.mes.mapper.all.CheckSnPDao;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.api.CheckSnPT;
import com.skeqi.mes.pojo.api.UpdateReworkSnPT;

@Service
public class CheckSnPServiceImpl implements CheckSnPService {

	@Autowired
	private CheckSnPDao checkSNPDao;

	@Autowired
	private CheckAllRecipePDao checkAllRecipeDao;

	@Autowired
	CheckAllRecipePService checkAllRecipePService;

	@Autowired
	InitializeDataPService initializeDataPServiceImpl;

	@Autowired
	CheckAllRecipePService checkPlanSnPService;

	@Autowired
	InitializeDataPService dataPService;

	// 记录条数
	private Integer tempTrackingCount;
	// 工位记录条数
	private Integer tempStationCount;
	// 产品工艺路线记录条数
	private Integer tempPwayCount;
	// 返修工艺路线记录条数
	private Integer tempReworkWayCount;
	//
	private Integer tempTrackingId;
	// 产品是否返工
	private String tempTrackingEnginesn;
	// 产品状态
	private String tempTrackingStatus;
	// 产品id
	private Integer tempTrackingProductionId;
	// 经过工位下标
	private String tempTrackingGearbosxn;
	// 上一个工位
	private String tempTrackingStationName;
	// 工位id
	private Integer tempStationId;
	// 工位名称
	private String tempStationName;
	// 工位下标
	private Integer tempStationIndex;
	// 是否线外站
	private String tempStationType;
	// 工位业务类型
	private String tempStationAutoornot;
	// 产线id
	private Integer tempStationLineId;
	// 是否末站
	private String tempStationEndornot;
	// 返修工位
	private Integer tempReworkStationId;
	// 返修工位顺序
	private Integer tempReworkStationSerialNo;
	// 返修路线前一工位
	private String tempReworkBeforeName;
	// 返修前一工位顺序
	private Integer tempReworkBeforeSerialNo;
	// 配方数量
	private Integer tempRecipeCount;
	// 工艺路线工位ID
	private Integer tempPwayStationId;
	// 产品工艺路线顺序
	private Integer tempPwayStationSerialNo;
	// 路线前工位名称
	private String tempPwayBeforeStationName;
	// 产品工艺路线前工位顺序
	private Integer tempPwayBeforeSerialNo;
	// 当前计划ID
//	private Integer tempPlanId;
	private Integer tempOnlineNumber;
	private String tempTrackingReworkFlag;
	private Integer tempPTrackingCout;
	// 产品在线、离线标记
	private String tempOnOff;
	private Integer tempWorkOrderId;
	private Integer tempWorkOrderOffline;
	// 工艺路线工位末站
	private Integer tempProductionSerialMax;
	// 工艺路线工位首站
	private Integer tempProductionSerialMin;
	// **工艺路线id**//
	private Integer routingId;
	// **总配方id**//
	private Integer totalRecipeId;

	private Integer r = 0;

	private Integer pass = 0;

	private Integer productionId = 0;
	@Autowired
	CheckSnPDao dao;

	@Override
	public UpdateReworkSnPT find1(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find1(snBarconde);
	}

	@Override
	public UpdateReworkSnPT find2(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find2(snBarconde);
	}

	@Override
	public void update1(String snBarconde) {
		// TODO Auto-generated method stub
		dao.update1(snBarconde);
	}

	@Override
	public void insert1(UpdateReworkSnPT dx) {
		// TODO Auto-generated method stub
		dao.insert1(dx);
	}

	@Override
	public void delete1(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete1(snBarconde);
	}

	@Override
	public void delete2(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete2(snBarconde);
	}

	@Override
	public void delete3(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete3(snBarconde);
	}

	@Override
	public void delete4(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete4(snBarconde);
	}

	@Override
	public void delete5(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete5(snBarconde);
	}

	@Override
	public UpdateReworkSnPT find3(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find3(snBarconde);
	}

	@Override
	public void update2(String snBarconde) {
		// TODO Auto-generated method stub
		dao.update2(snBarconde);
	}

	@Override
	public void delete6(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete6(snBarconde);
	}

	@Override
	public void delete7(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete7(snBarconde);
	}

	@Override
	public void delete8(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete8(snBarconde);
	}

	@Override
	public void delete9(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete9(snBarconde);
	}

	@Override
	public void delete10(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete10(snBarconde);
	}

	@Override
	public UpdateReworkSnPT find4(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find4(snBarconde);
	}

	@Override
	public UpdateReworkSnPT find5(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find5(snBarconde);
	}

	@Override
	public void insert2(UpdateReworkSnPT dx) {
		// TODO Auto-generated method stub
		dao.insert2(dx);
	}

	@Override
	public void insert3(UpdateReworkSnPT dx) {
		// TODO Auto-generated method stub
		dao.insert3(dx);
	}

	@Override
	public void insert4(UpdateReworkSnPT dx) {
		// TODO Auto-generated method stub
		dao.insert4(dx);
	}

	@Override
	public UpdateReworkSnPT find6(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.find6(snBarconde);
	}

	@Override
	public void insert5(UpdateReworkSnPT dx) {
		// TODO Auto-generated method stub
		dao.insert5(dx);
	}

	@Override
	public void delete11(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete11(snBarconde);
	}

	@Override
	public void delete12(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete12(snBarconde);
	}

	@Override
	public void delete13(String snBarconde) {
		// TODO Auto-generated method stub
		dao.delete13(snBarconde);
	}

	@Override
	public void updateRMesReworkSn(String sn) {
		// TODO Auto-generated method stub
		dao.updateRMesReworkSn(sn);
	}

	@Override
	public List<UpdateReworkSnPT> insert2Find(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.insert2Find(snBarconde);
	}

	@Override
	public List<UpdateReworkSnPT> insert3Find(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.insert3Find(snBarconde);
	}

	@Override
	public List<UpdateReworkSnPT> insert4Find(String snBarconde) {
		// TODO Auto-generated method stub
		return dao.insert4Find(snBarconde);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public JSONObject checkSN(String snBarcode, String station, String line, JSONObject jo) {

		JSONObject data = new JSONObject();
		JSONObject data2 = new JSONObject();

		// 查询工位信息，确定是首站、中站、末站
		// 查询工位记录条数
		tempStationCount = checkSNPDao.queryTempStationCountByLineAndStation(line, station);
		/// ***根据条码查询工单ID***//
		CheckSnPT csp = checkSNPDao.findWorkId(snBarcode);
		tempWorkOrderId = csp.getTempWorkOrderId();
		tempTrackingProductionId = csp.getTempTrackingProductionId();

		// 判断此工位是否存在
		if (tempStationCount > 0) {

			// 查询工位信息
			CheckSnPT checkSnPT = checkSNPDao.queryCheckSnPTByLineAndStation1(line, station);

			tempStationId = checkSnPT.getTempStationId();
			tempStationIndex = checkSnPT.getTempStationIndex();
			tempStationName = checkSnPT.getTempStationName();
			tempStationType = checkSnPT.getTempStationType();
			tempStationAutoornot = checkSnPT.getTempStationAutoornot();
			tempStationLineId = checkSnPT.getTempStationLineId();
			tempStationEndornot = checkSnPT.getTempStationEndornot();

//			ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//			checkPlanSnPService = (checkPlanSnPServiceImpl) ap.getBean("checkPlanSnPServiceImpl");

			// 校验此条码是否能匹配产品的正则表达式
//			data2 = checkPlanSnPService.checkProduction(snBarcode, checkSnPT.getTempStationId().toString(), data2);
			data2 = checkPlanSnPService.checkProduction1(snBarcode, tempTrackingProductionId.toString(), data2);

			r = Integer.parseInt(data2.getJSONObject("result").getString("r"));
			if (r == 0) {
				// 匹配到的产品id
//				tempTrackingProductionId = Integer.parseInt(data2.getJSONObject("result").getString("checkProductionId"));
				tempOnOff = data2.getString("onOff");
			} else if (r == 5) {
				return data2;
			}

			// 此条码有对应的产品
			if (tempTrackingProductionId > 0 && r == 0) {

				// 判断是否是线外站
				if ("1".equals(tempStationType)) {
					tempTrackingCount = checkSNPDao.queryTempTrackingCount(snBarcode, station);

					// 查询是否已经上线
					if (tempTrackingCount == 1) {

						productionId = tempTrackingProductionId;
						data.clear();
						jo.put("isSuccess", true);
						jo.put("errMsg", "");
						data.put("r", r);
						data.put("pass", pass);
						data.put("productionId", productionId);
						jo.put("result", data);
						return jo;

					} else {

						CheckAllRecipePServiceImpl checkAllRecipePService = new CheckAllRecipePServiceImpl();
						data2.clear();
						// 查询该工位是否在工艺路线内
						data2 = checkAllRecipePService.checkAllRecipe(snBarcode, tempTrackingProductionId,
								tempStationLineId, tempStationType, tempStationId, data2).getJSONObject("result");
						r = Integer.parseInt(data2.getString("r"));
						// **工艺路线id**//
						routingId = Integer.parseInt(data2.getString("routingId"));
						// **总配方id***//
						totalRecipeId = Integer.parseInt(data2.getString("totalRecipeId"));
						// **计划id**//
//						tempPlanId = Integer.parseInt(data2.getString("planId"));

						// 初始化各工位配方数据值
						if (r == 0) {
							InitializeDataPController initializeDataPController = new InitializeDataPController();
							initializeDataPController.iniProductionId = tempTrackingProductionId;
							initializeDataPController.productionInit = tempStationLineId;
							initializeDataPController.stationType = tempStationType;
							initializeDataPController.stationInId = tempStationId;
							initializeDataPController.snIni = snBarcode;
							initializeDataPController.routingId = routingId;
							initializeDataPController.totalRecipeId = totalRecipeId;
							initializeDataPController.main(dataPService);
							r = initializeDataPController.rIni;

							if (r == 0) {

								// -插入上线信息
//								if(tempPlanId==null||tempPlanId.equals("")){
//									tempPlanId = 0;
//								}
								// 插入总成表
								checkSNPDao.insertTracking(station, snBarcode, tempStationIndex, null,
										tempTrackingProductionId, tempStationLineId, tempWorkOrderId);

								// 插入过站信息记录
								checkSNPDao.insertStationPass(snBarcode, tempStationName, tempStationLineId);

								productionId = tempTrackingProductionId;
								data.clear();
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
								data.put("r", r);
								data.put("pass", pass);
								data.put("productionId", productionId);
								jo.put("result", data);
								return jo;
							} else {
								data.clear();
								jo.put("isSuccess", false);
								jo.put("errMsg", "回滚");
								data.put("r", r);
								data.put("pass", pass);
								data.put("productionId", productionId);
								jo.put("result", data);
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return jo;
							}

						} else {

							// 此产品工艺路线工位没有配置配方
							r = 8;
							productionId = tempTrackingProductionId;
							data.clear();
							jo.put("isSuccess", false);
							jo.put("errMsg", "此产品工艺路线工位没有配置配方");
							data.put("r", r);
							data.put("pass", pass);
							data.put("productionId", productionId);
							jo.put("result", data);
							return jo;

						}

					}

				} else { // 线内站
					// **根据sn查询工艺和配方id**//
					RMesPlanT findAllPlan = checkSNPDao.findAllPlan(snBarcode);
					if (findAllPlan != null) {
						routingId = findAllPlan.getRoutingId();
						totalRecipeId = findAllPlan.getTotalRecipeId();
					} else {
						routingId = checkAllRecipeDao.findDefaultRouting(tempStationLineId, tempTrackingProductionId);
						totalRecipeId = checkAllRecipeDao.findDefaultTotal(tempStationLineId, tempTrackingProductionId);
					}

					if (routingId == null || routingId.equals("")) {
						data.clear();
						jo.put("isSuccess", false);
						jo.put("errMsg", "此计划没有设置工艺路线并且此产品没有默认工艺路线");
						data.put("r", r);
						data.put("pass", pass);
						data.put("productionId", productionId);
						jo.put("result", data);
						return jo;
					}
					if (totalRecipeId == null || totalRecipeId.equals("")) {
						data.clear();
						jo.put("isSuccess", false);
						jo.put("errMsg", "此计划没有设置总配方并且此产品没有配置默认总配方");
						data.put("r", r);
						data.put("pass", pass);
						data.put("productionId", productionId);
						jo.put("result", data);
						return jo;
					}

					// 查询此工位是否在工艺路线内////
					Integer tempPwayCount = checkSNPDao.queryTempPwayCount(routingId, tempStationId);

					if (tempPwayCount > 0) {

						// **查询首站和末站**//
						CheckSnPT checkSn2 = checkSNPDao.queryTempProduction(routingId);
						tempProductionSerialMax = checkSn2.getTempProductionSerialMax();
						tempProductionSerialMin = checkSn2.getTempProductionSerialMin();

						// **查询此工位是第几站**//
						tempPwayStationSerialNo = checkSNPDao.queryTempPwayStationSerialNo(routingId, tempStationId);

						// 首站点
						if (tempPwayStationSerialNo == tempProductionSerialMin) {

							tempTrackingCount = checkSNPDao.queryTempTrackingCount1(snBarcode);

							tempPTrackingCout = checkSNPDao.queryTempPTrackingCout(snBarcode);

							// 已经上线
							if (tempTrackingCount > 0) {

								CheckSnPT checkSnPT4 = checkSNPDao.queryCheckSnPT4(snBarcode);
								tempTrackingId = checkSnPT4.getTempTrackingId();
								tempTrackingStationName = checkSnPT4.getTempTrackingStationName();
								tempTrackingEnginesn = checkSnPT4.getTempTrackingEnginesn();
								tempTrackingGearbosxn = checkSnPT4.getTempTrackingStationName();
								tempTrackingStatus = checkSnPT4.getTempTrackingStatus();
								tempTrackingProductionId = checkSnPT4.getTempTrackingProductionId();
								tempStationLineId = checkSnPT4.getTempStationLineId();

								// **返修**//
								if ("4".equals(tempTrackingEnginesn)) {

									UpdatePlanPController moveDataPController = new UpdatePlanPController();
									moveDataPController.snMove = snBarcode;
									moveDataPController.mainData();
									r = moveDataPController.rMove;

									// ***在线**//
									if ("0".equals(tempOnOff)) {

										CheckAllRecipePServiceImpl checkPlanSnPService = new CheckAllRecipePServiceImpl();
										data2 = checkPlanSnPService.checkWorkorderSn(snBarcode, data2)
												.getJSONObject("result");

										r = Integer.parseInt(data2.getString("r"));
//										tempPlanId = Integer.parseInt(data2.getString("planId"));
										tempWorkOrderId = Integer.parseInt(data2.getString("workOrderId"));

										// **此条码在计划中***//
										if (r == 0) {
											// **初始化配方***//
											InitializeDataPController initializeDataPController = new InitializeDataPController();
											initializeDataPController.iniProductionId = tempTrackingProductionId;
											initializeDataPController.productionInit = tempStationLineId;
											initializeDataPController.stationType = tempStationType;
											initializeDataPController.stationInId = tempStationId;
											initializeDataPController.snIni = "0";
											initializeDataPController.routingId = routingId;
											initializeDataPController.totalRecipeId = totalRecipeId;
											initializeDataPController.main(dataPService);
											r = initializeDataPController.rIni;

											if (r == 0) {

												checkSNPDao.insertTracking2(snBarcode, tempStationIndex, null,
														tempTrackingProductionId, tempStationLineId, tempWorkOrderId);
												checkSNPDao.insertStationPass(snBarcode, tempStationName,
														tempStationLineId);

												tempOnlineNumber = checkSNPDao.queryTempOnlineNumber(tempWorkOrderId);

												checkSNPDao.updatePlan((tempOnlineNumber + 1), tempWorkOrderId);

												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", true);
												jo.put("errMsg", "");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											} else {
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
												return jo;

											}

										} else {
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										}

									} else { // ***离线***初始化配方***//

										InitializeDataPController initializeDataPController = new InitializeDataPController();
										initializeDataPController.iniProductionId = tempTrackingProductionId;
										initializeDataPController.productionInit = tempStationLineId;
										initializeDataPController.stationType = tempStationType;
										initializeDataPController.stationInId = 0;
										initializeDataPController.snIni = snBarcode;
										initializeDataPController.routingId = routingId;
										initializeDataPController.totalRecipeId = totalRecipeId;
										initializeDataPController.main(dataPService);
										r = initializeDataPController.rIni;

										if (r == 0) {

//											if(tempPlanId==null||tempPlanId.equals("")){
//												tempPlanId = 0;
//											}
											checkSNPDao.insertTracking(station, snBarcode, tempStationIndex, null,
													tempTrackingProductionId, tempStationLineId, tempWorkOrderId);
											checkSNPDao.insertStationPass(snBarcode, tempStationName,
													tempStationLineId);
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", true);
											jo.put("errMsg", "");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										} else {
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "该工位没有配置配方");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
											return jo;
										}

									}

								}
								// **该SN不是返工也不是返修**//
								if (tempTrackingStationName != null && tempTrackingStationName.equals(station)
										&& !"3".equals(tempTrackingEnginesn)) {
									r = 1;
									productionId = tempTrackingProductionId;
									data.clear();
									jo.put("isSuccess", false);
									jo.put("errMsg", "该总成已经上线");
									data.put("r", r);
									data.put("pass", pass);
									data.put("productionId", productionId);
									jo.put("result", data);
									return jo;

								} else {

									if (tempTrackingStationName == null) {

										r = 0;
										productionId = tempTrackingProductionId;
										data.clear();
										jo.put("isSuccess", true);
										jo.put("errMsg", "");
										data.put("r", r);
										data.put("pass", pass);
										data.put("productionId", productionId);
										jo.put("result", data);
										return jo;

									} else {
										// ***线内返修***//
										if ("3".equals(tempTrackingEnginesn)) {

											// 查询返修路线表中是否存在此SN和工位
											tempReworkWayCount = checkSNPDao.queryTempReworkWayCount(snBarcode,
													tempStationId);

											// **该SN在返修路线内***//
											if (tempReworkWayCount > 0) {
												// **查询该工位在返修路线中是第几个工位***//
												CheckSnPT checkSnPT6 = checkSNPDao.queryReworkWay1(snBarcode,
														tempStationId);
												tempReworkStationId = checkSnPT6.getTempReworkStationId();
												tempReworkStationSerialNo = checkSnPT6.getTempReworkStationSerialNo();
												// **根据总配方id和工位id查询是否存在此配方***/
												tempRecipeCount = checkSNPDao.queryTempRecipeCount(totalRecipeId,
														tempStationId);

												if (tempRecipeCount > 0) {

													InitializeDataPController initializeReworkDataPController = new InitializeDataPController();
//													initializeReworkDataPController.productionId = tempTrackingProductionId;
//													initializeReworkDataPController.snRework = snBarcode;
//													initializeReworkDataPController.totalRecipeId=totalRecipeId;

													initializeReworkDataPController.iniProductionId = tempTrackingProductionId;
													initializeReworkDataPController.productionInit = tempStationLineId;
													initializeReworkDataPController.stationType = tempStationType;
													initializeReworkDataPController.stationInId = tempStationId;
													initializeReworkDataPController.snIni = snBarcode;
													initializeReworkDataPController.routingId = routingId;
													initializeReworkDataPController.totalRecipeId = totalRecipeId;

													initializeReworkDataPController.main2(dataPService);
													r = initializeReworkDataPController.rRework;

													checkSNPDao.insertStationPass(snBarcode, tempStationName,
															tempStationLineId);
													checkSNPDao.updateTracking(tempTrackingId);
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", true);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												} else {

													r = 14;
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", "该工位没有配方");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												}

											} else {

												r = 3;
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "不在返修路线内");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											}

										} else {
											r = 2;
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "产品NG了，直接放行");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										}

									}
								}
							}

							// **P表有数据**//
							if (tempPTrackingCout > 0) {

								CheckSnPT checkSnPT4 = checkSNPDao.queryCheckSnPT4P(snBarcode);
								tempTrackingId = checkSnPT4.getTempTrackingId();
								tempTrackingStationName = checkSnPT4.getTempTrackingStationName();
								tempTrackingEnginesn = checkSnPT4.getTempTrackingEnginesn();
								tempTrackingGearbosxn = checkSnPT4.getTempTrackingStationName();
								tempTrackingStatus = checkSnPT4.getTempTrackingStatus();
								tempTrackingProductionId = checkSnPT4.getTempTrackingProductionId();
								tempStationLineId = checkSnPT4.getTempStationLineId();

								if ("4".equals(tempTrackingEnginesn)) {

									if ("0".equals(tempOnOff)) {

										CheckAllRecipePServiceImpl checkPlanSnPService = new CheckAllRecipePServiceImpl();
										data2.clear();
										data2 = checkPlanSnPService.checkWorkorderSn(snBarcode, data2)
												.getJSONObject("result");
										r = Integer.parseInt(data2.getString("r"));
										if (r == 0) {
											if (r == 0) {
//												tempPlanId = Integer.parseInt(data2.getString("planId"));
												tempWorkOrderId = Integer.parseInt(data2.getString("workOrderId"));

											}

											InitializeDataPController initializeDataPController = new InitializeDataPController();
											initializeDataPController.iniProductionId = tempTrackingProductionId;
											initializeDataPController.productionInit = tempStationLineId;
											initializeDataPController.stationType = tempStationType;
											initializeDataPController.stationInId = 0;
											initializeDataPController.snIni = snBarcode;
											initializeDataPController.routingId = routingId;
											initializeDataPController.totalRecipeId = totalRecipeId;
											initializeDataPController.main(dataPService);
											r = initializeDataPController.rIni;

											if (r == 0) {

												checkSNPDao.insertTracking2(snBarcode, tempStationIndex, null,
														tempTrackingProductionId, tempStationLineId, tempWorkOrderId);
												checkSNPDao.insertStationPass(snBarcode, tempStationName,
														tempStationLineId);
												tempOnlineNumber = checkSNPDao.queryTempOnlineNumber(tempWorkOrderId);
												checkSNPDao.updatePlan((tempOnlineNumber + 1), tempWorkOrderId);
												tempWorkOrderOffline = checkSNPDao
														.queryTempOrderOffline(tempWorkOrderId);
												// checkSNPDao.updateWorkorderDetail((tempWorkOrderOffline + 1), tempWorkOrderId);
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", true);
												jo.put("errMsg", "");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											} else {

												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "回滚");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
												return jo;

											}

										} else {

											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;
										}

									} else {

										InitializeDataPController initializeDataPController = new InitializeDataPController();
										initializeDataPController.iniProductionId = tempTrackingProductionId;
										initializeDataPController.productionInit = tempStationLineId;
										initializeDataPController.stationType = tempStationType;
										initializeDataPController.stationInId = 0;
										initializeDataPController.snIni = snBarcode;
										initializeDataPController.routingId = routingId;
										initializeDataPController.totalRecipeId = totalRecipeId;
										initializeDataPController.main(dataPService);
										r = initializeDataPController.rIni;

										if (r == 0) {

//											if(tempPlanId==null||tempPlanId.equals("")){
//												tempPlanId = 0;
//											}
											checkSNPDao.insertTracking(station, snBarcode, tempStationIndex, null,
													tempTrackingProductionId, tempStationLineId, tempWorkOrderId);

											checkSNPDao.insertStationPass(snBarcode, tempStationName,
													tempStationLineId);

											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", true);
											jo.put("errMsg", "");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										} else {
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "回滚");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
											return jo;

										}

									}

								} else {

									r = 39;
									productionId = tempTrackingProductionId;
									data.clear();
									jo.put("isSuccess", false);
									jo.put("errMsg", "此产品已正常生产且下线");
									data.put("r", r);
									data.put("pass", pass);
									data.put("productionId", productionId);
									jo.put("result", data);
									return jo;

								}

							}

							// ***该总成未上线***//
							if (tempTrackingCount == 0 && tempPTrackingCout == 0) {

								data2.clear();
								data2 = checkAllRecipePService
										.checkProduction1(snBarcode, tempTrackingProductionId.toString(), data2)
										.getJSONObject("result");
								r = Integer.parseInt(data2.getString("r"));
								if (r == 0) {
									// 产品id
//									tempTrackingProductionId = Integer.parseInt(data2.getString("checkProductionId"));
									tempOnOff = data2.getString("onOff");

								}

								if (tempTrackingProductionId > 0 && r == 0) {

									// 查询是否存在于工艺路线内
									tempPwayCount = checkSNPDao.queryPwayCount(routingId, tempStationId);

									if (tempPwayCount > 0) {

										data2.clear();
										// ***//此处用SN查询工艺路线
										data2 = checkAllRecipePService.checkAllRecipe(snBarcode,
												tempTrackingProductionId, tempStationLineId, tempStationType, 0, data2)
												.getJSONObject("result");
										r = Integer.parseInt(data2.getString("r"));

										if (r == 0) {
											// ***在线**//
											if ("0".equals(tempOnOff)) {

												data2.clear();
												data2 = checkPlanSnPService.checkWorkorderSn(snBarcode, data2);
												r = Integer.parseInt(data2.getJSONObject("result").getString("r"));

												if (r == 0) {

//													tempPlanId = Integer.parseInt(data2.getJSONObject("result").getString("planId"));
													tempWorkOrderId = Integer.parseInt(
															data2.getJSONObject("result").getString("workOrderId"));

													InitializeDataPController initializeDataPController = new InitializeDataPController();
													initializeDataPController.iniProductionId = tempTrackingProductionId;
													initializeDataPController.productionInit = tempStationLineId;
													initializeDataPController.stationType = tempStationType;
													initializeDataPController.stationInId = tempStationId;
													initializeDataPController.snIni = snBarcode;
													initializeDataPController.routingId = routingId;
													initializeDataPController.totalRecipeId = totalRecipeId;
													initializeDataPController.main(dataPService);
													r = initializeDataPController.rIni;

													if (r == 0) {

//														if(tempPlanId==null||tempPlanId.equals("")){
//															tempPlanId = 0;
//														}
														// 添加工位完成情况表
														Map<String, Object> map = new HashMap<>();
														map.put("snBarcode", snBarcode);
														map.put("routingId", routingId);
														this.insertStationDerialFlag(map);

														checkSNPDao.insertTracking(station, snBarcode, tempStationIndex,
																null, tempTrackingProductionId, tempStationLineId,
																tempWorkOrderId);
														checkSNPDao.insertStationPass(snBarcode, tempStationName,
																tempStationLineId);

														tempOnlineNumber = checkSNPDao
																.queryTempOnlineNumber(tempWorkOrderId);

														checkSNPDao.updatePlan((tempOnlineNumber + 1), tempWorkOrderId);

														tempWorkOrderOffline = checkSNPDao
																.queryTempOrderOffline(tempWorkOrderId);

														// checkSNPDao.updateWorkorderDetail((tempWorkOrderOffline + 1), tempWorkOrderId);

														productionId = tempTrackingProductionId;
														data.clear();
														jo.put("isSuccess", true);
														jo.put("errMsg", "");
														data.put("r", r);
														data.put("pass", pass);
														data.put("productionId", productionId);
														jo.put("result", data);
														return jo;

													} else {

														data.clear();
														jo.put("isSuccess", false);
														jo.put("errMsg", "回滚");
														data.put("r", r);
														data.put("pass", pass);
														data.put("productionId", productionId);
														jo.put("result", data);
														TransactionAspectSupport.currentTransactionStatus()
																.setRollbackOnly();
														return jo;

													}

												} else {
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", data2.getString("errMsg"));
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												}

											} else {
												// 产品离线
												InitializeDataPController initializeDataPController = new InitializeDataPController();
												initializeDataPController.iniProductionId = tempTrackingProductionId;
												initializeDataPController.productionInit = tempStationLineId;
												initializeDataPController.stationType = tempStationType;
												initializeDataPController.stationInId = tempStationId;
												initializeDataPController.snIni = snBarcode;
												initializeDataPController.routingId = routingId;
												initializeDataPController.totalRecipeId = totalRecipeId;
												initializeDataPController.main(dataPService);
												r = initializeDataPController.rIni;

//												JSONObject json = new JSONObject();
//												json.put("iniProductionId",tempTrackingProductionId);
//												json.put("snIni",snBarcode);
//												json.put("productionInit",tempStationLineId);
//												json.put("stationType",tempStationType);
//												json.put("stationInId",0);
//												json.put("rIni",0);
//												initializeDataPServiceImpl.main(json);
//
//												r = initializeDataPServiceImpl.rIni;
												if (r == 0) {

//													if(tempPlanId==null||tempPlanId.equals("")){
//														tempPlanId = 0;
//													}
													// 添加工位完成情况表
													Map<String, Object> map = new HashMap<>();
													map.put("snBarcode", snBarcode);
													map.put("routingId", routingId);
													this.insertStationDerialFlag(map);

													checkSNPDao.insertTracking(station, snBarcode, tempStationIndex,
															null, tempTrackingProductionId, tempStationLineId,
															tempWorkOrderId);
													checkSNPDao.insertStationPass(snBarcode, tempStationName,
															tempStationLineId);
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", true);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												} else {
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													TransactionAspectSupport.currentTransactionStatus()
															.setRollbackOnly();
													return jo;

												}

											}

										} else {

											r = 8;
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "此产品工艺路线工位没有配置配方1");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;
										}
									} else {

										r = 7;
										productionId = tempTrackingProductionId;
										data.clear();
										jo.put("isSuccess", false);
										jo.put("errMsg", "此产品工艺路线必须包含首站");
										data.put("r", r);
										data.put("pass", pass);
										data.put("productionId", productionId);
										jo.put("result", data);
										return jo;

									}

								} else {

									r = 5;
									productionId = 0;
									jo.put("isSuccess", false);
									jo.put("errMsg", "系统中不存在此种产品的配置");
									data.clear();
									data.put("r", r);
									data.put("pass", pass);
									data.put("productionId", productionId);
									jo.put("result", data);
									return jo;

								}

							}

						}

						// 末站
						if (tempPwayStationSerialNo == tempProductionSerialMax) {


							tempTrackingCount = checkSNPDao.queryTempTrackingCount1(snBarcode);

							if (tempTrackingCount > 0) {

								CheckSnPT checkSnPT5 = checkSNPDao.queryCheckSnPT(snBarcode);
								tempTrackingId = checkSnPT5.getTempTrackingId();
								tempTrackingStationName = checkSnPT5.getTempTrackingStationName();
								tempTrackingEnginesn = checkSnPT5.getTempTrackingEnginesn();
								tempTrackingGearbosxn = checkSnPT5.getTempTrackingStationName();
								tempTrackingStatus = checkSnPT5.getTempTrackingStatus();
								tempTrackingProductionId = checkSnPT5.getTempTrackingProductionId();
								tempStationLineId = checkSnPT5.getTempStationLineId();
								tempTrackingReworkFlag = checkSnPT5.getTempTrackingReworkFlag();

								if ("OK".equals(tempTrackingStatus) && !"1".equals(tempTrackingReworkFlag)) {
									// **判断该工位是否在工艺路线内**//
									tempPwayCount = checkSNPDao.queryTempPwayCount(routingId, tempStationId);

									if (tempPwayCount > 0) {
										// ****查询此工位的序号和前工位是否完整**//
										CheckSnPT checkSnPT6 = checkSNPDao.queryCheckSnPT6(routingId, tempStationId);
										CheckSnPT checkSnPT7 = checkSNPDao.queryCheckSnPT7(routingId, tempStationLineId,
												(tempPwayStationSerialNo - 1));

										tempPwayStationId = checkSnPT6.getTempPwayStationId();
										tempPwayStationSerialNo = checkSnPT6.getTempPwayStationSerialNo();
										tempPwayBeforeStationName = checkSnPT7.getTempPwayBeforeStationName();
										tempPwayBeforeSerialNo = checkSnPT7.getTempPwayStationSerialNo();

										// 判断前工位是否全部都完成
										Map<String, Object> map = new HashMap<>();
										map.put("SN", snBarcode);
										map.put("SERIAL", (tempPwayStationSerialNo - 1));
										Integer countTotal = dao.queryFlagTotalCount(map);
										Integer countComplete = dao.queryFlagCompleteCount(map);

										// **完整***//
										//if (tempPwayBeforeStationName.equals(tempTrackingStationName)) {
										if(countTotal > 0 && countComplete == countTotal) {

											// **查询该工位是否有配方**//
											tempRecipeCount = checkSNPDao.queryTempRecipeCount2(totalRecipeId,
													tempStationId);
											if (tempRecipeCount > 0) {
												InitializeDataPController initializeDataPController = new InitializeDataPController();
												initializeDataPController.iniProductionId = tempTrackingProductionId;
												initializeDataPController.productionInit = tempStationLineId;
												initializeDataPController.stationType = tempStationType;
												initializeDataPController.stationInId = tempStationId;
												initializeDataPController.snIni = snBarcode;
												initializeDataPController.routingId = routingId;
												initializeDataPController.totalRecipeId = totalRecipeId;
												initializeDataPController.main(dataPService);
												r = initializeDataPController.rIni;

												productionId = tempTrackingProductionId;
												checkSNPDao.insertStationPass(snBarcode, tempStationName,
														tempStationLineId);

												data.clear();
												jo.put("isSuccess", true);
												jo.put("errMsg", "");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											} else {
												r = 14;
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "该工位没有配置配方");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											}

										} else {

											r = 13;
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "前工位未完成");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										}

									} else {

										r = 16;
										productionId = tempTrackingProductionId;
										data.clear();
										jo.put("isSuccess", false);
										jo.put("errMsg", "此工位不在工艺路线内");
										data.put("r", r);
										data.put("pass", pass);
										data.put("productionId", productionId);
										jo.put("result", data);
										return jo;

									}

								} else {

									// 返修
									if ("3".equals(tempTrackingEnginesn) && "1".equals(tempTrackingReworkFlag)) {

										// **判断是否在返修路线内**//
										tempReworkWayCount = checkSNPDao.queryReworkWay(snBarcode, tempStationId);

										if (tempReworkWayCount > 0) {

											// **判断是否经过上一个工位***///
											CheckSnPT checkSnPT6 = checkSNPDao.queryReworkWay1(snBarcode,
													tempStationId);
											CheckSnPT checkSnPT7 = checkSNPDao.queryReworkWay2(snBarcode,
													(tempPwayStationSerialNo - 1));

											tempReworkStationId = checkSnPT6.getTempReworkStationId();
											tempReworkStationSerialNo = checkSnPT6.getTempReworkStationSerialNo();
											tempReworkBeforeName = checkSnPT7.getTempReworkBeforeName();
											tempReworkBeforeSerialNo = checkSnPT7.getTempReworkBeforeSerialNo();

											// 判断前工位是否全部都完成
											Map<String, Object> map = new HashMap<>();
											map.put("SN", snBarcode);
											map.put("SERIAL", (tempPwayStationSerialNo - 1));
											Integer countTotal = dao.queryFlagTotalCount(map);
											Integer countComplete = dao.queryFlagCompleteCount(map);

											// **完整***//
											//if (tempPwayBeforeStationName.equals(tempTrackingStationName)) {
											if(countTotal > 0 && countComplete == countTotal) {

												// ***查询该工位是否有配方***//
												tempRecipeCount = checkSNPDao.queryTempRecipeCount2(totalRecipeId,
														tempStationId);

												if (tempRecipeCount > 0) {

													InitializeDataPController initializeReworkDataPController = new InitializeDataPController();

													initializeReworkDataPController.iniProductionId = tempTrackingProductionId;
													initializeReworkDataPController.productionInit = tempStationLineId;
													initializeReworkDataPController.stationType = tempStationType;
													initializeReworkDataPController.stationInId = tempStationId;
													initializeReworkDataPController.snIni = snBarcode;
													initializeReworkDataPController.routingId = routingId;
													initializeReworkDataPController.totalRecipeId = totalRecipeId;
													initializeReworkDataPController.main2(dataPService);
													r = initializeReworkDataPController.rRework;

													productionId = tempTrackingProductionId;
													checkSNPDao.insertStationPass(snBarcode, station, tempPwayCount);
													data.clear();
													jo.put("isSuccess", true);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;
												} else {
													r = 14;
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", "该工位没有配置配方");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												}

											} else {

												r = 13;
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "前工位没有装配完成");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;

											}

										} else {
											r = 15;
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "不在返修路线内");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										}

									} else {

										r = 1;
										productionId = tempTrackingProductionId;
										data.clear();
										jo.put("isSuccess", false);
										jo.put("errMsg", "产品NG,直接放行");
										data.put("r", r);
										data.put("pass", pass);
										data.put("productionId", productionId);
										jo.put("result", data);
										return jo;
									}

								}

							} else {

								r = 12;
								productionId = tempTrackingProductionId;
								data.clear();
								jo.put("isSuccess", false);
								jo.put("errMsg", "该总成没有上线");
								data.put("r", r);
								data.put("pass", pass);
								data.put("productionId", productionId);
								jo.put("result", data);
								return jo;

							}

						}

						// 中间站
						if (tempPwayStationSerialNo > tempProductionSerialMin
								&& tempPwayStationSerialNo < tempProductionSerialMax) {

							tempTrackingCount = checkSNPDao.queryTempTrackingCount1(snBarcode);
							// **是否上线***//
							if (tempTrackingCount > 0) {

								CheckSnPT checkSnPT5 = checkSNPDao.queryCheckSnPT(snBarcode);

								tempTrackingId = checkSnPT5.getTempTrackingId();
								tempTrackingStationName = checkSnPT5.getTempTrackingStationName();
								tempTrackingEnginesn = checkSnPT5.getTempTrackingEnginesn();
								tempTrackingGearbosxn = checkSnPT5.getTempTrackingStationName();
								tempTrackingStatus = checkSnPT5.getTempTrackingStatus();
								tempTrackingProductionId = checkSnPT5.getTempTrackingProductionId();
								tempStationLineId = checkSnPT5.getTempStationLineId();
								tempTrackingReworkFlag = checkSnPT5.getTempTrackingReworkFlag();

								// **产品状态OK**//
								if ("OK".equals(tempTrackingStatus) && !"1".equals(tempTrackingReworkFlag)) {

									// ***是否存在于该工艺路线///
									tempPwayCount = checkSNPDao.queryTempPwayCount(routingId, tempStationId);

									if (tempPwayCount > 0) {
										// ***判断完整**//
										CheckSnPT checkSnPT6 = checkSNPDao.queryCheckSnPT6(routingId, tempStationId);
										CheckSnPT checkSnPT7 = checkSNPDao.queryCheckSnPT7(routingId, tempStationLineId,
												(tempPwayStationSerialNo - 1));

										tempPwayStationId = checkSnPT6.getTempPwayStationId();
										tempPwayStationSerialNo = checkSnPT6.getTempPwayStationSerialNo();
										tempPwayBeforeStationName = checkSnPT7.getTempPwayBeforeStationName();
										tempPwayBeforeSerialNo = checkSnPT7.getTempPwayStationSerialNo();
										if (station.equals(tempTrackingStationName)) {

											r = 41;
											productionId = tempTrackingProductionId;
											data.clear();
											jo.put("isSuccess", false);
											jo.put("errMsg", "该总成没有上线");
											data.put("r", r);
											data.put("pass", pass);
											data.put("productionId", productionId);
											jo.put("result", data);
											return jo;

										} else {
											// 判断前工位是否全部都完成
											Map<String, Object> map = new HashMap<>();
											map.put("SN", snBarcode);
											map.put("SERIAL", (tempPwayStationSerialNo - 1));
											Integer countTotal = dao.queryFlagTotalCount(map);
											Integer countComplete = dao.queryFlagCompleteCount(map);

											// **完整***//
											//if (tempPwayBeforeStationName.equals(tempTrackingStationName)) {
											if(countTotal > 0 && countComplete == countTotal) {
												/// **该工位是否存在配方***//
												tempRecipeCount = checkSNPDao.queryTempRecipeCount(totalRecipeId,
														tempStationId);
												if (tempRecipeCount > 0) {
													InitializeDataPController initializeDataPController = new InitializeDataPController();
													initializeDataPController.iniProductionId = tempTrackingProductionId;
													initializeDataPController.productionInit = tempStationLineId;
													initializeDataPController.stationType = tempStationType;
													initializeDataPController.stationInId = tempStationId;
													initializeDataPController.snIni = snBarcode;
													initializeDataPController.routingId = routingId;
													initializeDataPController.totalRecipeId = totalRecipeId;
													initializeDataPController.main(dataPService);
													r = initializeDataPController.rIni;

													productionId = tempTrackingProductionId;
													checkSNPDao.insertStationPass(snBarcode, tempStationName,
															tempStationLineId);

													data.clear();
													jo.put("isSuccess", true);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												} else {
													r = 14;
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", "该工位没有配置配方");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												}

											} else {

												r = 13;
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "前工位不完整");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;
											}
										}
									} else {

										r = 1;
										productionId = tempTrackingProductionId;
										data.clear();
										jo.put("isSuccess", false);
										jo.put("errMsg", "该产品不经过此工位");
										data.put("r", r);
										data.put("pass", pass);
										data.put("productionId", productionId);
										jo.put("result", data);
										return jo;

									}
								} else {
									// 已经返修
									if ("3".equals(tempTrackingEnginesn) && "1".equals(tempTrackingReworkFlag)) {

										tempReworkWayCount = checkSNPDao.queryReworkWay(snBarcode, tempStationId);

										if (tempReworkWayCount > 0) {

											CheckSnPT checkSnPT6 = checkSNPDao.queryReworkWay1(snBarcode,
													tempStationId);
											CheckSnPT checkSnPT7 = checkSNPDao.queryReworkWay2(snBarcode,
													(tempPwayStationSerialNo - 1));

											tempReworkStationId = checkSnPT6.getTempReworkStationId();
											tempReworkStationSerialNo = checkSnPT6.getTempReworkStationSerialNo();
											tempReworkBeforeName = checkSnPT7.getTempReworkBeforeName();
											tempReworkBeforeSerialNo = checkSnPT7.getTempReworkBeforeSerialNo();

											if (tempReworkBeforeName.equals(tempTrackingStationName)) {

												// ***查询该工位是否有配方***//
												tempRecipeCount = checkSNPDao.queryTempRecipeCount2(totalRecipeId,
														tempStationId);

												if (tempRecipeCount > 0) {

													InitializeDataPController initializeReworkDataPController = new InitializeDataPController();

													initializeReworkDataPController.iniProductionId = tempTrackingProductionId;
													initializeReworkDataPController.productionInit = tempStationLineId;
													initializeReworkDataPController.stationType = tempStationType;
													initializeReworkDataPController.stationInId = tempStationId;
													initializeReworkDataPController.snIni = snBarcode;
													initializeReworkDataPController.routingId = routingId;
													initializeReworkDataPController.totalRecipeId = totalRecipeId;
													initializeReworkDataPController.main2(dataPService);
													r = initializeReworkDataPController.rRework;

													productionId = tempTrackingProductionId;
													checkSNPDao.insertStationPass(snBarcode, station, tempPwayCount);
													data.clear();
													jo.put("isSuccess", true);
													jo.put("errMsg", "");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;
												} else {
													r = 14;
													productionId = tempTrackingProductionId;
													data.clear();
													jo.put("isSuccess", false);
													jo.put("errMsg", "该工位没有配置配方");
													data.put("r", r);
													data.put("pass", pass);
													data.put("productionId", productionId);
													jo.put("result", data);
													return jo;

												}

											} else {

												r = 13;
												productionId = tempTrackingProductionId;
												data.clear();
												jo.put("isSuccess", false);
												jo.put("errMsg", "前工位没有装配完成");
												data.put("r", r);
												data.put("pass", pass);
												data.put("productionId", productionId);
												jo.put("result", data);
												return jo;
											}
										}

									}

								}

							} else {

								r = 12;
								productionId = 0;
								data.clear();
								jo.put("isSuccess", false);
								jo.put("errMsg", "该总成没有上线");
								data.put("r", r);
								data.put("pass", pass);
								data.put("productionId", productionId);
								jo.put("result", data);
								return jo;

							}
						}
					} else {
						productionId = tempTrackingProductionId;
						data.clear();
						jo.put("isSuccess", false);
						jo.put("errMsg", "工艺路线配置错误");
						data.put("r", r);
						data.put("pass", pass);
						data.put("productionId", productionId);
						jo.put("result", data);
						return jo;
					}
				}
			} else {
				r = 5;
				productionId = 0;
				data.clear();
				jo.put("isSuccess", false);
				jo.put("errMsg", "系统中不存在此种产品的配置");
				data.put("r", r);
				data.put("pass", pass);
				data.put("productionId", productionId);
				jo.put("result", data);
				return jo;
			}

		} else {
			data.clear();
			jo.put("isSuccess", false);
			jo.put("errMsg", "不存在传入的工位");
			data.put("r", "11");
			data.put("pass", pass);
			data.put("productionId", productionId);
			jo.put("result", data);
			return jo;
		}
		data.clear();
		jo.put("isSuccess", false);
		jo.put("errMsg", "程序之外的情况");
		data.put("r", "206");
		data.put("pass", 0);
		data.put("productionId", 0);
		jo.put("result", data);
		return jo;
	}

	/**
	 * 添加完成情况表数据
	 * @param map
	 */
	private void insertStationDerialFlag(Map<String, Object> map) {

		// 判断是否已添加过了
		Integer count = dao.queryStationDerialFlagCount(map);
		if(count == 0){
			List<Map<String, Object>> list = dao.queryStationWayList(map);
			dao.insertStationDerialFlag(list);
		}
	}

	@Override
	public CheckSnPT queryCheckSnPTByLineAndStation1(String line, String station) {
		// TODO Auto-generated method stub
		return checkSNPDao.queryCheckSnPTByLineAndStation1(line, station);
	}

}
