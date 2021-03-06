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
@Api(value = "????????????", description = "????????????", produces = MediaType.APPLICATION_JSON)
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
	@ApiOperation(value = "????????????????????????", notes = "????????????????????????")
	@ApiImplicitParams({

			})
	@ResponseBody
	public Rjson updateSattusInfox(HttpServletRequest request) throws ServicesException {

		try {

//			????????????
			service.updateSattusInfox();
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


//	??????????????????????????????
	@RequestMapping(value = "/showAllProcessInfo", method = RequestMethod.POST)
	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "??????ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showAllProcessInfo(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			????????????
			List<Map<String,Object>> list = service.showAllProcessInfo(id);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	????????????????????????
	@RequestMapping(value = "/addRoutListInfo", method = RequestMethod.POST)
	@ApiOperation(value = "????????????????????????", notes = "????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "routeName", value = "??????ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "??????ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "remarksInfos", value = "????????????", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson addRoutListInfo(HttpServletRequest request) throws ServicesException {
		try {
			String routeName = request.getParameter("routeName");
			String processName = request.getParameter("processName");
			String remarksInfos = request.getParameter("remarksInfos");
//			????????????
			if(service.countNum(routeName, processName)>0){//??????
//				return Rjson.error(210,"?????????????????????????????????????????????");
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

//	????????????????????????
	@RequestMapping(value = "/showRouteLineInfo", method = RequestMethod.POST)
	@ApiOperation(value = "???????????????????????????", notes = "???????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "??????ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showRouteLineInfo(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			????????????
			List<Map<String,Object>> list = service.showRouteLineInfo(id);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	??????????????????????????????
	@RequestMapping(value = "/showAllRouteLists", method = RequestMethod.POST)
	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "??????ID", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showAllRouteLists(HttpServletRequest request) throws ServicesException {
		try {
			String id = request.getParameter("id");
//			1????????????????????????????????? 2???????????????????????????????????? 3?????????????????????
//			????????????
			List<Map<String,Object>> lists = service.showAllProcessLists(id);
			for(Map map:lists){
			if(service.countNum(String.valueOf(map.get("ID")), String.valueOf(map.get("ST_ID")))==0){
					service.addRoutListInfo(String.valueOf(map.get("ID")), String.valueOf(map.get("ST_ID")), "");
			  }
			}

			List<Map<String,Object>> list = service.showAllRouteLists(id);//????????????????????????
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



//	????????????????????????pdf????????????????????????
	@RequestMapping(value = "/importProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "????????????pdf", notes = "????????????pdf")
	@ApiImplicitParams({})
	@ResponseBody
	public Rjson importProcessTask(HttpServletRequest request,@RequestParam(required=false)MultipartFile[] file) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			System.out.println(file.length);
			for(int i=0;i<file.length;i++){

//				????????????
				 Properties pps = new Properties();
				 pps .load( ProcessTaskController.class.getClassLoader().getResourceAsStream("file.properties"));
					 Enumeration enum1 = pps.propertyNames();//?????????????????????????????????
			        while(enum1.hasMoreElements()) {
			            String strKey = (String) enum1.nextElement();
			            String strValue = pps.getProperty(strKey);
				            System.out.println(strKey + "=" + strValue);
				        }
				     String pathName  =  pps.getProperty("pdfPath");//???????????????????????????
				     String clearPathName  =  pps.getProperty("clearPDF");//???????????????????????????(??????????????????)

			      System.out.println("???????????????"+pathName);
				     File dir = new File(pathName);
					 if (!dir.exists()) { // ???????????????????????????
						dir.mkdirs(); // ????????????????????????
					 }
					 System.out.println("????????????:"+file[i].getOriginalFilename());
					 File fileName =new File(pathName+file[i].getOriginalFilename());
			//
					if ( !fileName.exists() ){  //???????????????????????????
							file[i].transferTo(fileName);//??????????????????
						}else{ //??????????????????????????????

							if ( fileName.isFile() ){ //???????????????????????????

							    SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
						        Date d= new Date();
						        String beforeName = sdf.format(d);
						        File oldFilePath = new File(clearPathName); //????????????????????????
						        if(!oldFilePath.exists()){
						        	oldFilePath.mkdirs();//??????????????????
								File oldFile = new File(clearPathName+"(????????????)"+beforeName+"-"+file[i].getOriginalFilename());
								fileName.renameTo(oldFile);
								fileName.delete();//????????????
								file[i].transferTo(fileName);//???????????????
							}
						}
			}

//??????base64
//			System.out.println("???????????? ???"+file.length);
//			System.out.println("????????????:"+file[0].getOriginalFilename());//????????????  111.png
//			String fileString = null;
//			for(int i=0;i<file.length;i++){
//				fileString = encoder.encode(file[0].getBytes());
//				System.out.println("??????pdf:"+fileString);
//				service.addPDFFile(fileString);

			}

			return Rjson.success();

		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}




	@RequestMapping(value = "/importProcessTaskInfo", method = RequestMethod.POST)
	@ApiOperation(value = "????????????Excel", notes = "????????????Excel")
	@ApiImplicitParams({})
	@ResponseBody
	@Transactional
	public Rjson importProcessTaskInfo(HttpServletRequest request,@RequestParam(required=false)MultipartFile file) throws ServicesException {
		try {

//			System.out.println("????????????:"+file.getOriginalFilename());

//			System.out.println(file.getOriginalFilename().substring(fileName.length()-3,fileName.length()));
//			System.out.println(file.getOriginalFilename().substring(fileName.length()-4,fileName.length()));
//		           ??????????????????
//
//			System.out.println("???????????????"+file.getOriginalFilename());
			String fileName = file.getOriginalFilename();
//			System.out.println("???????????????"+file);

			if(!(fileName.substring(fileName.length()-4,fileName.length()).equals("xlsx") || fileName.substring(fileName.length()-3,fileName.length()).equals("xls"))){
				return Rjson.error(202, "?????????????????????Excel");
			}
//			??????excel???
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
				map = ImportExcel.getIoValue(ins, fileName); // ?????????????????????????????????
				List<Object> list = map.get(2);
				System.out.println("??????MAP:"+map);
				System.out.println("????????????????????????"+list.get(0).toString().trim());

				if(!list.get(0).toString().trim().equals("?????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(1).toString().trim().equals("?????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(2).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(3).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(4).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(5).toString().trim().equals("??????????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(6).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(7).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(8).toString().trim().equals("????????????")){
					return Rjson.error("?????????????????????");
				}
				if(!list.get(9).toString().trim().equals("?????????")){
					return Rjson.error("?????????????????????");
				}


				for (int i = 3; i < map.size(); i++) {
					List<Object> list2 = map.get(i);//?????????????????????

					Map<String,Object> mapx = new HashMap<String,Object>();
					for (int j = 0; j < list2.size(); j++) {
						if(j==0){  //?????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("PROJECT_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"????????????????????????");
							}
						}
						if(j==1){  //?????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("STATION_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"????????????????????????");
							}
						}
						if(j==2){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("SPECIFICATION_AND_MODEL", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==3){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_NAME", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==4){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("PLAN_NUM", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==5){  //??????????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("NUM_REMARKS", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"?????????????????????????????????");
							}
						}
						if(j==6){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_QUALITY", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==7){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("MATERIAL_CODE", list2.get(j).toString());
							}else{
								mapx.put("MATERIAL_CODE", "");
//								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==8){  //????????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString());
//								mapx.put("ROUTE_NAME", list2.get(j).toString());
								mapx.put("ROUTE_NAME", service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString()));
							}else{
								mapx.put("ROUTE_NAME", service.showRouteId(list2.get(0).toString(), list2.get(1).toString(), list2.get(2).toString()));
//								return Rjson.error("???"+i+"???????????????????????????");
							}
						}
						if(j==9){  //?????????
							if(!list2.get(j).equals("") && list2.get(j)!=null){
								mapx.put("OPERATION_DEPARTMENT", list2.get(j).toString());
							}else{
								return Rjson.error("???"+i+"????????????????????????");
							}
						}

					}
//					System.out.println("????????????"+String.valueOf(mapx.get("PROJECT_NUM")));
//					System.out.println("????????????"+String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")));
//					System.out.println("????????????"+String.valueOf(mapx.get("MATERIAL_CODE")));
//					System.out.println("????????????"+String.valueOf(mapx.get("ROUTE_NAME")));
//					System.out.println("????????????"+String.valueOf(mapx.get("PLAN_NUM")));
//					System.out.println("????????????"+String.valueOf(mapx.get("OPERATION_DEPARTMENT")));
//					System.out.println("????????????"+String.valueOf(mapx.get("MATERIAL_NAME")));
//					System.out.println("????????????"+String.valueOf(mapx.get("MATERIAL_QUALITY")));
//					System.out.println("????????????"+String.valueOf(mapx.get("NUM_REMARKS")));
//					service.addProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum, completeNum, NGNum, reworkNum, operationDepartment, status, images, materialName, materialQuality, numRemarks);
//					try{
						Integer alreadyNum = service.showAlreadyNum(String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), String.valueOf(mapx.get("MATERIAL_CODE")), String.valueOf(mapx.get("STATION_NUM")));
						if(alreadyNum>0){
							return Rjson.error("Excel?????????????????????????????????????????????");
						}
						service.addProcessTask("", String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")),String.valueOf(mapx.get("MATERIAL_CODE")), String.valueOf(mapx.get("ROUTE_NAME")),Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), 0, 0, 0, String.valueOf(mapx.get("OPERATION_DEPARTMENT")), 0, "",String.valueOf(mapx.get("MATERIAL_NAME")),String.valueOf(mapx.get("MATERIAL_QUALITY")),String.valueOf(mapx.get("NUM_REMARKS")),String.valueOf(mapx.get("STATION_NUM")));

						Integer taskId = services.showTaskId(String.valueOf(mapx.get("PROJECT_NUM")), String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), String.valueOf(mapx.get("STATION_NUM")));//?????????+????????????+?????????
						if(!String.valueOf(mapx.get("ROUTE_NAME")).equals("")&&String.valueOf(mapx.get("ROUTE_NAME"))!=null&&!String.valueOf(mapx.get("ROUTE_NAME")).equals("null")){
							List<Map<String,Object>> listx =services.showRouteLists(Integer.parseInt(String.valueOf(mapx.get("ROUTE_NAME"))));
//							services.addProcessDetails(taskId, Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))), listx);
//							services.updateFirstProcessDetails(Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))), Integer.parseInt(String.valueOf(mapx.get("PROJECT_NUM"))),taskId);
							services.addProcessDetails(taskId, Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), listx);
							services.updateFirstProcessDetails(Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))), Integer.parseInt(String.valueOf(mapx.get("PLAN_NUM"))),taskId);
						}

//					}catch(Exception e){
//						return Rjson.error("?????????Excel??????????????????????????????");
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
	@ApiOperation(value = "???????????????????????????", notes = "???????????????????????????")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "processId", value = "??????ID", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "projectName", value = "????????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "projectNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "specificationModel", value = "????????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "processType", value = "????????????", required = false, paramType = "query", dataType = "string"),
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
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "projectName", value = "????????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "projectNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "specificationModel", value = "????????????", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "processType", value = "????????????", required = false, paramType = "query", dataType = "string"),


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
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
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
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModel", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialCode", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialQuality", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "numRemarks", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "routeName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "planNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "completeNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "???????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "operationDepartment", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "stationNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson addProcessTask(HttpServletRequest request) throws ServicesException, IOException{


		String images = request.getParameter("pathBinary");
		System.out.println("????????????2???"+images);

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

//		4.0      ???Way????????????????????????ID
		 if(routeName ==null){
			 routeName =service.showRouteId(projectNum, stationNum, specificationModel);
			 if(routeName==null){
				 return Rjson.error(202, "????????????????????????");
			 }
		 }

		if(service.countProcessTask(specificationModel, projectNum,-1,materialCode,stationNum)>0){
			return Rjson.error(202, "???????????????????????????+????????????+??????????????????????????????????????????");
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
				 return Rjson.error(202, "?????????????????????????????????????????????????????????");
			 }else{
				 Integer maxId = services.showProductWayId(request.getParameter("routeName"));
				 Integer routeIdx = services.showRouteId(maxId.toString(),request.getParameter("routeName"));
				 if(routeIdx!=409){
					 return Rjson.error(202, "??????????????????????????????????????????????????????");
				 }
			 }
//			List<Map<String,Object>> list =services.showRouteLists(Integer.parseInt(request.getParameter("routeName")));
		  service.addProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum, 0, 0, 0, operationDepartment,0,images,materialName,materialQuality,numRemarks,stationNum);
		  Integer taskId = services.showTaskId(projectNum, specificationModel,stationNum);

//		  List<Map<String,Object>> list =services.showRouteList(Integer.parseInt(request.getParameter("routeName")),String.valueOf(taskId));//??????????????????

		  services.addProcessDetails(taskId, planNum, list);
		  Integer processId = services.selectProcessIdById(taskId.toString());
		  Integer processTypeInfoxl= servicex.showNowProcesstypeInfo(String.valueOf(projectNum),specificationModel, processId);
		  if(processTypeInfoxl==3){//??????
			  services.updateFirstProcessDetails(planNum,planNum, taskId);
		  }else{//?????????
			  services.updateFirstProcessDetailsx(planNum, taskId);
		  }


//			???????????????IP??????
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("?????????IP:"+IP);
		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo = model.taskLogInfo(userName, ipInfo,projectName, "??????", null,null, projectNum, specificationModel,null,null,null,stationNum);
		  logInfos.addProcessLogInfo(String.valueOf(taskId), null, logInfo, "????????????");

		return Rjson.success();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editProcessTask", method = RequestMethod.POST)
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "specificationModel", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialCode", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialQuality", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "numRemarks", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "routeName", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "planNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "completeNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "NGNum", value = "???????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "reworkNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "operationDepartment", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "Integer"),
		@ApiImplicitParam(name = "stationNum", value = "?????????", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson editProcessTask(HttpServletRequest request) throws ServicesException{
		String images = request.getParameter("pathBinary");
		System.out.println("?????????"+images);

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
			return Rjson.error(202, "???????????????????????????+????????????+??????????????????????????????????????????");
		}

		try {
			List<Map<String,Object>> list =services.showRouteLists(Integer.parseInt(request.getParameter("routeName")));
			Map<String,	Object> map = services.showTaskById(id);
			String oldPlanNum =  String.valueOf(map.get("PLAN_NUM"));
			if(!map.get("ROUTE_NAME").equals(routeName)){//?????????????????????????????????????????????
			   services.delProcessDetails(id);//??????????????????
			   services.addProcessDetails(id, planNum, list);//????????????????????????

			   Map<String,Object> maps = services.showFirstProcessInfo(id);
			   Map<String,Object> firstMapxx = services.showFirstProcessInfo(id);
			   String firstProcessId = firstMapxx.get("PROCESS_ID").toString();
			   String types = services.showStationTypeInfo(firstProcessId);


//			   services.updateFirstProcessDetails(planNums, id);//???????????????????????????
			   if(!types.equals("3")){
//				   ??????????????????
				   Integer planNums=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("TESTING_NUM");//?????????
				   services.updateFirstProcessPushDownDetails(planNums, id);
			   }else{
//				   ???????????????
				   Integer planNumsx=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("USEABLE_NUM");//??????
				   Integer planNumsxxs=(Integer)maps.get("TESTING_NUM")-(Integer)maps.get("OK_INSOURCE_TEM_APPROVLA");//??????
				   services.updateFirstProcessDetails(planNumsx,planNumsxxs, id);
			   }

//			   services.updateFirstProcessDetails(planNum, id);//???????????????????????????

if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
				   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"????????????" ,String.valueOf(map.get("STATION_NUM")));
				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("STATION_NUM")).equals(stationNum)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("STATION_NUM")),String.valueOf(stationNum), projectNum, specificationModel,null,null,"?????????",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"?????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("MATERIAL_NAME")).equals(materialName)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_NAME")),String.valueOf(materialName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("MATERIAL_QUALITY")).equals(materialQuality)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_QUALITY")),String.valueOf(materialQuality), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("NUM_REMARKS")).equals(numRemarks)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("NUM_REMARKS")),String.valueOf(numRemarks), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}


if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("IMAGES")).equals(images)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("IMAGES")),String.valueOf(images), projectNum, specificationModel,null,null,"??????",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}


			}else{//????????????????????????
			   if((Integer)map.get("PLAN_NUM")>planNum){
				return  Rjson.error(202,"????????????????????????????????????????????????????????????????????????");
			   }else if((Integer)map.get("PLAN_NUM")==planNum){
//				   ?????????????????????????????????????????????????????????????????????
//				   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
//					   String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "??????", oldPlanNum,String.valueOf(projectName), projectNum, specificationModel,null,null,"????????????");
//					   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
//	}

				   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
					   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
					   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"?????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("STATION_NUM")).equals(stationNum)){
		   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("STATION_NUM")),String.valueOf(stationNum), projectNum, specificationModel,null,null,"?????????",String.valueOf(map.get("STATION_NUM")));
		   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

	if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
			 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
			 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
	}

			   }else{
			   services.updatePlanNum(planNum, id);//?????????????????????
//			  ?????????????????????????????????????????????????????????
			   Map<String,Object> maps = services.showFirstProcessInfo(id);

			   Map<String,Object> firstMapxx = services.showFirstProcessInfo(id);
			   String firstProcessId = firstMapxx.get("PROCESS_ID").toString();
			   String types = services.showStationTypeInfo(firstProcessId);


//			   services.updateFirstProcessDetails(planNums, id);//???????????????????????????
			   if(!types.equals("3")){
//				   ??????????????????
				   Integer planNums=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("TESTING_NUM");//?????????
				   services.updateFirstProcessPushDownDetails(planNums, id);
			   }else{
//				   ???????????????
				   Integer planNumsx=planNum-(Integer)maps.get("COMPLETE_NUM")-(Integer)maps.get("NG_NUM")-(Integer)maps.get("USEABLE_NUM");//??????
//				   services.updateFirstProcessDetails(planNumsx, id);
				   Integer planNumsxxs=(Integer)maps.get("TESTING_NUM")-Integer.parseInt(maps.get("OK_INSOURCE_TEM_APPROVLA").toString());//??????
				   services.updateFirstProcessDetails(planNumsx,planNumsxxs, id);
			   }


//			   ??????????????????


//				???????????????IP??????

//				System.out.println("?????????IP:"+IP);
//			   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
//				   String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "??????", oldPlanNum,String.valueOf(planNum), projectNum, specificationModel,null,null,"????????????");
//				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
//}

			   if(!String.valueOf(map.get("PROJECT_NAME")).equals(projectName)){
				   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NAME")),String.valueOf(projectName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
				   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("PROJECT_NUM")).equals(projectNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PROJECT_NUM")),String.valueOf(projectNum), projectNum, specificationModel,null,null,"?????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("SPECIFICATION_AND_MODEL")).equals(specificationModel)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("SPECIFICATION_AND_MODEL")),String.valueOf(specificationModel), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("MATERIAL_CODE")).equals(materialCode)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_CODE")),String.valueOf(materialCode), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}


