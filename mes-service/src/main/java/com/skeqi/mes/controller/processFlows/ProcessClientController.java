package com.skeqi.mes.controller.processFlows;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.qh.CQhAuthorityInterfaceT;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ProcessLogModel;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.controller.crm.BusinessInfoFileController;
import com.skeqi.mes.service.processFlows.ProcessClientService;
import com.skeqi.mes.service.processFlows.ProcessDetailsService;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "工序流转客户端", description = "工序流转客户端", produces = MediaType.APPLICATION_JSON)
public class ProcessClientController {

	@Autowired
	private ProcessClientService service;
	@Autowired
	private ProcessLogInfoService logInfos;
	@Autowired
	private ProcessDetailsService servicexx;

	@Value(value = "${fileName.FileIP}")
	String pdfFileIP = "";


	//	批量下推5.0
	@RequestMapping(value = "/newUpdatePushDownData", method = RequestMethod.POST)
	@ApiOperation(value = "更新普通下推数据5.0", notes = "更新普通下推数据5.0")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson newUpdatePushDownData(HttpServletRequest request, @RequestBody String info) throws ServicesException {
		try {
			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskIdxl = jsonObject.get("ids");
				Object nextProcessOrderxl = jsonObject.get("processOrder");
				Object OKNumxl = jsonObject.get("OKNum");

				Map<String, Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), Integer.parseInt(nextProcessOrderxl.toString()));
				Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());

				Map<String, Object> showTaskIndox = service.showTaskStatusInfos(Integer.parseInt(showNextProcessMapsxl.get("TASK_ID").toString()));

				Integer testingNumxsxl = (Integer) showNextProcessMapsxl.get("TESTING_NUM");
				testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
				if (testingNumxsxl > (Integer) showNextProcessMapsxl.get("PLAN_NUM")) {
					return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
				}
				pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));
				Integer nextOutNumxl = testingNumxsxl - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_NUM_TEM_APPROVAL"))) - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("NG_NUM_TEM_APPROVAL")));


				//				更新下道工序信息(待检测数量)
				service.updateNextProcessInfox(testingNumxsxl, Integer.parseInt(taskIdxl.toString()), Integer.parseInt(nextProcessOrderxl.toString()), nextOutNumxl, pushDownNum.toString());

				String userNamexl = ToolUtils.getCookieUserName(request);
				Object projectNumxl = jsonObject.get("projectNum");
				Object specificationModelsxl = jsonObject.get("specificationModel");
				Object processNamexl = jsonObject.get("processName");
				Object stationNumxl = jsonObject.get("stationNum");
				Object meterialCodexl = jsonObject.get("meterialCode");

				//获取客户端IP地址
				ShowIPInfo ipxl = new ShowIPInfo();
				String ipInfoxl = ipxl.getIpAddr(request);
//				String qualityInspectorxl = request.getParameter("qualityInspector");
				String qualityInspectorxl = ToolUtils.getCookieUserName(request);
				String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl.toString()));
				ProcessLogModel modelxl = new ProcessLogModel();
				String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(showTaskIndox.get("PROJECT_NAME")), projectNumxl.toString(), specificationModelsxl.toString(), processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "正常", "", stationNumxl.toString());
				logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsxl.get("TASK_ID")), processNamexl.toString(), logInfoxl, "下推");
			}
//			String taskIdxl = request.getParameter("ids");
//			String nextProcessOrderxl = request.getParameter("processOrder");
//			String OKNumxl = request.getParameter("OKNum");
//			根据生产任务与序号查找下一道工序


//			String projectNumxl = jsonObject.get("projectNum").toString();
//			String specificationModelsxl = jsonObject.get("specificationModel").toString();
//			String processNamexl = jsonObject.get("processName").toString();
//			String stationNumxl = jsonObject.get("stationNum").toString();
//			String meterialCodexl = jsonObject.get("meterialCode").toString();

//			5.0
//			String userNamexl = ToolUtils.getCookieUserName(request);
//			String projectNumxl = request.getParameter("projectNum");
//			String specificationModelsxl = request.getParameter("specificationModel");
//			String processNamexl = request.getParameter("processName");
//			String stationNumxl = request.getParameter("stationNum");
//			String meterialCodexl = request.getParameter("meterialCode");
//


			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	工序4.0下推数据
	@RequestMapping(value = "/updatePushDownData", method = RequestMethod.POST)
	@ApiOperation(value = "更新普通下推数据4.0", notes = "更新普通下推数据4.0")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson updatePushDownData(HttpServletRequest request) throws ServicesException {
		try {
			String taskIdxl = request.getParameter("ids");
			String nextProcessOrderxl = request.getParameter("processOrder");
			String OKNumxl = request.getParameter("OKNum");
//			根据生产任务与序号查找下一道工序
			Map<String, Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), Integer.parseInt(nextProcessOrderxl));
			Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());

			Map<String, Object> showTaskIndox = service.showTaskStatusInfos(Integer.parseInt(showNextProcessMapsxl.get("TASK_ID").toString()));

			Integer testingNumxsxl = (Integer) showNextProcessMapsxl.get("TESTING_NUM");
			testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
			if (testingNumxsxl > (Integer) showNextProcessMapsxl.get("PLAN_NUM")) {
				return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
			}
			pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));
			Integer nextOutNumxl = testingNumxsxl - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_NUM_TEM_APPROVAL"))) - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("NG_NUM_TEM_APPROVAL")));


			//				更新下道工序信息(待检测数量)
			service.updateNextProcessInfox(testingNumxsxl, Integer.parseInt(taskIdxl), Integer.parseInt(nextProcessOrderxl), nextOutNumxl, pushDownNum.toString());

//			String projectNumxl = jsonObject.get("projectNum").toString();
//			String specificationModelsxl = jsonObject.get("specificationModel").toString();
//			String processNamexl = jsonObject.get("processName").toString();
//			String stationNumxl = jsonObject.get("stationNum").toString();
//			String meterialCodexl = jsonObject.get("meterialCode").toString();
			String userNamexl = ToolUtils.getCookieUserName(request);
			String projectNumxl = request.getParameter("projectNum");
			String specificationModelsxl = request.getParameter("specificationModel");
			String processNamexl = request.getParameter("processName");
			String stationNumxl = request.getParameter("stationNum");
			String meterialCodexl = request.getParameter("meterialCode");

			//获取客户端IP地址
			ShowIPInfo ipxl = new ShowIPInfo();
			String ipInfoxl = ipxl.getIpAddr(request);
			String qualityInspectorxl = request.getParameter("qualityInspector");
