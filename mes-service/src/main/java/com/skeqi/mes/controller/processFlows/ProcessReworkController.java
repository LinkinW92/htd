package com.skeqi.mes.controller.processFlows;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ProcessLogModel;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.processFlows.ProcessClientService;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.service.processFlows.ProcessReworkService;
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
@Api(value = "返修申请成功管理", description = "返修申请成功管理", produces = MediaType.APPLICATION_JSON)
public class ProcessReworkController {

	@Autowired
	private ProcessClientService services;

	@Autowired
	private ProcessReworkService service;

	@Autowired
	private ProcessLogInfoService logInfos;



	@RequestMapping(value = "/showProcessReworkInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示返修成功申请信息", notes = "展示返修成功申请信息")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showProcessReworkInfo(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String stationNum =request.getParameter("stationNum");
			String materiCode =request.getParameter("materiCode");
			String projectNum =request.getParameter("projectNum");
			String specificationModel =request.getParameter("specificationModel");
			list = service.showAllReworkInfo(stationNum,materiCode,projectNum,specificationModel);
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showProcessReworkInfox", method = RequestMethod.POST)
	@ApiOperation(value = "展示返修成功申请信息", notes = "展示返修成功申请信息")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showProcessReworkInfox(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String stationNum =request.getParameter("stationNum");
			String materiCode =request.getParameter("materiCode");
			String projectNum =request.getParameter("projectNum");
			String specificationModel =request.getParameter("specificationModel");
			list = service.showAllReworkInfox(stationNum,materiCode,projectNum,specificationModel);
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



	@RequestMapping(value = "/updateProcessReworkInfo", method = RequestMethod.POST)
	@ApiOperation(value = "更新返修成功申请信息", notes = "更新返修成功申请信息")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateProcessReworkInfo(HttpServletRequest request,@RequestBody String info) throws ServicesException {

		try {
			String person  = request.getParameter("person");
			JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Object taskId = jsonObject.get("taskId");
				Object processId = jsonObject.get("processId");
				Object reworkNum = jsonObject.get("reworkNum");
				Object type = jsonObject.get("type");
				Object id = jsonObject.get("id");

				Map<String,Object> map = service.showProcessDetailsInfo(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//获取指定数据
				Integer useReworkNums = Integer.parseInt(String.valueOf(map.get("USE_REWORK_NUM")));
				useReworkNums = useReworkNums - Integer.parseInt(String.valueOf(reworkNum));
				String newReworks = String.valueOf(map.get("USE_REWORK_NUM_TEM_APPROVAL"));
				if(newReworks.equals("")||newReworks.equals("null")||newReworks==null){
					newReworks="0";
				}
				Integer newReworksx = Integer.parseInt(newReworks)+Integer.parseInt(String.valueOf(reworkNum));

				String personx = String.valueOf(map.get("USE_REWORK_NUM_PERSON"));
				if(personx.equals("null")||personx==null){
					personx="";
				}
				personx = person+"//"+personx;

//				两种方案：1返修后返回为供应商记录的记录  2返修后回归到主分支记录(方便供应商调换)
				service.updateReworkInfo(String.valueOf(newReworksx), String.valueOf(useReworkNums), String.valueOf(taskId), String.valueOf(processId),personx,String.valueOf(id));
//				更新委外任务数据
				service.updateApplyReworkInsideNum(newReworksx.toString(), taskId.toString(), processId.toString(), id.toString());

//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
				Map<String,Object> mapx = services.showTaskById(String.valueOf(taskId));
				String stationName = services.showStationName(String.valueOf(processId));

			  ProcessLogModel model = new ProcessLogModel();
			  String logInfo =model.qualityLogInfo(userName, ipInfo,  String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(reworkNum), "", "", personx, "返修申请", String.valueOf(mapx.get("STATION_NUM")));
//			  String logInfo =model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), "", "", "", "品检撤销");

//			  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			  System.out.println("log:"+logInfo);
			  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfo, "返修申请");

			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updateProcessReworkInfoTask", method = RequestMethod.POST)
	@ApiOperation(value = "更新委外任务返修成功申请信息", notes = "更新委外任务返修成功申请信息")
	@ApiImplicitParams({

	})
	@ResponseBody
	public Rjson updateProcessReworkInfoTask(HttpServletRequest request,@RequestBody String info) throws ServicesException {

		try {
//			String person  = request.getParameter("person");
//			JSONArray jsonArray=JSONArray.fromObject(info);
//			for (int i = 0; i < jsonArray.size(); i++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(i);
//				Object taskId = jsonObject.get("taskId");
//				Object processId = jsonObject.get("processId");
//				Object reworkNum = jsonObject.get("reworkNum");
//				Object type = jsonObject.get("type");
//				Object id = jsonObject.get("id");
			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String reworkNum = request.getParameter("reworkNum");
			String id = request.getParameter("id");
			String type ="3";

				Map<String,Object> map = service.showProcessDetailsInfo(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//获取指定数据
				Integer useReworkNums = Integer.parseInt(String.valueOf(map.get("USE_REWORK_NUM")));
				useReworkNums = useReworkNums - Integer.parseInt(String.valueOf(reworkNum));
				String newReworks = String.valueOf(map.get("USE_REWORK_NUM_TEM_APPROVAL"));
				if(newReworks.equals("")||newReworks.equals("null")||newReworks==null){
					newReworks="0";
				}
				Integer newReworksx = Integer.parseInt(newReworks)+Integer.parseInt(String.valueOf(reworkNum));

				String personx = String.valueOf(map.get("USE_REWORK_NUM_PERSON"));
				if(personx.equals("null")||personx==null){
					personx="";
				}
				personx = person+"//"+personx;

//				两种方案：1返修后返回为供应商记录的记录  2返修后回归到主分支记录(方便供应商调换)
				service.updateReworkInfo(String.valueOf(newReworksx), String.valueOf(useReworkNums), String.valueOf(taskId), String.valueOf(processId),personx,String.valueOf(id));
//				更新委外任务数据
				service.updateApplyReworkInsideNum(newReworksx.toString(), taskId.toString(), processId.toString(), id.toString());

//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息
				Map<String,Object> mapx = services.showTaskById(String.valueOf(taskId));
				String stationName = services.showStationName(String.valueOf(processId));

			  ProcessLogModel model = new ProcessLogModel();
			  String logInfo =model.qualityLogInfo(userName, ipInfo,  String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(reworkNum), "", "", personx, "返修申请", String.valueOf(mapx.get("STATION_NUM")));
//			  String logInfo =model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), "", "", "", "品检撤销");

//			  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
			  System.out.println("log:"+logInfo);
			  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfo, "返修申请");

//			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/revockProcessReworkInfo", method = RequestMethod.POST)
	@ApiOperation(value = "撤销返修成功申请信息", notes = "撤销返修成功申请信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "taskId", value = "任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "返修数据", required = false, paramType = "query", dataType = "string"),

	})
	@ResponseBody
	public Rjson revockProcessReworkInfo(HttpServletRequest request) throws ServicesException {

		try {
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String reworkNum = request.getParameter("reworkNum");
			String id = request.getParameter("id");
			Map<String,Object> map = service.showProcessDetailsInfo(String.valueOf(taskId), String.valueOf(processId),id);//获取指定数据
			String reworkNumOne = String.valueOf(map.get("USE_REWORK_NUM"));
			if(reworkNumOne.equals("")||reworkNumOne.equals("null")||reworkNumOne==null){
				reworkNumOne="0";
			}
			Integer useReworkNums = Integer.parseInt(reworkNumOne);
			useReworkNums = useReworkNums + Integer.parseInt(String.valueOf(reworkNum));
			service.revockReworkInfo(String.valueOf(useReworkNums), taskId, processId,id);
//			更新委外任务
			service.updateRevockReworkInsideNum(taskId, processId, id);

//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息
			Map<String,Object> mapx = services.showTaskById(String.valueOf(taskId));
			String stationName = services.showStationName(String.valueOf(processId));

		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo =model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(reworkNum), "", "", "", "返修撤销", String.valueOf(mapx.get("STATION_NUM")));

//		  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfo, "返修撤销");

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateProcessReworkApprovalInfo", method = RequestMethod.POST)
	@ApiOperation(value = "更新返修成功审核信息", notes = "更新返修成功审核信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "taskId", value = "任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "返修数据", required = false, paramType = "query", dataType = "string"),

	})
	@ResponseBody
	public Rjson updateProcessReworkApprovalInfo(HttpServletRequest request,@RequestBody String info) throws ServicesException {

		try {
			String person = request.getParameter("person");
			JSONArray jsonArray=JSONArray.fromObject(info);
			for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
//			String taskId = request.getParameter("taskId");
//			String processId = request.getParameter("processId");
//			String reworkNum = request.getParameter("reworkNum");
			Object taskId = jsonObject.get("taskId");
			Object processId = jsonObject.get("processId");
			Object reworkNum = jsonObject.get("reworkNum");
			Object type = jsonObject.get("type");
			Object id = jsonObject.get("id");

			Map<String,Object> map = service.showProcessDetailsInfo(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//获取指定数据
			Map<String,Object> maps = service.showTaskById(String.valueOf(taskId));//获取指定数据
			Integer OKNum = null;
			Integer NGNum = null;
			Integer reworkNums = null;

			if(String.valueOf(map.get("USEABLE_NUM")).equals("")||String.valueOf(map.get("USEABLE_NUM")).equals("null")||String.valueOf(map.get("USEABLE_NUM"))==null){
				OKNum=0;
			}else{
				OKNum = Integer.parseInt(String.valueOf(map.get("USEABLE_NUM")));
			}

			if(String.valueOf(map.get("NG_NUM")).equals("")||String.valueOf(map.get("NG_NUM")).equals("null")||String.valueOf(map.get("NG_NUM"))==null){
				NGNum=0;
			}else{
				NGNum = Integer.parseInt(String.valueOf(map.get("NG_NUM")));
			}

			if(String.valueOf(map.get("REWORK_NUM")).equals("")||String.valueOf(map.get("REWORK_NUM")).equals("null")||String.valueOf(map.get("REWORK_NUM"))==null){
				reworkNums=0;
			}else{
				reworkNums = Integer.parseInt(String.valueOf(map.get("REWORK_NUM")));
			}

			Integer OKNumxl = Integer.parseInt(reworkNum.toString());
			String  OKNumxlxl = reworkNum.toString();

			OKNum=OKNum+Integer.parseInt(String.valueOf(reworkNum));
			System.out.println("OKNum:"+OKNum);
			System.out.println("reworkNum:"+reworkNum);
			NGNum=NGNum-Integer.parseInt(String.valueOf(reworkNum));
			reworkNums=reworkNums+Integer.parseInt(String.valueOf(reworkNum));
			System.out.println("NGNum:"+NGNum);
//			委外数量累加

			Integer outOutsourceTem = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEM")));//加上INSOURCE_IN_TEMS
			Integer okFinishProductionTems = Integer.parseInt(String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS")));//加FINISH_PRODUCT_TEM

			outOutsourceTem=outOutsourceTem+Integer.parseInt(String.valueOf(reworkNum));
			okFinishProductionTems=okFinishProductionTems+Integer.parseInt(String.valueOf(reworkNum));

			if(String.valueOf(type).equals("3")){//委外+OK_OUTSOURCE_TEM
				service.updateReworkApprovalDatas(String.valueOf(OKNum), String.valueOf(NGNum), String.valueOf(reworkNums), String.valueOf(taskId), String.valueOf(processId),person,String.valueOf(outOutsourceTem),String.valueOf(okFinishProductionTems),String.valueOf(id));

				Map<String,Object> mapx = services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer applyNum = Integer.parseInt(mapx.get("REWORK_APPLY_NUM").toString());
				applyNum = applyNum-(Integer)applyNum;
				Integer okNum = Integer.parseInt(mapx.get("OK_NUM").toString());
				okNum = okNum+reworkNums;
				service.updateReworkExamineInsideNum(String.valueOf(NGNum),  String.valueOf(reworkNums), String.valueOf(applyNum), taskId.toString(), processId.toString(), id.toString(),okNum.toString());
				Map<String,Object> mapxx = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
				Integer reworkNumxx = Integer.parseInt(mapxx.get("REWORK_NUM").toString());
				reworkNumxx = reworkNumxx + Integer.parseInt(reworkNum.toString());

				Map<String,Object> mapxyx = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
				Integer okNumxyx = Integer.parseInt(mapxyx.get("USEABLE_NUM").toString());
				Integer ngNumxyx = Integer.parseInt(mapxyx.get("NG_NUM").toString());
				okNumxyx=okNumxyx+Integer.parseInt(reworkNum.toString());
				ngNumxyx=ngNumxyx-Integer.parseInt(reworkNum.toString());

				service.updateReworkNumxx(reworkNumxx.toString(), taskId.toString(), processId.toString(),ngNumxyx.toString(),okNumxyx.toString());
//				service.updateReworkNumxx(reworkNumxx.toString(), taskId.toString(), processId.toString());

			}else{//非委外不+OK_OUTSOURCE_TEM
				service.updateReworkApprovalDatasx(String.valueOf(OKNum), String.valueOf(NGNum), String.valueOf(reworkNums), String.valueOf(taskId), String.valueOf(processId),person,String.valueOf(outOutsourceTem),String.valueOf(okFinishProductionTems));



			}

			Map<String,Object> mapxs = service.showTaskById(String.valueOf(taskId));
			Integer ngsNum = Integer.parseInt(String.valueOf(mapxs.get("NG_NUM")));
			ngsNum=ngsNum-Integer.parseInt(String.valueOf(reworkNum));

			service.updateTaskNGNum(String.valueOf(ngsNum),String.valueOf(taskId));

//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息
			Map<String,Object> mapx = services.showTaskById(String.valueOf(taskId));
			String stationName = services.showStationName(String.valueOf(processId));

		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo =model.qualityLogInfo(userName, ipInfo,  String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(reworkNum), "", "", person, "返修审核", String.valueOf(mapx.get("STATION_NUM")));
//		  String logInfo =model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), "", "", "", "品检撤销");

//		  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfo, "返修审核");


//		  =================普通下推逻辑代码部分================
		 Map<String,Object> showTaskByIdxx = service.showTaskById(taskId.toString());
		String projectNumxl=  showTaskByIdxx.get("PROJECT_NUM").toString();
		String specificationModelsxl=  showTaskByIdxx.get("SPECIFICATION_AND_MODEL").toString();
		String meterialCodexl=  showTaskByIdxx.get("MATERIAL_CODE").toString();
		String stationNumxl=  showTaskByIdxx.get("STATION_NUM").toString();
		String processNamexl=  map.get("PROCESS_ID").toString();
		String userNamexl = ToolUtils.getCookieUserName(request);


		  Map<String,Object> mapxl=null;
		 if(!String.valueOf(type).equals("3")){
				   mapxl= services.showTaskList(projectNumxl, specificationModelsxl,processNamexl,meterialCodexl,stationNumxl);

		 }
		  if((!String.valueOf(type).equals("3")) && (Integer.parseInt(mapxl.get("USEABLE_NUM").toString())>0)){

					String flagsxl = mapxl.get("STATUS_FLAGS").toString();
					if(flagsxl.equals("0")){
						return Rjson.error(202,"生产任务为开始状态无法下推");
					}else if(flagsxl.equals("2")){
						return Rjson.error(202,"生产任务为暂停状态无法下推");
					}else if(flagsxl.equals("3")){
						return Rjson.error(202,"生产任务为完成状态无法下推");
					}

					Integer useableNumxl = (Integer)mapxl.get("USEABLE_NUM");//旧有可用数量

					if(useableNumxl<OKNumxl){
						 return Rjson.error(202,"下推数量不能超过可用数量");
					}
					Integer completeNumxl = (Integer)mapxl.get("COMPLETE_NUM");//旧有完成数量
					completeNumxl = completeNumxl+OKNumxl;//现有完成数量
					useableNumxl=useableNumxl-OKNumxl;//现有可用数量

//					System.out.println("abc:"+(Integer)map.get("ORDER_NUM"));
//					System.out.println("abc:"+Integer.parseInt(map.get("ORDER_NUM")));
//					下道工序的待检测数量==上道工序的下推数量
					Integer orderNumxl =null;

					orderNumxl=(Integer)mapxl.get("ORDER_NUM");

					Integer taskIdxl =(Integer)mapxl.get("TASK_ID");
//					求出当前工艺路线最大的ID
					Integer maxOrderxl = services.maxOrder(taskIdxl);

//					1.判断是否为委外做不同的更新2.委外品检减委外发出数量
					Integer processTypeInfosxl= services.showNowProcesstypeInfo(projectNumxl, specificationModelsxl, (Integer)mapxl.get("PROCESS_ID"));
					if(orderNumxl==maxOrderxl){
//						if(1==0){
						//最后一道工序
//						1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
						if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
							services.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);

						}else{
							services.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
						}
						if(processTypeInfosxl!=3){//非委外
							services.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//						  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//						  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


						}else{//委外
							services.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
							//获取客户端IP地址
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");

						}

						if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = services.showProcessName(Integer.parseInt(processNamexl));
						  ProcessLogModel modelxl = new ProcessLogModel();
						  String logInfoxl = modelxl.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxl, "生产任务");
						}
//						}

					}else if(orderNumxl<maxOrderxl){//中间工序或者第一道工序
						if(processTypeInfosxl!=3){//非委外
							services.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量
//							//获取客户端IP地址
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"正常","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

						}else{//委外
							services.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
							//获取客户端IP地址
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//							String qualityInspectorxl = request.getParameter("qualityInspector");
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = services.showProcessName(Integer.parseInt(processNamexl));
						  ProcessLogModel modelxl = new ProcessLogModel();
						  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"委外接收","",stationNumxl);
						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "委外接收");
						}



						Integer nextProcessOrderxl = orderNumxl+1;


							//根据生产任务与序号查找下一道工序
//						获取下道工序主分支数据   获取task数据   判断工序类型

							Map<String,Object> showNextProcessMapxl = services.showNextProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							Integer testingNumxl = (Integer)showNextProcessMapxl.get("TESTING_NUM");
							Map<String,Object> showNextProcessMapxxl = services.showNextProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							Map<String,Object> showNextProcessMapxsxl = service.showTaskById(String.valueOf(taskIdxl));
							Integer processTypeInfoxl= services.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsxl.get("PROJECT_NUM")),String.valueOf(showNextProcessMapxsxl.get("SPECIFICATION_AND_MODEL")) , Integer.parseInt(String.valueOf(showNextProcessMapxxl.get("PROCESS_ID"))));
							if(processTypeInfoxl==3){//委外
								testingNumxl=testingNumxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if(testingNumxl>(Integer)showNextProcessMapxl.get("PLAN_NUM")){
									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
								}

								Map<String,Object> mapdxl = service.showTaskById(String.valueOf(taskIdxl));
								Integer planNumsxl  = Integer.parseInt(String.valueOf(mapxl.get("PLAN_NUM")));
//								Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
								Integer nextOutNumxl =null;
//								初始品检临时数量都为计划数量，下推需要变成真实数量就需要减去计划数量（TESTING_NUM_TEM），第一道不用减，下推需要减去
								if(Integer.parseInt(String.valueOf(showNextProcessMapxl.get("PROCESS_ORDER")))>1){
									if(Integer.parseInt((String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM"))))>=planNumsxl){
										nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")))-planNumsxl;
									}else{
										nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
									}

								}else{
							        nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
								}

								//				更新下道工序信息(待检测数量)
								services.updateNextProcessInfo(testingNumxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
								services.updateNextChildProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							}else{//非委外


									Map<String,Object> showNextProcessMapsxl = services.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
									Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());
									pushDownNum= pushDownNum+Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
									services.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextProcessOrderxl.toString());//更新下推数量
									Integer maxNum = services.maxOrder(taskIdxl);
									//如果下一道工序是成平入库
									if(nextProcessOrderxl==maxNum){

										Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
										testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
										if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
											 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
										}
//
//											Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//										service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl,"0");//
										String okFinishNum = showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS")==null?"0":showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS").toString();
										Integer finishNum = Integer.parseInt(okFinishNum);
										finishNum = finishNum+Integer.parseInt(String.valueOf(OKNumxl));
										services.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextProcessOrderxl.toString());


										completeNumxl= Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString())+OKNumxl;
										useableNumxl = Integer.parseInt(showNextProcessMapsxl.get("USEABLE_NUM").toString())-OKNumxl;