if(!String.valueOf(map.get("MATERIAL_NAME")).equals(materialName)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_NAME")),String.valueOf(materialName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("MATERIAL_QUALITY")).equals(materialQuality)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("MATERIAL_QUALITY")),String.valueOf(materialQuality), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("NUM_REMARKS")).equals(numRemarks)){
	 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("NUM_REMARKS")),String.valueOf(numRemarks), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
	 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}


if(!String.valueOf(map.get("ROUTE_NAME")).equals(routeName)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("ROUTE_NAME")),String.valueOf(routeName), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("PLAN_NUM")).equals(planNum)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("PLAN_NUM")),String.valueOf(planNum), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("OPERATION_DEPARTMENT")).equals(operationDepartment)){
		 String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("OPERATION_DEPARTMENT")),String.valueOf(operationDepartment), projectNum, specificationModel,null,null,"????????????",String.valueOf(map.get("STATION_NUM")));
		 logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}

if(!String.valueOf(map.get("IMAGES")).equals(images)){
	   String logInfo = model.taskLogInfo(userName, ipInfo, projectName, "??????", String.valueOf(map.get("IMAGES")),String.valueOf(images), projectNum, specificationModel,null,null,"??????",String.valueOf(map.get("STATION_NUM")));
	   logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
}
//			  String logInfo = model.taskLogInfo("admin", ipInfo, projectName, "??????", oldPlanNum,String.valueOf(planNum), projectNum, specificationModel,null,null,"????????????");
//			  logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
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
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "??????ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delProcessTask(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));

