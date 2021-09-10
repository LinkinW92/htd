package com.skeqi.mes.controller.qh;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.all.CMesLineTService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "看板", description = "看板", produces = MediaType.APPLICATION_JSON)
public class CMesBoardController {

	@Autowired
	private CMesLineTService cMesLineTService;

	@RequestMapping(value = "/findBoard", method = RequestMethod.POST)
	@ApiOperation(value = "看板列表", notes = "看板列表")
	public JSONObject findAll(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "100") Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		JSONObject json = new JSONObject();
		List<Map<String, Object>> list = cMesLineTService.findAll();// 查询看板数据
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		try {
			json.put("code", 0);
			json.put("msg", pageInfo);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}

		return json;
	}

	@RequestMapping(value = "/delBoard", method = RequestMethod.POST)
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query"), })
	public JSONObject delBoard(Integer id) {
		JSONObject json = new JSONObject();
		try {
			cMesLineTService.delBoard(id);// 查询看板数据
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "/addBoard", method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "添加")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardName", value = "看板名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "boardType", value = "看板类型", required = false, paramType = "query") ,
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query")})
	public JSONObject addBoard(String boardName, String boardType,String lineName) {
		JSONObject json = new JSONObject();
		System.err.println("lineName==="+lineName);
		try {
			cMesLineTService.addBoard(boardName, boardType,lineName);// 查询看板数据
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}


	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	@ApiOperation(value = "修改", notes = "修改")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardName", value = "看板名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "boardType", value = "看板类型", required = false, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query") ,
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query")})
	public JSONObject updateBoard(String boardName, String boardType, Integer id,String lineName) {
		JSONObject json = new JSONObject();
		try {
			cMesLineTService.updateBoard(boardName, boardType, lineName,id);// 查询看板数据
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}