										//最后一道工序
//										1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//										if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
//											service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);
//
//										}else{
//											service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
//										}
//										if(processTypeInfosxl!=3){
											//非委外
//											service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量   4.0
//										更新一下，useName4.0
										Map<String,Object> showNextProcessMapxlxs = services.showNextProcessInfo((Integer)taskIdxl, orderNumxl);
									    Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
									    Integer useNumxx = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString());//最后一道工序、
									    useNumxx = complete-useNumxx;//成品入库的可用数量
									    services.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextProcessOrderxl.toString());

//											ShowIPInfo ipxl = new ShowIPInfo();
//											String ipInfoxl = ipxl.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
//											String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//											String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxl = new ProcessLogModel();
//										  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//										  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//										  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//										}
//										else{//委外
//											service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//											//获取客户端IP地址
//											ShowIPInfo ipxl = new ShowIPInfo();
//											String ipInfoxl = ipxl.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
//											String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//											String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxl = new ProcessLogModel();
//										  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//										}

//										if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
//											ShowIPInfo ipxlx = new ShowIPInfo();
//											String ipInfoxlx = ipxlx.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
////											String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//											String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//											String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxlx = new ProcessLogModel();
//										  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxlx, "生产任务");
//										}


									}



							}
//							旧的更新品质的方法
//							else{
//								//根据生产任务与序号查找下一道工序
//								Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
//								Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
//								testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
//								if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
//									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//								}
//
//									Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//
//									//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
//							}










