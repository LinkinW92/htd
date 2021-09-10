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
 * 日产量统计
 *
 * @ClassName: CMesDayOutPutController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "日产量统计", description = "日产量统计", produces = MediaType.APPLICATION_JSON)
public class CMesDayOutPutController {

	@Autowired
	private CMesSystemService cSystemService;

	@Autowired
	private GetSomeYieldService gsys;

	// 日产量统计
	@RequestMapping(value = "/day/getMonthOutPut", method = RequestMethod.POST)
	@ApiOperation(value = "得到日产量", notes = "可根据条件查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "time", value = "日期", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")
			 })
	@ResponseBody
	public JSONObject findList(String time, Integer lineId) throws ServicesException {
		String str = time;// 获取时间和月份
																// //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0, 4));// 截取年份
		Integer getMonth = Integer.parseInt(str.substring(5, 7));// 截取月份

		JSONObject jo = new JSONObject();
		List<Integer> dayLearies = new ArrayList<>();// 日报废量
		List<Integer> dayYield = new ArrayList<>();// 日产量
		Integer getValue = 1;// 1为报废品数量 //null 为 日产量
		List<Integer> dayNumber = new ArrayList<>();
		// yield.dayNumber
		String getYearMonthDay = str.substring(0, 10);// 获取年份和月份
		List<Integer> getHourQualified = new ArrayList<>();
		for (int i = 0; i < 24; i++) {

			String getStartDate = getYearMonthDay + " " + i + ":00:00";
			String getEndDate = getYearMonthDay + " " + i + ":59:59";
			int index = gsys.getHourQualified2(getStartDate, getEndDate, null, lineId);
			getHourQualified.add(index);

		}
		jo.put("getDayMonthDay", getHourQualified);

		String getStartData = getYearMonthDay + " 0:00:00";// 开始时间
		String getEndData = getYearMonthDay + " 23:59:59";// 结束时间
		Integer productNumber = gsys.getHourQualified(getStartData, getEndData, null, lineId);// 获取当天的产品的数量
		Integer scrapRateNumber = gsys.getHourQualified(getStartData, getEndData, 1, lineId);// 获取当天的报废品数量

		BigDecimal scrapRate = null;

		// 计算 比率
		if (productNumber != 0) {
			BigDecimal number1 = new BigDecimal(scrapRateNumber);// 报废品数量
			scrapRate = number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN)
					.multiply(new BigDecimal(100));

		} else {
			scrapRate = new BigDecimal(0);
		}
		jo.put("scrapRate", scrapRate);

		if (getMonth == 12) {
			for (int i = 1; i <= 31; i++) {
				String getStartTime = null;
				String getEndTime = null;
				if (i != 31) {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + getMonth + "-" + (i + 1);

				} else {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = (getYear + 1) + "-" + 1 + "-" + 1;// 另一年的1月1日

				}
				Integer LearieskList = gsys.getEveryDayYeild(getStartTime, getEndTime, getValue, lineId);// 报废品
				Integer YieldList = gsys.getEveryDayYeild(getStartTime, getEndTime, null, lineId);// 产品
				dayLearies.add(LearieskList);//// 报废品数量
				dayYield.add(YieldList);// 产品数量
				dayNumber.add(i);// 每月天数

			}

		} else if (getYear % 4 == 0 && getMonth == 2) {// 闰年二月
			for (int i = 1; i <= 29; i++) {
				String getStartTime = null;
				String getEndTime = null;
				if (i != 29) {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + getMonth + "-" + (i + 1);

				} else {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1;// 3月1日

				}
				Integer LearieskList = gsys.getEveryDayYeild(getStartTime, getEndTime, getValue, lineId);// 报废品
				Integer YieldList = gsys.getEveryDayYeild(getStartTime, getEndTime, null, lineId);// 产品
				dayLearies.add(LearieskList);//// 报废品数量
				dayYield.add(YieldList);// 产品数量
				dayNumber.add(i);// 每月天数
			}

		} else if (getYear % 4 != 0 && getMonth == 2) {// 平年二月

			for (int i = 1; i <= 28; i++) {
				String getStartTime = null;
				String getEndTime = null;
				if (i != 28) {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + getMonth + "-" + (i + 1);

				} else {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1;// 3月1日

				}
				Integer LearieskList = gsys.getEveryDayYeild(getStartTime, getEndTime, getValue, lineId);// 报废品
				Integer YieldList = gsys.getEveryDayYeild(getStartTime, getEndTime, null, lineId);// 产品
				dayLearies.add(LearieskList);//// 报废品数量
				dayYield.add(YieldList);// 产品数量
				dayNumber.add(i);// 每月天数
			}

		} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {
			for (int i = 1; i <= 30; i++) {
				String getStartTime = null;
				String getEndTime = null;
				if (i != 30) {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + getMonth + "-" + (i + 1);

				} else {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1;

				}
				Integer LearieskList = gsys.getEveryDayYeild(getStartTime, getEndTime, getValue, lineId);// 报废品
				Integer YieldList = gsys.getEveryDayYeild(getStartTime, getEndTime, null, lineId);// 产品
				dayLearies.add(LearieskList);//// 报废品数量
				dayYield.add(YieldList);// 产品数量
				dayNumber.add(i);// 每月天数
			}

		} else {
			for (int i = 1; i <= 31; i++) {
				String getStartTime = null;
				String getEndTime = null;
				if (i != 31) {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + getMonth + "-" + (i + 1);

				} else {
					getStartTime = getYear + "-" + getMonth + "-" + i;
					getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1;

				}
				Integer LearieskList = gsys.getEveryDayYeild(getStartTime, getEndTime, getValue, lineId);// 报废品
				Integer YieldList = gsys.getEveryDayYeild(getStartTime, getEndTime, null, lineId);// 产品
				dayLearies.add(LearieskList);//// 报废品数量
				dayYield.add(YieldList);// 产品数量
				dayNumber.add(i);// 每月天数
			}

		}

		jo.put("dayYield", dayYield);// 每天的产量
		jo.put("dayLearies", dayLearies);// 每天的报废品数量
		jo.put("dayNumber", dayNumber);// 每月的天数
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/day/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	// 系统数据
	@RequestMapping(value = "/day/findByAll", method = RequestMethod.POST)
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

	@RequestMapping(value = "/day/setMenuData", method = RequestMethod.POST)
	@ApiOperation(value = "修改菜单时间", notes = "修改菜单时间")
	@ResponseBody
	public JSONObject setMenuData(HttpServletRequest request) {
		//TODO
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		String menuData = request.getParameter("menuData");
//		String menuModuleData = request.getParameter("menuModuleData");
//
//		subject.getSession().setAttribute("menuData", menuData);
//		subject.getSession().setAttribute("menuModuleData", menuModuleData);
//		if (!subject.isAuthenticated()) {
//			jo.put("value", true);
//		} else {
//			jo.put("value", false);
//		}
//		Version version = new Version();
//		jo.put("version", version.getVersion());
		return null;
	}

}
