package com.skeqi.mes.controller.zch;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.LogService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.ExcelExportUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 日志管理
 * @author SKQ
 *
 */
@Controller
@RequestMapping(value = "log", produces = MediaType.APPLICATION_JSON)
@Api(value = "日志管理", description = "日志管理", produces = MediaType.APPLICATION_JSON)
public class LogController {
	Logger log = Logger.getLogger(LogController.class);

	@Autowired
	LogService logService;

	@RequestMapping(value = "/findOperationLogList", method = RequestMethod.POST)
	@ResponseBody
	public Rjson findOperationLogList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("module", request.getParameter("module"));
			map.put("username", request.getParameter("username"));
			map.put("beginTime", request.getParameter("beginTime"));
			map.put("endTime", request.getParameter("endTime"));

			Integer pageNum = 1;
			Integer pageSize = 6;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			PageHelper.startPage(pageNum, pageSize);
			list=logService.findOperationLogList(map);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/exportOperationLogList", method = RequestMethod.GET)
	@ResponseBody
	public void exportOperationLogList(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("module", request.getParameter("module"));
			map.put("username", request.getParameter("username"));
			map.put("beginTime", request.getParameter("beginTime"));
			map.put("endTime", request.getParameter("endTime"));

			list=logService.findOperationLogList(map);

			//excel标题
	        String[] title = {"操作时间", "操作用户", "一级模块", "二级模块", "操作明细", "IP地址"};

	        //excel文件名
	        String fileName = "操作日志表"+System.currentTimeMillis()+".xls";

	        //sheet名
	        String sheetName = "操作日志表";

	        String[][] content = new String[list.size()][];

	        for (int i = 0; i < list.size(); i++) {
	            content[i] = new String[title.length];
	            Map<String, Object> obj = list.get(i);
	            content[i][0] = obj.get("dt").toString();
	            content[i][1] = obj.get("username").toString();
	            content[i][2] = obj.get("module").toString();
	            content[i][3] = obj.get("module2").toString();
	            content[i][4] = obj.get("methods").toString();
	            content[i][5] = obj.get("ip").toString();
	        }

	        //创建HSSFWorkbook
	        HSSFWorkbook wb = ExcelExportUtil.getHSSFWorkbook(sheetName, title, content, null);

	     	//响应到客户端
	        fileName = URLEncoder.encode(fileName, "UTF-8");
	        response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

         	OutputStream os = response.getOutputStream();
         	wb.write(os);
         	os.flush();
         	os.close();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

	@RequestMapping(value = "/writeVisitLog", method = RequestMethod.POST)
	@ApiOperation(value="写入访问日志",notes = "写入访问日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "pageName", value = "页面", required = false, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public Rjson writeVisitLog(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			String username = ToolUtils.getCookieUserName(request);
			String pageName = request.getParameter("pageName");
			String ip = ToolUtils.getRealIp(request);
			log.warn("(" + ip +  ") " + username + " [访问] " + pageName);

			map.put("username", username);
			map.put("pageName", pageName);
			map.put("ip", ip);

			logService.addVisitLog(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findVisitLogList", method = RequestMethod.POST)
	@ApiOperation(value="查询访问日志",notes = "查询访问日志")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public Rjson findVisitLogList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();

		try {

			String username = request.getParameter("username");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			Integer pageNum = 1;
			Integer pageSize = 10;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			map.put("username", username);
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);

//			BufferedReader in = new BufferedReader(new FileReader("D:/qhlog/log.log"));
//	        String str;
//	        while ((str = in.readLine()) != null) {
//	            if(str.contains("访问")) {
//	            	System.out.println(str);
//	            }
//	        }
			PageHelper.startPage(pageNum, pageSize);
			list=logService.findVisitLogList(map);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
