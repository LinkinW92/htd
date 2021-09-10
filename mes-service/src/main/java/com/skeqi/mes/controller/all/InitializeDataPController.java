package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.InitializeDataPT;
import com.skeqi.mes.pojo.api.InitializeReworkDataPT;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.InitializeDataPService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * @date 2020年1月11日
 * @author yinp
 *
 */
@RestController
@RequestMapping("api")
public class InitializeDataPController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	InitializeDataPService service;

	/**
	 * 入参
	 */
	public Integer iniProductionId;
	public String snIni;
	public Integer productionInit;
	public String stationType;
	public Integer stationInId;
	//**工艺路线id//
	public Integer routingId;
	//**总配方id//
	public Integer totalRecipeId;

	/**
	 * 既是出参也是入参
	 */
	public Integer rIni;

	/**
	 * 临时变量
	 */
	Integer whileTemp;
	Integer tempStationRecipeId;
	String tempParamartersName;
	String tempExceptionMsg;
	String tempStationName;

	String cRecipesStepCategory;
	String cRecipesMaterialName;
	String cRecipesMaterialpn;
	String cRecipesNumbers;
	String cRecipesTLimit;
	String cRecipesALimit;

	public InitializeDataPController() {
		rIni = 0;
		// TODO Auto-generated constructor stub
	}

	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();

	/**
	 * 入参
	 */
	String serialnumber;
	String station;
	String line;

	/**
	 * 出参
	 */
	Integer r;

	/**
	 * 临时变量
	 */
	Integer tempSteprecordCount;// 记录条数
	Integer tempStep;// 步序
	Integer maxid;// 最大id


	/**
	 * 入参
	 */
	public Integer productionId;
	public String snRework;
	/**
	 * 既是出参也是入参
	 */
	public Integer rRework;
	/**
	 * 临时变量
	 */

	String cStationsStName;


	@RequestMapping(value="initializeReworkDataP",method = RequestMethod.POST)
	public void initializeReworkDataP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("initializeReworkDataP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			productionId = Integer.parseInt(json.get("productionId").toString());
			snRework = json.get("snRework").toString();
			rRework = Integer.parseInt(json.get("rRework").toString());
		} catch (NullPointerException e) {
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
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main2(service);
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {
			jo.put("isSuccess", false);
			joz.put("rRework", rRework);
			joz.put("code", rRework);
			jo.put("result", joz);

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

	public void main2(InitializeDataPService service) {
			//查询返修路线涉及的工位名称
//			List<InitializeReworkDataPT> list1 = service.finds1(snRework);
//
//			for (InitializeReworkDataPT dx1 : list1) {
				//***根据总配方id和工位id查询配方id**//
				InitializeDataPT a = service.find3(stationInId.toString(),totalRecipeId);
				tempStationRecipeId = a.getTempStationRecipeId();

				//***根据工位id查询工位名称**//
				InitializeDataPT b = service.find4(stationInId.toString());
				tempStationName = b.getTempStationName();

//				service.updateRBolt(snRework, b.getTempStationName());
//				service.updateRKeypart(snRework, b.getTempStationName());
//				service.updateRLeakage(snRework, b.getTempStationName());
//				service.updateRBoltNg(snRework, b.getTempStationName());


				service.deleteRBolt(snIni, b.getTempStationName());
				service.deleteRKeypart(snIni, b.getTempStationName());
				service.deleteRLeakage(snIni, b.getTempStationName());

				Integer findKeypart = service.findKeypart(tempStationName, snIni);
				Integer findBolt = service.findBolt(tempStationName, snIni);
				Integer findWeight = service.findWeight(tempStationName, snIni);
				Integer findleakage = service.findleakage(tempStationName, snIni);

				//**根据配方id查询配方明细**//
				List<InitializeDataPT> list2 = service.find2(tempStationRecipeId.toString());

				for (InitializeDataPT dx2 : list2) {

					cRecipesStepCategory = dx2.getcRecipesStepCategory();
					cRecipesMaterialName = dx2.getcRecipesMaterialName();
					cRecipesMaterialpn = dx2.getcRecipesMaterialpn();
					cRecipesNumbers = dx2.getcRecipesNumbers();
					cRecipesTLimit = dx2.getcRecipesTLimit();
					cRecipesALimit = dx2.getcRecipesALimit();

					if (cRecipesStepCategory.equals("3") && findKeypart<=0) {  //扫描物料
						service.insert1(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("5") && findKeypart<=0) {  //扫描模组
						service.insert2(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("6") && findKeypart<=0) {  //扫描电信
						service.insert3(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("12") && findKeypart<=0) {  //用户录入
						service.insert4(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("7") && findBolt<=0) {  //拧紧
						// 拧紧
						whileTemp = 1;
						while (whileTemp <= Integer.parseInt(cRecipesNumbers)) {
							tempParamartersName = cRecipesMaterialName + "_" + whileTemp;
							service.insert5(snIni, tempStationName, cRecipesTLimit, cRecipesALimit, tempParamartersName,
									whileTemp.toString());
							whileTemp++;
						}
						continue;
					}
					if (cRecipesStepCategory.equals("8") && findleakage<=0) {  //气密
						service.insert6(tempStationName, snIni, cRecipesMaterialName);
						continue;
					}
					if (cRecipesStepCategory.equals("15") && findWeight<=0) {  //称重
						service.insert7(tempStationName, snIni);
						continue;
					}
					if (cRecipesStepCategory.equals("4")  && findKeypart<=0) {   //扫描唯一码
						service.insert8(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("16")  && findKeypart<=0) {  //扫描二级总成
						service.insert9(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}

				}

			}

//				//查询配方id
//				InitializeReworkDataPT a = service.finds3(dx1.getcStationsStName(), totalRecipeId.toString());
//
//				tempStationRecipeId = Integer.parseInt(a.getTempStationRecipeId());
//
//				List<InitializeReworkDataPT> list2 = service.finds2(tempStationRecipeId.toString());
//
//				for (InitializeReworkDataPT dx2 : list2) {
//
//					if (dx2.getcRecipesStepCategory().equals("3")) {
//						service.updateRKeypart(snRework, cStationsStName);
////						service.inserts1(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("4")) {
//						service.updateRKeypart(snRework, cStationsStName);
////						service.inserts2(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("5")) {
//						service.updateRKeypart(snRework, cStationsStName);
////						service.inserts3(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("6")) {
//						service.updateRKeypart(snRework, cStationsStName);
////						service.inserts4(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("12")) {
////						service.inserts5(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("7")) {
//						service.
////						service.inserts6(snRework, cStationsStName, dx2.getcRecipesTLimit(), dx2.getcRecipesALimit(),
////								tempParamartersName);
//						continue;
//					}
//					if (dx2.getcRecipesStepCategory().equals("8")) {
////						service.inserts7(cStationsStName, snRework, dx2.getcRecipesMaterialName());
//						continue;
//					}
//
//				}

//			}


	@RequestMapping(value = "initializeCurrentstepP", method = RequestMethod.POST)
	public void initializeCurrentstepP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("code", "201");
			jo.put("msg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("initializeCurrentstepP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);


		try {
			serialnumber = json.get("serialnumber").toString();
			station = json.get("station").toString();
			line = json.get("line").toString();
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
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			mains();
			jo.put("isSuccess", false);
			joz.put("code", "0");
			jo.put("errMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {

			joz.put("r", r);
			joz.put("code", r);
			jo.put("result", joz);

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

	public void mains() {
		tempSteprecordCount = Integer.parseInt(service.find1s(serialnumber, station, line).getTempSteprecordCount());

		if (tempSteprecordCount == 0) {
			// 没有步序记录
			service.insert1s(serialnumber, station, line);
			r = 0;
			jo.put("msg", "没有步序记录");
		} else if (tempSteprecordCount == 1) {
			r = 0;
			jo.put("msg", "一条步序记录");
		} else {
			r = 53;
			jo.put("msg", "多条步序记录");
		}

	}

	@RequestMapping(value = "initializeDataP", method = RequestMethod.POST)
	public void initializeDataP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("initializeDataP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			iniProductionId = Integer.parseInt(json.get("iniProductionId").toString());
			snIni = json.get("snIni").toString();
			productionInit = Integer.parseInt(json.get("productionInit").toString());
			stationType = json.get("stationType").toString();
			stationInId = Integer.parseInt(json.get("stationInId").toString());
			rIni = Integer.parseInt(json.get("rIni").toString());
		} catch (NullPointerException e) {
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
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main(null);
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {

			joz.put("rIni", rIni);
			joz.put("code", rIni);
			jo.put("result", joz);

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

	public static class BoltInfo {
		public String snIni, tempStationName, cRecipesTLimit, cRecipesALimit, tempParamartersName, whileTemp;
		public Integer MATERIAL_INSTANCE_ID;

		public BoltInfo(String snIni, String tempStationName, String cRecipesTLimit, String cRecipesALimit,
						String tempParamartersName, String whileTemp, Integer MATERIAL_INSTANCE_ID) {
			this.snIni = snIni;
			this.tempStationName = tempStationName;
			this.cRecipesTLimit = cRecipesTLimit;
			this.cRecipesALimit = cRecipesALimit;
			this.tempParamartersName = tempParamartersName;
			this.whileTemp = whileTemp;
			this.MATERIAL_INSTANCE_ID = MATERIAL_INSTANCE_ID;
		}
	}

	public void main(InitializeDataPService service1) {
		if (stationType.equals("0")) {

			if(service==null){
				service = service1;
			}

			//**获取工艺路线的所有工位id***//
//			List<InitializeDataPT> list1 = service.find1(routingId);
//
//
//			for (InitializeDataPT dx1 : list1) {

				//***根据总配方id和工位id查询配方id**//
				InitializeDataPT a = service.find3(stationInId.toString(),totalRecipeId);
				tempStationRecipeId = a.getTempStationRecipeId();

				//***根据工位id查询工位名称**//
				InitializeDataPT b = service.find4(stationInId.toString());
				tempStationName = b.getTempStationName();

				Integer findKeypart = service.findKeypart(tempStationName, snIni);
				Integer findBolt = service.findBolt(tempStationName, snIni);
				Integer findWeight = service.findWeight(tempStationName, snIni);
				Integer findleakage = service.findleakage(tempStationName, snIni);

				//**根据配方id查询配方明细**//
				List<InitializeDataPT> list2 = service.find2(tempStationRecipeId.toString());

				for (InitializeDataPT dx2 : list2) {

					cRecipesStepCategory = dx2.getcRecipesStepCategory();
					cRecipesMaterialName = dx2.getcRecipesMaterialName();
					cRecipesMaterialpn = dx2.getcRecipesMaterialpn();
					cRecipesNumbers = dx2.getcRecipesNumbers();
					cRecipesTLimit = dx2.getcRecipesTLimit();
					cRecipesALimit = dx2.getcRecipesALimit();

					if (cRecipesStepCategory.equals("3") && findKeypart<=0) {  //扫描物料
						service.insert1(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("5") && findKeypart<=0) {  //扫描模组
						service.insert2(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("6") && findKeypart<=0) {  //扫描电信
						service.insert3(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("12") && findKeypart<=0) {  //用户录入
						service.insert4(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("7") && findBolt<=0) {  //拧紧
						// 拧紧
						Integer MATERIAL_INSTANCE_ID = service.getMaterialInstanceIdWrapper("螺栓");
						List<BoltInfo> boltInfos = new ArrayList<>();
						whileTemp = 1;
						while (whileTemp <= Integer.parseInt(cRecipesNumbers)) {
							tempParamartersName = cRecipesMaterialName + "_" + whileTemp;
							BoltInfo boltInfo = new BoltInfo(snIni, tempStationName, cRecipesTLimit, cRecipesALimit,
									tempParamartersName, whileTemp.toString(), MATERIAL_INSTANCE_ID);
							boltInfos.add(boltInfo);
							whileTemp++;
						}

						service.insertBoltData(boltInfos);
						continue;
					}
					if (cRecipesStepCategory.equals("8") && findleakage<=0) {  //气密
						service.insert6(tempStationName, snIni, cRecipesMaterialName);
						continue;
					}
					if (cRecipesStepCategory.equals("15") && findWeight<=0) {  //称重
						service.insert7(tempStationName, snIni);
						continue;
					}
					if (cRecipesStepCategory.equals("4")  && findKeypart<=0) {   //扫描唯一码
						service.insert8(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}
					if (cRecipesStepCategory.equals("16")  && findKeypart<=0) {  //扫描二级总成
						service.insert9(tempStationName, snIni, cRecipesMaterialName, cRecipesMaterialpn);
						continue;
					}

				}

			}

		}
//	}

}
