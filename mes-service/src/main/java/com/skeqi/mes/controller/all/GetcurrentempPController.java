package com.skeqi.mes.controller.all;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.UpdatecurrentempPT;
import com.skeqi.mes.pojo.api.UpdatecurrentstepPT;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.GetcurrentempPService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * @date 2020年1月11日
 * @author yinp
 * 装配
 *
 */
@RestController
@RequestMapping("api")
public class GetcurrentempPController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	GetcurrentempPService service;

	/**
	 * 入参
	 */
	String snBarcode;
	String stationEmpName;
	String lineEmpName;
	/**
	 * 出参
	 */
	String eName;
	Integer rEmp;

	/**
	 * 临时变量
	 */
	// 记录条数
	Integer tempSteprecordCount;
	String tmepEmpName;

	public GetcurrentempPController() {
		eName = "";
		rEmp = 0;
		// TODO Auto-generated constructor stub
	}

	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();


	/**
	 * 入参
	 */
	String serialnumber;
	String station;
	String line;
	/**
	 * 出参
	 */
	Integer step;
	Integer r;

	/**
	 * 临时变量
	 */
	Integer tempStep;// 步序
	Integer maxid;// 最大id

	/**
	 * 入参
	 */
	String stationName;
	String empname;




	@RequestMapping(value="updatecurrentstepP",method = RequestMethod.POST)
	public void updatecurrentstepP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("updatecurrentstepP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);


		try {
			serialnumber = json.get("serialnumber").toString();
			station = json.get("station").toString();
			line = json.get("line").toString();
			step = Integer.parseInt(json.get("step").toString());
		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main3();
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {
			joz.put("r", r);
			joz.put("code", r);
			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	public void main3() {
		UpdatecurrentstepPT pt = service.find1s(serialnumber, station, line);
		tempSteprecordCount = Integer.parseInt(pt.getTempSteprecordCount());
		if (tempSteprecordCount.equals("0")) {
			// 没有步序记录
			r = 53;
			jo.put("msg", "没有步序记录");
		} else if (tempSteprecordCount.equals("1")) {
			// 一条步序记录
			service.update1s(step.toString(), serialnumber, station, line);
			r = 0;
			jo.put("msg", "一条步序记录");
		} else {
			// 多条步序记录
			r = 52;
			jo.put("msg", "多条步序记录");
		}
	}

	@RequestMapping(value = "updatecurrentempP", method = RequestMethod.POST)
	public void updatecurrentempP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("updatecurrentempP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			serialnumber = json.get("serialnumber").toString();
			stationName = json.get("stationName").toString();
			line = json.get("line").toString();
			empname = json.get("empname").toString();
		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main2();
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {
			joz.put("r", r);
			joz.put("code", r);
			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	public void main2() {
		UpdatecurrentempPT pt = service.find100(serialnumber, stationName, line);
		tempSteprecordCount = Integer.parseInt(pt.getTempSteprecordCount());
		if (tempSteprecordCount.equals("0")) {
			// 没有步序记录
			r = 53;
			jo.put("msg", "没有步序记录");
		} else if (tempSteprecordCount.equals("1")) {
			// 一条步序记录
			service.update1(empname, serialnumber, stationName, line);
			r = 0;
			jo.put("msg", "一条步序记录");
		} else {
			// 多条步序记录
			r = 52;
			jo.put("msg", "多条步序记录");
		}
	}



	// 保存返回数据
	@RequestMapping(value="getcurrentstepP",method = RequestMethod.POST)
	public void getcurrentstepP(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("getcurrentstepP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		/**
		 * 判断参数
		 */
		try {
			serialnumber = json.get("serialnumber").toString();
			station = json.get("station").toString();
			line = json.get("line").toString();
		} catch (NullPointerException e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		/**
		 * 执行主体
		 */
		try {
			// 调用方法主体
			mains();
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {
			joz.put("step", step);
			joz.put("r", r);

			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	public void mains() {
		tempSteprecordCount = Integer.parseInt(service.find10(serialnumber, station, line).getTempSteprecordCount());

		if (tempSteprecordCount == 0) {
			// 没有步序记录
			step = 0;
			r = 53;
			jo.put("msg", "没有步序记录");
		} else if (tempSteprecordCount == 1) {
			// 一条步序记录
			step = Integer.parseInt(service.find20(serialnumber, station, line).getStep());
			r = 0;
			jo.put("msg", "一条步序记录");
		} else {
			// 多条步序记录
			step = 0;
			r = 52;
			jo.put("msg", "多条步序记录");
		}

	}

	//
	//
	//
	//

	@RequestMapping(value = "getcurrentempP", method = RequestMethod.POST)
	public void getcurrentempP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");

		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			joz.put("code", "201");
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("getcurrentempP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			snBarcode = json.get("snBarcode").toString();
			stationEmpName = json.get("stationEmpName").toString();
			lineEmpName = json.get("lineEmpName").toString();
		} catch (NullPointerException e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");
			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg")+"生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main();
			jo.put("isSuccess", true);
			joz.put("code", "0");
			jo.put("errMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", true);
			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
		} finally {

			joz.put("eName", eName);
			joz.put("rEmp", rEmp);

			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	public void main() {
		tempSteprecordCount = Integer
				.parseInt(service.find1(snBarcode, stationEmpName, lineEmpName).getTempSteprecordCount());

		if (tempSteprecordCount == 0) {
			rEmp = 53;// 没有步序记录
			jo.put("msg", "没有步序记录");
			return;
		}

		if (tempSteprecordCount == 1) {
			// 一条步序记录
			tmepEmpName = service.find2(snBarcode, stationEmpName, lineEmpName).getTmepEmpName();
			eName = tmepEmpName;
			jo.put("msg", "一条步序记录");
			return;
		}

		if (tempSteprecordCount > 1) {
			rEmp = 52;
			jo.put("msg", "多条步序记录");
			return;
		}
	}

}
