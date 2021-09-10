package com.skeqi.mes.controller.processFlows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ProcessLogModel;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.service.processFlows.ProcessClientService;
import com.skeqi.mes.service.processFlows.ProcessDetailsService;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.service.processFlows.ProcessTaskService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.ImportExcel;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "生产任务", description = "生产任务", produces = MediaType.APPLICATION_JSON)
public class ProcessTaskController {

	@Autowired
	private ProcessTaskService service;
	@Autowired
	private ProcessDetailsService services;
	@Autowired
	private ProcessLogInfoService logInfos;
	@Autowired
	private ProcessClientService servicex;

	static BASE64Encoder encoder = new BASE64Encoder();


	@RequestMapping(value = "/updateSattusInfox", method = RequestMethod.POST)
	@ApiOperation(value = "一键开始全部任务", notes = "一键开始全部任务")
	@ApiImplicitParams({

			})
	@ResponseBody
	public Rjson updateSattusInfox(HttpServletRequest request) throws ServicesException {

		try {

//			一键开始
			service.updateSattusInfox();
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


//	查询工艺路线下的工序
	@RequestMapping(value = "/showAllProcessInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询工艺路线下的工序", notes = "查询工艺路线下的工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工艺ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showAllProcessInfo(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			一键开始
			List<Map<String,Object>> list = service.showAllProcessInfo(id);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	新增工艺路线备注
	@RequestMapping(value = "/addRoutListInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增工艺路线备注", notes = "新增工艺路线备注")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routeName", value = "工艺ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工序ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "remarksInfos", value = "工序备注", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson addRoutListInfo(HttpServletRequest request) throws ServicesException {
		try {
			String routeName = request.getParameter("routeName");
			String processName = request.getParameter("processName");
			String remarksInfos = request.getParameter("remarksInfos");
//			一键开始
			if(service.countNum(routeName, processName)>0){//编辑
//				return Rjson.error(210,"不能重复添加该工艺同一工序备注");
				service.updateRoutListInfo(remarksInfos, routeName, processName);
				return Rjson.success();
			}else if(service.countNum(routeName, processName)==0){
				service.addRoutListInfo(routeName, processName, remarksInfos);
				return Rjson.success();
			}
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	查询工艺路线名称
	@RequestMapping(value = "/showRouteLineInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询工艺路线下名称", notes = "查询工艺路线下名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工艺ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showRouteLineInfo(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			一键开始
			List<Map<String,Object>> list = service.showRouteLineInfo(id);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	查询工艺路线备注记录
	@RequestMapping(value = "/showAllRouteLists", method = RequestMethod.POST)
	@ApiOperation(value = "查询工艺路线备注记录", notes = "查询工艺路线备注记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工艺ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showAllRouteLists(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			1、先找到工艺路线的工序 2、遍历工序添加到备注表单 3、进行多表连接
//			一键开始
			List<Map<String,Object>> lists = service.showAllProcessLists(id);
			for(Map map:lists){
			if(service.countNum(String.valueOf(map.get("ID")), String.valueOf(map.get("ST_ID")))==0){
					service.addRoutListInfo(String.valueOf(map.get("ID")), String.valueOf(map.get("ST_ID")), "");
			  }
			}

			List<Map<String,Object>> list = service.showAllRouteLists(id);//展示工艺路线备注
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



//	批量导入图片或者pdf（保存在本地中）
	@RequestMapping(value = "/importProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "批量导入pdf", notes = "批量导入pdf")
	@ApiImplicitParams({})
	@ResponseBody
	public Rjson importProcessTask(HttpServletRequest request,@RequestParam(required=false)MultipartFile[] file) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			System.out.println(file.length);
			for(int i=0;i<file.length;i++){

//				配置文件
				 Properties pps = new Properties();
				 pps .load( ProcessTaskController.class.getClassLoader().getResourceAsStream("file.properties"));
					 Enumeration enum1 = pps.propertyNames();//得到配置文件的内容名字
			        while(enum1.hasMoreElements()) {
			            String strKey = (String) enum1.nextElement();
			            String strValue = pps.getProperty(strKey);
				            System.out.println(strKey + "=" + strValue);
				        }
				     String pathName  =  pps.getProperty("pdfPath");//根据内容名称获取值
				     String clearPathName  =  pps.getProperty("clearPDF");//根据内容名称获取值(删除文件路径)

			      System.out.println("文件路径："+pathName);
				     File dir = new File(pathName);
					 if (!dir.exists()) { // 判断这个路径不存在
						dir.mkdirs(); // 如果不存在就创建
					 }
					 System.out.println("文件名称:"+file[i].getOriginalFilename());
					 File fileName =new File(pathName+file[i].getOriginalFilename());
			//
					if ( !fileName.exists() ){  //要删除的文件不存在
							file[i].transferTo(fileName);//保存当前文件
						}else{ //要删除的文件已经存在

							if ( fileName.isFile() ){ //如果目标文件是文件

							    SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
						        Date d= new Date();
						        String beforeName = sdf.format(d);
						        File oldFilePath = new File(clearPathName); //判断文件是否存在
						        if(!oldFilePath.exists()){
						        	oldFilePath.mkdirs();//不存在就创建
								File oldFile = new File(clearPathName+"(覆盖文件)"+beforeName+"-"+file[i].getOriginalFilename());
								fileName.renameTo(oldFile);
								fileName.delete();//删除文件
								file[i].transferTo(fileName);//保存新文件
							}
						}
			}

//转化base64
//			System.out.println("文件大小 ："+file.length);
//			System.out.println("文件数据:"+file[0].getOriginalFilename());//文件名称  111.png
//			String fileString = null;
//			for(int i=0;i<file.length;i++){
//				fileString = encoder.encode(file[0].getBytes());
//				System.out.println("输出pdf:"+fileString);
//				service.addPDFFile(fileString);

			}

			return Rjson.success();

		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}




	@RequestMapping(value = "/importProcessTaskInfo", method = RequestMethod.POST)
	@ApiOperation(value = "导入任务Excel", notes = "导入任务Excel")
	@ApiImplicitParams({})
	@ResponseBody
	@Transactional
	public Rjson importProcessTaskInfo(HttpServletRequest request,@RequestParam(required=false)MultipartFile file) throws ServicesException {
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
				List<Object> list = map.get(2);
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
				if(!list.get(3).toString().trim().equals("物料名称")){
					return Rjson.error("第四列名称错误");
				}
				if(!list.get(4).toString().trim().equals("计划数量")){
					return Rjson.error("第五列名称错误");
				}
				if(!list.get(5).toString().trim().equals("计划数量备注")){
					return Rjson.error("第六列名称错误");
				}
				if(!list.get(6).toString().trim().equals("物料材质")){
					return Rjson.error("第七列名称错误");
				}
				if(!list.get(7).toString().trim().equals("物料代码")){
					return Rjson.error("第八列名称错误");
				}
				if(!list.get(8).toString().trim().equals("工艺路线")){
					return Rjson.error("第九列名称错误");
				}
				if(!list.get(9).toString().trim().equals("操作人")){
					return Rjson.error("第十列名称错误");
				}


				for (int i = 3; i < map.size(); i++) {
					List<Object> list2 = map.get(i);//获取每行的数据

					Map<String,Object> mapx = new HashMap<String,Object>();
					for (int j = 0; j < list2.size(); j++) {
						if(j==0){  //项目号
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("PROJECT_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行项目号不能为空");
							}
						}
						if(j==1){  //工位号
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("STATION_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行工位号不能为空");
							}
						}
						if(j==2){  //规格型号
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("SPECIFICATION_AND_MODEL", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行规格型号不能为空");
							}
						}
						if(j==3){  //物料名称
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_NAME", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行物料名称不能为空");
							}
						}
						if(j==4){  //计划数量
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("PLAN_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行计划数量不能为空");
							}
						}
						if(j==5){  //计划数量备注
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("NUM_REMARKS", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行计划数量备注不能为空");
							}
						}
						if(j==6){  //物料材质
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_QUALITY", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行物料材质不能为空");
							}
						}
						if(j==7){  //物料代码
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_CODE", list2.get(j).toString());
							}else{
								mapx.put("MATERIAL_CODE", "");
//								return Rjson.error("第"+i+"行物料代码不能为空");
							}
						}
						if(j==8){  //工艺路线
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString());
//								mapx.put("ROUTE_NAME", list2.get(j).toString());
								mapx.put("ROUTE_NAME", service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString()));
							}else{
								mapx.put("ROUTE_NAME", service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString()));
//								return Rjson.error("第"+i+"行工艺路线不能为空");
							}
						}
						if(j==9){  //操作人
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("OPERATION_DEPARTMENT", list2.get(j).toString());
							}else{
								return Rjson.error("第"+i+"行操作人不能为空");
							}
						}

					}
