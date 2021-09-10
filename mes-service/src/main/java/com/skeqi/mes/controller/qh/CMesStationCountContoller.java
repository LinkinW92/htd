package com.skeqi.mes.controller.qh;

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
import com.skeqi.mes.common.lcy.GetRandomColor;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.service.lcy.GetReportFormsService;
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.Version;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 工位完成数量统计
 *
 * @ClassName: CMesStationCountContoller
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "工位完成数量统计", description = "工位完成数量统计", produces = MediaType.APPLICATION_JSON)
public class CMesStationCountContoller {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	@Autowired
	private GetReportFormsService gReportFormsService;

	@RequestMapping(value = "/stationCount/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到工位完成数量统计", notes = "根据多条件得到工位完成数量统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject get(Integer lineId, String time) throws ServicesException {

		String str = time;// 获取时间和月份

		JSONObject jo = new JSONObject();
		if (lineId == null) {
			lineId = 1;
		}
		Integer getYear = Integer.parseInt(str.substring(0, 4));
		Integer getMonth = Integer.parseInt(str.substring(5, 7));
		Integer getDay = Integer.parseInt(str.substring(8, 10));

		String getStartTime = null;
		String getEndTime = null;
		List<String> colorList = new ArrayList<>();
		// 获取该天运行的工位
		List<String> stList = gReportFormsService.getStationPassSt(lineId.toString());
		if (stList.size() != 0) {
			List<Integer> timeList = new ArrayList<>();
			if (stList.size() <= 3) {// 显示3个工位 一个月
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					for (int i = 0; i < stList.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (stList.get(i) != null && stList.get(i) != "") {
							List<Integer> getStProductNumberList = new ArrayList<>();
							for (int j = 1; j <= 29; j++) {
								getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								getStProductNumberList.add(gReportFormsService.getStationPassProductNumber(getStartTime,
										getEndTime, stList.get(i), lineId.toString()));

								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getStProductNumberList" + i, getStProductNumberList);
						}
					}
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					for (int i = 0; i < stList.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (stList.get(i) != null && stList.get(i) != "") {
							List<Integer> getStProductNumberList = new ArrayList<>();
							for (int j = 1; j <= 28; j++) {
								getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								getStProductNumberList.add(gReportFormsService.getStationPassProductNumber(getStartTime,
										getEndTime, stList.get(i), lineId.toString()));

								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getStProductNumberList" + i, getStProductNumberList);
						}
					}
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					for (int i = 0; i < stList.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (stList.get(i) != null && stList.get(i) != "") {
							List<Integer> getStProductNumberList = new ArrayList<>();
							for (int j = 1; j <= 30; j++) {
								getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								getStProductNumberList.add(gReportFormsService.getStationPassProductNumber(getStartTime,
										getEndTime, stList.get(i), lineId.toString()));

								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getStProductNumberList" + i, getStProductNumberList);
						}
					}
				} else {
					for (int i = 0; i < stList.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (stList.get(i) != null && stList.get(i) != "") {
							List<Integer> getStProductNumberList = new ArrayList<>();
							for (int j = 1; j <= 31; j++) {
								getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								getStProductNumberList.add(gReportFormsService.getStationPassProductNumber(getStartTime,
										getEndTime, stList.get(i), lineId.toString()));

								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getStProductNumberList" + i, getStProductNumberList);
						}
					}

				}

			} else if (stList.size() <= 7) {// 显示5个工位 半个月
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 29, 12, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 28, 12, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 30, 12, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 31, 12, colorList);
				}
			} else if (stList.size() <= 15) {// 显示10个工位 一周
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 29, 6, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 28, 6, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 30, 6, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 31, 6, colorList);
				}
			} else if (stList.size() <= 31) {// 显示20个工位 三天
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 29, 3, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 28, 3, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 30, 3, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							gReportFormsService, timeList, lineId.toString(), 31, 3, colorList);
				}
			} else {// 显示一天
				String getTime = str.substring(0, 10);
				for (int i = 0; i < stList.size(); i++) {
					colorList.add(GetRandomColor.getRandomColor());
					if (stList.get(i) != null && stList.get(i) != "") {
						List<Integer> getStProductNumberList = new ArrayList<>();
						getStartTime = getTime + " 0:00:00";
						getEndTime = getTime + " 23:59:59";
						getStProductNumberList.add(gReportFormsService.getStationPassProductNumber(getStartTime,
								getEndTime, stList.get(i), lineId.toString()));
						timeList.add(getDay);
						jo.put("getStProductNumberList" + i, getStProductNumberList);
					}
				}

			}
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < stList.size(); i++) {
				sb.append("{name: '" + stList.get(i) + "', type: 'bar', barWidth: 13,itemStyle:{ normal:{color:'"
						+ colorList.get(i) + "'}}," + "label: {normal: {show: true,position: 'top'}}," + "data: "
						+ (jo.getObject("getStProductNumberList" + i, List.class)) + "},");
			}
			String getStr = sb.toString().substring(0, sb.toString().length() - 1);
			jo.put("getStr", getStr);
			jo.put("timeList", timeList);
			jo.put("stList", stList);
		}
		return jo;
	}

	@ResponseBody
	@RequestMapping(value = "/stationCount/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/stationCount/sessionConversation", method = RequestMethod.POST)
	@ApiOperation("版本时间控制")
	public JSONObject getSessionConversation() {
		//TODO
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
	@RequestMapping(value = "/stationCount/findByAll", method = RequestMethod.POST)
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

	// limitDay 限制天数 这次循环多少次
	// monthDay 这个月最多有多少天
	public static void getStationNumberStatisticsValue(JSONObject jo, List<String> stList, Integer getDay,
			Integer getYear, Integer getMonth, String getStartTime, String getEndTime, GetReportFormsService grfs,
			List<Integer> timeList, String getLine, int MonthDay, int limitDay, List<String> colorList) {
		for (int i = 0; i < stList.size(); i++) {
			colorList.add(GetRandomColor.getRandomColor());
			if (stList.get(i) != null && stList.get(i) != "") {
				List<Integer> getStProductNumberList = new ArrayList<>();
				if (getDay + limitDay - 1 > MonthDay) {
					for (int j = MonthDay - limitDay; j <= MonthDay; j++) {
						getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
						getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
						getStProductNumberList.add(
								grfs.getStationPassProductNumber(getStartTime, getEndTime, stList.get(i), getLine));
						if (i == 0) {
							timeList.add(j);
						}
					}
					jo.put("getStProductNumberList" + i, getStProductNumberList);
				} else {
					for (int j = getDay; j <= getDay + limitDay - 1; j++) {
						getStartTime = getYear + "-" + getMonth + "-" + j + " 0:00:00";
						getEndTime = getYear + "-" + getMonth + "-" + j + " 23:59:59";
						getStProductNumberList.add(
								grfs.getStationPassProductNumber(getStartTime, getEndTime, stList.get(i), getLine));
						if (i == 0) {
							timeList.add(j);
						}
					}
					jo.put("getStProductNumberList" + i, getStProductNumberList);
				}
			}
		}
	}
}