//						Integer nextProcessOrder = orderNum+1;
////						根据生产任务与序号查找下一道工序
//						Map<String,Object> showNextProcessMap = service.showNextProcessInfo(taskId, nextProcessOrder);
//						Integer testingNum = (Integer)showNextProcessMap.get("TESTING_NUM");
//						testingNum=testingNum+OKNum;//旧有的待测试数量+上道工序的下推数量
//						if(testingNum>(Integer)showNextProcessMap.get("PLAN_NUM")){
//							 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//						}
//						Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//						service.updateNextProcessInfox(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//








//						Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//						if(processTypeInfosx!=3){


							//				更新下道工序信息(待检测数量)

							//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
////						}else{
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//
//							//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfo(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//							//				更新下道工序信息(待检测数量)
////							service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
//						}
//


//					}
					}else if(orderNumxl>maxOrderxl){//异常工序
						 return Rjson.error(202,"工艺路线序号异常，请联系工艺部");
					}

//					return Rjson.success();
					// }}

			  }


//		  ==============委外接收下推（合并）===============
			  if(String.valueOf(type).equals("3")){

				  type="3";

				  if(String.valueOf(OKNumxlxl).equals("")||String.valueOf(OKNumxlxl)==null){
					  OKNumxlxl="0";
					}

					String okNumxww = String.valueOf(OKNumxlxl);

					Map<String,Object> mapww = services.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//查询工序详情

					String outSourceInNumww = String.valueOf(mapww.get("OUTSOURCE_IN_NUM"));
//					String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
					String completeNumww = String.valueOf(mapww.get("COMPLETE_NUM"));
					String useNumww = String.valueOf(mapww.get("USEABLE_NUM"));
					String planNumww = String.valueOf(mapww.get("PLAN_NUM"));
					Integer orderNumww =(Integer)mapww.get("PROCESS_ORDER");//当前工序的排序
					String insupplerww = String.valueOf(mapww.get("ALL_OUT_SUPPLIER"));
					Integer nextOrderNumww = orderNumww+1;
					if(String.valueOf(("OKNum")).equals("")){
						OKNumxlxl="0";
					}

					Integer completeNumsww = Integer.parseInt(completeNumww)+Integer.parseInt(String.valueOf((OKNumxlxl)));
					System.out.println("完成数量:"+completeNumsww+","+planNumww);
					Integer useNumsww = Integer.parseInt(useNumww)-Integer.parseInt(String.valueOf((OKNumxlxl)));
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
					if(planNumww.equals(completeNumxww)){//全部完成
						services.updateInSourceprocessInfo("11", person, String.valueOf(completeNumsww), String.valueOf(useNumsww),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
						Map<String,Object> mapxww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUM = Integer.parseInt(mapxww.get("OK_NUM").toString());
//					    completeNumsww = mapxww.get("COMPLETE_NUM")==null?0:Integer.parseInt(mapxww.get("COMPLETE_NUM").toString());
//						okNUM = okNUM-completeNumsww;
						okNUM = okNUM-Integer.parseInt(OKNumxlxl.toString());//4.0
//						System.out.println("useNums:"+useNums);
						Integer applyNUM = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUM = applyNUM-Integer.parseInt(OKNumxlxl.toString());
						Integer completeNumswwx = Integer.parseInt((mapxww.get("IN_SIDE_NUM_EXAMINE").toString()));
						completeNumswwx=completeNumswwx+Integer.parseInt(OKNumxlxl.toString());
						services.updateOutsideExamineNums(okNUM.toString(),applyNUM.toString(), completeNumswwx.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String,Object> mapxsww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String,Object> mainMapsww = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer)mainMapsww.get("COMPLETE_NUM");
						completeww=completeww+Integer.parseInt(OKNumxlxl.toString());
						services.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if(planNumsww==examineInsideww){
							services.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
					}else{
						Map<String,Object> mapxww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUMww = Integer.parseInt(mapxww.get("OK_NUM").toString());
						okNUMww = okNUMww-Integer.parseInt(OKNumxlxl.toString());
						System.out.println("useNums:"+useNumsww);
						Integer applyNUMww = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUMww = applyNUMww-Integer.parseInt(OKNumxlxl.toString());
						services.updateInSourceprocessInfo("0", person, String.valueOf(completeNumsww), String.valueOf(useNumsww),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
						services.updateOutsideExamineNums(okNUMww.toString(),applyNUMww.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String,Object> mapxsww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String,Object> mainMapsww = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer)mainMapsww.get("COMPLETE_NUM");
						completeww=completeww+Integer.parseInt(OKNumxlxl.toString());
						services.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if(planNumsww==examineInsideww){
							services.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
						}

//					更新下道工序的待检测数量   nextOrderNum



					if(String.valueOf(type).equals("3")){
						//根据生产任务与序号查找下一道工序

						Map<String,Object> showNextProcessMapww = services.showNextProcessInfo((Integer)taskId, nextOrderNumww);
						Integer testingNumww = (Integer)showNextProcessMapww.get("TESTING_NUM");
						Map<String,Object> showNextProcessMapxww = services.showNextProcessInfo((Integer)taskId, nextOrderNumww);
						Map<String,Object> showNextProcessMapxsww = service.showTaskById(String.valueOf(taskId));
						Integer processTypeInfosww= services.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsww.get("PROJECT_NUM")),String.valueOf(showNextProcessMapxsww.get("SPECIFICATION_AND_MODEL")) , Integer.parseInt(String.valueOf(showNextProcessMapxww.get("PROCESS_ID"))));
						if(processTypeInfosww==3){
							testingNumww=testingNumww+Integer.parseInt(String.valueOf(OKNumxlxl));//旧有的待测试数量+上道工序的下推数量
							if(testingNumww>(Integer)showNextProcessMapww.get("PLAN_NUM")){
								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
							}

							Map<String,Object> mapdww = service.showTaskById(String.valueOf(taskId));
							Integer planNumsww  = Integer.parseInt(String.valueOf(mapww.get("PLAN_NUM")));
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
							Integer nextOutNumww =null;
							if(Integer.parseInt(String.valueOf(showNextProcessMapww.get("PROCESS_ORDER")))>1){
								if(Integer.parseInt((String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM"))))>=planNumsww){
									nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")))-planNumsww;
								}else{
									nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
								}

							}else{
						        nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
							}

							//				更新下道工序信息(待检测数量)
							services.updateNextProcessInfo(testingNumww, (Integer)taskId, nextOrderNumww,nextOutNumww);
						}else{

//							Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
							Map<String,Object> showNextProcessMapsww = services.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
							Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
							pushDownNum= pushDownNum+Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
							services.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextOrderNumww.toString());//更新下推数量
							Integer maxNum = services.maxOrder(Integer.parseInt(taskId.toString()));
							//如果下一道工序是成平入库
							if(nextOrderNumww==maxNum){
								Integer testingNumxsxl = (Integer)showNextProcessMapsww.get("TESTING_NUM");
								testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if(testingNumxsxl>(Integer)showNextProcessMapsww.get("PLAN_NUM")){
									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
								}

//									Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//								service.updateNextProcessInfox(testingNumxsxl, (Integer)taskId, nextOrderNumww,nextOutNumxl,"0");

								String okFinishNum = showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS")==null?"0":showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS").toString();
								Integer finishNum = Integer.parseInt(okFinishNum);
								finishNum = finishNum+Integer.parseInt(String.valueOf(OKNumxl));
								services.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextOrderNumww.toString());


								Integer completeNumxl= Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString())+OKNumxl;
								Integer useableNumxl = Integer.parseInt(showNextProcessMapsww.get("USEABLE_NUM").toString())-OKNumxl;


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
								asx=nextOrderNumww-1;
								if(asx==0){
									asx= nextOrderNumww;
								}
								Map<String,Object> showNextProcessMapxlxs = services.showNextProcessInfo((Integer)taskId, asx);
							    Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
							    Integer useNumxx = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString());//最后一道工序、
							    useNumxx = complete-useNumxx;//成品入库的可用数量
							    services.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextOrderNumww.toString());
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

//						else{
//							//根据生产任务与序号查找下一道工序
//							Map<String,Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
//							Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
//
//							Integer testingNumxsww = (Integer)showNextProcessMapsww.get("TESTING_NUM");
//							testingNumxsww=testingNumxsww+Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
//							if(testingNumxsww>(Integer)showNextProcessMapsww.get("PLAN_NUM")){
//								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//							}
//							pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));
//
////							Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));
//
////							if(processTypeInfosx!=3){
//								Integer nextOutNumww = testingNumxsww-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//
//								//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfox(testingNumxsww, (Integer)taskId, nextOrderNumww,nextOutNumww,pushDownNum.toString());
//						}



					}else{

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
							Map<String,Object> mapxww = service.showTaskById(String.valueOf(taskId));
							String stationNameww = services.showStationName(String.valueOf(processId));

						  ProcessLogModel modelww = new ProcessLogModel();
						  String logInfoww =modelww.pushDownLogInfo(userNameww, ipInfoww, String.valueOf(mapxww.get("PROJECT_NAME")), String.valueOf(mapxww.get("PROJECT_NUM")), String.valueOf(mapxww.get("SPECIFICATION_AND_MODEL")), stationNameww,okNumxww, person, "委外接收审核",insupplerww,String.valueOf(mapxww.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
						  System.out.println("log:"+logInfoww);
						  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfoww, "委外接收审核");



			  }






			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updateProcessReworkApprovalInfoTask", method = RequestMethod.POST)
	@ApiOperation(value = "更新返修成功审核信息", notes = "更新返修成功审核信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "taskId", value = "任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "返修数据", required = false, paramType = "query", dataType = "string"),

	})
	@ResponseBody
	public Rjson updateProcessReworkApprovalInfoTask(HttpServletRequest request,@RequestBody String info) throws ServicesException {

		try {
//			String person = request.getParameter("person");
//			JSONArray jsonArray=JSONArray.fromObject(info);
//			for (int i = 0; i < jsonArray.size(); i++) {
//			JSONObject jsonObject = jsonArray.getJSONObject(i);
////			String taskId = request.getParameter("taskId");
////			String processId = request.getParameter("processId");
////			String reworkNum = request.getParameter("reworkNum");
//			Object taskId = jsonObject.get("taskId");
//			Object processId = jsonObject.get("processId");
//			Object reworkNum = jsonObject.get("reworkNum");
//			Object type = jsonObject.get("type");
//			Object id = jsonObject.get("id");
			String person = request.getParameter("person");
			String taskId = request.getParameter("taskId");
			String processId = request.getParameter("processId");
			String reworkNum = request.getParameter("reworkNum");
			String id = request.getParameter("id");
			String type ="3";


			Map<String,Object> map = service.showProcessDetailsInfo(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//获取指定数据
			Map<String,Object> maps = service.showTaskById(String.valueOf(taskId));//获取指定数据
			Integer OKNum = null;
			Integer NGNum = null;
			Integer reworkNums = null;

			if(String.valueOf(map.get("USEABLE_NUM")).equals("")||String.valueOf(map.get("USEABLE_NUM")).equals("null")||String.valueOf(map.get("USEABLE_NUM"))==null){
				OKNum=0;
			}else{
				OKNum = Integer.parseInt(String.valueOf(map.get("USEABLE_NUM")));
			}

			if(String.valueOf(map.get("NG_NUM")).equals("")||String.valueOf(map.get("NG_NUM")).equals("null")||String.valueOf(map.get("NG_NUM"))==null){
				NGNum=0;
			}else{
				NGNum = Integer.parseInt(String.valueOf(map.get("NG_NUM")));
			}

			if(String.valueOf(map.get("REWORK_NUM")).equals("")||String.valueOf(map.get("REWORK_NUM")).equals("null")||String.valueOf(map.get("REWORK_NUM"))==null){
				reworkNums=0;
			}else{
				reworkNums = Integer.parseInt(String.valueOf(map.get("REWORK_NUM")));
			}

			Integer OKNumxl = Integer.parseInt(reworkNum.toString());
			String  OKNumxlxl = reworkNum.toString();

			OKNum=OKNum+Integer.parseInt(String.valueOf(reworkNum));
			System.out.println("OKNum:"+OKNum);
			System.out.println("reworkNum:"+reworkNum);
			NGNum=NGNum-Integer.parseInt(String.valueOf(reworkNum));
			reworkNums=reworkNums+Integer.parseInt(String.valueOf(reworkNum));
			System.out.println("NGNum:"+NGNum);
//			委外数量累加

			Integer outOutsourceTem = Integer.parseInt(String.valueOf(map.get("OK_OUTSOURCE_TEM")));//加上INSOURCE_IN_TEMS
			Integer okFinishProductionTems = Integer.parseInt(String.valueOf(map.get("OK_FINISH_PRODUCTION_TEMS")));//加FINISH_PRODUCT_TEM

			outOutsourceTem=outOutsourceTem+Integer.parseInt(String.valueOf(reworkNum));
			okFinishProductionTems=okFinishProductionTems+Integer.parseInt(String.valueOf(reworkNum));

			if(String.valueOf(type).equals("3")){//委外+OK_OUTSOURCE_TEM
				service.updateReworkApprovalDatas(String.valueOf(OKNum), String.valueOf(NGNum), String.valueOf(reworkNums), String.valueOf(taskId), String.valueOf(processId),person,String.valueOf(outOutsourceTem),String.valueOf(okFinishProductionTems),String.valueOf(id));

				Map<String,Object> mapx = services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
				Integer applyNum = Integer.parseInt(mapx.get("REWORK_APPLY_NUM").toString());
				applyNum = applyNum-(Integer)applyNum;
				Integer okNum = Integer.parseInt(mapx.get("OK_NUM").toString());
				okNum = okNum+Integer.parseInt(reworkNum);
				service.updateReworkExamineInsideNum(String.valueOf(NGNum),  String.valueOf(reworkNums), String.valueOf(applyNum), taskId.toString(), processId.toString(), id.toString(),okNum.toString());
				Map<String,Object> mapxx = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
				Integer reworkNumxx = Integer.parseInt(mapxx.get("REWORK_NUM").toString());
				reworkNumxx = reworkNumxx + Integer.parseInt(reworkNum);
//				返修数量，合格数量，NG数量
				Map<String,Object> mapxyx = services.showAllMainBranchInfo(taskId, processId);
				Integer okNumxyx = Integer.parseInt(mapxyx.get("USEABLE_NUM").toString());
				Integer ngNumxyx = Integer.parseInt(mapxyx.get("NG_NUM").toString());
				okNumxyx=okNumxyx+Integer.parseInt(reworkNum);
				ngNumxyx=ngNumxyx-Integer.parseInt(reworkNum);

				service.updateReworkNumxx(reworkNumxx.toString(), taskId.toString(), processId.toString(),ngNumxyx.toString(),okNumxyx.toString());

			}else{//非委外不+OK_OUTSOURCE_TEM
				service.updateReworkApprovalDatasx(String.valueOf(OKNum), String.valueOf(NGNum), String.valueOf(reworkNums), String.valueOf(taskId), String.valueOf(processId),person,String.valueOf(outOutsourceTem),String.valueOf(okFinishProductionTems));

			}

			Map<String,Object> mapxs = service.showTaskById(String.valueOf(taskId));
			Integer ngsNum = Integer.parseInt(String.valueOf(mapxs.get("NG_NUM")));
			ngsNum=ngsNum-Integer.parseInt(String.valueOf(reworkNum));

			service.updateTaskNGNum(String.valueOf(ngsNum),String.valueOf(taskId));

//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息
			Map<String,Object> mapx = services.showTaskById(String.valueOf(taskId));
			String stationName = services.showStationName(String.valueOf(processId));

		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo =model.qualityLogInfo(userName, ipInfo,  String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(reworkNum), "", "", person, "返修审核", String.valueOf(mapx.get("STATION_NUM")));
//		  String logInfo =model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), "", "", "", "品检撤销");

//		  String logInfo =model.pushDownLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), person, "成品入库申请","");
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfo, "返修审核");


//			}



//		  =================普通下推逻辑代码部分================
		 Map<String,Object> showTaskByIdxx = service.showTaskById(taskId.toString());
		String projectNumxl=  showTaskByIdxx.get("PROJECT_NUM").toString();
		String specificationModelsxl=  showTaskByIdxx.get("SPECIFICATION_AND_MODEL").toString();
		String meterialCodexl=  showTaskByIdxx.get("MATERIAL_CODE").toString();
		String stationNumxl=  showTaskByIdxx.get("STATION_NUM").toString();
		String processNamexl=  map.get("PROCESS_ID").toString();
		String userNamexl = ToolUtils.getCookieUserName(request);


		  Map<String,Object> mapxl=null;
		 if(!String.valueOf(type).equals("3")){
				   mapxl= services.showTaskList(projectNumxl, specificationModelsxl,processNamexl,meterialCodexl,stationNumxl);

		 }
		  if((!String.valueOf(type).equals("3")) && (Integer.parseInt(mapxl.get("USEABLE_NUM").toString())>0)){

					String flagsxl = mapxl.get("STATUS_FLAGS").toString();
					if(flagsxl.equals("0")){
						return Rjson.error(202,"生产任务为开始状态无法下推");
					}else if(flagsxl.equals("2")){
						return Rjson.error(202,"生产任务为暂停状态无法下推");
					}else if(flagsxl.equals("3")){
						return Rjson.error(202,"生产任务为完成状态无法下推");
					}

					Integer useableNumxl = (Integer)mapxl.get("USEABLE_NUM");//旧有可用数量

					if(useableNumxl<OKNumxl){
						 return Rjson.error(202,"下推数量不能超过可用数量");
					}
					Integer completeNumxl = (Integer)mapxl.get("COMPLETE_NUM");//旧有完成数量
					completeNumxl = completeNumxl+OKNumxl;//现有完成数量
					useableNumxl=useableNumxl-OKNumxl;//现有可用数量

//					System.out.println("abc:"+(Integer)map.get("ORDER_NUM"));
//					System.out.println("abc:"+Integer.parseInt(map.get("ORDER_NUM")));
//					下道工序的待检测数量==上道工序的下推数量
					Integer orderNumxl =null;

					orderNumxl=(Integer)mapxl.get("ORDER_NUM");

					Integer taskIdxl =(Integer)mapxl.get("TASK_ID");
//					求出当前工艺路线最大的ID
					Integer maxOrderxl = services.maxOrder(taskIdxl);

//					1.判断是否为委外做不同的更新2.委外品检减委外发出数量
					Integer processTypeInfosxl= services.showNowProcesstypeInfo(projectNumxl, specificationModelsxl, (Integer)mapxl.get("PROCESS_ID"));
					if(orderNumxl==maxOrderxl){
//						if(1==0){
						//最后一道工序
//						1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
						if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
							services.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);

						}else{
							services.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
						}
						if(processTypeInfosxl!=3){//非委外
							services.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//						  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//						  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


						}else{//委外
							services.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
							//获取客户端IP地址
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");

						}

						if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = services.showProcessName(Integer.parseInt(processNamexl));
						  ProcessLogModel modelxl = new ProcessLogModel();
						  String logInfoxl = modelxl.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxl, "生产任务");
						}
