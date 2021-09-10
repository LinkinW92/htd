package com.skeqi.mes.controller.lcy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.service.lcy.GetFlawRateService;

@Controller
@RequestMapping("skq")
public class GetFlawRateController{

	@Autowired
	private GetFlawRateService getFlawRateService;

	@RequestMapping("getFlawRate")
	@ResponseBody
	public JSONObject getFlawRateController(HttpServletRequest req){
		JSONObject jo = new JSONObject();
		String str=req.getParameter("date");
		int lineId = Integer.parseInt(req.getParameter("getLine"));
		String getTime = str.substring(0,10);
		Date date = GetDate.getYearMonthDay(getTime);
		int monthDays = GetDate.getMonthDays(date);//获取当前月有多少天
		date = GetDate.getMonthFirstDay(date);//获取当前月第一天
		Date endDate = GetDate.getNextDay(date);//获取下一天的日期
		System.out.println(date);
		System.out.println(endDate);
		List<Double> flawProductionRateList = new ArrayList<>();
		List<Integer> monthDaysList = new ArrayList<>();
		for(int i=1;i<=monthDays;i++){
			monthDaysList.add(i);
			int productionNumber = getFlawRateService.getProductionNumber(date,endDate,lineId);//获取产品数量
			int flawProductionNumber = getFlawRateService.getFlawProductionNumber(date,endDate,lineId);//获取缺陷产品数量
			double flawProductionRate = 0;
			if(productionNumber!=0){
				flawProductionRate = flawProductionNumber/(double)productionNumber*100;
			}
			flawProductionRateList.add(flawProductionRate);

		}

		jo.put("monthDaysList", monthDaysList);
		jo.put("flawProductionRateList", flawProductionRateList);
		return jo ;
	}





}