//					System.out.println("输出值："+String.valueOf(mapx.get("PROJECT_NUM")));
//					System.out.println("输出值："+String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")));
//					System.out.println("输出值："+String.valueOf(mapx.get("MATERIAL_CODE")));
//					System.out.println("输出值："+String.valueOf(mapx.get("ROUTE_NAME")));
//					System.out.println("输出值："+String.valueOf(mapx.get("PLAN_NUM")));
//					System.out.println("输出值："+String.valueOf(mapx.get("OPERATION_DEPARTMENT")));
//					System.out.println("输出值："+String.valueOf(mapx.get("MATERIAL_NAME")));
//					System.out.println("输出值："+String.valueOf(mapx.get("MATERIAL_QUALITY")));
//					System.out.println("输出值："+String.valueOf(mapx.get("NUM_REMARKS")));
//					service.addProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum, completeNum, NGNum, reworkNum, operationDepartment, status, images, materialName, materialQuality, numRemarks);
//					try{
						Integer alreadyNum = service.showAlreadyNum(String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), String.valueOf(mapx.get("MATERIAL_CODE")), String.valueOf(mapx.get("STATION_NUM")));
						if(alreadyNum>0){
							return Rjson.error("Excel不能重复导入已经存在的生产任务");
						}
						service.addProcessTask("", String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")),String.valueOf(mapx.get("MATERIAL_CODE")), String.valueOf(mapx.get("ROUTE_NAME")),Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), 0, 0, 0, String.valueOf(mapx.get("OPERATION_DEPARTMENT")), 0, "",String.valueOf(mapx.get("MATERIAL_NAME")),String.valueOf(mapx.get("MATERIAL_QUALITY")),String.valueOf(mapx.get("NUM_REMARKS")),String.valueOf(mapx.get("STATION_NUM")));

						Integer taskId = services.showTaskId(String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), String.valueOf(mapx.get("STATION_NUM")));//项目号+规格型号+工位号
						if(!String.valueOf(mapx.get("ROUTE_NAME")).equals("")&&String.valueOf(mapx.get("ROUTE_NAME"))!=null&&!String.valueOf(mapx.get("ROUTE_NAME")).equals("null")){
							List<Map<String,Object>> listx =services.showRouteLists(Integer.parseInt(String.valueOf(mapx.get("ROUTE_NAME"))));
//							services.addProcessDetails(taskId, Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))), listx);
//							services.updateFirstProcessDetails(Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))), Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))),taskId);
							services.addProcessDetails(taskId, Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), listx);
							services.updateFirstProcessDetails(Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))),taskId);
						}

