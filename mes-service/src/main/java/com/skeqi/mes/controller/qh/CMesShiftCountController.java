package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.common.lcy.GetRandomColor;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
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
 * 班次数量统计
 *
 * @ClassName: CMesPlantFactorController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "班次数量统计", description = "班次数量统计", produces = MediaType.APPLICATION_JSON)
public class CMesShiftCountController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	@Autowired
	private GetReportFormsService gReportFormsService;


	/**
	 * 设备使用率
	 */

	@RequestMapping(value = "/shiftCount/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到班次数量统计", notes = "根据多条件得到班次数量统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject get(Integer lineId, String time) throws ServicesException {

		JSONObject jo = new JSONObject();
		String str = time;// 获取时间和月份 //yyyy-mm-dd

		Integer getYear = Integer.parseInt(str.substring(0, 4));
		Integer getMonth = Integer.parseInt(str.substring(5, 7));
		Integer getDay = Integer.parseInt(str.substring(8, 10));
		String getSomeDate = str.substring(0, 8);
		String getStartTime = null;
		String getEndTime = null;

		// 获取产线的班次列表
		List<CMesShiftsTeamT> shiftsTeamList = gReportFormsService.shiftsTeamList(lineId,null);

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
										.add(gReportFormsService.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), lineId));
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
										.add(gReportFormsService.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), lineId));
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
										.add(gReportFormsService.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), lineId));
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
										.add(gReportFormsService.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
												GetDate.getDateforSimple(getEndTime), lineId));
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
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 12, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 12, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 12, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 12, 31, colorList);

				}

			} else if (shiftsTeamList.size() <= 15) {// 显示一周

				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 6, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 6, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 6, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 6, 31, colorList);
				}

			} else if (shiftsTeamList.size() <= 31) {// 显示3天
				if (getMonth == 2 && getYear % 4 == 0) {// 润年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 3, 29, colorList);
				} else if (getMonth == 2 && getYear % 4 != 0) {// 平年二月
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 3, 28, colorList);

				} else if (getMonth == 4 || getMonth == 6 || getMonth == 9 || getMonth == 11) {// 30天的月份
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 3, 30, colorList);

				} else {// 31天的天数
					getShiftNumberStatisticsValue(getSomeDate, jo, shiftsTeamList, getDay, getYear, getMonth,
							getStartTime, getEndTime, gReportFormsService, timeList, lineId, 3, 31, colorList);
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

						getShiftsNumberList.add(gReportFormsService.getShiftNumberStatistics(GetDate.getDateforSimple(getStartTime),
								GetDate.getDateforSimple(getEndTime), lineId));

						jo.put("getShiftsNumberList" + i, getShiftsNumberList);
					}
				}

			}

			jo.put("timeList", timeList);
		}
		return jo;
	}


	@ResponseBody
	@RequestMapping(value = "/shiftCount/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/shiftCount/sessionConversation", method = RequestMethod.POST)
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
	@RequestMapping(value = "/shiftCount/findByAll", method = RequestMethod.POST)
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

}
