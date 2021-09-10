package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.wms.StockAlarmService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 库存报警
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/stockAlarm", produces = MediaType.APPLICATION_JSON)
@Api(value = "库存报警", description = "库存报警", produces = MediaType.APPLICATION_JSON)
public class StockAlarmController {

	@Autowired
	StockAlarmService service;

	/**
	 * 查询
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询", notes = "查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public void findList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			Integer pageNumber = Integer.parseInt( request.getParameter("pageNumber"));
			if ( request.getParameter("pageNumber") == null ||  request.getParameter("pageNumber").equals("") || Integer.parseInt( request.getParameter("pageNumber")) == 0) {
				pageNumber = 1;
			}
			PageHelper.startPage(pageNumber, 8);
			List<Map<String,Object>> lineList = service.findList(null);
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(lineList, 5);

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", false);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

}
