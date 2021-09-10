package com.skeqi.mes.service.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.all.RMesNextBarcodePEcDao;
import com.skeqi.mes.pojo.api.RMesNextBarcodePEcT;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.StringUtil;

@Service
public class RMesNextBarcodePEcServiceImpl implements RMesNextBarcodePEcService {

	@Autowired
	RMesNextBarcodePEcDao dao;

	RMesNextBarcodePEcT dx = new RMesNextBarcodePEcT();

	// 入参
	Integer lineInId;
	// 出参
	Integer rPrint;// 0：成功 >1 失败
	String nextBarcode;
	String nextOneBarcode;
	String labelName;

	// 用于判断日期在#的左边还是右边
	boolean res = false;
	Integer temp_plan_count;// 产线计划数量
	Integer temp_plan_level_max;// 最大优先级
	Integer temp_plan_level;
	Integer temp_plan_comdition_count;
	Integer temp_plan_number;
	Integer temp_plan_online_number;
	Integer temp_current_plan_id;// 工单id
	Integer temp_plan_id;// 计划id
	Integer temp_plan_print_count;
	Integer temp_alarm_mark;// 没有计划标记
	String temp_label_rules;// 条码标签
	String temp_label_vr;// 条码日期和流水号格式
	Integer temp_label_type_id;// 条码类型id gb/t 34014 强制规定为id=1
	String temp_label_head;// 条码标签‘#’之前部分
	String temp_label_end;// 条码标签‘#’之后部分
	String temp_label_ymd;// 条码类型中月日年
	String temp_label_code;// 条码类型中流水号
	Integer tmep_code_serial_max;
	Integer temp_production_id;
	String temp_old_sn_code;
	Integer temp_old_sn_num;
	String temp_current_create_year;// 处理后的年份
	String tmep_current_month;// 当前月份
	String temp_current_create_day;// 处理后的天数
	String temp_print_flag;// 是否打印的标记
	String temp_shift;// 班次
	Integer temp_mark;// 标记
	String temp_exception_msg;
	Integer temp_day;
	Integer temp_year;
	String temp_label_name;// 标签名称
	String temp_date;
	String tempLabelTypeName;// 规则类型名称

	JSONObject json = new JSONObject();

