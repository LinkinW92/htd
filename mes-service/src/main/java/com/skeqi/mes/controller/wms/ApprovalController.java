package com.skeqi.mes.controller.wms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.service.wms.ApprovalDetailsService;
import com.skeqi.mes.service.wms.ApprovalService;
import com.skeqi.mes.service.wms.InTaskqueueService;
import com.skeqi.mes.service.wms.MaterialNumberService;
import com.skeqi.mes.service.wms.OutTaskqueueService;
import com.skeqi.mes.service.wms.StorageDetailService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 审批记录
 *
 * @author yinp
 * @date 2020年3月9日
 *
 */
@RestController
@RequestMapping(value = "wms/approval", produces = MediaType.APPLICATION_JSON)
@Api(value = "审批记录", description = "审批记录", produces = MediaType.APPLICATION_JSON)
public class ApprovalController {

	@Autowired
	ApprovalService service;

	@Autowired
	ApprovalDetailsService dService;

	@Autowired
	InTaskqueueService iService;

	@Autowired
	OutTaskqueueService oService;

	@Autowired
	StorageDetailService sService;

	@Autowired
	MaterialNumberService mService;

	/**
	 * 审批驳回
	 *
	 * @param request
	 */
	@ApiOperation(value = "审批驳回", notes = "审批驳回")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "approvalReject", method = RequestMethod.POST)
	public Rjson approvalReject(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			boolean res = service.approvalReject(userId, listNo);
			if (!res) {
				throw new Exception("操作失败，未知异常");
			}

			return Rjson.success("驳回成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 审批同意
	 *
	 * @param request
	 */
	@ApiOperation(value = "审批同意", notes = "审批同意")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "approvalAgree", method = RequestMethod.POST)
	public Rjson approvalAgree(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			boolean res = service.approvalAgree(userId, listNo);
			if (!res) {
				throw new Exception("操作失败，未知异常");
			}
			return Rjson.success("审批成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批记录集合
	 *
	 * @param request
	 */
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("pageNumber", pageNumber);

			PageInfo<CWmsApprovalT> pageInfo = service.findApprovalList(json);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过id查询审批记录
	 *
	 * @param request
	 */
	@ApiOperation(value = "通过id查询审批记录", notes = "查询审批记录集合")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "approvalId", value = "审批记录id", required = true, paramType = "query") })
	@RequestMapping(value = "findById", method = RequestMethod.POST)
	public Rjson findById(HttpServletRequest request) {
		try {
			Integer approvalId = EqualsUtil.integer(request, "approvalId", "审批记录id", true);

			CWmsApprovalT dx = service.findApprovalById(approvalId);

			return Rjson.success("查询成功", dx);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存详情R集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库存详情集合", notes = "查询库存详情集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findStorageDetail", method = RequestMethod.POST)
	public Rjson findStorageDetail(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			PageHelper.startPage(pageNumber, 8);
			List<JSONObject> list = service.findRStorageDetailSum(listNo);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过单号查询盘点详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/findInventoryDetailByListNo")
	public Rjson findInventoryDetailByListNo(HttpServletRequest request) {
		try {

			int pageNumber = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);
			String listNo = EqualsUtil.string(request, "listNo", "单哈", true);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.findInventoryDetailByListNo(listNo);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}
}