//					}catch(Exception e){
//						return Rjson.error("请检查Excel数据，查看是否异常！");
//					}


				}

			return Rjson.success();
			} catch (Exception e) {
				e.printStackTrace();
				return Rjson.error(Constant.INTERFACE_EXCEPTION);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}






	@RequestMapping(value = "/showAllProcessTaskPage", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序下推任务单", notes = "查询工序下推任务单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "specificationModel", value = "规格型号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "processType", value = "生产状态", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showAllProcessTaskPage(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;

		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {

			if(pageNum!=null&&pageSize!=null){
			PageHelper.startPage(pageNum, pageSize);
			Integer processId  = Integer.parseInt(request.getParameter("processId"));
			list = service.showAllProcessTaskPage(processId);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);
			}else if(pageNum==null&&pageSize==null){
				Integer processId  = Integer.parseInt(request.getParameter("processId"));
				String projectName=request.getParameter("projectName");
				String projectNum=request.getParameter("projectNum");
				String specificationModel=request.getParameter("specificationModel");
				String processType=request.getParameter("processType");
				String id = request.getParameter("id");
				list = service.showAllProcessTaskPageByCondition(processId, projectName, projectNum, specificationModel, processType, id);
				return Rjson.success(210,list);
			}else{
				PageHelper.startPage(pageNum, pageSize);
				Integer processId  = Integer.parseInt(request.getParameter("processId"));
				list = service.showAllProcessTaskPage(processId);
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				return Rjson.success(pageInfo);
			}
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "查询生产任务", notes = "查询生产任务")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "specificationModel", value = "规格型号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "processType", value = "生产状态", required = false, paramType = "query", dataType = "string"),


			})
	@ResponseBody
	public Rjson showProcessTask(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			// if(pageNum!=null&&pageSize!=null){
			// PageHelper.startPage(pageNum, pageSize);
			//// list = service.showDepartmentInfo(customerId);
			// list = service.showProcess();
			// PageInfo<Map<String, Object>>
			// pageInfo=Rjson.getPageInfoByFormatTime(list);
			// return Rjson.success(pageInfo);
			// }else if(pageNum==null&&pageSize==null){
			// list = service.showProcess();
			// return Rjson.success(list);
			// }else{
			if(pageNum!=null&&pageSize!=null){
			PageHelper.startPage(pageNum, pageSize);
			// list = service.showDepartmentInfo(customerId);
			list = service.showProcessTask();
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);
			}else if(pageNum==null&&pageSize==null){
				String stationNum=request.getParameter("stationNum");
				String projectNum=request.getParameter("projectNum");
				String specificationModel=request.getParameter("specificationModel");
				String processType=request.getParameter("processType");
				String materiCode=request.getParameter("materiCode");
				String id = request.getParameter("id");
				list = service.showProcessTaskByCondition(stationNum, projectNum, specificationModel, processType,id,materiCode);
				return Rjson.success(210,list);
			}else{
				PageHelper.startPage(pageNum, pageSize);
				// list = service.showDepartmentInfo(customerId);
				list = service.showProcessTask();
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				return Rjson.success(pageInfo);
			}
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showRouteLists", method = RequestMethod.POST)
	@ApiOperation(value = "查询工艺路线", notes = "查询工艺路线")
	@ApiImplicitParams({})
	@ResponseBody
	public Rjson showRouteLists(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {

			list = service.showRouteLists();
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "新增生产任务", notes = "新增生产任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModel", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialCode", value = "物料代码", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialQuality", value = "物料材质", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "numRemarks", value = "数量备注", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "routeName", value = "工艺路线", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "planNum", value = "计划数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "completeNum", value = "完成数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "不合格数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "返修数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "operationDepartment", value = "操作部门", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "stationNum", value = "工位号", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson addProcessTask(HttpServletRequest request) throws ServicesException, IOException{


		String images = request.getParameter("pathBinary");
		System.out.println("文件内容2："+images);

		String materialName=request.getParameter("materialName");
		String materialQuality=request.getParameter("materialQuality");
		String numRemarks=request.getParameter("numRemarks");

		String projectName=request.getParameter("projectName");
		String projectNum=request.getParameter("projectNum");
		String specificationModel=request.getParameter("specificationModel");
		String materialCode=request.getParameter("materialCode");
		String routeName=request.getParameter("routeName");
		Integer planNum=Integer.parseInt(request.getParameter("planNum"));
		String operationDepartment=request.getParameter("operationDepartment");
		String stationNum=request.getParameter("stationNum");
//		String userName = request.getParameter("userName");
		 String userName = ToolUtils.getCookieUserName(request);

//		4.0      在Way中检索工艺路线的ID
		 if(routeName ==null){
			 routeName =service.showRouteId(projectNum, stationNum, specificationModel);
			 if(routeName==null){
				 return Rjson.error(202, "工艺路线不能为空");
			 }
		 }

		if(service.countProcessTask(specificationModel, projectNum,-1,materialCode,stationNum)>0){
			return Rjson.error(202, "该项目下的规格型号+物料代码+工位号已存在，不能重复添加！");
		}

		try {
			Integer flagsx = 0;
			 List<Map<String,Object>> list =services.showRouteLists(Integer.parseInt(request.getParameter("routeName")));
			 for(Map mapxs:list){
				 if(mapxs.get("ST_ID").toString().equals("409")){
					 flagsx=1;
				 }
			 }
			 if(flagsx!=1){
				 return Rjson.error(202, "工艺路线必须包含成品入库且为最大工序号");
			 }else{
				 Integer maxId = services.showProductWayId(request.getParameter("routeName"));
				 Integer routeIdx = services.showRouteId(maxId.toString(),request.getParameter("routeName"));
				 if(routeIdx!=409){
					 return Rjson.error(202, "工艺路线的最后一道工序必须是成品入库");
				 }
			 }
//			List<Map<String,Object>> list =services.showRouteLists(Integer.parseInt(request.getParameter("routeName")));
		  service.addProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum, 0, 0, 0, operationDepartment,0,images,materialName,materialQuality,numRemarks,stationNum);
		  Integer taskId = services.showTaskId(projectNum, specificationModel,stationNum);

//		  List<Map<String,Object>> list =services.showRouteList(Integer.parseInt(request.getParameter("routeName")),String.valueOf(taskId));//查找当前工艺

		  services.addProcessDetails(taskId, planNum, list);
		  Integer processId = services.selectProcessIdById(taskId.toString());
		  Integer processTypeInfoxl= servicex.showNowProcesstypeInfo(String.valueOf(projectNum),specificationModel, processId);
		  if(processTypeInfoxl==3){//委外
			  services.updateFirstProcessDetails(planNum,planNum, taskId);
		  }else{//非委外
			  services.updateFirstProcessDetailsx(planNum, taskId);
		  }


//			获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("客户端IP:"+IP);
		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo = model.taskLogInfo(userName, ipInfo,projectName, "创建", null,null, projectNum, specificationModel,null,null,null,stationNum);
		  logInfos.addProcessLogInfo(String.valueOf(taskId), null, logInfo, "生产任务");

		return Rjson.success();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "编辑生产任务", notes = "编辑生产任务")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectNum", value = "项目号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModel", value = "规格型号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialCode", value = "物料代码", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialQuality", value = "物料材质", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "numRemarks", value = "数量备注", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "routeName", value = "工艺路线", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "planNum", value = "计划数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "completeNum", value = "完成数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "不合格数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "返修数量", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "operationDepartment", value = "操作部门", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "stationNum", value = "工位号", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson editProcessTask(HttpServletRequest request) throws ServicesException{
		String images = request.getParameter("pathBinary");
		System.out.println("图片："+images);

		String materialName=request.getParameter("materialName");
		String materialQuality=request.getParameter("materialQuality");
		String numRemarks=request.getParameter("numRemarks");
		Integer id =Integer.parseInt(request.getParameter("id"));
		String projectName=request.getParameter("projectName");
		String projectNum=request.getParameter("projectNum");
		String specificationModel=request.getParameter("specificationModel");
		String materialCode=request.getParameter("materialCode");
		String routeName=request.getParameter("routeName");
		Integer planNum=Integer.parseInt(request.getParameter("planNum"));
		String operationDepartment=request.getParameter("operationDepartment");
		String stationNum=request.getParameter("stationNum");
//		String userName = request.getParameter("userName");
		String userName = ToolUtils.getCookieUserName(request);

		  ProcessLogModel model = new ProcessLogModel();
		  ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
		if(service.countProcessTask(specificationModel, projectNum,id,materialCode,stationNum)>0){
			return Rjson.error(202, "该项目下的规格型号+物料代码+工位号已存在，不能重复添加！");
		}

		try {
			List<Map<String,Object>> list =services.showRouteLists(Integer.parseInt(request.getParameter("routeName")));
			Map<String,	Object> map = services.showTaskById(id);
			String oldPlanNum =  String.valueOf(map.get("PLAN_NUM"));
			if(!map.get("ROUTE_NAME").equals(routeName)){//工艺路线被修改需要改变工序详情
			   services.delProcessDetails(id);//删除工序详情
			   services.addProcessDetails(id, planNum, list);//重新加载新的工序

			   Map<String,Object> maps = services.showFirstProcessInfo(id);
			   Map<String,Object> firstMapxx = services.showFirstProcessInfo(id);
			   String firstProcessId = firstMapxx.get("PROCESS_ID").toString();
			   String types = services.showStationTypeInfo(firstProcessId);


//			   services.updateFirstProcessDetails(planNums, id);//首工序赋值可用数量
			   if(!types.equals("3")){
//				   如果是非委外
				   Integer planNums=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("TESTING_NUM");//非委外
				   services.updateFirstProcessPushDownDetails(planNums, id);
			   }else{
//				   如果是委外
				   Integer planNumsx=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("USEABLE_NUM");//委外
				   Integer planNumsxxs=(Integer)maps.get("TESTING_NUM")-(Integer)maps.get("OK_INSOURCE_TEM_APPROVLA");//委外
				   services.updateFirstProcessDetails(planNumsx,planNumsxxs, id);
			   }

//			   services.updateFirstProcessDetails(planNum, id);//首工序赋值可用数量

if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
				   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"项目名称" ,String.valueOf(map.get("STATION_NUM")));
				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("STATION_NUM")).equals(stationNum)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("STATION_NUM")),String.valueOf(stationNum), projectNum, specificationModel,null,null,"工位号",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"项目号",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"规格型号",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"物料代码",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("MATERIAL_NAME")).equals(materialName)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_NAME")),String.valueOf(materialName), projectNum, specificationModel,null,null,"物料名称",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("MATERIAL_QUALITY")).equals(materialQuality)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_QUALITY")),String.valueOf(materialQuality), projectNum, specificationModel,null,null,"物料材质",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("NUM_REMARKS")).equals(numRemarks)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("NUM_REMARKS")),String.valueOf(numRemarks), projectNum, specificationModel,null,null,"数量备注",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}


