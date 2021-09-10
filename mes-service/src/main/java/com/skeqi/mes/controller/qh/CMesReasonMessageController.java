package com.skeqi.mes.controller.qh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 *
 * @author ENS 原因管理
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "原因管理", description = "原因管理", produces = MediaType.APPLICATION_JSON)
public class CMesReasonMessageController {

	@Autowired
	QualityService qualityService;

	@RequestMapping(value = "/reason/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询原因信息", notes = "查询原因信息")
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		JSONObject json = new JSONObject();

		try {
			CMesDefectResionT dr = new CMesDefectResionT();
			List<CMesDefectResionT> list = qualityService.findAllReason(dr);
			PageInfo<CMesDefectResionT> pageInfo = new PageInfo<>(list, 3);

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

	@ResponseBody
	@RequestMapping(value = "/reason/add", method = RequestMethod.POST)
	@ApiOperation("新增原因")
	public JSONObject addDefect(HttpServletRequest request, @ModelAttribute CMesDefectResionT cDefectResionT) {
		JSONObject json = new JSONObject();

		try {
			qualityService.addReason(cDefectResionT);
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


	@RequestMapping(value = "/reason/del", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除原因信息", notes = "根据id删除原因信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "缺陷id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delDefect(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.delReason(id);
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

	@RequestMapping(value = "/reason/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询原因信息", notes = "根据id查询原因信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "原因id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {

		JSONObject json = new JSONObject();

		try {
			CMesDefectResionT resion = qualityService.findReasonByid(id);
			json.put("code", 0);
			json.put("resion", resion);
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


	@RequestMapping(value = "/reason/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改原因信息", notes = "根据id修改原因信息")
	@ResponseBody

	public JSONObject alterIQC(HttpServletRequest request, @ModelAttribute CMesDefectResionT cDefectResionT) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.updateReason(cDefectResionT);
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
