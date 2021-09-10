package com.skeqi.mes.controller.lcy;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.common.lcy.GetRandomColor;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.lcy.GetReportFormsService;

@Controller
@RequestMapping("skq")
public class GetReportFormsController {

	@Autowired
	private GetReportFormsService grfs;

	/**
	 *
	 * 工位完成统计
	 *
	 * @return
	 */

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

	// 工位完成数量统计
	@ResponseBody
	@RequestMapping("getStationNumberStatistics")
	public JSONObject getStationNumberStatistics(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		String str = request.getParameter("getTime");// 获取时间
		String getLine = request.getParameter("getLine");// 获取产线
		if (getLine == "" || getLine == null) {
			getLine = "1";
		}
		Integer getYear = Integer.parseInt(str.substring(0, 4));
		Integer getMonth = Integer.parseInt(str.substring(5, 7));
		Integer getDay = Integer.parseInt(str.substring(8, 10));

		String getStartTime = null;
		String getEndTime = null;
		List<String> colorList = new ArrayList<>();
		// 获取该天运行的工位
		List<String> stList = grfs.getStationPassSt(getLine);
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
								getStProductNumberList.add(grfs.getStationPassProductNumber(getStartTime, getEndTime,
										stList.get(i), getLine));

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
								getStProductNumberList.add(grfs.getStationPassProductNumber(getStartTime, getEndTime,
										stList.get(i), getLine));

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
								getStProductNumberList.add(grfs.getStationPassProductNumber(getStartTime, getEndTime,
										stList.get(i), getLine));

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
								getStProductNumberList.add(grfs.getStationPassProductNumber(getStartTime, getEndTime,
										stList.get(i), getLine));

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
							grfs, timeList, getLine, 29, 12, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 28, 12, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 30, 12, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 31, 12, colorList);
				}
			} else if (stList.size() <= 15) {// 显示10个工位 一周
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 29, 6, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 28, 6, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 30, 6, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 31, 6, colorList);
				}
			} else if (stList.size() <= 31) {// 显示20个工位 三天
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 29, 3, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 28, 3, colorList);
				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 30, 3, colorList);
				} else {// 31天的天数
					getStationNumberStatisticsValue(jo, stList, getDay, getYear, getMonth, getStartTime, getEndTime,
							grfs, timeList, getLine, 31, 3, colorList);
				}
			} else {// 显示一天
				String getTime = str.substring(0, 10);
				for (int i = 0; i < stList.size(); i++) {
					colorList.add(GetRandomColor.getRandomColor());
					if (stList.get(i) != null && stList.get(i) != "") {
						List<Integer> getStProductNumberList = new ArrayList<>();
						getStartTime = getTime + " 0:00:00";
						getEndTime = getTime + " 23:59:59";
						getStProductNumberList.add(
								grfs.getStationPassProductNumber(getStartTime, getEndTime, stList.get(i), getLine));
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
	/**
	 * 班次数量统计
	 */
	// limitDay 限制天数 这次循环多少次
	// monthDay 这个月最多有多少天
	public static void getShiftNumberStatisticsValue(String getSomeDate, JSONObject jo,
			List<CMesShiftsTeamT> shiftsTeamList, Integer getDay, Integer getYear, Integer getMonth,
			String getStartTime, String getEndTime, GetReportFormsService grfs, List<Integer> timeList, Integer getLine,
			int limitDay, int monthDay, List<String> colorList) {
		for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
			colorList.add(GetRandomColor.getRandomColor());
			if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
					&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
					&& shiftsTeamList.get(i).getStartTime() != "" && shiftsTeamList.get(i).getEndTime() != ""
					&& shiftsTeamList.get(i).getName() != "") {
				List<Integer> getShiftsNumberList = new ArrayList<>();
				if (getDay + limitDay - 1 > monthDay) {
					for (int j = monthDay - limitDay; j <= monthDay; j++) {

						if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 说明是当天的班
							getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
							getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
						} else if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 小时相等 看分钟
							if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
									.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
								// 输入结束的分钟大于开始的分钟
								getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
							} else {
								if (j != monthDay) {
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();

								} else {

									if (getMonth != 12) {

										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
												+ shiftsTeamList.get(i).getEndTime();
									} else {
										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();

									}
								}
							}
						} else {
							if (j != monthDay) {
								getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();
							} else {
								if (getMonth != 12) {
									getStartTime = getYear + "-" + getMonth + "-" + j + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
											+ shiftsTeamList.get(i).getEndTime();
								} else {
									getStartTime = getYear + "-" + getMonth + "-" + j + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();
								}
							}
						}
						getShiftsNumberList.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
								GetDate.getDateforSimple(getEndTime), getLine));
						if (i == 0) {
							timeList.add(j);
						}
					}
					jo.put("getShiftsNumberList" + i, getShiftsNumberList);
				} else {
					for (int j = getDay; j <= getDay + limitDay - 1; j++) {
						if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 说明是当天的班
							getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
							getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
						} else if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 小时相等 看分钟
							if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
									.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
								// 输入结束的分钟大于开始的分钟
								getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
							} else {
								if (j != monthDay) {
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();
								} else {

									if (getMonth != 12) {

										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
												+ shiftsTeamList.get(i).getEndTime();
									} else {
										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();
									}
								}
							}

						} else {
							if (j != monthDay) {
								getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();
							} else {

								if (getMonth != 12) {

									getStartTime = getYear + getMonth + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
											+ shiftsTeamList.get(i).getEndTime();
								} else {
									getStartTime = getYear + "-" + getMonth + "-" + j + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();
								}
							}
						}
						getShiftsNumberList.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
								GetDate.getDateforSimple(getEndTime), getLine));
						if (i == 0) {
							timeList.add(j);
						}
					}
					jo.put("getShiftsNumberList" + i, getShiftsNumberList);
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping("getShiftNumberStatistics")
	public JSONObject getShiftNumberStatistics(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		String lineStr = request.getParameter("getLine");
		Integer getLine = Integer.parseInt(lineStr);
		String str = request.getParameter("getTime");// 获取时间
		Integer getYear = Integer.parseInt(str.substring(0, 4));
		Integer getMonth = Integer.parseInt(str.substring(5, 7));
		Integer getDay = Integer.parseInt(str.substring(8, 10));
		String getSomeDate = str.substring(0, 8);
		String getStartTime = null;
		String getEndTime = null;

		// 获取产线的班次列表
		List<CMesShiftsTeamT> shiftsTeamList = grfs.shiftsTeamList(getLine,null);

		List<String> colorList = new ArrayList<>();
		if (shiftsTeamList.size() != 0) {
			List<Integer> timeList = new ArrayList<>();// 时间列表

			if (shiftsTeamList.size() <= 3) {
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
						colorList.add(GetRandomColor.getRandomColor());
						if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
								&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
								&& shiftsTeamList.get(i).getStartTime() != ""
								&& shiftsTeamList.get(i).getEndTime() != "" && shiftsTeamList.get(i).getName() != "") {
							List<Integer> getShiftsNumberList = new ArrayList<>();
							for (int j = 1; j <= 29; j++) {
								if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
										.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 说明是当天的班
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
								} else if (Integer
										.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
												.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 小时相等 看分钟
									if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
											.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
										// 输入结束的分钟大于开始的分钟
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
									} else {
										if (j != 28) {
											getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (j + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											getStartTime = getYear + "-" + getMonth + "-" + j + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
													+ shiftsTeamList.get(i).getEndTime();

										}
									}

								} else {
									if (j != 28) {
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
												+ shiftsTeamList.get(i).getEndTime();

									}
								}

								getShiftsNumberList
										.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), getLine));
								if (i == 0) {
									timeList.add(j);
								}

							}

							jo.put("getShiftsNumberList" + i, getShiftsNumberList);
						}
					}
				} else if (getMonth == 2 && getYear % 4 != 0) {
					for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
						colorList.add(GetRandomColor.getRandomColor());
						if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
								&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
								&& shiftsTeamList.get(i).getStartTime() != ""
								&& shiftsTeamList.get(i).getEndTime() != "" && shiftsTeamList.get(i).getName() != "") {
							List<Integer> getShiftsNumberList = new ArrayList<>();
							for (int j = 1; j <= 28; j++) {
								if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
										.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 说明是当天的班
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
								} else if (Integer
										.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
												.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 小时相等 看分钟
									if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
											.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
										// 输入结束的分钟大于开始的分钟
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
									} else {
										if (j != 28) {
											getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (j + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											getStartTime = getYear + "-" + getMonth + "-" + j + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
													+ shiftsTeamList.get(i).getEndTime();

										}
									}

								} else {
									if (j != 28) {
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
												+ shiftsTeamList.get(i).getEndTime();

									}
								}
								getShiftsNumberList
										.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), getLine));
								if (i == 0) {
									timeList.add(j);
								}

							}

							jo.put("getShiftsNumberList" + i, getShiftsNumberList);
						}
					}

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {
					for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
						colorList.add(GetRandomColor.getRandomColor());
						if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
								&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
								&& shiftsTeamList.get(i).getStartTime() != ""
								&& shiftsTeamList.get(i).getEndTime() != "" && shiftsTeamList.get(i).getName() != "") {
							List<Integer> getShiftsNumberList = new ArrayList<>();
							for (int j = 1; j <= 30; j++) {
								if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
										.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 说明是当天的班
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
								} else if (Integer
										.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
												.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 小时相等 看分钟
									if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
											.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
										// 输入结束的分钟大于开始的分钟
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
									} else {
										if (j != 30) {
											getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (j + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											getStartTime = getYear + "-" + getMonth + "-" + j + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
													+ shiftsTeamList.get(i).getEndTime();

										}
									}

								} else {
									if (j != 30) {
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + j + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
												+ shiftsTeamList.get(i).getEndTime();

									}
								}
								getShiftsNumberList
										.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), getLine));
								if (i == 0) {
									timeList.add(j);
								}

							}

							jo.put("getShiftsNumberList" + i, getShiftsNumberList);
						}
					}

				} else {
					for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
						colorList.add(GetRandomColor.getRandomColor());
						if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
								&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
								&& shiftsTeamList.get(i).getStartTime() != ""
								&& shiftsTeamList.get(i).getEndTime() != "" && shiftsTeamList.get(i).getName() != "") {
							List<Integer> getShiftsNumberList = new ArrayList<>();
							for (int j = 1; j <= 31; j++) {
								if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
										.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 说明是当天的班
									getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
								} else if (Integer
										.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
												.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
									// 小时相等 看分钟
									if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
											.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
										// 输入结束的分钟大于开始的分钟
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
									} else {
										if (j != 31) {
											getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (j + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											if (getMonth != 12) {
												getStartTime = getYear + getMonth + j + " "
														+ shiftsTeamList.get(i).getStartTime();
												getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
														+ shiftsTeamList.get(i).getEndTime();
											} else {
												getStartTime = getYear + getMonth + j + " "
														+ shiftsTeamList.get(i).getStartTime();
												getEndTime = (getYear + 1) + "-1-1 "
														+ shiftsTeamList.get(i).getEndTime();
											}
										}
									}
								} else {
									if (j != 31) {
										getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (j + 1) + " " + shiftsTeamList.get(i).getEndTime();

									} else {
										if (getMonth != 12) {
											getStartTime = getYear + getMonth + j + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getYear + "-" + (getMonth + 1) + "-1 "
													+ shiftsTeamList.get(i).getEndTime();
										} else {
											getStartTime = getYear + getMonth + j + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();

										}

									}
								}
								getStartTime = getSomeDate + j + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getSomeDate + j + " " + shiftsTeamList.get(i).getEndTime();
								getShiftsNumberList
										.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), getLine));
								if (i == 0) {
									timeList.add(j);
								}

							}

							jo.put("getShiftsNumberList" + i, getShiftsNumberList);
						}
					}
				}

			} else if (shiftsTeamList.size() <= 7) {// 显示15天

				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 12, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 12, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 12, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 12, 31, colorList);

				}

			} else if (shiftsTeamList.size() <= 15) {// 显示一周

				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 6, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 6, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 6, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 6, 31, colorList);
				}

			} else if (shiftsTeamList.size() <= 31) {// 显示3天
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 3, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 3, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 3, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, grfs, timeList, getLine, 3, 31, colorList);
				}

			} else {// 只显示一天
				for (int i = 0; i < shiftsTeamList.size(); i++) {// 根据排版来循环获取数据
					colorList.add(GetRandomColor.getRandomColor());
					if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
							&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
							&& shiftsTeamList.get(i).getStartTime() != "" && shiftsTeamList.get(i).getEndTime() != ""
							&& shiftsTeamList.get(i).getName() != "") {
						List<Integer> getShiftsNumberList = new ArrayList<>();
						String getTime = str.substring(0, 10);

						if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) > Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 说明是当天的班
							getStartTime = getTime + " " + shiftsTeamList.get(i).getStartTime();
							getEndTime = getTime + " " + shiftsTeamList.get(i).getEndTime();
						} else if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(0, 2)) == Integer
								.parseInt(shiftsTeamList.get(i).getStartTime().substring(0, 2))) {
							// 小时相等 看分钟
							if (Integer.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5)) > Integer
									.parseInt(shiftsTeamList.get(i).getEndTime().substring(3, 5))) {
								// 输入结束的分钟大于开始的分钟
								getStartTime = getTime + " " + shiftsTeamList.get(i).getStartTime();
								getEndTime = getTime + " " + shiftsTeamList.get(i).getEndTime();
							} else {

								if (getMonth == 2 && getYear % 4 == 0) {
									if (getDay != 29) {
										getStartTime = getSomeDate + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (getDay + 1) + " "
												+ shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
												+ shiftsTeamList.get(i).getEndTime();
									}

								} else if (getMonth == 2 && getYear % 4 != 0) {
									if (getDay != 28) {
										getStartTime = getSomeDate + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (getDay + 1) + " "
												+ shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
												+ shiftsTeamList.get(i).getEndTime();
									}

								} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {

									if (getDay != 30) {
										getStartTime = getSomeDate + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (getDay + 1) + " "
												+ shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
												+ shiftsTeamList.get(i).getEndTime();
									}

								} else {
									if (getMonth != 12) {
										if (getDay != 31) {
											getStartTime = getSomeDate + getDay + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (getDay + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
													+ shiftsTeamList.get(i).getEndTime();
										}

									} else {

										if (getDay != 31) {
											getStartTime = getSomeDate + getDay + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = getSomeDate + (getDay + 1) + " "
													+ shiftsTeamList.get(i).getEndTime();

										} else {
											getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
													+ shiftsTeamList.get(i).getStartTime();
											getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();
										}

									}
								}

							}
						} else {

							if (getMonth == 2 && getYear % 4 == 0) {
								if (getDay != 29) {
									getStartTime = getSomeDate + getDay + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + (getDay + 1) + " " + shiftsTeamList.get(i).getEndTime();

								} else {
									getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
											+ shiftsTeamList.get(i).getEndTime();
								}

							} else if (getMonth == 2 && getYear % 4 != 0) {
								if (getDay != 28) {
									getStartTime = getSomeDate + getDay + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + (getDay + 1) + " " + shiftsTeamList.get(i).getEndTime();

								} else {
									getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
											+ shiftsTeamList.get(i).getEndTime();
								}

							} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {

								if (getDay != 30) {
									getStartTime = getSomeDate + getDay + " " + shiftsTeamList.get(i).getStartTime();
									getEndTime = getSomeDate + (getDay + 1) + " " + shiftsTeamList.get(i).getEndTime();

								} else {
									getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
											+ shiftsTeamList.get(i).getStartTime();
									getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
											+ shiftsTeamList.get(i).getEndTime();
								}

							} else {
								if (getMonth != 12) {
									if (getDay != 31) {
										getStartTime = getSomeDate + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (getDay + 1) + " "
												+ shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getYear + "-" + (getMonth + 1) + "-" + 1 + " "
												+ shiftsTeamList.get(i).getEndTime();
									}

								} else {

									if (getDay != 31) {
										getStartTime = getSomeDate + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = getSomeDate + (getDay + 1) + " "
												+ shiftsTeamList.get(i).getEndTime();

									} else {
										getStartTime = getYear + "-" + getMonth + "-" + getDay + " "
												+ shiftsTeamList.get(i).getStartTime();
										getEndTime = (getYear + 1) + "-1-1 " + shiftsTeamList.get(i).getEndTime();
									}

								}
							}
						}

						getShiftsNumberList.add(grfs.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
								GetDate.getDateforSimple(getEndTime), getLine));

						jo.put("getShiftsNumberList" + i, getShiftsNumberList);
					}
				}

			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < shiftsTeamList.size(); i++) {
				if (shiftsTeamList.get(i) != null && shiftsTeamList.get(i).getStartTime() != null
						&& shiftsTeamList.get(i).getEndTime() != null && shiftsTeamList.get(i).getName() != null
						&& shiftsTeamList.get(i).getStartTime() != "" && shiftsTeamList.get(i).getEndTime() != ""
						&& shiftsTeamList.get(i).getName() != "") {
					sb.append("{name: '" + shiftsTeamList.get(i).getName()
							+ "', type: 'bar', barWidth: 13,itemStyle:{ normal:{color:'" + colorList.get(i) + "'}},"
							+ "label: {normal: {show: true,position: 'top'}}," + "data: "
							+ (jo.getObject("getShiftsNumberList" + i, List.class)) + "},");

				}
			}
			String getStr = sb.toString().substring(0, sb.toString().length() - 1);
			jo.put("getStr", getStr);
			jo.put("timeList", timeList);
		}
		return jo;
	}

}