//		???????????????????????????????????????????????????????????????
//		if(service.countRoutes(id)>0){
//			return Rjson.error(202, "????????????????????????????????????????????????????????????");
//		}
//		String userName = request.getParameter("userName");
		String userName = ToolUtils.getCookieUserName(request);

		try {
			Map<String,	Object> map = services.showTaskById(id);
			System.out.println(map);

//			???????????????IP??????
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			System.out.println("?????????IP:"+IP);
		  ProcessLogModel model = new ProcessLogModel();
		  String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(map.get("PROJECT_NAME")), "??????", null,null, String.valueOf(map.get("PROJECT_NAME")), String.valueOf(map.get("SPECIFICATION_AND_MODEL")),null,null,null,String.valueOf(map.get("STATION_NUM")));
		  logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");

			service.delProcessTask(id);
//			???????????????????????????????????????????????????
			services.delProcessDetails(id);


		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateTaskStatus", method = RequestMethod.POST)
	@ApiOperation(value = "????????????", notes = "????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "flags", value = "??????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "id", value = "????????????ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson updateTaskStatus(HttpServletRequest request) throws ServicesException{
		Integer id =Integer.parseInt(request.getParameter("id"));
		String flags=request.getParameter("flags");
//		String userName=request.getParameter("userName");
		String userName = ToolUtils.getCookieUserName(request);

//		???????????????IP??????
		ShowIPInfo ip = new ShowIPInfo();
		String ipInfo = ip.getIpAddr(request);
//		System.out.println("?????????IP:"+IP);
	  ProcessLogModel model = new ProcessLogModel();
	  Map<String,Object> showTaskInfoByIds = service.showTaskInfoById(id);
//	  String logInfo = model.taskLogInfo("admin", ipInfo,projectName, "??????", null,null, projectNum, specificationModel,null,null);
//	  logInfos.addProcessLogInfo(String.valueOf(taskId), null, logInfo, "????????????");

		try {
		service.updateTaskStatus(id, flags);
		if(flags.equals("0")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"??????","??????",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
		}else if(flags.equals("1")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"??????","??????",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
		}else if(flags.equals("2")){
			String logInfo = model.taskLogInfo(userName, ipInfo, String.valueOf(showTaskInfoByIds.get("PROJECT_NAME")), null, null,null, String.valueOf(showTaskInfoByIds.get("PROJECT_NUM")), String.valueOf(showTaskInfoByIds.get("SPECIFICATION_AND_MODEL")),"??????","??????",null,String.valueOf(showTaskInfoByIds.get("STATION_NUM")));
			logInfos.addProcessLogInfo(String.valueOf(id), null, logInfo, "????????????");
		}
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}



	@RequestMapping(value = "/showRouteList", method = RequestMethod.POST)
	@ApiOperation(value = "????????????????????????", notes = "????????????????????????")
	@ApiImplicitParams({

			})
	@ResponseBody
	public Rjson showRouteList(HttpServletRequest request) throws ServicesException {

		try {
//			????????????
		List<Map<String,Object>> list = service.showRouteList();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showRouteListsx", method = RequestMethod.POST)
	@ApiOperation(value = "????????????????????????????????????", notes = "????????????????????????????????????")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processNum", value = "????????????", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processName", value = "????????????", required = false, paramType = "query", dataType = "string"),
			})
	@ResponseBody
	public Rjson showRouteListsx(HttpServletRequest request) throws ServicesException {

		try {
//			????????????
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
