package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.MoveDataPT;
import com.skeqi.mes.pojo.api.MoveOrderDataPT;
import com.skeqi.mes.pojo.api.StationpassPT;
import com.skeqi.mes.pojo.api.UpdatePlanPT;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.UpdatePlanPService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * @date 2020年1月13日
 * @author yinp R表转移到P表（打印表、工单表、计划表）
 */
@RestController
@RequestMapping("api")
public class UpdatePlanPController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	UpdatePlanPService service;

	/**
	 * 入参
	 */
	public Integer runPlanId;
	public Integer runLineId;
	/**
	 * 既是入参也是出参
	 */
	public Integer runR;
	/**
	 * 临时变量
	 */
	Integer tempPlanBCount;// 是否有前计划
	Integer tempPlanLevel;
	Integer resultCode = 0;



	public UpdatePlanPController() {
		runR = 0;
		// TODO Auto-generated constructor stub
	}

	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();

	/**
	 * 入参
	 */
	public String snMove;
	/**
	 * 出参
	 */
	public Integer rMove;
	/**
	 * 临时变量
	 */
	String rMessage;

	// 入参
		public String runOrderId;
		public String runPrintCode;
		// 既是入参也是出参

		/**
		 * 入参
		 */
		String strLine;
		String beginTime;
		String endTime;
		/**
		 * 出参
		 */
		String addtime;
		String averagetime;
		String qualified;
		String empgroup;
		String station;
		/**
		 * 临时变量
		 */
		Integer tempTime;
		Integer num1;
		Integer num2;
		Integer temp1;
		Integer temp2;
		Integer temp3;
		Integer temp4;
		String testAddtime1;
		String testAddtime2;

		String testNg;
		String testAll;

		Integer testEmpnum;
		String testEmp;
		String testEmpgroup;
		String locationStationSt;

		@RequestMapping(value = "StationpassP", method = RequestMethod.POST)
		public void StationpassP(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			dx.setApiName("StationpassP");
			dx.setCallTime(DateUtil.getNowDate());
			dx.setParameter(str);

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			try {
				strLine = json.get("strLine").toString();
				beginTime = json.get("beginTime").toString();
				endTime = json.get("endTime").toString();
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
				mainStation();
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

				joz.put("addtime", addtime);
				joz.put("averagetime", averagetime);
				joz.put("qualified", qualified);
				joz.put("empgroup", empgroup);
				joz.put("station", station);

				jo.put("result", joz);
				// jo.put("code", runR);

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

		public void mainStation() {

			// 查询产线对应的工位
			List<StationpassPT> list1 = service.findStation1(strLine);
			// 查询对应产线对应工位的对应条码
			List<StationpassPT> list2 = service.findStation2(strLine, locationStationSt, beginTime, endTime);

			for (StationpassPT dx1 : list1) {
				locationStationSt = dx1.getLocationStationSt();

				for (StationpassPT dx2 : list2) {

					num1 = Integer.parseInt(service.findStation3(dx2.getLocationDataSn(), strLine, locationStationSt).getNum1());
					num2 = Integer.parseInt(service.findStation4(dx2.getLocationDataSn(), strLine, locationStationSt).getNum2());
					temp1 = num1 - num2;

					if (temp1 == temp2) {

						temp3 = Integer
								.parseInt(service.findStation5(dx2.getLocationDataSn(), strLine, locationStationSt).getTemp3());
						tempTime = tempTime + temp3;
						temp4++;

					} else {
						tempTime = tempTime;
					}

				}

				testNg = service.findStation6(strLine, locationStationSt, beginTime, endTime).getTestNg();
				testAll = service.findStation7(strLine, locationStationSt).getTestAll();

				qualified = qualified + "," + testNg + "/" + testAll;
				addtime = addtime + "," + tempTime;

				// 班组获取
				testEmpnum = Integer
						.parseInt(service.findStation8(strLine, locationStationSt, beginTime, endTime).getTestEmpnum());

				if (testEmpnum == temp2) {
					empgroup = testEmpnum + "," + "当前无班组";
				} else {
					testEmpnum = Integer
							.parseInt(service.findStation9(strLine, locationStationSt, beginTime, endTime).getTestEmpnum());
				}

				// 班组获取
				station = station + "," + locationStationSt;

				if (temp4 == temp2) {
					averagetime = averagetime + ",0";
					tempTime = 0;
				} else {
					averagetime = averagetime + "," + (tempTime / Integer.parseInt(testAll));
					tempTime = 0;
					temp4 = 0;
				}

			}

		}

		public String dateReplace(String time) {
			return time.replace("-", "").replace(" ", "").replace(":", "").replace("/", "");
		}


		@RequestMapping(value="moveOrderData",method = RequestMethod.POST)
		public void moveOrderData(HttpServletRequest request,HttpServletResponse response) throws IOException {
			String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

			response.setContentType("application/json");

			joz.put("param", str);
			if (str == null || str == "") {
				jo.put("isSuccess", false);
				jo.put("code", "201");
				jo.put("msg", "发送数据为空");
				response.getWriter().append(jo.toJSONString());
			}

			CMesWebApiLogs dx = new CMesWebApiLogs();
			dx.setApiName("moveOrderData");
			dx.setCallTime(DateUtil.getNowDate());
			dx.setParameter(str);

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);


			try {
				runOrderId = json.get("runOrderId").toString();
				runPrintCode = json.get("runPrintCode").toString();
				rMove = Integer.parseInt(json.get("rMove").toString());
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
				mainOrder();
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

		public void mainOrder() {

			List<MoveOrderDataPT> cPlanPrints = service.findOrder1(runPrintCode);

			for (MoveOrderDataPT dx : cPlanPrints) {

				service.insertOrder1(dx);

				service.deleteOrder1(dx.getcPlanPrintId());
			}

			List<MoveOrderDataPT> cWorkOrder = service.findOrder2(runOrderId);

			for (MoveOrderDataPT dx : cWorkOrder) {

				service.insertOrder2(dx);

				service.deleteOrder1(dx.getcPlanPrintId());

			}

		}

	@RequestMapping(value = "moveDataP", method = RequestMethod.POST)
	public void moveDataP(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		dx.setApiName("moveDataP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);
		snMove = json.get("snMove").toString();
		try {
			// 调用方法主体
			mainData();
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

			joz.put("rIni", rMove);
			joz.put("code", rMove);
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

	@Transactional
	public void mainData() {

		List<MoveDataPT> list1 = service.findListData1(snMove);
		List<MoveDataPT> list2 = service.findListData2(snMove);
		List<MoveDataPT> list3 = service.findListData3(snMove);

		for (MoveDataPT dx : list1) {
			service.insertData1(dx);
		}
		for (MoveDataPT dx : list2) {
			service.insertData2(dx);
		}
		for (MoveDataPT dx : list3) {
			service.insertData3(dx);
		}

	}

	@RequestMapping(value = "updatePlanP", method = RequestMethod.POST)
	public void updatePlanP(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		dx.setApiName("updatePlanP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			runPlanId = Integer.parseInt(json.get("runPlanId").toString());
			runLineId = Integer.parseInt(json.get("runLineId").toString());
			runR = Integer.parseInt(json.get("runR").toString());
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
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
//			main();
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
			joz.put("runR", runR);
			joz.put("code", runR);
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


	public void insertP(Integer planid,Integer lineId ){

			System.out.println(planid+"***"+lineId);
			System.out.println(planid+"***"+lineId);
			System.out.println(planid+"***"+lineId);
			System.out.println(planid+"***"+lineId);
			System.out.println(planid+"***"+lineId);
			System.out.println(planid+"***"+lineId);
			List<UpdatePlanPT> list1 = service.findList1(planid,lineId);
			List<UpdatePlanPT> list2 = service.findList2(lineId.toString());
			List<UpdatePlanPT> list3 = service.findList3(planid.toString());
			List<UpdatePlanPT> list4 = service.findList4(planid.toString());

			for (UpdatePlanPT dx : list1) {
				service.insert1(dx);
			}
			for (UpdatePlanPT dx : list2) {
				service.insert2(dx);
			}
			for (UpdatePlanPT dx : list3) {
				service.insert3(dx);
			}
			for (UpdatePlanPT dx : list4) {
				service.update1(dx.getcPlanPlanLevel().toString(), dx.getcPlanId().toString());
			}
	}


}