	@Transactional
	@Override
	public JSONObject main(JSONObject jObject) throws ParseException {

		rPrint = 0;
		nextBarcode = "";
		nextOneBarcode = "";
		labelName = "";
		temp_alarm_mark = 0;

		// 获取产线
		lineInId = jObject.getInteger("lineId");

		jObject = new JSONObject();

		temp_plan_count = dao.find2(lineInId);

		if (temp_plan_count > 0) {

			// temp_plans_d
			List<RMesNextBarcodePEcT> find1Dxs = dao.find1(lineInId);

			for (RMesNextBarcodePEcT find1Dx : find1Dxs) {

				temp_plan_count = dao.find3(find1Dx.getLineId());

				if (temp_plan_count > 0) {

					temp_plan_level_max = dao.find4(find1Dx.getLineId());

					if (temp_plan_level_max > 0) {

						// temp_plan_level
						List<RMesNextBarcodePEcT> find5Dxs = dao.find5(find1Dx.getLineId());

						for (RMesNextBarcodePEcT find5Dx : find5Dxs) {
							temp_plan_comdition_count = dao.find6(lineInId, find5Dx.getLevelNo());

							if (temp_plan_comdition_count > 0) {

								RMesNextBarcodePEcT find7Dx = dao.find7(find1Dx.getId(), lineInId,
										find5Dx.getLevelNo());
								temp_current_plan_id = find7Dx.getTempCurrentPlanId();
								temp_plan_number = find7Dx.getTempPlanNumber();
								temp_plan_online_number = find7Dx.getTempPlanOnlineNumber();
								temp_production_id = find7Dx.getTempProductionId();
								temp_plan_id = find7Dx.getTempPlanId();

								RMesNextBarcodePEcT find8Dx = dao.find8(temp_current_plan_id);
								temp_label_rules = find8Dx.getTempLabelRules();
								temp_label_name = find8Dx.getTempLabelName();
								temp_label_vr = find8Dx.getTempLabelVr();
								temp_label_type_id = find8Dx.getTempLabelTypeId();
								labelName = temp_label_name;
								temp_label_head = find8Dx.getTempLabelHead();
								temp_label_end = find8Dx.getTempLabelEnd();
								temp_label_ymd = find8Dx.getTempLabelYmd();
								temp_label_code = find8Dx.getTempLabelCode();
								tempLabelTypeName = find8Dx.getTempLabelTypeName();

								// 当前时间
								Date nowTime = DateUtil.getNowDateHms();
								String format = "HH:mm:ss";
								// 开始时间
								Date startTime = new SimpleDateFormat(format).parse("08:30:00");
								// 结束时间
								Date endTime = new SimpleDateFormat(format).parse("20:30:00");
								// 判断是白班还是晚班
								if (DateUtil.isEffectiveDate(nowTime, startTime, endTime)) {
									temp_shift = "a";
								} else {
									temp_shift = "b";
								}

								// 国标
								if (tempLabelTypeName.equals("GB/T 34014")) {

									temp_label_head = temp_label_head.substring(0, temp_label_head.indexOf("##"));

									temp_label_end = temp_label_end.substring(temp_label_end.lastIndexOf("#") + 1);

									temp_label_ymd = temp_label_ymd.substring(0, temp_label_ymd.indexOf("#"));

									temp_label_code = temp_label_code.substring(temp_label_code.indexOf("#"));
									// 年 代码
									temp_current_create_year = nian();
									// 日 代码
									temp_current_create_day = ri();
									// 月 代码
									tmep_current_month = yue();

									temp_date = temp_current_create_year + tmep_current_month + temp_current_create_day;

								} else {
									// 非国标=========
									// 开头
									temp_label_head = temp_label_head.substring(0, temp_label_head.indexOf("#"));
									// 结尾
									temp_label_end = temp_label_end.substring(temp_label_end.lastIndexOf("#") + 1,
											temp_label_end.length());

									// 保存###
									temp_label_code = temp_label_vr.substring(temp_label_vr.indexOf("#"),
											temp_label_vr.lastIndexOf("#") + 1);
									// 保存日期格式
									temp_label_ymd = "";

									// 判断日期在#的左边还是右边
									// res = true 表示日期在#的左边
									if (temp_label_vr.indexOf("#") == 0) {
										temp_label_ymd = temp_label_vr.substring(temp_label_vr.lastIndexOf("#") + 1,
												temp_label_vr.length());
									} else {
										res = true;
										temp_label_ymd = temp_label_vr.substring(0, temp_label_vr.indexOf("#"));
									}

									SimpleDateFormat df = new SimpleDateFormat(temp_label_ymd);// 设置日期格式
									// 保存日期
									temp_date = df.format(new Date());
									// if(!res){
									// System.out.println(temp_date+str);
									// }else{
									// System.out.println(str+temp_date);
									// }
									// temp_label_vr =

								}

								// 获取当前月
								Integer yue = Integer.parseInt(DateUtil.getYue());

								// 年 正常
								temp_year = Integer.parseInt(DateUtil.getNian());

								// 日 正常
								temp_day = Integer.parseInt(DateUtil.getRi());


								temp_mark = dao.find9();

								// 保存已生成条码
								RMesNextBarcodePEcT yshengchengCode = null;
								RMesNextBarcodePEcT yshengchengCodeP = null;

								// 查询产线条码生成类型
								Integer codeType = dao.findLineCodeType(lineInId);
								switch (codeType) {
								case 0:
									// 同日期同产品
									// yshengchengCode =
									// mapper.findSNYrqYcp(temp_production_id);
									yshengchengCode = dao.findSNYrqYcp(temp_production_id);
//									if (yshengchengCode == null) {
//										yshengchengCode = mapper.trqtcpcxpb(temp_production_id);
//									}
									break;
								case 1:
									// 同日期不同产品
									yshengchengCode = dao.findSNYrqNcp();
									yshengchengCodeP = dao.findSNYrqNcpP();
									if(yshengchengCode == null) {
										yshengchengCode = yshengchengCodeP;
									} else {
										if (yshengchengCodeP != null) {
											if(yshengchengCode.getTempOldSnCode().compareTo(yshengchengCodeP.getTempOldSnCode()) < 0) {
												yshengchengCode = yshengchengCodeP;
											}
										}
									}
									break;
								case 2:
									// 不同日期同产品
									yshengchengCode = dao.findSNNrqYcp(temp_production_id);
									break;
								case 3:
									// 不同日期不同产品
									yshengchengCode = dao.findSNNrqNcp();
									break;
								case 4:
									// 其他
									yshengchengCode = dao.find10();
									break;
								}

								if (yshengchengCode == null) {
									temp_print_flag = "0";
									temp_old_sn_code = "";
								} else {
									temp_print_flag = yshengchengCode.getTempPrintFlag();
									temp_old_sn_code = yshengchengCode.getTempOldSnCode();
								}

								if (temp_plan_number > temp_plan_online_number) {
									// 是否已经全部打印
									if (!temp_old_sn_code.equals("")) {

										// 判断是否有生成的条码没有打印，若有则直接返回 若没有则生成新的条码
										temp_alarm_mark = 1;// 取消没有条码报警
										temp_plan_print_count = dao.find11();


										temp_old_sn_num = Integer.parseInt(temp_old_sn_code.substring(
												temp_old_sn_code.length()
														- (temp_label_end.length() + temp_label_code.length()),
												(temp_old_sn_code.length()
														- (temp_label_end.length() + temp_label_code.length()))
														+ temp_label_code.length()));

										tmep_code_serial_max = yshengchengCode.getTmepCodeSerialMax();
										if (tmep_code_serial_max == null) {
											tmep_code_serial_max = 1;
										} else {
											tmep_code_serial_max ++;
										}
										Integer findPlanstatus = dao.findPlanstatus(temp_old_sn_code);
										if (temp_print_flag.equals("0") && findPlanstatus==1) {
											nextBarcode = temp_old_sn_code;
											Integer str1num = temp_old_sn_num + 1;
											String str1 = StringUtil.lpad(str1num.toString(), temp_label_code.length());
											str1 = str1.replace(" ", "0");
											// nextOneBarcode = temp_label_head
											// + temp_date + str1 +
											// temp_label_end;
											if (tempLabelTypeName.equals("GB/T 34014")) {
												nextOneBarcode = temp_label_head + temp_date + str1 + temp_label_end;
											} else {
												if (!res) {
													nextOneBarcode = temp_label_head + str1 + temp_date
															+ temp_label_end;
												} else {
													nextOneBarcode = temp_label_head + temp_date + str1
															+ temp_label_end;
												}
											}
											jObject.put("isSuccess", true);
											jObject.put("errMsg", "");
											json.put("barcode", nextBarcode);
											json.put("barcodeNext", nextOneBarcode);
											json.put("labelName", labelName);
											jObject.put("result", json);
											return jObject;
										} else {
//											RMesPlanPrintT findMaxSn = mapper.findMaxSn();
//											String sn = findMaxSn.getSn();
//											if(sn!=null && sn!="") {
//												temp_old_sn_num = Integer.parseInt(sn.substring(sn.length()-7, sn.length()));
//											}
//											Integer str1num = temp_old_sn_num + 1;
//											String str1 = StringUtil.lpad(str1num.toString(), temp_label_code.length());
//											str1 = str1.replace(" ", "0");
											String sn = dao.findMaxSn();
											if(sn!=null && sn!="") {
												temp_old_sn_num = Integer.parseInt(sn.substring(sn.length()-7, sn.length()));
											}
											Integer str1num = temp_old_sn_num + 1;
											String str1 = StringUtil.lpad(str1num.toString(), temp_label_code.length());
											str1 = str1.replace(" ", "0");
											nextBarcode = temp_label_head + temp_date + str1 + temp_label_end;

											if (temp_plan_number != (temp_plan_online_number + 1)) {
												Integer str2Num = temp_old_sn_num + 2;

												// 国标
												String str2 = StringUtil.lpad(str2Num.toString(),
														temp_label_code.length());
												str2 = str2.replace(" ", "0");
												if (tempLabelTypeName.equals("GB/T 34014")) {
													nextOneBarcode = temp_label_head + temp_date + str2
															+ temp_label_end;
												} else {
													if (!res) {
														nextOneBarcode = temp_label_head + str2 + temp_date
																+ temp_label_end;
													} else {
														nextOneBarcode = temp_label_head + temp_date + str2
																+ temp_label_end;
													}
												}
											}

											Integer resultinsert1 = dao.insert1(nextBarcode, temp_plan_id, lineInId,
													temp_production_id, tmep_code_serial_max, temp_current_plan_id);
											Integer resultupdate1 = dao.update1(temp_current_plan_id);
											jObject.put("isSuccess", true);
											jObject.put("errMsg", "");
											json.put("barcode", nextBarcode);
											json.put("barcodeNext", nextOneBarcode);
											json.put("labelName", labelName);
											jObject.put("result", json);
											return jObject;
										}
									} else {

//										String str1 = StringUtil.lpad("1", temp_label_code.length());
//										str1 = str1.replace(" ", "0");
										Integer parseInt = 0;
										String str1 = "0000001";
										String sn = dao.findMaxSn();
										if(sn!=null && sn!="") {
											parseInt = Integer.parseInt(sn.substring(sn.length()-7, sn.length()));
											str1 = String.format("%7d", parseInt+1).replace(" ", "0");
										}
										nextBarcode = temp_label_head + temp_date + str1 + temp_label_end;
										if (tempLabelTypeName.equals("GB/T 34014")) {
											nextBarcode = temp_label_head + temp_date + str1 + temp_label_end;
										} else {
											if (!res) {
												nextBarcode = temp_label_head + str1 + temp_date + temp_label_end;
											} else {
												nextBarcode = temp_label_head + temp_date + str1 + temp_label_end;
											}
										}
										str1 = String.format("%7d", parseInt+2).replace(" ", "0");

										if (temp_plan_number != (temp_plan_online_number + 1)) {
											String str2 = "00000002";
											if(sn!=null && sn!="") {
												 str2 = String.format("%7d", parseInt+2).replace(" ", "0");
											}
											if (tempLabelTypeName.equals("GB/T 34014")) {
												nextOneBarcode = temp_label_head + temp_date + str2 + temp_label_end;
											} else {
												if (!res) {
													nextOneBarcode = temp_label_head + str2 + temp_date
															+ temp_label_end;
												} else {
													nextOneBarcode = temp_label_head + temp_date + str2
															+ temp_label_end;
												}
											}
											// nextOneBarcode = temp_label_head
											// + temp_date + str2 +
											// temp_label_end;
										}

										Integer resultinsert1 = dao.insert2(nextBarcode, temp_plan_id, lineInId,
												temp_production_id, temp_current_plan_id);
										Integer resultupdate1 = dao.update1(temp_current_plan_id);

										jObject.put("isSuccess", true);
										jObject.put("errMsg", "");
										json.put("barcode", nextBarcode);
										json.put("barcodeNext", nextOneBarcode);
										json.put("labelName", labelName);
										jObject.put("result", json);
										return jObject;

									}
								} else {
									if (temp_print_flag != null && !temp_print_flag.equals("")
											&& temp_print_flag.equals("0")) {
										nextBarcode = temp_old_sn_code;
										jObject.put("isSuccess", true);
										jObject.put("errMsg", "");
										json.put("barcode", nextBarcode);
										json.put("barcodeNext", nextOneBarcode);
										json.put("labelName", labelName);
										jObject.put("result", json);
										return jObject;
									}
								}
								// 生成条码到此结束
							}

						}

					} else {
						// 计划没有开始
						rPrint = 36;
						jObject.put("isSuccess", false);
						jObject.put("errMsg", "计划没有开始");
						json.put("barcode", nextBarcode);
						json.put("barcodeNext", nextOneBarcode);
						json.put("labelName", labelName);
						jObject.put("result", json);
						return jObject;
					}

				}

			}

		}

		if (temp_alarm_mark != null && !temp_alarm_mark.equals("") && temp_alarm_mark == 0) {
			// 计划没有开始
			rPrint = 36;
			jObject.put("isSuccess", false);
			jObject.put("errMsg", "计划没有开始");
			json.put("barcode", nextBarcode);
			json.put("barcodeNext", nextOneBarcode);
			json.put("labelName", labelName);
			jObject.put("result", json);
			return jObject;
		}
		jObject.put("isSuccess", true);
		jObject.put("errMsg", "");
		json.put("barcode", nextBarcode);
		json.put("barcodeNext", nextOneBarcode);
		json.put("labelName", labelName);
		jObject.put("result", json);
		return jObject;
	}

