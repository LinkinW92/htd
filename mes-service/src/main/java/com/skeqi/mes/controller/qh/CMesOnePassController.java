package com.skeqi.mes.controller.qh;

import java.math.BigDecimal;
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
 * 一次性通过率
 *
 * @ClassName: CMesOnePassController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "一次性通过率", description = "一次性通过率", produces = MediaType.APPLICATION_JSON)
public class CMesOnePassController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;


	@RequestMapping(value = "/onePass/get", method = RequestMethod.POST)
	@ApiOperation(value = "得到一次性通过率", notes = "根据多条件得到一次性通过率")
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

	@ResponseBody
	@RequestMapping(value = "/onePass/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/onePass/sessionConversation", method = RequestMethod.POST)
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
	@RequestMapping(value = "/onePass/findByAll", method = RequestMethod.POST)
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


}
