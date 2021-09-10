package com.skeqi.mes.controller.lcy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.common.lcy.GetRandomColor;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.PMesStation;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;
import com.skeqi.mes.service.lcy.GetSomeYieldService;

/*
 * 获取一些报表产量的信息
 *
 */
@Controller
@RequestMapping("skq")
public class GetSomeYieldController {
	@Autowired
	private GetSomeYieldService gsys;


	/**
	 * 设备使用率
	 */

	@ResponseBody
	@RequestMapping("getEquipmentUsageRate")
	public JSONObject getEquipmentUsageRate(HttpServletRequest request){

		JSONObject jo = new JSONObject();
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//获取产线id
		Integer productionValue = Integer.parseInt(request.getParameter("productionValue"));//理论生产量
		String str=request.getParameter("getTime");//获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		List<Integer> monthList = new ArrayList<>();//每月有多少天


		//设备利用率=每天实际产量/ 每天理论产量×100%

		List<BigDecimal> getEquipmentUsageRate = new ArrayList<>();//合格率列表
		if(getMonth==2&&getYear%4==0){//润年二月
			for (int i = 1; i <=29; i++) {
					monthList.add(i);
					String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
					String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
					Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
					BigDecimal getEquipmentValue=null;
					BigDecimal num=new BigDecimal(productNumber);//当天产量
					//当天产量/当天理论产量 *100% 获取设备利用率
					getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
					getEquipmentUsageRate.add(getEquipmentValue);
				}
		}else if(getMonth==2&&getYear%4!=0){//平年二月
			for (int i = 1; i <=28; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				BigDecimal getEquipmentValue=null;
				BigDecimal num=new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
				}

		}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
			for (int i = 1; i <=30; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				BigDecimal getEquipmentValue=null;
				BigDecimal num=new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}

		}else{//31天的月份
			for (int i = 1; i <=31; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				BigDecimal getEquipmentValue=null;
				BigDecimal num=new BigDecimal(productNumber);
				getEquipmentValue = num.divide(new BigDecimal(productionValue), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
				getEquipmentUsageRate.add(getEquipmentValue);
			}
		}
		jo.put("getEquipmentUsageRate", getEquipmentUsageRate);
		jo.put("monthList", monthList);
		return jo;

	}











	//工位时间统计的 初始化产线
	@ResponseBody
	@RequestMapping("getLine")
	public JSONObject getLine(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		List<CMesLineT> list=gsys.getLine();
		jo.put("getLine", list);
		return jo;

	}



	//工位时间统计
	@ResponseBody
	@RequestMapping("getStationTimeStatistics")
	public JSONObject getStationTimeStatistics(HttpServletRequest request){
		String str = request.getParameter("getDate");
		String pageValue = request.getParameter("getLine");
		String getStartTime=null;
		String getEndTime =null;
		if(str!=null&&str!=""){
			String getTime=str.substring(0, 10);
			getStartTime= getTime+" 0:00:00";
			getEndTime = getTime +" 23:59:59";
		}
		JSONObject jo = new JSONObject();
		//获取工位信息
		List<String> stList = gsys.getStList();

		List<PMesStation> pMesList = new ArrayList<>();
		for (String stName : stList) {

			List<PMesStationPassEqStatusT> list =gsys.getStationTimeStatistics(getStartTime,getEndTime,pageValue,stName);
			Integer stopTime = 0;//停机时间
			Integer lackTime = 0;//待料时间
			Integer runTime = 0;//运行时间
			Integer faultTime = 0;//故障时间
			Integer wallTime =0;//堵料时间
			Integer elseTime = 0;//其他时间
			PMesStation pMesStation = new PMesStation();
			for (int i =0;i<list.size();i++) {
				PMesStationPassEqStatusT pMesStationPassEqStatusT = list.get(i);

				if(pMesStationPassEqStatusT.getEqStatusType()==0){//正常
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						runTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
							runTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
							String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
								runTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);
						}
					}
				}

