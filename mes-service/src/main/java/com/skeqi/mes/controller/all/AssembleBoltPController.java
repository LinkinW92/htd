package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skeqi.mes.util.aop.OptionalLog;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.AssembleBoltPT;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.GetcurrentBoltPT;
import com.skeqi.mes.service.all.AssembleBoltPService;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.CheckAllRecipePService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author liu 更新螺栓数据
 *
 */
@RestController
@RequestMapping("api")
public class AssembleBoltPController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	CheckAllRecipePService RecipeService;

	@Autowired
	EventService serviceEvent;


	/**
	 * 出参
	 */
	Integer rBolt;
	String outBoltNum;// 螺栓编号
	Integer remainNumber;//剩余螺栓颗数
	/**
	 * 临时变量
	 */
	String tempMaterialName;// 名称
	String tempReworkTimes;// 返工次数
	Integer tempMinId;// 没装配最小id
	String tempT;
	String tempA;
	String tempR;
	String tempALimit;
	String tempTLimit;
	String tempBoltName;
	String tempReworkFlag;
	String tempReworkSt;
	Integer tempRemainBoltCount;
	Integer tempBoltReworkFlag;
	String tempBoltNum;
	String tempStationType;
	String rMessage;


	// 临时变量
	String stationType;//
	String tempBoltTLimit;// 当前螺栓扭矩上下限
	String tempBoltALimit;// 当前螺栓角度上下限
	String tempTLimitLower;// 扭矩下限
	String tempTLimitUpper;// 扭矩上限
	Integer tempTLimitLowerNumber;// ;
	Integer tempTLimitUpperNumber;//
	Integer tempCurrentTNumber;//
	String tempALimitLower;// 角度下限
	String tempALimitUpper;// 角度上限
	Integer tempALimitLowerNumber;//
	Integer tempALimitUpperNumber;//
	Integer tempCurrentANumber;//
	String tempTResult;// 临时判断结果
	String tempAResult;// 临时判断结果
	String tempAllResult;// 总判定结果
	Integer totalRecipeId;   //总配方ID

	// 出参
	Integer reworkTimesFlag;// 表示未达到返工次数 1:表示达到返工次数
	String res;// 报错信息







	@Autowired
	AssembleBoltPService Aservice;

	@RequestMapping(value = "assembleBolt")
	@OptionalLog(module="生产", module2="生产模拟", method="拧紧螺栓")
	public void assembleBolt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 保存返回数据
		JSONObject jo = new JSONObject();
		// 保存返回数据
		JSONObject joz = new JSONObject();

		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		// 获取总称码
		String sn;
		// 获取角度值
		String aValues;
		// 获取扭矩值
		String tValues;
		// 获取拧紧结果
		String rValues;
		// 获取产线名字
		String lineName;
		// 获取步序
		Integer stepNo;
		// 工位名称
		String stationBoltName;
		// 员工号
		String emp;
		//设备
		String eqName;
		response.setContentType("application/json");

//		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", true);
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("assembleBolt");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			// 获取总称码
			sn = json.get("sn").toString();
			// 获取角度值
			aValues = json.get("a").toString();
			// 获取扭矩值
			tValues = json.get("t").toString();
			// // 获取结果
			 rValues = json.get("r").toString();
			// 获取产线名字
			lineName = json.get("line").toString();
			// 获取步序
			stepNo = Integer.parseInt(json.get("step").toString());
			//
			stationBoltName = json.get("station").toString();
			//
			emp = json.get("emp").toString();
			//
			eqName = json.get("eqName").toString();

			rBolt = 0;
			remainNumber = 0;
			tempBoltReworkFlag = 0;
			tempMinId = 0;
			reworkTimesFlag = 0;
			tempTResult = "OK";
			tempAResult = "OK";
			tempAllResult = rValues;

		} catch (NullPointerException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

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
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
			}

			response.getWriter().append(jo.toJSONString());
			return;

		}

		try {

			// 事件添加
			Map<String, Object> map = new HashMap<>();
			map.put("NAME", lineName);
			map.put("snBarcode", sn);
			map.put("stationName", stationBoltName);
			map.put("snBarcode", stationBoltName);
			Map<String, Object> mapResult = serviceEvent.getLineCode(map);
			Map<String, Object> mapResultBolt = serviceEvent.getMaterialId(map);
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "成品");
			mapEvent.put("OBJECT_ID", sn);
			mapEvent.put("EVENT", "拧紧螺栓");
			if(mapResult != null){
				mapEvent.put("PARAMETER1", mapResult.get("code"));
			}
			mapEvent.put("PARAMETER2", stationBoltName);
			mapEvent.put("PARAMETER3", stepNo);
			if(mapResultBolt != null){
				mapEvent.put("PARAMETER4", mapResultBolt.get("MATERIAL_INSTANCE_ID"));
			}
			serviceEvent.addEvent(mapEvent);

			// 调用方法主体
			main(sn,aValues,tValues,rValues,lineName,stepNo,stationBoltName,emp,jo,joz);
