package com.skeqi.mes.controller.qh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.Version;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 设备使用率
 *
 * @ClassName: CMesPlantFactorController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备使用率", description = "设备使用率", produces = MediaType.APPLICATION_JSON)
public class CMesPlantFactorController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	/**
	 * 设备使用率
	 */

	@RequestMapping(value = "/plantFactor/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到设备使用率", notes = "根据多条件得到设备使用率")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "productionValue", value = "理论生产量", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject get(Integer lineId, Integer productionValue, String time) throws ServicesException {

		JSONObject jo = new JSONObject();
		String str = time;// 获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0, 4));// 截取年份
		Integer getMonth = Integer.parseInt(str.substring(5, 7));// 截取月份
		List<Integer> monthList = new ArrayList<>();// 每月有多少天

		// 设备利用率=每天实际产量/ 每天理论产量×100%

		List<BigDecimal> getEquipmentUsageRate = new ArrayList<>();// 合格率列表
		if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
			for (int i = 1; i <= 29; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				Integer productNumber = gsys.getHourQualified(getStartDate, getEndDate, null, lineId);// 获取当天的产品的数量
				BigDecimal getEquipmentValue = null;
				BigDecimal num = new BigDecimal(productNumber);// 当天产量
				// 当天产量/当天理论产量 *100% 获取设备利用率
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN)
						.multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}
		} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
			for (int i = 1; i <= 28; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				Integer productNumber = gsys.getHourQualified(getStartDate, getEndDate, null, lineId);// 获取当天的产品的数量
				BigDecimal getEquipmentValue = null;
				BigDecimal num = new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN)
						.multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}

		} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
			for (int i = 1; i <= 30; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				Integer productNumber = gsys.getHourQualified(getStartDate, getEndDate, null, lineId);// 获取当天的产品的数量
				BigDecimal getEquipmentValue = null;
				BigDecimal num = new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN)
						.multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}

		} else {// 31天的月份
			for (int i = 1; i <= 31; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				Integer productNumber = gsys.getHourQualified(getStartDate, getEndDate, null, lineId);// 获取当天的产品的数量
				BigDecimal getEquipmentValue = null;
				BigDecimal num = new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN)
						.multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}
		}
		jo.put("getEquipmentUsageRate", getEquipmentUsageRate);
		jo.put("monthList", monthList);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/plantFactor/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/plantFactor/sessionConversation", method = RequestMethod.POST)
	@ApiOperation("版本时间控制")
	public JSONObject getSessionConversation() {
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		if (!subject.isAuthenticated()) {
//			jo.put("value", true);
//		} else {
//			jo.put("menuData", subject.getSession().getAttribute("menuData"));
//			jo.put("menuModuleData", subject.getSession().getAttribute("menuModuleData"));
//			jo.put("value", false);
//		}
//		Version version = new Version();
//		jo.put("version", version.getVersion());
		return null;
	}

	// 系统数据
	@RequestMapping(value = "/plantFactor/findByAll", method = RequestMethod.POST)
	@ApiOperation(value = "系统数据", notes = "系统数据")
	@ResponseBody
	public JSONObject findByAll(HttpServletRequest request) throws ServicesException {
		List<CMesSystem> cSystems = cSystemService.findByAll(null);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("cSystems", cSystems);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}
