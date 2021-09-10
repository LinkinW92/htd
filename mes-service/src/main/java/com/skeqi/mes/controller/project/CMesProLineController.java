package com.skeqi.mes.controller.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.project.CMesProLIneService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "安灯产线管理", description = "安灯产线管理", produces = MediaType.APPLICATION_JSON)
public class CMesProLineController {

	@Autowired
	CMesProLIneService service;

	@RequestMapping(value = "findProLine", method = RequestMethod.POST)
	@ApiOperation(value = "产线列表", notes = "产线列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"), })
	public JSONObject findAllLine(HttpServletRequest request, String name, Integer pages) {
		JSONObject json = new JSONObject();
		DateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			List<CMesLineT> findAllLine = null;
			if (pages == null || pages == 0) {

				findAllLine = service.findAllLine(name);
			} else {
				PageHelper.startPage(pages, 10);
				findAllLine = service.findAllLine(name);
				PageInfo<CMesLineT> pageinfo = new PageInfo<CMesLineT>(findAllLine, 5);
				json.put("pageNum", pageinfo.getTotal());
				findAllLine = pageinfo.getList();
			}

			json.put("code", 0);
			json.put("info", findAllLine);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value = "addLine", method = RequestMethod.POST)
	@ApiOperation(value = "添加产线", notes = "添加产线")
	@ApiImplicitParams({ @ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dsc", value = "产线描述", required = true, paramType = "query"),
			@ApiImplicitParam(name = "yieldNumber", value = "理论产量", required = true, paramType = "query"),
			@ApiImplicitParam(name = "region", value = "所属区域", required = true, paramType = "query"),
			@ApiImplicitParam(name = "countType", value = "计数方式", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productmark", value = "产品标记", required = true, paramType = "query"),
			@ApiImplicitParam(name = "paibanStatus", value = "是否安灯", required = true, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "andengStatus", value = "是否排版", required = true, paramType = "query", dataType = "Integer"), })
	public JSONObject addLine(HttpServletRequest request, String lineName, String dsc, Integer yieldNumber, Integer region, Integer countType,
			String productmark, Integer paibanStatus, Integer andengStatus) {
		JSONObject json = new JSONObject();
		Integer a = service.findByName(lineName);
		if (a != 0) {
			json.put("code", 1);
			json.put("msg", "当前产线已存在");
		} else {
			try {
				CMesLineT line = new CMesLineT();
				line.setName(lineName);
				line.setDsc(dsc);
				if (yieldNumber != null) {
					line.setYieldNumber(yieldNumber);
				} else {
					line.setYieldNumber(0);
				}
				line.setPaibanStatus(paibanStatus);
				line.setAndengStatus(andengStatus);
				line.setRegion(region);
				line.setCountType(countType);
				line.setProductMark(productmark);
				service.addLine(line);
				json.put("code", 0);
				json.put("msg", "添加成功");
			} catch (ServicesException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
		}

		return json;
	}

	@RequestMapping(value = "updateLine", method = RequestMethod.POST)
	@ApiOperation(value = "修改产线", notes = "修改产线")
	@ApiImplicitParams({ @ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dsc", value = "产线描述", required = false, paramType = "query"),
			@ApiImplicitParam(name = "yieldNumber", value = "理论产量", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "countType", value = "计数方式", required = true, paramType = "query"),
			@ApiImplicitParam(name = "region", value = "所属区域", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productmark", value = "产品标记", required = true, paramType = "query"),
			@ApiImplicitParam(name = "paibanStatus", value = "是否安灯", required = true, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "andengStatus", value = "是否排版", required = true, paramType = "query", dataType = "int"), })
	public JSONObject updateLine(HttpServletRequest request, String lineName, String dsc, String yieldNumber, Integer id, Integer region,
			Integer countType, String productmark, Integer paibanStatus, Integer andengStatus) {
		JSONObject json = new JSONObject();
		try {
			CMesLineT line = new CMesLineT();
			line.setName(lineName);
			line.setDsc(dsc);
			line.setYieldNumber(Integer.parseInt(yieldNumber));
			line.setId(id);
			line.setRegion(region);
			line.setCountType(countType);
			line.setProductMark(productmark);
			line.setPaibanStatus(paibanStatus);
			line.setAndengStatus(andengStatus);
			service.updateLine(line);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "deleteLine", method = RequestMethod.POST)
	@ApiOperation(value = "删除产线", notes = "删除产线")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject deleteLine(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delLine(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findproTypeByLine", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品编码查询产线", notes = "根据产品编码查询产线")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productionId", value = "产品Id", required = false, paramType = "query"), })
	public JSONObject findproTypeByLine(Integer productionId) {
		JSONObject json = new JSONObject();
		String proType = service.findPidByType(productionId);
		List<CMesLineT> findProVrByLine = service.findproTypeByLine(proType);
		try {
			json.put("code", 0);
			json.put("msg", findProVrByLine);
			json.put("proType", proType);
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
