package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

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
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.service.wms.ProjectService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author yinp 项目管理
 */
@RestController
@RequestMapping(value = "wms/project", produces = MediaType.APPLICATION_JSON)
//@Api(value = "项目管理", description = "项目管理", produces = MediaType.APPLICATION_JSON)
public class ProjectController {

	@Autowired
	ProjectService service;

	/**
	 * 查询产品类别
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "findProjectTypeAllIdAndName", method = RequestMethod.POST)
	public Rjson findProjectTypeAllIdAndName(HttpServletRequest request) throws IOException {
		try {
			List<JSONObject> list = service.findProjectTypeAllIdAndName();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改viewMode状态 用于删除
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
//	@ApiOperation(value = "修改viewMode状态 用于删除", notes = "修改viewMode状态 用于删除")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query") })
	@RequestMapping(value = "ViewMode", method = RequestMethod.POST)
	public Rjson ViewMode(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);

			boolean res = service.updateViewMode(projectId);
			if (res) {
				return Rjson.success("删除成功", true);
			} else {
				return Rjson.error("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
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
//	@ApiOperation(value = "查询集合", notes = "查询集合")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "managementNo", value = "管理编号", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "contractNo", value = "合同编号", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "startDate", value = "指派时间", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "endDate", value = "交货日期", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "projectLeader", value = "项目负责人", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "projectType", value = "项目类别", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			CWmsProject dx = new CWmsProject();
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			dx.setProjectName(projectName);

			PageHelper.startPage(pageNumber, 8);
			List<CWmsProject> lineList = service.findProjectList(dx);
			PageInfo<CWmsProject> pageInfo = new PageInfo<CWmsProject>(lineList, 5);
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
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
	@ApiImplicitParams({ @ApiImplicitParam(name = "managementNo", value = "管理编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "contractNo", value = "合同编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "productNumber", value = "产品数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "startDate", value = "指派时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "交货日期", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectNature", value = " 项目性质", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectLeader", value = "项目负责人", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectType", value = "项目类别", required = false, paramType = "query"),
			@ApiImplicitParam(name = "internalProjectNo", value = "内部项目号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目管理", method="新增项目")
	public Rjson addWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			CWmsProject dx = new CWmsProject();
			String managementNo = EqualsUtil.string(request, "managementNo", "管理编号", true);
			String contractNo = EqualsUtil.string(request, "contractNo", "合同编号", false);
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			Integer productNumber = EqualsUtil.integer(request, "productNumber", "产品数量", false);
			String startDate = EqualsUtil.string(request, "startDate", "指派时间", false);
			String endDate = EqualsUtil.string(request, "endDate", "交货日期", false);
			String projectNature = EqualsUtil.string(request, "projectNature", "项目性质", false);
			String projectLeader = EqualsUtil.string(request, "projectLeader", "项目负责人", false);
			Integer projectType = EqualsUtil.integer(request, "projectType", "项目类别", true);
			String internalProjectNo = EqualsUtil.string(request, "internalProjectNo", "内部项目号", false);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			dx.setManagementNo(managementNo);
			dx.setContractNo(contractNo);
			dx.setProjectName(projectName);
			dx.setProductNumber(productNumber);
			dx.setStartDate(startDate);
			dx.setEndDate(endDate);
			dx.setProjectNature(projectNature);
			dx.setProjectLeader(projectLeader);
			dx.setProjectType(projectType);
			dx.setInternalProjectNo(internalProjectNo);
			dx.setDis(dis);

			int res = service.addProject(dx);
			if (res==1) {
				return Rjson.success("新增成功", true);
			} else {
				throw new Exception("新增失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

	/**
	 * 更新
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "更新", notes = "更新")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "managementNo", value = "管理编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "contractNo", value = "合同编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "projectName", value = "项目名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "productNumber", value = "产品数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "startDate", value = "指派时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "交货日期", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectNature", value = " 项目性质", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectLeader", value = "项目负责人", required = false, paramType = "query"),
			@ApiImplicitParam(name = "projectType", value = "项目类别", required = false, paramType = "query"),
			@ApiImplicitParam(name = "internalProjectNo", value = "内部项目号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目管理", method="编辑项目")
	public Rjson updateWarehouse(HttpServletRequest request, HttpServletResponse response){
		try {
			Integer id = EqualsUtil.integer(request, "id", "产品id", true);
			String managementNo = EqualsUtil.string(request, "managementNo", "管理编号", true);
			String contractNo = EqualsUtil.string(request, "contractNo", "合同编号", false);
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			Integer productNumber = EqualsUtil.integer(request, "productNumber", "产品数量", false);
			String startDate = EqualsUtil.string(request, "startDate", "指派时间", false);
			String endDate = EqualsUtil.string(request, "endDate", "交货日期", false);
			String projectNature = EqualsUtil.string(request, "projectNature", "项目性质", false);
			String projectLeader = EqualsUtil.string(request, "projectLeader", "项目负责人", false);
			Integer projectType = EqualsUtil.integer(request, "projectType", "项目类别", true);
			String internalProjectNo = EqualsUtil.string(request, "internalProjectNo", "内部项目号", false);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("managementNo", managementNo);
			json.put("contractNo", contractNo);
			json.put("projectName", projectName);
			json.put("productNumber", productNumber);
			json.put("startDate", startDate);
			json.put("endDate", endDate);
			json.put("projectNature", projectNature);
			json.put("projectLeader", projectLeader);
			json.put("projectType", projectType);
			json.put("internalProjectNo", internalProjectNo);
			json.put("dis", dis);

			int res = service.updateProject(json);
			if (res==1) {
				return Rjson.success("更新成功", true);
			} else {
				throw new Exception("更新失败");
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
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目管理", method="删除项目")
	public Rjson deleteWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		try {
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			int res = service.deleteProject(projectId);
			if (res==1) {
				return Rjson.success("删除成功", true);
			} else {
				throw new Exception("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}

	}

}
