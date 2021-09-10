package com.skeqi.mes.controller.zch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.zch.ModuleApiService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

@RestController
@RequestMapping(value = "qhapi", produces = MediaType.APPLICATION_JSON)
public class ModuleApiController {

	@Autowired
	ModuleApiService moduleApiService;

	/**
	 * 心跳接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "heartbeat", method = RequestMethod.POST)
	public synchronized void heartbeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		// 保存返回数据
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", true);
		jo.put("code", "0");
		jo.put("errMsg", "成功");

		response.getWriter().append(jo.toJSONString());
	}

	/**
	 * 日志存储
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "writeLog", method = RequestMethod.POST)
	public synchronized void writeLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");

		Integer type = null;// 日志类型
		String lineName = null;// 产线名称
		String content = null;// 内容

		if (str == null || str == "") {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		JSONObject json = JSONObject.parseObject(str);
		Map<String, Object> map = new HashMap<>();

		type = json.getInteger("type");
		lineName = json.getString("lineName");
		content = json.getString("content");

		if (type == null || lineName.isEmpty() || content.isEmpty()) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}
		map.put("type", type);
		map.put("lineName", lineName);
		map.put("content", content);

		try {
			// 调用方法主体
			jo = moduleApiService.writeLog(map);

		} catch (Exception e) {
			e.printStackTrace();
			jo = errorMsg(jo, "999", "未知错误");
		} finally {
			ToolUtils.insertApiLogs(Thread.currentThread().getStackTrace()[1].getMethodName(), callTime, str, jo.toJSONString());

			if(!jo.getBooleanValue("isSuccess")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 日志获取
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findLog", method = RequestMethod.POST)
	public synchronized void findLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

//		Integer type = null;// 日志类型
//		String lineName = null;// 产线名称
//		String beginTime = null;// 开始时间
//		String endTime = null;// 结束时间

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("type")) || StringUtils.isEmpty(map.get("lineName"))) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.findLog(map);

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
	 * 数据收束
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "dataCollect", method = RequestMethod.POST)
	public synchronized void dataCollect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName")) 	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName"))	//工位名称
				|| StringUtils.isEmpty(map.get("productName"))	//产品名称
				|| StringUtils.isEmpty(map.get("sn"))	//总成
				|| StringUtils.isEmpty(map.get("status")) ) {	//状态 OK/NG
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.dataCollect(map);

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
	 * 进站
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "inStation", method = RequestMethod.POST)
	public synchronized void inStation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName")) 	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName"))	//工位名称
				|| StringUtils.isEmpty(map.get("type"))	//类型 1 电芯 2 模组
				|| StringUtils.isEmpty(map.get("sn"))	//总成
				|| StringUtils.isEmpty(map.get("emp"))) {	//员工号
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.inStation(map);

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
	 * 出站
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "outStation", method = RequestMethod.POST)
	public synchronized void outStation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName")) 	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName"))	//工位名称
				|| StringUtils.isEmpty(map.get("sn")) ) {	//总成
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.outStation(map);

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
	 * 生产信息接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "getProductionInformation", method = RequestMethod.POST)
	public synchronized void getProductionInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName")) 	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName"))	//工位名称
				) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.getProductionInformation(map);

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
	 * 登录接口
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public synchronized void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("emp")) 	// 员工号
				|| StringUtils.isEmpty(map.get("password"))	// 密码
				|| StringUtils.isEmpty(map.get("lineName"))	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName")) ) {	// 工位名称
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.login(map);

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
	 * 设备状态API
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "deviceState", method = RequestMethod.POST)
	public synchronized void deviceState(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("emp")) 	// 员工号
				|| StringUtils.isEmpty(map.get("state"))	// 状态
				|| StringUtils.isEmpty(map.get("lineName"))	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName")) ) {	// 工位名称
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.deviceState(map);

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
	 * 获取工位物料
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findStationMaterial", method = RequestMethod.POST)
	public synchronized void findStationMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName"))	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName")) // 工位名称
				|| StringUtils.isEmpty(map.get("sn"))) { //总成
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.findStationMaterial(map);

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
	 * 获取工位物料
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "unbundStationMaterial", method = RequestMethod.POST)
	public synchronized void unbundStationMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName"))	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName")) // 工位名称
				|| StringUtils.isEmpty(map.get("materialCode")) //
				|| StringUtils.isEmpty(map.get("sn"))) { //总成
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.unbundStationMaterial(map);

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
	 * 获取采集项目
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findDataCollectPara", method = RequestMethod.POST)
	public synchronized void findDataCollectPara(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callTime = DateUtil.getNowDate();
		// 保存返回数据
		JSONObject jo = new JSONObject();

		Map<String, Object> map = ToolUtils.getJson2Map(request, response);

		if (map == null) {
			jo = errorMsg(jo, "201", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		if (StringUtils.isEmpty(map.get("lineName"))	// 产线名称
				|| StringUtils.isEmpty(map.get("stationName")) // 工位名称
				) {
			jo = errorMsg(jo, "202", "参数缺失");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = moduleApiService.findDataCollectPara(map);

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