	// 返回年代码
	public String nian() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		String nian = df.format(new Date());// new Date()为获取当前系统时间

		switch (nian) {
		case "2011":
			nian = "1";
			break;
		case "2012":
			nian = "2";
			break;
		case "2013":
			nian = "3";
			break;
		case "2014":
			nian = "4";
			break;
		case "2015":
			nian = "5";
			break;
		case "2016":
			nian = "6";
			break;
		case "2017":
			nian = "7";
			break;
		case "2018":
			nian = "8";
			break;
		case "2019":
			nian = "9";
			break;
		case "2020":
			nian = "A";
			break;
		case "2021":
			nian = "B";
			break;
		case "2022":
			nian = "C";
			break;
		case "2023":
			nian = "D";
			break;
		case "2024":
			nian = "E";
			break;
		case "2025":
			nian = "F";
			break;
		case "2026":
			nian = "G";
			break;
		case "2027":
			nian = "H";
			break;
		case "2028":
			nian = "J";
			break;
		case "2029":
			nian = "K";
			break;
		case "2030":
			nian = "L";
			break;
		case "2031":
			nian = "M";
			break;
		case "2032":
			nian = "N";
			break;
		case "2033":
			nian = "P";
			break;
		case "2034":
			nian = "R";
			break;
		case "2035":
			nian = "S";
			break;
		case "2036":
			nian = "T";
			break;
		case "2037":
			nian = "V";
			break;
		case "2038":
			nian = "W";
			break;
		case "2039":
			nian = "X";
			break;
		case "2040":
			nian = "Y";
			break;

		}
		return nian;
	}

	// 返回月代码
	public String yue() {

		SimpleDateFormat df = new SimpleDateFormat("MM");// 设置日期格式
		// new Date()为获取当前系统时间
		String yue = df.format(new Date());

		switch (yue) {
		case "01":
			yue = "1";
			break;
		case "02":
			yue = "2";
			break;
		case "03":
			yue = "3";
			break;
		case "04":
			yue = "4";
			break;
		case "05":
			yue = "5";
			break;
		case "06":
			yue = "6";
			break;
		case "07":
			yue = "7";
			break;
		case "08":
			yue = "8";
			break;
		case "09":
			yue = "9";
			break;
		case "10":
			yue = "A";
			break;
		case "11":
			yue = "B";
			break;
		case "12":
			yue = "C";
			break;
		}
		return yue;
	}

	// 返回日代码
	public String ri() {
		SimpleDateFormat df = new SimpleDateFormat("dd");// 设置日期格式
		String ri = df.format(new Date());// new Date()为获取当前系统时间

		switch (ri) {
		case "01":
			ri = "1";
			break;
		case "02":
			ri = "2";
			break;
		case "03":
			ri = "3";
			break;
		case "04":
			ri = "4";
			break;
		case "05":
			ri = "5";
			break;
		case "06":
			ri = "6";
			break;
		case "07":
			ri = "7";
			break;
		case "08":
			ri = "8";
			break;
		case "09":
			ri = "9";
			break;
		case "10":
			ri = "A";
			break;
		case "11":
			ri = "B";
			break;
		case "12":
			ri = "C";
			break;
		case "13":
			ri = "D";
			break;
		case "14":
			ri = "E";
			break;
		case "15":
			ri = "F";
			break;
		case "16":
			ri = "G";
			break;
		case "17":
			ri = "H";
			break;
		case "18":
			ri = "J";
			break;
		case "19":
			ri = "K";
			break;
		case "20":
			ri = "L";
			break;
		case "21":
			ri = "M";
			break;
		case "22":
			ri = "N";
			break;
		case "23":
			ri = "P";
			break;
		case "24":
			ri = "R";
			break;
		case "25":
			ri = "S";
			break;
		case "26":
			ri = "T";
			break;
		case "27":
			ri = "V";
			break;
		case "28":
			ri = "W";
			break;
		case "29":
			ri = "X";
			break;
		case "30":
			ri = "Y";
			break;
		case "31":
			ri = "0";
			break;
		}

		return ri;
	}

}
