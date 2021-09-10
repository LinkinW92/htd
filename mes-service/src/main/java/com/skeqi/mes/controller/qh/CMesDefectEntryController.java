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
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/***
 *
 * 缺陷录入
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "缺陷录入", description = "缺陷录入", produces = MediaType.APPLICATION_JSON)
public class CMesDefectEntryController {

	@Autowired
	QualityService qualityService;
	@Autowired
	CMesProductionTService productionService;



	@RequestMapping(value = "/defectEntry/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询完成工单", notes = "可根据工单编号，起止时间查询对应完成工单信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "startTiem", value = "开始时间", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "endTiem", value = "结束时间", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10")Integer pageSize,String sn,String startTiem,String endTiem)throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		CMesDefectEntryT entry = new CMesDefectEntryT();
			entry.setStarttime(startTiem);
			entry.setEndtime(endTiem);
			entry.setSn(sn);
		JSONObject json = new JSONObject();

		try {
			List<CMesDefectEntryT> findAll = qualityService.findAllDefectEntry(entry);
			PageInfo<CMesDefectEntryT> pageInfo = new PageInfo<CMesDefectEntryT>(findAll);
			List<CMesProductionT> prodectionList = productionService.findAllProductionL();

			json.put("code", 0);
			json.put("msg", pageInfo);
			json.put("prodectionList", prodectionList);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/defectEntry/addDefect", method = RequestMethod.POST)
	@ApiOperation("新增缺陷")
	@OptionalLog(module="质量", module2="缺陷录入", method="新增缺陷录入")
	public JSONObject addDefect(@ModelAttribute CMesDefectEntryT cDefectEntryT){

		JSONObject json = new JSONObject();
		try {
			qualityService.addDefectEntry(cDefectEntryT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}


	@RequestMapping(value = "/defectEntry/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询缺陷信息", notes = "根据id查询缺陷信息")
	@ApiImplicitParam(name = "id", value = "缺陷ID", required = false, paramType = "query", dataType = "int")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			CMesDefectEntryT cDefectEntryT  = qualityService.findDefectEntryByid(Integer.parseInt(id));
			json.put("msg", cDefectEntryT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/defectEntry/alterDefect", method = RequestMethod.POST)
	@ApiOperation("修改缺陷")
	@OptionalLog(module="质量", module2="缺陷录入", method="编辑缺陷录入")
	public JSONObject alterDefect(@ModelAttribute CMesDefectEntryT cDefectEntryT){
		JSONObject json = new JSONObject();

		try {
			qualityService.updateDefectEntry(cDefectEntryT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/defectEntry/delDefect", method = RequestMethod.POST)
	@ApiOperation(value="删除缺陷", notes = "根据ID删除缺陷")
	@ApiImplicitParam(name = "id", value = "缺陷ID", required = false, paramType = "query", dataType = "int")
	@OptionalLog(module="质量", module2="缺陷录入", method="删除缺陷录入")
	public JSONObject  deleteentry(Integer id){
		JSONObject json = new JSONObject();
		try {
			qualityService.delDefectEntry(id);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}



}
