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
import com.skeqi.mes.pojo.wms.CWmsMaterialT;
import com.skeqi.mes.service.wms.WmsMaterialService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物料管理
 *
 * @author yinp
 * @date 2020年3月18日
 *
 */
@RestController
@RequestMapping(value = "wms/material",produces = MediaType.APPLICATION_JSON )
@Api(value = "物料管理", description = "物料管理", produces = MediaType.APPLICATION_JSON)
public class WmsMaterialController {

	@Autowired
	WmsMaterialService service;

	/**
	 * 查询所有ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ApiOperation(value = "查询所有ID、NAME", notes = "查询所有ID、NAME")
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);*/


		try {
			List<CWmsMaterialT> list = service.findMaterialAll();

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", "查询失败，数据异常！");
			data.put("code", false);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

}
