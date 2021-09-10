package com.skeqi.mes.controller.qh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.qh.CMesDefectManagerL;
import com.skeqi.mes.service.all.QualityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/***
 *
 * 缺陷管理
 *
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON)
@Api(value = "缺陷管理", description = "缺陷管理", produces = MediaType.APPLICATION_JSON)
public class CMesDefectController {

	@Autowired
	QualityService qualityService;

	/**
	 *
	 * 查询缺陷等级
	 */
	@RequestMapping(value = "/defect/findGrade", method = RequestMethod.POST)
	@ApiOperation(value = "查询缺陷等级", notes = "查询缺陷等级")
	public JSONObject findGrade() {
		JSONObject json = new JSONObject();
		try {
			List<CMesDefectGradeManagerT> list = qualityService.findAllDefectGrade(null);
			json.put("code", 0);
			json.put("msg", list);
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/defect/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除缺陷信息", notes = "根据id删除缺陷信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "缺陷id", required = true, dataType = "Integer")
	public JSONObject delDefect(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.delDefectManager(id);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		json.put("flag", true);
		return json;
	}

	@RequestMapping(value = "/defect/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询缺陷信息", notes = "根据id查询缺陷信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "缺陷id", required = true, dataType = "Integer")
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			CMesDefectManager defectManager = qualityService.findDefectManagerByid(id);
			json.put("msg", defectManager);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}

	@RequestMapping(value = "/defect/add", method = RequestMethod.POST)
	@ApiOperation("新增缺陷信息")
	public JSONObject addDefect(HttpServletRequest request, @ModelAttribute CMesDefectManager cDefectManager) {
		JSONObject json = new JSONObject();
		try {
			qualityService.addDefectManager(cDefectManager);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return json;
	}

	@RequestMapping(value = "/defect/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改缺陷信息", notes = "根据id修改缺陷信息")
	public JSONObject alterIQC(HttpServletRequest request, @ModelAttribute CMesDefectManager cDefectManager) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.updateDefectManager(cDefectManager);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return json;
	}

	@RequestMapping(value = "/defect/findList", method = RequestMethod.POST)
	@ApiOperation(value = "缺陷列表", notes = "缺陷列表·")
	public JSONObject findList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			Integer pageNum = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNum, pageSize);

			List<CMesDefectManagerL> findAll = qualityService.findAllDefectManagerL();
			PageInfo<CMesDefectManagerL> pageinfo = new PageInfo<CMesDefectManagerL>(findAll);

			json.put("code", 0);
			json.put("msg", pageinfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
