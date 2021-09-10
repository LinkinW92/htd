package com.skeqi.mes.controller.processFlows;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ProcessLogModel;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.processFlows.ProcessClientService;
import com.skeqi.mes.service.processFlows.ProcessDetailsService;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.service.processFlows.RouteLineInfoService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.ImportExcel;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "工艺路线", description = "工艺路线", produces = MediaType.APPLICATION_JSON)
public class RouteLineInfoController {

	@Autowired
	private RouteLineInfoService service;
	@Autowired
	private ProcessClientService services;

	@Autowired
	private ProcessDetailsService servicesx;

	@Autowired
	private ProcessLogInfoService logInfos;


	@RequestMapping(value = "/importProcessWayInfo", method = RequestMethod.POST)
	@ApiOperation(value = "导入工艺Excel", notes = "导入工艺Excel")
	@ApiImplicitParams({})
	@ResponseBody
	@Transactional
	public Rjson importProcessWayInfo(HttpServletRequest request,@RequestParam(required=false)MultipartFile file) throws ServicesException {
		try {

//			System.out.println("文件名称:"+file.getOriginalFilename());

//			System.out.println(file.getOriginalFilename().substring(fileName.length()-3,fileName.length()));
//			System.out.println(file.getOriginalFilename().substring(fileName.length()-4,fileName.length()));
//		           判断文件格式
//
//			System.out.println("文件名称："+file.getOriginalFilename());
			String fileName = file.getOriginalFilename();
//			System.out.println("文件名称："+file);

			if(!(fileName.substring(fileName.length()-4,fileName.length()).equals("xlsx") || fileName.substring(fileName.length()-3,fileName.length()).equals("xls"))){
				return Rjson.error(202, "上传文件必须是Excel");
			}
//			获取excel流
			InputStream in = null;
			try {
				in = file.getInputStream();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}

			Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
			Map<String,String> maps = new HashMap<>();
			if(fileName.substring(fileName.length()-4,fileName.length()).equals("xlsx")){
				XSSFWorkbook  workbook = new XSSFWorkbook(in);
				XSSFSheet sheetAt = workbook.getSheetAt(0);
			}else if(fileName.substring(fileName.length()-3,fileName.length()).equals("xls")){
				HSSFWorkbook  workbook = new HSSFWorkbook(in);
				HSSFSheet sheetAt = workbook.getSheetAt(0);
			}

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			try {
				InputStream ins = file.getInputStream();
				map = ImportExcel.getIoValue(ins, fileName); // 调用封装的方法获取数据
				List<Object> list = map.get(5);
				System.out.println("输出MAP:"+map);
				System.out.println("输出第一个字段："+list.get(0).toString().trim());

				if(!list.get(0).toString().trim().equals("项目号")){
					return Rjson.error("第一列名称错误");
				}
				if(!list.get(1).toString().trim().equals("工位号")){
					return Rjson.error("第二列名称错误");
				}
				if(!list.get(2).toString().trim().equals("规格型号")){
					return Rjson.error("第三列名称错误");
				}

				if(!list.get(4).toString().trim().equals("工件名称")){
					return Rjson.error("第五列名称错误");
				}
				if(!list.get(6).toString().trim().equals("工序序号")){
					return Rjson.error("第七列名称错误");
				}
				if(!list.get(7).toString().trim().equals("工序名称")){
					return Rjson.error("第八列名称错误");
				}
				if(!list.get(12).toString().trim().equals("单件运行时间")){
					return Rjson.error("第十三列名称错误");
				}
				if(!list.get(13).toString().trim().equals("备注")){
					return Rjson.error("第十四列名称错误");
				}
				Integer dropRow;
				for (int i = 6; i <= map.size(); i++) {
					List<Object> list2 = map.get(i);//获取每行的数据
					System.out.println(list2);

					Map<String,Object> mapxs = new HashMap<String,Object>();
					dropRow=0;
					for (int j = 0; j < list2.size(); j++) {//循环遍历赋值单个Map
						if(j==0){  //项目号
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("PROJECT_NUMX", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行项目号不能为空");
							}
						}
						if(j==1){  //工位号
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("STATION_NUMX", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行工位号不能为空");
							}
						}
						if(j==2){  //规格型号
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("SPECIFICATION_AND_MODELX", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行规格型号不能为空");
							}
						}
						if(j==4){  //工件名称
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("NAME", list2.get(j).toString());
							}
							else{
								return Rjson.error("第"+i+"行工件名称不能为空");
							}
						}
						if(j==6){  //工序序号
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("SERIAL_NO", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行工序序号不能为空");
							}
						}
						if(j==7){  //工序名称
							if(list2.get(j).equals("")||list2.get(j)==null){
								dropRow++;
								continue;
							}
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("STATION_NAME", list2.get(j).toString());
							}
							else{
								return Rjson.error("第"+i+"行工序名称不能为空");
							}
						}
						if(j==12){  //单件运行时间
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("RUN_TIMES", list2.get(j).toString());
							}else{
								mapxs.put("RUN_TIMES", "");
//								return Rjson.error("第"+i+"行单件运行时间不能为空");
							}
						}
						if(j==13){  //备注
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapxs.put("PROCESS_REMARKS", list2.get(j).toString());
							}else{
//								return Rjson.error("第"+i+"行备注不能为空");
								mapxs.put("PROCESS_REMARKS", "");
							}
						}

						System.out.println("Map数据内："+mapxs);
					}
					System.out.println("Map数据外："+mapxs);
					if(dropRow>0){//出现空数据忽略当前循环，进入下道循环
						continue;
					}

					try{

						String projectNum = mapxs.get("PROJECT_NUMX").toString();
						String stationNum = mapxs.get("STATION_NUMX").toString();
						String specificationAndModel = mapxs.get("SPECIFICATION_AND_MODELX").toString();
						String name = mapxs.get("NAME").toString();

						String stationName = mapxs.get("STATION_NAME").toString();

						String serialNo = mapxs.get("SERIAL_NO").toString();
						String processRemarks = mapxs.get("PROCESS_REMARKS").toString();
						String runtimes = mapxs.get("RUN_TIMES").toString();

						String maxRouteId = null;
						Integer routIdById=null;
//						判断是否已经存在该工艺路线
						Map<String,Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
						if(Integer.parseInt(countRoute.get("count").toString())>0){
							maxRouteId = countRoute.get("ID").toString();
						}else{
							maxRouteId = service.showMaxRouteLineId()==null?"0":service.showMaxRouteLineId();

							routIdById = (Integer.parseInt(maxRouteId)+1);
							maxRouteId = routIdById.toString();
							service.addRouteLine(maxRouteId, projectNum, stationNum, specificationAndModel, name);//添加最新的routing
						}
//						查询Station对应的ID
						Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);
						if(stationId==-1){
							return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
						}
						List<Integer> showAllSTId = service.showAllStIdByWayId(maxRouteId.toString());
						for(Integer stId:showAllSTId){
//							System.out.println("打印st信息："+stId);
							if(stationId.toString().equals(stId.toString())){
								return Rjson.error(202,"工艺路线不能存在同名工序");
							}
						}

						Integer newMaxSerialNo =null;
						if(stationId==409){
							Integer maxSerialNo = service.showProductWayId(maxRouteId);
							newMaxSerialNo=maxSerialNo+1;
							if(Integer.parseInt(serialNo)!=newMaxSerialNo){
								return Rjson.error(202, "成品入库必须为工艺最大工序");
							}
						}

//						更新任务单数据（确保当前最大的工序无绑定数据，否则有出现下推缺失）

						List<Map<String,Object>> showTaskByRouteId = service.showAllTaskByRouteId(maxRouteId.toString());
						Integer flags = 0;
						Integer flagss = 0;
						Integer flagsss = 0;
						for(Map mapx:showTaskByRouteId){
//							获取当前最大的details
							Integer maxDetailsOrder = service.showMaxDetailsIdByTaskId(mapx.get("ID").toString());
							Integer maxDetailsId = service.showMaxId(mapx.get("ID").toString(), maxDetailsOrder.toString());
							Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());

							String testNumTem=null;
							String planNum = null;
							String pushDownNum=null;
							String ngNum=null;
							String useableNum = null;
							String testNum=null;
							String completeNum = null;
							String okOuttems = null;
							String ngOuttems = null;

							if(showDetailsById.get("PLAN_NUM")==null){
								planNum = "0";
							}else{
								planNum =showDetailsById.get("PLAN_NUM").toString();
							}
							if(showDetailsById.get("PUSH_DOWN_NUM")==null){
								pushDownNum = "0";
							}else{
								pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
							}
							if(showDetailsById.get("TESTING_NUM_TEM")==null){
								testNumTem = "0";
							}else{
								testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
							}
							if(showDetailsById.get("NG_NUM")==null){
								ngNum = "0";
							}else{
								ngNum =showDetailsById.get("NG_NUM").toString();
							}
							if(showDetailsById.get("USEABLE_NUM")==null){
								useableNum = "0";
							}else{
								useableNum =showDetailsById.get("USEABLE_NUM").toString();
							}
							if(showDetailsById.get("TESTING_NUM")==null){
								testNum = "0";
							}else{
								testNum =showDetailsById.get("TESTING_NUM").toString();
							}
							if(showDetailsById.get("COMPLETE_NUM")==null){
								completeNum = "0";
							}else{
								completeNum =showDetailsById.get("COMPLETE_NUM").toString();
							}
							if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
								okOuttems = "0";
							}else{
								okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
							}

							if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
								ngOuttems = "0";
							}else{
								ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
							}



//						判断是否存在数据绑定（判断是否是委外的工序）
//							String processTypeInfoxl = service.showStationTypeInfos(stationId.toString());
							String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString());