				if (pMesStationPassEqStatusT.getEqStatusType()==1){//停机
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						stopTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
								stopTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
								String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
								stopTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);


						}
					}
				}

				if (pMesStationPassEqStatusT.getEqStatusType()==2){//故障
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						faultTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
							faultTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
							String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
							faultTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);

						}
					}
				}
				if (pMesStationPassEqStatusT.getEqStatusType()==3){//缺料
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						lackTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
							lackTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
							String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
							lackTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);


						}
					}
				}
				if (pMesStationPassEqStatusT.getEqStatusType()==4){//堵料
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						wallTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
							wallTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
							String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
							wallTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);

						}
					}
				}
				if (pMesStationPassEqStatusT.getEqStatusType()==5){//其他
					if((list.size()-1)!=i){
						PMesStationPassEqStatusT pMesStationPassEqStatusT2 = list.get(i+1);
						elseTime +=gsys.getSomeDate(pMesStationPassEqStatusT.getId(),pMesStationPassEqStatusT2.getId());
					}else{
						if(GetDate.getYearMonthDay(new Date()).equals(GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt()))){
							elseTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),GetDate.getDateforSimple(new Date()));
						}else{
							String getTime = GetDate.getYearMonthDay(pMesStationPassEqStatusT.getDt())+" 23:59:59";
							elseTime +=gsys.addSomeDate(GetDate.getDateforSimple(pMesStationPassEqStatusT.getDt()),getTime);
						}
					}
				}
				pMesStation.setEqName(pMesStationPassEqStatusT.getEqName());
			}

			if(lackTime>=3600){
				pMesStation.setLackTime(lackTime/3600+"时"+(lackTime%3600)/60+"分"+lackTime%60+"秒");
			}else if(lackTime>=60){
				pMesStation.setLackTime(lackTime/60+"分"+lackTime%60+"秒");
			}else{
				pMesStation.setLackTime(lackTime+"秒");
			}

			if(runTime>=3600){
				pMesStation.setRunTime(runTime/3600+"时"+(runTime%3600)/60+"分"+runTime%60+"秒");
			}else if(runTime>=60){
				pMesStation.setRunTime(runTime/60+"分"+runTime%60+"秒");
			}else{
				pMesStation.setRunTime(runTime+"秒");
			}

			if(stopTime>=3600){
				pMesStation.setStopTime(stopTime/3600+"时"+(stopTime%3600)/60+"分"+stopTime%60+"秒");
			}else if(stopTime>=60){
				pMesStation.setStopTime(stopTime/60+"分"+stopTime%60+"秒");
			}else{
				pMesStation.setStopTime(stopTime+"秒");
			}

			if(faultTime>=3600){
				pMesStation.setFaultTime(faultTime/3600+"时"+(faultTime%3600)/60+"分"+faultTime%60+"秒");
			}else if(faultTime>=60){
				pMesStation.setFaultTime(faultTime/60+"分"+faultTime%60+"秒");
			}else{
				pMesStation.setFaultTime(faultTime+"秒");
			}

			if(wallTime>=3600){
				pMesStation.setWallTime(wallTime/3600+"时"+(wallTime%3600)/60+"分"+wallTime%60+"秒");
			}else if(wallTime>=60){
				pMesStation.setWallTime(wallTime/60+"分"+wallTime%60+"秒");
			}else{
				pMesStation.setWallTime(wallTime+"秒");
			}
			if(elseTime>=3600){
				pMesStation.setElseTime(elseTime/3600+"时"+(elseTime%3600)/60+"分"+elseTime%60+"秒");
			}else if(elseTime>=60){
				pMesStation.setElseTime(elseTime/60+"分"+elseTime%60+"秒");
			}else{
				pMesStation.setElseTime(elseTime+"秒");
			}
			pMesStation.setSt(stName);
			if(pMesStation.getEqName()!=null&&pMesStation.getEqName()!=""){
				pMesList.add(pMesStation);
			}

		}
		jo.put("pMesList", pMesList);
		if(pMesList.size()==0){
			jo.put("pMesKey","数据为空");
		}

		return jo;
	}








	//一次通过率
	@ResponseBody
	@RequestMapping("onePassRate")
	public JSONObject onePassRate(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String str = request.getParameter("getYearAndMonth");
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		List<BigDecimal> getRates = new ArrayList<>();
		List<Integer> getNumbers = new ArrayList<>();
		String getStartDate = null;
		String getEndDate = null;
		if(getMonth==2&&getYear%4==0){//润年二月
			getStartDate = getYear+"-"+getMonth+"-1 00:00:00";//得到开始该月的1号
			getEndDate = getYear+"-"+getMonth+"-29 23:59:59";//得到本月月末的时间
			int productNumber=gsys.getMonthYeild(getStartDate,getEndDate,null,lineId);//产品数量
			if(productNumber!=0){
				getRates.add(new BigDecimal("0"));
				getNumbers.add(0);
			}

			int index=(int) Math.ceil((double)productNumber/50);
			Integer qualifiedNumber = null;
			int j =0;
			if(index>10){
				j=index;

			}else{

				j=10;
			}


			for (int i = 1; i <=j; i++) {
				if(i==index){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,productNumber,lineId);//一次合格品的数量
					getNumbers.add(productNumber);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}
				if(i<=j){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,50*i,lineId);//一次合格品的数量
					getNumbers.add(50*i);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(50*i), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}



				//计算 比率
				}
			jo.put("getRates", getRates);
			jo.put("getNumbers", getNumbers);
		}else if(getMonth==2&&getYear%4!=0){//平年二月
			getStartDate = getYear+"-"+getMonth+"-1 00:00:00";//得到开始该月的1号
			getEndDate = getYear+"-"+getMonth+"-28 23:59:59";//得到本月月末的时间
			int productNumber=gsys.getMonthYeild(getStartDate,getEndDate,null,lineId);//产品数量
			if(productNumber!=0){
				getRates.add(new BigDecimal("0"));
				getNumbers.add(0);
			}
			int index=(int) Math.ceil((double)productNumber/50);
			Integer qualifiedNumber = null;
			int j =0;
			if(index>10){
				j=index;

			}else{

				j=10;
			}


			for (int i = 1; i <=j; i++) {
				if(i==index){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,productNumber,lineId);//一次合格品的数量
					getNumbers.add(productNumber);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}
				if(i<=j){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,50*i,lineId);//一次合格品的数量
					getNumbers.add(50*i);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(50*i), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}



				//计算 比率
				}
			jo.put("getRates", getRates);
			jo.put("getNumbers", getNumbers);
		}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
			getStartDate = getYear+"-"+getMonth+"-1 00:00:00";//得到开始该月的1号
			getEndDate = getYear+"-"+getMonth+"-30 23:59:59";//得到本月月末的时间
			int productNumber=gsys.getMonthYeild(getStartDate,getEndDate,null,lineId);//产品数量
			if(productNumber!=0){
				getRates.add(new BigDecimal("0"));
				getNumbers.add(0);
			}
			int index=(int) Math.ceil((double)productNumber/50);
			Integer qualifiedNumber = null;
			int j =0;
			if(index>10){
				j=index;

			}else{

				j=10;
			}


			for (int i = 1; i <=j; i++) {
				if(i==index){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,productNumber,lineId);//一次合格品的数量
					getNumbers.add(productNumber);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}
				if(i<=j){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,50*i,lineId);//一次合格品的数量
					getNumbers.add(50*i);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(50*i), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}



				//计算 比率
				}
			jo.put("getRates", getRates);
			jo.put("getNumbers", getNumbers);
		}else{//31天的月份
			getStartDate = getYear+"-"+getMonth+"-1 00:00:00";//得到开始该月的1号
			getEndDate = getYear+"-"+getMonth+"-31 23:59:59";//得到本月月末的时间
			int productNumber=gsys.getMonthYeild(getStartDate,getEndDate,null,lineId);//产品数量
			if(productNumber!=0){
				getRates.add(new BigDecimal("0"));
				getNumbers.add(0);
			}
			int index=(int) Math.ceil((double)productNumber/50);
			Integer qualifiedNumber = null;
			int j =0;
			if(index>10){
				j=index;

			}else{
				j=10;
			}

			for (int i = 1; i <=j; i++) {
				if(i==index){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,productNumber,lineId);//一次合格品的数量
					getNumbers.add(productNumber);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}
				if(i<=j){
					qualifiedNumber =gsys.getPMesTrackingTList(getStartDate,getEndDate,50*i,lineId);//一次合格品的数量
					getNumbers.add(50*i);
					BigDecimal qualifiedRate=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualifiedNumber);//一次合格品的数量
						qualifiedRate=number1.divide(new BigDecimal(50*i), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

						getRates.add(qualifiedRate);

					}else{
						qualifiedRate = new BigDecimal("0");
						getRates.add(qualifiedRate);
					}
				}
				//计算 比率
				}
			jo.put("getRates", getRates);
			jo.put("getNumbers", getNumbers);
		}
		return jo;
	}


	//limitDay 限制天数  这次循环多少次
	//monthDay 这个月最多有多少天
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






	//拧紧合格率
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("tightenRate")
	public JSONObject getTightenRate(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String str=request.getParameter("getTime");//获取时间和月份 //yyyy-mm-dd
		String getLine = request.getParameter("getLine");//获取产线id
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		Integer getDay =Integer.parseInt(str.substring(8,10));//截取天
		List<Integer> timeList = new ArrayList<>();//有多少天


		List<String> strs = gsys.getProductST(getLine);//获取工位名称
		@SuppressWarnings("rawtypes")
		List<String> colorList = new ArrayList();

		String getStartDate = null;
		String getEndDate = null;
		if(strs.size()!=0){
			if(strs.size()<=3){//显示3个工位   一个月
				if(getMonth==2&&getYear%4==0){//润年二月
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if(strs.get(i)!=null&&strs.get(i)!=""){
						List<Double> getQualifiedRate = new ArrayList<>();
						for (int j = 1; j <=29; j++) {
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

				}else if(getMonth==2&&getYear%4!=0){//平年二月
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if(strs.get(i)!=null&&strs.get(i)!=""){
						List<Double> getQualifiedRate = new ArrayList<>();
						for (int j = 1; j <=28; j++) {
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
				}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if(strs.get(i)!=null&&strs.get(i)!=""){
						List<Double> getQualifiedRate = new ArrayList<>();
						for (int j = 1; j <=30; j++) {
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
				}else{
					for (int i = 0; i < strs.size(); i++) {
						colorList.add(GetRandomColor.getRandomColor());
						if(strs.get(i)!=null&&strs.get(i)!=""){
						List<Double> getQualifiedRate = new ArrayList<>();
						for (int j = 1; j <=31; j++) {
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


			}else if(strs.size()<=7){//显示5个工位   半个月
				if(getMonth==2&&getYear%4==0){//润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 29, 12,colorList);
				}
				else if(getMonth==2&&getYear%4!=0){//平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 28, 12,colorList);
				}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 30, 12,colorList);

				}else{//31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 31, 12,colorList);
				}
			}else if(strs.size()<=15){//显示10个工位   一周
				if(getMonth==2&&getYear%4==0){//润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 29, 6,colorList);
				}else if(getMonth==2&&getYear%4!=0){//平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 28, 6,colorList);
				}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 30, 6,colorList);
				}else{//31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 31, 6,colorList);
				}
			}else if(strs.size()<=31){//显示20个工位  三天
				if(getMonth==2&&getYear%4==0){//润年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 29, 3,colorList);
				}else if(getMonth==2&&getYear%4!=0){//平年二月
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 28, 3,colorList);
				}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 30, 3,colorList);
				}else{//31天的天数
					getTightenRateValue(jo, strs, getDay, getYear, getMonth, getStartDate, getEndDate, gsys, timeList, 31, 3,colorList);

				}
			}else{//显示一天
				for (int i = 0; i < strs.size(); i++) {
					colorList.add(GetRandomColor.getRandomColor());
					if(strs.get(i)!=null&&strs.get(i)!=""){
					List<Double> getQualifiedRate = new ArrayList<>();
						 getStartDate = getYear+"-"+getMonth+"-"+getDay+" 0:00:00";
						 getEndDate=getYear+"-"+getMonth+"-"+getDay+" 23:59:59";
						 Double qualifiedRate = gsys.someBoltProduct(strs.get(i),getStartDate,getEndDate);//获取产
							getQualifiedRate.add(qualifiedRate);

							if(i==0){
								timeList.add(getDay);
							}


					jo.put("getQualifiedRate"+i,getQualifiedRate);
					}
				}
			}
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<strs.size();i++){
				sb.append("{name: '"+strs.get(i)+"', type: 'bar', barWidth: 13,itemStyle:{ normal:{color:'"+colorList.get(i)+"'}},"+
						"label: {normal: {show: true,position: 'top'}},"+
						"data: "+(jo.getObject("getQualifiedRate"+i, List.class))+"},");
			}
			String getStr = sb.toString().substring(0, sb.toString().length()-1);
			jo.put("getStr",getStr);
			jo.put("timeList", timeList);
	}
		return jo;


	}










	//oee 内部调用的方法
	public static void queryEquipmentOEEValue(List<BigDecimal> getOEEValueList,String getStartDate,
			String getEndDate,Integer lineId,Integer productionValue,GetSomeYieldService gsys,Integer getDay,int i,JSONObject jo){
		Integer getTheoryTime = 0;//获取理论工作时间
		List<CMesShiftsTeamT> getShiftsTeamList = gsys.getShiftsTeamList(lineId);//获取排班列表
		for (CMesShiftsTeamT cMesShiftsTeamT : getShiftsTeamList) {
			String[] strs2 = cMesShiftsTeamT.getStartTime().split(":");
			String[] strs1 = cMesShiftsTeamT.getEndTime().split(":");
			if(Integer.parseInt(strs1[0])-Integer.parseInt(strs2[0])>0){
				getTheoryTime+=(Integer.parseInt(strs1[0])-Integer.parseInt(strs2[0]))*60*60;//获取小时的  秒数
				getTheoryTime+=(Integer.parseInt(strs1[1])-Integer.parseInt(strs2[1]))*60;//获取分钟的秒数
			}else if (Integer.parseInt(strs1[0])-Integer.parseInt(strs2[0])==0&&
					Integer.parseInt(strs1[1])-Integer.parseInt(strs2[1])>0){
				getTheoryTime+=(Integer.parseInt(strs1[0])-Integer.parseInt(strs2[0]))*60*60;//获取小时的  秒数
				getTheoryTime+=(Integer.parseInt(strs1[1])-Integer.parseInt(strs2[1]))*60;//获取分钟的秒数
			}else{//结束时间小于开始时间   也就是说 从第一天去上班到了第二天
				getTheoryTime+=(Integer.parseInt(strs1[0])-Integer.parseInt(strs2[0]))*60*60;//获取小时的  秒数
				getTheoryTime+=(Integer.parseInt(strs1[1])-Integer.parseInt(strs2[1]))*60;//获取分钟的秒数
				getTheoryTime+=24*60*60;
			}
		}
		Integer getActualTime = gsys.getTime(getStartDate,getEndDate,lineId);//获取实际时间
		if(getActualTime==null){
			getActualTime = 0;
		}
		//时间开动率   即  实际工作时间/理论工作时间
		Double availability = 0.0;
		if(getTheoryTime>0){
			availability = (double)getActualTime/(double)getTheoryTime;
		}

		Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
		Integer qualificationNumber=gsys.getHourQualified(getStartDate,getEndDate,2,lineId);//获取当天的合格品的数量  2为合格品
		//性能开动率    实际产量/理论产量
		Double  performance = (double)productNumber/(double)productionValue;


		//合格品率   即合格品的数量/产品的数量
		Double quality = 0.0;
		if(productNumber!=0){
			quality = (double)qualificationNumber/(double)productNumber;
		}
		BigDecimal num=new BigDecimal(availability);
		BigDecimal getOEEValue =num.multiply(new BigDecimal(performance)).multiply(new BigDecimal(quality)).multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_DOWN);
		getOEEValueList.add(getOEEValue);
		if(i==getDay){
			jo.put("availability", GetDate.getNumber(availability*100));
			jo.put("performance", GetDate.getNumber(performance*100));
			jo.put("quality", GetDate.getNumber(quality*100));
			jo.put("getOEEValue", getOEEValue);
		}
	}





	//设备oee
	@ResponseBody
	@RequestMapping("queryEquipmentOEE")
	public JSONObject queryEquipmentOEE(HttpServletRequest request){

		JSONObject jo = new JSONObject();
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		Integer productionValue = Integer.parseInt(request.getParameter("productionValue"));//理论生产量
		String str=request.getParameter("getTime");//获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		Integer getDay = Integer.parseInt(str.substring(8,10));//截取天
		List<Integer> monthList = new ArrayList<>();//每月有多少天

		//设备综合效率   oee算法是 (每天实际工作时间/理论工作时间)*（每天生产的产量/实际产量）*（每天合格产量/实际产量）*100%
		List<BigDecimal> getOEEValueList = new ArrayList<>();//合格率列表
		if(getMonth==2&&getYear%4==0){//润年二月
			for (int i = 1; i <=29; i++) {
					monthList.add(i);
					String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
					String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
					queryEquipmentOEEValue(getOEEValueList,getStartDate,
							getEndDate,lineId,productionValue,gsys, getDay, i, jo);
				}

		}else if(getMonth==2&&getYear%4!=0){//平年二月
			for (int i = 1; i <=28; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				queryEquipmentOEEValue(getOEEValueList,getStartDate,
						getEndDate,lineId,productionValue,gsys, getDay, i, jo);
				}

		}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
			for (int i = 1; i <=30; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				queryEquipmentOEEValue(getOEEValueList,getStartDate,
						getEndDate,lineId,productionValue,gsys, getDay, i, jo);
			}

		}else{//31天的月份
			for (int i = 1; i <=31; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				queryEquipmentOEEValue(getOEEValueList,getStartDate,
						getEndDate,lineId,productionValue,gsys, getDay, i, jo);
			}
		}
		jo.put("getOEEValueList", getOEEValueList);
		jo.put("monthList", monthList);
		return jo;

	}






















	//整体合格率统计

	@ResponseBody
	@RequestMapping("getQualificationRate")
	public JSONObject getQualificationRate(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String str=request.getParameter("getYearAndMonth");//获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份和月份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		List<Integer> monthList = new ArrayList<>();//每月有多少天
		List<BigDecimal> valueList = new ArrayList<>();//合格率列表
		if(getMonth==2&&getYear%4==0){//润年二月
			for (int i = 1; i <=29; i++) {
					monthList.add(i);
					String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
					String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
					Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
					Integer qualificationNumber=gsys.getHourQualified(getStartDate,getEndDate,2,lineId);//获取当天的合格品的数量  2为合格品
					BigDecimal qualification=null;
					if(productNumber!=0){
						BigDecimal number1=new BigDecimal(qualificationNumber);//合格品数量
						//合格率
						qualification=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
						valueList.add(qualification);
					}else{
						qualification=new BigDecimal(0);
						valueList.add(qualification);
					}

				}

		}else if(getMonth==2&&getYear%4!=0){//平年二月
			for (int i = 1; i <=28; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				Integer qualificationNumber=gsys.getHourQualified(getStartDate,getEndDate,2,lineId);//获取当天的合格品的数量  2为合格品
				BigDecimal qualification=null;
				if(productNumber!=0){
					BigDecimal number1=new BigDecimal(qualificationNumber);//合格品数量
					//合格率
					qualification=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
					valueList.add(qualification);
				}else{
					qualification=new BigDecimal(0);
					valueList.add(qualification);
				}

				}

		}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){//30天的月份
			for (int i = 1; i <=30; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				Integer qualificationNumber=gsys.getHourQualified(getStartDate,getEndDate,2,lineId);//获取当天的合格品的数量  2为合格品
				BigDecimal qualification=null;
				if(productNumber!=0){
					BigDecimal number1=new BigDecimal(qualificationNumber);//合格品数量
					//合格率
					qualification=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
					valueList.add(qualification);
				}else{
					qualification=new BigDecimal(0);
					valueList.add(qualification);
				}


			}

		}else{//31天的月份
			for (int i = 1; i <=31; i++) {
				monthList.add(i);
				String getStartDate = getYear+"-"+getMonth+"-"+i+" 0:00:00";
				String getEndDate=getYear+"-"+getMonth+"-"+i+" 23:59:59";
				Integer productNumber=gsys.getHourQualified(getStartDate,getEndDate,null,lineId);//获取当天的产品的数量
				Integer qualificationNumber=gsys.getHourQualified(getStartDate,getEndDate,2,lineId);//获取当天的合格品的数量  2为合格品
				BigDecimal qualification=null;
				if(productNumber!=0){
					BigDecimal number1=new BigDecimal(qualificationNumber);//合格品数量
					//合格率
					qualification=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
					valueList.add(qualification);
				}else{
					qualification=new BigDecimal(0);
					valueList.add(qualification);
				}

			}
		}
		jo.put("monthList", monthList);
		jo.put("qualificationRate", valueList);
		return jo;
	}
	/*//得到日产量 *
	@ResponseBody
	@RequestMapping("getDayYeildQualified")
	public JSONObject getDayYeildQualified(HttpServletRequest request){
		String str=request.getParameter("getYearMonthDay");
		String getYearMonthDay=str.substring(0, 10) ;//获取年份和月份
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		JSONObject jo = new JSONObject();
		List<Integer> getHourQualified = new ArrayList<>();
		for(int i = 0;i<24;i++){

				String getStartDate = getYearMonthDay+" "+i+":00:00";
				String getEndDate=getYearMonthDay+" "+i+":59:59";
				int index=gsys.getHourQualified2(getStartDate,getEndDate,null,lineId);
				getHourQualified.add(index);

		}
		jo.put("getDayMonthDay", getHourQualified);
		return jo;
	}


	//废品率 *
	@ResponseBody
	@RequestMapping("getScrapRate")
	public JSONObject getScrapRate(HttpServletRequest request){
		String str=request.getParameter("getTime");
		String getTime=str.substring(0, 10) ;
		JSONObject jo = new JSONObject();
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		String getStartTime = getTime+" 0:00:00";//开始时间
		String getEndTime = getTime+" 23:59:59";//结束时间
		Integer productNumber=gsys.getHourQualified(getStartTime,getEndTime,null,lineId);//获取当天的产品的数量
		Integer getValue = 1;
		Integer scrapRateNumber=gsys.getHourQualified(getStartTime,getEndTime,getValue,lineId);//获取当天的报废品数量

		BigDecimal scrapRate=null;

		//计算 比率
		if(productNumber!=0){
			BigDecimal number1=new BigDecimal(scrapRateNumber);//报废品数量
			scrapRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

		}else{
			scrapRate=new BigDecimal(0);
		}
		jo.put("scrapRate", scrapRate);
		return jo;
	}*/




	//日产量统计 *
	@ResponseBody
	@RequestMapping("getMonthAndDayYield")
	public JSONObject getMonthAndDayYeild(HttpServletRequest request){
		String str=request.getParameter("getYearAndMonth");//获取时间和月份 //yyyy-mm-dd
		Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		JSONObject jo = new JSONObject();
		List<Integer> dayLearies = new  ArrayList<>();//日报废量
		List<Integer> dayYield = new  ArrayList<>();//日产量
		Integer getValue = 1;//1为报废品数量     //null 为 日产量
		List<Integer> dayNumber =new ArrayList<>();
		//yield.dayNumber
		String getYearMonthDay=str.substring(0, 10) ;//获取年份和月份
		List<Integer> getHourQualified = new ArrayList<>();
		for(int i = 0;i<24;i++){

				String getStartDate = getYearMonthDay+" "+i+":00:00";
				String getEndDate=getYearMonthDay+" "+i+":59:59";
				int index=gsys.getHourQualified2(getStartDate,getEndDate,null,lineId);
				getHourQualified.add(index);

		}
		jo.put("getDayMonthDay", getHourQualified);

		String getStartData = getYearMonthDay+" 0:00:00";//开始时间
		String getEndData = getYearMonthDay+" 23:59:59";//结束时间
		Integer productNumber=gsys.getHourQualified(getStartData,getEndData,null,lineId);//获取当天的产品的数量
		Integer scrapRateNumber=gsys.getHourQualified(getStartData,getEndData,1,lineId);//获取当天的报废品数量

		BigDecimal scrapRate=null;

		//计算 比率
		if(productNumber!=0){
			BigDecimal number1=new BigDecimal(scrapRateNumber);//报废品数量
			scrapRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

		}else{
			scrapRate=new BigDecimal(0);
		}
		jo.put("scrapRate", scrapRate);




		if(getMonth==12){
			for(int i=1;i<=31;i++){
				String getStartTime = null;
				String getEndTime =null;
				if(i!=31){
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+getMonth+"-"+(i+1);

				}else{
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = (getYear+1)+"-"+1+"-"+1;//另一年的1月1日

				}
				Integer LearieskList=gsys.getEveryDayYeild( getStartTime,getEndTime,getValue,lineId);//报废品
				Integer YieldList=gsys.getEveryDayYeild( getStartTime,getEndTime,null,lineId);//产品
				dayLearies.add(LearieskList);////报废品数量
				dayYield.add(YieldList);//产品数量
				dayNumber.add(i);//每月天数

			}

		}else if(getYear%4==0&&getMonth==2){//闰年二月
			for(int i=1;i<=29;i++){
				String getStartTime = null;
				String getEndTime =null;
				if(i!=29){
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+getMonth+"-"+(i+1);

				}else{
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+(getMonth+1)+"-"+1;//3月1日

				}
				Integer LearieskList=gsys.getEveryDayYeild( getStartTime,getEndTime,getValue,lineId);//报废品
				Integer YieldList=gsys.getEveryDayYeild( getStartTime,getEndTime,null,lineId);//产品
				dayLearies.add(LearieskList);////报废品数量
				dayYield.add(YieldList);//产品数量
				dayNumber.add(i);//每月天数
			}

		}else if(getYear%4!=0&&getMonth==2){//平年二月

			for(int i=1;i<=28;i++){
				String getStartTime = null;
				String getEndTime =null;
				if(i!=28){
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+getMonth+"-"+(i+1);

				}else{
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+(getMonth+1)+"-"+1;//3月1日

				}
				Integer LearieskList=gsys.getEveryDayYeild( getStartTime,getEndTime,getValue,lineId);//报废品
				Integer YieldList=gsys.getEveryDayYeild( getStartTime,getEndTime,null,lineId);//产品
				dayLearies.add(LearieskList);////报废品数量
				dayYield.add(YieldList);//产品数量
				dayNumber.add(i);//每月天数
			}


		}else if(getMonth==4||getMonth==6||getMonth==9||getMonth==11){
			for(int i=1;i<=30;i++){
				String getStartTime = null;
				String getEndTime =null;
				if(i!=30){
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+getMonth+"-"+(i+1);

				}else{
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+(getMonth+1)+"-"+1;

				}
				Integer LearieskList=gsys.getEveryDayYeild( getStartTime,getEndTime,getValue,lineId);//报废品
				Integer YieldList=gsys.getEveryDayYeild( getStartTime,getEndTime,null,lineId);//产品
				dayLearies.add(LearieskList);////报废品数量
				dayYield.add(YieldList);//产品数量
				dayNumber.add(i);//每月天数
			}




		}else {
			for(int i=1;i<=31;i++){
				String getStartTime = null;
				String getEndTime =null;
				if(i!=31){
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+getMonth+"-"+(i+1);

				}else{
					getStartTime = getYear+"-"+getMonth+"-"+i;
					getEndTime = getYear+"-"+(getMonth+1)+"-"+1;

				}
				Integer LearieskList=gsys.getEveryDayYeild( getStartTime,getEndTime,getValue,lineId);//报废品
				Integer YieldList=gsys.getEveryDayYeild( getStartTime,getEndTime,null,lineId);//产品
				dayLearies.add(LearieskList);////报废品数量
				dayYield.add(YieldList);//产品数量
				dayNumber.add(i);//每月天数
			}


		}

		jo.put("dayYield", dayYield);//每天的产量
		jo.put("dayLearies", dayLearies);//每天的报废品数量
		jo.put("dayNumber",dayNumber);//每月的天数
		return jo;

	}




	/*//月气密性 *
		@ResponseBody
		@RequestMapping("getMonthLeakage")
		public JSONObject getMonthLeakage(HttpServletRequest request){
			String str=request.getParameter("getYearAndMonth");//获取时间和月份 //yyyy-mm-dd
			Integer getYear = Integer.parseInt(str.substring(0,4));//截取年份
			Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
			Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
			String getStartYearAndMonth = "";
			String getEndYearAndMonth="";
			if(getMonth!=12){
				getStartYearAndMonth = getYear+"-"+getMonth+"-1";//得到开始该月的1号
				getEndYearAndMonth = getYear+"-"+(getMonth+1)+"-1";//得到下个月的1号   只要是在这两个日期之间的产量数 就是该月的产量
				}else{
					getStartYearAndMonth = getYear+"-"+getMonth+"-1";//得到开始该月的1号
					getEndYearAndMonth = (getYear+1)+"-1-1";//得到下个月的1号   只要是在这两个日期之间的产量数 就是该月的产量

				}
			Integer getValue =2;//1为报废品数量     //null 为 日产量 //2为指定日期合格量 //3为指定日期不合格量
			Integer leakage = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,getValue,lineId);//指定日期合格量
			getValue = 3;
			Integer unLeakage = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,getValue,lineId);//指定日期不合格量

			Integer productNumber = leakage+unLeakage;//月产量

			JSONObject jo = new JSONObject();
			jo.put("leakage", leakage);//指定日期合格量
			jo.put("unLeakage", unLeakage);//指定日期不合格量

			BigDecimal leakageRate=null;//合格率
			if(productNumber!=0){
				BigDecimal number1=new BigDecimal(leakage);//合格品的数量
				leakageRate=number1.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

			}else{
				leakageRate= new BigDecimal(0);
			}
			jo.put("leakageRate", leakageRate);//产品合格率


			BigDecimal unLeakageRate=null;//不合格率
			if(unLeakage!=0){
				BigDecimal number2=new BigDecimal(unLeakage);//产品不合格品的数量

				unLeakageRate=number2.divide(new BigDecimal(productNumber), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

			}else{
				unLeakageRate= new BigDecimal(0);
			}
			jo.put("unLeakageRate", unLeakageRate);//产品不合格率





			return jo;
		}*/