//						}

					}else if(orderNumxl<maxOrderxl){//中间工序或者第一道工序
						if(processTypeInfosxl!=3){//非委外
							services.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量
//							//获取客户端IP地址
//							ShowIPInfo ipxl = new ShowIPInfo();
//							String ipInfoxl = ipxl.getIpAddr(request);
////							String qualityInspectorxl = request.getParameter("qualityInspector");
//							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//							String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//						  ProcessLogModel modelxl = new ProcessLogModel();
//						  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"正常","",stationNumxl);
//						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "下推");

						}else{//委外
							services.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
							//获取客户端IP地址
							ShowIPInfo ipxl = new ShowIPInfo();
							String ipInfoxl = ipxl.getIpAddr(request);
//							String qualityInspectorxl = request.getParameter("qualityInspector");
							String qualityInspectorxl = ToolUtils.getCookieUserName(request);
							String processNamexxl = services.showProcessName(Integer.parseInt(processNamexl));
						  ProcessLogModel modelxl = new ProcessLogModel();
						  String logInfoxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl,String.valueOf(mapxl.get("PROJECT_NAME")) , projectNumxl, specificationModelsxl, processNamexxl, String.valueOf(OKNumxl),qualityInspectorxl,"委外接收","",stationNumxl);
						  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxl, "委外接收");
						}



						Integer nextProcessOrderxl = orderNumxl+1;


							//根据生产任务与序号查找下一道工序