//			String qualityInspectorxl = ToolUtils.getCookieUserName(request);
			String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
			ProcessLogModel modelxl = new ProcessLogModel();
			String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(showTaskIndox.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "正常", "", stationNumxl);
			logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	展示委外清单列表
	@RequestMapping(value = "/showOutsourceInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示委外清单列表", notes = "展示委外清单列表")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showOutsourceInfo(HttpServletRequest request) throws ServicesException {
		try {
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			List<Map<String, Object>> list = service.showAllOutsourceData(taskId, processId);
			return Rjson.success(200, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	判断工序是否存在操作数据
	@RequestMapping(value = "/dataUpdateInfo", method = RequestMethod.POST)
	@ApiOperation(value = "判断工序是否存在操作数据", notes = "判断工序是否存在操作数据")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson dataUpdateInfo(HttpServletRequest request) throws ServicesException {
		try {
			String routeId = request.getParameter("routeId");
			String processId = request.getParameter("processId");
			Integer flags = 0;//判断数据修改标识
			Integer planNum = 0;
			Integer testNum = 0;
			Integer outsourceOutNum = 0;
			Integer useableNum = 0;
			Integer NuNum = 0;
			Integer outSourceInNum = 0;
			Integer reworkNum = 0;
			Integer completeNum = 0;
			Integer sum = 0;

			List<Map<String, Object>> taskList = service.showAllTaskByRouteIdInfo(routeId);
			for (Map map : taskList) {
				String id = String.valueOf(map.get("ID"));
				List<Map<String, Object>> detailsList = service.showAllDetailsByIdInfo(id, processId);
				for (Map maps : detailsList) {
					String processOrder = String.valueOf(maps.get("PROCESS_ORDER"));
					if (processOrder.equals("1")) {
						planNum = Integer.parseInt(String.valueOf(maps.get("PLAN_NUM")));
						testNum = Integer.parseInt(String.valueOf(maps.get("TESTING_NUM")));
						if (planNum != testNum) {
							flags = 1;
						}
					} else {
						outsourceOutNum = Integer.parseInt(String.valueOf(maps.get("OUTSOURCE_OUT_NUM")));
						testNum = Integer.parseInt(String.valueOf(maps.get("TESTING_NUM")));
						useableNum = Integer.parseInt(String.valueOf(maps.get("USEABLE_NUM")));
						NuNum = Integer.parseInt(String.valueOf(maps.get("NG_NUM")));
						outSourceInNum = Integer.parseInt(String.valueOf(maps.get("OUTSOURCE_IN_NUM")));
						reworkNum = Integer.parseInt(String.valueOf(maps.get("REWORK_NUM")));
						completeNum = Integer.parseInt(String.valueOf(maps.get("COMPLETE_NUM")));
						sum = outsourceOutNum + testNum + useableNum + NuNum + outSourceInNum + reworkNum + completeNum;
						if (sum > 0) {
							flags = 1;
						}
					}
				}
			}

			return Rjson.success(200, flags);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	展示工序流转用户名称接口
	@RequestMapping(value = "/showUserNameInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示用户名", notes = "展示用户名")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showUserNameInfo(HttpServletRequest request) throws ServicesException {
		try {
			String userName = ToolUtils.getCookieUserName(request);
			return Rjson.success(200, userName);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	展示pdf的IP地址
	@RequestMapping(value = "/showFileIPInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示pdf的IP地址", notes = "展示pdf的IP地址")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showFileIPInfo(HttpServletRequest request) throws ServicesException {
		try {

//			Properties pps = new Properties();
//			 pps .load( ProcessClientController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//			 Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
//		        while(enum1.hasMoreElements()) {
//		            String strKey = (String) enum1.nextElement();
//		            String strValue = pps.getProperty(strKey);
//		            System.out.println(strKey + "=" + strValue);
//		        }
//		      String pdfFileIP  =  pps.getProperty("FileIP");
//		      System.out.println("pdfFileIP:"+pdfFileIP);
			return Rjson.success(200, pdfFileIP);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//删除子分支（新版）===================================================
	@RequestMapping(value = "/delOutSideDataSplit", method = RequestMethod.POST)
	@ApiOperation(value = "删除子分支", notes = "删除子分支")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delOutSideDataSplit(HttpServletRequest request) throws ServicesException {
		try {


			String id = request.getParameter("id");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");


			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
			Integer testNum = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
			Integer okNum = Integer.parseInt(String.valueOf(map.get("OK_INSOURCE_TEM_APPROVLA")));
//				testNum=testNum+okNum;
			testNum = okNum;
//				service.updateOutSideMainPlanNum(taskId, processId, String.valueOf(testNum));
			Map<String, Object> mapxx = service.showAllMainBranchInfo(taskId, processId);
			Integer testNumxx = Integer.parseInt(mapxx.get("TESTING_NUM_TEM").toString());
			testNumxx = testNumxx + testNum;
			service.updateOutSideMainPlanNumxx(taskId, processId, String.valueOf(testNumxx));

			service.delOutSideChildBrach(id);//主分支更新状态
			service.deloutSourceInfoById(taskId, processId, id);//删除委外清单列表
			Integer countNum = service.countSplitChildNums(taskId, processId);
			if (countNum == 1) {
				service.updateSplitStatusData(taskId, processId);
			}

//				打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//				获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
			String logInfo = model.splitChildInfoLog(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(mapx.get("STATION_NUM")), "删除子分支");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "删除子分支");
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delOutSideDataSplitTask", method = RequestMethod.POST)
	@ApiOperation(value = "删除子分支", notes = "删除子分支")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delOutSideDataSplitTask(HttpServletRequest request) throws ServicesException {
		try {


			String id = request.getParameter("id");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
			Integer testNum = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
			Integer okNum = Integer.parseInt(String.valueOf(map.get("OK_INSOURCE_TEM_APPROVLA")));

//				testNum=testNum+okNum;
			testNum = okNum;
//				加上主的数据更新
			Map<String, Object> mapxx = service.showAllMainBranchInfo(taskId, processId);
			Integer testNumxx = Integer.parseInt(mapxx.get("TESTING_NUM_TEM").toString());
			testNumxx = testNumxx + testNum;
			service.updateOutSideMainPlanNumxx(taskId, processId, String.valueOf(testNumxx));
			service.delOutSideChildBrach(id);//主分支更新状态
			service.deloutSourceInfoById(taskId, processId, id);//删除委外清单列表
			Integer countNum = service.countSplitChildNums(taskId, processId);
			if (countNum == 1) {
				service.updateSplitStatusData(taskId, processId);
			}

//				打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//				获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
			String logInfo = model.splitChildInfoLog(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(mapx.get("STATION_NUM")), "删除子分支");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "删除子分支");
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//拆分操作（新版）===================================================
	@RequestMapping(value = "/outSideDataSplit", method = RequestMethod.POST)
	@ApiOperation(value = "委外发出数据拆分", notes = "委外发出数据拆分")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson outSideDataSplit(HttpServletRequest request) throws ServicesException {
		try {
			String type = request.getParameter("type");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String okNum = request.getParameter("okNum");
			Map<String, Object> map = service.showAllMainBranchInfo(taskId, processId);//主分支对象
			String planNum = String.valueOf(map.get("PLAN_NUM"));
//			String planNum  = String.valueOf(map.get("TASK_ID"));
//			String planNum  = String.valueOf(map.get("PROCESS_ID"));
			String processOrder = String.valueOf(map.get("PROCESS_ORDER"));

			String testNum = String.valueOf(map.get("TESTING_NUM_TEM"));
			String testNums = String.valueOf(map.get("TESTING_NUM"));
			String id = String.valueOf(map.get("ID"));
			service.insertAllChildBranchInfo(taskId, processId, processOrder, planNum, String.valueOf(testNum), testNums);//新增子分支数据
			Integer maxId = service.showDetailsIdInfoById(taskId, processId);
//			获取最大的ID数据
			Integer idxx = service.showDetailsIdInfoById(taskId, processId);

			Integer listId = service.showDetailsIdInfoById(taskId, processId);
			if (okNum == null || okNum.equals("")) {
				service.insertOutsourceListDatasx(taskId, processId, String.valueOf(listId)); //新增委外清单列表（状态0）
			} else {
				Integer okNums = null;
				if (service.sumMainPlanNum(taskId, processId) == null) {
					okNums = 0;
				} else {
					okNums = service.sumMainPlanNum(taskId, processId);
				}

//				首站和计划数量比对， 非首站和上道工序的接收数量比对
				Integer order = Integer.parseInt(map.get("PROCESS_ORDER").toString());
				if (order == 1) {//首站工序（对比计划数量）
					Integer planNumxx = Integer.parseInt(map.get("PLAN_NUM").toString());
					if (planNumxx < (okNums + Integer.parseInt(okNum))) {
						service.delOutSideChildBrach(maxId.toString());
						return Rjson.error("委外发出数量不能大于可委外发出数量");
					} else {
//						service.insertAllChildBranchInfo(taskId, processId, processOrder, planNum,String.valueOf(testNum),testNums);//新增子分支数据
						service.insertOutsourceListDatas(taskId, processId, String.valueOf(listId)); //新增委外清单列表（状态1）委外发出审核
					}
				} else {//非首站
					Integer outNumxx = null;
					Integer orderBefore = order - 1;
					//查询上道工序的委外发出数量（对比）
					Map<String, Object> mapxx = service.showNextProcessInfo(Integer.parseInt(taskId), orderBefore);
//					判断是委外还是普通
//					如果是委外
//					寻找到上道工序的type
					System.out.println("type:" + type);
					Map<String, Object> mapxxx = servicexx.showProcessDetailsxx(Integer.parseInt(taskId.toString()), Integer.parseInt(mapxx.get("PROCESS_ID").toString()));
					type = mapxxx.get("STATION_TYPES").toString();

					if (type.equals("3")) {
						outNumxx = Integer.parseInt(mapxx.get("OUTSOURCE_IN_NUM").toString());
					} else {
						outNumxx = Integer.parseInt(mapxx.get("COMPLETE_NUM").toString());
					}


					if (outNumxx < (okNums + Integer.parseInt(okNum))) {
						return Rjson.error("委外发出数量不能大于可委外发出数量");
					} else {
//						service.insertAllChildBranchInfo(taskId, processId, processOrder, planNum,String.valueOf(testNum),testNums);//新增子分支数据
						service.insertOutsourceListDatas(taskId, processId, String.valueOf(listId)); //新增委外清单列表（状态1）委外发出审核
					}

				}

//				if(Integer.parseInt(testNums)<(okNums+Integer.parseInt(okNum))){
//					service.delOutSideChildBrach(idxx.toString());
//					return Rjson.error("委外发出数量不能大于可委外发出数量");
//				}
			}

			service.updateSplitMainBrachInfo(id);//主分支更新状态

//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
			String logInfo = model.splitChildInfoLog(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(mapx.get("STATION_NUM")), "拆分");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "记录拆分");
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//品检展示页面（新版）===================================================
	@RequestMapping(value = "/showQualityInfoList", method = RequestMethod.POST)
	@ApiOperation(value = "查询品检列表", notes = "查询品检列表")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showQualityInfoList(HttpServletRequest request) throws ServicesException {
		try {
			String statusFlags = request.getParameter("statusFlags");
			String materiCode = request.getParameter("materiCode");
			String stationNum = request.getParameter("stationNum");
			String projectNum = request.getParameter("projectNum");
			String processName = request.getParameter("processName");
			String specificationModel = request.getParameter("specificationModel");
			Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list = service.showQualityInfoList(statusFlags, stationNum, projectNum, specificationModel, materiCode,processName);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list, 5);
			return Rjson.success(210,pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	进入品检评审页面(新版)
	@RequestMapping(value = "/intoQualityApproval", method = RequestMethod.POST)
	@ApiOperation(value = "获取批量数据进入审批环节", notes = "获取批量数据进入审批环节")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson intoQualityApproval(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			System.out.println("品检员：" + person);
//		System.out.println(info);
			JSONArray jsonArray = JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object NGNum = jsonObject.get("NGNum");
				Object remarks = jsonObject.get("remarks");
				Object okNum = jsonObject.get("okNum");
				Object type = jsonObject.get("type");
				Object id = jsonObject.get("id");
//				System.out.println("id:"+id);
//				Object testingNumTem = jsonObject.get("testingNumTem");
//				if(String.valueOf(outsourceTem).equals("")||String.valueOf(outsourceTem)==null){
//					outsourceTem=0;
//				}
//				if(String.valueOf(testingNumTem).equals("")||String.valueOf(testingNumTem)==null){
//					testingNumTem=0;
//				}

				Map<String, Object> map = service.showAllQualityApprovasList("0", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				if (map == null || map.size() == 0) {
					map = service.showAllQualityApprovasList("1", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					if (map == null || map.size() == 0) {
						map = service.showAllQualityApprovasList("6", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						if (map == null || map.size() == 0) {
							map = service.showAllQualityApprovasList("7", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
							if (map == null || map.size() == 0) {
								map = service.showAllQualityApprovasList("8", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("10", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
									if (map == null || map.size() == 0) {
										map = service.showAllQualityApprovasList("11", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));

									}
								}
							}
						}
					}
				}
				String numTest = String.valueOf(map.get("TESTING_NUM_TEM"));
				if (numTest == null || numTest == "" || numTest.equals("null")) {
					numTest = "0";
				}
				String okInsourceTem = String.valueOf(map.get("OK_INSOURCE_TEMS"));
				if (okInsourceTem == null || okInsourceTem == "" || okInsourceTem.equals("null")) {
					okInsourceTem = "0";
				}

				String allQualityPerson = String.valueOf(map.get("ALL_QUALITY_PERSON"));
				if (allQualityPerson == null || allQualityPerson.equals("null")) {
					allQualityPerson = "";
				}
				if (person.equals("") || person == null) {
					person = "无";
				}

				allQualityPerson = person + "//" + allQualityPerson;

				if (numTest.equals("") || numTest == null) {
					numTest = "0";
				}

				String allRemarks = String.valueOf(map.get("ALL_REMARKS"));
				if (allRemarks == null || allRemarks.equals("null")) {
					allRemarks = "";
				}

				if (String.valueOf(remarks).equals("") || remarks == null) {
					remarks = "无";
				}
				allRemarks = String.valueOf(remarks) + "//" + allRemarks;

				Integer testNum = null;
				if (String.valueOf(type).equals("3")) {
					testNum = Integer.parseInt(okInsourceTem);
				} else {
					testNum = Integer.parseInt(numTest);
				}

				if (String.valueOf(okNum).equals("") || String.valueOf(okNum).equals("null") || String.valueOf(okNum) == null) {
					okNum = "0";
				}


				List<Map<String, Object>> splitQualityLists = service.showAllSplitQualityApprovasList(String.valueOf(taskId), String.valueOf(processId));//获取拆分数据
//				Integer ngApproval=null;
//				Integer okApproval=null;
				Integer ngNumSum = 0;
				Integer okNumSum = 0;

//				for(Map splitMap:splitQualityLists){
//					ngNumSum= ngNumSum+Integer.parseInt(String.valueOf(splitMap.get("NG_NUM_TEM_APPROVAL")));
//					okNumSum= okNumSum+ Integer.parseInt(String.valueOf(splitMap.get("OK_NUM_TEM_APPROVAL")));
//				}
//				System.out.println("合格数量："+okNumSum+"ng数量："+ngNumSum);

//				数据发生转变
				Integer ngApproval = Integer.parseInt(String.valueOf(map.get("NG_NUM_TEM_APPROVAL")));
				Integer okApproval = Integer.parseInt(String.valueOf(map.get("OK_NUM_TEM_APPROVAL")));
//				Integer ngApproval =ngNumSum;
//				Integer okApproval =okNumSum;

				Integer okNums = Integer.parseInt(String.valueOf(okNum)) + okApproval;//旧审核+新进
				Integer ngNums = Integer.parseInt(String.valueOf(NGNum)) + ngApproval;//旧审核+新进
				testNum = testNum - Integer.parseInt(String.valueOf(okNum)) - Integer.parseInt(String.valueOf(NGNum));
				if (testNum < 0) {
					return Rjson.error("提交审批数量超过可用于品检数量");
				}
				Integer outsideNum = null;
//				service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), String.valueOf(NGNum),"1",String.valueOf(remarks),person,String.valueOf(outsourceTem),String.valueOf(testNum),String.valueOf(testingNumTem));
				if (String.valueOf(type).equals("3")) {
					service.updateQualityInSourceApprovalLists("1", String.valueOf(testNum), String.valueOf(okNums), String.valueOf(ngNums), String.valueOf(taskId), String.valueOf(processId), allRemarks, allQualityPerson, String.valueOf(id));
					if (testNum == 0) {//委外任务状态3
//						Map<String,Object> outsideMap = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//						 outsideNum = Integer.parseInt(outsideMap.get("OUT_SIDE_NUM").toString());
//						 outsideNum=outsideNum-okNums-ngNums;

						service.updateQualityAllNumTask(okNums.toString(), ngNums.toString(), taskId.toString(), processId.toString(), id.toString(), testNum.toString());
					} else if (testNum > 0) {//委外任务状态20
						service.updateQualityPartNumTask(okNums.toString(), ngNums.toString(), taskId.toString(), processId.toString(), id.toString(), testNum.toString());
					}

				} else {
					service.updateQualityApprovalLists("1", String.valueOf(testNum), String.valueOf(okNums), String.valueOf(ngNums), String.valueOf(taskId), String.valueOf(processId), allRemarks, allQualityPerson, String.valueOf(id));
					service.updateSplitQualityData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum));

				}

//				更新主从计划待品检数据


//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson, "申请", String.valueOf(mapx.get("STATION_NUM")));
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "品检申请");


			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	进入品检评审页面(新版)
	@RequestMapping(value = "/intoQualityApprovalTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外任务质检申请", notes = "委外任务质检申请")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson intoQualityApprovalTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
//			String person  = request.getParameter("person");
//		System.out.println(info);
//		JSONArray jsonArray=JSONArray.fromObject(info);
//			for (int i = 0; i < jsonArray.size(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				Object taskId = jsonObject.get("taskId");
//				Object processId = jsonObject.get("processId");
//				Object NGNum = jsonObject.get("NGNum");
//				Object remarks = jsonObject.get("remarks");
//				Object okNum = jsonObject.get("okNum");
//				Object type = jsonObject.get("type");
//				Object id = jsonObject.get("id");
			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String okNum = request.getParameter("okNum");
			String NGNum = request.getParameter("ngNum");
			String remarks = request.getParameter("remarks");
			String id = request.getParameter("idxs");
			String type = "3";

//			Map<String,Object>  showTaskByIdxx = service.showTaskById(taskId);
////			String projectNumxl = jsonObject.get("projectNum").toString();
////			String specificationModelsxl = jsonObject.get("specificationModel").toString();
////			String processNamexl = jsonObject.get("processName").toString();
////			String stationNumxl = jsonObject.get("stationNum").toString();
////			String meterialCodexl = jsonObject.get("meterialCode").toString();
//			String userNamexl = ToolUtils.getCookieUserName(request);
//			String projectNumxl =showTaskByIdxx.get("PROJECT_NUM").toString();
//			String specificationModelsxl =showTaskByIdxx.get("SPECIFICATION_AND_MODEL").toString();
//			String processNamexl = request.getParameter("processId");
//			String stationNumxl =showTaskByIdxx.get("STATION_NUM").toString();
//			String meterialCodexl =showTaskByIdxx.get("MATERIAL_CODE").toString();
//
//			Integer okNum1 = Integer.parseInt(okNum.toString());
//			Integer ngNum1 = Integer.parseInt(NGNum.toString());
//			Integer OKNumxl =okNum1;

//				System.out.println("id:"+id);
//				Object testingNumTem = jsonObject.get("testingNumTem");
//				if(String.valueOf(outsourceTem).equals("")||String.valueOf(outsourceTem)==null){
//					outsourceTem=0;
//				}
//				if(String.valueOf(testingNumTem).equals("")||String.valueOf(testingNumTem)==null){
//					testingNumTem=0;
//				}

			Map<String, Object> map = service.showAllQualityApprovasList("0", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
			if (map == null || map.size() == 0) {
				map = service.showAllQualityApprovasList("1", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				if (map == null || map.size() == 0) {
					map = service.showAllQualityApprovasList("6", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					if (map == null || map.size() == 0) {
						map = service.showAllQualityApprovasList("7", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						if (map == null || map.size() == 0) {
							map = service.showAllQualityApprovasList("8", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
							if (map == null || map.size() == 0) {
								map = service.showAllQualityApprovasList("10", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("11", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));

								}
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("2", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								}
							}
						}
					}
				}
			}
			String numTest = String.valueOf(map.get("TESTING_NUM_TEM"));
			if (numTest == null || numTest == "" || numTest.equals("null")) {
				numTest = "0";
			}
			String okInsourceTem = String.valueOf(map.get("OK_INSOURCE_TEMS"));
			if (okInsourceTem == null || okInsourceTem == "" || okInsourceTem.equals("null")) {
				okInsourceTem = "0";
			}

			String allQualityPerson = String.valueOf(map.get("ALL_QUALITY_PERSON"));
			if (allQualityPerson == null || allQualityPerson.equals("null")) {
				allQualityPerson = "";
			}
			if (person.equals("") || person == null) {
				person = "无";
			}

			allQualityPerson = person + "//" + allQualityPerson;

			if (numTest.equals("") || numTest == null) {
				numTest = "0";
			}

			String allRemarks = String.valueOf(map.get("ALL_REMARKS"));
			if (allRemarks == null || allRemarks.equals("null")) {
				allRemarks = "";
			}

			if (String.valueOf(remarks).equals("") || remarks == null) {
				remarks = "无";
			}
			allRemarks = String.valueOf(remarks) + "//" + allRemarks;

			Integer testNum = null;
			if (String.valueOf(type).equals("3")) {
				testNum = Integer.parseInt(okInsourceTem);
			} else {
				testNum = Integer.parseInt(numTest);
			}

			if (String.valueOf(okNum).equals("") || String.valueOf(okNum).equals("null") || String.valueOf(okNum) == null) {
				okNum = "0";
			}


			List<Map<String, Object>> splitQualityLists = service.showAllSplitQualityApprovasList(String.valueOf(taskId), String.valueOf(processId));//获取拆分数据
//				Integer ngApproval=null;
//				Integer okApproval=null;
			Integer ngNumSum = 0;
			Integer okNumSum = 0;

//				for(Map splitMap:splitQualityLists){
//					ngNumSum= ngNumSum+Integer.parseInt(String.valueOf(splitMap.get("NG_NUM_TEM_APPROVAL")));
//					okNumSum= okNumSum+ Integer.parseInt(String.valueOf(splitMap.get("OK_NUM_TEM_APPROVAL")));
//				}
//				System.out.println("合格数量："+okNumSum+"ng数量："+ngNumSum);

//				数据发生转变
			Integer ngApproval = Integer.parseInt(String.valueOf(map.get("NG_NUM_TEM_APPROVAL")));
			Integer okApproval = Integer.parseInt(String.valueOf(map.get("OK_NUM_TEM_APPROVAL")));
//				Integer ngApproval =ngNumSum;
//				Integer okApproval =okNumSum;

			Integer okNums = Integer.parseInt(String.valueOf(okNum)) + okApproval;//旧审核+新进
			Integer ngNums = Integer.parseInt(String.valueOf(NGNum)) + ngApproval;//旧审核+新进
			testNum = testNum - Integer.parseInt(String.valueOf(okNum)) - Integer.parseInt(String.valueOf(NGNum));
			if (testNum < 0) {
				return Rjson.error("提交审批数量超过可用于品检数量");
			}
			Integer outsideNum = null;
//				service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), String.valueOf(NGNum),"1",String.valueOf(remarks),person,String.valueOf(outsourceTem),String.valueOf(testNum),String.valueOf(testingNumTem));
			if (String.valueOf(type).equals("3")) {
				service.updateQualityInSourceApprovalLists("1", String.valueOf(testNum), String.valueOf(okNums), String.valueOf(ngNums), String.valueOf(taskId), String.valueOf(processId), allRemarks, allQualityPerson, String.valueOf(id));
				if (testNum == 0) {//委外任务状态3
//						Map<String,Object> outsideMap = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//						 outsideNum = Integer.parseInt(outsideMap.get("OUT_SIDE_NUM").toString());
//						 outsideNum=outsideNum-okNums-ngNums;

					service.updateQualityAllNumTask(okNums.toString(), ngNums.toString(), taskId.toString(), processId.toString(), id.toString(), testNum.toString());
				} else if (testNum > 0) {//委外任务状态20
					service.updateQualityPartNumTask(okNums.toString(), ngNums.toString(), taskId.toString(), processId.toString(), id.toString(), testNum.toString());
				}

			} else {
				service.updateQualityApprovalLists("1", String.valueOf(testNum), String.valueOf(okNums), String.valueOf(ngNums), String.valueOf(taskId), String.valueOf(processId), allRemarks, allQualityPerson, String.valueOf(id));
				service.updateSplitQualityData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum));

			}

//				更新主从计划待品检数据


//				打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//				获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson, "申请", String.valueOf(mapx.get("STATION_NUM")));
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "品检申请");


//			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	更新品检审核内容（新版）
	@RequestMapping(value = "/updateQualityApproval", method = RequestMethod.POST)
	@ApiOperation(value = "更新审批数据", notes = "更新审批数据")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson updateQualityApproval(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			System.out.println("审核员:" + person);
//		System.out.println(info);
			JSONArray jsonArray = JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object NGNum = jsonObject.get("NGNum");
				Object OKNum = jsonObject.get("OKNum");
				Object id = jsonObject.get("id");
				Object splitFlags = jsonObject.get("splitFlags");

				String projectNumxl = jsonObject.get("projectNum").toString();
				String specificationModelsxl = jsonObject.get("specificationModel").toString();
				String processNamexl = jsonObject.get("processName").toString();
				String stationNumxl = jsonObject.get("stationNum").toString();
				String meterialCodexl = jsonObject.get("meterialCode").toString();
				String userNamexl = ToolUtils.getCookieUserName(request);


//				 	String projectNumxl = request.getParameter("projectNum");
//					String specificationModelsxl = request.getParameter("specificationModel");
//					String processNamexl = request.getParameter("processName");
//					String stationNumxl = request.getParameter("stationNum");
//					String meterialCodexl = request.getParameter("meterialCode");
//					Integer OKNumxl = Integer.parseInt(request.getParameter("OKNum"));
//					String userNamexl = ToolUtils.getCookieUserName(request);

				Integer okNum1 = Integer.parseInt(OKNum.toString());
				Integer ngNum1 = Integer.parseInt(NGNum.toString());
				Integer OKNumxl = okNum1;


				//循环遍历评审状态(2审核完成状态，1进入审核状态，0未操作状态)
//				service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), String.valueOf(NGNum),"2","","","","","");
				Map<String, Object> map = service.showAllQualityApprovasList("1", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				Integer userNum = null;
				if (map == null || map.size() == 0) {
					map = service.showAllQualityApprovasList("0", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					if (map == null || map.size() == 0) {
						map = service.showAllQualityApprovasList("6", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						if (map == null || map.size() == 0) {
							map = service.showAllQualityApprovasList("7", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
							if (map == null || map.size() == 0) {
								map = service.showAllQualityApprovasList("8", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("9", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
									if (map == null || map.size() == 0) {
										map = service.showAllQualityApprovasList("10", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
									}
									if (map == null || map.size() == 0) {
										map = service.showAllQualityApprovasList("2", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
									}
									if (map == null || map.size() == 0) {
										map = service.showAllQualityApprovasList("11", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
									}
								}
							}
						}
					}
				}
				if (String.valueOf(map.get("USEABLE_NUM")).equals("") || String.valueOf(map.get("USEABLE_NUM")) == null || String.valueOf(map.get("USEABLE_NUM")).equals("null")) {
					userNum = 0;
				} else {
					userNum = (Integer) map.get("USEABLE_NUM");//旧的可用数量+新的OK数量
				}

//				ok ng 待检测
				Integer ngNum = (Integer) map.get("NG_NUM");
				System.out.println(ngNum);
//				Integer testings  =  Integer.parseInt(String.valueOf(map.get("TESTING_NUM")));
				Integer testing = null;


//				testing = testing + testings;


				String reamrks = (String) map.get("REMARKS");
				String allReamrks = (String) map.get("ALL_REMARKS");
				Integer planNum = (Integer) map.get("PLAN_NUM");
				Integer comleteNum = (Integer) map.get("COMPLETE_NUM");
				Integer outsourceNum = (Integer) map.get("OUTSOURCE_OUT_NUM");
				String stationType = (String) map.get("STATION_TYPES");
				if (allReamrks != null) {
					if (allReamrks.equals("")) {
						allReamrks = reamrks;
						reamrks = "";
					} else {
						allReamrks = allReamrks + "--分割--" + reamrks;
						reamrks = "";
					}
				} else {
					allReamrks = reamrks;
					reamrks = "";
				}
				reamrks = "";

				if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null || String.valueOf(OKNum).equals("null")) {
					OKNum = 0;
				}
				if (String.valueOf(NGNum).equals("") || String.valueOf(NGNum) == null || String.valueOf(NGNum).equals("null")) {
					NGNum = 0;
				}
				String temsNum = null;
				Integer userNums = null;

				if (!stationType.equals("3")) {
					temsNum = String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS_APPROVAL"));
				} else {
					temsNum = String.valueOf(map.get("INSOURCE_IN_TEMS_APPROVAL"));
				}

				if (temsNum.equals("") || temsNum.equals("null") || temsNum == null) {
					temsNum = "0";
				}
				Integer newUseNums = userNum + Integer.parseInt((String.valueOf(OKNum)));
				userNum = userNum + Integer.parseInt((String.valueOf(OKNum))) - Integer.parseInt(temsNum);
				ngNum = ngNum + Integer.parseInt((String.valueOf(NGNum)));


//					if(stationType.equals("3")){
//						outsourceNum=outsourceNum+Integer.parseInt((String.valueOf(OKNum)))+Integer.parseInt((String.valueOf(NGNum)));//扣除委外
//					}
				Integer splitFlagsx = Integer.parseInt(String.valueOf(splitFlags));
//					if(splitFlagsx==4){
////						获取子分支testNum  获取主分支的testNum 主分支-子分支  更新主分支数据
////						Map<String,Object> mapDatas = service.showTestNumData(String.valueOf(id));
////						Integer testNumData = Integer.parseInt(String.valueOf(mapDatas.get("TESTING_NUM")));
//						Map<String,Object> dataMaps= service.showAllMainBranchInfo(String.valueOf(taskId), String.valueOf(processId));
//						String idData = String.valueOf(dataMaps.get("ID"));
//						Integer testNumsData = Integer.parseInt(String.valueOf(dataMaps.get("TESTING_NUM")));
//						testNumsData = testNumsData -Integer.parseInt(String.valueOf(OKNum))-Integer.parseInt((String.valueOf(NGNum)));
//						service.updateMainTestNumData(String.valueOf(testNumsData), idData);
//					}
				if (stationType.equals("3")) {//委外更新
					System.out.println(String.valueOf(OKNum));
					System.out.println(ngNum);


					testing = Integer.parseInt(String.valueOf(map.get("TESTING_NUM"))) - Integer.parseInt(String.valueOf(OKNum)) - Integer.parseInt((String.valueOf(NGNum)));//旧的待品检数量+新的待品检数量
				} else {
					testing = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));//旧的待品检数量+新的待品检数量
				}

				Integer ngNumx = service.showProcessById(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				ngNumx = ngNumx + Integer.parseInt((String.valueOf(NGNum)));


				Integer outsideNum = null;
//				service.updateQualityApprovalData(String.valueOf(testing), String.valueOf(userNum), String.valueOf(ngNum), String.valueOf(taskId), String.valueOf(processId),person,reamrks,allReamrks, String.valueOf(outsourceNum)); 更新数据
				if (stationType.equals("3")) {//委外更新


					service.updateQualityApprovalDatas(String.valueOf(testing), String.valueOf(ngNum), String.valueOf(userNum), String.valueOf(taskId), String.valueOf(processId), String.valueOf(outsourceNum), String.valueOf(newUseNums), String.valueOf(id));
//						委外任务质检审批
					Map<String, Object> qualityMap = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					outsideNum = Integer.parseInt(qualityMap.get("OUT_SIDE_NUM").toString());
					outsideNum = outsideNum - okNum1 - ngNum1;

					Map<String, Object> mapxyx = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer okNumExmine = Integer.parseInt(mapxyx.get("OK_NUM_EXAMINE").toString());
					Integer ngNumExmine = Integer.parseInt(mapxyx.get("NG_NUM_EXAMINE").toString());
					okNumExmine = okNumExmine + Integer.parseInt(OKNum.toString());
					ngNumExmine = ngNumExmine + Integer.parseInt(NGNum.toString());
					userNums = okNum1 + Integer.parseInt(mapxyx.get("OK_NUM_EXAMINE").toString()) + Integer.parseInt(mapxyx.get("REWORK_EXAMINE_NUM").toString()) - Integer.parseInt(mapxyx.get("IN_SIDE_NUM_EXAMINE").toString());
					service.updateOutsideExamineNum(userNums.toString(), ngNum.toString(), outsideNum.toString(), taskId.toString(), processId.toString(), id.toString(), okNumExmine.toString(), ngNumExmine.toString());
//						service.updateOutsideExamineNum(userNum.toString(), ngNum.toString(), outsideNum.toString(),taskId.toString(), processId.toString(), id.toString());
					if (outsideNum == 0) {
						service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "4");
					}
//						if(splitFlagsx==1 || splitFlagsx==2){
//							service.updateTestNumData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testing));
//						}
					Map<String, Object> mapxx = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
					Integer testNumxx = Integer.parseInt((mapxx.get("TESTING_NUM").toString()));
					Integer ngNumxx = Integer.parseInt((mapxx.get("NG_NUM").toString()));
					Integer useableNumxx = Integer.parseInt((mapxx.get("USEABLE_NUM").toString()));
					ngNumxx = ngNumxx + ngNum1;
					useableNumxx = useableNumxx + okNum1;
					testNumxx = testNumxx - Integer.parseInt(String.valueOf(OKNum)) - Integer.parseInt((String.valueOf(NGNum)));
					service.updateTestNumData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNumxx), useableNumxx.toString(), ngNumxx.toString());
//						testNumxx=testNumxx-Integer.parseInt(String.valueOf(OKNum))-Integer.parseInt((String.valueOf(NGNum)));
//						service.updateTestNumData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNumxx));


				} else {//非委外更新
					service.updateQualityApprovalDatasx(String.valueOf(testing), String.valueOf(ngNum), String.valueOf(userNum), String.valueOf(taskId), String.valueOf(processId), String.valueOf(outsourceNum), String.valueOf(newUseNums), String.valueOf(id));
				}

//				service.updateNGNumTem( String.valueOf(taskId), String.valueOf(processId));
				Integer oldNGNum = service.showNGNum(String.valueOf(taskId));
				oldNGNum = oldNGNum + Integer.parseInt((String.valueOf(NGNum)));
				service.updateTaskNGNum(String.valueOf(taskId), oldNGNum);


				if (!planNum.equals(comleteNum)) {
					if (stationType.equals("3")) {
						service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), "", "0", "", "", "", "", "", "1", String.valueOf(ngNumx), String.valueOf(id));
					} else {
						service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), "", "0", "", "", "", "", "", "0", String.valueOf(ngNumx), String.valueOf(id));
					}
				}


//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKNum), String.valueOf(NGNum), "", person, "审核", String.valueOf(mapx.get("STATION_NUM")));
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "品检审核");


//			  =================普通下推逻辑代码部分================
				Map<String, Object> mapxl = null;
				if (!stationType.equals("3")) {
					mapxl = service.showTaskList(projectNumxl, specificationModelsxl, processNamexl, meterialCodexl, stationNumxl);
				}
				if ((!stationType.equals("3")) && (Integer.parseInt(mapxl.get("USEABLE_NUM").toString()) > 0)) {

					String flagsxl = mapxl.get("STATUS_FLAGS").toString();
					if (flagsxl.equals("0")) {
						return Rjson.error(202, "生产任务为开始状态无法下推");
					} else if (flagsxl.equals("2")) {
						return Rjson.error(202, "生产任务为暂停状态无法下推");
					} else if (flagsxl.equals("3")) {
						return Rjson.error(202, "生产任务为完成状态无法下推");
					}

					Integer useableNumxl = (Integer) mapxl.get("USEABLE_NUM");//旧有可用数量

					if (useableNumxl < OKNumxl) {
						return Rjson.error(202, "下推数量不能超过可用数量");
					}
					Integer completeNumxl = (Integer) mapxl.get("COMPLETE_NUM");//旧有完成数量
					completeNumxl = completeNumxl + OKNumxl;//现有完成数量
					useableNumxl = useableNumxl - OKNumxl;//现有可用数量

//						System.out.println("abc:"+(Integer)map.get("ORDER_NUM"));
//						System.out.println("abc:"+Integer.parseInt(map.get("ORDER_NUM")));
//						下道工序的待检测数量==上道工序的下推数量
					Integer orderNumxl = null;

					orderNumxl = (Integer) mapxl.get("ORDER_NUM");

					Integer taskIdxl = (Integer) mapxl.get("TASK_ID");
//						求出当前工艺路线最大的ID
					Integer maxOrderxl = service.maxOrder(taskIdxl);

//						1.判断是否为委外做不同的更新2.委外品检减委外发出数量
					Integer processTypeInfosxl = service.showNowProcesstypeInfo(projectNumxl, specificationModelsxl, (Integer) mapxl.get("PROCESS_ID"));
					if (orderNumxl == maxOrderxl) {
//							if(1==0){
						//最后一道工序
//							1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
						if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {//完成数量==计划数量
							service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);

						} else {
							service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
						}
						if (processTypeInfosxl != 3) {//非委外
							service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//							  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//							  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


						} else {//委外
							service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
							//获取客户端IP地址
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");

						}

						if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
							ProcessLogModel modelxl = new ProcessLogModel();
							String logInfoxl = modelxl.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null, null, projectNumxl, specificationModelsxl, null, null, null, stationNumxl);
							logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxl, "生产任务");
						}
//							}

					} else if (orderNumxl < maxOrderxl) {//中间工序或者第一道工序
						if (processTypeInfosxl != 3) {//非委外
							service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
//								//获取客户端IP地址
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"正常","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

						} else {//委外
							service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
							//获取客户端IP地址
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//								String qualityInspectorxl = request.getParameter("qualityInspector");
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
							ProcessLogModel modelxl = new ProcessLogModel();
							String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "委外接收", "", stationNumxl);
							logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "委外接收");
						}


						Integer nextProcessOrderxl = orderNumxl + 1;


						//根据生产任务与序号查找下一道工序
//							获取下道工序主分支数据   获取task数据   判断工序类型

						Map<String, Object> showNextProcessMapxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
						Integer testingNumxl = (Integer) showNextProcessMapxl.get("TESTING_NUM");
						Map<String, Object> showNextProcessMapxxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
						Map<String, Object> showNextProcessMapxsxl = service.showTaskById(String.valueOf(taskIdxl));
						Integer processTypeInfoxl = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsxl.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsxl.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxxl.get("PROCESS_ID"))));
						if (processTypeInfoxl == 3) {//委外
							testingNumxl = testingNumxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
							if (testingNumxl > (Integer) showNextProcessMapxl.get("PLAN_NUM")) {
								return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
							}

							Map<String, Object> mapdxl = service.showTaskById(String.valueOf(taskIdxl));
							Integer planNumsxl = Integer.parseInt(String.valueOf(mapxl.get("PLAN_NUM")));
//									Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
							Integer nextOutNumxl = null;
//									初始品检临时数量都为计划数量，下推需要变成真实数量就需要减去计划数量（TESTING_NUM_TEM），第一道不用减，下推需要减去
							if (Integer.parseInt(String.valueOf(showNextProcessMapxl.get("PROCESS_ORDER"))) > 1) {
								if (Integer.parseInt((String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")))) >= planNumsxl) {
									nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM"))) - planNumsxl;
								} else {
									nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
								}

							} else {
								nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
							}

							//				更新下道工序信息(待检测数量)
							service.updateNextProcessInfo(testingNumxl, (Integer) taskIdxl, nextProcessOrderxl, nextOutNumxl);
							service.updateNextChildProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
						} else {//非委外


							Map<String, Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
							Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());
							pushDownNum = pushDownNum + Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
							service.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextProcessOrderxl.toString());//更新下推数量
							Integer maxNum = service.maxOrder(taskIdxl);
							//如果下一道工序是成平入库
							if (nextProcessOrderxl == maxNum) {

								Integer testingNumxsxl = (Integer) showNextProcessMapsxl.get("TESTING_NUM");
								testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if (testingNumxsxl > (Integer) showNextProcessMapsxl.get("PLAN_NUM")) {
									return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
								}
//
//												Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//											service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl,"0");//
								String okFinishNum = showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS") == null ? "0" : showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS").toString();
								Integer finishNum = Integer.parseInt(okFinishNum);
								finishNum = finishNum + Integer.parseInt(String.valueOf(OKNumxl));
								service.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextProcessOrderxl.toString());


								completeNumxl = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString()) + OKNumxl;
								useableNumxl = Integer.parseInt(showNextProcessMapsxl.get("USEABLE_NUM").toString()) - OKNumxl;


								//最后一道工序
//											1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//											if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
//												service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);
//
//											}else{
//												service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
//											}
//											if(processTypeInfosxl!=3){
								//非委外
//												service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量   4.0
//											更新一下，useName4.0
								Map<String, Object> showNextProcessMapxlxs = service.showNextProcessInfo((Integer) taskIdxl, orderNumxl);
								Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
								Integer useNumxx = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString());//最后一道工序、
								useNumxx = complete - useNumxx;//成品入库的可用数量
								service.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextProcessOrderxl.toString());

//												ShowIPInfo ipxl = new ShowIPInfo();
//												String ipInfoxl = ipxl.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
//												String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//												String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxl = new ProcessLogModel();
//											  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//											  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//											  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//											}
//											else{//委外
//												service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//												//获取客户端IP地址
//												ShowIPInfo ipxl = new ShowIPInfo();
//												String ipInfoxl = ipxl.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
//												String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//												String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxl = new ProcessLogModel();
//											  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//											}

//											if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
//												ShowIPInfo ipxlx = new ShowIPInfo();
//												String ipInfoxlx = ipxlx.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
////												String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//												String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//												String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxlx = new ProcessLogModel();
//											  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxlx, "生产任务");
//											}


							}


						}
//								旧的更新品质的方法
//								else{
//									//根据生产任务与序号查找下一道工序
//									Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
//									Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
//									testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
//									if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
//										 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//									}
//
//										Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//
//										//				更新下道工序信息(待检测数量)
//									service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
//								}


//							Integer nextProcessOrder = orderNum+1;
////							根据生产任务与序号查找下一道工序
//							Map<String,Object> showNextProcessMap = service.showNextProcessInfo(taskId, nextProcessOrder);
//							Integer testingNum = (Integer)showNextProcessMap.get("TESTING_NUM");
//							testingNum=testingNum+OKNum;//旧有的待测试数量+上道工序的下推数量
//							if(testingNum>(Integer)showNextProcessMap.get("PLAN_NUM")){
//								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//							}
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//							service.updateNextProcessInfox(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//


//							Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//							if(processTypeInfosx!=3){


						//				更新下道工序信息(待检测数量)

						//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
////							}else{
//								Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//
//								//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfo(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//								//				更新下道工序信息(待检测数量)
////								service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
//							}
//


//						}
					} else if (orderNumxl > maxOrderxl) {//异常工序
						return Rjson.error(202, "工艺路线序号异常，请联系工艺部");
					}

//						return Rjson.success();
					// }}

				}

//			  ==============委外接收下推（合并）===============
				if (stationType.equals("3")) {

					String type = "3";

					if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null) {
						OKNum = "0";
					}

					String okNumxww = String.valueOf(OKNum);

					Map<String, Object> mapww = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情

					String outSourceInNumww = String.valueOf(mapww.get("OUTSOURCE_IN_NUM"));
//						String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
					String completeNumww = String.valueOf(mapww.get("COMPLETE_NUM"));
					String useNumww = String.valueOf(mapww.get("USEABLE_NUM"));
					String planNumww = String.valueOf(mapww.get("PLAN_NUM"));
					Integer orderNumww = (Integer) mapww.get("PROCESS_ORDER");//当前工序的排序
					String insupplerww = String.valueOf(mapww.get("ALL_OUT_SUPPLIER"));
					Integer nextOrderNumww = orderNumww + 1;
					if (String.valueOf(("OKNum")).equals("")) {
						OKNum = 0;
					}

					Integer completeNumsww = Integer.parseInt(completeNumww) + Integer.parseInt(String.valueOf((OKNum)));
					System.out.println("完成数量:" + completeNumsww + "," + planNumww);
					Integer useNumsww = Integer.parseInt(useNumww) - Integer.parseInt(String.valueOf((OKNum)));
					String completeNumxww = String.valueOf(completeNumsww);
//						if(planNum.equals(completeNumx)){//未全部完成
//							service.updateInSourceprocessInfo("11", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//							Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//							Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//							okNUM = okNUM-completeNums;
//							service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//						}else{
//							Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//							Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//							okNUM = okNUM-Integer.parseInt(OKNum.toString());
//							service.updateInSourceprocessInfo("0", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//							service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//						}
					if (planNumww.equals(completeNumxww)) {//全部完成
						service.updateInSourceprocessInfo("11", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUM = Integer.parseInt(mapxww.get("OK_NUM").toString());
//						    completeNumsww = mapxww.get("COMPLETE_NUM")==null?0:Integer.parseInt(mapxww.get("COMPLETE_NUM").toString());
//							okNUM = okNUM-completeNumsww;
						okNUM = okNUM - Integer.parseInt(OKNum.toString());//4.0
//							System.out.println("useNums:"+useNums);
						Integer applyNUM = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUM = applyNUM - Integer.parseInt(OKNum.toString());
						Integer completeNumswwx = Integer.parseInt((mapxww.get("IN_SIDE_NUM_EXAMINE").toString()));
						completeNumswwx = completeNumswwx + Integer.parseInt(OKNum.toString());
						service.updateOutsideExamineNums(okNUM.toString(), applyNUM.toString(), completeNumswwx.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
						completeww = completeww + Integer.parseInt(OKNum.toString());
						service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if (planNumsww == examineInsideww) {
							service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
					} else {
						Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUMww = Integer.parseInt(mapxww.get("OK_NUM").toString());
						okNUMww = okNUMww - Integer.parseInt(OKNum.toString());
						System.out.println("useNums:" + useNumsww);
						Integer applyNUMww = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUMww = applyNUMww - Integer.parseInt(OKNum.toString());
						service.updateInSourceprocessInfo("0", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						service.updateOutsideExamineNums(okNUMww.toString(), applyNUMww.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
						completeww = completeww + Integer.parseInt(OKNum.toString());
						service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if (planNumsww == examineInsideww) {
							service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
					}

//						更新下道工序的待检测数量   nextOrderNum


					if (String.valueOf(type).equals("3")) {
						//根据生产任务与序号查找下一道工序

						Map<String, Object> showNextProcessMapww = service.showNextProcessInfo((Integer) taskId, nextOrderNumww);
						Integer testingNumww = (Integer) showNextProcessMapww.get("TESTING_NUM");
						Map<String, Object> showNextProcessMapxww = service.showNextProcessInfo((Integer) taskId, nextOrderNumww);
						Map<String, Object> showNextProcessMapxsww = service.showTaskById(String.valueOf(taskId));
						Integer processTypeInfosww = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsww.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsww.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxww.get("PROCESS_ID"))));
						if (processTypeInfosww == 3) {
							testingNumww = testingNumww + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
							if (testingNumww > (Integer) showNextProcessMapww.get("PLAN_NUM")) {
								return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
							}

							Map<String, Object> mapdww = service.showTaskById(String.valueOf(taskId));
							Integer planNumsww = Integer.parseInt(String.valueOf(mapww.get("PLAN_NUM")));
//								Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
							Integer nextOutNumww = null;
							if (Integer.parseInt(String.valueOf(showNextProcessMapww.get("PROCESS_ORDER"))) > 1) {
								if (Integer.parseInt((String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")))) >= planNumsww) {
									nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM"))) - planNumsww;
								} else {
									nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
								}

							} else {
								nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
							}

							//				更新下道工序信息(待检测数量)
							service.updateNextProcessInfo(testingNumww, (Integer) taskId, nextOrderNumww, nextOutNumww);
						} else {

//								Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
							Map<String, Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
							Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
							pushDownNum = pushDownNum + Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
							service.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextOrderNumww.toString());//更新下推数量
							Integer maxNum = service.maxOrder(Integer.parseInt(taskId.toString()));
							//如果下一道工序是成平入库
							if (nextOrderNumww == maxNum) {
								Integer testingNumxsxl = (Integer) showNextProcessMapsww.get("TESTING_NUM");
								testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if (testingNumxsxl > (Integer) showNextProcessMapsww.get("PLAN_NUM")) {
									return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
								}

//										Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//									service.updateNextProcessInfox(testingNumxsxl, (Integer)taskId, nextOrderNumww,nextOutNumxl,"0");

								String okFinishNum = showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS") == null ? "0" : showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS").toString();
								Integer finishNum = Integer.parseInt(okFinishNum);
								finishNum = finishNum + Integer.parseInt(String.valueOf(OKNumxl));
								service.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextOrderNumww.toString());


								Integer completeNumxl = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString()) + OKNumxl;
								Integer useableNumxl = Integer.parseInt(showNextProcessMapsww.get("USEABLE_NUM").toString()) - OKNumxl;


								//最后一道工序
//									1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//									if(completeNumxl==(Integer)showNextProcessMapsww.get("PLAN_NUM")){//完成数量==计划数量
//										service.updateProcessTaskCompleteInfo(completeNumxl, 3, Integer.parseInt(taskId.toString()));
//
//									}else{
//										service.updateProcessTaskCompleteInfo(completeNumxl, null, Integer.parseInt(taskId.toString()));
//									}

//									更新一下，useName4.0
								Integer asx = null;
								asx = nextOrderNumww - 1;
								if (asx == 0) {
									asx = nextOrderNumww;
								}
								Map<String, Object> showNextProcessMapxlxs = service.showNextProcessInfo((Integer) taskId, asx);
								Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
								Integer useNumxx = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString());//最后一道工序、
								useNumxx = complete - useNumxx;//成品入库的可用数量
								service.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextOrderNumww.toString());
//									if(processTypeInfosxl!=3){
								//非委外
//										service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),Integer.parseInt(taskId.toString()));//更新当前工序的完成数量与可用数量    4.0
//										ShowIPInfo ipxl = new ShowIPInfo();
//										String ipInfoxl = ipxl.getIpAddr(request);
////										String qualityInspectorxl = request.getParameter("qualityInspector");
//										String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//										String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//									  ProcessLogModel modelxl = new ProcessLogModel();
//									  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(showNextProcessMapsww.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//									  logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsww.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//									  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//									  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//									}
//									else{//委外
//										service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//										//获取客户端IP地址
//										ShowIPInfo ipxl = new ShowIPInfo();
//										String ipInfoxl = ipxl.getIpAddr(request);
////										String qualityInspectorxl = request.getParameter("qualityInspector");
//										String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//										String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//									  ProcessLogModel modelxl = new ProcessLogModel();
//									  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//									  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//									}

//									if(completeNumxl==(Integer)showNextProcessMapsww.get("PLAN_NUM")){
//										ShowIPInfo ipxlx = new ShowIPInfo();
//										String ipInfoxlx = ipxlx.getIpAddr(request);
////										String qualityInspectorxl = request.getParameter("qualityInspector");
////										String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//										String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//										String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//									  ProcessLogModel modelxlx = new ProcessLogModel();
//									  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(showNextProcessMapsww.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//									  logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsww.get("TASK_ID")), null, logInfoxlx, "生产任务");
//									}


							}

						}

//							else{
//								//根据生产任务与序号查找下一道工序
//								Map<String,Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
//								Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
//
//								Integer testingNumxsww = (Integer)showNextProcessMapsww.get("TESTING_NUM");
//								testingNumxsww=testingNumxsww+Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
//								if(testingNumxsww>(Integer)showNextProcessMapsww.get("PLAN_NUM")){
//									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//								}
//								pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));
//
////								Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));
//
////								if(processTypeInfosx!=3){
//									Integer nextOutNumww = testingNumxsww-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//
//									//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfox(testingNumxsww, (Integer)taskId, nextOrderNumww,nextOutNumww,pushDownNum.toString());
//							}


					} else {

//

					}
//

//

//						Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));


//						if((Integer)map.get("PLAN_NUM")==newOutSourceInNum){
//							service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person);
//						}else{
//							service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person);
//						}
//
//						打印日志
					//获取客户端IP地址
					ShowIPInfo ipww = new ShowIPInfo();
					String ipInfoww = ipww.getIpAddr(request);
//								获取用户名
					String userNameww = ToolUtils.getCookieUserName(request);
//								获取任务单信息
					Map<String, Object> mapxww = service.showTaskById(String.valueOf(taskId));
					String stationNameww = service.showStationName(String.valueOf(processId));

					ProcessLogModel modelww = new ProcessLogModel();
					String logInfoww = modelww.pushDownLogInfo(userNameww, ipInfoww, String.valueOf(mapxww.get("PROJECT_NAME")), String.valueOf(mapxww.get("PROJECT_NUM")), String.valueOf(mapxww.get("SPECIFICATION_AND_MODEL")), stationNameww, okNumxww, person, "委外接收审核", insupplerww, String.valueOf(mapxww.get("STATION_NUM")));
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
					System.out.println("log:" + logInfoww);
					logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfoww, "委外接收审核");


				}
			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	更新品检审核内容（新版）
	@RequestMapping(value = "/updateQualityApprovalTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外任务质检审批", notes = "委外任务质检审批")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson updateQualityApprovalTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			System.out.println("审核员:" + person);
