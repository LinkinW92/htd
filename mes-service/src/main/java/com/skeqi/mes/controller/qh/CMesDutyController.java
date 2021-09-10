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
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 *
 * 责任管理
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "责任管理", description = "责任管理", produces = MediaType.APPLICATION_JSON)
public class CMesDutyController {

	@Autowired
	QualityService qualityService;

	/**
	 *
	 * 责任管理
	 */
	@RequestMapping(value = "/duty/findType", method = RequestMethod.POST)
	@ApiOperation(value = "查询责任类型", notes = "查询责任类型")
	@ResponseBody
	public JSONObject findType() {
		JSONObject json = new JSONObject();
		CMesDutyTypeManagerT dtm = new CMesDutyTypeManagerT();
		List<CMesDutyTypeManagerT> list;
		try {
			list = qualityService.findAllDutyType(dtm);
			json.put("code", 0);
			json.put("msg", list);
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;

	}

	@RequestMapping(value = "/duty/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询责任信息", notes = "查询责任信息")
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		JSONObject json = new JSONObject();

		try {
			CMesDutyManagerT cMesDutyManagerT = new CMesDutyManagerT();
			List<CMesDutyManagerT> findAll = qualityService.findAllDutyManager(cMesDutyManagerT);
			PageInfo<CMesDutyManagerT> pageinfo = new PageInfo<CMesDutyManagerT>(findAll);

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

	@RequestMapping(value = "/duty/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除责任信息", notes = "根据id删除责任信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			qualityService.delDutyManager(id);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/duty/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询责任信息", notes = "根据id查询责任信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			CMesDutyManagerT dutyManager = qualityService.findDutyManagerByid(id);
			json.put("dutyManager", dutyManager);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/duty/add", method = RequestMethod.POST)
	@ApiOperation("新增责任信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "dutyId", value = "责任编号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyName", value = "责任名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyTypeId", value = "责任类型id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyDis", value = "责任描述", required = false, paramType = "query"),
	 })
	public JSONObject add(HttpServletRequest request, String dutyId,String dutyName,Integer dutyTypeId,String dutyDis) {
		JSONObject json = new JSONObject();
         CMesDutyManagerT cMesDutyManagerT = new CMesDutyManagerT();
         cMesDutyManagerT.setDutyDis(dutyDis);
         cMesDutyManagerT.setDutyId(dutyId);
         cMesDutyManagerT.setDutyName(dutyName);
         cMesDutyManagerT.setDutyTypeId(dutyTypeId);
		try {
			qualityService.addDutyManager(cMesDutyManagerT);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}

	@RequestMapping(value = "/duty/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改责任信息", notes = "根据id修改责任信息")
	@ResponseBody
	@ApiImplicitParams({ @ApiImplicitParam(name = "dutyId", value = "责任编号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyName", value = "责任名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyTypeId", value = "责任类型id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dutyDis", value = "责任描述", required = false, paramType = "query"),
		@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject update(HttpServletRequest request, String dutyId,String dutyName,Integer dutyTypeId,String dutyDis,Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		   CMesDutyManagerT cMesDutyManagerT = new CMesDutyManagerT();
	         cMesDutyManagerT.setDutyDis(dutyDis);
	         cMesDutyManagerT.setDutyId(dutyId);
	         cMesDutyManagerT.setDutyName(dutyName);
	         cMesDutyManagerT.setDutyTypeId(dutyTypeId);
	         cMesDutyManagerT.setId(id);
		try {
			qualityService.updateDutyManager(cMesDutyManagerT);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return json;
	}

}