if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"工艺路线",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"计划数量",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"操作部门",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("IMAGES")).equals(images)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("IMAGES")),String.valueOf(images), projectNum, specificationModel,null,null,"图片",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}


			}else{//工艺路线未被改变
			   if((Integer)map.get("PLAN_NUM")>planNum){
				return  Rjson.error(202,"任务已开始生产，更改计划数量不能少于原定计划数量");
			   }else if((Integer)map.get("PLAN_NUM")==planNum){
//				   未改变计划数量与工艺路线，工序详情不做任何操作
//				   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
//					   String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "编辑", oldPlanNum,String.valueOf(projectName), projectNum, specificationModel,null,null,"项目名称");
//					   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
//	}

				   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
					   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"项目名称",String.valueOf(map.get("STATION_NUM")));
					   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"项目号",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("STATION_NUM")).equals(stationNum)){
		   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("STATION_NUM")),String.valueOf(stationNum), projectNum, specificationModel,null,null,"工位号",String.valueOf(map.get("STATION_NUM")));
		   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"规格型号",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"物料代码",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"工艺路线",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"计划数量",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

	if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"操作部门",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
	}

			   }else{
			   services.updatePlanNum(planNum, id);//值更新计划数量
//			  计划数量等于总数减去所有已经生产的部分
			   Map<String,Object> maps = services.showFirstProcessInfo(id);

			   Map<String,Object> firstMapxx = services.showFirstProcessInfo(id);
			   String firstProcessId = firstMapxx.get("PROCESS_ID").toString();
			   String types = services.showStationTypeInfo(firstProcessId);


//			   services.updateFirstProcessDetails(planNums, id);//首工序赋值可用数量
			   if(!types.equals("3")){
//				   如果是非委外
				   Integer planNums=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("TESTING_NUM");//非委外
				   services.updateFirstProcessPushDownDetails(planNums, id);
			   }else{
//				   如果是委外
				   Integer planNumsx=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("USEABLE_NUM");//委外
//				   services.updateFirstProcessDetails(planNumsx, id);
				   Integer planNumsxxs=(Integer)maps.get("TESTING_NUM")-Integer.parseInt(maps.get("OK_INSOURCE_TEM_APPROVLA").toString());//委外
				   services.updateFirstProcessDetails(planNumsx,planNumsxxs, id);
			   }


//			   更新下推数据


//				获取客户端IP地址

//				System.out.println("客户端IP:"+IP);
//			   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
//				   String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "编辑", oldPlanNum,String.valueOf(planNum), projectNum, specificationModel,null,null,"项目名称");
//				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
//}

			   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
				   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"项目名称",String.valueOf(map.get("STATION_NUM")));
				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"项目号",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"规格型号",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"物料代码",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}


