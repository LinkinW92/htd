package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

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
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 *
 * 缺陷等级管理
 *
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "缺陷等级管理", description = "缺陷等级管理", produces = MediaType.APPLICATION_JSON)
public class CMesDefectGradeController {

	@Autowired
	QualityService qualityService;

	@RequestMapping(value = "/defectGrade/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除缺陷等级信息", notes = "根据id删除等级缺陷信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "缺陷id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delDefect(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.delDefectGrade(id);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/defectGrade/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询缺陷等级信息", notes = "根据id查询缺陷等级信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "缺陷id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			CMesDefectGradeManagerT defectGradeManage = qualityService.findDefectGradeByid(id);
			json.put("code", 0);
			json.put("defectGradeManage", defectGradeManage);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/defectGrade/add", method = RequestMethod.POST)
	@ApiOperation("新增缺陷等级信息")
	public JSONObject addDefect(@ModelAttribute CMesDefectGradeManagerT defectGradeManage) {
		JSONObject json = new JSONObject();

		try {
			qualityService.addDefectGrade(defectGradeManage);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/defectGrade/alter", method = RequestMethod.POST)
	@ApiOperation(value = "修改缺陷等级信息", notes = "根据id修改缺陷等级信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	public JSONObject alterIQC(@ModelAttribute CMesDefectGradeManagerT defectGradeManage) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.updateDefectGrade(defectGradeManage);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;

	}

	@RequestMapping(value = "/defectGrade/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询缺陷等级信息", notes = "查询缺陷等级信息")
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		JSONObject json = new JSONObject();
		try {
			CMesDefectGradeManagerT defectGradeManage = new CMesDefectGradeManagerT();
			List<CMesDefectGradeManagerT> defectGradeList = qualityService.findAllDefectGrade(defectGradeManage);
			PageInfo<CMesDefectGradeManagerT> pageInfo = new PageInfo<>(defectGradeList, 8);

			json.put("code", 0);
			json.put("msg", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
