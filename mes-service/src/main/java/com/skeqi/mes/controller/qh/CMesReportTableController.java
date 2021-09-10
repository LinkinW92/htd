package com.skeqi.mes.controller.qh;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.qh.CMesReportTableService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/dataReport", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据报表管理", description = "数据报表管理", produces = MediaType.APPLICATION_JSON)
public class CMesReportTableController {

	@Autowired
	CMesReportTableService service;

	@ResponseBody
	@RequestMapping(value = "/reportTable", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据报表的所有表")
	public Rjson reportTable() {
		return Rjson.success(service.findAllReport());
	}

	@ResponseBody
	@RequestMapping(value = "/reportTableColumn", method = RequestMethod.POST)
	@ApiOperation(value = "查询该表的所有列")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableReportId", value = "表id", required = false, paramType = "query") })
	public Rjson reportTableColumn(HttpServletRequest request) {
		Object tableReportId = request.getParameter("tableReportId");
		if(tableReportId == null || "".equals(tableReportId.toString())){
			return Rjson.error("参数为空");
		}
		Integer id = Integer.parseInt(tableReportId.toString());
		return Rjson.success(service.findColumnById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/updateReportStatus", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tableReportId", value = "表id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "tableColumnsName", value = "列名", required = false, paramType = "query"),
			@ApiImplicitParam(name = "showFlag", value = "显示标记", required = false, paramType = "query") })
	@ApiOperation(value = "修改该表可以查看的列")
	public Rjson updateReportStatus(HttpServletRequest request, Integer tableReportId, String tableColumnsName, Integer showFlag) {
		try {
			Integer result = service.updateStatus(tableReportId, tableColumnsName, showFlag);
			if (result > 0) {
				return Rjson.success();
			} else {
				return Rjson.error("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/findDataReport", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据报表内容")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "查询数", required = false, paramType = "query"),
			@ApiImplicitParam(name = "tableReportId", value = "表id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "sn", value = "总成号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query") })
	public Rjson findDataReport(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request) {
		try {
			Integer tableReportId = Integer.parseInt(request.getParameter("tableReportId"));
			// 根据表id查询表名
			String tableName = service.getTableNameById(tableReportId);

			Map<String, String> map = new HashMap<>();
			map.put("startTime", request.getParameter("startTime"));
			map.put("endTime", request.getParameter("endTime"));
			map.put("sn", request.getParameter("sn"));
			map.put("tableName", tableName);

			PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list = service.findDataReport(map);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			List<Map<String, Object>> l = pageInfo.getList();
			for (Map<String, Object> m : l) {
				for (Map.Entry<String, Object> entry : m.entrySet()) {
					Object value = entry.getValue();
					if(value != null && "Timestamp".equals(value.getClass().getSimpleName())){
//						entry.setValue(value.toString());
						entry.setValue(sdf.format((Timestamp)value));
					}
				}
			}
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	// 导出excel
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	@ApiOperation(value = "导出数据报表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "查询数", required = false, paramType = "query"),
			@ApiImplicitParam(name = "tableReportId", value = "表id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "sn", value = "总成号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query") })
	public ResponseEntity<byte[]> exportExcel(HttpServletRequest request) {
		try {
			Map<String, String> map = new HashMap<>();
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			if(pageNum == null) {
				pageNum = "1";
			}
			if(pageSize == null) {
				pageSize = "10";
			}
			map.put("pageNum", pageNum);
			map.put("pageSize", pageSize);
			map.put("startTime", request.getParameter("startTime"));
			map.put("endTime", request.getParameter("endTime"));
			map.put("sn", request.getParameter("sn"));
			map.put("tableReportId", request.getParameter("tableReportId"));

			return service.exportExcel(map);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return null;
		}
	}
}