//						获取下道工序主分支数据   获取task数据   判断工序类型

							Map<String,Object> showNextProcessMapxl = services.showNextProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							Integer testingNumxl = (Integer)showNextProcessMapxl.get("TESTING_NUM");
							Map<String,Object> showNextProcessMapxxl = services.showNextProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							Map<String,Object> showNextProcessMapxsxl = service.showTaskById(String.valueOf(taskIdxl));
							Integer processTypeInfoxl= services.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsxl.get("PROJECT_NUM")),String.valueOf(showNextProcessMapxsxl.get("SPECIFICATION_AND_MODEL")) , Integer.parseInt(String.valueOf(showNextProcessMapxxl.get("PROCESS_ID"))));
							if(processTypeInfoxl==3){//委外
								testingNumxl=testingNumxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if(testingNumxl>(Integer)showNextProcessMapxl.get("PLAN_NUM")){
									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
								}

								Map<String,Object> mapdxl = service.showTaskById(String.valueOf(taskIdxl));
								Integer planNumsxl  = Integer.parseInt(String.valueOf(mapxl.get("PLAN_NUM")));
//								Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
								Integer nextOutNumxl =null;
//								初始品检临时数量都为计划数量，下推需要变成真实数量就需要减去计划数量（TESTING_NUM_TEM），第一道不用减，下推需要减去
								if(Integer.parseInt(String.valueOf(showNextProcessMapxl.get("PROCESS_ORDER")))>1){
									if(Integer.parseInt((String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM"))))>=planNumsxl){
										nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")))-planNumsxl;
									}else{
										nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
									}

								}else{
							        nextOutNumxl = Integer.parseInt(String.valueOf((OKNumxl)))+Integer.parseInt(String.valueOf(showNextProcessMapxl.get("TESTING_NUM_TEM")));
								}

								//				更新下道工序信息(待检测数量)
								services.updateNextProcessInfo(testingNumxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
								services.updateNextChildProcessInfo((Integer)taskIdxl, nextProcessOrderxl);
							}else{//非委外


									Map<String,Object> showNextProcessMapsxl = services.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
									Integer pushDownNum = Integer.parseInt(showNextProcessMapsxl.get("PUSH_DOWN_NUM").toString());
									pushDownNum= pushDownNum+Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
									services.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextProcessOrderxl.toString());//更新下推数量
									Integer maxNum = services.maxOrder(taskIdxl);
									//如果下一道工序是成平入库
									if(nextProcessOrderxl==maxNum){

										Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
										testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
										if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
											 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
										}
//
//											Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//										service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl,"0");//
										String okFinishNum = showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS")==null?"0":showNextProcessMapsxl.get("OK_FINISH_PRODUCTION_TEMS").toString();
										Integer finishNum = Integer.parseInt(okFinishNum);
										finishNum = finishNum+Integer.parseInt(String.valueOf(OKNumxl));
										services.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextProcessOrderxl.toString());


										completeNumxl= Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString())+OKNumxl;
										useableNumxl = Integer.parseInt(showNextProcessMapsxl.get("USEABLE_NUM").toString())-OKNumxl;


										//最后一道工序
//										1.下推数量转为任务完成数量 2.更新当前工序完成数量3.判断当前工序完成数量是否等于计划数量做任务按钮状态的更改
//										if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){//完成数量==计划数量
//											service.updateProcessTaskCompleteInfo(completeNumxl, 3, taskIdxl);
//
//										}else{
//											service.updateProcessTaskCompleteInfo(completeNumxl, null, taskIdxl);
//										}
//										if(processTypeInfosxl!=3){
											//非委外
//											service.updateDetailsTaskInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl);//更新当前工序的完成数量与可用数量   4.0
//										更新一下，useName4.0
										Map<String,Object> showNextProcessMapxlxs = services.showNextProcessInfo((Integer)taskIdxl, orderNumxl);
									    Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
									    Integer useNumxx = Integer.parseInt(showNextProcessMapsxl.get("COMPLETE_NUM").toString());//最后一道工序、
									    useNumxx = complete-useNumxx;//成品入库的可用数量
									    services.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextProcessOrderxl.toString());