//							判断是否为首工序
							String processOrder = showDetailsById.get("PROCESS_ORDER").toString();
							Integer contrastNum = Integer.parseInt(processOrder)+1;

							if(contrastNum==Integer.parseInt(serialNo)){//确保工序号是连续的

							if(processOrder.equals("1")){//最大的工序为首工序，即只存在一道工序
								if(!processTypeInfoxl.equals("3")){//首工序非委外
									if(planNum.equals(pushDownNum)){
//										判断是否只有成品入库
										Integer maxNumx = service.showProductWayId(maxRouteId);
										Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
										if(stId==409){
											return Rjson.error(202, "不能在成品入库后添加工序");
										}

										service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
										service.updateInitData(mapx.get("ID").toString(), serialNo);
										//判断初始化新增的details

//										servicesx.updateFirstProcessDetailsx(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
										flags++;
									}else{
//										累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
										flagss=1;

									}
								}else{//首工序委外
									if(testNumTem.equals(planNum)){
									    service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
									    service.updateInitData(mapx.get("ID").toString(), serialNo);
//									    servicesx.updateFirstProcessDetails(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
									    flags++;
									}else{
//										累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
										flagss=1;
									}
								}
							}else{//非首工序，判断的条件是不同的，首工序有原始数据
								if(!processTypeInfoxl.equals("3")){//非委外
									Integer maxNumx = service.showProductWayId(maxRouteId);
									Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
									if(stId==409){
										return Rjson.error(202, "不能在成品入库后添加工序");
									}
									if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){
										//未绑定数据

										service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
										service.updateInitData(mapx.get("ID").toString(), serialNo);
//										servicesx.updateFirstProcessDetailsx(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
										flags++;
									}else{
//										累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
										flagss=1;
									}
								}else{//委外OK_OUTSOURCE_TEMS NG_OUTSOURCE_TEMS COMPLETE_NUM
									if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){
										 service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
										 service.updateInitData(mapx.get("ID").toString(), serialNo);
//										 servicesx.updateFirstProcessDetails(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
										 flags++;
									}else{
//										累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
										flagss=1;
									}
								}
							}
						}else{
//							添加一个数值用于说明存在不连续的序号error
							flagsss=1;
						}

						}
						if(flags>0){
							service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//							打印日志

							//获取客户端IP地址
							ShowIPInfo ip = new ShowIPInfo();
							String ipInfo = ip.getIpAddr(request);
//							获取用户名
							String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息


						  ProcessLogModel model = new ProcessLogModel();
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//						 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
						 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
						  System.out.println("log:"+logInfo);
						  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "导入工艺工序");
						}
						if(flagss==1&&flagsss==1){
							return Rjson.error(202, "存在数据绑定或者序号不连续，不支持新增操作");
						}
						if(flagss==1){
							return Rjson.error(202, "存在数据绑定，不支持新增操作");
						}
						if(flagsss==1){
							return Rjson.error(202, "存在序号不连续，不支持新增操作");
						}


						Integer maxWayIds = service.showProductWayId(maxRouteId)==null?0:service.showProductWayId(maxRouteId);
						Integer newWayIds = maxWayIds+1;
						if(Integer.parseInt(serialNo)>newWayIds){
							return Rjson.error(202, "工艺路线添加的工序序号必须连续");
						}
//						如果不存在绑定的task
						if(showTaskByRouteId.size()==0){
							Integer maxWayNum = service.countProductWayNum(maxRouteId.toString());
							if(maxWayNum==0){//判断序号是否正常
								if(Integer.parseInt(serialNo)!=1){
									return Rjson.error(202, "工序序号必须从1开始");
								}else{
									service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


								  ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
								 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
								  System.out.println("log:"+logInfo);
								  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "导入工艺工序");
								}
							}else{
								Integer maxNumx = service.showProductWayId(maxRouteId);
								Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
								if(stId==409){
									return Rjson.error(202, "不能在成品入库后添加工序");
								}
								service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//								打印日志

								//获取客户端IP地址
								ShowIPInfo ip = new ShowIPInfo();
								String ipInfo = ip.getIpAddr(request);
//								获取用户名
								String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


							  ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
							 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
							  System.out.println("log:"+logInfo);
							  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "导入工艺工序");
							}

						}
					}catch(Exception e){
						ProcessLogModel info = new ProcessLogModel();
						String logInfo = info.addRouteInfo( "", "","","","","","导入报错","","请检查Excel数据，查看是否异常！");
						String stationName = mapxs.get("STATION_NAME").toString();
						Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);
						logInfos.addProcessLogInfo("-1",stationId.toString(), logInfo, "导入工艺工序");
						return Rjson.error("请检查Excel数据，查看是否异常！");
					}


				}

			return Rjson.success();
			} catch (Exception e) {
				e.printStackTrace();
				return Rjson.error(Constant.INTERFACE_EXCEPTION);
			}
		}catch (Exception e) {
			ProcessLogModel info = new ProcessLogModel();
			info.addRouteInfo( "", "","","","","","","",e.getMessage());
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}




//	展示工艺路线清单列表
	@RequestMapping(value = "/showRouteLine", method = RequestMethod.POST)
	@ApiOperation(value = "展示工艺路线", notes = "展示工艺路线")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showRouteLine(HttpServletRequest request) throws ServicesException {
		try {

			Integer pageNum = null;
			Integer pageSize = null;
			String projectNum = request.getParameter("projectNum");
			String specificationModel = request.getParameter("specificationModel");
			String stationNum = request.getParameter("stationNum");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			System.out.println(startTime+"--"+endTime);

			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if (request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			PageHelper.startPage(pageNum, pageSize);
			List<Map<String,Object>> list =service.showRouteLine(projectNum,specificationModel,stationNum,startTime,endTime);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(200,pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	编辑工艺路线清单列表
	@RequestMapping(value = "/editRouteLine", method = RequestMethod.POST)
	@ApiOperation(value = "编辑工艺路线", notes = "编辑工艺路线")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson editRouteLine(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String stationNum = request.getParameter("stationNum");
			String specificationAndModel = request.getParameter("specificationAndModel");
			String name = request.getParameter("name");

			String stationName = request.getParameter("stationName");

			String serialNo = request.getParameter("serialNo");
			String oldSerialNo = request.getParameter("oldSerialNo");
			String processRemarks = request.getParameter("processRemarks");
			String runtimes = request.getParameter("runtimes");

			Map<String,Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
			String routeId = countRoute.get("ID").toString();
			List<Map<String,Object>> showTaskByRouteId = service.showAllTaskByRouteId(routeId);//是否存在task

			Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);
			String processType = service.showStationTypeInfos(stationId.toString()); //委外判定

			Integer maxWayId = service.showProductWayId(routeId);

//		    当前传入的数值是否是工艺最大，它是否是成品入库
		 	if(maxWayId==Integer.parseInt(serialNo)){
		 		if(stationId!=409){
		 			return Rjson.error(202,"工艺路线的最大工序必须是成品入库，请检查数据");
		 		}
		 	}

		 	String productWayIdxx = service.showProductWayIdxxs(routeId, oldSerialNo);
		 	service.updateWayDataInfosx(runtimes, productWayIdxx.toString(),processRemarks);

			if(showTaskByRouteId.size()>0){
				Integer countNum=0;
				for(Map mapxx:showTaskByRouteId){
				Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), oldSerialNo);
				Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
				String testNumTem=null;
				String planNum = null;
				String pushDownNum=null;
				String ngNum=null;
				String useableNum = null;
				String testNum=null;
				String completeNum = null;
				String okOuttems = null;
				String ngOuttems = null;

				if(showDetailsById.get("PLAN_NUM")==null){
					planNum = "0";
				}else{
					planNum =showDetailsById.get("PLAN_NUM").toString();
				}
				if(showDetailsById.get("PUSH_DOWN_NUM")==null){
					pushDownNum = "0";
				}else{
					pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
				}
				if(showDetailsById.get("TESTING_NUM_TEM")==null){
					testNumTem = "0";
				}else{
					testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
				}
				if(showDetailsById.get("NG_NUM")==null){
					ngNum = "0";
				}else{
					ngNum =showDetailsById.get("NG_NUM").toString();
				}
				if(showDetailsById.get("USEABLE_NUM")==null){
					useableNum = "0";
				}else{
					useableNum =showDetailsById.get("USEABLE_NUM").toString();
				}
				if(showDetailsById.get("TESTING_NUM")==null){
					testNum = "0";
				}else{
					testNum =showDetailsById.get("TESTING_NUM").toString();
				}
				if(showDetailsById.get("COMPLETE_NUM")==null){
					completeNum = "0";
				}else{
					completeNum =showDetailsById.get("COMPLETE_NUM").toString();
				}
				if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
					okOuttems = "0";
				}else{
					okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
				}

				if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
					ngOuttems = "0";
				}else{
					ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
				}

				String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
				if(oldSerialNo.equals("1")){
					if(!processTypeInfoxl.equals("3")){
						if(planNum.equals(pushDownNum)){//非数据绑定

						}else{//+1
							countNum++;
						}
					}else{
						if(testNumTem.equals(planNum)){

						}else{//+1
							countNum++;
						}
					}
				}else{
					if(!processTypeInfoxl.equals("3")){
						if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){//非数据绑定

						}else{//+1
							countNum++;
						}
					}else{

						if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){

						}else{//+1
							countNum++;
						}
					}
				}

//				service.updateWayDataInfosx(runtimes, maxDetailsId.toString());这边的ID 不同

				}

				if(countNum>0){//本身存在数据绑定
					return Rjson.error(202,"当前工序存在绑定数据，不支持编辑操作");
				}

				Integer countNewNum=0;