//		System.out.println(info);
//		JSONArray jsonArray=JSONArray.fromObject(info);
//			for (int i = 0; i < jsonArray.size(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				Object taskId = jsonObject.get("taskId");
//				Object processId = jsonObject.get("processId");
//				Object NGNum = jsonObject.get("NGNum");
//				Object OKNum = jsonObject.get("OKNum");
//				Object id = jsonObject.get("id");
//				Object splitFlags = jsonObject.get("splitFlags");
//		String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String OKNum = request.getParameter("okNum");
			String NGNum = request.getParameter("ngNum");
//		String supplier = request.getParameter("supplier");
			String id = request.getParameter("id");
			String splitFlags = "4";

			Map<String, Object> showTaskByIdxx = service.showTaskById(taskId);
//	String projectNumxl = jsonObject.get("projectNum").toString();
//	String specificationModelsxl = jsonObject.get("specificationModel").toString();
//	String processNamexl = jsonObject.get("processName").toString();
//	String stationNumxl = jsonObject.get("stationNum").toString();
//	String meterialCodexl = jsonObject.get("meterialCode").toString();
			String userNamexl = ToolUtils.getCookieUserName(request);
			String projectNumxl = showTaskByIdxx.get("PROJECT_NUM").toString();
			String specificationModelsxl = showTaskByIdxx.get("SPECIFICATION_AND_MODEL").toString();
			String processNamexl = request.getParameter("processId");
			String stationNumxl = showTaskByIdxx.get("STATION_NUM").toString();
			String meterialCodexl = showTaskByIdxx.get("MATERIAL_CODE").toString();


			Integer okNum1 = Integer.parseInt(OKNum.toString());
			Integer ngNum1 = Integer.parseInt(NGNum.toString());
			Integer OKNumxl = okNum1;

			//循环遍历评审状态(2审核完成状态，1进入审核状态，0未操作状态)
