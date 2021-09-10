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
 * 月产量统计
 * @ClassName: CMesMonthOutPutController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "月产量统计", description = "月产量统计", produces = MediaType.APPLICATION_JSON)
public class CMesMonthOutPutController {


	@Autowired
	private GetSomeYieldService gsys;

	@Autowired
	private CMesSystemService cSystemService;


	//得到月产量
	@RequestMapping(value = "/month/getMonthOutPut", method = RequestMethod.POST)
	@ApiOperation(value = "得到月产量", notes = "可根据条件查询信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "time", value = "日期", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")
			 })
	@ResponseBody
	public JSONObject findList(String time, Integer lineId) throws ServicesException {

		String getYear=time.substring(0, 4);//截取年份
		Integer getMonth =Integer.parseInt(time.substring(5,7));//截取月份
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
	@ResponseBody
	@RequestMapping(value = "/month/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		List<CMesLineT> list=gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}
	// 系统数据
	@RequestMapping(value = "/month/findByAll", method = RequestMethod.POST)
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

	@RequestMapping(value = "/month/setMenuData", method = RequestMethod.POST)
	@ApiOperation(value = "修改菜单时间", notes = "修改菜单时间")
	@ResponseBody
	public JSONObject setMenuData(HttpServletRequest request) {
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
