package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.wms.ApprovalResultService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 审批结果
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/approvalResult", produces = MediaType.APPLICATION_JSON)
@Api(value = "审批结果", description = "审批结果", produces = MediaType.APPLICATION_JSON)
public class ApprovalResultController {

	@Autowired
	ApprovalResultService service;

	/**
	 * 查询审批单据
	 *
	 */
	@ApiOperation(value = "查询审批记录集合", notes = "查询审批记录集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query")})
	@RequestMapping(value = "findApprovalList", method = RequestMethod.POST)
	public Rjson findApprovalList(HttpServletRequest request) {
		try {

			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer type = EqualsUtil.integer(request, "type", "审批类型", false);

			PageHelper.startPage(pageNumber, 8);
			List<CWmsApprovalT> lineList = service.findApprovalList(null);
			PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(lineList, 5);

			return Rjson.success("查询成功",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询查询库存详情
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询查询库存详情", notes = "查询查询库存详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query")})
	@RequestMapping(value = "findStorageDetailList", method = RequestMethod.POST)
	public Rjson findStorageDetailList(HttpServletRequest request) {
		try {

			Integer pageNumber = EqualsUtil.pageNumber(request);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);


			JSONObject json = new JSONObject();
			json.put("listNo",listNo);


			PageHelper.startPage(pageNumber, 8);
			List<CWmsStorageDetailT> lineList = service.findStorageDetailList(json);

			if(lineList==null||lineList.size()==0) {
				PageHelper.startPage(pageNumber, 8);
				lineList = service.findPStorageDetailList(json);
			}
			PageInfo<CWmsStorageDetailT> pageInfo = new PageInfo<CWmsStorageDetailT>(lineList, 5);

			return Rjson.success("查询成功",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}



}