//				处理大序号插入到小序号，小序号插入到大序号
				Integer maxSerialNo = null;
				Integer minSerialNo = null;
				if(Integer.parseInt(serialNo)> Integer.parseInt(oldSerialNo)){
					maxSerialNo=Integer.parseInt(serialNo);
					minSerialNo = Integer.parseInt(oldSerialNo);
				}else{
					maxSerialNo=Integer.parseInt(oldSerialNo);
					minSerialNo=Integer.parseInt(serialNo);
				}

				if(maxSerialNo>maxWayId){
					return Rjson.error(202, "编辑的工序序号不能超过工艺路线的最大工序序号");
				}

				for(Map mapxx:showTaskByRouteId){
					//判断区间是否存在数据绑定
					for(int i=minSerialNo;i<maxSerialNo;i++){

						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), String.valueOf(i));
						Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
						String testNumTem=null;
						String planNum = null;
						String pushDownNum=null;
						String ngNum=null;
						String useableNum = null;
						String testNum=null;
						String completeNum = null;
						String okOuttems = null;
						String ngOuttems = null;

						if(showDetailsById.get("PLAN_NUM")==null){
							planNum = "0";
						}else{
							planNum =showDetailsById.get("PLAN_NUM").toString();
						}
						if(showDetailsById.get("PUSH_DOWN_NUM")==null){
							pushDownNum = "0";
						}else{
							pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
						}
						if(showDetailsById.get("TESTING_NUM_TEM")==null){
							testNumTem = "0";
						}else{
							testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
						}
						if(showDetailsById.get("NG_NUM")==null){
							ngNum = "0";
						}else{
							ngNum =showDetailsById.get("NG_NUM").toString();
						}
						if(showDetailsById.get("USEABLE_NUM")==null){
							useableNum = "0";
						}else{
							useableNum =showDetailsById.get("USEABLE_NUM").toString();
						}
						if(showDetailsById.get("TESTING_NUM")==null){
							testNum = "0";
						}else{
							testNum =showDetailsById.get("TESTING_NUM").toString();
						}
						if(showDetailsById.get("COMPLETE_NUM")==null){
							completeNum = "0";
						}else{
							completeNum =showDetailsById.get("COMPLETE_NUM").toString();
						}
						if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
							okOuttems = "0";
						}else{
							okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
						}

						if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
							ngOuttems = "0";
						}else{
							ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
						}

						String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
						if(String.valueOf(i).equals("1")){
							if(!processTypeInfoxl.equals("3")){
								if(planNum.equals(pushDownNum)){//非数据绑定

								}else{//+1
									countNewNum++;
								}
							}else{
								if(testNumTem.equals(planNum)){

								}else{//+1
									countNewNum++;
								}
							}
						}else{
							if(!processTypeInfoxl.equals("3")){
								if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){//非数据绑定

								}else{//+1
									countNewNum++;
								}
							}else{

								if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){

								}else{//+1
									countNewNum++;
								}
							}
						}
					}
					}
				if(countNewNum>0){
					return Rjson.error(202, "当前工序与待插入工序之间存在数据绑定，不支持编辑操作");
				}

				for(Map mapxx:showTaskByRouteId){

					if(Integer.parseInt(serialNo)> Integer.parseInt(oldSerialNo)){
						maxSerialNo=Integer.parseInt(serialNo);
						minSerialNo = Integer.parseInt(oldSerialNo);
					}else{
						maxSerialNo=Integer.parseInt(oldSerialNo);
						minSerialNo=Integer.parseInt(serialNo);
					}

					List<Map<String,Object>> showAllDDetailsInfoByTaskIds = null;
//					if(showAllDDetailsInfoByTaskIds.size()>0){
//						插入前保存当前序号的ID
						Integer initDetailsId = service.showMaxId(mapxx.get("ID").toString(), oldSerialNo);
						Integer addSerialNo = null;
						if(Integer.parseInt(serialNo)<Integer.parseInt(oldSerialNo)){//5插入到1
							 showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskIdAndArea(mapxx.get("ID").toString(), minSerialNo.toString(),maxSerialNo.toString());
							if(showAllDDetailsInfoByTaskIds.size()>0){
							 for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
								addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())+1;
								service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
								service.updateInitData(mapxx.get("ID").toString(), addSerialNo.toString());
							}
							}


						}else{//1插入到5
							 showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskIdAndAreaxx(mapxx.get("ID").toString(), minSerialNo.toString(),maxSerialNo.toString());
							if(showAllDDetailsInfoByTaskIds.size()>0){
							 for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
								addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())-1;
								service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
								service.updateInitData(mapxx.get("ID").toString(), addSerialNo.toString());
							}
							}
						}
//						if(showAllDDetailsInfoByTaskIds.size()>0){
						service.updateDetailsOrderNum(serialNo, initDetailsId.toString());
						service.updateInitData(mapxx.get("ID").toString(), serialNo.toString());

//						更新插入工序
						if(serialNo.equals("1")){
//							更新首工序
//							获取最新首工序的类型（委外、非委外）
							Integer stIds = service.stId(routeId, serialNo);
							String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
//							判断加入的工序是否为委外工序
							if(processTypeInfoxls.equals("3")){//委外
								  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
							}else{//非委外
								  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("ID").toString()));
							}
						}
						if(oldSerialNo.equals("1")){
//							更新首工序
//							获取最新首工序的类型（委外、非委外）
							Integer stIds = service.stId(routeId, oldSerialNo);
							String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
//							判断加入的工序是否为委外工序
							if(processTypeInfoxls.equals("3")){//委外
								  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
							}else{//非委外
								  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("ID").toString()));
							}
						}
//						}

//					}else{//未更新工序序号,不做操作
//
//					}

				}

//				更新way数据
//				1获取工艺路线的工序  2获取当前工序的ID 3更新serialNo 4更新当前工序的顺序
				List<Map<String,Object>> showAllProductWay = service.showProductWayByRouteId(routeId);
				if(showAllProductWay.size()>0){
					String productWayId = service.showProductWayIdxxs(routeId, oldSerialNo);
					Integer minserialNo = null;
					Integer maxserialNo=null;
					if(Integer.parseInt(serialNo)>Integer.parseInt(oldSerialNo)){
						minserialNo=Integer.parseInt(oldSerialNo);
						maxserialNo = Integer.parseInt(serialNo);
					}else{
						minserialNo=Integer.parseInt(serialNo);
						maxserialNo = Integer.parseInt(oldSerialNo);
					}
					List<Map<String,Object>> showAllWayArea = null;
					if(Integer.parseInt(serialNo)<Integer.parseInt(oldSerialNo)){
						Integer newWayOrder = 0;
						showAllWayArea = service.showAllWayInfoByTaskIdAndArea(routeId, minserialNo.toString(), maxserialNo.toString());
						for(Map wayId:showAllWayArea){
							System.out.println("ID:"+wayId.get("ID").toString());
							newWayOrder = Integer.parseInt(wayId.get("SERIAL_NO").toString())+1;
							service.updateWayDataInfos(newWayOrder.toString(), wayId.get("ID").toString());
						}
						service.updateWayDataInfos(serialNo,productWayId);
//						更新单件运行时间
						service.updateWayDataInfosx(runtimes, productWayId.toString(),processRemarks);
//						打印日志

						//获取客户端IP地址
						ShowIPInfo ip = new ShowIPInfo();
						String ipInfo = ip.getIpAddr(request);
//						获取用户名
						String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息


					  ProcessLogModel model = new ProcessLogModel();
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//					 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "编辑工艺工序",oldSerialNo,serialNo);
					  System.out.println("log:"+logInfo);
					  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "编辑工艺工序");
					}else{
						Integer newWayOrder = 0;
						showAllWayArea = service.showAllWayInfoByTaskIdAndAreaxx(routeId, minserialNo.toString(), maxserialNo.toString());
						for(Map wayId:showAllWayArea){
							newWayOrder = Integer.parseInt(wayId.get("SERIAL_NO").toString())-1;
							service.updateWayDataInfos(newWayOrder.toString(), wayId.get("ID").toString());
						}
						service.updateWayDataInfos(serialNo,productWayId);
//						更新单件运行时间
						service.updateWayDataInfosx(runtimes, productWayId.toString(),processRemarks);
//						打印日志

						//获取客户端IP地址
						ShowIPInfo ip = new ShowIPInfo();
						String ipInfo = ip.getIpAddr(request);
//						获取用户名
						String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息


					  ProcessLogModel model = new ProcessLogModel();
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//					 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "编辑工艺工序",oldSerialNo,serialNo);
					  System.out.println("log:"+logInfo);
					  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "编辑工艺工序");
					}
				}

			}else{
				if(Integer.parseInt(serialNo)>maxWayId){
					return Rjson.error(202, "编辑的工序序号不能超过工艺路线的最大工序序号");
				}
//				1获取工艺路线的工序  2获取当前工序的ID 3更新serialNo 4更新当前工序的顺序
				List<Map<String,Object>> showAllProductWay = service.showProductWayByRouteId(routeId);
				if(showAllProductWay.size()>0){
					String productWayId = service.showProductWayIdxxs(routeId, oldSerialNo);
					Integer minserialNo = null;
					Integer maxserialNo=null;
					if(Integer.parseInt(serialNo)>Integer.parseInt(oldSerialNo)){
						minserialNo=Integer.parseInt(oldSerialNo);
						maxserialNo = Integer.parseInt(serialNo);
					}else{
						minserialNo=Integer.parseInt(serialNo);
						maxserialNo = Integer.parseInt(oldSerialNo);
					}
					List<Map<String,Object>> showAllWayArea = null;
					if(Integer.parseInt(serialNo)<Integer.parseInt(oldSerialNo)){
						showAllWayArea = service.showAllWayInfoByTaskIdAndArea(routeId, minserialNo.toString(), maxserialNo.toString());
						Integer newWayOrder = 0;
						for(Map wayId:showAllWayArea){
							newWayOrder = Integer.parseInt(wayId.get("SERIAL_NO").toString())+1;
							service.updateWayDataInfos(newWayOrder.toString(), wayId.get("ID").toString());
						}
						service.updateWayDataInfos(serialNo,productWayId);
//						更新单件运行时间
						service.updateWayDataInfosx(runtimes, productWayId.toString(),processRemarks);
//						打印日志

						//获取客户端IP地址
						ShowIPInfo ip = new ShowIPInfo();
						String ipInfo = ip.getIpAddr(request);
//						获取用户名
						String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息


					  ProcessLogModel model = new ProcessLogModel();
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//					 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "编辑工艺工序",oldSerialNo,serialNo);
					  System.out.println("log:"+logInfo);
					  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "编辑工艺工序");
					}else{
						showAllWayArea = service.showAllWayInfoByTaskIdAndAreaxx(routeId, minserialNo.toString(), maxserialNo.toString());
						Integer newWayOrder = 0;
						for(Map wayId:showAllWayArea){
							newWayOrder = Integer.parseInt(wayId.get("SERIAL_NO").toString())-1;
							service.updateWayDataInfos(newWayOrder.toString(), wayId.get("ID").toString());
						}
						service.updateWayDataInfos(serialNo,productWayId);
//						更新单件运行时间
						service.updateWayDataInfosx(runtimes, productWayId.toString(),processRemarks);
//						打印日志

						//获取客户端IP地址
						ShowIPInfo ip = new ShowIPInfo();
						String ipInfo = ip.getIpAddr(request);
//						获取用户名
						String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息


					  ProcessLogModel model = new ProcessLogModel();
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//					 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "编辑工艺工序",oldSerialNo,serialNo);
					  System.out.println("log:"+logInfo);
					  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "编辑工艺工序");
					}
				 }
			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	删除工艺路线清单列表
	@RequestMapping(value = "/delRouteLine", method = RequestMethod.POST)
	@ApiOperation(value = "删除工艺路线", notes = "删除工艺路线")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delRouteLine(HttpServletRequest request) throws ServicesException {
		try {
//			获取routeID STATIONid  wayID
			String projectNum = request.getParameter("projectNum");
			String stationNum = request.getParameter("stationNum");
			String specificationAndModel = request.getParameter("specificationAndModel");
			String name = request.getParameter("name");

			String stationName = request.getParameter("stationName");

			String serialNo = request.getParameter("serialNo");
			String processRemarks = request.getParameter("processRemarks");

			Map<String,Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
			String routeId = countRoute.get("ID").toString();
			Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);

			List<Map<String,Object>> showTaskByRouteId = service.showAllTaskByRouteId(routeId);//是否存在task
			if(showTaskByRouteId.size()>0){//存在绑定的task
//				该工艺路线存在task，不允许删除成品入库
				if(stationId==409){
					return Rjson.error(202, "该工艺路线存在绑定的任务单，不支持删除成品入库");
				}
				Integer countNum=0;
				for(Map mapxx:showTaskByRouteId){
					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
					String testNumTem=null;
					String planNum = null;
					String pushDownNum=null;
					String ngNum=null;
					String useableNum = null;
					String testNum=null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if(showDetailsById.get("PLAN_NUM")==null){
						planNum = "0";
					}else{
						planNum =showDetailsById.get("PLAN_NUM").toString();
					}
					if(showDetailsById.get("PUSH_DOWN_NUM")==null){
						pushDownNum = "0";
					}else{
						pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM_TEM")==null){
						testNumTem = "0";
					}else{
						testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
					}
					if(showDetailsById.get("NG_NUM")==null){
						ngNum = "0";
					}else{
						ngNum =showDetailsById.get("NG_NUM").toString();
					}
					if(showDetailsById.get("USEABLE_NUM")==null){
						useableNum = "0";
					}else{
						useableNum =showDetailsById.get("USEABLE_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM")==null){
						testNum = "0";
					}else{
						testNum =showDetailsById.get("TESTING_NUM").toString();
					}
					if(showDetailsById.get("COMPLETE_NUM")==null){
						completeNum = "0";
					}else{
						completeNum =showDetailsById.get("COMPLETE_NUM").toString();
					}
					if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
						okOuttems = "0";
					}else{
						okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
					}

					if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
						ngOuttems = "0";
					}else{
						ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
					}



					String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
					if(serialNo.equals("1")){
						if(!processTypeInfoxl.equals("3")){
							if(planNum.equals(pushDownNum)){//非数据绑定

							}else{//+1
								countNum++;
							}
						}else{
							if(testNumTem.equals(planNum)){

							}else{//+1
								countNum++;
							}
						}
					}else{
						if(!processTypeInfoxl.equals("3")){
							if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){//非数据绑定

							}else{//+1
								countNum++;
							}
						}else{

							if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){

							}else{//+1
								countNum++;
							}
						}
					}

				}
				if(countNum>0){
					return Rjson.error(202, "存在数据绑定不允许删除");
				}


				if(serialNo.equals("1")){//删除首工序
//					判断首工序是否存在数据绑定
					for(Map mapxx:showTaskByRouteId){
						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
						Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());


//						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
//						Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
						String testNumTem=null;
						String planNum = null;
						String pushDownNum=null;
						String ngNum=null;
						String useableNum = null;
						String testNum=null;
						String completeNum = null;
						String okOuttems = null;
						String ngOuttems = null;

						if(showDetailsById.get("PLAN_NUM")==null){
							planNum = "0";
						}else{
							planNum =showDetailsById.get("PLAN_NUM").toString();
						}
						if(showDetailsById.get("PUSH_DOWN_NUM")==null){
							pushDownNum = "0";
						}else{
							pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
						}
						if(showDetailsById.get("TESTING_NUM_TEM")==null){
							testNumTem = "0";
						}else{
							testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
						}
						if(showDetailsById.get("NG_NUM")==null){
							ngNum = "0";
						}else{
							ngNum =showDetailsById.get("NG_NUM").toString();
						}
						if(showDetailsById.get("USEABLE_NUM")==null){
							useableNum = "0";
						}else{
							useableNum =showDetailsById.get("USEABLE_NUM").toString();
						}
						if(showDetailsById.get("TESTING_NUM")==null){
							testNum = "0";
						}else{
							testNum =showDetailsById.get("TESTING_NUM").toString();
						}
						if(showDetailsById.get("COMPLETE_NUM")==null){
							completeNum = "0";
						}else{
							completeNum =showDetailsById.get("COMPLETE_NUM").toString();
						}
						if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
							okOuttems = "0";
						}else{
							okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
						}

						if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
							ngOuttems = "0";
						}else{
							ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
						}