//											ShowIPInfo ipxl = new ShowIPInfo();
//											String ipInfoxl = ipxl.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
//											String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//											String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxl = new ProcessLogModel();
//										  String logInfox = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfox, "成品入库");
//										  String logInfo = model.taskLogInfo("admin", ipInfo, String.valueOf(map.get("PROJECT_NAME")), "完成", null,null, projectNum, specificationModels,null,null);
//										  logInfos.addProcessLogInfo(String.valueOf(map.get("TASK_ID")), null, logInfo, "生产任务");


//										}
//										else{//委外
//											service.updateDetailsTaskInInfos(completeNumxl, useableNumxl, Integer.parseInt(processNamexl),taskIdxl, completeNumxl);
//											//获取客户端IP地址
//											ShowIPInfo ipxl = new ShowIPInfo();
//											String ipInfoxl = ipxl.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
//											String qualityInspectorxl =  ToolUtils.getCookieUserName(request);
//											String processNamexxl = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxl = new ProcessLogModel();
//										  String logInfoxxl = modelxl.pushDownLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), projectNumxl, specificationModelsxl, "成品入库", String.valueOf(OKNumxl),qualityInspectorxl,"成品入库","",stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), processNamexl, logInfoxxl, "成品入库");
//
//										}

//										if(completeNumxl==(Integer)mapxl.get("PLAN_NUM")){
//											ShowIPInfo ipxlx = new ShowIPInfo();
//											String ipInfoxlx = ipxlx.getIpAddr(request);
////											String qualityInspectorxl = request.getParameter("qualityInspector");
////											String qualityInspectorxl = ToolUtils.getCookieUserName(request);
//											String qualityInspectorxlx = ToolUtils.getCookieUserName(request);
//											String processNamexxlx = service.showProcessName(Integer.parseInt(processNamexl));
//										  ProcessLogModel modelxlx = new ProcessLogModel();
//										  String logInfoxlx = modelxlx.taskLogInfo(userNamexl, ipInfoxl, String.valueOf(mapxl.get("PROJECT_NAME")), "完成", null,null, projectNumxl, specificationModelsxl,null,null,null,stationNumxl);
//										  logInfos.addProcessLogInfo(String.valueOf(mapxl.get("TASK_ID")), null, logInfoxlx, "生产任务");
//										}


									}



							}