if(!String.valueOf(map.get("MATERIAL_NAME")).equals(materialName)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_NAME")),String.valueOf(materialName), projectNum, specificationModel,null,null,"物料名称",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("MATERIAL_QUALITY")).equals(materialQuality)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("MATERIAL_QUALITY")),String.valueOf(materialQuality), projectNum, specificationModel,null,null,"物料材质",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("NUM_REMARKS")).equals(numRemarks)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("NUM_REMARKS")),String.valueOf(numRemarks), projectNum, specificationModel,null,null,"数量备注",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}


if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"工艺路线",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"计划数量",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"操作部门",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}

if(!String.valueOf(map.get("IMAGES")).equals(images)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "编辑", String.valueOf(map.get("IMAGES")),String.valueOf(images), projectNum, specificationModel,null,null,"图片",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
}
//			  String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "编辑", oldPlanNum,String.valueOf(planNum), projectNum, specificationModel,null,null,"计划数量");
//			  logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
			   }
			}
		 service.editProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum,  operationDepartment, id,images,materialName,materialQuality,numRemarks,stationNum);


		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/delProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "删除任务计划", notes = "删除任务计划")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工序ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delProcessTask(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));

//		只有开始状态才能删除，所以不需要做其他限制
//		if(service.countRoutes(id)>0){
//			return Rjson.error(202, "该工序存在工艺路线，请先删除关联工艺路线");
//		}
//		String userName = request.getParameter("userName");
		String userName = ToolUtils.getCookieUserName(request);

		try {
			Map<String,	Object> map = services.showTaskById(id);
			System.out.println(map);

//			获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("客户端IP:"+IP);
		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(map.get("PROJECT_NAME")), "删除", null,null, String.valueOf(map.get("PROJECT_NAME")), String.valueOf(map.get("SPECIFICATION_AND_MODEL")),null,null,null,String.valueOf(map.get("STATION_NUM")));
		  logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");

			service.delProcessTask(id);