//				service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), String.valueOf(NGNum),"2","","","","","");
			Map<String, Object> map = service.showAllQualityApprovasList("1", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
			Integer userNum = null;
			if (map == null || map.size() == 0) {
				map = service.showAllQualityApprovasList("0", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				if (map == null || map.size() == 0) {
					map = service.showAllQualityApprovasList("6", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					if (map == null || map.size() == 0) {
						map = service.showAllQualityApprovasList("7", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
						if (map == null || map.size() == 0) {
							map = service.showAllQualityApprovasList("8", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
							if (map == null || map.size() == 0) {
								map = service.showAllQualityApprovasList("9", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("10", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								}
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("2", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								}
								if (map == null || map.size() == 0) {
									map = service.showAllQualityApprovasList("11", String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
								}
							}
						}
					}
				}
			}
			if (String.valueOf(map.get("USEABLE_NUM")).equals("") || String.valueOf(map.get("USEABLE_NUM")) == null || String.valueOf(map.get("USEABLE_NUM")).equals("null")) {
				userNum = 0;
			} else {
				userNum = (Integer) map.get("USEABLE_NUM");//旧的可用数量+新的OK数量
			}

//				ok ng 待检测
			Integer ngNum = (Integer) map.get("NG_NUM");
			System.out.println(ngNum);
//				Integer testings  =  Integer.parseInt(String.valueOf(map.get("TESTING_NUM")));
			Integer testing = null;


//				testing = testing + testings;


			String reamrks = (String) map.get("REMARKS");
			String allReamrks = (String) map.get("ALL_REMARKS");
			Integer planNum = (Integer) map.get("PLAN_NUM");
			Integer comleteNum = (Integer) map.get("COMPLETE_NUM");
			Integer outsourceNum = (Integer) map.get("OUTSOURCE_OUT_NUM");
			String stationType = (String) map.get("STATION_TYPES");
			if (allReamrks != null) {
				if (allReamrks.equals("")) {
					allReamrks = reamrks;
					reamrks = "";
				} else {
					allReamrks = allReamrks + "--分割--" + reamrks;
					reamrks = "";
				}
			} else {
				allReamrks = reamrks;
				reamrks = "";
			}
			reamrks = "";

			if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null || String.valueOf(OKNum).equals("null")) {
				OKNum = "0";
			}
			if (String.valueOf(NGNum).equals("") || String.valueOf(NGNum) == null || String.valueOf(NGNum).equals("null")) {
				NGNum = "0";
			}
			String temsNum = null;
			String temsNums = null;

			if (!stationType.equals("3")) {
				temsNum = String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS_APPROVAL"));
			} else {
				temsNum = String.valueOf(map.get("INSOURCE_IN_TEMS_APPROVAL"));
				temsNums = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
			}

			if (temsNum.equals("") || temsNum.equals("null") || temsNum == null) {
				temsNum = "0";
			}
			Integer userNums = null;
			Integer newUseNums = userNum + Integer.parseInt((String.valueOf(OKNum)));
//				userNums = userNum+Integer.parseInt((String.valueOf(OKNum)))-Integer.parseInt(temsNums);
			userNum = userNum + Integer.parseInt((String.valueOf(OKNum))) - Integer.parseInt(temsNum);//temsNum

			ngNum = ngNum + Integer.parseInt((String.valueOf(NGNum)));


//					if(stationType.equals("3")){
//						outsourceNum=outsourceNum+Integer.parseInt((String.valueOf(OKNum)))+Integer.parseInt((String.valueOf(NGNum)));//扣除委外
//					}
			Integer splitFlagsx = Integer.parseInt(String.valueOf(splitFlags));
//					if(splitFlagsx==4){
////						获取子分支testNum  获取主分支的testNum 主分支-子分支  更新主分支数据
////						Map<String,Object> mapDatas = service.showTestNumData(String.valueOf(id));
////						Integer testNumData = Integer.parseInt(String.valueOf(mapDatas.get("TESTING_NUM")));
//						Map<String,Object> dataMaps= service.showAllMainBranchInfo(String.valueOf(taskId), String.valueOf(processId));
//						String idData = String.valueOf(dataMaps.get("ID"));
//						Integer testNumsData = Integer.parseInt(String.valueOf(dataMaps.get("TESTING_NUM")));
//						testNumsData = testNumsData -Integer.parseInt(String.valueOf(OKNum))-Integer.parseInt((String.valueOf(NGNum)));
//						service.updateMainTestNumData(String.valueOf(testNumsData), idData);
//					}
			if (stationType.equals("3")) {//委外更新
				System.out.println(String.valueOf(OKNum));
				System.out.println(ngNum);


				testing = Integer.parseInt(String.valueOf(map.get("TESTING_NUM"))) - Integer.parseInt(String.valueOf(OKNum)) - Integer.parseInt((String.valueOf(NGNum)));//旧的待品检数量+新的待品检数量
			} else {
				testing = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));//旧的待品检数量+新的待品检数量
			}

			Integer ngNumx = service.showProcessById(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
			ngNumx = ngNumx + Integer.parseInt((String.valueOf(NGNum)));


			Integer outsideNum = null;
//				service.updateQualityApprovalData(String.valueOf(testing), String.valueOf(userNum), String.valueOf(ngNum), String.valueOf(taskId), String.valueOf(processId),person,reamrks,allReamrks, String.valueOf(outsourceNum)); 更新数据
			if (stationType.equals("3")) {//委外更新


				service.updateQualityApprovalDatas(String.valueOf(testing), String.valueOf(ngNum), String.valueOf(userNum), String.valueOf(taskId), String.valueOf(processId), String.valueOf(outsourceNum), String.valueOf(newUseNums), String.valueOf(id));
//						委外任务质检审批
				Map<String, Object> qualityMap = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				outsideNum = Integer.parseInt(qualityMap.get("OUT_SIDE_NUM").toString());
				outsideNum = outsideNum - okNum1 - ngNum1;
				Map<String, Object> mapxyx = service.showOutsideTaskById(taskId, processId, id);
//						Integer okNumExmine = Integer.parseInt(mapxyx.get("OK_NUM_EXAMINE").toString());
//						okNumExmine=okNumExmine+Integer.parseInt(OKNum);
//
//						service.updateOutsideExamineNum(userNum.toString(), ngNum.toString(), outsideNum.toString(),taskId.toString(), processId.toString(), id.toString(),okNumExmine.toString());
				Integer okNumExmine = Integer.parseInt(mapxyx.get("OK_NUM_EXAMINE").toString());
				Integer ngNumExmine = Integer.parseInt(mapxyx.get("NG_NUM_EXAMINE").toString());
				okNumExmine = okNumExmine + Integer.parseInt(OKNum.toString());
				ngNumExmine = ngNumExmine + Integer.parseInt(NGNum.toString());
				System.out.println(mapxyx.get("OK_NUM_EXAMINE").toString() + "123456");
				System.out.println(mapxyx.get("REWORK_EXAMINE_NUM").toString());
				System.out.println(mapxyx.get("IN_SIDE_NUM_EXAMINE").toString());
				userNums = okNum1 + Integer.parseInt(mapxyx.get("OK_NUM_EXAMINE").toString()) + Integer.parseInt(mapxyx.get("REWORK_EXAMINE_NUM").toString()) - Integer.parseInt(mapxyx.get("IN_SIDE_NUM_EXAMINE").toString());

				service.updateOutsideExamineNum(userNums.toString(), ngNum.toString(), outsideNum.toString(), taskId.toString(), processId.toString(), id.toString(), okNumExmine.toString(), ngNumExmine.toString());
				if (outsideNum == 0) {
					service.updateOutsideStatusNum(taskId, processId, id, "4");
				}
//						if(splitFlagsx==1 || splitFlagsx==2){
				Map<String, Object> mapxx = service.showAllMainBranchInfo(taskId, processId);
				Integer testNumxx = Integer.parseInt((mapxx.get("TESTING_NUM").toString()));
				Integer ngNumxx = Integer.parseInt((mapxx.get("NG_NUM").toString()));
				Integer useableNumxx = Integer.parseInt((mapxx.get("USEABLE_NUM").toString()));
				ngNumxx = ngNumxx + ngNum1;
				useableNumxx = useableNumxx + okNum1;
				testNumxx = testNumxx - Integer.parseInt(String.valueOf(OKNum)) - Integer.parseInt((String.valueOf(NGNum)));
				service.updateTestNumData(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNumxx), useableNumxx.toString(), ngNumxx.toString());


//						}


			} else {//非委外更新
				service.updateQualityApprovalDatasx(String.valueOf(testing), String.valueOf(ngNum), String.valueOf(userNum), String.valueOf(taskId), String.valueOf(processId), String.valueOf(outsourceNum), String.valueOf(newUseNums), String.valueOf(id));
			}

//				service.updateNGNumTem( String.valueOf(taskId), String.valueOf(processId));
			Integer oldNGNum = service.showNGNum(String.valueOf(taskId));
			oldNGNum = oldNGNum + Integer.parseInt((String.valueOf(NGNum)));
			service.updateTaskNGNum(String.valueOf(taskId), oldNGNum);


			if (!planNum.equals(comleteNum)) {
				if (stationType.equals("3")) {
					service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), "", "0", "", "", "", "", "", "1", String.valueOf(ngNumx), String.valueOf(id));
				} else {
					service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), "", "0", "", "", "", "", "", "0", String.valueOf(ngNumx), String.valueOf(id));
				}
			}


//				打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//				获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKNum), String.valueOf(NGNum), "", person, "审核", String.valueOf(mapx.get("STATION_NUM")));
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "品检审核");


//			  =================普通下推逻辑代码部分================
			Map<String, Object> mapxl = null;
			if (!stationType.equals("3")) {
				mapxl = service.showTaskList(projectNumxl, specificationModelsxl, processNamexl, meterialCodexl, stationNumxl);
			}
			if ((!stationType.equals("3")) && (Integer.parseInt(mapxl.get("USEABLE_NUM").toString()) > 0)) {

				String flagsxl = mapxl.get("STATUS_FLAGS").toString();
				if (flagsxl.equals("0")) {
					return Rjson.error(202, "生产任务为开始状态无法下推");
				} else if (flagsxl.equals("2")) {
					return Rjson.error(202, "生产任务为暂停状态无法下推");
				} else if (flagsxl.equals("3")) {
					return Rjson.error(202, "生产任务为完成状态无法下推");
				}

				Integer useableNumxl = (Integer) mapxl.get("USEABLE_NUM");//旧有可用数量

				if (useableNumxl < OKNumxl) {
					return Rjson.error(202, "下推数量不能超过可用数量");
				}
				Integer completeNumxl = (Integer) mapxl.get("COMPLETE_NUM");//旧有完成数量
				completeNumxl = completeNumxl + OKNumxl;//现有完成数量
				useableNumxl = useableNumxl - OKNumxl;//现有可用数量

//						System.out.println("abc:"+(Integer)map.get("ORDER_NUM"));
//						System.out.println("abc:"+Integer.parseInt(map.get("ORDER_NUM")));
//						下道工序的待检测数量==上道工序的下推数量
				Integer orderNumxl = null;

				orderNumxl = (Integer) mapxl.get("ORDER_NUM");

				Integer taskIdxl = (Integer) mapxl.get("TASK_ID");
//						求出当前工艺路线最大的ID
				Integer maxOrderxl = service.maxOrder(taskIdxl);

//						1.判断是否为委外做不同的更新2.委外品检减委外发出数量
				Integer processTypeInfosxl = service.showNowProcesstypeInfo(projectNumxl, specificationModelsxl, (Integer) mapxl.get("PROCESS_ID"));
				if (orderNumxl == maxOrderxl) {
//							if(1==0){
					//最后一道工序
//							1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
					if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {//完成数量==计划数量
						service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);

					} else {
						service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
					}
					if (processTypeInfosxl != 3) {//非委外
						service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//							  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//							  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


					} else {//委外
						service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
						//获取客户端IP地址
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");

					}

					if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {
						ShowIPInfo ipxl = new ShowIPInfo();
						String ipInfoxl = ipxl.getIpAddr(request);
//								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
						String qualityInspectorxl = ToolUtils.getCookieUserName(request);
						String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
						ProcessLogModel modelxl = new ProcessLogModel();
						String logInfoxl = modelxl.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null, null, projectNumxl, specificationModelsxl, null, null, null, stationNumxl);
						logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxl, "生产任务");
					}
//							}

				} else if (orderNumxl < maxOrderxl) {//中间工序或者第一道工序
					if (processTypeInfosxl != 3) {//非委外
						service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
//								//获取客户端IP地址
//								ShowIPInfo ipxl = new ShowIPInfo();
//								String ipInfoxl = ipxl.getIpAddr(request);
////								String qualityInspectorxl = request.getParameter("qualityInspector");
//								String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//								String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//							  ProcessLogModel modelxl = new ProcessLogModel();
//							  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"正常","",stationNumxl);
//							  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

					} else {//委外
						service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
						//获取客户端IP地址
						ShowIPInfo ipxl = new ShowIPInfo();
						String ipInfoxl = ipxl.getIpAddr(request);
//								String qualityInspectorxl = request.getParameter("qualityInspector");
						String qualityInspectorxl = ToolUtils.getCookieUserName(request);
						String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
						ProcessLogModel modelxl = new ProcessLogModel();
						String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "委外接收", "", stationNumxl);
						logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "委外接收");
					}


					Integer nextProcessOrderxl = orderNumxl + 1;


					//根据生产任务与序号查找下一道工序
//							获取下道工序主分支数据   获取task数据   判断工序类型

					Map<String, Object> showNextProcessMapxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
					Integer testingNumxl = (Integer) showNextProcessMapxl.get("TESTING_NUM");
					Map<String, Object> showNextProcessMapxxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
					Map<String, Object> showNextProcessMapxsxl = service.showTaskById(String.valueOf(taskIdxl));
					Integer processTypeInfoxl = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsxl.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsxl.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxxl.get("PROCESS_ID"))));
					if (processTypeInfoxl == 3) {//委外
						testingNumxl = testingNumxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
						if (testingNumxl > (Integer) showNextProcessMapxl.get("PLAN_NUM")) {
							return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
						}

						Map<String, Object> mapdxl = service.showTaskById(String.valueOf(taskIdxl));
						Integer planNumsxl = Integer.parseInt(String.valueOf(mapxl.get("PLAN_NUM")));
//									Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
						Integer nextOutNumxl = null;
//									初始品检临时数量都为计划数量，下推需要变成真实数量就需要减去计划数量（TESTING_NUM_TEM），第一道不用减，下推需要减去
						if (Integer.parseInt(String.valueOf(showNextProcessMapxl.get("PROCESS_ORDER"))) > 1) {
							if (Integer.parseInt((String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")))) >= planNumsxl) {
								nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM"))) - planNumsxl;
							} else {
								nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
							}

						} else {
							nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
						}

						//				更新下道工序信息(待检测数量)
						service.updateNextProcessInfo(testingNumxl, (Integer) taskIdxl, nextProcessOrderxl, nextOutNumxl);
						service.updateNextChildProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
					} else {//非委外


						Map<String, Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
						Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());
						pushDownNum = pushDownNum + Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
						service.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextProcessOrderxl.toString());//更新下推数量
						Integer maxNum = service.maxOrder(taskIdxl);
						//如果下一道工序是成平入库
						if (nextProcessOrderxl == maxNum) {

							Integer testingNumxsxl = (Integer) showNextProcessMapsxl.get("TESTING_NUM");
							testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
							if (testingNumxsxl > (Integer) showNextProcessMapsxl.get("PLAN_NUM")) {
								return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
							}
//
//												Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//											service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl,"0");//
							String okFinishNum = showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS") == null ? "0" : showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS").toString();
							Integer finishNum = Integer.parseInt(okFinishNum);
							finishNum = finishNum + Integer.parseInt(String.valueOf(OKNumxl));
							service.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextProcessOrderxl.toString());


							completeNumxl = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString()) + OKNumxl;
							useableNumxl = Integer.parseInt(showNextProcessMapsxl.get("USEABLE_NUM").toString()) - OKNumxl;


							//最后一道工序
//											1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//											if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
//												service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);
//
//											}else{
//												service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
//											}
//											if(processTypeInfosxl!=3){
							//非委外
//												service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量   4.0
//											更新一下，useName4.0
							Map<String, Object> showNextProcessMapxlxs = service.showNextProcessInfo((Integer) taskIdxl, orderNumxl);
							Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
							Integer useNumxx = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString());//最后一道工序、
							useNumxx = complete - useNumxx;//成品入库的可用数量
							service.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextProcessOrderxl.toString());

//												ShowIPInfo ipxl = new ShowIPInfo();
//												String ipInfoxl = ipxl.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
//												String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//												String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxl = new ProcessLogModel();
//											  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//											  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//											  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//											}
//											else{//委外
//												service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//												//获取客户端IP地址
//												ShowIPInfo ipxl = new ShowIPInfo();
//												String ipInfoxl = ipxl.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
//												String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//												String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxl = new ProcessLogModel();
//											  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//											}

//											if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
//												ShowIPInfo ipxlx = new ShowIPInfo();
//												String ipInfoxlx = ipxlx.getIpAddr(request);
////												String qualityInspectorxl = request.getParameter("qualityInspector");
////												String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//												String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//												String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//											  ProcessLogModel modelxlx = new ProcessLogModel();
//											  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//											  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxlx, "生产任务");
//											}


						}


					}
//								旧的更新品质的方法
//								else{
//									//根据生产任务与序号查找下一道工序
//									Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
//									Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
//									testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
//									if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
//										 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//									}
//
//										Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//
//										//				更新下道工序信息(待检测数量)
//									service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
//								}


//							Integer nextProcessOrder = orderNum+1;
////							根据生产任务与序号查找下一道工序
//							Map<String,Object> showNextProcessMap = service.showNextProcessInfo(taskId, nextProcessOrder);
//							Integer testingNum = (Integer)showNextProcessMap.get("TESTING_NUM");
//							testingNum=testingNum+OKNum;//旧有的待测试数量+上道工序的下推数量
//							if(testingNum>(Integer)showNextProcessMap.get("PLAN_NUM")){
//								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//							}
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//							service.updateNextProcessInfox(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//


//							Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//							if(processTypeInfosx!=3){


					//				更新下道工序信息(待检测数量)

					//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
////							}else{
//								Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//
//								//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfo(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//								//				更新下道工序信息(待检测数量)
////								service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
//							}
//


//						}
				} else if (orderNumxl > maxOrderxl) {//异常工序
					return Rjson.error(202, "工艺路线序号异常，请联系工艺部");
				}

//						return Rjson.success();
				// }}

			}


//			  =========委外接收代码块=========

			if (stationType.equals("3")) {

				String type = "3";

				if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null) {
					OKNum = "0";
				}

				String okNumxww = String.valueOf(OKNum);

				Map<String, Object> mapww = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情

				String outSourceInNumww = String.valueOf(mapww.get("OUTSOURCE_IN_NUM"));
//					String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
				String completeNumww = String.valueOf(mapww.get("COMPLETE_NUM"));
				String useNumww = String.valueOf(mapww.get("USEABLE_NUM"));
				String planNumww = String.valueOf(mapww.get("PLAN_NUM"));
				Integer orderNumww = (Integer) mapww.get("PROCESS_ORDER");//当前工序的排序
				String insupplerww = String.valueOf(mapww.get("ALL_OUT_SUPPLIER"));
				Integer nextOrderNumww = orderNumww + 1;
				if (String.valueOf(("OKNum")).equals("")) {
					OKNum = "0";
				}

				Integer completeNumsww = Integer.parseInt(completeNumww) + Integer.parseInt(String.valueOf((OKNum)));
				System.out.println("完成数量:" + completeNumsww + "," + planNumww);
				Integer useNumsww = Integer.parseInt(useNumww) - Integer.parseInt(String.valueOf((OKNum)));
				String completeNumxww = String.valueOf(completeNumsww);