//						判断是否为委外工序
						String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
						if(!processTypeInfoxl.equals("3")){//首工序非委外
							if(planNum.equals(pushDownNum)){//非数据绑定
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
								if(serialNo.equals("1")){//删除首工序
									List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if(showLastProcessInfo.size()!=0){
										Integer newSerialNo=null;
										for(Map mapx:showLastProcessInfo){
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}else{//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}
//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


								  ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
								 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
								  System.out.println("log:"+logInfo);
								  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");

								}else{//删除非首工序
									List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if(showLastProcessInfo.size()!=0){
										Integer newSerialNo=null;
										for(Map mapx:showLastProcessInfo){
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
									}
								    }
//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


								  ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
								 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
								  System.out.println("log:"+logInfo);
								  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
				      			}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if(showAllDDetailsInfoByTaskIds.size()>0){
									Integer addSerialNo = null;
									for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
										addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())-1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
//									更新首工序
//									获取最新首工序的类型（委外、非委外）
									Integer stIds = service.stId(routeId, serialNo);
									String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
//									判断加入的工序是否为委外工序
									if(processTypeInfoxls.equals("3")){//委外
										  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									}else{//非委外
										  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("ID").toString()));
									}
								}else{//删除工序后无工序删除任务单
									service.delDetailsInfos(mapxx.get("ID").toString());
									service.delOutsideListInfos(mapxx.get("ID").toString());
									service.delTaskInfos(mapxx.get("ID").toString());
								}


							}
//							else{//存在数据绑定 已经在之前判断过了
//
//							}
						}else{//首工序委外
							if(testNumTem.equals(planNum)){
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
								if(serialNo.equals("1")){//删除首工序
									List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if(showLastProcessInfo.size()!=0){
										Integer newSerialNo=null;
										for(Map mapx:showLastProcessInfo){
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}else{//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}

//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


								  ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
								 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
								  System.out.println("log:"+logInfo);
								  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");

								}else{//删除非首工序
									List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if(showLastProcessInfo.size()!=0){
										Integer newSerialNo=null;
										for(Map mapx:showLastProcessInfo){
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
									}
								    }

//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


								  ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
								 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
								  System.out.println("log:"+logInfo);
								  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");

				      			}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if(showAllDDetailsInfoByTaskIds.size()>0){
									Integer addSerialNo = null;
									for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
										addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())-1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
									Integer stIds = service.stId(routeId, serialNo);
									String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
									if(processTypeInfoxls.equals("3")){//委外
										  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									}else{//非委外
										  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()),Integer.parseInt(mapxx.get("ID").toString()));
									}
								}else{//删除工序后无工序删除任务单
									service.delDetailsInfos(mapxx.get("ID").toString());
									service.delOutsideListInfos(mapxx.get("ID").toString());
									service.delTaskInfos(mapxx.get("ID").toString());
								}

							}
						}

					}


				}else{//删除非首工序
					for(Map mapxx:showTaskByRouteId){
					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
//					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
//					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
					String testNumTem=null;
					String planNum = null;
					String pushDownNum=null;
					String ngNum=null;
					String useableNum = null;
					String testNum=null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if(showDetailsById.get("PLAN_NUM")==null){
						planNum = "0";
					}else{
						planNum =showDetailsById.get("PLAN_NUM").toString();
					}
					if(showDetailsById.get("PUSH_DOWN_NUM")==null){
						pushDownNum = "0";
					}else{
						pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM_TEM")==null){
						testNumTem = "0";
					}else{
						testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
					}
					if(showDetailsById.get("NG_NUM")==null){
						ngNum = "0";
					}else{
						ngNum =showDetailsById.get("NG_NUM").toString();
					}
					if(showDetailsById.get("USEABLE_NUM")==null){
						useableNum = "0";
					}else{
						useableNum =showDetailsById.get("USEABLE_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM")==null){
						testNum = "0";
					}else{
						testNum =showDetailsById.get("TESTING_NUM").toString();
					}
					if(showDetailsById.get("COMPLETE_NUM")==null){
						completeNum = "0";
					}else{
						completeNum =showDetailsById.get("COMPLETE_NUM").toString();
					}
					if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
						okOuttems = "0";
					}else{
						okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
					}

					if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
						ngOuttems = "0";
					}else{
						ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
					}


