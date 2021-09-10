package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "员工类型管理", description = "员工类型管理", produces = MediaType.APPLICATION_JSON)
public class CMesEmpTypeControllerl {


	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;
	@Autowired
	CMesRoleTService roleService;

	/**
	 * 员工类型管理
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/empTypeList", method = RequestMethod.POST)
	@ApiOperation(value = "员工类型列表", notes = "员工类型列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int") })
	public JSONObject empTypeList(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
		JSONObject json = new JSONObject();

		PageHelper.startPage(pageNum,pageSize);
		CMesEmpTypeT emp = new CMesEmpTypeT();
		List<CMesEmpTypeT> empTypeList = null;
		try {
			 empTypeList = produceService.findAllEmpType(emp);
			PageInfo<CMesEmpTypeT> pageInfo = new PageInfo<>(empTypeList);
			json.put("empTypeList", pageInfo);
		}catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 添加员工类型
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addEmpType", method = RequestMethod.POST)
	@ApiOperation(value = "添加员工类型", notes = "添加员工类型")
	@OptionalLog(module="生产", module2="员工类型管理", method="新增员工类型")
	public  JSONObject addEmpType(@ModelAttribute CMesEmpTypeT cMesEmpTypeT) {
		JSONObject json = new JSONObject();
		try {
			produceService.addEmpType(cMesEmpTypeT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/toUpdateEmpType", method = RequestMethod.POST)
	@ApiOperation(value = "回显", notes = "回显")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "员工类型id", required = false, paramType = "query", dataType = "int"),})
	public  JSONObject toUpdateEmpType(Integer id){
		JSONObject json = new JSONObject();
		try {
			CMesEmpTypeT empType = produceService.findEmpTypeByid(id);
			json.put("code", 0);
			json.put("empTypes", empType);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/editEmpType", method = RequestMethod.POST)
	@ApiOperation(value = "修改", notes = "修改")
	@OptionalLog(module="生产", module2="员工类型管理", method="编辑员工类型")
	public  JSONObject editEmpType(@ModelAttribute CMesEmpTypeT cMesEmpTypeT) {
		JSONObject json = new JSONObject();
		try {
			produceService.updateEmpType(cMesEmpTypeT);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}
	@RequestMapping(value = "/delEmpType", method = RequestMethod.POST)
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "员工类型id", required = false, paramType = "query", dataType = "int"),})
	@OptionalLog(module="生产", module2="员工类型管理", method="删除员工类型")
	public JSONObject delEmpType(Integer id) {
		JSONObject json = new JSONObject();
		try {
			produceService.delEmpType(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

}