//			删除计划的同时删除相关联的工序详情
			services.delProcessDetails(id);


		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateTaskStatus", method = RequestMethod.POST)
	@ApiOperation(value = "编辑状态", notes = "编辑状态")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "flags", value = "标记", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "id", value = "生产任务ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson updateTaskStatus(HttpServletRequest request) throws ServicesException{
		Integer id =Integer.parseInt(request.getParameter("id"));
		String flags=request.getParameter("flags");
//		String userName=request.getParameter("userName");
		String userName = ToolUtils.getCookieUserName(request);

//		获取客户端IP地址
		ShowIPInfo ip = new ShowIPInfo();
		String ipInfo = ip.getIpAddr(request);
//		System.out.println("客户端IP:"+IP);
	  ProcessLogModel model = new ProcessLogModel();
	  Map<String,Object> showTaskInfoByIds = service.showTaskInfoById(id);
//	  String logInfo = model.taskLogInfo("admin", ipInfo,projectName, "创建", null,null, projectNum, specificationModel,null,null);
//	  logInfos.addProcessLogInfo(String.valueOf(taskId), null, logInfo, "生产任务");

		try {
		service.updateTaskStatus(id, flags);
		if(flags.equals("0")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"开始","生产",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
		}else if(flags.equals("1")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"生产","暂停",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
		}else if(flags.equals("2")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"暂停","生产",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "生产任务");
		}
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



	@RequestMapping(value = "/showRouteList", method = RequestMethod.POST)
	@ApiOperation(value = "展示工艺路线清单", notes = "展示工艺路线清单")
	@ApiImplicitParams({

			})
	@ResponseBody
	public Rjson showRouteList(HttpServletRequest request) throws ServicesException {

		try {
//			一键开始
		List<Map<String,Object>> list = service.showRouteList();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showRouteListsx", method = RequestMethod.POST)
	@ApiOperation(value = "展示条件查询工艺路线清单", notes = "展示条件查询工艺路线清单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processNum", value = "工艺编号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "工艺名称", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showRouteListsx(HttpServletRequest request) throws ServicesException {

		try {
//			一键开始
	    String processNum = request.getParameter("processNum");
	    String processName = request.getParameter("processName");
		List<Map<String,Object>> list = service.showRouteListxs(processNum, processName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