//					判断是否为委外工序
					String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
					if(!processTypeInfoxl.equals("3")){//首工序非委外
						if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){//非数据绑定
							service.delWayProductInfos(stationId.toString(), routeId, serialNo);
							if(serialNo.equals("1")){//删除首工序
								List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
								if(showLastProcessInfo.size()!=0){
									Integer newSerialNo=null;
									for(Map mapx:showLastProcessInfo){
										newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
										service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
									}
								}else{//只有首工序，也被删除需要删除routing数据
									service.delRouteLine(routeId);
								}
//								打印日志

								//获取客户端IP地址
								ShowIPInfo ip = new ShowIPInfo();
								String ipInfo = ip.getIpAddr(request);
//								获取用户名
								String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


							  ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
							 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
							  System.out.println("log:"+logInfo);
							  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
							}else{//删除非首工序
								List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
								if(showLastProcessInfo.size()!=0){
									Integer newSerialNo=null;
									for(Map mapx:showLastProcessInfo){
										newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
										service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
								}
							    }
//								打印日志

								//获取客户端IP地址
								ShowIPInfo ip = new ShowIPInfo();
								String ipInfo = ip.getIpAddr(request);
//								获取用户名
								String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


							  ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
							 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
							  System.out.println("log:"+logInfo);
							  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");

			      			}
							service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
							List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
							if(showAllDDetailsInfoByTaskIds.size()>0){
								Integer addSerialNo = null;
								for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
									addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())-1;
									service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
								}
							}

						}
					}else{
						if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){
							service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
							if(serialNo.equals("1")){//删除首工序
								List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
								if(showLastProcessInfo.size()!=0){
									Integer newSerialNo=null;
									for(Map mapx:showLastProcessInfo){
										newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
										service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
									}
								}else{//只有首工序，也被删除需要删除routing数据
									service.delRouteLine(routeId);
								}
//								打印日志

								//获取客户端IP地址
								ShowIPInfo ip = new ShowIPInfo();
								String ipInfo = ip.getIpAddr(request);
//								获取用户名
								String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


							  ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
							 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
							  System.out.println("log:"+logInfo);
							  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
							}else{//删除非首工序
								List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
								if(showLastProcessInfo.size()!=0){
									Integer newSerialNo=null;
									for(Map mapx:showLastProcessInfo){
										newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
										service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
								}
							    }

//								打印日志

								//获取客户端IP地址
								ShowIPInfo ip = new ShowIPInfo();
								String ipInfo = ip.getIpAddr(request);
//								获取用户名
								String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


							  ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
							 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
							  System.out.println("log:"+logInfo);
							  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
			      			}
							service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
							List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
							if(showAllDDetailsInfoByTaskIds.size()>0){
								Integer addSerialNo = null;
								for(Map mapxss:showAllDDetailsInfoByTaskIds){//更新后续的数据order
									addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())-1;
									service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
								}
							}
						}
					}

					}
				}


			}else{//不存在绑定的task
				service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除工序
				if(serialNo.equals("1")){//删除首工序
					List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
					if(showLastProcessInfo.size()!=0){
						Integer newSerialNo=null;
						for(Map mapx:showLastProcessInfo){
							newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
							service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
						}
					}else{//只有首工序，也被删除需要删除routing数据
						service.delRouteLine(routeId);
					}

//					打印日志

					//获取客户端IP地址
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
//					获取用户名
					String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息


				  ProcessLogModel model = new ProcessLogModel();
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//				 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
				 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
				  System.out.println("log:"+logInfo);
				  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
				}else{//删除非首工序
					List<Map<String,Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
					if(showLastProcessInfo.size()!=0){
						Integer newSerialNo=null;
						for(Map mapx:showLastProcessInfo){
							newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())-1;
							service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
					}
				    }

//					打印日志

					//获取客户端IP地址
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
//					获取用户名
					String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息


				  ProcessLogModel model = new ProcessLogModel();
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//				 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
				 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序",serialNo,"");
				  System.out.println("log:"+logInfo);
				  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "删除工艺工序");
      			}
			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	//	删除工艺路线清单列表
	@RequestMapping(value = "/delRouteLineList", method = RequestMethod.POST)
	@ApiOperation(value = "批量删除工艺路线", notes = "批量删除工艺路线")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delRouteLineList(HttpServletRequest request, @RequestBody List<Map<String,Object>> mapList ) throws ServicesException {
		try {
//			逻辑删除
			System.out.println("asdasdasdsa");
			System.out.println("asdasdasdsa2");
			System.out.println("asdasdasdsa3");
			System.out.println("asdasdasdsa4");

			for (Map map:mapList){
//			获取routeID STATIONid  wayID
				String projectNum = map.get("PROJECT_NUMX").toString();
				String stationNum = map.get("STATION_NUMX").toString();
				String specificationAndModel=map.get("SPECIFICATION_AND_MODELX").toString();
				String name = map.get("NAME").toString();
				String stationName = map.get("STATION_NAME").toString();
				String serialNo = map.get("SERIAL_NO").toString();
				String processRemarks =map.get("PROCESS_REMARKS").toString();
//			String projectNum = request.getParameter("projectNum");
//			String stationNum = request.getParameter("stationNum");
//			String specificationAndModel = request.getParameter("specificationAndModel");
//			String name = request.getParameter("name");

//			String stationName = request.getParameter("stationName");

//			String serialNo = request.getParameter("serialNo");
//			String processRemarks = request.getParameter("processRemarks");

			Map<String, Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
			String routeId = countRoute.get("ID").toString();
			Integer stationId = service.showStationId(stationName) == null ? -1 : service.showStationId(stationName);

			List<Map<String, Object>> showTaskByRouteId = service.showAllTaskByRouteId(routeId);//是否存在task
			if (showTaskByRouteId.size() > 0) {//存在绑定的task
//				该工艺路线存在task，不允许删除成品入库
				if (stationId == 409) {
					return Rjson.error(202, "该工艺路线存在绑定的任务单，不支持删除成品入库");
				}
				Integer countNum = 0;
				for (Map mapxx : showTaskByRouteId) {
					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
					Map<String, Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
					String testNumTem = null;
					String planNum = null;
					String pushDownNum = null;
					String ngNum = null;
					String useableNum = null;
					String testNum = null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if (showDetailsById.get("PLAN_NUM") == null) {
						planNum = "0";
					} else {
						planNum = showDetailsById.get("PLAN_NUM").toString();
					}
					if (showDetailsById.get("PUSH_DOWN_NUM") == null) {
						pushDownNum = "0";
					} else {
						pushDownNum = showDetailsById.get("PUSH_DOWN_NUM").toString();
					}
					if (showDetailsById.get("TESTING_NUM_TEM") == null) {
						testNumTem = "0";
					} else {
						testNumTem = showDetailsById.get("TESTING_NUM_TEM").toString();
					}
					if (showDetailsById.get("NG_NUM") == null) {
						ngNum = "0";
					} else {
						ngNum = showDetailsById.get("NG_NUM").toString();
					}
					if (showDetailsById.get("USEABLE_NUM") == null) {
						useableNum = "0";
					} else {
						useableNum = showDetailsById.get("USEABLE_NUM").toString();
					}
					if (showDetailsById.get("TESTING_NUM") == null) {
						testNum = "0";
					} else {
						testNum = showDetailsById.get("TESTING_NUM").toString();
					}
					if (showDetailsById.get("COMPLETE_NUM") == null) {
						completeNum = "0";
					} else {
						completeNum = showDetailsById.get("COMPLETE_NUM").toString();
					}
					if (showDetailsById.get("OK_OUTSOURCE_TEMS") == null) {
						okOuttems = "0";
					} else {
						okOuttems = showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
					}

					if (showDetailsById.get("NG_OUTSOURCE_TEMS") == null) {
						ngOuttems = "0";
					} else {
						ngOuttems = showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
					}


					String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
					if (serialNo.equals("1")) {
						if (!processTypeInfoxl.equals("3")) {
							if (planNum.equals(pushDownNum)) {//非数据绑定

							} else {//+1
								countNum++;
							}
						} else {
							if (testNumTem.equals(planNum)) {

							} else {//+1
								countNum++;
							}
						}
					} else {
						if (!processTypeInfoxl.equals("3")) {
							if (pushDownNum.equals("0") && ngNum.equals("0") && useableNum.equals("0") && testNum.equals("0") && completeNum.equals("0")) {//非数据绑定

							} else {//+1
								countNum++;
							}
						} else {

							if (testNumTem.equals("0") && okOuttems.equals("0") && ngOuttems.equals("0") && completeNum.equals("0")) {

							} else {//+1
								countNum++;
							}
						}
					}

				}
				if (countNum > 0) {
					return Rjson.error(202, "存在数据绑定不允许删除");
				}


				if (serialNo.equals("1")) {//删除首工序
//					判断首工序是否存在数据绑定
					for (Map mapxx : showTaskByRouteId) {
						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
						Map<String, Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());


//						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
//						Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
						String testNumTem = null;
						String planNum = null;
						String pushDownNum = null;
						String ngNum = null;
						String useableNum = null;
						String testNum = null;
						String completeNum = null;
						String okOuttems = null;
						String ngOuttems = null;

						if (showDetailsById.get("PLAN_NUM") == null) {
							planNum = "0";
						} else {
							planNum = showDetailsById.get("PLAN_NUM").toString();
						}
						if (showDetailsById.get("PUSH_DOWN_NUM") == null) {
							pushDownNum = "0";
						} else {
							pushDownNum = showDetailsById.get("PUSH_DOWN_NUM").toString();
						}
						if (showDetailsById.get("TESTING_NUM_TEM") == null) {
							testNumTem = "0";
						} else {
							testNumTem = showDetailsById.get("TESTING_NUM_TEM").toString();
						}
						if (showDetailsById.get("NG_NUM") == null) {
							ngNum = "0";
						} else {
							ngNum = showDetailsById.get("NG_NUM").toString();
						}
						if (showDetailsById.get("USEABLE_NUM") == null) {
							useableNum = "0";
						} else {
							useableNum = showDetailsById.get("USEABLE_NUM").toString();
						}
						if (showDetailsById.get("TESTING_NUM") == null) {
							testNum = "0";
						} else {
							testNum = showDetailsById.get("TESTING_NUM").toString();
						}
						if (showDetailsById.get("COMPLETE_NUM") == null) {
							completeNum = "0";
						} else {
							completeNum = showDetailsById.get("COMPLETE_NUM").toString();
						}
						if (showDetailsById.get("OK_OUTSOURCE_TEMS") == null) {
							okOuttems = "0";
						} else {
							okOuttems = showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
						}

						if (showDetailsById.get("NG_OUTSOURCE_TEMS") == null) {
							ngOuttems = "0";
						} else {
							ngOuttems = showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
						}


//						判断是否为委外工序
						String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
						if (!processTypeInfoxl.equals("3")) {//首工序非委外
							if (planNum.equals(pushDownNum)) {//非数据绑定
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
								if (serialNo.equals("1")) {//删除首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									} else {//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}
//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");

								} else {//删除非首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}
//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
								}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String, Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if (showAllDDetailsInfoByTaskIds.size() > 0) {
									Integer addSerialNo = null;
									for (Map mapxss : showAllDDetailsInfoByTaskIds) {//更新后续的数据order
										addSerialNo = Integer.parseInt(mapxss.get("PROCESS_ORDER").toString()) - 1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
//									更新首工序
//									获取最新首工序的类型（委外、非委外）
									Integer stIds = service.stId(routeId, serialNo);
									String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
//									判断加入的工序是否为委外工序
									if (processTypeInfoxls.equals("3")) {//委外
										servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									} else {//非委外
										servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									}
								} else {//删除工序后无工序删除任务单
									service.delDetailsInfos(mapxx.get("ID").toString());
									service.delOutsideListInfos(mapxx.get("ID").toString());
									service.delTaskInfos(mapxx.get("ID").toString());
								}


							}
//							else{//存在数据绑定 已经在之前判断过了
//
//							}
						} else {//首工序委外
							if (testNumTem.equals(planNum)) {
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
								if (serialNo.equals("1")) {//删除首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									} else {//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}

//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");

								} else {//删除非首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}

//									打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//									获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//									获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//								  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//								 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");

								}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String, Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if (showAllDDetailsInfoByTaskIds.size() > 0) {
									Integer addSerialNo = null;
									for (Map mapxss : showAllDDetailsInfoByTaskIds) {//更新后续的数据order
										addSerialNo = Integer.parseInt(mapxss.get("PROCESS_ORDER").toString()) - 1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
									Integer stIds = service.stId(routeId, serialNo);
									String processTypeInfoxls = service.showStationTypeInfos(stIds.toString()); //委外判定
									if (processTypeInfoxls.equals("3")) {//委外
										servicesx.updateFirstProcessDetails(Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									} else {//非委外
										servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxx.get("PLAN_NUM").toString()), Integer.parseInt(mapxx.get("ID").toString()));
									}
								} else {//删除工序后无工序删除任务单
									service.delDetailsInfos(mapxx.get("ID").toString());
									service.delOutsideListInfos(mapxx.get("ID").toString());
									service.delTaskInfos(mapxx.get("ID").toString());
								}

							}
						}

					}


				} else {//删除非首工序
					for (Map mapxx : showTaskByRouteId) {
						Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
						Map<String, Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
//					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
//					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
						String testNumTem = null;
						String planNum = null;
						String pushDownNum = null;
						String ngNum = null;
						String useableNum = null;
						String testNum = null;
						String completeNum = null;
						String okOuttems = null;
						String ngOuttems = null;

						if (showDetailsById.get("PLAN_NUM") == null) {
							planNum = "0";
						} else {
							planNum = showDetailsById.get("PLAN_NUM").toString();
						}
						if (showDetailsById.get("PUSH_DOWN_NUM") == null) {
							pushDownNum = "0";
						} else {
							pushDownNum = showDetailsById.get("PUSH_DOWN_NUM").toString();
						}
						if (showDetailsById.get("TESTING_NUM_TEM") == null) {
							testNumTem = "0";
						} else {
							testNumTem = showDetailsById.get("TESTING_NUM_TEM").toString();
						}
						if (showDetailsById.get("NG_NUM") == null) {
							ngNum = "0";
						} else {
							ngNum = showDetailsById.get("NG_NUM").toString();
						}
						if (showDetailsById.get("USEABLE_NUM") == null) {
							useableNum = "0";
						} else {
							useableNum = showDetailsById.get("USEABLE_NUM").toString();
						}
						if (showDetailsById.get("TESTING_NUM") == null) {
							testNum = "0";
						} else {
							testNum = showDetailsById.get("TESTING_NUM").toString();
						}
						if (showDetailsById.get("COMPLETE_NUM") == null) {
							completeNum = "0";
						} else {
							completeNum = showDetailsById.get("COMPLETE_NUM").toString();
						}
						if (showDetailsById.get("OK_OUTSOURCE_TEMS") == null) {
							okOuttems = "0";
						} else {
							okOuttems = showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
						}

						if (showDetailsById.get("NG_OUTSOURCE_TEMS") == null) {
							ngOuttems = "0";
						} else {
							ngOuttems = showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
						}


//					判断是否为委外工序
						String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
						if (!processTypeInfoxl.equals("3")) {//首工序非委外
							if (pushDownNum.equals("0") && ngNum.equals("0") && useableNum.equals("0") && testNum.equals("0") && completeNum.equals("0")) {//非数据绑定
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);
								if (serialNo.equals("1")) {//删除首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									} else {//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}
//								打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//								获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
								} else {//删除非首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}
//								打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//								获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");

								}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String, Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if (showAllDDetailsInfoByTaskIds.size() > 0) {
									Integer addSerialNo = null;
									for (Map mapxss : showAllDDetailsInfoByTaskIds) {//更新后续的数据order
										addSerialNo = Integer.parseInt(mapxss.get("PROCESS_ORDER").toString()) - 1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
								}

							}
						} else {
							if (testNumTem.equals("0") && okOuttems.equals("0") && ngOuttems.equals("0") && completeNum.equals("0")) {
								service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除首工序
								if (serialNo.equals("1")) {//删除首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									} else {//只有首工序，也被删除需要删除routing数据
										service.delRouteLine(routeId);
									}
//								打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//								获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
								} else {//删除非首工序
									List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
									if (showLastProcessInfo.size() != 0) {
										Integer newSerialNo = null;
										for (Map mapx : showLastProcessInfo) {
											newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
											service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
										}
									}

//								打印日志

									//获取客户端IP地址
									ShowIPInfo ip = new ShowIPInfo();
									String ipInfo = ip.getIpAddr(request);
//								获取用户名
									String userName = ToolUtils.getCookieUserName(request);
//								获取任务单信息


									ProcessLogModel model = new ProcessLogModel();
//							  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//							 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
									String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
									System.out.println("log:" + logInfo);
									logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
								}
								service.delTaskDetailsInfo(mapxx.get("ID").toString(), serialNo);
								List<Map<String, Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapxx.get("ID").toString(), serialNo);
								if (showAllDDetailsInfoByTaskIds.size() > 0) {
									Integer addSerialNo = null;
									for (Map mapxss : showAllDDetailsInfoByTaskIds) {//更新后续的数据order
										addSerialNo = Integer.parseInt(mapxss.get("PROCESS_ORDER").toString()) - 1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
								}
							}
						}

					}
				}


			} else {//不存在绑定的task
				service.delWayProductInfos(stationId.toString(), routeId, serialNo);//删除工序
				if (serialNo.equals("1")) {//删除首工序
					List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
					if (showLastProcessInfo.size() != 0) {
						Integer newSerialNo = null;
						for (Map mapx : showLastProcessInfo) {
							newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
							service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
						}
					} else {//只有首工序，也被删除需要删除routing数据
						service.delRouteLine(routeId);
					}

//					打印日志

					//获取客户端IP地址
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
//					获取用户名
					String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息


					ProcessLogModel model = new ProcessLogModel();
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//				 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
					System.out.println("log:" + logInfo);
					logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
				} else {//删除非首工序
					List<Map<String, Object>> showLastProcessInfo = service.showProductWayBySTAndRoutes(routeId, serialNo);//删除后工序
					if (showLastProcessInfo.size() != 0) {
						Integer newSerialNo = null;
						for (Map mapx : showLastProcessInfo) {
							newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString()) - 1;
							service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
						}
					}

//					打印日志

					//获取客户端IP地址
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
//					获取用户名
					String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息


					ProcessLogModel model = new ProcessLogModel();
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//				 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "删除工艺工序", serialNo, "");
					System.out.println("log:" + logInfo);
					logInfos.addProcessLogInfo("-1", String.valueOf(stationId), logInfo, "删除工艺工序");
				}
			}
		}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