//			jo.put("isSuccess", true);
//			jo.put("errMsg", "");

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			jo.put("errMsg", "更新数据失败！");
		} finally {

			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("生成日志出错了");
			}

			response.getWriter().append(jo.toJSONString());

		}

	}

	@Transactional
	public synchronized void main(String sn1,String aValues1,String tValues1,String rValues1,
			String lineName1,Integer stepNo1,String stationBoltName1,String emp1,JSONObject jo, JSONObject joz) {
		synchronized(this){
			Integer reworkTimesFlag=0;
			String sn = sn1;
			String aValues = aValues1;
			String tValues = tValues1;
			String rValues = rValues1;
			String lineName = lineName1;
			Integer stepNo = stepNo1;
			String stationBoltName = stationBoltName1;
			String emp = emp1;
		//***根据sn获取配方ID***//
		List<CheckAllRecipePT> queryProductionWayList = Aservice.queryProductionWayList(sn);
		JSONObject data = new JSONObject();
		if(queryProductionWayList.size()==0){
			JSONObject checkProduction = RecipeService.checkProduction(sn, null, data);
			Integer pid = checkProduction.getJSONObject("result").getInteger("checkProductionId");
			totalRecipeId = RecipeService.findDefaultTotal(RecipeService.findLineId(lineName), pid);
		}else{
			totalRecipeId = queryProductionWayList.get(0).getTotalRecipeId();
		}

		if (sn != null && aValues != null && tValues != null && stationBoltName != null && emp != null
				&& rValues != null && lineName != null && stepNo > 0) {

			// 获取工位类型
			stationType = Aservice.finds1(stationBoltName,lineName).getTempStationType();

			//***线内站***//
			if (stationType.equals("0")) {

				AssembleBoltPT assembleBoltPT = Aservice.find2(totalRecipeId, stationBoltName, sn, stepNo.toString());
				tempMaterialName = assembleBoltPT.getMaterialName();
				tempReworkTimes = assembleBoltPT.getReworktimes();
				tempBoltALimit = assembleBoltPT.getaLimit();
				tempBoltTLimit = assembleBoltPT.gettLimit();

			} else {

				AssembleBoltPT assembleBoltPT = Aservice.find3(totalRecipeId, stationBoltName, sn, stepNo.toString());
				tempMaterialName = assembleBoltPT.getMaterialName();
				tempReworkTimes = assembleBoltPT.getReworktimes();
				tempBoltALimit = assembleBoltPT.getaLimit();
				tempBoltTLimit = assembleBoltPT.gettLimit();

			}


			//保存当前螺栓名称
			String boltName = "";

			//查询此步序有多少条初始化记录
            Integer count1 = Aservice.find1004(sn, stationBoltName, tempMaterialName);

			if (count1!=null && !count1.equals("") && count1 > 0) {  //有装配的螺栓


				//查询是否还有NG的螺栓没有OK  条件（Y=0 , status=NG）
				Integer	conut = Aservice.find1001(sn, stationBoltName, tempMaterialName);

				Integer Y = 0;

//				if(rValues.equals("OK")){
//					Y = 1;
//				}

				// conut大于0说明有螺栓没有被OK
				if (conut != null && !conut.equals("") && conut > 0) {
					// 查询没有被OK的BOoltName  条件（Y=0 , status=NG）
					AssembleBoltPT dx = Aservice.find1002(sn, stationBoltName, tempMaterialName);
					dx.setY(0);
					dx.setR(rValues);
					dx.setA(aValues);
					dx.setT(tValues);
					boltName = dx.getBoltName();  //目前做到的颗数的名称
					if(rValues.equals("NG") || rValues=="NG") {
						//查询已经此颗树已经NG的次数
						tempRemainBoltCount = Integer.parseInt(Aservice.find14(sn, stationBoltName, boltName).getTempRemainBoltCount());

						// NG次数>返修次数
						if (tempRemainBoltCount > Integer.parseInt(tempReworkTimes)) {
							// 查询剩余螺栓数量
								jo.put("isSuccess", false);
								jo.put("errMsg", "拧紧不合格,当前螺栓ng且达到返工次数");
//								rBolt = 71;// 当前螺栓ng且达到返工次数并返回剩余螺栓数量
								reworkTimesFlag = 1;
								joz.put("reworkTimesFlag", reworkTimesFlag);
								joz.put("remainNumber", Aservice.find15s(sn, stationBoltName, tempMaterialName));
								joz.put("BoltName", boltName);
								jo.put("result", joz);
								return;
						}else{
							Aservice.insert1001(dx);
							jo.put("isSuccess", false);
							jo.put("errMsg", "拧紧不合格");
							joz.put("reworkTimesFlag", reworkTimesFlag);
							joz.put("remainNumber", Aservice.find15s(sn, stationBoltName, tempMaterialName));
							joz.put("BoltName", boltName);
							jo.put("result", joz);
							return;
						}
					}else{  //ok
						Aservice.update1002s(aValues, tValues, rValues, emp, sn, stationBoltName, boltName);
						Aservice.update1001(sn, stationBoltName, boltName, 1);  //更改此颗数的Y
						jo.put("isSuccess", true);
						jo.put("errMsg", "拧紧成功");
						joz.put("reworkTimesFlag", reworkTimesFlag);
						joz.put("remainNumber", Aservice.find15s(sn, stationBoltName, tempMaterialName));
						joz.put("BoltName", boltName);
						jo.put("result", joz);
						return ;
					}
				} else {
					// //进入else表示之前没有存在还没有被OK的螺栓
					// 查询最大的已经ok最大的BOLT_NUM跟最大的BOLT_NUM
					AssembleBoltPT dx1 = Aservice.find1003(sn, stationBoltName, tempMaterialName);
					if (dx1.getDanQianBoltNum() < dx1.getMAXBoltNum()) {
						Integer num = dx1.getDanQianBoltNum()+1;
						boltName = tempMaterialName+"_"+num;
						Aservice.update1001(sn, stationBoltName, boltName, Y);
						Aservice.update1002(aValues, tValues, rValues, emp, sn, stationBoltName, boltName);
					}
				}


				if (tempAllResult!=null&&!tempAllResult.equals("OK")) {

					if (tempReworkTimes != null) {

						if (tempBoltReworkFlag == 1) {

							tempRemainBoltCount = Integer.parseInt(Aservice
									.find11(sn, stationBoltName, boltName).getTempRemainBoltCount());

							//tempRemainBoltCount NG次数      tempReworkTimes可返工次数
							if (tempRemainBoltCount > Integer.parseInt(tempReworkTimes)) {


								// 查询剩余螺栓数量
//								if (tempRemainBoltCount > 0) {
									jo.put("isSuccess", false);
									jo.put("errMsg", "报警拧紧不合格，当前螺栓ng且达到返工次数");
//									rBolt = 71;// 报警拧紧不合格 当前螺栓ng且达到返工次数并返回剩余螺栓数量
									reworkTimesFlag = 1;
									joz.put("reworkTimesFlag", reworkTimesFlag);
									joz.put("remainNumber",Integer.parseInt(
											Aservice.find12(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount()));

									jo.put("result", joz);
									return;
//								} else {
//									jo.put("isSuccess", false);
//									jo.put("errMsg", "报警拧紧不合格,当前螺栓ng且达到返工次数并返回剩余螺栓数量");
////									rBolt = 71;// 报警拧紧不合格 当前螺栓ng且达到返工次数并返回剩余螺栓数量
//									reworkTimesFlag = 1;
//									boltnums = 0;
//									return;
//								}

							} else {


								// 查询剩余螺栓数量
//								if (tempRemainBoltCount > 0) {
									jo.put("isSuccess", false);
									jo.put("errMsg", "拧紧不合格");
//									rBolt = 71;
									joz.put("reworkTimesFlag", reworkTimesFlag);
									joz.put("remainNumber", Integer.parseInt(
											Aservice.find13(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount()));

									jo.put("result", joz);
									return;
//								} else {
//									jo.put("isSuccess", false);
//									jo.put("errMsg", "拧紧不合格");
////									rBolt = 71;
//									boltnums = 0;
//									return;
//								}

							}

						} else {

							if (tempRemainBoltCount > Integer.parseInt(tempReworkTimes)) {
								tempRemainBoltCount = Integer.parseInt(
										Aservice.find15(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount());
								// 查询剩余螺栓数量
//								if (tempRemainBoltCount > 0) {
									jo.put("isSuccess", false);
									jo.put("errMsg", "拧紧不合格,当前螺栓ng且达到返工次数");
//									rBolt = 71;// 当前螺栓ng且达到返工次数并返回剩余螺栓数量
									reworkTimesFlag = 1;
									joz.put("reworkTimesFlag", reworkTimesFlag);
									joz.put("remainNumber", Integer.parseInt(Aservice.find14(sn, stationBoltName, boltName).getTempRemainBoltCount()));

									jo.put("result", joz);
									return;
//								} else {
//									jo.put("isSuccess", false);
//									jo.put("errMsg", "拧紧不合格,当前螺栓ng且达到返工次数并返回剩余螺栓数量");
//									rBolt = 71;// 当前螺栓ng且达到返工次数并返回剩余螺栓数量
//									reworkTimesFlag = 1;
//									boltnums = 0;
//									return;
//								}
							} else {

								if (tempRemainBoltCount > Integer.parseInt(tempReworkTimes)) {
									tempRemainBoltCount = Integer.parseInt(
											Aservice.find15(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount());
									// 查询剩余螺栓数量
										jo.put("isSuccess", false);
										jo.put("errMsg", "拧紧不合格,当前螺栓ng且达到返工次数");
//										rBolt = 71;// 当前螺栓ng且达到返工次数并返回剩余螺栓数量
										joz.put("reworkTimesFlag", reworkTimesFlag);
										joz.put("remainNumber", Integer.parseInt(Aservice
												.find14(sn, stationBoltName, boltName).getTempRemainBoltCount()));

										jo.put("result", joz);
										return;
								} else {
									AssembleBoltPT dx = Aservice.find1002(sn, stationBoltName, tempMaterialName);
									dx.setY(Y);
									dx.setR(rValues);
									dx.setA(aValues);
									dx.setT(tValues);
									boltName = dx.getBoltName();
									Aservice.insert1001(dx);
									Aservice.update1001(sn, stationBoltName, dx.getBoltName(), Y);
										jo.put("isSuccess", false);
										jo.put("errMsg", "拧紧不合格");
//										rBolt = 71;// 当前螺栓ng且达到返工次数并返回剩余螺栓数量
										joz.put("reworkTimesFlag", reworkTimesFlag);
										joz.put("remainNumber", Integer.parseInt(
												Aservice.find16(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount()));

										jo.put("result", joz);
										return;
								}
							}

						}

					} else {

						if (tempBoltReworkFlag == 1) {


							// 查询剩余螺栓数量
							if (tempRemainBoltCount > 0) {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								rBolt = 0;
								joz.put("reworkTimesFlag", reworkTimesFlag);
								joz.put("remainNumber", Integer.parseInt(
										Aservice.find17(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount()));

								jo.put("result", joz);
								return;
							} else {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								rBolt = 0;
								joz.put("reworkTimesFlag", reworkTimesFlag);
								joz.put("remainNumber", 0);

								jo.put("result", joz);
								return;
							}

						} else {


							// 查询剩余螺栓数量
							if (tempRemainBoltCount > 0) {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								rBolt = 0;
								joz.put("reworkTimesFlag", reworkTimesFlag);
								joz.put("remainNumber", Integer.parseInt(
										Aservice.find18(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount()));

								jo.put("result", joz);
								return;
							} else {
								jo.put("isSuccess", true);
								jo.put("errMsg", "");
//								rBolt = 0;
								joz.put("reworkTimesFlag", reworkTimesFlag);
								joz.put("remainNumber", 0);

								jo.put("result", joz);
								return;
							}

						}

					}

				} else {

					if (tempBoltReworkFlag == 1) {

						tempRemainBoltCount = Integer
								.parseInt(Aservice.find18(sn, stationBoltName, tempMaterialName).getTempRemainBoltCount());

						if (tempRemainBoltCount > 0) {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							rBolt = 0;
							joz.put("reworkTimesFlag", reworkTimesFlag);
							joz.put("remainNumber", 0);

							jo.put("result", joz);
							return;
						} else {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							rBolt = 0;
							joz.put("reworkTimesFlag", reworkTimesFlag);
							joz.put("remainNumber", 0);

							jo.put("result", joz);
							return;
						}

					} else {


						// 查询剩余螺栓数量
						if (tempRemainBoltCount > 0) {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							rBolt = 0;
							joz.put("reworkTimesFlag", reworkTimesFlag);
							joz.put("remainNumber", Aservice.find15s(sn, stationBoltName, tempMaterialName));

							jo.put("result", joz);
							return;
						} else {
							jo.put("isSuccess", true);
							jo.put("errMsg", "");
//							rBolt = 0;
							joz.put("reworkTimesFlag", reworkTimesFlag);
							joz.put("remainNumber", Aservice.find15s(sn, stationBoltName, tempMaterialName));

							jo.put("result", joz);
							return;
						}

					}

				}

			} else {
				// 系统没有初始化该螺栓信息或已装配完成
				jo.put("isSuccess", false);
				jo.put("errMsg", "系统没有初始化该螺栓信息或已装配完成");
				joz.put("reworkTimesFlag", reworkTimesFlag);
				joz.put("remainNumber", 0);

				jo.put("result", joz);
//				rBolt = 22;
				return;
			}

		} else {
			jo.put("isSuccess", false);
			jo.put("errMsg", "输入信息不完善");
			joz.put("reworkTimesFlag", reworkTimesFlag);
			joz.put("remainNumber", 0);

			jo.put("result", joz);
//			rBolt = 18;
			return;
		}
	}

	}

	@RequestMapping(value="getBolt",method = RequestMethod.POST)
	@OptionalLog(module="生产", module2="生产模拟", method="检验螺栓")
	public void getcurrentBoltP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 保存返回数据
		JSONObject jo = new JSONObject();
		// 保存返回数据
		JSONObject joz = new JSONObject();

		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		String snBarcode;//总成码
		String lineName;//产线名称
		Integer stepNo;//步序
		String stationBoltName;
		response.setContentType("application/json");

		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("getBolt");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {

			snBarcode = json.get("sn").toString();
			lineName = json.get("line").toString();
			stepNo = Integer.parseInt(json.get("step").toString());
			stationBoltName = json.get("station").toString();

		} catch (NullPointerException e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");
			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main(snBarcode, lineName, stepNo, stationBoltName,jo, joz);
//			joz.put("code", "0");
			jo.put("errMsg", "");
			jo.put("isSuccess", true);

			// 事件添加
			Map<String, Object> map = new HashMap<>();
			map.put("NAME", lineName);
			Map<String, Object> mapResult = serviceEvent.getLineCode(map);
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "成品");
			mapEvent.put("OBJECT_ID", snBarcode);
			mapEvent.put("EVENT", "检验螺栓");
			if(mapResult != null){
				mapEvent.put("PARAMETER1", mapResult.get("code"));
			}
			mapEvent.put("PARAMETER2", stationBoltName);
			mapEvent.put("PARAMETER3", stepNo);
			serviceEvent.addEvent(mapEvent);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("code", "17");
			joz.put("errMsg", "更新数据失败！");
			joz.put("boltNumber", 0);
			joz.put("remainNumber", 0);
		} finally {

			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("生成日志出错了");
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	public  synchronized void main(String sn,String  line, Integer step, String station ,JSONObject jo, JSONObject joz) {
		synchronized(this){
			String snBarcode = sn;
			String lineName = line;
			Integer stepNo = step;
			String stationBoltName = station;
		String totalRecipeId = Aservice.findAllPlan(snBarcode);  //总配方id
		if (snBarcode != null && stationBoltName != null && lineName != null && stepNo > 0) {

			tempStationType = Aservice.finds1(stationBoltName,lineName).getTempStationType();   //查询工位类型

			if (tempStationType.equals("0")) {
				GetcurrentBoltPT dx = Aservice.finds2(totalRecipeId,lineName, stationBoltName, snBarcode, stepNo.toString());
				tempMaterialName = dx.getTempMaterialName();
				tempReworkTimes = dx.getTempReworkTimes();
			} else {
				GetcurrentBoltPT dx = Aservice.finds3(totalRecipeId,lineName, stationBoltName, snBarcode, stepNo.toString());
				tempMaterialName = dx.getTempMaterialName();
				tempReworkTimes = dx.getTempReworkTimes();
			}

//			/查询当前没有装配或者ng的最小id螺栓数据0
			tempMinId = Aservice.finds4(snBarcode, stationBoltName, tempMaterialName);

			if (tempMinId == null) {
				tempMinId = Aservice.finds5(snBarcode, stationBoltName, tempMaterialName);
			} else {
				tempBoltReworkFlag = 1;
			}

			if (tempMinId > 0) {
				GetcurrentBoltPT dx = Aservice.finds6(tempMinId.toString());
				tempT = dx.getTempT();
				tempA = dx.getTempA();
				tempR = dx.getTempR();
				tempALimit = dx.getTempALimit();
				tempTLimit = dx.getTempTLimit();
				tempBoltName = dx.getTempBoltName();
				tempReworkFlag = dx.getTempReworkFlag();
				tempReworkSt = dx.getTempReworkSt();
				tempBoltNum = dx.getTempBoltNum();

				if (tempBoltReworkFlag.equals("1")) {

					tempRemainBoltCount = Aservice.finds7(snBarcode, stationBoltName, tempMaterialName);

					// 查询剩余螺栓数量
					if (tempRemainBoltCount > 0) {
						rBolt = 0;
						outBoltNum = tempBoltNum;
						remainNumber = tempRemainBoltCount;
						joz.put("boltName", tempMaterialName);
						joz.put("boltNumber", outBoltNum);
						joz.put("remainNumber", remainNumber);

						jo.put("result", joz);
						return;
					} else {
						rBolt = 0;
						outBoltNum = tempBoltNum;
						remainNumber = 0;
						joz.put("boltName", tempMaterialName);
						joz.put("boltNumber", outBoltNum);
						joz.put("remainNumber", remainNumber);

						jo.put("result", joz);
						return;
					}

				} else {

					tempRemainBoltCount =Aservice.finds8(snBarcode, stationBoltName, tempMaterialName);

					if (tempRemainBoltCount > 0) {
						rBolt = 0;
						outBoltNum = tempBoltNum;
						remainNumber = tempRemainBoltCount;
						joz.put("boltName", tempMaterialName);
						joz.put("boltNumber", outBoltNum);
						joz.put("remainNumber", remainNumber);

						jo.put("result", joz);
						return;
					} else {
						rBolt = 0;
						outBoltNum = tempBoltNum;
						joz.put("boltNumber", outBoltNum);
						joz.put("remainNumber", 0);

						jo.put("result", joz);
						return;
					}

				}

			} else {
				rBolt = 42;// 没有可装配的螺栓
				joz.put("boltNumber", 0);
				joz.put("remainNumber", 0);

				jo.put("result", joz);
				return;
			}

		} else {
			// 输入信息不完整
			rBolt = 18;
			joz.put("boltNumber", 0);
			joz.put("remainNumber", 0);

			jo.put("result", joz);
			return;
		}
		}
	}

}