//					if(planNum.equals(completeNumx)){//未全部完成
//						service.updateInSourceprocessInfo("11", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//						Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//						Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//						okNUM = okNUM-completeNums;
//						service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//					}else{
//						Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//						Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//						okNUM = okNUM-Integer.parseInt(OKNum.toString());
//						service.updateInSourceprocessInfo("0", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//						service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//					}
				if (planNumww.equals(completeNumxww)) {//未全部完成
					service.updateInSourceprocessInfo("11", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer okNUM = Integer.parseInt(mapxww.get("OK_NUM").toString());
//						okNUM = okNUM-completeNumsww;
//						System.out.println("useNums:"+useNums);
					okNUM = okNUM - Integer.parseInt(OKNum.toString());//4.0
					Integer applyNUM = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
					applyNUM = applyNUM - Integer.parseInt(OKNum.toString());
					Integer completeNumswwx = Integer.parseInt((mapxww.get("IN_SIDE_NUM_EXAMINE").toString()));
					completeNumswwx = completeNumswwx + Integer.parseInt(OKNum.toString());
					service.updateOutsideExamineNums(okNUM.toString(), applyNUM.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
					Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
					Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
					Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
					Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
					completeww = completeww + Integer.parseInt(OKNum.toString());
					service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
					if (planNumsww == examineInsideww) {
						service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
					}
				} else {
					Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer okNUMww = Integer.parseInt(mapxww.get("OK_NUM").toString());
					okNUMww = okNUMww - Integer.parseInt(OKNum.toString());
					System.out.println("useNums:" + useNumsww);
					Integer applyNUMww = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
					applyNUMww = applyNUMww - Integer.parseInt(OKNum.toString());
					service.updateInSourceprocessInfo("0", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					service.updateOutsideExamineNums(okNUMww.toString(), applyNUMww.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
					Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
					Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
					Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
					Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
					completeww = completeww + Integer.parseInt(OKNum.toString());
					service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
					if (planNumsww == examineInsideww) {
						service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
					}
				}

//					更新下道工序的待检测数量   nextOrderNum


				if (String.valueOf(type).equals("3")) {
					//根据生产任务与序号查找下一道工序

					Map<String, Object> showNextProcessMapww = service.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNumww);
					Integer testingNumww = (Integer) showNextProcessMapww.get("TESTING_NUM");
					Map<String, Object> showNextProcessMapxww = service.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNumww);
					Map<String, Object> showNextProcessMapxsww = service.showTaskById(String.valueOf(taskId));
					Integer processTypeInfosww = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsww.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsww.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxww.get("PROCESS_ID"))));
					if (processTypeInfosww == 3) {
						testingNumww = testingNumww + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
						if (testingNumww > (Integer) showNextProcessMapww.get("PLAN_NUM")) {
							return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
						}

						Map<String, Object> mapdww = service.showTaskById(String.valueOf(taskId));
						Integer planNumsww = Integer.parseInt(String.valueOf(mapww.get("PLAN_NUM")));
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
						Integer nextOutNumww = null;
						if (Integer.parseInt(String.valueOf(showNextProcessMapww.get("PROCESS_ORDER"))) > 1) {
							if (Integer.parseInt((String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")))) >= planNumsww) {
								nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM"))) - planNumsww;
							} else {
								nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
							}

						} else {
							nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
						}

						//				更新下道工序信息(待检测数量)
						service.updateNextProcessInfo(testingNumww, Integer.parseInt(taskId), nextOrderNumww, nextOutNumww);
					} else {

//							Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
						Map<String, Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
						Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
						pushDownNum = pushDownNum + Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
						service.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextOrderNumww.toString());//更新下推数量
						Integer maxNum = service.maxOrder(Integer.parseInt(taskId.toString()));
						//如果下一道工序是成平入库
						if (nextOrderNumww == maxNum) {
							Integer testingNumxsxl = (Integer) showNextProcessMapsww.get("TESTING_NUM");
							testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
							if (testingNumxsxl > (Integer) showNextProcessMapsww.get("PLAN_NUM")) {
								return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
							}

//									Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//								service.updateNextProcessInfox(testingNumxsxl, (Integer)taskId, nextOrderNumww,nextOutNumxl,"0");

							String okFinishNum = showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS") == null ? "0" : showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS").toString();
							Integer finishNum = Integer.parseInt(okFinishNum);
							finishNum = finishNum + Integer.parseInt(String.valueOf(OKNumxl));
							service.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextOrderNumww.toString());


							Integer completeNumxl = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString()) + OKNumxl;
							Integer useableNumxl = Integer.parseInt(showNextProcessMapsww.get("USEABLE_NUM").toString()) - OKNumxl;


							//最后一道工序
//								1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//								if(completeNumxl==(Integer)showNextProcessMapsww.get("PLAN_NUM")){//完成数量==计划数量
//									service.updateProcessTaskCompleteInfo(completeNumxl, 3, Integer.parseInt(taskId.toString()));
//
//								}else{
//									service.updateProcessTaskCompleteInfo(completeNumxl, null, Integer.parseInt(taskId.toString()));
//								}

//								更新一下，useName4.0
							Integer asx = null;
							asx = nextOrderNumww - 1;
							if (asx == 0) {
								asx = nextOrderNumww;
							}
							Map<String, Object> showNextProcessMapxlxs = service.showNextProcessInfo(Integer.parseInt(taskId), asx);
							Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
							Integer useNumxx = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString());//最后一道工序、
							useNumxx = complete - useNumxx;//成品入库的可用数量
							service.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextOrderNumww.toString());
//								if(processTypeInfosxl!=3){
							//非委外
//									service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),Integer.parseInt(taskId.toString()));//更新当前工序的完成数量与可用数量    4.0
//									ShowIPInfo ipxl = new ShowIPInfo();
//									String ipInfoxl = ipxl.getIpAddr(request);
////									String qualityInspectorxl = request.getParameter("qualityInspector");
//									String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//									String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//								  ProcessLogModel modelxl = new ProcessLogModel();
//								  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(showNextProcessMapsww.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//								  logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsww.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//								  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//								  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//								}
//								else{//委外
//									service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//									//获取客户端IP地址
//									ShowIPInfo ipxl = new ShowIPInfo();
//									String ipInfoxl = ipxl.getIpAddr(request);
////									String qualityInspectorxl = request.getParameter("qualityInspector");
//									String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//									String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//								  ProcessLogModel modelxl = new ProcessLogModel();
//								  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//								  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//								}

//								if(completeNumxl==(Integer)showNextProcessMapsww.get("PLAN_NUM")){
//									ShowIPInfo ipxlx = new ShowIPInfo();
//									String ipInfoxlx = ipxlx.getIpAddr(request);
////									String qualityInspectorxl = request.getParameter("qualityInspector");
////									String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//									String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//									String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//								  ProcessLogModel modelxlx = new ProcessLogModel();
//								  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(showNextProcessMapsww.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//								  logInfos.addProcessLogInfo(String.valueOf(showNextProcessMapsww.get("TASK_ID")), null, logInfoxlx, "生产任务");
//								}


						}

					}


				} else {

//

				}
//

//

//					Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));


//					if((Integer)map.get("PLAN_NUM")==newOutSourceInNum){
//						service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person);
//					}else{
//						service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person);
//					}
//
//					打印日志
				//获取客户端IP地址
				ShowIPInfo ipww = new ShowIPInfo();
				String ipInfoww = ipww.getIpAddr(request);
//							获取用户名
				String userNameww = ToolUtils.getCookieUserName(request);
//							获取任务单信息
				Map<String, Object> mapxww = service.showTaskById(String.valueOf(taskId));
				String stationNameww = service.showStationName(String.valueOf(processId));

				ProcessLogModel modelww = new ProcessLogModel();
				String logInfoww = modelww.pushDownLogInfo(userNameww, ipInfoww, String.valueOf(mapxww.get("PROJECT_NAME")), String.valueOf(mapxww.get("PROJECT_NUM")), String.valueOf(mapxww.get("SPECIFICATION_AND_MODEL")), stationNameww, okNumxww, person, "委外接收审核", insupplerww, String.valueOf(mapxww.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfoww);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfoww, "委外接收审核");
			}