//	新增工艺路线清单列表
	@RequestMapping(value = "/addRouteLine", method = RequestMethod.POST)
	@ApiOperation(value = "新增工艺路线", notes = "新增工艺路线")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson addRouteLine(HttpServletRequest request) throws ServicesException {
		try {
			String projectNum = request.getParameter("projectNum");
			String stationNum = request.getParameter("stationNum");
			String specificationAndModel = request.getParameter("specificationAndModel");
			String name = request.getParameter("name");

			String stationName = request.getParameter("stationName");

			String serialNo = request.getParameter("serialNo");
			String processRemarks = request.getParameter("processRemarks");
			String runtimes = request.getParameter("runtimes");

			Map<String,Object> map = new HashMap<String, Object>();
//			Map<String,Object> map =null;  未分配内存报错
			map.put("projectNum", projectNum);
			map.put("stationNum", stationNum);
			map.put("specificationAndModel",specificationAndModel );
			map.put("name",name);
			map.put("stationName",stationName );
			map.put("serialNo",serialNo);
			map.put("processRemarks",processRemarks);
			map.put("runtimes",runtimes);
//			routing：项目号、工位号、规格型号、工件名称          station:工序名称    way：工序序号、备注

//			System.out.println(projectNum+""+stationNum+""+specificationAndModel+""+name+""+serialNo+""+stationName+""+processRemarks);
//			1、判断该工艺路线是否已经该顺序的工序ID way表



			String maxRouteId = null;
			Integer routIdById=null;
//			判断是否已经存在该工艺路线
			Map<String,Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
			if(Integer.parseInt(countRoute.get("count").toString())>0){
				maxRouteId = countRoute.get("ID").toString();
			}else{
				maxRouteId = service.showMaxRouteLineId()==null?"0":service.showMaxRouteLineId();

				routIdById = (Integer.parseInt(maxRouteId)+1);
				maxRouteId = routIdById.toString();
				service.addRouteLine(maxRouteId, projectNum, stationNum, specificationAndModel, name);//添加最新的routing
			}

//			查询Station对应的ID
			Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);
			if(stationId==-1){
				return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
			}
			List<Integer> showAllSTId = service.showAllStIdByWayId(maxRouteId.toString());
			for(Integer stId:showAllSTId){
//				System.out.println("打印st信息："+stId);
				if(stationId.toString().equals(stId.toString())){
					return Rjson.error(202,"工艺路线不能存在同名工序");
				}
			}
			Integer newMaxSerialNo =null;
					Integer maxSerialNo =null;
			if(stationId==409){

				maxSerialNo = service.showProductWayId(maxRouteId)==null?0:service.showProductWayId(maxRouteId);
				newMaxSerialNo=maxSerialNo+1;
				if(Integer.parseInt(serialNo)!=newMaxSerialNo){
					return Rjson.error(202, "成品入库必须为工艺最大工序");
				}
			}
//			新增way的数据
			Integer	countProductWay = service.countProductWay( maxRouteId.toString(), serialNo);
			if(countProductWay>0){//存在工序重叠（转移处理details与way）
				System.out.println(203);
				return Rjson.success(203,map);
			}else{//不存在工序重叠（单独处理）

//				更新任务单数据（确保当前最大的工序无绑定数据，否则有出现下推缺失）

				List<Map<String,Object>> showTaskByRouteId = service.showAllTaskByRouteId(maxRouteId.toString());
				Integer flags = 0;
				Integer flagss = 0;
				Integer flagsss = 0;
				for(Map mapx:showTaskByRouteId){
//					获取当前最大的details
					Integer maxDetailsOrder = service.showMaxDetailsIdByTaskId(mapx.get("ID").toString());
					Integer maxDetailsId = service.showMaxId(mapx.get("ID").toString(), maxDetailsOrder.toString());
					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());

					String testNumTem=null;
					String planNum = null;
					String pushDownNum=null;
					String ngNum=null;
					String useableNum = null;
					String testNum=null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if(showDetailsById.get("PLAN_NUM")==null){
						planNum = "0";
					}else{
						planNum =showDetailsById.get("PLAN_NUM").toString();
					}
					if(showDetailsById.get("PUSH_DOWN_NUM")==null){
						pushDownNum = "0";
					}else{
						pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM_TEM")==null){
						testNumTem = "0";
					}else{
						testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
					}
					if(showDetailsById.get("NG_NUM")==null){
						ngNum = "0";
					}else{
						ngNum =showDetailsById.get("NG_NUM").toString();
					}
					if(showDetailsById.get("USEABLE_NUM")==null){
						useableNum = "0";
					}else{
						useableNum =showDetailsById.get("USEABLE_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM")==null){
						testNum = "0";
					}else{
						testNum =showDetailsById.get("TESTING_NUM").toString();
					}
					if(showDetailsById.get("COMPLETE_NUM")==null){
						completeNum = "0";
					}else{
						completeNum =showDetailsById.get("COMPLETE_NUM").toString();
					}
					if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
						okOuttems = "0";
					}else{
						okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
					}

					if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
						ngOuttems = "0";
					}else{
						ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
					}



