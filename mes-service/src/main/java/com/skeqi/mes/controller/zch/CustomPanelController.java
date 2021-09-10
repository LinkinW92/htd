package com.skeqi.mes.controller.zch;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.zch.CustomPanelService;

import io.swagger.annotations.Api;

/**
 * 定制面板管理
 * @author Zhangch
 *
 */
@Controller
@RequestMapping(value = "qhapi", produces = MediaType.APPLICATION_JSON)
@Api(value = "定制看板", description = "定制看板", produces = MediaType.APPLICATION_JSON)
public class CustomPanelController {

	@Autowired
	CustomPanelService service;

	@Transactional
    @RequestMapping(value = "snDetailedList", method = RequestMethod.POST)
//    @OptionalLog(module = "生产", module2 = "生产模拟", method = "扫描总成")
    public synchronized void snDetailedList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

        response.setContentType("application/json");
        JSONObject jo = new JSONObject();

        if (str == null || str == "") {
            jo.put("code", "201");
            jo.put("errMsg", "发送数据为空");
            response.getWriter().append(jo.toJSONString());
            return;
        }

        try {
            // 将接受到的字符串转换为json数组
            JSONObject json = JSONObject.parseObject(str);
            jo = service.snDetailedList(json);

        } catch (Exception e) {
            e.printStackTrace();

            jo.put("code", "999");
            jo.put("errMsg", "出现异常");

        } finally {

            if (!(jo.getIntValue("code") == 200)) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            response.getWriter().append(jo.toJSONString());
        }
	}

	@Transactional
	@RequestMapping(value = "assemblyMaterial", method = RequestMethod.POST)
	public synchronized void assemblyMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		if (str == null || str == "") {
			jo.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn"))
					|| StringUtils.isEmpty(json.get("line")) || StringUtils.isEmpty(json.get("step"))
					|| StringUtils.isEmpty(json.get("station"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.assemblyMaterial(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	@RequestMapping(value = "splitSN", method = RequestMethod.POST)
	public synchronized void splitSN(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		if (str == null || str == "") {
			jo.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn")) || StringUtils.isEmpty(json.get("lineId"))
					|| StringUtils.isEmpty(json.get("quantity"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.splitSN(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	@RequestMapping(value = "findLoadingMaterialList", method = RequestMethod.POST)
	public synchronized void findLoadingMaterialList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		if (str == null || str == "") {
			jo.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("workorderId")) || StringUtils.isEmpty(json.get("lineId"))
					|| StringUtils.isEmpty(json.get("stationId"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.findLoadingMaterialList(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	@RequestMapping(value = "addLoadingMaterial", method = RequestMethod.POST)
	public synchronized void addLoadingMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		if (str == null || str == "") {
			jo.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("workorderId")) || StringUtils.isEmpty(json.get("lineId"))
					|| StringUtils.isEmpty(json.get("stationId")) || StringUtils.isEmpty(json.get("materialNo"))
					|| StringUtils.isEmpty(json.get("batchCode")) || StringUtils.isEmpty(json.get("quantity"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.addLoadingMaterial(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 不合格代码配置列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findNcCodeConfigList", method = RequestMethod.POST)
	public synchronized void findNcCodeConfigList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			//业务代码
			jo = service.findNcCodeConfigList(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 不合格代码记录列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findNcCodeRecordList", method = RequestMethod.POST)
	public synchronized void findNcCodeRecordList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.findNcCodeRecordList(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 不合格代码记录列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "addNcCodeRecord", method = RequestMethod.POST)
	public synchronized void addNcCodeRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn")) || StringUtils.isEmpty(json.get("nc_code"))
					|| StringUtils.isEmpty(json.get("emp"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.addNcCodeRecord(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 喇叭报警
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "hornAlarm", method = RequestMethod.POST)
	public synchronized void hornAlarm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("line")) || StringUtils.isEmpty(json.get("station"))
					|| StringUtils.isEmpty(json.get("message"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.hornAlarm(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 获取上料条码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "getLoadingCode", method = RequestMethod.POST)
	public synchronized void getLoadingCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn")) || StringUtils.isEmpty(json.get("lineId"))
					|| StringUtils.isEmpty(json.get("stationId")) || StringUtils.isEmpty(json.get("step"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.getLoadingCode(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 获取数据收集记录列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findDataCollectionRecordList", method = RequestMethod.POST)
	public synchronized void findDataCollectionRecordList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn")) || StringUtils.isEmpty(json.get("stationId"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.findDataCollectionRecordList(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 获取数据收集记录列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "findDataCollectionParams", method = RequestMethod.POST)
	public synchronized void findDataCollectionParams(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("groupNumber"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.findDataCollectionParams(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	/**
	 * 新增数据收束
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Transactional
	@RequestMapping(value = "addDataCollection", method = RequestMethod.POST)
	public synchronized void addDataCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");
		JSONObject jo = new JSONObject();

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			if(StringUtils.isEmpty(json.get("sn")) || StringUtils.isEmpty(json.get("stationId"))
					|| StringUtils.isEmpty(json.get("paramsData")) || StringUtils.isEmpty(json.get("emp"))
					|| StringUtils.isEmpty(json.get("groupNumber"))) {
				jo.put("code", "202");
				jo.put("errMsg", "参数缺失");
				response.getWriter().append(jo.toJSONString());
				return;
			}

			//业务代码
			jo = service.addDataCollection(json);

		} catch (Exception e) {
			e.printStackTrace();

			jo.put("code", "999");
			jo.put("errMsg", "出现异常");

		} finally {

			if (!(jo.getIntValue("code") == 200)) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}

			response.getWriter().append(jo.toJSONString());
		}
	}
}
