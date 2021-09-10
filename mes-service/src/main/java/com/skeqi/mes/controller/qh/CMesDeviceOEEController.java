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
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
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
 * 设备OEE
 *
 * @ClassName: CMesDeviceOEEController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备OEE", description = "设备OEE", produces = MediaType.APPLICATION_JSON)
public class CMesDeviceOEEController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	@RequestMapping(value = "/deciceOEE/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到设备OEE", notes = "根据多条件得到设备OEE")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "productionValue", value = "理论生产量", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject get(Integer lineId, Integer productionValue, String time) throws ServicesException {

		JSONObject jo = new JSONObject();
		String str = time;// 获取时间和月份

		Integer getYear = Integer.parseInt(str.substring(0, 4));// 截取年份
		Integer getMonth = Integer.parseInt(str.substring(5, 7));// 截取月份
		Integer getDay = Integer.parseInt(str.substring(8, 10));// 截取天
		List<Integer> monthList = new ArrayList<>();// 每月有多少天

		// 设备综合效率 oee算法是 (每天实际工作时间/理论工作时间)*（每天生产的产量/实际产量）*（每天合格产量/实际产量）*100%
		List<BigDecimal> getOEEValueList = new ArrayList<>();// 合格率列表
		if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
			for (int i = 1; i <= 29; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				queryEquipmentOEEValue(getOEEValueList, getStartDate, getEndDate, lineId, productionValue, gsys, getDay,
						i, jo);
			}

		} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
			for (int i = 1; i <= 28; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				queryEquipmentOEEValue(getOEEValueList, getStartDate, getEndDate, lineId, productionValue, gsys, getDay,
						i, jo);
			}

		} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
			for (int i = 1; i <= 30; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				queryEquipmentOEEValue(getOEEValueList, getStartDate, getEndDate, lineId, productionValue, gsys, getDay,
						i, jo);
			}

		} else {// 31天的月份
			for (int i = 1; i <= 31; i++) {
				monthList.add(i);
				String getStartDate = getYear + "-" + getMonth + "-" + i + " 0:00:00";
				String getEndDate = getYear + "-" + getMonth + "-" + i + " 23:59:59";
				queryEquipmentOEEValue(getOEEValueList, getStartDate, getEndDate, lineId, productionValue, gsys, getDay,
						i, jo);
			}
		}
		jo.put("getOEEValueList", getOEEValueList);
		jo.put("monthList", monthList);
		return jo;
	}

	@ResponseBody
	@RequestMapping(value = "/deciceOEE/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/deciceOEE/sessionConversation", method = RequestMethod.POST)
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
	@RequestMapping(value = "/deciceOEE/findByAll", method = RequestMethod.POST)
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

	// oee 内部调用的方法
	public static void queryEquipmentOEEValue(List<BigDecimal> getOEEValueList, String getStartDate, String getEndDate,
			Integer lineId, Integer productionValue, GetSomeYieldService gsys, Integer getDay, int i, JSONObject jo) {
		Integer getTheoryTime = 0;// 获取理论工作时间
		List<CMesShiftsTeamT> getShiftsTeamList = gsys.getShiftsTeamList(lineId);// 获取排班列表
		for (CMesShiftsTeamT cMesShiftsTeamT : getShiftsTeamList) {
			String[] strs2 = cMesShiftsTeamT.getStartTime().split(":");
			String[] strs1 = cMesShiftsTeamT.getEndTime().split(":");
			if (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0]) > 0) {
				getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;// 获取小时的
																										// 秒数
				getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;// 获取分钟的秒数
			} else if (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0]) == 0
					&& Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1]) > 0) {
				getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;// 获取小时的
																										// 秒数
				getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;// 获取分钟的秒数
			} else {// 结束时间小于开始时间 也就是说 从第一天去上班到了第二天
				getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;// 获取小时的
																										// 秒数
				getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;// 获取分钟的秒数
				getTheoryTime += 24 * 60 * 60;
			}
		}
		Integer getActualTime = gsys.getTime(getStartDate, getEndDate, lineId);// 获取实际时间
		if (getActualTime == null) {
			getActualTime = 0;
		}
		// 时间开动率 即 实际工作时间/理论工作时间
		Double availability = 0.0;
		if (getTheoryTime > 0) {
			availability = (double) getActualTime / (double) getTheoryTime;
		}

		Integer productNumber = gsys.getHourQualified(getStartDate, getEndDate, null, lineId);// 获取当天的产品的数量
		Integer qualificationNumber = gsys.getHourQualified(getStartDate, getEndDate, 2, lineId);// 获取当天的合格品的数量
																									// 2为合格品
		// 性能开动率 实际产量/理论产量
		Double performance = (double) productNumber / (double) productionValue;

		// 合格品率 即合格品的数量/产品的数量
		Double quality = 0.0;
		if (productNumber != 0) {
			quality = (double) qualificationNumber / (double) productNumber;
		}
		BigDecimal num = new BigDecimal(availability);
		BigDecimal getOEEValue = num.multiply(new BigDecimal(performance)).multiply(new BigDecimal(quality))
				.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_DOWN);
		getOEEValueList.add(getOEEValue);
		if (i == getDay) {
			jo.put("availability", GetDate.getNumber(availability * 100));
			jo.put("performance", GetDate.getNumber(performance * 100));
			jo.put("quality", GetDate.getNumber(quality * 100));
			jo.put("getOEEValue", getOEEValue);
		}
	}
}
