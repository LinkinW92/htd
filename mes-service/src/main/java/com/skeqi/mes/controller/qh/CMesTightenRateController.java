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
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.Version;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 拧紧合格率
 *
 * @ClassName: CMesTightenRateController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "拧紧合格率", description = "拧紧合格率", produces = MediaType.APPLICATION_JSON)
public class CMesTightenRateController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	/**
	 * 设备使用率
	 */

	@RequestMapping(value = "/tightenRate/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到拧紧合格率", notes = "根据多条件得到拧紧合格率")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject get(Integer lineId, String time) throws ServicesException {

		JSONObject jo = new JSONObject();
		String str = time;// 获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0, 4));// 截取年份
		Integer getMonth = Integer.parseInt(str.substring(5, 7));// 截取月份
		Integer getDay = Integer.parseInt(str.substring(8, 10));// 截取天
		List<Integer> timeList = new ArrayList<>();// 有多少天

		List<String> strs = gsys.getProductST(lineId.toString());// 获取工位名称
		@SuppressWarnings("rawtypes")
		List<String> colorList = new ArrayList();

		String getStartDate = null;
		String getEndDate = null;
		if (strs.size() != 0) {
			if (strs.size() <= 3) {// 显示3个工位 一个月
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (strs.get(i) != null && strs.get(i) != "") {
							List<Double> getQualifiedRate = new ArrayList<>();
							for (int j = 1; j <= 29; j++) {
								getStartDate = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndDate = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								Double qualifiedRate = gsys.someBoltProduct(strs.get(i), getStartDate, getEndDate);// 获取产
								getQualifiedRate.add(qualifiedRate);

								if (i == 0) {
									timeList.add(j);
								}

							}
							jo.put("getQualifiedRate" + i, getQualifiedRate);
						}

					}

				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (strs.get(i) != null && strs.get(i) != "") {
							List<Double> getQualifiedRate = new ArrayList<>();
							for (int j = 1; j <= 28; j++) {
								getStartDate = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndDate = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								Double qualifiedRate = gsys.someBoltProduct(strs.get(i), getStartDate, getEndDate);// 获取产
								getQualifiedRate.add(qualifiedRate);
								if (i == 0) {
									timeList.add(j);
								}

							}
							jo.put("getQualifiedRate" + i, getQualifiedRate);
						}
					}
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (strs.get(i) != null && strs.get(i) != "") {
							List<Double> getQualifiedRate = new ArrayList<>();
							for (int j = 1; j <= 30; j++) {
								getStartDate = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndDate = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								Double qualifiedRate = gsys.someBoltProduct(strs.get(i), getStartDate, getEndDate);// 获取产
								getQualifiedRate.add(qualifiedRate);
								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getQualifiedRate" + i, getQualifiedRate);
						}
					}
				} else {
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if (strs.get(i) != null && strs.get(i) != "") {
							List<Double> getQualifiedRate = new ArrayList<>();
							for (int j = 1; j <= 31; j++) {
								getStartDate = getYear + "-" + getMonth + "-" + j + " 0:00:00";
								getEndDate = getYear + "-" + getMonth + "-" + j + " 23:59:59";
								Double qualifiedRate = gsys.someBoltProduct(strs.get(i), getStartDate, getEndDate);// 获取产
								getQualifiedRate.add(qualifiedRate);
								if (i == 0) {
									timeList.add(j);
								}
							}
							jo.put("getQualifiedRate" + i, getQualifiedRate);
						}
					}
				}

			} else if (strs.size() <= 7) {// 显示5个工位 半个月
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							29, 12, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							28, 12, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							30, 12, colorList);

				} else {// 31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							31, 12, colorList);
				}
			} else if (strs.size() <= 15) {// 显示10个工位 一周
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							29, 6, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							28, 6, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							30, 6, colorList);
				} else {// 31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							31, 6, colorList);
				}
			} else if (strs.size() <= 31) {// 显示20个工位 三天
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							29, 3, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							28, 3, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							30, 3, colorList);
				} else {// 31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList,
							31, 3, colorList);

				}
			} else {// 显示一天
				for (int i = 0; i < strs.size(); i++) {
					colorList.add(GetRandomColor.getRandomColor());
					if (strs.get(i) != null && strs.get(i) != "") {
						List<Double> getQualifiedRate = new ArrayList<>();
						getStartDate = getYear + "-" + getMonth + "-" + getDay + " 0:00:00";
						getEndDate = getYear + "-" + getMonth + "-" + getDay + " 23:59:59";
						Double qualifiedRate = gsys.someBoltProduct(strs.get(i), getStartDate, getEndDate);// 获取产
						getQualifiedRate.add(qualifiedRate);

						if (i == 0) {
							timeList.add(getDay);
						}

						jo.put("getQualifiedRate" + i, getQualifiedRate);
					}
				}
			}
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strs.size(); i++) {
				sb.append("{name: '" + strs.get(i) + "', type: 'bar', barWidth: 13,itemStyle:{ normal:{color:'"
						+ colorList.get(i) + "'}}," + "label: {normal: {show: true,position: 'top'}}," + "data: "
						+ (jo.getObject("getQualifiedRate" + i, List.class)) + "},");
			}
			String getStr = sb.toString().substring(0, sb.toString().length() - 1);
			jo.put("getStr", getStr);
			jo.put("timeList", timeList);
		}
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/tightenRate/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/tightenRate/sessionConversation", method = RequestMethod.POST)
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
	@RequestMapping(value = "/tightenRate/findByAll", method = RequestMethod.POST)
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
	public static void getTightenRateValue(JSONObject jo,List<String> strs,Integer getDay,Integer getYear,Integer getMonth,
				String getStartDate,String getEndDate,GetSomeYieldService gsys,List<Integer> timeList,
				int MonthDay,int limitDay,List<String> colorList){
			for (int i = 0; i < strs.size(); i++){
				colorList.add(GetRandomColor.getRandomColor());
				if(strs.get(i)!=null&&strs.get(i)!=""){
				List<Double> getQualifiedRate = new ArrayList<>();
				if(getDay+limitDay-1>MonthDay){
					for(int j =MonthDay-limitDay;j<=MonthDay;j++){
						getStartDate = getYear+"-"+getMonth+"-"+j+" 0:00:00";
						getEndDate=getYear+"-"+getMonth+"-"+j+" 23:59:59";
						Double qualifiedRate = gsys.someBoltProduct(strs.get(i),getStartDate,getEndDate);//获取产
						getQualifiedRate.add(qualifiedRate);
						if(i==0){
							timeList.add(j);
						}
					}
					jo.put("getQualifiedRate"+i,getQualifiedRate);
				}else{
					for(int j =getDay;j<=getDay+limitDay-1;j++){
						getStartDate = getYear+"-"+getMonth+"-"+j+" 0:00:00";
						getEndDate=getYear+"-"+getMonth+"-"+j+" 23:59:59";
						Double qualifiedRate = gsys.someBoltProduct(strs.get(i),getStartDate,getEndDate);//获取产
						getQualifiedRate.add(qualifiedRate);
						if(i==0){
							timeList.add(j);
						}
					}
					jo.put("getQualifiedRate"+i,getQualifiedRate);
				}
			}
		}
	}
}
