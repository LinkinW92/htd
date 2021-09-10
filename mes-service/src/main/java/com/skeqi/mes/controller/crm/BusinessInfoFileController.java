package com.skeqi.mes.controller.crm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.util.chenj.EqualsPoJoUtil;
import com.skeqi.mes.util.chenj.FileDownloadUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ConfigPathInfo;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.crm.BusinessInfoFileService;
import com.skeqi.mes.service.crm.CRMLogService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import static com.skeqi.mes.util.chenj.FileUploadUtils.isOSLinux;

/**
 * 商务信息文件存档
 * @author liyah
 *
 */
//@CrossOrigin
@Controller
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "CRM文件管理", description = "CRM文件管理", produces = MediaType.APPLICATION_JSON)
public class BusinessInfoFileController {

	@Autowired
	private BusinessInfoFileService service;
	@Autowired CRMLogService services;

	@Value(value = "${fileName.CRMFile}")
	String path;
	@Value(value = "${fileName.CRMClear}")
	String clearPath;



	@RequestMapping(value = "/FileDownload", method = RequestMethod.POST)
	@ApiOperation(value = "文件下载", notes = "文件下载")
	@ApiImplicitParams({
	})
	public Rjson FileDownload(HttpServletRequest request, String projectId,String fileName, String typeId, String openMode, HttpServletResponse response) throws Exception {
//		projectId, typeId

		EqualsPoJoUtil.string(projectId, "文件名");
		EqualsPoJoUtil.string(typeId, "文件名");
		EqualsPoJoUtil.string(fileName, "存放服务器的文件名");
		EqualsPoJoUtil.integer(openMode, "显示方式", true);
		String filePath="";
//		Properties pps = new Properties();
//		pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//		String path = pps.getProperty("CRMFile");

		if(isOSLinux()) {
			filePath = path +"/"+ projectId + "/" + typeId + "/" + fileName;
		}else{
			filePath = path +"\\" + projectId + "\\" + typeId + "\\" + fileName;
		}
		FileDownloadUtils.downloadStreamExtend(response, filePath, openMode);

		return null;
	}
	@RequestMapping(value = "/showFileTypeNames", method = RequestMethod.POST)
	@ApiOperation(value = "查询文件类型名称", notes = "查询文件类型名称")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showFileTypeNames(HttpServletRequest request) throws ServicesException{
//		Integer customerId = Integer.parseInt(request.getParameter("customerId"));


		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
//
		try {

			Map map  = new HashMap<>();
			String fileTypeNames = service.showFileTypeName(typeId);
			map.put("fileTypeNames", fileTypeNames);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "查询文件类型名称");
			return Rjson.success(map);
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/testFile", method = RequestMethod.POST)
	@ApiOperation(value = "文件上传情况", notes = "文件上传情况")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "typeId", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson testFile(HttpServletRequest request,@RequestParam(required=false)MultipartFile[] file) throws ServicesException ,FileNotFoundException, IOException {
		try {
			System.out.println("文件数据:"+file);

//			ConfigPathInfo configs = new ConfigPathInfo();
//			System.out.println(configs.obtainPath("CRMFile"));
//			System.out.println(configs.obtainPath("CRMFile"));
//			String path = configs.obtainPath("CRMFile");
//			String clearPath = configs.obtainPath("CRMClear");

//			Properties pps = new Properties();
//	        pps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
//	        pps.load(new InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"), "UTF-8"));
//	        pps .load( BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//	        pps.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//	        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
//	        while(enum1.hasMoreElements()) {
//	            String strKey = (String) enum1.nextElement();
//	            String strValue = pps.getProperty(strKey);
//	            System.out.println(strKey + "=" + strValue);
//	        }
//	        String path  =  pps.getProperty("QRCode");
//	        String path  =  pps.getProperty("CRMFile");
//	        String clearPath =  pps.getProperty("CRMClear");


			Integer projectId = Integer.parseInt(request.getParameter("projectId"));
			Integer typeId = Integer.parseInt(request.getParameter("typeId"));
			String filePath="";
			String filePath1="";
			String filePath2="";
			String filePath3="";
			for(int i=0;i<file.length;i++){

//				File dir = new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId);
				if(isOSLinux()) {
					filePath = path +"/"+ projectId + "/" + typeId;
				}else{
					filePath = path+"\\"+projectId+"\\"+typeId;
				}
				File dir = new File(filePath);
				if (!dir.exists()) { // 判断这个路径不存在
					dir.mkdirs(); // 如果不存在就创建
				}
				if (!file[i].isEmpty()) {

				System.out.println("filName:"+file[i].getOriginalFilename());
				System.out.println("传输的文件:"+file[i]);

//				File fileName =new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId+"\\"+file[i].getOriginalFilename());
					if(isOSLinux()) {
						filePath1 = path +"/"+ projectId + "/" + typeId+"/"+file[i].getOriginalFilename();
					}else{
						filePath1 = path+"\\"+projectId+"\\"+typeId+"\\"+file[i].getOriginalFilename();
					}
				File fileName =new File(filePath1);
//				long size = fileName.length();
//				if(size/1024/1024/1024>1){
//					return Rjson.error(201,"传输文件不能超过1G");
//				}

				if ( !fileName.exists() ){  //要删除的文件不存在
//					System.out.println("文件"+"E:\\1\\testFileDemo\\"+file[i].getOriginalFilename()+"不存在，删除失败！" );
					file[i].transferTo(fileName);//保存当前文件
				}else{ //要删除的文件已经存在

					if ( fileName.isFile() ){ //如果目标文件是文件

					    SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
				        Date d= new Date();
				        String beforeName = sdf.format(d);
//				        File oldFilePath = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId);
						if(isOSLinux()) {
							filePath2 = clearPath +"/"+ projectId + "/" + typeId;
						}else{
							filePath2 = clearPath+"\\"+projectId+"\\"+typeId;
						}
				        File oldFilePath = new File(filePath2);
				        if(!oldFilePath.exists()){
				        	oldFilePath.mkdirs();
				        }
//						File oldFile = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId+"\\(覆盖文件)"+beforeName+"-"+file[i].getOriginalFilename());
						if(isOSLinux()) {
							filePath3 = clearPath +"/"+ projectId + "/" + typeId+"/(覆盖文件)"+beforeName+"-"+file[i].getOriginalFilename();
						}else{
							filePath3 = clearPath+"\\"+projectId+"\\"+typeId+"\\(覆盖文件)"+beforeName+"-"+file[i].getOriginalFilename();
						}
				        File oldFile = new File(filePath3);
						fileName.renameTo(oldFile);
						fileName.delete();//删除文件
						file[i].transferTo(fileName);//保存新文件
					}
				}
				}
			}
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}





	@RequestMapping(value = "/showFileDetailsList", method = RequestMethod.POST)
	@ApiOperation(value = "展示文件上传列表", notes = "展示文件上传列表")
	@ApiImplicitParams({
//		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
//		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectId", value = "项目ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "typeId", value = "类型ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson showFileDetailsList(HttpServletRequest request) throws ServicesException, FileNotFoundException, IOException{
		Integer projectId =Integer.parseInt(request.getParameter("projectId"));
		Integer typeId =Integer.parseInt(request.getParameter("typeId"));
		List<Map<String, Object>> list = null;
		String fileTypeNames = service.showFileTypeName(typeId);

//		Properties pps = new Properties();
//        pps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
//        pps.load(new InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"), "UTF-8"));
//        pps .load( BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//        pps.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
//        while(enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
//        }
//        String path  =  pps.getProperty("QRCode");
//        String path  =  pps.getProperty("CRMFile");
//        String clearPath =  pps.getProperty("CRMClear");

//		System.out.println("文件名称："+fileTypeNames);

//		Integer pageNum = null;
//		Integer pageSize = null;
//		if(request.getParameter("pageNum") != null){
//			pageNum = Integer.parseInt(request.getParameter("pageNum"));
//		}
//		if(request.getParameter("pageSize") != null){
//			pageSize = Integer.parseInt(request.getParameter("pageSize"));
//		}
		String filePath="";
		try {
//			if(pageNum!=null&&pageSize!=null){
//				PageHelper.startPage(pageNum, pageSize);
////				list  = service.showDepartmentInfo(customerId);
//				list  = service.showBusinessInfoFileList(projectId, typeId);
//				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
//				return Rjson.success(pageInfo);
//			}else if(pageNum==null&&pageSize==null){
//			File file =new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId);//fileList是一个文件夹
			if(isOSLinux()) {
				filePath = path +"/"+ projectId + "/" + typeId;
			}else{
				filePath = path+"\\"+projectId+"\\"+typeId;
			}
			File file =new File(filePath);
			File[] fileList = file.listFiles();//将所有文件放置在一个File类型的数组中
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			if(fileList!=null){
				System.out.println("文件为空，请处理");


			List<File> uploadFile = new ArrayList<File>();
			for(int i=0;i< fileList.length;i++){
				if(fileList[i].isFile()){
					uploadFile.add(fileList[i]);
				}
			}
			System.out.println("数组:"+uploadFile);

			for(File files:uploadFile){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("fileTypeNames", fileTypeNames);
				map.put("fileName", files.getName());


				long filesLong = files.length();
				DecimalFormat dfs = new DecimalFormat("#.00");
				String fileSzie=0+"B";
				if(filesLong==0){
					 fileSzie =fileSzie;
				}else if(filesLong<1024){
					fileSzie = dfs.format(((double)filesLong))+"B";
				}else if(filesLong<1048576){
					fileSzie = dfs.format(((double)filesLong/1024))+"KB";
				}else if(filesLong<1073741824){
					fileSzie = dfs.format(((double)filesLong/1048576))+"MB";
				}else{
					fileSzie = dfs.format(((double)filesLong/1073741824))+"GB";
				}

				map.put("fileSize", fileSzie);
				SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
				Long lastModified = files.lastModified();
		        String uploadTime = sdf1.format(new Date(lastModified));
		    	map.put("uploadTime", uploadTime);
		    	mapList.add(map);
			}

//				list  = service.showBusinessInfoFileList(projectId, typeId);
//			System.out.println("mapList:"+mapList);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件上传列表");
				return Rjson.success(mapList);
				}else{
//					Map<String,Object> map = new HashMap<String, Object>();
//					map.put("fileTypeNames", fileTypeNames);
//					mapList.add(map);
//					System.out.println("mapList:"+mapList);
					String userName = ToolUtils.getCookieUserName(request);
					ShowIPInfo ip = new ShowIPInfo();
					String ipInfo = ip.getIpAddr(request);
					services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件上传列表");
					return	Rjson.success(mapList);
				}
//			}else{
//				PageHelper.startPage(pageNum, pageSize);
////				list  = service.showDepartmentInfo(customerId);
//				list  = service.showBusinessInfoFileList(projectId, typeId);
//				PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
//				return Rjson.success(pageInfo);
//			}

	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}



	@RequestMapping(value = "/delMeetingMinutes", method = RequestMethod.POST)
	@ApiOperation(value = "删除上传文件", notes = "删除上传文件")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fileName", value = "文件名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "projectId", value = "项目ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "typeId", value = "类型ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delMeetingMinutes(HttpServletRequest request,HttpServletResponse response ) throws ServicesException, FileNotFoundException, IOException{

//		Properties pps = new Properties();
//        pps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
//        pps.load(new InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"), "UTF-8"));
//        pps .load( BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//        pps.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
//        while(enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
//        }
//        String path  =  pps.getProperty("QRCode");
//        String path  =  pps.getProperty("CRMFile");
//        String clearPath =  pps.getProperty("CRMClear");


		String fileName = request.getParameter("fileName");
//		String fileName="";
//		fileName= URLDecoder.decode(fileName,"utf-8");
		System.out.println("删除文件名称:"+fileName);
		Integer projectId = Integer.parseInt(request.getParameter("projectId"));
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
//		File file =new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId+"\\"+fileName);
		String filePath="";
		String filePath1="";
		String filePath2="";
		if(isOSLinux()) {
			filePath = path +"/"+ projectId + "/" + typeId+"/"+fileName;
		}else{
			filePath = path+"\\"+projectId+"\\"+typeId+"\\"+fileName;
		}
		File file =new File(filePath);
		if(file.exists()){
			  SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
		        Date d= new Date();
		        String beforeName = sdf.format(d);
//			File oldFile = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId);
			if(isOSLinux()) {
				filePath1 = fileName+projectId+"/"+typeId;
			}else{
				filePath1 = fileName+projectId+"\\"+typeId;
			}
		        File oldFile = new File(filePath1);
			if(!oldFile.exists()){
				oldFile.mkdirs();
			}
//			File oldFiles = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId+"\\(删除文件)"+beforeName+"-"+file.getName());
			if(isOSLinux()) {
				filePath2 = clearPath+projectId+"/"+typeId+"/(删除文件)"+beforeName+"-"+file.getName();
			}else{
				filePath2 = clearPath+projectId+"\\"+typeId+"\\(删除文件)"+beforeName+"-"+file.getName();
			}
			File oldFiles = new File(filePath2);
			file.renameTo(oldFiles);
			file.delete();
		}
		String userName = ToolUtils.getCookieUserName(request);
		ShowIPInfo ip = new ShowIPInfo();
		String ipInfo = ip.getIpAddr(request);
		services.addCRMLogInfo(userName+"("+ipInfo+")", "删除上传文件");
		return Rjson.success();
	}


	@RequestMapping(value = "/showAllFileType", method = RequestMethod.POST)
	@ApiOperation(value = "展示文件类型", notes = "展示文件类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "flags", value = "文件标记", required = false, paramType = "query", dataType = "string"),

		 })
	@ResponseBody
	public Rjson showAllFileType(HttpServletRequest request) throws ServicesException{

	 String flags = request.getParameter("flags");
		List<Map<String, Object>> list = null;

		try {
			if(flags!=null &&flags!=""){
				if(flags.equals("2")){
					list = service.showAllCompanyFileType();
				}
			}else{
			list = service.showAllFileType();
		}
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件类型");
		return Rjson.success(list);
	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}

	@RequestMapping(value = "/addFileType", method = RequestMethod.POST)
	@ApiOperation(value = "添加文件类型", notes = "添加文件类型")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectId", value = "项目ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson addFileType(HttpServletRequest request) throws ServicesException{
	Integer typeId = Integer.parseInt(request.getParameter("typeId"));
	Integer projectId = Integer.parseInt(request.getParameter("projectId"));
	String filePath ="/"+projectId+"/"+typeId;
		try {
			if(service.countFilePath(projectId, typeId)>0){
				return Rjson.error(202,"该文件类型已经存在");
			}else{
				service.addBusinessInfoFile(projectId, typeId, filePath);
				String userName = ToolUtils.getCookieUserName(request);
				ShowIPInfo ip = new ShowIPInfo();
				String ipInfo = ip.getIpAddr(request);
				services.addCRMLogInfo(userName+"("+ipInfo+")", "添加文件类型");
				return Rjson.success();
			}

	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}

	@RequestMapping(value = "/showFileType", method = RequestMethod.POST)
	@ApiOperation(value = "展示文件存储", notes = "展示文件存储")
	@ApiImplicitParams({
//		@ApiImplicitParam(name = "typeId", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "projectId", value = "项目ID", required = false, paramType = "query", dataType = "int"),

		 })
	@ResponseBody
	public Rjson showFileType(HttpServletRequest request) throws ServicesException{
//	Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		List<Map<String,Object>> list = new ArrayList<>();
	Integer projectId = Integer.parseInt(request.getParameter("projectId"));
//	String filePath ="/"+projectId+"/"+typeId;
		try {
//		service.addBusinessInfoFile(projectId, typeId, filePath);
			list = service.showBusinessInfoFileList(projectId);
			String userName = ToolUtils.getCookieUserName(request);
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
			services.addCRMLogInfo(userName+"("+ipInfo+")", "展示文件存储");
		return Rjson.success(list);
	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}

	@RequestMapping(value = "/delFileType", method = RequestMethod.POST)
	@ApiOperation(value = "删除文件存储", notes = "删除文件存储")
	@ApiImplicitParams({
//		@ApiImplicitParam(name = "typeId", value = "文件类型ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "id", value = "类型表ID", required = false, paramType = "query", dataType = "int"),

		 })
	@ResponseBody
	public Rjson delFileType(HttpServletRequest request) throws ServicesException{
	Integer id = Integer.parseInt(request.getParameter("id"));
		try {

		List<Map<String,Object>> showFileInfoByIds = service.showFileInfoById(id);
		System.out.println(showFileInfoByIds);
		String projectId = String.valueOf(showFileInfoByIds.get(0).get("PROJECT_ID"));
		String typeId = String.valueOf(showFileInfoByIds.get(0).get("TYPE_ID"));

//		Properties pps = new Properties();
//        pps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
//        pps.load(new InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"), "UTF-8"));
//        pps .load( BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/log4j.properties"));
//        pps.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
//        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
//        while(enum1.hasMoreElements()) {
//            String strKey = (String) enum1.nextElement();
//            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
//        }
//        String path  =  pps.getProperty("QRCode");
//        String path  =  pps.getProperty("CRMFile");
//        String clearPath =  pps.getProperty("CRMClear");

//	    File file = new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId);
			String filePath="";
			String filePath1="";
			String filePath2="";
			String filePath3="";
			if(isOSLinux()) {
				filePath = path+projectId+"/"+typeId;
			}else{
				filePath = path+projectId+"\\"+typeId;
			}
		File file = new File(filePath);
	    if (!file.exists()) { // 判断这个路径不存在
	    	file.mkdirs(); // 如果不存在就创建
		}
	    if(file.exists()){//文件存在
	    	File[] fileList = file.listFiles();//将所有文件放置在一个File类型的数组中
	    	for(int i=0;i<fileList.length;i++){
	    		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
			    Date d= new Date();
			    String beforeName = sdf.format(d);
//			    File oldFiles = new File("D:\\CRMFile\\file\\"+projectId+"\\"+typeId+"\\"+fileList[i].getName());
				if(isOSLinux()) {
					filePath1 = path+projectId+"/"+typeId+"/"+fileList[i].getName();
				}else{
					filePath1 = path+projectId+"\\"+typeId+"\\"+fileList[i].getName();
				}
			    File oldFiles = new File(filePath1);
//			    File dirs = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId);
				if(isOSLinux()) {
					filePath2 = clearPath+projectId+"/"+typeId;
				}else{
					filePath2 = clearPath+projectId+"\\"+typeId;
				}
			    File dirs = new File(filePath2);
			    if (!dirs.exists()) { // 判断这个路径不存在
			    	dirs.mkdirs(); // 如果不存在就创建
				}
//	    		File newFiles = new File("D:\\CRMFile\\file\\clearFile\\"+projectId+"\\"+typeId+"\\(删除文件)"+beforeName+"-"+fileList[i].getName());
				if(isOSLinux()) {
					filePath3 = clearPath+projectId+"/"+typeId+"/(删除文件)"+beforeName+"-"+fileList[i].getName();
				}else{
					filePath3 = clearPath+projectId+"\\"+typeId+"\\(删除文件)"+beforeName+"-"+fileList[i].getName();
				}
			    File newFiles = new File(filePath3);
	    		oldFiles.renameTo(newFiles);
	    		oldFiles.delete();
	    	}
	    	service.delBusinessInfoFile(id);
	    }

	    String userName = ToolUtils.getCookieUserName(request);
		ShowIPInfo ip = new ShowIPInfo();
		String ipInfo = ip.getIpAddr(request);
		services.addCRMLogInfo(userName+"("+ipInfo+")", "删除文件存储");
		return Rjson.success();
	}catch (Exception e) {
		e.printStackTrace();
		return Rjson.error(Constant.INTERFACE_EXCEPTION);
	}
	}










}