//				判断是否存在数据绑定（判断是否是委外的工序）
//					String processTypeInfoxl = service.showStationTypeInfos(stationId.toString());
					String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString());
//					判断是否为首工序
					String processOrder = showDetailsById.get("PROCESS_ORDER").toString();
					Integer contrastNum = Integer.parseInt(processOrder)+1;

					if(contrastNum==Integer.parseInt(serialNo)){//确保工序号是连续的

					if(processOrder.equals("1")){//最大的工序为首工序，即只存在一道工序
						if(!processTypeInfoxl.equals("3")){//首工序非委外
							if(planNum.equals(pushDownNum)){
//								判断是否只有成品入库
								Integer maxNumx = service.showProductWayId(maxRouteId);
								Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
								if(stId==409){
									return Rjson.error(202, "不能在成品入库后添加工序");
								}

								service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
								service.updateInitData(mapx.get("ID").toString(), serialNo);
								//判断初始化新增的details

//								servicesx.updateFirstProcessDetailsx(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
								flags++;
							}else{
//								累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
								flagss=1;

							}
						}else{//首工序委外
							if(testNumTem.equals(planNum)){
							    service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
							    service.updateInitData(mapx.get("ID").toString(), serialNo);
//							    servicesx.updateFirstProcessDetails(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
							    flags++;
							}else{
//								累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
								flagss=1;
							}
						}
					}else{//非首工序，判断的条件是不同的，首工序有原始数据
						if(!processTypeInfoxl.equals("3")){//非委外
							Integer maxNumx = service.showProductWayId(maxRouteId);
							Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
							if(stId==409){
								return Rjson.error(202, "不能在成品入库后添加工序");
							}
							if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){
								//未绑定数据

								service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
								service.updateInitData(mapx.get("ID").toString(), serialNo);
//								servicesx.updateFirstProcessDetailsx(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
								flags++;
							}else{
//								累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
								flagss=1;
							}
						}else{//委外OK_OUTSOURCE_TEMS NG_OUTSOURCE_TEMS COMPLETE_NUM
							if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){
								 service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
								 service.updateInitData(mapx.get("ID").toString(), serialNo);
//								 servicesx.updateFirstProcessDetails(Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()),Integer.parseInt(showDetailsById.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
								 flags++;
							}else{
//								累加数值去赋值error（首工序存在数据绑定，不支持新增操作）
								flagss=1;
							}
						}
					}
				}else{
//					添加一个数值用于说明存在不连续的序号error
					flagsss=1;
				}

				}
				if(flags>0){
					service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//					打印日志

					//获取客户端IP地址
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
//					获取用户名
					String userName = ToolUtils.getCookieUserName(request);
//					获取任务单信息


				  ProcessLogModel model = new ProcessLogModel();
//				  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//				 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
				 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
				  System.out.println("log:"+logInfo);
				  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "新增工艺工序");
				}
				if(flagss==1&&flagsss==1){
					return Rjson.error(202, "存在数据绑定或者序号不连续，不支持新增操作");
				}
				if(flagss==1){
					return Rjson.error(202, "存在数据绑定，不支持新增操作");
				}
				if(flagsss==1){
					return Rjson.error(202, "存在序号不连续，不支持新增操作");
				}


				Integer maxWayIds = service.showProductWayId(maxRouteId)==null?0:service.showProductWayId(maxRouteId);
				Integer newWayIds = maxWayIds+1;
				if(Integer.parseInt(serialNo)>newWayIds){
					return Rjson.error(202, "工艺路线添加的工序序号必须连续");
				}
//				如果不存在绑定的task
				if(showTaskByRouteId.size()==0){
					Integer maxWayNum = service.countProductWayNum(maxRouteId.toString());
					if(maxWayNum==0){//判断序号是否正常
						if(Integer.parseInt(serialNo)!=1){
							return Rjson.error(202, "工序序号必须从1开始");
						}else{
							service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//							打印日志

							//获取客户端IP地址
							ShowIPInfo ip = new ShowIPInfo();
							String ipInfo = ip.getIpAddr(request);
//							获取用户名
							String userName = ToolUtils.getCookieUserName(request);
//							获取任务单信息


						  ProcessLogModel model = new ProcessLogModel();
//						  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//						 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
						 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
						  System.out.println("log:"+logInfo);
						  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "新增工艺工序");
						}
					}else{
						Integer maxNumx = service.showProductWayId(maxRouteId);
						Integer stId = service.showRouteId(maxNumx.toString(), maxRouteId);
						if(stId==409){
							return Rjson.error(202, "不能在成品入库后添加工序");
						}
						service.addProductWay(stationId.toString(), maxRouteId.toString(), serialNo.toString(), processRemarks,runtimes);
//						打印日志

						//获取客户端IP地址
						ShowIPInfo ip = new ShowIPInfo();
						String ipInfo = ip.getIpAddr(request);
//						获取用户名
						String userName = ToolUtils.getCookieUserName(request);
//						获取任务单信息


					  ProcessLogModel model = new ProcessLogModel();
//					  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//					 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
					 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "新增工艺工序",serialNo,"");
					  System.out.println("log:"+logInfo);
					  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "新增工艺工序");
					}

				}
			}

			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