//			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	撤销品检审批(新版)
	@RequestMapping(value = "/revokeApproval", method = RequestMethod.POST)
	@ApiOperation(value = "撤销审批", notes = "撤销审批")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "taskId", value = "任务ID", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson revokeApproval(HttpServletRequest request) throws ServicesException {
		try {
			String processId = request.getParameter("processId");
			String taskId = request.getParameter("taskId");
			String okNum = request.getParameter("okNum");
			String ngNum = request.getParameter("ngNum");
			String type = request.getParameter("type");
			String id = request.getParameter("id");
//			Integer outRouce=null;
			Map<String, Object> map = service.showAllQualityApprovasList("1", String.valueOf(taskId), String.valueOf(processId), id);
			if (map == null || map.size() == 0) {
				map = service.showAllQualityApprovasList("0", String.valueOf(taskId), String.valueOf(processId), id);
			}

			Integer testNumTem = null;
			Integer ousideExamine = null;
			Integer ousidenNum = null;
			if (!type.equals("3")) {
				testNumTem = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM"))) + Integer.parseInt(okNum) + Integer.parseInt(ngNum);
			} else {
				testNumTem = Integer.parseInt(String.valueOf(map.get("OK_INSOURCE_TEMS"))) + Integer.parseInt(okNum) + Integer.parseInt(ngNum);
			}


			if (!type.equals("3")) {
				service.updateQualityByFlags(String.valueOf(testNumTem), taskId, processId, id);

			} else {
				service.updateQualityByFlagsx(String.valueOf(testNumTem), taskId, processId, id);
				service.revockOutsideQuqalityNumTask(testNumTem.toString(), taskId, processId, id);
				Map<String, Object> outsideMap = service.showOutsideTaskById(taskId, processId, id);
				ousideExamine = Integer.parseInt(outsideMap.get("OUT_SIDE_NUM_EXAMINE").toString());
//				ousidenNum = Integer.parseInt(outsideMap.get("OUT_SIDE_NUM").toString());
				ousidenNum = testNumTem;
				if (ousideExamine == ousidenNum) {//更新状态为2
					service.updateOutsideStatusNum(taskId, processId, id, "2");
				} else if (ousideExamine > ousidenNum) {//委外任务状态20
					service.updateOutsideStatusNum(taskId, processId, id, "20");
				}
			}


//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(ngNum), "", "", "品检撤销", String.valueOf(mapx.get("STATION_NUM")));

//		  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "品检撤销");


			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	更新成品入库批量数据
	@RequestMapping(value = "/finishProductApproval", method = RequestMethod.POST)
	@ApiOperation(value = "获取批量数据进入审批环节", notes = "获取批量数据进入审批环节")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson finishProductApproval(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
//		System.out.println(info);
			JSONArray jsonArray = JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object OKNum = jsonObject.get("OKNum");
				Object remarks = jsonObject.get("remarks");
//				更新临时可入库数量
				Map<String, Object> map = null;

				map = service.showAllQualityApprovasListx("6", String.valueOf(taskId), String.valueOf(processId));
				if (map == null || map.size() == 0) {
					map = service.showAllQualityApprovasListx("0", String.valueOf(taskId), String.valueOf(processId));
					if (map == null || map.size() == 0) {
						map = service.showAllQualityApprovasListx("1", String.valueOf(taskId), String.valueOf(processId));

					}
				}

				System.out.println("map:" + map);


				System.out.println("输出值：" + Integer.parseInt(String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS"))));
				Integer okOutsourceTem = Integer.parseInt(String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS")));

				okOutsourceTem = okOutsourceTem - Integer.parseInt(String.valueOf(OKNum));

				String finishNum = String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS_APPROVAL"));
				if (finishNum.equals("") || finishNum.equals("null") || finishNum == null) {
					finishNum = "0";
				}
				Integer okFinishProductionTems = Integer.parseInt(finishNum);//旧的完成数量
				okFinishProductionTems = okFinishProductionTems + Integer.parseInt(String.valueOf(OKNum));


				service.finishProductApproval(String.valueOf(OKNum), String.valueOf(taskId), String.valueOf(processId), person, String.valueOf(remarks), okOutsourceTem, okFinishProductionTems);//更新审批状态

//				Object remarks = jsonObject.get("remarks");
//				Object outsourceTem = jsonObject.get("outsourceTem");
//
//				if(String.valueOf(outsourceTem).equals("")||String.valueOf(outsourceTem)==null){
//					outsourceTem=0;
//				}
//				service.updateQualityApprovalList(String.valueOf(taskId), String.valueOf(processId), String.valueOf(NGNum),"1",String.valueOf(remarks),person,String.valueOf(outsourceTem));

//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKNum), person, "成品入库申请", "", String.valueOf(mapx.get("STATION_NUM")));
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "成品入库申请");

			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//入库展示
	@RequestMapping(value = "/showAllFinishProduct", method = RequestMethod.POST)
	@ApiOperation(value = "入库展示", notes = "入库展示")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllFinishProduct(HttpServletRequest request) throws ServicesException {

		try {

//				获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String IP = ip.getIpAddr(request);
			System.out.println("客户端IP:" + IP);

			String flags = request.getParameter("flags");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModel");
			List<Map<String, Object>> list = service.showAllFinishProduct(flags, stationNum, materiCode, projectNum, specificationModel);//更新状态6
			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//入库展示
	@RequestMapping(value = "/showAllFinishProductx", method = RequestMethod.POST)
	@ApiOperation(value = "入库展示", notes = "入库展示")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllFinishProductx(HttpServletRequest request) throws ServicesException {

		try {

//				获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String IP = ip.getIpAddr(request);
			System.out.println("客户端IP:" + IP);

			String flags = request.getParameter("flags");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModel");
			List<Map<String, Object>> list = service.showAllFinishProductx(flags, stationNum, materiCode, projectNum, specificationModel);//更新状态6
			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateAllFinishProduct", method = RequestMethod.POST)
	@ApiOperation(value = "入库数据更新", notes = "入库数据更新")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateAllFinishProduct(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object OKNum = jsonObject.get("OKNum");
				Object id = jsonObject.get("id");
//							Object remarks = jsonObject.get("remarks");
//							service.finishProductApproval(String.valueOf(OKNum), String.valueOf(taskId), String.valueOf(processId),person,String.valueOf(remarks));
				Map<String, Object> showTaskInfo = service.showProcessTaskInfos((Integer) taskId);//查询任务单
				Map<String, Object> showDetailsInfo = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
				Integer complete = (Integer) showTaskInfo.get("COMPLETE_NUM");
				Integer planNum = (Integer) showTaskInfo.get("PLAN_NUM");
				Integer DetailsCompleteNum = (Integer) showDetailsInfo.get("COMPLETE_NUM");
				Integer DetailsUseNum = (Integer) showDetailsInfo.get("USEABLE_NUM");


				if (String.valueOf(OKNum).equals("") || OKNum == null) {
					OKNum = "0";
				}
				System.out.println(DetailsCompleteNum);
				DetailsCompleteNum = DetailsCompleteNum + Integer.parseInt(String.valueOf(OKNum));//新的工序完成数量
				DetailsUseNum = DetailsUseNum - Integer.parseInt(String.valueOf(OKNum));//可用数量
				if (DetailsCompleteNum == planNum) {
					service.updateDetailsInfo(String.valueOf(DetailsCompleteNum), String.valueOf(DetailsUseNum), String.valueOf(taskId), String.valueOf(processId), "7");//全部入库
				} else {
					service.updateDetailsInfo(String.valueOf(DetailsCompleteNum), String.valueOf(DetailsUseNum), String.valueOf(taskId), String.valueOf(processId), "0");//未全部入库（可以品检）
				}

				complete = complete + Integer.parseInt(String.valueOf(OKNum));//新的完成数量2计划==完成  林外考虑

//							打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//							获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();


				if (complete == planNum) {//更新状态
					service.updateNewTaskInfos(String.valueOf(complete), "3", String.valueOf(taskId));
					String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), null, null, null, String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), "生产", "完成", null, String.valueOf(mapx.get("STATION_NUM")));
					logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");

				} else {
					service.updateNewTaskInfos(String.valueOf(complete), "", String.valueOf(taskId));
				}


//							打印日志

				//获取客户端IP地址
//							ShowIPInfo ip = new ShowIPInfo();
//							String ipInfo = ip.getIpAddr(request);
////							获取用户名
//							String userName = ToolUtils.getCookieUserName(request);
////							获取任务单信息
//							Map<String,Object> mapx = service.showTaskById(String.valueOf(taskId));
//							String stationName = service.showStationName(String.valueOf(processId));
//
//						  ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKNum), person, "成品入库审核", "", String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "成品入库审核");


			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//				撤销成品入库
	@RequestMapping(value = "/revokeFinishProductNum", method = RequestMethod.POST)
	@ApiOperation(value = "撤销入库", notes = "撤销入库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson revokeFinishProductNum(HttpServletRequest request) throws ServicesException {

		try {

//						获取客户端IP地址
//						ShowIPInfo ip = new ShowIPInfo();
//						String IP = ip.getIpAddr(request);
//						System.out.println("客户端IP:"+IP);
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			Integer useNum = Integer.parseInt(request.getParameter("useNum"));
			Integer useNumx = useNum;

			Map<String, Object> map = null;

			map = service.showAllQualityApprovasListx("6", String.valueOf(taskId), String.valueOf(processId));
			if (map == null || map.size() == 0) {
				map = service.showAllQualityApprovasListx("0", String.valueOf(taskId), String.valueOf(processId));

			}
			String temsNumx = null;
			if (map == null) {
				temsNumx = "0";
			} else {
				temsNumx = String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS"));
				if (temsNumx.equals("") || temsNumx.equals("null") || temsNumx == null) {
					temsNumx = "0";
				}
			}

			Integer okFinishTems = Integer.parseInt(temsNumx);

			useNum = useNum + okFinishTems;

			service.revokeFinishProductNum(String.valueOf(useNum), taskId, processId);


//						打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//						获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(useNumx), "", "", "", "成品撤销", String.valueOf(mapx.get("STATION_NUM")));

//					  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "成品撤销");


			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//				更新委外发出
	@RequestMapping(value = "/updateOutSourceNum", method = RequestMethod.POST)
	@ApiOperation(value = "委外发出申请", notes = "委外发出申请")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateOutSourceNum(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {


			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object okNum = jsonObject.get("okNum");
				Object supplier = jsonObject.get("supplier");
				Object ids = jsonObject.get("id");
				Object splitFlags = jsonObject.get("splitFlags");


				Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(ids));//查询工序详情

				Integer testNum = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
				testNum = testNum - Integer.parseInt(String.valueOf(okNum));
				Integer insourceApproval = Integer.parseInt(String.valueOf(map.get("OK_INSOURCE_TEM_APPROVLA")));
				insourceApproval = Integer.parseInt(String.valueOf(okNum)) + insourceApproval;
				if (testNum < 0) {
					return Rjson.error("实际发出数量总和不能大于当前可发出数量");
				}


				String outSupplier = String.valueOf(map.get("ALL_OUT_SUPPLIER"));
				if (outSupplier.equals("") || outSupplier == null || outSupplier.equals("null")) {
					outSupplier = "";
					if (String.valueOf(supplier).equals("") || String.valueOf(supplier) == null || String.valueOf(supplier).equals("null")) {
						supplier = "无";
					}
					outSupplier = String.valueOf(supplier) + "  " + outSupplier;
				} else {
					if (String.valueOf(supplier).equals("") || String.valueOf(supplier) == null || String.valueOf(supplier).equals("null")) {
						supplier = "无";
					}
					outSupplier = String.valueOf(supplier) + "  " + outSupplier;
				}


				String outPerson = String.valueOf(map.get("OUT_PERSON"));
				if (outPerson.equals("") || outPerson == null || outPerson.equals("null")) {
					outPerson = "";
					if (person.equals("") || person == null || person.equals("null")) {
						person = "无";
					}
					outPerson = person + "  " + outPerson;

				} else {
					if (person.equals("") || person == null || person.equals("null")) {
						person = "无";
					}
					outPerson = person + "  " + outPerson;
				}

				service.updateOutSourceInfo(String.valueOf(insourceApproval), outSupplier, String.valueOf(taskId), String.valueOf(processId), outPerson, "8", testNum, String.valueOf(ids));//更新8状态

				System.out.println("testNum:" + testNum);


				service.updateOutSidePlanNum(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum), String.valueOf(splitFlags), String.valueOf(ids));//更新主分支数据

				service.updateOutSideMainPlanNum(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum));

				service.updateOutSideChildPlanNum(String.valueOf(taskId), String.valueOf(processId));//更新子分支数据

				service.updateOutsideNumTask(String.valueOf(okNum), outSupplier, taskId.toString(), processId.toString(), ids.toString());

//							打印日志
//							打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//							获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "委外发出申请", outSupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外发出申请");


			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//				任务单委外发出申请
	@RequestMapping(value = "/updateOutSourceNumTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外任务发出申请", notes = "委外任务发出申请")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateOutSourceNumTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {


			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String okNum = request.getParameter("okNum");
			String supplier = request.getParameter("supplier");
			String idxs = request.getParameter("idxs");
			String ids = null;
			if (idxs == null || idxs.equals("")) {
				ids = service.showDetailsIdInfoById(taskId, processId).toString();
			} else {
				ids = idxs;
			}

//						String id = request.getParameter("id");
			String splitFlags = "3";


//						JSONArray jsonArray=JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
//						for (int i = 0; i < jsonArray.size(); i++) {
//							JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//							Object taskId = jsonObject.get("taskId");
//							Object processId = jsonObject.get("processId");
//							Object okNum = jsonObject.get("okNum");
//							Object supplier = jsonObject.get("supplier");
//							Object ids = jsonObject.get("id");
//							Object splitFlags = jsonObject.get("splitFlags");


			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(ids));//查询工序详情

			Integer testNum = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
			testNum = testNum - Integer.parseInt(String.valueOf(okNum));
			Integer insourceApproval = Integer.parseInt(String.valueOf(map.get("OK_INSOURCE_TEM_APPROVLA")));
			insourceApproval = Integer.parseInt(String.valueOf(okNum)) + insourceApproval;
			if (testNum < 0) {
				if (idxs == null || idxs.equals("")) {
					service.deloutSourceInfoById(taskId, processId, ids);
				}
				return Rjson.error("实际发出数量总和不能大于当前可发出数量");
			}


			String outSupplier = String.valueOf(map.get("ALL_OUT_SUPPLIER"));
			if (outSupplier.equals("") || outSupplier == null || outSupplier.equals("null")) {
				outSupplier = "";
				if (String.valueOf(supplier).equals("") || String.valueOf(supplier) == null || String.valueOf(supplier).equals("null")) {
					supplier = "无";
				}
				outSupplier = String.valueOf(supplier) + "  " + outSupplier;
			} else {
				if (String.valueOf(supplier).equals("") || String.valueOf(supplier) == null || String.valueOf(supplier).equals("null")) {
					supplier = "无";
				}
				outSupplier = String.valueOf(supplier) + "  " + outSupplier;
			}


			String outPerson = String.valueOf(map.get("OUT_PERSON"));
			if (outPerson.equals("") || outPerson == null || outPerson.equals("null")) {
				outPerson = "";
				if (person.equals("") || person == null || person.equals("null")) {
					person = "无";
				}
				outPerson = person + "  " + outPerson;

			} else {
				if (person.equals("") || person == null || person.equals("null")) {
					person = "无";
				}
				outPerson = person + "  " + outPerson;
			}

			service.updateOutSourceInfo(String.valueOf(insourceApproval), outSupplier, String.valueOf(taskId), String.valueOf(processId), outPerson, "8", testNum, String.valueOf(ids));//更新8状态

			System.out.println("testNum:" + testNum);


			service.updateOutSidePlanNum(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum), String.valueOf(splitFlags), String.valueOf(ids));//更新主分支数据

			service.updateOutSideMainPlanNum(String.valueOf(taskId), String.valueOf(processId), String.valueOf(testNum));

			service.updateOutSideChildPlanNum(String.valueOf(taskId), String.valueOf(processId));//更新子分支数据

			service.updateOutsideNumTask(String.valueOf(okNum), outSupplier, taskId, processId, ids);


//							打印日志
//							打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//							获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "委外发出申请", outSupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外发出申请");


//						}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	委外发出审批
	@RequestMapping(value = "/updateOutSourceInfos", method = RequestMethod.POST)
	@ApiOperation(value = "委外发出审批", notes = "委外发出审批")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateOutSourceInfos(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {


			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object okNum = jsonObject.get("okNum");
				Object supplier = jsonObject.get("supplier");
				Object outPerson = jsonObject.get("outPerson");
				Object type = jsonObject.get("type");
				Object id = jsonObject.get("id");
				Object splitFlags = jsonObject.get("splitFlags");

				String okNumx = String.valueOf(okNum);
				String okNumxsx = String.valueOf(okNum);
				Integer splitFlagsx = Integer.parseInt(String.valueOf(splitFlags));

				if (splitFlagsx == 4) {
//								获取子分支testNum  获取主分支的testNum 主分支-子分支  更新主分支数据
//								Map<String,Object> mapDatas = service.showTestNumData(String.valueOf(id));
//								Integer testNumData = Integer.parseInt(String.valueOf(mapDatas.get("TESTING_NUM")));

//								Map<String,Object> dataMaps= service.showAllMainBranchInfo(String.valueOf(taskId), String.valueOf(processId));
//								String idData = String.valueOf(dataMaps.get("ID"));
//								Integer testNumsData = Integer.parseInt(String.valueOf(dataMaps.get("TESTING_NUM")));
//								testNumsData = testNumsData -Integer.parseInt(okNumx);
//								service.updateMainTestNumData(String.valueOf(testNumsData), idData);
				}


				Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
				Integer testNumTem = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
				Integer outNumx = Integer.parseInt(String.valueOf(map.get("OUTSOURCE_OUT_NUM")));
				String allOutSupplier = String.valueOf(map.get("ALL_OUT_SUPPLIER"));
//							testNumTem = testNumTem-Integer.parseInt(String.valueOf(okNum));//新的委外发出数量
				Integer outNum = null;
				System.out.println(String.valueOf(type));
				if (String.valueOf(type).equals("3")) {
					if (String.valueOf(map.get("OK_OUTSOURCE_TEMS")) == null || String.valueOf(map.get("OK_OUTSOURCE_TEMS")).equals("") || String.valueOf(map.get("OK_OUTSOURCE_TEMS")).equals("null")) {
						outNum = 0;
					} else {
						outNum = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEMS")));
					}

				} else {
					outNum = (Integer) map.get("OUTSOURCE_OUT_NUM");
				}

				outNumx = outNumx + Integer.parseInt(String.valueOf(okNum));
				okNum = Integer.parseInt(String.valueOf(okNum)) + outNum;//委外发出数量

//							testNum= testNum-Integer.parseInt(String.valueOf(testingTem));
				service.updateOutSourceInfox(String.valueOf(okNum), String.valueOf(testNumTem), String.valueOf(taskId), String.valueOf(processId), "0", person, outNumx, String.valueOf(id));
				service.updateOutChildSourceInfox(String.valueOf(okNum), String.valueOf(testNumTem), String.valueOf(taskId), String.valueOf(processId), "0", person, outNumx, String.valueOf(id));
				service.updateExamineOutsideNumTask(okNumxsx, String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				Map<String, Object> mainMap = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
				Integer outsideMainNum = Integer.parseInt(mainMap.get("OUTSOURCE_OUT_NUM").toString());//主分支旧的委外发出数量
				outsideMainNum = outsideMainNum + Integer.parseInt(okNumxsx);
				String mainId = mainMap.get("ID").toString();//主分支	ID
				service.updateMainOutsideNumTask(outsideMainNum.toString(), taskId.toString(), processId.toString(), mainId);//更新委外发出数量主分支


//							if((Integer)map.get("TESTING_NUM")==Integer.parseInt(String.valueOf(testingTem))){
//								service.updateOutSourceInfox(String.valueOf(testingTem), String.valueOf(testNum), String.valueOf(taskId), String.valueOf(processId), "9",person);
//							}else{
//								service.updateOutSourceInfox(String.valueOf(testingTem), String.valueOf(testNum), String.valueOf(taskId), String.valueOf(processId), "0",person);
//							}
//							打印日志


				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//							获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, okNumx, person, "委外发出审核", allOutSupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外发出审核");


			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateOutSourceInfosTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外任务发出审批", notes = "委外任务发出审批")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateOutSourceInfosTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {


//						String person = request.getParameter("person");
//						JSONArray jsonArray=JSONArray.fromObject(info);
////						JSONArray jsonArray=JSONArray.fromObject(info);
//						for (int i = 0; i < jsonArray.size(); i++) {
//							JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//							Object taskId = jsonObject.get("taskId");
//							Object processId = jsonObject.get("processId");
//							Object okNum = jsonObject.get("okNum");
//							Object supplier = jsonObject.get("supplier");
//							Object outPerson = jsonObject.get("outPerson");
//							Object type = jsonObject.get("type");
//							Object id = jsonObject.get("id");
//							Object splitFlags = jsonObject.get("splitFlags");
			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			Integer okNum = Integer.parseInt(request.getParameter("okNum"));
			String supplier = request.getParameter("supplier");
			String id = request.getParameter("idxs");
			String type = "3";
			Integer splitFlagsx = 4;
			String okNumxsx = String.valueOf(okNum);
			String okNumx = String.valueOf(okNum);
//							Integer splitFlagsx = Integer.parseInt(String.valueOf(splitFlags));

			if (splitFlagsx == 4) {
//								获取子分支testNum  获取主分支的testNum 主分支-子分支  更新主分支数据
//								Map<String,Object> mapDatas = service.showTestNumData(String.valueOf(id));
//								Integer testNumData = Integer.parseInt(String.valueOf(mapDatas.get("TESTING_NUM")));


//								Map<String,Object> dataMaps= service.showAllMainBranchInfo(String.valueOf(taskId), String.valueOf(processId));
//								String idData = String.valueOf(dataMaps.get("ID"));
//								Integer testNumsData = Integer.parseInt(String.valueOf(dataMaps.get("TESTING_NUM")));
//								testNumsData = testNumsData -Integer.parseInt(okNumx);
//								service.updateMainTestNumData(String.valueOf(testNumsData), idData);
			}


			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
			Integer testNumTem = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
			Integer outNumx = Integer.parseInt(String.valueOf(map.get("OUTSOURCE_OUT_NUM")));
			String allOutSupplier = String.valueOf(map.get("ALL_OUT_SUPPLIER"));
//							testNumTem = testNumTem-Integer.parseInt(String.valueOf(okNum));//新的委外发出数量
			Integer outNum = null;
			System.out.println(String.valueOf(type));
			if (String.valueOf(type).equals("3")) {
				if (String.valueOf(map.get("OK_OUTSOURCE_TEMS")) == null || String.valueOf(map.get("OK_OUTSOURCE_TEMS")).equals("") || String.valueOf(map.get("OK_OUTSOURCE_TEMS")).equals("null")) {
					outNum = 0;
				} else {
					outNum = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEMS")));
				}

			} else {
				outNum = (Integer) map.get("OUTSOURCE_OUT_NUM");
			}

			outNumx = outNumx + Integer.parseInt(String.valueOf(okNum));
			okNum = okNum + outNum;//委外发出数量

//							testNum= testNum-Integer.parseInt(String.valueOf(testingTem));
			service.updateOutSourceInfox(String.valueOf(okNum), String.valueOf(testNumTem), String.valueOf(taskId), String.valueOf(processId), "0", person, outNumx, String.valueOf(id));
			service.updateOutChildSourceInfox(String.valueOf(okNum), String.valueOf(testNumTem), String.valueOf(taskId), String.valueOf(processId), "0", person, outNumx, String.valueOf(id));
//							委外任务数据更新
			service.updateExamineOutsideNumTask(okNumxsx, taskId, processId, id);

			Map<String, Object> mainMap = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
			Integer outsideMainNum = Integer.parseInt(mainMap.get("OUTSOURCE_OUT_NUM").toString());//主分支旧的委外发出数量
			outsideMainNum = outsideMainNum + Integer.parseInt(okNumxsx);
			String mainId = mainMap.get("ID").toString();//主分支	ID
			service.updateMainOutsideNumTask(outsideMainNum.toString(), taskId.toString(), processId.toString(), mainId);//更新委外发出数量主分支

//							if((Integer)map.get("TESTING_NUM")==Integer.parseInt(String.valueOf(testingTem))){
//								service.updateOutSourceInfox(String.valueOf(testingTem), String.valueOf(testNum), String.valueOf(taskId), String.valueOf(processId), "9",person);
//							}else{
//								service.updateOutSourceInfox(String.valueOf(testingTem), String.valueOf(testNum), String.valueOf(taskId), String.valueOf(processId), "0",person);
//							}
//							打印日志


			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//							获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, okNumx, person, "委外发出审核", allOutSupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外发出审核");


//						}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	撤销委外审批
	@RequestMapping(value = "/revokeOutSource", method = RequestMethod.POST)
	@ApiOperation(value = "撤销委外发出申请", notes = "撤销委外发出申请")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson revokeOutSource(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String id = request.getParameter("id");
			Integer splitFlags = Integer.parseInt(request.getParameter("splitFlags"));


			Integer okNum = Integer.parseInt(request.getParameter("okNum"));
			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情
			Integer testNum = Integer.parseInt(String.valueOf(map.get("TESTING_NUM_TEM")));
			testNum = testNum + okNum;


//							String useNum = String.valueOf(map.get("OUTSOURCE_OUT_NUM"));
//							Integer newTestNum=null;
//							newTestNum=Integer.parseInt(testNum)-Integer.parseInt(useNum);
//							1、找出标记为1或者2的主分支   2、添加数量  3、删除当前ID的子分支
			System.out.println("testNum:" + testNum);
//							service.revokeOutSource(String.valueOf(testNum), taskId, processId, "0",id);
			service.updateOutSideMainPlanNum(taskId, processId, String.valueOf(testNum));
			service.updateOutSideChildPlanNum(taskId, processId);

			if (splitFlags != 1 && splitFlags != 2) {
				service.delOutSideChildBrach(id);
				service.deloutSourceInfoById(taskId, processId, id);//删除委外任务
				Integer countNum = service.countSplitChildNums(taskId, processId);
				if (countNum == 1) {
					service.updateSplitStatusData(taskId, processId);
				}
			} else {
				service.updateSplitOKApprovlaNum(id);
			}


//							打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//							获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), "", "", "", "委外发出撤销", String.valueOf(mapx.get("STATION_NUM")));

//						  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外发出撤销");


			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//				委外接收申请

	@RequestMapping(value = "/updateInSource", method = RequestMethod.POST)
	@ApiOperation(value = "委外接收申请", notes = "委外接收申请")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateInSource(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {

			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object OKInsourceTem = jsonObject.get("OKInsourceTem");
				Object inSupplier = jsonObject.get("inSupplier");
				Object ids = jsonObject.get("id");
				if (String.valueOf(inSupplier).equals("") || String.valueOf(inSupplier).equals("null") || String.valueOf(inSupplier) == null) {
					inSupplier = "无";
				}
				if (String.valueOf(OKInsourceTem).equals("") || String.valueOf(OKInsourceTem) == null) {
					OKInsourceTem = "0";
				}

				Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(ids));//查询工序详情

				String outSourceInNum = String.valueOf(map.get("INSOURCE_IN_TEMS_APPROVAL"));
				Integer okOutsourceTems = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEM")));//临时接收数量

				String allInsupplier = String.valueOf(map.get("ALL_IN_SUPPLIER"));
				if (allInsupplier.equals("") || allInsupplier.equals("null") || allInsupplier == null) {
					allInsupplier = inSupplier + "//";
				} else {
					allInsupplier = inSupplier + "//" + allInsupplier;
				}

				if (outSourceInNum.equals("") || outSourceInNum.equals("null") || outSourceInNum == null) {
					outSourceInNum = "0";
				}
				Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));//加到数据更新中


				okOutsourceTems = okOutsourceTems - Integer.parseInt(String.valueOf(OKInsourceTem));


				if ((Integer) map.get("PLAN_NUM") == newOutSourceInNum) {
					service.updateInSourceInfo(allInsupplier, String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person, okOutsourceTems, newOutSourceInNum, String.valueOf(ids));
					service.updateOutsideInsideApplyNum(newOutSourceInNum.toString(), taskId.toString(), processId.toString(), ids.toString());
				} else {
					service.updateInSourceInfo(allInsupplier, String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person, okOutsourceTems, newOutSourceInNum, String.valueOf(ids));
					service.updateOutsideInsideApplyNum(newOutSourceInNum.toString(), taskId.toString(), processId.toString(), ids.toString());
				}
				inSupplier = "";


//							打印日志
//							打印日志


				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//							获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
				Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
				String stationName = service.showStationName(String.valueOf(processId));

				ProcessLogModel model = new ProcessLogModel();
				String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKInsourceTem), person, "委外接收申请", allInsupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfo);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外接收申请");


			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//				委外接收申请

	@RequestMapping(value = "/updateInSourceTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外任务接收申请", notes = "委外任务接收申请")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateInSourceTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {

//						String person = request.getParameter("person");
//						JSONArray jsonArray=JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
//						for (int i = 0; i < jsonArray.size(); i++) {
//							JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//							Object taskId = jsonObject.get("taskId");
//							Object processId = jsonObject.get("processId");
//							Object OKInsourceTem = jsonObject.get("OKInsourceTem");
//							Object inSupplier = jsonObject.get("inSupplier");
//							Object ids = jsonObject.get("id");
			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String OKInsourceTem = request.getParameter("OKInsourceTem");
			String inSupplier = request.getParameter("inSupplier");
			String ids = request.getParameter("id");

			if (String.valueOf(inSupplier).equals("") || String.valueOf(inSupplier).equals("null") || String.valueOf(inSupplier) == null) {
				inSupplier = "无";
			}
			if (String.valueOf(OKInsourceTem).equals("") || String.valueOf(OKInsourceTem) == null) {
				OKInsourceTem = "0";
			}

			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(ids));//查询工序详情

			String outSourceInNum = String.valueOf(map.get("INSOURCE_IN_TEMS_APPROVAL"));
			Integer okOutsourceTems = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEM")));//临时接收数量

			String allInsupplier = String.valueOf(map.get("ALL_IN_SUPPLIER"));
			if (allInsupplier.equals("") || allInsupplier.equals("null") || allInsupplier == null) {
				allInsupplier = inSupplier + "//";
			} else {
				allInsupplier = inSupplier + "//" + allInsupplier;
			}

			if (outSourceInNum.equals("") || outSourceInNum.equals("null") || outSourceInNum == null) {
				outSourceInNum = "0";
			}
			Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));//加到数据更新中


			okOutsourceTems = okOutsourceTems - Integer.parseInt(String.valueOf(OKInsourceTem));


			if ((Integer) map.get("PLAN_NUM") == newOutSourceInNum) {
				service.updateInSourceInfo(allInsupplier, String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person, okOutsourceTems, newOutSourceInNum, String.valueOf(ids));
				service.updateOutsideInsideApplyNum(newOutSourceInNum.toString(), taskId.toString(), processId.toString(), ids.toString());
			} else {
				service.updateInSourceInfo(allInsupplier, String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person, okOutsourceTems, newOutSourceInNum, String.valueOf(ids));
				service.updateOutsideInsideApplyNum(newOutSourceInNum.toString(), taskId.toString(), processId.toString(), ids.toString());
			}
			inSupplier = "";


//							打印日志
//							打印日志


			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//							获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(OKInsourceTem), person, "委外接收申请", allInsupplier, String.valueOf(mapx.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外接收申请");


//						}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//				委外接收撤销
	@RequestMapping(value = "/updateRevokeSourceInfo", method = RequestMethod.POST)
	@ApiOperation(value = "委外接收撤销", notes = "委外接收撤销")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateRevokeSourceInfo(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String id = request.getParameter("id");
			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), id);//查询工序详情
//					String useNum = String.valueOf(map.get("USEABLE_NUM"));
			String okOutSourceTem = String.valueOf(map.get("OK_OUTSOURCE_TEM"));
			String insourceInTemsApproval = String.valueOf(map.get("INSOURCE_IN_TEMS_APPROVAL"));
			Integer newNum = Integer.parseInt(okOutSourceTem) + Integer.parseInt(insourceInTemsApproval);

			service.updateRevokeSourceInfo(String.valueOf(newNum), taskId, processId, id);//更新撤销操作
			service.updateOutsideRevockApplyNum(taskId, processId, id);//更新委外任务


//					打印日志
			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//					获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(insourceInTemsApproval), "", "", "", "委外接收撤销", String.valueOf(mapx.get("STATION_NUM")));

//				  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外接收撤销");


			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//				委外接收数据更新
	@RequestMapping(value = "/updateInSourceprocessInfoTask", method = RequestMethod.POST)
	@ApiOperation(value = "委外数据更新", notes = "委外数据更新")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateInSourceprocessInfoTask(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
//						JSONArray jsonArray=JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
//						for (int i = 0; i < jsonArray.size(); i++) {
//							JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//							Object taskId = jsonObject.get("taskId");
//							Object processId = jsonObject.get("processId");
//							Object OKNum = jsonObject.get("OKNum");
//							Object type = jsonObject.get("type");
//							Object id = jsonObject.get("id");
//						String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String OKNum = request.getParameter("OKNum");
			String id = request.getParameter("id");
			String type = "3";


			if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null) {
				OKNum = "0";
			}

			String okNumx = String.valueOf(OKNum);

			Map<String, Object> map = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情

			String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
//							String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
			String completeNum = String.valueOf(map.get("COMPLETE_NUM"));
			String useNum = String.valueOf(map.get("USEABLE_NUM"));
			String planNum = String.valueOf(map.get("PLAN_NUM"));
			Integer orderNum = (Integer) map.get("PROCESS_ORDER");//当前工序的排序
			String insuppler = String.valueOf(map.get("ALL_IN_SUPPLIER"));
			Integer nextOrderNum = orderNum + 1;
			if (String.valueOf(("OKNum")).equals("")) {
				OKNum = "0";
			}

			Integer completeNums = Integer.parseInt(completeNum) + Integer.parseInt(String.valueOf((OKNum)));
			System.out.println("完成数量:" + completeNums + "," + planNum);
			Integer useNums = Integer.parseInt(useNum) - Integer.parseInt(String.valueOf((OKNum)));
			String completeNumx = String.valueOf(completeNums);
			if (planNum.equals(completeNumx)) {//未全部完成
				service.updateInSourceprocessInfo("11", person, String.valueOf(completeNums), String.valueOf(useNums), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				Map<String, Object> mapx = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
				okNUM = okNUM - completeNums;
//								System.out.println("useNums:"+useNums);
				Integer applyNUM = Integer.parseInt(mapx.get("IN_SIDE_NUM_APPLY").toString());
				applyNUM = applyNUM - Integer.parseInt(OKNum);
				service.updateOutsideExamineNums(okNUM.toString(), applyNUM.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
				Map<String, Object> mapxs = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer planNums = Integer.parseInt(mapxs.get("PLAN_NUM").toString());
				Integer examineInside = Integer.parseInt(mapxs.get("IN_SIDE_NUM_EXAMINE").toString());
				Map<String, Object> mainMaps = service.showAllMainBranchInfo(taskId, processId);
				Integer complete = (Integer) mainMaps.get("COMPLETE_NUM");
				complete = complete + Integer.parseInt(OKNum);
				service.updateMainComplete(complete.toString(), taskId, processId);
				if (planNums == examineInside) {
					service.updateOutsideStatusNum(taskId, processId, id, "6");
				}
			} else {
				Map<String, Object> mapx = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
				okNUM = okNUM - Integer.parseInt(OKNum.toString());
				System.out.println("useNums:" + useNums);
				Integer applyNUM = Integer.parseInt(mapx.get("IN_SIDE_NUM_APPLY").toString());
				applyNUM = applyNUM - Integer.parseInt(OKNum);
				service.updateInSourceprocessInfo("0", person, String.valueOf(completeNums), String.valueOf(useNums), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
				service.updateOutsideExamineNums(okNUM.toString(), applyNUM.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
				Map<String, Object> mapxs = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer planNums = Integer.parseInt(mapxs.get("PLAN_NUM").toString());
				Integer examineInside = Integer.parseInt(mapxs.get("IN_SIDE_NUM_EXAMINE").toString());
				Map<String, Object> mainMaps = service.showAllMainBranchInfo(taskId, processId);
				Integer complete = (Integer) mainMaps.get("COMPLETE_NUM");
				complete = complete + Integer.parseInt(OKNum);
				service.updateMainComplete(complete.toString(), taskId, processId);
				if (planNums == examineInside) {
					service.updateOutsideStatusNum(taskId, processId, id, "6");
				}
			}

//							更新下道工序的待检测数量   nextOrderNum


			if (String.valueOf(type).equals("3")) {
				//根据生产任务与序号查找下一道工序

				Map<String, Object> showNextProcessMap = service.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNum);
				Integer testingNum = (Integer) showNextProcessMap.get("TESTING_NUM");
				Map<String, Object> showNextProcessMapx = service.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNum);
				Map<String, Object> showNextProcessMapxs = service.showTaskById(String.valueOf(taskId));
				Integer processTypeInfos = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxs.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxs.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapx.get("PROCESS_ID"))));
				if (processTypeInfos == 3) {
					testingNum = testingNum + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
					if (testingNum > (Integer) showNextProcessMap.get("PLAN_NUM")) {
						return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
					}

					Map<String, Object> mapd = service.showTaskById(String.valueOf(taskId));
					Integer planNums = Integer.parseInt(String.valueOf(map.get("PLAN_NUM")));
//									Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
					Integer nextOutNum = null;
					if (Integer.parseInt(String.valueOf(showNextProcessMap.get("PROCESS_ORDER"))) > 1) {
						if (Integer.parseInt((String.valueOf(showNextProcessMap.get("TESTING_NUM_TEM")))) >= planNums) {
							nextOutNum = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMap.get("TESTING_NUM_TEM"))) - planNums;
						} else {
							nextOutNum = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMap.get("TESTING_NUM_TEM")));
						}

					} else {
						nextOutNum = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMap.get("TESTING_NUM_TEM")));
					}

					//				更新下道工序信息(待检测数量)
					service.updateNextProcessInfo(testingNum, Integer.parseInt(taskId), nextOrderNum, nextOutNum);
				} else {
					//根据生产任务与序号查找下一道工序
					Map<String, Object> showNextProcessMaps = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNum);
					Integer pushDownNum = Integer.parseInt(showNextProcessMaps.get("PUSH_DOWN_NUM").toString());


					Integer testingNumxs = (Integer) showNextProcessMaps.get("TESTING_NUM");
					testingNumxs = testingNumxs + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
					if (testingNumxs > (Integer) showNextProcessMaps.get("PLAN_NUM")) {
						return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
					}
					pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNum));


