package com.skeqi.mes.controller.qh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 巡检记录
 * @ClassName: CMesPatrolsController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "巡检记录", description = "巡检记录", produces = MediaType.APPLICATION_JSON)
public class CMesPatrolsController {


	@Autowired
	QualityService qualityService;

	@Autowired
	CMesProductionTService productionService;

	@Autowired
	CMesStationTService stationService;


	@RequestMapping(value = "/patrol/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询巡检记录", notes = "可根据多条件查询巡检记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "productId", value = "产品ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "stationId", value = "工位ID", required = false, paramType = "query", dataType = "int")
	})
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize,String sn,Integer productId,Integer stationId)throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		CMesPatrolT patrol = new CMesPatrolT();
		CMesProductionT pro = new CMesProductionT();
		CMesStationT station = new CMesStationT();
		if(productId!=null) {
			patrol.setProductionId(productId);
		}
		if(sn!=null) {
			patrol.setSn(sn);
		}
		if(stationId!=null) {
			patrol.setStationId(stationId);
		}

		JSONObject json = new JSONObject();

		try {

			List<CMesPatrolT> findPatrol = qualityService.findAllPatrol(patrol);

			//产品
			List<CMesProductionT> findProduction = productionService.findAllProductionL();

			//工位
			List<CMesStationT> findStation = stationService.findAllStation(station);

			PageInfo<CMesPatrolT> pageInfo = new PageInfo<CMesPatrolT>(findPatrol);

			json.put("code", 0);
			json.put("msg", pageInfo);
			json.put("productList", findProduction);
			json.put("stationList", findStation);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/patrol/addPatrol", method = RequestMethod.POST)
	@ApiOperation("新增巡检信息")
	@OptionalLog(module="质量", module2="巡检记录", method="新增巡检信息")
	public JSONObject addPatrol(HttpServletRequest request, @ModelAttribute CMesPatrolT cPatrolT){
		JSONObject json = new JSONObject();
		try {
			 qualityService.addPatrol(cPatrolT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/patrol/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询巡检信息", notes = "根据id查询巡检信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "巡检id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			CMesPatrolT patrolT = qualityService.findPatrolByid(id);
			json.put("msg", patrolT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/patrol/alterPatrol", method = RequestMethod.POST)
	@ApiOperation(value = "根据id修改巡检信息", notes = "根据id修改巡检信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	@OptionalLog(module="质量", module2="巡检记录", method="编辑巡检信息")
	public JSONObject alterPatrol(HttpServletRequest request, @ModelAttribute CMesPatrolT cPatrolT) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			qualityService.updatePatrol(cPatrolT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/patrol/delPatrol", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除巡检信息", notes = "根据id删除巡检信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "巡检id", required = true, dataType = "Integer")
	@ResponseBody
	@OptionalLog(module="质量", module2="巡检记录", method="删除巡检信息")
	public JSONObject delPatrol(HttpServletRequest request, Integer id) throws ServicesException {

		JSONObject json = new JSONObject();

		try {
			qualityService.delPatrol(id);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}



}
