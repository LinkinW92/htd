package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.service.wms.ApprovalDetailsService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 审批记录详情
 * @author yinp
 * @date 2020年3月16日
 *
 */
@RestController
@RequestMapping(value = "wms/approvalDetail", produces = MediaType.APPLICATION_JSON)
@Api(value = "审批记录详情", description = "审批记录详情", produces = MediaType.APPLICATION_JSON)
public class ApprovalDetailsConler {

	@Autowired
	ApprovalDetailsService service;

	/**
	 * 查询审批记录集合
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询审批记录集合", notes = "查询审批记录集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "approvalId", value = "审批记录主表id", required = true, paramType = "query")})
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public void findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {

			CWmsApprovalDetailsT dx = new CWmsApprovalDetailsT();

			if(request.getParameter("approvalId")!=null
					&& !request.getParameter("approvalId").equals("")
					&& !request.getParameter("approvalId").equals("0")){
				try {
					dx.setApprovalId(Integer.parseInt(request.getParameter("approvalId")));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'单号'参数类型有误");
				}
			}

			List<CWmsApprovalDetailsT> list = service.findApprovalDetails(dx);

			data.put("msg","查询成功！");
			data.put("code",true);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg",e.getMessage());
			data.put("code",false);
			result.put("result", data);
		}finally {
			response.getWriter().append(result.toJSONString());
		}
	}

}