//	插入工艺路线指定工序
	@RequestMapping(value = "/addRouteProcessInfos", method = RequestMethod.POST)
	@ApiOperation(value = "插入工艺路线指定工序", notes = "插入工艺路线指定工序")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson addRouteProcessInfos(HttpServletRequest request) throws ServicesException {
		try {
//			1、判断当前插入的已经存在工序是否有数据绑定，（首工序与其他工序不同），若存在数据绑定则不允许插入，不存在则允许插入，并改变之后工序的序号
//			工艺路线的这条工序，是否存在，找到捆绑的task 循环task找到指定工序查看数据是否有捆绑
			String projectNum = request.getParameter("projectNum");
			String stationNum = request.getParameter("stationNum");
			String specificationAndModel = request.getParameter("specificationAndModel");
			String name = request.getParameter("name");

			String stationName = request.getParameter("stationName");

			String serialNo = request.getParameter("serialNo");
			String processRemarks = request.getParameter("processRemarks");
			String runtimes = request.getParameter("runtimes");
			Map<String,Object> countRoute = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
			Map<String,Object> mapxs = new HashMap<String, Object>();

			List<Map<String,Object>> showTaskByRouteId = service.showAllTaskByRouteId(countRoute.get("ID").toString());
			Integer countNumxs = 0;
			if(showTaskByRouteId.size()>0){//存在绑定的task

				Integer countNum=0;
				for(Map mapxx:showTaskByRouteId){
					Integer maxDetailsId = service.showMaxId(mapxx.get("ID").toString(), serialNo);
					Map<String,Object> showDetailsById = service.showDetailsById(maxDetailsId.toString());
					String testNumTem=null;
					String planNum = null;
					String pushDownNum=null;
					String ngNum=null;
					String useableNum = null;
					String testNum=null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if(showDetailsById.get("PLAN_NUM")==null){
						planNum = "0";
					}else{
						planNum =showDetailsById.get("PLAN_NUM").toString();
					}
					if(showDetailsById.get("PUSH_DOWN_NUM")==null){
						pushDownNum = "0";
					}else{
						pushDownNum =showDetailsById.get("PUSH_DOWN_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM_TEM")==null){
						testNumTem = "0";
					}else{
						testNumTem =showDetailsById.get("TESTING_NUM_TEM").toString();
					}
					if(showDetailsById.get("NG_NUM")==null){
						ngNum = "0";
					}else{
						ngNum =showDetailsById.get("NG_NUM").toString();
					}
					if(showDetailsById.get("USEABLE_NUM")==null){
						useableNum = "0";
					}else{
						useableNum =showDetailsById.get("USEABLE_NUM").toString();
					}
					if(showDetailsById.get("TESTING_NUM")==null){
						testNum = "0";
					}else{
						testNum =showDetailsById.get("TESTING_NUM").toString();
					}
					if(showDetailsById.get("COMPLETE_NUM")==null){
						completeNum = "0";
					}else{
						completeNum =showDetailsById.get("COMPLETE_NUM").toString();
					}
					if(showDetailsById.get("OK_OUTSOURCE_TEMS")==null){
						okOuttems = "0";
					}else{
						okOuttems =showDetailsById.get("OK_OUTSOURCE_TEMS").toString();
					}

					if(showDetailsById.get("NG_OUTSOURCE_TEMS")==null){
						ngOuttems = "0";
					}else{
						ngOuttems =showDetailsById.get("NG_OUTSOURCE_TEMS").toString();
					}



					String processTypeInfoxl = service.showStationTypeInfos(showDetailsById.get("PROCESS_ID").toString()); //委外判定
					if(serialNo.equals("1")){
						if(!processTypeInfoxl.equals("3")){
							if(planNum.equals(pushDownNum)){//非数据绑定

							}else{//+1
								countNum++;
							}
						}else{
							if(testNumTem.equals(planNum)){

							}else{//+1
								countNum++;
							}
						}
					}else{
						if(!processTypeInfoxl.equals("3")){
							if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){//非数据绑定

							}else{//+1
								countNum++;
							}
						}else{

							if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){

							}else{//+1
								countNum++;
							}
						}
					}

				}
				if(countNum>0){
					return Rjson.error(202, "序号工序存在数据绑定,不支持插入新工序");
				}





//				获取task的ID，然后去查询details，在details中的order中查看数据是否有绑定
			for(Map mapx:showTaskByRouteId){
//				查询的details
				 mapxs = service.showDetailsInfo(mapx.get("ID").toString(), serialNo);
				 String testNumTem=null;
					String planNum = null;
					String pushDownNum=null;
					String ngNum=null;
					String useableNum = null;
					String testNum=null;
					String completeNum = null;
					String okOuttems = null;
					String ngOuttems = null;

					if(mapxs.get("PLAN_NUM")==null){
						planNum = "0";
					}else{
						planNum =mapxs.get("PLAN_NUM").toString();
					}
					if(mapxs.get("PUSH_DOWN_NUM")==null){
						pushDownNum = "0";
					}else{
						pushDownNum =mapxs.get("PUSH_DOWN_NUM").toString();
					}
					if(mapxs.get("TESTING_NUM_TEM")==null){
						testNumTem = "0";
					}else{
						testNumTem =mapxs.get("TESTING_NUM_TEM").toString();
					}
					if(mapxs.get("NG_NUM")==null){
						ngNum = "0";
					}else{
						ngNum =mapxs.get("NG_NUM").toString();
					}
					if(mapxs.get("USEABLE_NUM")==null){
						useableNum = "0";
					}else{
						useableNum =mapxs.get("USEABLE_NUM").toString();
					}
					if(mapxs.get("TESTING_NUM")==null){
						testNum = "0";
					}else{
						testNum =mapxs.get("TESTING_NUM").toString();
					}
					if(mapxs.get("COMPLETE_NUM")==null){
						completeNum = "0";
					}else{
						completeNum =mapxs.get("COMPLETE_NUM").toString();
					}
					if(mapxs.get("OK_OUTSOURCE_TEMS")==null){
						okOuttems = "0";
					}else{
						okOuttems =mapxs.get("OK_OUTSOURCE_TEMS").toString();
					}

					if(mapxs.get("NG_OUTSOURCE_TEMS")==null){
						ngOuttems = "0";
					}else{
						ngOuttems =mapxs.get("NG_OUTSOURCE_TEMS").toString();
					}
				 if(mapxs!=null){
				 if(serialNo.equals("1")){//判断是否为首工序
//					 Integer processTypeInfosx= services.showNowProcesstypeInfo(projectNum, specificationAndModel, Integer.parseInt(mapxs.get("PROCESS_ID").toString()));//判断是否为委外
					 String processTypeInfosx=service.showStationTypeInfos(mapxs.get("PROCESS_ID").toString());
					 if(!processTypeInfosx.equals("3")){//首工序非委外  下推数量==计划数量
						 if(planNum.equals(pushDownNum)){//判断是否存在数据绑定
//							 1、搜索当前所有的details，然后序号+1  2、对首工位特殊赋值
							List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapx.get("ID").toString(), serialNo);
							Integer addSerialNo=null;
							Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
							if(stationId==-1){
								return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
							}

							for(Map mapxss:showAllDDetailsInfoByTaskIds){
//								更新序列号，获取details的ID然后进行更新
								addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())+1;
								service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
							}
//							更新首工序details，新增首工序数据

							service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(mapxs.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
							String processTypeInfoxl = service.showStationTypeInfos(stationId.toString());
//							判断加入的工序是否为委外工序
							if(processTypeInfoxl.equals("3")){//委外
								  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxs.get("PLAN_NUM").toString()),Integer.parseInt(mapxs.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
							}else{//非委外
								  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxs.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
							}

//							添加插入数据way
//							String routeId = countRoute.get("ID").toString();//routID
//
//							List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeId, serialNo);
//							Integer newSerialNo = null;
//							for(Map mapxxs:showAllWayInfo){
//								newSerialNo = Integer.parseInt(mapxxs.get("SERIAL_NO").toString())+1;
//								service.updateWayDataInfos(newSerialNo.toString(), mapxxs.get("ID").toString());
//							}
//							service.addProductWay(stationId.toString(), routeId, serialNo, processRemarks);//新增当前工位
//							更新指定task数据的details的下推数据

						 }else{
//							 return Rjson.error(202, "插入的序号已经存在绑定数据，不支持插入"); 不能return否则后续数据无法更新，可以采用计量方式提示报错
							 countNumxs++;
						 }
					 }else{//首工序委外   委外发出数量==计划数量
						 if(planNum.equals(testNumTem)){
							 List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapx.get("ID").toString(), serialNo);
								Integer addSerialNo=null;
								Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
								if(stationId==-1){
									return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
								}

								for(Map mapxss:showAllDDetailsInfoByTaskIds){
//									更新序列号，获取details的ID然后进行更新
									addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())+1;
									service.updateDetailsOrderNumx(addSerialNo.toString(), mapxss.get("ID").toString());
								}
								service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(mapxs.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
								String processTypeInfoxl = service.showStationTypeInfos(stationId.toString());
								if(processTypeInfoxl.equals("3")){//委外
									  servicesx.updateFirstProcessDetails(Integer.parseInt(mapxs.get("PLAN_NUM").toString()),Integer.parseInt(mapxs.get("PLAN_NUM").toString()), Integer.parseInt(mapx.get("ID").toString()));
								}else{//非委外
									  servicesx.updateFirstProcessDetailsx(Integer.parseInt(mapxs.get("PLAN_NUM").toString()),Integer.parseInt(mapx.get("ID").toString()));
								}
//								String routeId = countRoute.get("ID").toString();//routID
//								List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeId, serialNo);
//								Integer newSerialNo = null;
//								for(Map mapxxs:showAllWayInfo){
//									newSerialNo = Integer.parseInt(mapxxs.get("SERIAL_NO").toString())+1;
//									service.updateWayDataInfos(newSerialNo.toString(), mapxxs.get("ID").toString());
//								}
//								service.addProductWay(stationId.toString(), routeId, serialNo, processRemarks);//新增当前工位
						 }else{
							 countNumxs++;
						 }

					 }}else{//非首工序
						 String processTypeInfosx=service.showStationTypeInfos(mapxs.get("PROCESS_ID").toString());//判断是否为委外
						 if(!processTypeInfosx.equals("3")){//非委外
							 if(pushDownNum.equals("0")&&ngNum.equals("0")&&useableNum.equals("0")&&testNum.equals("0")&&completeNum.equals("0")){
								 List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapx.get("ID").toString(), serialNo);
									Integer addSerialNo=null;
									Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
									if(stationId==-1){
										return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
									}

									for(Map mapxss:showAllDDetailsInfoByTaskIds){
//										更新序列号，获取details的ID然后进行更新
										addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())+1;
										service.updateDetailsOrderNum(addSerialNo.toString(), mapxss.get("ID").toString());
									}
									service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(mapxs.get("PLAN_NUM").toString()), stationId.toString(), serialNo);//新增问题
									service.updateInitData(mapx.get("ID").toString(), serialNo);
//									String routeId = countRoute.get("ID").toString();//routID
//									List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeId, serialNo);
//									Integer newSerialNo = null;
//									for(Map mapxxs:showAllWayInfo){
//										newSerialNo = Integer.parseInt(mapxxs.get("SERIAL_NO").toString())+1;
//										service.updateWayDataInfos(newSerialNo.toString(), mapxxs.get("ID").toString());
//									}
//									service.addProductWay(stationId.toString(), routeId, serialNo, processRemarks);//新增当前工位
							 }else{
								 countNumxs++;
							 }
						 }else{//委外
							 if(testNumTem.equals("0")&&okOuttems.equals("0")&&ngOuttems.equals("0")&&completeNum.equals("0")){
								 List<Map<String,Object>> showAllDDetailsInfoByTaskIds = service.showAllDetailsInfoByTaskId(mapx.get("ID").toString(), serialNo);
									Integer addSerialNo=null;
									Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
									if(stationId==-1){
										return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
									}
									for(Map mapxss:showAllDDetailsInfoByTaskIds){
//										更新序列号，获取details的ID然后进行更新
										addSerialNo=Integer.parseInt(mapxss.get("PROCESS_ORDER").toString())+1;
										service.updateDetailsOrderNumx(addSerialNo.toString(), mapxss.get("ID").toString());
									}
									service.addProcessDetails(Integer.parseInt(mapx.get("ID").toString()), Integer.parseInt(mapxs.get("PLAN_NUM").toString()), stationId.toString(), serialNo);
									service.updateInitData(mapx.get("ID").toString(), serialNo);
//									String routeId = countRoute.get("ID").toString();//routID
//									List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeId, serialNo);
//									Integer newSerialNo = null;
//									for(Map mapxxs:showAllWayInfo){
//										newSerialNo = Integer.parseInt(mapxxs.get("SERIAL_NO").toString())+1;
//										service.updateWayDataInfos(newSerialNo.toString(), mapxxs.get("ID").toString());
//									}
//									service.addProductWay(stationId.toString(), routeId, serialNo, processRemarks);//新增当前工位
							 }else{
								 countNumxs++;
							 }
						 }
					 }

				 }
			}
			if(countNumxs>0){
				return Rjson.error(203, "存在工艺路线绑定多任务单，已经对未绑定数据工序进行更新");
			}
			String routeId = countRoute.get("ID").toString();//routID
			List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeId, serialNo);
			Integer newSerialNo = null;
			for(Map mapxxs:showAllWayInfo){
				newSerialNo = Integer.parseInt(mapxxs.get("SERIAL_NO").toString())+1;
				service.updateWayDataInfos(newSerialNo.toString(), mapxxs.get("ID").toString());
			}
			Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
			if(stationId==-1){
				return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
			}
			service.addProductWay(stationId.toString(), routeId, serialNo, processRemarks,runtimes);//新增当前工位
//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息


		  ProcessLogModel model = new ProcessLogModel();
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//		 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
		 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "插入工艺工序",serialNo,"");
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "插入工艺工序");

			}else{//更新details的详细数据   更新插入的way的数据
//				根据工位与工艺查询数据
				Integer stationId = service.showStationId(stationName)==null?-1:service.showStationId(stationName);//stationId
				if(stationId==-1){
					return Rjson.error(202,"工序名称不存在，请先创建工序【"+stationName+"】");
				}
				Map<String,Object> countRoutes = service.countRoutrLine(projectNum, stationNum, specificationAndModel);
				String routeLineId = countRoutes.get("ID").toString();
				List<Map<String,Object>> showAllWayInfo = service.showProductWayBySTAndRoute( routeLineId, serialNo);
				Integer newSerialNo = null;
				for(Map mapx:showAllWayInfo){
					newSerialNo = Integer.parseInt(mapx.get("SERIAL_NO").toString())+1;
					service.updateWayDataInfos(newSerialNo.toString(), mapx.get("ID").toString());
				}
				service.addProductWay(stationId.toString(), routeLineId, serialNo, processRemarks,runtimes);
//				打印日志

				//获取客户端IP地址
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
//				获取用户名
				String userName = ToolUtils.getCookieUserName(request);
//				获取任务单信息


			  ProcessLogModel model = new ProcessLogModel();
//			  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//			 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
			 String logInfo = model.addRouteInfo(userName, ipInfo, projectNum, specificationAndModel, stationName, stationNum, "插入工艺工序",serialNo,"");
			  System.out.println("log:"+logInfo);
			  logInfos.addProcessLogInfo("-1",String.valueOf(stationId), logInfo, "插入工艺工序");
			}
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}




}
