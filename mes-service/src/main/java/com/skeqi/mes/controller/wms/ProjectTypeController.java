package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsProjectType;
import com.skeqi.mes.service.wms.ProjectTypeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *
 * @author yinp 项目类型
 */
@RestController
@RequestMapping(value = "wms/projectType", produces = MediaType.APPLICATION_JSON)
@Api(value = "项目类型", description = "项目类型", produces = MediaType.APPLICATION_JSON)
public class ProjectTypeController {

	@Autowired
	ProjectTypeService service;

	/**
	 * 修改viewMode状态 用于删除
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改viewMode状态 用于删除", notes = "修改viewMode状态 用于删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projectTypeId", value = "项目类型id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "viewMode", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目类型管理", method="删除项目类型")
	public Rjson viewMode(HttpServletRequest request) {
		try {
			CWmsProjectType dx = new CWmsProjectType();
			dx.setId(EqualsUtil.integer(request, "projectTypeId", "项目类型id", true));
			dx.setViewMode(0);
			int res = service.updateProjectType(dx);
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

	/**
	 * 查询集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsProjectType> lineList = service.findProjectTypeList(null);
			PageInfo<CWmsProjectType> pageInfo = new PageInfo<CWmsProjectType>(lineList, 5);

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
	 */
	@ApiOperation(value = "新增", notes = "新增")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projectType", value = "项目类型名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目类型管理", method="新增项目类型")
	public Rjson addWarehouse(HttpServletRequest request) {
		try {
			CWmsProjectType dx = new CWmsProjectType();
			String projectType = EqualsUtil.string(request, "projectType", "项目类型名称", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			dx.setProjectType(projectType);
			dx.setDis(dis);

			int res = service.addProjectType(dx);
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
	 */
	@ApiOperation(value = "更新", notes = "更新")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "projectTypeName", value = "项目类型名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目类型管理", method="编辑项目类型")
	public Rjson updateWarehouse(HttpServletRequest request) {
		try {
			CWmsProjectType dx = new CWmsProjectType();
			int id = EqualsUtil.integer(request, "id", "项目类型id", true);
			String projectType = EqualsUtil.string(request, "projectType", "项目类型名称", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			dx.setId(id);
			dx.setProjectType(projectType);
			dx.setDis(dis);

			int res = service.updateProjectType(dx);
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
	 */
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "projectTypeId", value = "项目类型id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="项目类型管理", method="删除项目类型")
	public Rjson deleteWarehouse(HttpServletRequest request) {
		try {

			Integer projectTypeId = EqualsUtil.integer(request, "projectTypeId", "项目类型id", true);

			int res = service.deleteProjectType(projectTypeId);
			if (res == 1) {
				return Rjson.success("删除成功");
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
