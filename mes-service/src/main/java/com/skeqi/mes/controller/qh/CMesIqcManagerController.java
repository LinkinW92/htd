package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 *
 * IQC检验
 *
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "IQC检验", description = "IQC检验", produces = MediaType.APPLICATION_JSON)
public class CMesIqcManagerController {

	@Autowired
	QualityService qualityService;

	@Autowired
	CMesMaterialService materialService;

	@RequestMapping(value = "/iqc/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询iqc信息", notes = "可根据多条件查询iqc信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "factoryNo", value = "工厂编号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "materialVoucher", value = "物料凭证号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "checkBatch", value = "校验批次", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "otigin", value = "校验批来源", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "materialNo", value = "物料号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "emp", value = "创建人", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "checkPerson", value = "校验人", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "productionHandie", value = "产品处置", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "act_start_time", value = "开始时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "act_stop_time", value = "结束时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, String factoryNo, String materialVoucher,
			String checkBatch, String otigin, String materialNo, String supplierName, String emp, String status,
			String checkPerson, String materialName, String act_start_time, String act_stop_time,
			String productionHandie) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		if (productionHandie == null || productionHandie.endsWith("")) {
			productionHandie = "1";
		}

		if (status == null || status.equals("")) {
			status = "1";
		}

		map.put("factoryNo", factoryNo);
		map.put("materialName", materialName);
		map.put("materialVoucher", materialVoucher);
		map.put("checkBatch", checkBatch);
		map.put("otigin", otigin);
		map.put("materialNo", materialNo);
		map.put("supplierName", supplierName);
		map.put("emp", emp);
		map.put("checkPerson", checkPerson);
		map.put("productionHandie", productionHandie);
		map.put("status", status);
		map.put("act_start_time", act_start_time);
		map.put("act_stop_time", act_stop_time);

		JSONObject json = new JSONObject();

		try {
			List<CMesIqcCheckT> findAll = qualityService.findAllIQC(map);
			PageInfo<CMesIqcCheckT> pageinfo = new PageInfo<CMesIqcCheckT>(findAll);

			CMesJlMaterialT jl = new CMesJlMaterialT();
			List<CMesJlMaterialT> findAll2 = materialService.findAllMaterial(jl);

			json.put("code", 0);
			json.put("msg", pageinfo);
			json.put("materialList", findAll2);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/iqc/addIQC", method = RequestMethod.POST)
	@ApiOperation("新增IQC校验")
	@OptionalLog(module="质量", module2="IQC检验", method="新增IQC")
	public JSONObject insertIQC(@ModelAttribute CMesIqcCheckT cMesIqcCheckT) {
		JSONObject json = new JSONObject();

		try {
			Integer addIQC = qualityService.addIQC(cMesIqcCheckT);
			if (addIQC == 1) {
				json.put("code", 0);
				json.put("msg", "新增成功");
			} else {
				json.put("code", 1);
				json.put("msg", "后端异常，新增失败");
			}

		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/iqc/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询IQC校验信息", notes = "根据id查询IQC校验信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "iqc校验id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			CMesIqcCheckT iqcCheckT = qualityService.findIQCByid(id);
			json.put("code", 0);
			json.put("msg", iqcCheckT);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/iqc/alterIQC", method = RequestMethod.POST)
	@ApiOperation(value = "修改IQC校验信息", notes = "根据id修改IQC校验信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	@OptionalLog(module="质量", module2="IQC检验", method="编辑IQC")
	public JSONObject alterIQC(HttpServletRequest request, CMesIqcCheckT iqcCheckT) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			qualityService.updateIQC(iqcCheckT);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;

	}

	@RequestMapping(value = "/iqc/alterStatus", method = RequestMethod.POST)
	@ApiOperation(value = "修改当前iqc状态", notes = "根据id，产品处置状态修改当前iqc状态")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "productionHandie", value = "产品处置状态", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "id", value = "iqc校验id", required = true, dataType = "Integer") })
	@ResponseBody
	@OptionalLog(module="质量", module2="IQC检验", method="修改IQC状态")
	public JSONObject alterStatus(HttpServletRequest request, Integer id, String productionHandie) {
		JSONObject json = new JSONObject();

		if (productionHandie.equals("入库")) {
			productionHandie = "3";
		} else {
			productionHandie = "2";
		}
		try {
			qualityService.updateStatus(id, productionHandie);
			json.put("code", 0);
		} catch (ServicesException e) {
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

	@RequestMapping(value = "/iqc/alterFreezeState", method = RequestMethod.POST)
	@ApiOperation(value = "修改当前iqc为冻结状态", notes = "根据id，冻结状态修改")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "freeze", value = "冻结状态", required = true, dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "id", value = "iqc校验id", required = true, dataType = "Integer") })
	@ResponseBody
	@OptionalLog(module="质量", module2="IQC检验", method="修改IQC为冻结状态")
	public JSONObject alterFreezeState(HttpServletRequest request, Integer id, Integer freeze) {
		JSONObject json = new JSONObject();

		try {
			qualityService.updateFreeze(id, freeze);
			json.put("code", 0);
		} catch (ServicesException e) {
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
