package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.service.wms.ProcessApprovalDetailService;
import com.skeqi.mes.service.wms.ProcessApprovalService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 审批流程
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/processApproval", produces = MediaType.APPLICATION_JSON)
@Api(value = " 审批流程", description = " 审批流程", produces = MediaType.APPLICATION_JSON)
public class ProcessApprovalController {

	@Autowired
	ProcessApprovalDetailService service;

	@Autowired
	ProcessApprovalService PAservice;

	/**
	 * 通过userid查询user
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "通过userid查询user", notes = "通过userid查询user")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query") })
	@RequestMapping(value = "findUserById", method = RequestMethod.POST)
	public Rjson findUserById(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户", true);;
			JSONObject dx = PAservice.findUserById(userId);
			return Rjson.success(dx);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过部门个跟角色查询用户
	 *
	 * @return
	 */
	@ApiOperation(value = "通过部门个跟角色查询用户", notes = "通过部门个跟角色查询用户")
	@ApiImplicitParams({ @ApiImplicitParam(name = "departmentId", value = "部门id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query") })
	@RequestMapping(value = "findUserAll", method = RequestMethod.POST)
	public Rjson findUserAll(HttpServletRequest request) {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色", true);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门", true);
			List<JSONObject> list = PAservice.findUserAll(departmentId, roleId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有审批类型
	 *
	 * @return
	 */
	@ApiOperation(value = "查询所有审批类型", notes = "查询所有审批类型")
	@ApiImplicitParams({})
	@RequestMapping(value = "findProcessTypeAll", method = RequestMethod.POST)
	public Rjson findProcessTypeAll() {
		try {
			List<JSONObject> list = PAservice.findProcessTypeAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有部门名称
	 *
	 * @return
	 */
	@ApiOperation(value = "查询所有部门名称", notes = "查询所有部门名称")
	@ApiImplicitParams({})
	@RequestMapping(value = "findDepartmentAll", method = RequestMethod.POST)
	public Rjson findDepartmentAll() {
		try {
			List<JSONObject> list = PAservice.findDepartmentAll();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有角色id+name
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询所有角色id+name", notes = "查询所有角色id+name")
	@ApiImplicitParams({})
	@RequestMapping(value = "findRoleIdAndName", method = RequestMethod.POST)
	public Rjson findRoleIdAndName(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<JSONObject> list = PAservice.findRoleIdAndName();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dmId", value = "部门id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "processTypeId", value = "审批类型id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色", false);
			Integer dmId = EqualsUtil.integer(request, "dmId", "部门", false);
			Integer processTypeId = EqualsUtil.integer(request, "processTypeId", "审批类型", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("deptId", dmId);
			map.put("typeId", processTypeId);
			PageHelper.startPage(pageNumber, 8);
			List<ProcessApprovalDetail> lineList = service.findProcessApprovalDetailList(map);
			PageInfo<ProcessApprovalDetail> pageInfo = new PageInfo<ProcessApprovalDetail>(lineList, 5);
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询最大优先级
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询最大优先级", notes = "查询最大优先级")
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dmId", value = "部门id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "typeId", value = "审批类型id", required = true, paramType = "query") })
	@RequestMapping(value = "findMaxPriorityLevel", method = RequestMethod.POST)
	public Rjson findMaxPriorityLevel(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色", true);
			Integer dmId = EqualsUtil.integer(request, "dmId", "部门", true);
			Integer typeId = EqualsUtil.integer(request, "typeId", "审批类型", true);
			Integer maxPriorityLevel = service.findMaxPriorityLevel(dmId, roleId, typeId);
			return Rjson.success("查询成功", maxPriorityLevel);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "修改", notes = "修改")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dmId", value = "部门id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "typeId", value = "审批类型id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "priorityLevel", value = "优先级", required = false, paramType = "query"),
			@ApiImplicitParam(name = "qpriorityLevel", value = "修改前优先级", required = true, paramType = "query") })
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@Transactional
	@OptionalLog(module="仓管", module2="审批流程", method="编辑审批流程")
	public Rjson update(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色", true);
			Integer dmId = EqualsUtil.integer(request, "dmId", "部门", true);
			Integer typeId = EqualsUtil.integer(request, "typeId", "审批类型", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户", true);
			Integer priorityLevel = EqualsUtil.integer(request, "priorityLevel", "优先级", true);
			Integer qpriorityLevel = EqualsUtil.integer(request, "qpriorityLevel", "修改前的优先级", true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("dmId", dmId);
			map.put("roleId", roleId);
			map.put("typeId", typeId);
			map.put("userId", userId);
			map.put("priorityLevel", priorityLevel);
			map.put("qpriorityLevel", qpriorityLevel);
			boolean res = PAservice.updateProcessApproval(map);
			if (res) {
				return Rjson.success("修改成功", true);
			} else {
				return Rjson.error("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 新增
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "新增", notes = "新增")
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dmId", value = "部门id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "typeId", value = "审批类型id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "priorityLevel", value = "优先级", required = false, paramType = "query"),
			@ApiImplicitParam(name = "qpriorityLevel", value = "修改前的优先级", required = false, paramType = "query") })
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@Transactional
	@OptionalLog(module="仓管", module2="审批流程", method="新增审批流程")
	public Rjson add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServicesException {
		try {
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色", true);
			Integer dmId = EqualsUtil.integer(request, "dmId", "部门", true);
			Integer typeId = EqualsUtil.integer(request, "typeId", "审批类型", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户", true);
			Integer priorityLevel = EqualsUtil.integer(request, "priorityLevel", "优先级", true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dmId", dmId);
			map.put("roleId", roleId);
			map.put("typeId", typeId);
			map.put("userId", userId);
			map.put("priorityLevel", priorityLevel);
			boolean res = PAservice.addProcessApproval(map);
			if (res) {
				return Rjson.success("新增成功", true);
			} else {
				return Rjson.error("新增失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "processApprovalDetailId", value = "审批流程详情id", required = true, paramType = "query") })
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="审批流程", method="删除审批流程")
	public Rjson delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer processApprovalDetailId =EqualsUtil.integer(request, "processApprovalDetailId", "审批流程详情", true);
			boolean res = service.deleteProcessApprovalDetail(processApprovalDetailId);
			if (res) {
				return Rjson.success("删除成功", true);
			} else {
				return Rjson.error("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}

	}
}
