package com.skeqi.mes.controller.zch;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.wf.timer.instantiationTimer.MarginLibraryTimer;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.service.zch.LineSideLibraryApiService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "qhapi", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库客户端api", description = "线边库客户端api", produces = MediaType.APPLICATION_JSON)
public class LineSideLibraryApiController {

	@Autowired
	LineSideLibraryApiService lineSideLibraryApiService;
	@Resource
	MarginLibraryTimer libraryTimer;

	/**
	 * 收料查询接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "materialReceivingQuery", method = RequestMethod.POST)
	@ApiOperation(value = "收料查询接口", notes = "收料查询接口")
	public synchronized void materialReceivingQuery(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("line"))
				|| StringUtils.isEmpty(map.get("station"))) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = lineSideLibraryApiService.materialReceivingQuery(map);

			if(!jo.getBooleanValue("isSuccess")) {
				jo.put("questList", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime,
					map.get("jsonStr").toString(), jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 收料确认接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "materialReceiving", method = RequestMethod.POST)
	@ApiOperation(value = "收料确认接口", notes = "收料确认接口")
	public synchronized void materialReceiving(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("emp"))
				|| StringUtils.isEmpty(map.get("billNo"))) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = lineSideLibraryApiService.materialReceiving(map);

			if(!jo.getBooleanValue("isSuccess")) {
				jo.put("rockNo", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime,
					map.get("jsonStr").toString(), jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 手动要料接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "manualRequest", method = RequestMethod.POST)
	@ApiOperation(value = "手动要料接口", notes = "手动要料接口")
	public synchronized void manualRequest(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("line"))
				|| StringUtils.isEmpty(map.get("station"))
				|| StringUtils.isEmpty(map.get("rockNo"))
				|| StringUtils.isEmpty(map.get("barcode"))
				|| StringUtils.isEmpty(map.get("emp"))
				|| StringUtils.isEmpty(map.get("quantity"))) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = lineSideLibraryApiService.manualRequest(map);

			if(jo.getBooleanValue("isSuccess")) {
				List<RLslMaterialRequestT> requestList = lineSideLibraryApiService.findLastRequest();
				libraryTimer.alarm(requestList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime,
					map.get("jsonStr").toString(), jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 取料位置提醒接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "reclaimAlarm", method = RequestMethod.POST)
	@ApiOperation(value = "取料位置提醒接口", notes = "取料位置提醒接口")
	public synchronized void reclaimAlarm(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("line"))
				|| StringUtils.isEmpty(map.get("station"))
				|| StringUtils.isEmpty(map.get("sn"))
				|| StringUtils.isEmpty(map.get("step")) ) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = lineSideLibraryApiService.reclaimAlarm(map);

			if(!jo.getBooleanValue("isSuccess")) {
				jo.put("ptlNo", "");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime,
					map.get("jsonStr").toString(), jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 物料报废接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "materialScrap", method = RequestMethod.POST)
//	@ApiOperation(value = "物料报废接口", notes = "物料报废接口")
	public synchronized void materialScrap(@RequestBody JSONObject json, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("code"))
				|| StringUtils.isEmpty(map.get("quantity"))
				) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = lineSideLibraryApiService.materialScrap(map);

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime,
					map.get("jsonStr").toString(), jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	private JSONObject errorMsg(JSONObject jo, String code, String msg) {
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", msg);
		return jo;
	}
}