/*
	//月合格量统计
	@ResponseBody
	@RequestMapping("getMonthPercentOfPass")
	public JSONObject getMonthPercentOfPass(HttpServletRequest request){
		String str=request.getParameter("getYear");
		Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
		String getYear=str.substring(0, 4);//截取年份
		Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
		String getStartYearAndMonth = null;
		String getEndYearAndMonth=null;
		Integer getValue =1;//1为合格产品数量    //null 产品数量
		if(getMonth!=12){
			getStartYearAndMonth = getYear+"-"+getMonth+"-1";//开始时间为当月第一号 默认为0：00：00
			getEndYearAndMonth = getYear+"-"+(getMonth+1)+"-1";//结束时间为下一个月第一号 默认为0：00：00
			}else{
				getStartYearAndMonth = getYear+"-"+getMonth+"-1";//开始时间为当月第一号 默认为0：00：00
				getEndYearAndMonth = (getYear+1)+"-1-1";//结束时间为第二年的1月1号  默认为0：00：00

			}
		Integer monthQualified = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,null,lineId);//获取产品量
		Integer unQualified = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,getValue,lineId);//获取不合格品数量
		Integer qualified = monthQualified -unQualified;//产品合格量=产品量减去不合格品数量

		JSONObject jo = new JSONObject();
		jo.put("qualified",qualified);//获取合格品
		jo.put("unQualified", unQualified);//获取不合格品
		BigDecimal qualifiedRate=null;//计算合格率
		if(monthQualified!=0){
			BigDecimal number1=new BigDecimal(qualified);//
			//用bigdecimal 计算合格率  保留两位小数， 最后乘以100
			qualifiedRate=number1.divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

		}else{
			qualifiedRate= new BigDecimal(0);
		}
		jo.put("qualifiedRate", qualifiedRate);
		BigDecimal unQualifiedRate=null;//计算不合格率
		if(monthQualified!=0){
			BigDecimal number2=new BigDecimal(unQualified);
			//用bigdecimal 计算合格率  保留两位小数， 最后乘以100
			unQualifiedRate=number2.divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

		}else{
			unQualifiedRate= new BigDecimal(0);
		}
		jo.put("unQualifiedRate", unQualifiedRate);

		return jo;
	}
	*/

	//得到月产量 *
		@ResponseBody
		@RequestMapping("getMonthYield")
		public JSONObject getEveryMonthYeild(HttpServletRequest request){
			String str=request.getParameter("getYear");
			Integer lineId = Integer.parseInt(request.getParameter("getLine"));//产线id
			String getYear=str.substring(0, 4);//截取年份
			Integer getMonth =Integer.parseInt(str.substring(5,7));//截取月份
			JSONObject jo = new JSONObject();
			List<Integer> list = new  ArrayList<>();
			List<String> monthList = new ArrayList<>();
			String getStartYearAndMonth = null;
			String getEndYearAndMonth = null;
			for(int i =1;i<=12;i++){
				if(i!=12){
				getStartYearAndMonth = getYear+"-"+i+"-1";//得到开始该月的1号
				getEndYearAndMonth = getYear+"-"+(i+1)+"-1";//得到下个月的1号   只要是在这两个日期之间的产量数 就是该月的产量
				int index=gsys.getMonthYeild(getStartYearAndMonth,getEndYearAndMonth,null,lineId);
				monthList.add(i+"月");
				list.add(index);
				}else{
					getStartYearAndMonth = getYear+"-"+i+"-1";//得到开始该月的1号
					getEndYearAndMonth = (Integer.parseInt(getYear)+1)+"-"+1+"-1";//得到下个月的1号   只要是在这两个日期之间的产量数 就是该月的产量
					int index=gsys.getMonthYeild(getStartYearAndMonth,getEndYearAndMonth,null,lineId);
					monthList.add(i+"月");
					list.add(index);
				}



				if(i==getMonth){
					//第三个参数为null时 为产品数量   1为不合格品数量  2为指定日期合格量 //3为指定日期不合格量
					Integer monthQualified = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,null,lineId);//获取产品量
					Integer unQualified = gsys.getMonthYeild(getStartYearAndMonth, getEndYearAndMonth,1,lineId);//获取不合格品数量
					Integer leakage = gsys.getMonthLeakage(getStartYearAndMonth, getEndYearAndMonth,1,lineId);//指定日期气密性合格量
					Integer unLeakage = gsys.getMonthLeakage(getStartYearAndMonth, getEndYearAndMonth,2,lineId);//指定日期气密性不合格量

					jo.put("leakage", leakage);//指定日期合格量
					jo.put("unLeakage", unLeakage);//指定日期不合格量
					BigDecimal leakageRate=null;//合格率
					if(monthQualified!=0){
						BigDecimal number1=new BigDecimal(leakage);//合格品的数量
						leakageRate=number1.divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

					}else{
						leakageRate= new BigDecimal(0);
					}
					jo.put("leakageRate", leakageRate);//产品合格率


					BigDecimal unLeakageRate=null;//不合格率
					if(unLeakage!=0){
						BigDecimal number2=new BigDecimal(unLeakage);//产品不合格品的数量

						unLeakageRate=number2.divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));

					}else{
						unLeakageRate= new BigDecimal(0);
					}
					jo.put("unLeakageRate", unLeakageRate);//产品不合格率

					Integer qualified = monthQualified -unQualified;//产品合格量=产品量减去不合格品数量
					jo.put("qualified",qualified);//获取合格品
					jo.put("unQualified", unQualified);//获取不合格品
					BigDecimal qualifiedRate=null;//计算合格率
					if(monthQualified!=0){
						BigDecimal number1=new BigDecimal(qualified);//
						//用bigdecimal 计算合格率  保留两位小数， 最后乘以100
						qualifiedRate=number1.multiply(new BigDecimal(100)).divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN);

					}else{
						qualifiedRate= new BigDecimal(0);
					}
					jo.put("qualifiedRate", qualifiedRate);
					BigDecimal unQualifiedRate=null;//计算不合格率
					if(monthQualified!=0){
						BigDecimal number2=new BigDecimal(unQualified);
						//用bigdecimal 计算合格率  保留两位小数， 最后乘以100
						unQualifiedRate=number2.multiply(new BigDecimal(100)).divide(new BigDecimal(monthQualified), 2, BigDecimal.ROUND_HALF_DOWN);

					}else{
						unQualifiedRate= new BigDecimal(0);
					}
					jo.put("unQualifiedRate", unQualifiedRate);
				}
			}
			jo.put("someList", list);//月产量
			jo.put("monthList",monthList);
			return jo;

		}
}