//							旧的更新品质的方法
//							else{
//								//根据生产任务与序号查找下一道工序
//								Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
//								Integer testingNumxsxl = (Integer)showNextProcessMapsxl.get("TESTING_NUM");
//								testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
//								if(testingNumxsxl>(Integer)showNextProcessMapsxl.get("PLAN_NUM")){
//									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//								}
//
//									Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsxl.get("OK_INSOURCE_TEM_APPROVLA")));
//
//									//				更新下道工序信息(待检测数量)
//								service.updateNextProcessInfox(testingNumxsxl, (Integer)taskIdxl, nextProcessOrderxl,nextOutNumxl);
//							}










//						Integer nextProcessOrder = orderNum+1;
////						根据生产任务与序号查找下一道工序
//						Map<String,Object> showNextProcessMap = service.showNextProcessInfo(taskId, nextProcessOrder);
//						Integer testingNum = (Integer)showNextProcessMap.get("TESTING_NUM");
//						testingNum=testingNum+OKNum;//旧有的待测试数量+上道工序的下推数量
//						if(testingNum>(Integer)showNextProcessMap.get("PLAN_NUM")){
//							 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//						}
//						Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//						service.updateNextProcessInfox(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//








//						Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));

//						if(processTypeInfosx!=3){


							//				更新下道工序信息(待检测数量)

							//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
////						}else{
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
//
//							//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfo(testingNum, (Integer)taskId, nextProcessOrder,nextOutNum);
//							//				更新下道工序信息(待检测数量)
////							service.updateNextProcessInfo(testingNum, taskId, nextProcessOrder);
//						}
//


//					}
					}else if(orderNumxl>maxOrderxl){//异常工序
						 return Rjson.error(202,"工艺路线序号异常，请联系工艺部");
					}

//					return Rjson.success();
					// }}

			  }