//									Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//									if(processTypeInfosx!=3){
					Integer nextOutNum = testingNumxs - Integer.parseInt(String.valueOf(showNextProcessMaps.get("OUTSOURCE_OUT_NUM"))) - Integer.parseInt(String.valueOf(showNextProcessMaps.get("OK_INSOURCE_TEM_APPROVLA")));

					//				更新下道工序信息(待检测数量)
					service.updateNextProcessInfox(testingNumxs, Integer.parseInt(taskId), nextOrderNum, nextOutNum, pushDownNum.toString());
				}


			} else {

//

			}
//

//

//							Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));


//							if((Integer)map.get("PLAN_NUM")==newOutSourceInNum){
//								service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person);
//							}else{
//								service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person);
//							}
//
//							打印日志
			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//									获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息
			Map<String, Object> mapx = service.showTaskById(String.valueOf(taskId));
			String stationName = service.showStationName(String.valueOf(processId));

			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, okNumx, person, "委外接收审核", insuppler, String.valueOf(mapx.get("STATION_NUM")));
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			System.out.println("log:" + logInfo);
			logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfo, "委外接收审核");


//						}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//				委外接收数据更新
	@RequestMapping(value = "/updateInSourceprocessInfo", method = RequestMethod.POST)
	@ApiOperation(value = "委外数据更新", notes = "委外数据更新")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateInSourceprocessInfo(HttpServletRequest request, @RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			JSONArray jsonArray = JSONArray.fromObject(info);
//						JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object OKNum = jsonObject.get("OKNum");
				Object type = jsonObject.get("type");
				Object id = jsonObject.get("id");


				if (String.valueOf(OKNum).equals("") || String.valueOf(OKNum) == null) {
					OKNum = "0";
				}

				String okNumxww = String.valueOf(OKNum);

				Map<String, Object> mapww = service.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));//查询工序详情

				String outSourceInNumww = String.valueOf(mapww.get("OUTSOURCE_IN_NUM"));
//							String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
				String completeNumww = String.valueOf(mapww.get("COMPLETE_NUM"));
				String useNumww = String.valueOf(mapww.get("USEABLE_NUM"));
				String planNumww = String.valueOf(mapww.get("PLAN_NUM"));
				Integer orderNumww = (Integer) mapww.get("PROCESS_ORDER");//当前工序的排序
				String insupplerww = String.valueOf(mapww.get("ALL_IN_SUPPLIER"));
				Integer nextOrderNumww = orderNumww + 1;
				if (String.valueOf(("OKNum")).equals("")) {
					OKNum = 0;
				}

				Integer completeNumsww = Integer.parseInt(completeNumww) + Integer.parseInt(String.valueOf((OKNum)));
				System.out.println("完成数量:" + completeNumsww + "," + planNumww);
				Integer useNumsww = Integer.parseInt(useNumww) - Integer.parseInt(String.valueOf((OKNum)));
				String completeNumxww = String.valueOf(completeNumsww);
//							if(planNum.equals(completeNumx)){//未全部完成
//								service.updateInSourceprocessInfo("11", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//								Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//								Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//								okNUM = okNUM-completeNums;
//								service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//							}else{
//								Map<String,Object> mapx=  service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
//								Integer okNUM = Integer.parseInt(mapx.get("OK_NUM").toString());
//								okNUM = okNUM-Integer.parseInt(OKNum.toString());
//								service.updateInSourceprocessInfo("0", person, String.valueOf(completeNums), String.valueOf(useNums),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
//								service.updateOutsideExamineNums(okNUM.toString(),useNums.toString(), completeNums.toString(), taskId.toString(), processId.toString(), id.toString());
//							}
				if (planNumww.equals(completeNumxww)) {//未全部完成
					service.updateInSourceprocessInfo("11", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer okNUM = Integer.parseInt(mapxww.get("OK_NUM").toString());
					okNUM = okNUM - completeNumsww;
//								System.out.println("useNums:"+useNums);
					Integer applyNUM = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
					applyNUM = applyNUM - Integer.parseInt(OKNum.toString());
					service.updateOutsideExamineNums(okNUM.toString(), applyNUM.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
					Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
					Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
					Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
					Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
					completeww = completeww + Integer.parseInt(OKNum.toString());
					service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
					if (planNumsww == examineInsideww) {
						service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
					}
				} else {
					Map<String, Object> mapxww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer okNUMww = Integer.parseInt(mapxww.get("OK_NUM").toString());
					okNUMww = okNUMww - Integer.parseInt(OKNum.toString());
					System.out.println("useNums:" + useNumsww);
					Integer applyNUMww = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
					applyNUMww = applyNUMww - Integer.parseInt(OKNum.toString());
					service.updateInSourceprocessInfo("0", person, String.valueOf(completeNumsww), String.valueOf(useNumsww), String.valueOf(taskId), String.valueOf(processId), String.valueOf(id));
					service.updateOutsideExamineNums(okNUMww.toString(), applyNUMww.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
					Map<String, Object> mapxsww = service.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
					Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
					Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
					Map<String, Object> mainMapsww = service.showAllMainBranchInfo(taskId.toString(), processId.toString());
					Integer completeww = (Integer) mainMapsww.get("COMPLETE_NUM");
					completeww = completeww + Integer.parseInt(OKNum.toString());
					service.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
					if (planNumsww == examineInsideww) {
						service.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
					}
				}

//							更新下道工序的待检测数量   nextOrderNum


				if (String.valueOf(type).equals("3")) {
					//根据生产任务与序号查找下一道工序

					Map<String, Object> showNextProcessMapww = service.showNextProcessInfo((Integer) taskId, nextOrderNumww);
					Integer testingNumww = (Integer) showNextProcessMapww.get("TESTING_NUM");
					Map<String, Object> showNextProcessMapxww = service.showNextProcessInfo((Integer) taskId, nextOrderNumww);
					Map<String, Object> showNextProcessMapxsww = service.showTaskById(String.valueOf(taskId));
					Integer processTypeInfosww = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsww.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsww.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxww.get("PROCESS_ID"))));
					if (processTypeInfosww == 3) {
						testingNumww = testingNumww + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
						if (testingNumww > (Integer) showNextProcessMapww.get("PLAN_NUM")) {
							return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
						}

						Map<String, Object> mapdww = service.showTaskById(String.valueOf(taskId));
						Integer planNumsww = Integer.parseInt(String.valueOf(mapww.get("PLAN_NUM")));
//									Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
						Integer nextOutNumww = null;
						if (Integer.parseInt(String.valueOf(showNextProcessMapww.get("PROCESS_ORDER"))) > 1) {
							if (Integer.parseInt((String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")))) >= planNumsww) {
								nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM"))) - planNumsww;
							} else {
								nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
							}

						} else {
							nextOutNumww = Integer.parseInt(String.valueOf((OKNum))) + Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
						}

						//				更新下道工序信息(待检测数量)
						service.updateNextProcessInfo(testingNumww, (Integer) taskId, nextOrderNumww, nextOutNumww);
					} else {
						//根据生产任务与序号查找下一道工序
						Map<String, Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
						Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());

						Integer testingNumxsww = (Integer) showNextProcessMapsww.get("TESTING_NUM");
						testingNumxsww = testingNumxsww + Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
						if (testingNumxsww > (Integer) showNextProcessMapsww.get("PLAN_NUM")) {
							return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
						}
						pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNum));


//									Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//									if(processTypeInfosx!=3){
						Integer nextOutNumww = testingNumxsww - Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM"))) - Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));

						//				更新下道工序信息(待检测数量)
						service.updateNextProcessInfox(testingNumxsww, (Integer) taskId, nextOrderNumww, nextOutNumww, pushDownNum.toString());
					}


				} else {

//

				}
//

//

//							Integer newOutSourceInNum = Integer.parseInt(outSourceInNum) + Integer.parseInt(String.valueOf(OKInsourceTem));


//							if((Integer)map.get("PLAN_NUM")==newOutSourceInNum){
//								service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "11", String.valueOf(taskId), String.valueOf(processId), person);
//							}else{
//								service.updateInSourceInfo(String.valueOf(inSupplier), String.valueOf(OKInsourceTem), "10", String.valueOf(taskId), String.valueOf(processId), person);
//							}
//
//							打印日志
				//获取客户端IP地址
				ShowIPInfo ipww = new ShowIPInfo();
				String ipInfoww = ipww.getIpAddr(request);
//									获取用户名
				String userNameww = ToolUtils.getCookieUserName(request);
