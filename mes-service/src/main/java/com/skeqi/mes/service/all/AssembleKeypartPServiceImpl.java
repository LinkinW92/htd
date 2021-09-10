package com.skeqi.mes.service.all;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.AssembleKeypartPDao;
import com.skeqi.mes.pojo.api.AssembleKeypartPT;
import com.skeqi.mes.pojo.api.CheckMaterialPT;
import com.skeqi.mes.service.all.AssembleKeypartPService;

@Service
public class AssembleKeypartPServiceImpl implements AssembleKeypartPService {

	@Autowired
	private AssembleKeypartPDao assembleKeypartPDao;



	@Override
	public JSONObject checkMaterial(String materialBarcode, String stationInName, String stepNo, String lineName,
			String snMaterial, JSONObject jo) {
		JSONObject data = new JSONObject();
		String stationType = assembleKeypartPDao.queryStationType(stationInName,lineName);
		CheckMaterialPT  checkMaterialPT= null;
		Pattern pattern = null;
		Matcher matcher = null;

		String totalRecipeId = assembleKeypartPDao.findAllPlan(snMaterial);

		if("0".equals(stationType)) {

			checkMaterialPT = assembleKeypartPDao.queryCheckMaterialPT1(totalRecipeId,lineName,stationInName,snMaterial,stepNo);


		}else {

			checkMaterialPT = assembleKeypartPDao.queryCheckMaterialPT2(totalRecipeId,lineName,stationInName,snMaterial,stepNo);


		}

		if(checkMaterialPT==null) {

			jo.put("isSuccess", false);
			jo.put("errMsg", "所查询的物料数据为空");
//			data.put("r", "205");
			jo.put("result", false);
			return jo;
		}

		if ("4".equals(checkMaterialPT.getTempMaterialScdType())) {

			String tempMaterialSecondNum = assembleKeypartPDao.queryTempMaterialSecondNum(snMaterial,stationInName,checkMaterialPT.getTempMaterialName());

			if(tempMaterialSecondNum!=null) {

				jo.put("isSuccess", false);
				jo.put("errMsg", "该总成已经装配了该物料");
//				data.put("r", "33");
				jo.put("result", false);
				return jo;
			}

			if("0".equals(checkMaterialPT.getTempMaterialCheckFlag())) {

				if("0".equals(checkMaterialPT.getTempExactorno())) {

					jo.put("isSuccess", true);
					jo.put("errMsg", "");
//					data.put("r", "0");
					jo.put("result", true);
					return jo;
				}else {

					Integer tempKepartcountP = assembleKeypartPDao.queryTempKepartcountP(materialBarcode);
					Integer tempKepartcountR = assembleKeypartPDao.queryTempKepartcountR(materialBarcode);

					if(tempKepartcountP>0||tempKepartcountR>0) {

						if(tempKepartcountR>0) {
							String tempTrackingEnginesnR = assembleKeypartPDao.queryTempTrackingEnginesnR(snMaterial);
							if("4".equals(tempTrackingEnginesnR)) {

								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								data.put("r", "0");
								jo.put("result", true);
								return jo;
							}else {
								jo.put("isSuccess", false);
								jo.put("errMsg", "该物料已被装配");
//								data.put("r", "40");
								jo.put("result", false);
								return jo;
							}
						}else {
							String tempTrackingEnginesnP = assembleKeypartPDao.queryTempTrackingEnginesnP(snMaterial);

							if("4".equals(tempTrackingEnginesnP)) {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								data.put("r", "0");
								jo.put("result", true);
								return jo;
							}else {
								jo.put("isSuccess", false);
								jo.put("errMsg", "该物料已被装配");
//								data.put("r", "40");
								jo.put("result", false);
								return jo;
							}

						}
					}else {
						jo.put("isSuccess", true);
						jo.put("errMsg", "");
//						data.put("r", "0");
						jo.put("result", true);
						return jo;
					}
				}
			}else {
				if("0".equals(checkMaterialPT.getTempExactorno())) {



						pattern = Pattern.compile(checkMaterialPT.getTempMaterialVr());
						matcher = pattern.matcher(materialBarcode);
						if(matcher.find()) {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							data.put("r", "0");
							jo.put("result", true);
							return jo;

						}else {

							jo.put("isSuccess",false);
							jo.put("errMsg", "物料校验不通过");
//							data.put("r", "34");
							jo.put("result", false);
							return jo;

						}

				}else {
						pattern = Pattern.compile(checkMaterialPT.getTempMaterialVr());
						matcher = pattern.matcher(materialBarcode);
						if(matcher.find()) {

							Integer tempKepartcountP = assembleKeypartPDao.queryTempKepartcountP(materialBarcode);
							Integer tempKepartcountR = assembleKeypartPDao.queryTempKepartcountR(materialBarcode);

							if(tempKepartcountP>0 ||tempKepartcountR>0) {

								if(tempKepartcountR>0) {

								String enginesnR = assembleKeypartPDao.queryEnginesnByTrackingR(snMaterial);

								if("4".equals(enginesnR)) {
									jo.put("isSuccess", true);
									jo.put("errMsg", "");
//									data.put("r", "0");
									jo.put("result", true);
									return jo;
								}else {
									jo.put("isSuccess", false);
									jo.put("errMsg", "该物料已被装配");
//									data.put("r", "40");
									jo.put("result", false);
									return jo;
								}

								}else {


								String enginesnP = assembleKeypartPDao.queryEnginesnByTrackingP(snMaterial);

								if("4".equals(enginesnP)) {
									jo.put("isSuccess", true);
									jo.put("errMsg", "");
//									data.put("r", "0");
									jo.put("result", true);
									return jo;
								}else {
									jo.put("isSuccess", false);
									jo.put("errMsg", "该物料已被装配");
//									data.put("r", "40");
									jo.put("result", false);
									return jo;
								}
								}
							}else {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								data.put("r", "0");
								jo.put("result", true);
								return jo;
							}
						}else {
							jo.put("isSuccess", false);
							jo.put("errMsg", "物料校验不通过");
//							data.put("r", "34");
							jo.put("result", false);
							return jo;
						}
				}
			}
		}else {


			String tempMaterialNumR = assembleKeypartPDao.queryKeypartNum(snMaterial,stationInName,checkMaterialPT.getTempMaterialName());

			if(tempMaterialNumR!=null&&!tempMaterialNumR.equals("")) {

				jo.put("isSuccess", false);
				jo.put("errMsg", "该总成已经装配了该物料");
//				data.put("r", "33");
				jo.put("result", false);
				return jo;
			}

			if("0".equals(checkMaterialPT.getTempMaterialCheckFlag())) {
				if("0".equals(checkMaterialPT.getTempExactorno())) {
					jo.put("isSuccess", true);
					jo.put("errMsg", "");
//					data.put("r", "0");
					jo.put("result", true);
					return jo;
				}else {

					Integer tempKepartcontP = assembleKeypartPDao.queryTempKepartcountP(materialBarcode);
					Integer tempKepartcontR = assembleKeypartPDao.queryTempKepartcountR(materialBarcode);

					if(tempKepartcontP>0||tempKepartcontR>0) {

						if(tempKepartcontR>0) {

							String tempTrackingEnginesn = assembleKeypartPDao.queryTempTrackingEnginesnR(snMaterial);

							if("4".equals(tempTrackingEnginesn)) {

								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								data.put("r", "0");
								jo.put("result", true);
								return jo;


							}else {
								jo.put("isSuccess", false);
								jo.put("errMsg", "该物料已被装配");
//								data.put("r", "40");
								jo.put("result", false);
								return jo;


							}



						}else {

							String tempTrackingEnginesn = assembleKeypartPDao.queryTempTrackingEnginesnP(snMaterial);

							if("4".equals(tempTrackingEnginesn)) {

								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								data.put("r", "0");
								jo.put("result", true);
								return jo;


							}else {

								jo.put("isSuccess", false);
								jo.put("errMsg", "该物料已被装配");
//								data.put("r", "40");
								jo.put("result", false);
								return jo;


							}

						}



					}else {
						jo.put("isSuccess", true);
						jo.put("errMsg", "");
//						data.put("r", "0");
						jo.put("result", true);
						return jo;
					}
				}
			}else {

				if("0".equals(checkMaterialPT.getTempExactorno())) {


					pattern = Pattern.compile(checkMaterialPT.getTempMaterialVr());
					matcher = pattern.matcher(materialBarcode);
					if(matcher.find()) {

						jo.put("isSuccess", true);
						jo.put("errMsg", "");
//						data.put("r", "0");
						jo.put("result", true);
						return jo;

					}else {

						jo.put("isSuccess", false);
						jo.put("errMsg", "物料校验不通过");
//						data.put("r", "34");
						jo.put("result", false);
						return jo;

					}

				}else {

					pattern = Pattern.compile(checkMaterialPT.getTempMaterialVr());
					matcher = pattern.matcher(materialBarcode);
					if(matcher.find()) {

						Integer tempKeypartcountP = assembleKeypartPDao.queryTempKepartcountP(materialBarcode);
						Integer tempKeypartcountR = assembleKeypartPDao.queryTempKepartcountR(materialBarcode);

						if(tempKeypartcountP>0||tempKeypartcountR>0) {

							if(tempKeypartcountR>0) {

								String tempTrackingEnginesn = assembleKeypartPDao.queryTempTrackingEnginesnR(snMaterial);

								if("4".equals(tempTrackingEnginesn)) {
									jo.put("isSuccess", true);
									jo.put("errMsg", "");
//									data.put("r", "0");
									jo.put("result", true);
									return jo;

								}else {
									jo.put("isSuccess", false);
									jo.put("errMsg", "该物料已被装配");
//									data.put("r", "40");
									jo.put("result", false);
									return jo;
								}

							}else {

								String tempTrackingEnginesn = assembleKeypartPDao.queryTempTrackingEnginesnP(snMaterial);

								if("4".equals(tempTrackingEnginesn)) {
									jo.put("isSuccess", true);
									jo.put("errMsg", "");
//									data.put("r", "0");
									jo.put("result", true);
									return jo;

								}else {
									jo.put("isSuccess", false);
									jo.put("errMsg", "该物料已被装配");
//									data.put("r", "40");
									jo.put("result", false);
									return jo;
								}


							}



						}else {

							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							data.put("r", "0");
							jo.put("result", true);
							return jo;

						}

					}else {

						jo.put("isSuccess", false);
						jo.put("errMsg", "物料校验不通过");
//						data.put("r", "34");
						jo.put("result", false);
						return jo;

					}
				}
			}
		}

	}
	@Override
	public JSONObject assembleKeypart(String snBarcode, String materialBarcode, String materialName, String stationName,
			String emp, JSONObject jo) {
		JSONObject data = new JSONObject();

		Integer tempMaxId = assembleKeypartPDao.getKeypartMaxIdBySnAndStAndKeypartName(snBarcode, stationName, materialName);
		if(tempMaxId==null) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "查询到的tempMaxId为空,用户可能修改了产品配方");
			data.put("r", "203");
			jo.put("result", false);
			return jo;
		}


		if(tempMaxId>0) {
			AssembleKeypartPT assembleKeypartPT = assembleKeypartPDao.getAssembleKeypartPTById(tempMaxId);
			if(assembleKeypartPT.getTempKeypartNum()!=null&&!assembleKeypartPT.getTempKeypartNum().equals("")) {
				if(assembleKeypartPT.getTempKeypartType()!=null && !assembleKeypartPT.getTempKeypartType().equals("") && assembleKeypartPT.getTempKeypartType()==4) {
					if(assembleKeypartPT.getTempSecondNum()!=null && assembleKeypartPT.getTempSecondNum().equals("")) {
						jo.put("isSuccess", false);
						jo.put("errMsg", "物料已经装配");
						data.put("r", "33");
						jo.put("result", false);
						return jo;
					}else {
						//装配物料
						assembleKeypartPDao.updateAssembleKeypartPT(materialBarcode,emp,tempMaxId);
						jo.put("isSuccess", true);
						jo.put("errMsg", "");
						jo.put("result", true);
						return jo;
					}
				}else {
					jo.put("isSuccess", false);
					jo.put("errMsg", "物料已经装配");
					jo.put("result", false);
					return jo;
				}
			}else {
				assembleKeypartPDao.updateAssembleKeypartPT(materialBarcode,emp,tempMaxId);
				jo.put("isSuccess", true);
				jo.put("errMsg", "");
				jo.put("result", true);
				return jo;
			}
		}else {
			jo.put("isSuccess", false);
			jo.put("errMsg", "系统没有初始化该物料信息");
			jo.put("result", false);
			return jo;
		}


	}

}
