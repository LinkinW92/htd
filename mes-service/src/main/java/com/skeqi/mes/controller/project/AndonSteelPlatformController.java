package com.skeqi.mes.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.project.AndonSteelPlatform;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.service.project.AndonSteelPlatformService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 钢平台数据
 */
@RestController
@RequestMapping("/api/andon/andonSteelPlatform")
public class AndonSteelPlatformController {

	@Autowired
	AndonSteelPlatformService service;

	/**
	 * 实时数据
	 * @return
	 */
	@RequestMapping("/realTimeData")
	public Rjson realTimeData(HttpServletRequest request) {
		try {
			List<AndonSteelPlatform> list = service.findRAndonSteelPlatform();
			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 历史数据
	 * @return
	 */
	@RequestMapping("/historyData")
	public Rjson historyData(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn","总成号",false);
			String startTime = EqualsUtil.string(request, "startTime","开始时间",false);
			String endTime = EqualsUtil.string(request, "endTime","结束时间",false);
			String productName = EqualsUtil.string(request, "productName","产品名称",false);
			String productNo = EqualsUtil.string(request, "productNo","产品编码",false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			JSONObject json = new JSONObject();
			json.put("sn", sn);
			json.put("startTime", startTime);
			json.put("endTime", endTime);
			json.put("productName", productName);
			json.put("productNo", productNo);

			PageHelper.startPage(pageNumber, 10);
			List<AndonSteelPlatform> list = service.findPAndonSteelPlatform(json);
			PageInfo<AndonSteelPlatform> pageInfo = new PageInfo<AndonSteelPlatform>(list, 5);


			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 扫码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/scanCode",method = RequestMethod.POST)
	@Transactional
	public Rjson scanCode(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn","总成号",true);
			service.scanCode(sn);
			return new Rjson().success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