//									获取任务单信息
				Map<String, Object> mapxww = service.showTaskById(String.valueOf(taskId));
				String stationNameww = service.showStationName(String.valueOf(processId));

				ProcessLogModel modelww = new ProcessLogModel();
				String logInfoww = modelww.pushDownLogInfo(userNameww, ipInfoww, String.valueOf(mapxww.get("PROJECT_NAME")), String.valueOf(mapxww.get("PROJECT_NUM")), String.valueOf(mapxww.get("SPECIFICATION_AND_MODEL")), stationNameww, okNumxww, person, "委外接收审核", insupplerww, String.valueOf(mapxww.get("STATION_NUM")));
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
				System.out.println("log:" + logInfoww);
				logInfos.addProcessLogInfo(String.valueOf(taskId), String.valueOf(processId), logInfoww, "委外接收审核");


			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//===========================================================================旧版============================================================
	//遍历存在工序类型1的集合（委外发出集合）
	@RequestMapping(value = "/showTaskStatusInfos", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序类型", notes = "查询工序类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showTaskStatusInfos(HttpServletRequest request) throws ServicesException {

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));

			Map<String, Object> status = service.showTaskStatusInfos(id);
			return Rjson.success(210, status);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//遍历存在工序类型1的集合（委外发出集合）
	@RequestMapping(value = "/showAllTaskByTypeOutNum", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询委外发出工序", notes = "根据指定生产任务查询委外发出工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllTaskByTypeOutNum(HttpServletRequest request) throws ServicesException {

		try {
//			pageNum: this.queryInfo.pageNum,
//				pageSize: this.queryInfo.pageSize
			Integer  pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Integer  pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String typeId = request.getParameter("typeId");
			String flags = request.getParameter("flags");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModel");
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list = null;
//
//				String statusFlags = request.getParameter("statusFlags");
//				String projectNum=request.getParameter("projectNum");
//				String specificationModel=request.getParameter("specificationModels");

			list = service.showAllProcessTypeTaskOutInfos(typeId, flags, stationNum, projectNum, specificationModel, materiCode);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list, 5);


			return Rjson.success(210, pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//遍历存在工序类型1的集合（委外发出集合）
	@RequestMapping(value = "/showAllTaskByTypeOutNumxs", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询委外发出工序", notes = "根据指定生产任务查询委外发出工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllTaskByTypeOutNumxs(HttpServletRequest request) throws ServicesException {

		try {
			String typeId = request.getParameter("typeId");
			String flags = request.getParameter("flags");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModel");


			List<Map<String, Object>> list = null;
//
//						String statusFlags = request.getParameter("statusFlags");
//						String projectNum=request.getParameter("projectNum");
//						String specificationModel=request.getParameter("specificationModels");

			list = service.showAllProcessTypeTaskOutInfosxs(typeId, flags, stationNum, materiCode, projectNum, specificationModel);


			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//遍历存在工序类型1的集合（委外发出集合）
	@RequestMapping(value = "/showAllTaskByTypeOutNumx", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询委外发出工序", notes = "根据指定生产任务查询委外发出工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllTaskByTypeOutNumx(HttpServletRequest request) throws ServicesException {

		try {
			String typeId = request.getParameter("typeId");
			String flags = request.getParameter("flags");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			String projectNum = request.getParameter("projectNum");
			Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String specificationModel = request.getParameter("specificationModel");
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list = null;
//
//				String statusFlags = request.getParameter("statusFlags");
//				String projectNum=request.getParameter("projectNum");
//				String specificationModel=request.getParameter("specificationModels");

			list = service.showAllProcessTypeTaskOutInfosx(typeId, flags, stationNum, materiCode, projectNum, specificationModel);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list, 5);


			return Rjson.success(210, pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//遍历存在工序类型1的集合（下料类型集合）
	@RequestMapping(value = "/showAllTaskByTypeNum", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询工序", notes = "根据指定生产任务查询工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllTaskByTypeNum(HttpServletRequest request) throws ServicesException {

		try {

//			获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String IP = ip.getIpAddr(request);
			System.out.println("客户端IP:" + IP);
//			获取客户端IP地址

			Integer typeId = Integer.parseInt(request.getParameter("typeId"));

			String statusFlags = request.getParameter("statusFlags");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModels");
			String materiCode = request.getParameter("materiCode");
			String stationNum = request.getParameter("stationNum");

			List<Map<String, Object>> list = service.showAllProcessTypeTaskInfos(typeId, projectNum, specificationModel, statusFlags, "0", stationNum, materiCode);
//			List<Map<String,Object>> list =service.showAllFinishProduct("0")
			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//遍历存在工序类型1的集合（下料类型集合）
	@RequestMapping(value = "/showAllTaskByTypeNumx", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询工序", notes = "根据指定生产任务查询工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "statusFlags", value = "任务单状态", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllTaskByTypeNumx(HttpServletRequest request) throws ServicesException {

		try {

//				获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String IP = ip.getIpAddr(request);
			System.out.println("客户端IP:" + IP);
//				获取客户端IP地址

			Integer typeId = Integer.parseInt(request.getParameter("typeId"));

			String statusFlags = request.getParameter("statusFlags");
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModels");
			String stationNum = request.getParameter("stationNum");
			String materiCode = request.getParameter("materiCode");
			List<Map<String, Object>> list = service.showAllProcessTypeTaskInfosx(typeId, projectNum, specificationModel, statusFlags, "0", stationNum, materiCode);
//				List<Map<String,Object>> list =service.showAllFinishProduct("0")
			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	遍历下料类型的任务单下拉框数据
	@RequestMapping(value = "/showAllSelectByTypeId", method = RequestMethod.POST)
	@ApiOperation(value = "查询指定类型select", notes = "查询指定类型select")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "工序类型编号", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "taskId", value = "生产任务ID", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showAllSelectByTypeId(HttpServletRequest request) throws ServicesException {

		try {
			Integer typeId = Integer.parseInt(request.getParameter("typeId"));
			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			List<Map<String, Object>> list = service.showAllRouteNameList(taskId, typeId);
			return Rjson.success(210, list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	//	显示品检可用数量
	@RequestMapping(value = "/showAllUseAbleNum", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询工序", notes = "根据指定生产任务查询工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllUseAbleNum(HttpServletRequest request) throws ServicesException {

		try {
//			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModels");
			String processName = request.getParameter("processName");
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");
			return Rjson.success(map.get("USEABLE_NUMS"));
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showAllClientRoute", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询工序", notes = "根据指定生产任务查询工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllClientRoute(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
//			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModels");
			list = service.showAllClientRoute(projectNum, specificationModels);
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateProcessTaskInfo", method = RequestMethod.POST)
	@ApiOperation(value = "更新任务单信息(普通+委外检测)", notes = "更新任务单信息(普通+委外检测)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "qualityType", value = "工序类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "OKNum", value = "合格数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "NG数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGReason", value = "NG原因", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson updateProcessTaskInfo(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
//			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");
			String qualityType = request.getParameter("qualityType");
			String OKNum = request.getParameter("OKNum");
			String NGNum = request.getParameter("NGNum");
			String NGReason = request.getParameter("NGReason");
//			String userName = request.getParameter("userName");
			String userName = ToolUtils.getCookieUserName(request);
			if (NGReason.equals("")) {
				NGReason = "无";
			}
//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");
			if ((Integer) map.get("STATUS_FLAGS") == 0) {
				return Rjson.error(202, "生产任务未开始，无法录入品检数量");
			}
			Integer countNowNum = null;
			Integer countOldNum = null;
			countOldNum = (Integer) map.get("PLAN_NUM") - (Integer) map.get("COMPLETE_NUM") - (Integer) map.get("NG_NUM") - (Integer) map.get("USEABLE_NUM");//等于待检测数量testingNum

			if (!OKNum.equals("") && !NGNum.equals("")) {
				countNowNum = Integer.parseInt(OKNum) + Integer.parseInt(NGNum);
			}
			if (OKNum.equals("") && !NGNum.equals("")) {
				countNowNum = Integer.parseInt(NGNum);
				OKNum = "0";
			}
			if (!OKNum.equals("") && NGNum.equals("")) {
				countNowNum = Integer.parseInt(OKNum);
				NGNum = "0";
			}
			if (OKNum.equals("") && NGNum.equals("")) {
				countNowNum = 0;
				OKNum = "0";
				NGNum = "0";
			}
			if (countOldNum < countNowNum) {
				return Rjson.error(202, "品检数量不能大于待品检数量,查看是否存在NG返修");
			}
			Integer detailsId = (Integer) map.get("DETAILS_ID");
			Integer taskId = (Integer) map.get("TASK_ID");
			Integer OKNums = null;
			Integer NGNums = null;
			OKNums = (Integer) map.get("USEABLE_NUM") + Integer.parseInt(OKNum);//新的可用数量（合格数量+旧的可用数量）
			NGNums = (Integer) map.get("NG_NUM") + Integer.parseInt(NGNum);
			Integer delTestingNum = (Integer) map.get("TESTING_NUM");
			delTestingNum = delTestingNum - countNowNum;//旧的待测试数量-目前检测的数量
			if (delTestingNum < 0) {
				return Rjson.error(202, "品检数量不能大于待品检数量,查看是否存在NG返修");
			}
			Integer typeInfos = service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer) map.get("PROCESS_ID"));
			if (typeInfos != 3) {//非委外
				service.updateDetailsUseable(OKNums, taskId, detailsId, delTestingNum, NGNums, null);
			} else {//委外
				Integer processOutNums = (Integer) map.get("OUTSOURCE_OUT_NUM");//就有的委外发出数量
				Integer countOutNums = Integer.parseInt(OKNum) + Integer.parseInt(NGNum);
				processOutNums = processOutNums - countOutNums;
				if (processOutNums < 0) {
					return Rjson.error(202, "品检数量不能大于委外发出数量,查看是否存在NG返修");
				}

				service.updateDetailsUseable(OKNums, taskId, detailsId, delTestingNum, NGNums, processOutNums);
			}


			Map<String, Object> mapx = service.showProcessTaskInfos(taskId);
			Integer taskNGNum = (Integer) mapx.get("NG_NUM");
			System.out.println("taskNg:" + taskNGNum);
//			Integer taskreworkNum = (Integer)mapx.get("REWORK_NUM");
			taskNGNum = taskNGNum + Integer.parseInt(NGNum);//任务单上的NG数量为各个工位NG数量的总和（因为只有下道工序只接收可用，所以下道工序的NG，一定是新的NG数量）
			System.out.println("taskNGNum:" + taskNGNum);

			service.updateProcessTaskInfo(taskNGNum, null, taskId);
			String qualityInspector = request.getParameter("qualityInspector");
			String processNamex = service.showProcessName(Integer.parseInt(processName));


//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("客户端IP:"+IP);
			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(map.get("PROJECT_NAME")), projectNum, specificationModels, processNamex, OKNum, NGNum, NGReason, qualityInspector, "正常", String.valueOf(map.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), processName, logInfo, "品检");

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}

	}


	@RequestMapping(value = "/showprocessType", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序类型", notes = "查询工序类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工序ID", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showprocessType(HttpServletRequest request) throws ServicesException {
		Integer id = null;
		try {

			id = Integer.parseInt(request.getParameter("id"));
			Integer type = service.showprocessType(id);

			return Rjson.success(type);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateReworkProcessTaskInfo", method = RequestMethod.POST)
	@ApiOperation(value = "更新任务单信息(返修检测)", notes = "更新任务单信息(返修检测)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "qualityType", value = "工序类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "OKNum", value = "合格数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "NG数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGReason", value = "NG原因", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson updateReworkProcessTaskInfo(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {

//			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");
			String qualityType = request.getParameter("qualityType");
			String OKNum = request.getParameter("OKNum");
			String NGNum = request.getParameter("NGNum");
			String NGReason = request.getParameter("NGReason");
//			String userName = request.getParameter("userName");
			String userName = ToolUtils.getCookieUserName(request);
			if (NGReason.equals("")) {
				NGReason = "无";
			}
//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");
			if ((Integer) map.get("STATUS_FLAGS") == 0) {
				return Rjson.error(202, "生产任务未开始，无法录入返修品检数量");
			}
			Integer countNowNum = null;
			Integer countOldNum = null;
			countOldNum = (Integer) map.get("NG_NUM");//等于待检测数量testingNum

			if (!OKNum.equals("") && !NGNum.equals("")) {
				countNowNum = Integer.parseInt(OKNum) + Integer.parseInt(NGNum);
			}
			if (OKNum.equals("") && !NGNum.equals("")) {
				countNowNum = Integer.parseInt(NGNum);
				OKNum = "0";
			}
			if (!OKNum.equals("") && NGNum.equals("")) {
				countNowNum = Integer.parseInt(OKNum);
				NGNum = "0";
			}
			if (OKNum.equals("") && NGNum.equals("")) {
				countNowNum = 0;
				OKNum = "0";
				NGNum = "0";
			}
			if (countOldNum < countNowNum) {
				return Rjson.error(202, "当前工序返修数量不能大于存在NG数量");
			}
			Integer detailsId = (Integer) map.get("DETAILS_ID");
			Integer taskId = (Integer) map.get("TASK_ID");
			Integer OKNums = null;
			Integer NGNums = null;
			Integer reworkNums = null;

			reworkNums = (Integer) map.get("REWORK_NUM") + Integer.parseInt(OKNum);//旧返修+新返修
//			if(reworkNums>countOldNum){
//				return Rjson.error(202, "返修数量不能大于NG数量");
//			}
			NGNums = (Integer) map.get("NG_NUM") - Integer.parseInt(OKNum);//ng=ng-返修数量
			OKNums = (Integer) map.get("USEABLE_NUM") + Integer.parseInt(OKNum);//新的可用数量（返修数量+旧的可用数量）

//			Integer delTestingNum = (Integer)map.get("TESTING_NUM");
//			delTestingNum = delTestingNum-countNowNum;//旧的待测试数量-目前检测的数量
//			if(delTestingNum<0){
//				return Rjson.error(202, "品检数量不能大于待品检数量");
//			}

			service.updateReworksDetailsUseable(OKNums, taskId, detailsId, reworkNums, NGNums);

			Map<String, Object> mapx = service.showProcessTaskInfos(taskId);
//			Integer taskNGNum = (Integer)mapx.get("NG_NUM");
			Integer taskreworkNum = (Integer) mapx.get("REWORK_NUM") + Integer.parseInt(OKNum);
			Integer taskNGNum = (Integer) mapx.get("NG_NUM");
			taskNGNum = taskNGNum - Integer.parseInt(OKNum);
			service.updateProcessTaskInfo(taskNGNum, taskreworkNum, taskId);
//			System.out.println("123:"+map.get("NG_NUM"));


			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("客户端IP:"+IP);
			String qualityInspector = request.getParameter("qualityInspector");
			String processNamex = service.showProcessName(Integer.parseInt(processName));
			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(map.get("PROJECT_NAME")), projectNum, specificationModels, processNamex, OKNum, NGNum, NGReason, qualityInspector, "返修", String.valueOf(map.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), processName, logInfo, "品检");
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}

	}


	@RequestMapping(value = "/limitQualityDialog", method = RequestMethod.POST)
	@ApiOperation(value = "校验通过后出品质检验单Dialog", notes = "校验通过后出品质检验单Dialog")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson limitQualityDialog(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");
			String qualityType = request.getParameter("qualityType");
//			String userName = request.getParameter("userName");

//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");

			Integer testingNum = (Integer) map.get("TESTING_NUM");//待检测数量
			if (qualityType.equals("1") || qualityType.equals("0")) {
				if (testingNum <= 0) {
					return Rjson.error(202, "当前工序不存在待品检数量，不支持品检");
				}
			}

			Integer ngNum = (Integer) map.get("NG_NUM");
			if (qualityType.equals("2")) {
				if (ngNum <= 0) {
					return Rjson.error(202, "当前工序不存在NG数量,不支持返修");
				}
			}

			return Rjson.success(map);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/limitCuttingDialog", method = RequestMethod.POST)
	@ApiOperation(value = "校验通过后出下推Dialog", notes = "校验通过后出下推Dialog")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson limitCuttingDialog(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");

//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");

			Integer useableNum = (Integer) map.get("USEABLE_NUM");//可用数量

			if (useableNum <= 0) {
				return Rjson.error(202, "当前工序无可用数量，请确认后联系品检员");
			}

			return Rjson.success(map);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/limitOutSourcingDialog", method = RequestMethod.POST)
	@ApiOperation(value = "校验通过后出下推委外发出Dialog", notes = "校验通过后出下推委外发出Dialog")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson limitOutSourcingDialog(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");
//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");

			Integer testingNum = (Integer) map.get("TESTING_NUM");//可用数量

			if (testingNum <= 0) {
				return Rjson.error(202, "当前工序无可用于发出的委外数量，请确认后查看生产任务待品检数量");
			}

			if (testingNum == (Integer) map.get("OUTSOURCE_OUT_NUM")) {
				return Rjson.error(202, "当前工序无可用于发出的委外数量，请确认后查看生产任务待品检数量");
			}

			return Rjson.success(map);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateOutSourcingInfo", method = RequestMethod.POST)
	@ApiOperation(value = "更新委外发出", notes = "更新委外发出")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "OKNum", value = "下推数量", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson updateOutSourcingInfo(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String specificationModels = request.getParameter("specificationModel");
			String processName = request.getParameter("processName");
			Integer OKNum = Integer.parseInt(request.getParameter("OKNum"));
//			String userName = request.getParameter("userName");
			String userName = ToolUtils.getCookieUserName(request);

//			当前工序信息
			Map<String, Object> map = service.showTaskList(projectNum, specificationModels, processName, "", "");

			Integer testingNum = (Integer) map.get("TESTING_NUM");//可委外数量
			Integer oldOutNum = (Integer) map.get("OUTSOURCE_OUT_NUM");//旧有的委外发出数量
			Integer taskId = (Integer) map.get("TASK_ID");

			oldOutNum = oldOutNum + OKNum;//新的委外发出数量(旧的委外发出+新的委外发出)
			if (oldOutNum > testingNum) {
				return Rjson.error(202, "委外发出数量不能超过待品检数量");
			}
//			更新委外数量

			if (testingNum <= 0) {
				return Rjson.error(202, "当前工序无可用于委外发出的数量，请确认后查看生产任务待品检数量");
			}

			service.updateOutNums(oldOutNum, Integer.parseInt(processName), taskId);//更新委外发出数量


			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			String qualityInspector = request.getParameter("qualityInspector");
			String processNamex = service.showProcessName(Integer.parseInt(processName));
			ProcessLogModel model = new ProcessLogModel();
			String logInfo = model.pushDownLogInfo(userName, ipInfo, processNamex, projectNum, specificationModels, processNamex, String.valueOf(OKNum), qualityInspector, "委外发出", "", "");
			logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), processName, logInfo, "委外发出");

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updatePushDownTask", method = RequestMethod.POST)
	@ApiOperation(value = "更新下推数据", notes = "更新下推数据")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModels", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "OKNum", value = "下推数量", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson updatePushDownTask(HttpServletRequest request) throws ServicesException {
		try {
			String projectNumxl = request.getParameter("projectNum");
			String specificationModelsxl = request.getParameter("specificationModel");
			String processNamexl = request.getParameter("processName");
			String stationNumxl = request.getParameter("stationNum");
			String meterialCodexl = request.getParameter("meterialCode");
			Integer OKNumxl = Integer.parseInt(request.getParameter("OKNum"));
//			String userName = request.getParameter("userName");
			String userNamexl = ToolUtils.getCookieUserName(request);
//			当前工序信息？？？
			Map<String, Object> mapxl = service.showTaskList(projectNumxl, specificationModelsxl, processNamexl, meterialCodexl, stationNumxl);
			String flagsxl = mapxl.get("STATUS_FLAGS").toString();
			if (flagsxl.equals("0")) {
				return Rjson.error(202, "生产任务为开始状态无法下推");
			} else if (flagsxl.equals("2")) {
				return Rjson.error(202, "生产任务为暂停状态无法下推");
			} else if (flagsxl.equals("3")) {
				return Rjson.error(202, "生产任务为完成状态无法下推");
			}

			Integer useableNumxl = (Integer) mapxl.get("USEABLE_NUM");//旧有可用数量

			if (useableNumxl < OKNumxl) {
				return Rjson.error(202, "下推数量不能超过可用数量");
			}
			Integer completeNumxl = (Integer) mapxl.get("COMPLETE_NUM");//旧有完成数量
			completeNumxl = completeNumxl + OKNumxl;//现有完成数量
			useableNumxl = useableNumxl - OKNumxl;//现有可用数量

//			System.out.println("abc:"+(Integer)map.get("ORDER_NUM"));
//			System.out.println("abc:"+Integer.parseInt(map.get("ORDER_NUM")));
//			下道工序的待检测数量==上道工序的下推数量
			Integer orderNumxl = null;

			orderNumxl = (Integer) mapxl.get("ORDER_NUM");

			Integer taskIdxl = (Integer) mapxl.get("TASK_ID");
//			求出当前工艺路线最大的ID
			Integer maxOrderxl = service.maxOrder(taskIdxl);

//			1.判断是否为委外做不同的更新2.委外品检减委外发出数量
			Integer processTypeInfosxl = service.showNowProcesstypeInfo(projectNumxl, specificationModelsxl, (Integer) mapxl.get("PROCESS_ID"));
			if (orderNumxl == maxOrderxl) {//最后一道工序
//				1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
				if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {//完成数量==计划数量
					service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);

				} else {
					service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
				}
				if (processTypeInfosxl != 3) {//非委外
					service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
					ShowIPInfo ipxl = new ShowIPInfo();
					String ipInfoxl = ipxl.getIpAddr(request);
					String qualityInspectorxl = request.getParameter("qualityInspector");
					String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
					ProcessLogModel modelxl = new ProcessLogModel();
					String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl), qualityInspectorxl, "成品入库", "", stationNumxl);
					logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//				  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//				  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


				} else {//委外
					service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
					//获取客户端IP地址
					ShowIPInfo ipxl = new ShowIPInfo();
					String ipInfoxl = ipxl.getIpAddr(request);
					String qualityInspectorxl = request.getParameter("qualityInspector");
					String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
					ProcessLogModel modelxl = new ProcessLogModel();
					String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl), qualityInspectorxl, "成品入库", "", stationNumxl);
					logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");

				}

				if (completeNumxl == (Integer) mapxl.get("PLAN_NUM")) {
					ShowIPInfo ipxl = new ShowIPInfo();
					String ipInfoxl = ipxl.getIpAddr(request);
					String qualityInspectorxl = request.getParameter("qualityInspector");
					String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
					ProcessLogModel modelxl = new ProcessLogModel();
					String logInfoxl = modelxl.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null, null, projectNumxl, specificationModelsxl, null, null, null, stationNumxl);
					logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxl, "生产任务");
				}

			} else if (orderNumxl < maxOrderxl) {//中间工序或者第一道工序
				if (processTypeInfosxl != 3) {//非委外
					service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl);//更新当前工序的完成数量与可用数量
					//获取客户端IP地址
					ShowIPInfo ipxl = new ShowIPInfo();
					String ipInfoxl = ipxl.getIpAddr(request);
					String qualityInspectorxl = request.getParameter("qualityInspector");
					String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
					ProcessLogModel modelxl = new ProcessLogModel();
					String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "正常", "", stationNumxl);
					logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

				} else {//委外
					service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl), taskIdxl, completeNumxl);
					//获取客户端IP地址
					ShowIPInfo ipxl = new ShowIPInfo();
					String ipInfoxl = ipxl.getIpAddr(request);
					String qualityInspectorxl = request.getParameter("qualityInspector");
					String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
					ProcessLogModel modelxl = new ProcessLogModel();
					String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl), qualityInspectorxl, "委外接收", "", stationNumxl);
					logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "委外接收");
				}


				Integer nextProcessOrderxl = orderNumxl + 1;


				//根据生产任务与序号查找下一道工序
//				获取下道工序主分支数据   获取task数据   判断工序类型

				Map<String, Object> showNextProcessMapxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
				Integer testingNumxl = (Integer) showNextProcessMapxl.get("TESTING_NUM");
				Map<String, Object> showNextProcessMapxxl = service.showNextProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
				Map<String, Object> showNextProcessMapxsxl = service.showTaskById(String.valueOf(taskIdxl));
				Integer processTypeInfoxl = service.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsxl.get("PROJECT_NUM")), String.valueOf(showNextProcessMapxsxl.get("SPECIFICATION_AND_MODEL")), Integer.parseInt(String.valueOf(showNextProcessMapxxl.get("PROCESS_ID"))));
				if (processTypeInfoxl == 3) {
					testingNumxl = testingNumxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
					if (testingNumxl > (Integer) showNextProcessMapxl.get("PLAN_NUM")) {
						return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
					}

					Map<String, Object> mapdxl = service.showTaskById(String.valueOf(taskIdxl));
					Integer planNumsxl = Integer.parseInt(String.valueOf(mapxl.get("PLAN_NUM")));
//						Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
					Integer nextOutNumxl = null;
//						初始品检临时数量都为计划数量，下推需要变成真实数量就需要减去计划数量（TESTING_NUM_TEM），第一道不用减，下推需要减去
					if (Integer.parseInt(String.valueOf(showNextProcessMapxl.get("PROCESS_ORDER"))) > 1) {
						if (Integer.parseInt((String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")))) >= planNumsxl) {
							nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM"))) - planNumsxl;
						} else {
							nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
						}

					} else {
						nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl))) + Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
					}

					//				更新下道工序信息(待检测数量)
					service.updateNextProcessInfo(testingNumxl, (Integer) taskIdxl, nextProcessOrderxl, nextOutNumxl);
					service.updateNextChildProcessInfo((Integer) taskIdxl, nextProcessOrderxl);
				} else {
					//根据生产任务与序号查找下一道工序
					Map<String, Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
					Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());

					Integer testingNumxsxl = (Integer) showNextProcessMapsxl.get("TESTING_NUM");
					testingNumxsxl = testingNumxsxl + Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
					if (testingNumxsxl > (Integer) showNextProcessMapsxl.get("PLAN_NUM")) {
						return Rjson.error(202, "请检查工序流转，待检测数量不能大于计划数量");
					}
					pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));


//						Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//						if(processTypeInfosx!=3){
					Integer nextOutNumxl = testingNumxsxl - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM"))) - Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));

					//				更新下道工序信息(待检测数量)
					service.updateNextProcessInfox(testingNumxsxl, (Integer) taskIdxl, nextProcessOrderxl, nextOutNumxl, pushDownNum.toString());
				}


//				Integer nextProcessOrder = orderNum+1;
////				根据生产任务与序号查找下一道工序
//				Map<String,Object> showNextProcessMap = service.showNextProcessInfo(taskId, nextProcessOrder);
//				Integer testingNum = (Integer)showNextProcessMap.get("TESTING_NUM");
//				testingNum=testingNum+OKNum;//旧有的待测试数量+上道工序的下推数量
//				if(testingNum>(Integer)showNextProcessMap.get("PLAN_NUM")){
//					 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//				}
//				Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//				service.updateNextProcessInfox(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//


//				Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//				if(processTypeInfosx!=3){


				//				更新下道工序信息(待检测数量)

				//				更新下道工序信息(待检测数量)
//					service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
////				}else{
//					Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//
//					//				更新下道工序信息(待检测数量)
//					service.updateNextProcessInfo(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//					//				更新下道工序信息(待检测数量)
////					service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
//				}
//


			} else if (orderNumxl > maxOrderxl) {//异常工序
				return Rjson.error(202, "工艺路线序号异常，请联系工艺部");
			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


}