//		  ==============委外接收下推（合并）===============
			  if(String.valueOf(type).equals("3")){

				  type="3";

				  if(String.valueOf(OKNumxlxl).equals("")||String.valueOf(OKNumxlxl)==null){
					  OKNumxlxl="0";
					}

					String okNumxww = String.valueOf(OKNumxlxl);

					Map<String,Object> mapww = services.showDetailsInfos(String.valueOf(taskId), String.valueOf(processId),String.valueOf(id));//查询工序详情

					String outSourceInNumww = String.valueOf(mapww.get("OUTSOURCE_IN_NUM"));
//					String outSourceInNum = String.valueOf(map.get("OUTSOURCE_IN_NUM"));
					String completeNumww = String.valueOf(mapww.get("COMPLETE_NUM"));
					String useNumww = String.valueOf(mapww.get("USEABLE_NUM"));
					String planNumww = String.valueOf(mapww.get("PLAN_NUM"));
					Integer orderNumww =(Integer)mapww.get("PROCESS_ORDER");//当前工序的排序
					String insupplerww = String.valueOf(mapww.get("ALL_OUT_SUPPLIER"));
					Integer nextOrderNumww = orderNumww+1;
					if(String.valueOf(("OKNum")).equals("")){
						OKNumxlxl="0";
					}

					Integer completeNumsww = Integer.parseInt(completeNumww)+Integer.parseInt(String.valueOf((OKNumxlxl)));
					System.out.println("完成数量:"+completeNumsww+","+planNumww);
					Integer useNumsww = Integer.parseInt(useNumww)-Integer.parseInt(String.valueOf((OKNumxlxl)));
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
					if(planNumww.equals(completeNumxww)){//全部完成
						services.updateInSourceprocessInfo("11", person, String.valueOf(completeNumsww), String.valueOf(useNumsww),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
						Map<String,Object> mapxww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUM = Integer.parseInt(mapxww.get("OK_NUM").toString());
//					    completeNumsww = mapxww.get("COMPLETE_NUM")==null?0:Integer.parseInt(mapxww.get("COMPLETE_NUM").toString());
//						okNUM = okNUM-completeNumsww;
						okNUM = okNUM-Integer.parseInt(OKNumxlxl.toString());//4.0
//						System.out.println("useNums:"+useNums);
						Integer applyNUM = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUM = applyNUM-Integer.parseInt(OKNumxlxl.toString());
						Integer completeNumswwx = Integer.parseInt((mapxww.get("IN_SIDE_NUM_EXAMINE").toString()));
						completeNumswwx=completeNumswwx+Integer.parseInt(OKNumxlxl.toString());
						services.updateOutsideExamineNums(okNUM.toString(),applyNUM.toString(), completeNumswwx.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String,Object> mapxsww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String,Object> mainMapsww = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer)mainMapsww.get("COMPLETE_NUM");
						completeww=completeww+Integer.parseInt(OKNumxlxl.toString());
						services.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if(planNumsww==examineInsideww){
							services.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
					}else{
						Map<String,Object> mapxww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer okNUMww = Integer.parseInt(mapxww.get("OK_NUM").toString());
						okNUMww = okNUMww-Integer.parseInt(OKNumxlxl.toString());
						System.out.println("useNums:"+useNumsww);
						Integer applyNUMww = Integer.parseInt(mapxww.get("IN_SIDE_NUM_APPLY").toString());
						applyNUMww = applyNUMww-Integer.parseInt(OKNumxlxl.toString());
						services.updateInSourceprocessInfo("0", person, String.valueOf(completeNumsww), String.valueOf(useNumsww),String.valueOf(taskId),String.valueOf(processId),String.valueOf(id));
						services.updateOutsideExamineNums(okNUMww.toString(),applyNUMww.toString(), completeNumsww.toString(), taskId.toString(), processId.toString(), id.toString());
						Map<String,Object> mapxsww=  services.showOutsideTaskById(taskId.toString(), processId.toString(), id.toString());
						Integer planNumsww = Integer.parseInt(mapxsww.get("PLAN_NUM").toString());
						Integer examineInsideww = Integer.parseInt(mapxsww.get("IN_SIDE_NUM_EXAMINE").toString());
						Map<String,Object> mainMapsww = services.showAllMainBranchInfo(taskId.toString(), processId.toString());
						Integer completeww = (Integer)mainMapsww.get("COMPLETE_NUM");
						completeww=completeww+Integer.parseInt(OKNumxlxl.toString());
						services.updateMainComplete(completeww.toString(), taskId.toString(), processId.toString());
						if(planNumsww==examineInsideww){
							services.updateOutsideStatusNum(taskId.toString(), processId.toString(), id.toString(), "6");
						}
						}

//					更新下道工序的待检测数量   nextOrderNum



					if(String.valueOf(type).equals("3")){
						//根据生产任务与序号查找下一道工序

						Map<String,Object> showNextProcessMapww = services.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNumww);
						Integer testingNumww = (Integer)showNextProcessMapww.get("TESTING_NUM");
						Map<String,Object> showNextProcessMapxww = services.showNextProcessInfo(Integer.parseInt(taskId), nextOrderNumww);
						Map<String,Object> showNextProcessMapxsww = service.showTaskById(String.valueOf(taskId));
						Integer processTypeInfosww= services.showNowProcesstypeInfo(String.valueOf(showNextProcessMapxsww.get("PROJECT_NUM")),String.valueOf(showNextProcessMapxsww.get("SPECIFICATION_AND_MODEL")) , Integer.parseInt(String.valueOf(showNextProcessMapxww.get("PROCESS_ID"))));
						if(processTypeInfosww==3){
							testingNumww=testingNumww+Integer.parseInt(String.valueOf(OKNumxlxl));//旧有的待测试数量+上道工序的下推数量
							if(testingNumww>(Integer)showNextProcessMapww.get("PLAN_NUM")){
								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
							}

							Map<String,Object> mapdww = service.showTaskById(String.valueOf(taskId));
							Integer planNumsww  = Integer.parseInt(String.valueOf(mapww.get("PLAN_NUM")));
//							Integer nextOutNum = testingNum-Integer.parseInt(String.valueOf(showNextProcessMap.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMap.get("OK_INSOURCE_TEM_APPROVLA")));
							Integer nextOutNumww =null;
							if(Integer.parseInt(String.valueOf(showNextProcessMapww.get("PROCESS_ORDER")))>1){
								if(Integer.parseInt((String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM"))))>=planNumsww){
									nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")))-planNumsww;
								}else{
									nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
								}

							}else{
						        nextOutNumww = Integer.parseInt(String.valueOf((OKNumxlxl)))+Integer.parseInt(String.valueOf(showNextProcessMapww.get("TESTING_NUM_TEM")));
							}

							//				更新下道工序信息(待检测数量)
							services.updateNextProcessInfo(testingNumww, Integer.parseInt(taskId), nextOrderNumww,nextOutNumww);
						}else{

//							Map<String,Object> showNextProcessMapsxl = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskIdxl)), nextProcessOrderxl);
							Map<String,Object> showNextProcessMapsww = services.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
							Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
							pushDownNum= pushDownNum+Integer.parseInt(String.valueOf(OKNumxl)); // 旧有的下推数量+新的下推数量
							services.updateDetailsPushDownNum(pushDownNum.toString(), taskId.toString(), nextOrderNumww.toString());//更新下推数量
							Integer maxNum = services.maxOrder(Integer.parseInt(taskId.toString()));
							//如果下一道工序是成平入库
							if(nextOrderNumww==maxNum){
								Integer testingNumxsxl = (Integer)showNextProcessMapsww.get("TESTING_NUM");
								testingNumxsxl=testingNumxsxl+Integer.parseInt(String.valueOf(OKNumxl));//旧有的待测试数量+上道工序的下推数量
								if(testingNumxsxl>(Integer)showNextProcessMapsww.get("PLAN_NUM")){
									 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
								}

//									Integer nextOutNumxl = testingNumxsxl-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//								service.updateNextProcessInfox(testingNumxsxl, (Integer)taskId, nextOrderNumww,nextOutNumxl,"0");

								String okFinishNum = showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS")==null?"0":showNextProcessMapsww.get("OK_FINISH_PRODUCTION_TEMS").toString();
								Integer finishNum = Integer.parseInt(okFinishNum);
								finishNum = finishNum+Integer.parseInt(String.valueOf(OKNumxl));
								services.updateFinishDataxs(finishNum.toString(), taskId.toString(), nextOrderNumww.toString());


								Integer completeNumxl= Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString())+OKNumxl;
								Integer useableNumxl = Integer.parseInt(showNextProcessMapsww.get("USEABLE_NUM").toString())-OKNumxl;


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
								asx=nextOrderNumww-1;
								if(asx==0){
									asx= nextOrderNumww;
								}
								Map<String,Object> showNextProcessMapxlxs = services.showNextProcessInfo(Integer.parseInt(taskId), asx);
							    Integer complete = Integer.parseInt(showNextProcessMapxlxs.get("COMPLETE_NUM").toString());//上道工序
							    Integer useNumxx = Integer.parseInt(showNextProcessMapsww.get("COMPLETE_NUM").toString());//最后一道工序、
							    useNumxx = complete-useNumxx;//成品入库的可用数量
							    services.updateUseNumxx(useNumxx.toString(), taskId.toString(), nextOrderNumww.toString());
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

//						else{
//							//根据生产任务与序号查找下一道工序
//							Map<String,Object> showNextProcessMapsww = service.showNextProcessInfo(Integer.parseInt(String.valueOf(taskId)), nextOrderNumww);
//							Integer pushDownNum = Integer.parseInt(showNextProcessMapsww.get("PUSH_DOWN_NUM").toString());
//
//							Integer testingNumxsww = (Integer)showNextProcessMapsww.get("TESTING_NUM");
//							testingNumxsww=testingNumxsww+Integer.parseInt(String.valueOf(OKNum));//旧有的待测试数量+上道工序的下推数量
//							if(testingNumxsww>(Integer)showNextProcessMapsww.get("PLAN_NUM")){
//								 return Rjson.error(202,"请检查工序流转，待检测数量不能大于计划数量");
//							}
//							pushDownNum = pushDownNum - Integer.parseInt(String.valueOf(OKNumxl));
//
////							Integer processTypeInfosx= service.showNowProcesstypeInfo(projectNum, specificationModels, (Integer)showNextProcessMap.get("PROCESS_ID"));
//
////							if(processTypeInfosx!=3){
//								Integer nextOutNumww = testingNumxsww-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OUTSOURCE_OUT_NUM")))-Integer.parseInt(String.valueOf(showNextProcessMapsww.get("OK_INSOURCE_TEM_APPROVLA")));
//
//								//				更新下道工序信息(待检测数量)
//							service.updateNextProcessInfox(testingNumxsww, (Integer)taskId, nextOrderNumww,nextOutNumww,pushDownNum.toString());
//						}



					}else{

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
							Map<String,Object> mapxww = service.showTaskById(String.valueOf(taskId));
							String stationNameww = services.showStationName(String.valueOf(processId));

						  ProcessLogModel modelww = new ProcessLogModel();
						  String logInfoww =modelww.pushDownLogInfo(userNameww, ipInfoww, String.valueOf(mapxww.get("PROJECT_NAME")), String.valueOf(mapxww.get("PROJECT_NUM")), String.valueOf(mapxww.get("SPECIFICATION_AND_MODEL")), stationNameww,okNumxww, person, "委外接收审核",insupplerww,String.valueOf(mapxww.get("STATION_NUM")));
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请");
						  System.out.println("log:"+logInfoww);
						  logInfos.addProcessLogInfo(String.valueOf(taskId),String.valueOf(processId), logInfoww, "委外接收审核");



			  }





			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
