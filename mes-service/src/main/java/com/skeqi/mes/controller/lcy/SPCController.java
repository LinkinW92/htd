package com.skeqi.mes.controller.lcy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesSPCCoeffic;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.service.lcy.SPCService;

@Controller
@RequestMapping("skq")
public class SPCController {

	@Autowired
	private SPCService spcs;

	@ResponseBody
	@RequestMapping("spcStation")
	//获取spc工位信息
	public JSONObject getSpcStation(){
		JSONObject jo = new JSONObject();
		List<String> stationList = spcs.querySPCStation();
		jo.put("stationList", stationList);
		return jo ;
	}

	@ResponseBody
	@RequestMapping("spcSite")
	public JSONObject getSpcSite(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String spcStation= request.getParameter("spcStation");
		List<String> spcSiteList = spcs.querySPCSite(spcStation);
		jo.put("spcSiteList", spcSiteList);
		return jo;
	}

	@ResponseBody
	@RequestMapping("spcType")
	public JSONObject getSpcType(){
		JSONObject jo = new JSONObject();
		jo.put("t","扭矩" );
		return jo;
	}


	@ResponseBody
	@RequestMapping("spcData")
	public JSONObject getSPCData(HttpServletRequest request){
			JSONObject jo = new JSONObject();
		String station = request.getParameter("station");//获取工位信息
		String site = request.getParameter("site");//获取位置
		//String type = request.getParameter("type");//获取扭矩
		String startTime = request.getParameter("startTime");//获取开始时间
		String endTime = request.getParameter("endTime");
		String limit = request.getParameter("limit");//获取上限
		String floor = request.getParameter("floor");//获取下限
		String targetValue = request.getParameter("targetValue");//获取目标值
		String sampleSize = request.getParameter("sampleSize");//获取目标容量
		String sampleNumber = request.getParameter("sampleNumber");//获取目标数量
		Double sumNumber = 0.0;

		Map<String,Object> varianceMap = new HashMap<>();//
		CMesSPCCoeffic coeffic = spcs.querySPCCoeffic(sampleSize);

		//数据
		List<PMesBoltT> boltList = spcs.querySpcBoltList(station,site,startTime,endTime,sampleNumber);

		if(boltList.size()==0||boltList==null){
			jo.put("flag",false );
			jo.put("msg", "查询无数据");
			return jo;
		}


		List<Double> TList = new ArrayList<>();//扭矩数据
		String head = "<tr>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">编号</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">时间</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">总成</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">工位</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">扭矩值</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">扭矩上限</th>"+
					  "<th class=\"col-sm-1\" style=\"text-align: center;\">螺栓位置</th>"+
					  "</tr>";
		StringBuffer sb = new StringBuffer();
		for (PMesBoltT bolt : boltList) {
			sb.append("<tr>");
			sb.append("<td scope=\"row\">"+bolt.getBoltNum()+"</td>");
			sb.append("<td scope=\"row\">"+GetDate.getDateforSimple(bolt.getDt())+"</td>");
			sb.append("<td scope=\"row\">"+bolt.getSn()+"</td>");
			sb.append("<td scope=\"row\">"+bolt.getSt()+"</td>");
			sb.append("<td scope=\"row\">"+bolt.getT()+"</td>");
			sb.append("<td scope=\"row\">"+bolt.gettLimit()+"</td>");
			sb.append("<td scope=\"row\">"+bolt.getBoltName()+"</td>");
			sb.append("</tr>");
			TList.add(Double.parseDouble(bolt.getT()));//增加扭矩数据
			sumNumber+=Double.parseDouble(bolt.getT());//计算总数
		}

		Integer getSampleSize =Integer.parseInt(sampleSize);//样本每组有多少容量
		Integer getSampleNumber =Integer.parseInt(sampleNumber);//样本数量
		Integer getReallySampleNumber =TList.size();//样本真实数量
		Integer getSampleGroup = getReallySampleNumber/getSampleSize;//样本有多少组

		if(getSampleGroup==0){
			jo.put("flag",false );
			jo.put("msg", "数据过少无法显示");
			return jo;
		}
		Double max = Collections.max(TList);//所有数据的最大值
		Double min = Collections.min(TList);//所有数据的最小值
		Double meanNumber =getDouble(sumNumber/TList.size());//所有数据的平均数
		Double SumVariance = 0.0;//总方差
		for (int i = 0; i < TList.size(); i++) {
			Double variance=Math.pow((TList.get(i)-meanNumber),2);//获取方差
			SumVariance+=variance;
		}
		Double sigma = Math.sqrt((SumVariance/TList.size()));//获取标准差  sigma  (方差数值/n)
		//Double sigmaSt = Math.sqrt((SumVariance/(TList.size()-1)));//获取样本标准差 (方差数值/(n-1))

		Double uclx = meanNumber+3*sigma;//+3倍的sigma 值
		Double lclx = meanNumber-3*sigma;//-3倍的sigma 值


		List<Double> meanNumberList = new ArrayList<>();//每组平均数的集合
		List<Double> rangeList = new ArrayList<>();//每组极差的集合
		List<Double> varianceList = new ArrayList<>();//每组的方差的集合
		List<Integer> getGroupNumber = new ArrayList<>();
		for (int i = 0; i < getSampleGroup; i++) {
			List<Double> groupNumberList = new ArrayList<>();//每个小组的数据
			Double groupNumberSumNumber = 0.0;//每组数的总数
			for (int j = i*getSampleSize+0; j < getSampleSize*(i+1); j++) {
				groupNumberSumNumber+=TList.get(j);//计算所有总数
				groupNumberList.add(TList.get(j));//给小的分组加上数据

			}
			Double groupNumberMeanNumber =getDouble(groupNumberSumNumber/getSampleSize);//计算平均数
			Double groupMaxNumber = getDouble(Collections.max(groupNumberList)) ;//计算分组的最大值
			Double groupMinNumber = getDouble(Collections.min(groupNumberList)) ;//计算分组的最小值
			//计算每组数据的平均方差值
			Double groupSumVariance = 0.0;
			for (int j = 0; j < groupNumberList.size(); j++) {
				Double groupVariance = Math.pow((groupNumberList.get(j)-groupNumberMeanNumber), 2);//获取方差值
				groupSumVariance+=groupVariance;//获取总方差值
			}
			getGroupNumber.add(i+1);


			varianceList.add(getDouble(Math.sqrt(groupSumVariance/(groupNumberList.size()-1))));//增加标准偏差
			rangeList.add(getDouble(groupMaxNumber-groupMinNumber));//增加极差
			meanNumberList.add(getDouble(groupNumberMeanNumber));//增加每组的平均数
		}


		//控制图数据
		Map<String,Object> spcControlChart = new HashMap<>();
		//spcControlChart.put("sigmaP",sigmaSt);


		Map<String,Object> spcRangeChartMap = new HashMap<>();

		Double groupSum = 0.0;
		for (Double num : meanNumberList) {
			groupSum+=num;
		}
		Double rangeSum = 0.0;
		for (Double r : rangeList) {
			rangeSum+=r;
		}
		Double clRRange = rangeSum/rangeList.size();

		Double uclXRange = (groupSum/meanNumberList.size())+coeffic.getA2()*clRRange;
		Double lclXRange = (groupSum/meanNumberList.size())-coeffic.getA2()*clRRange;
		Double uclRRange = coeffic.getD4()*clRRange;//r上限
		Double lclRRange = coeffic.getD3()*clRRange;//r下限




		Double varianceSum = 0.0;
		  for (Double d : varianceList) {
			 varianceSum+=d;
		}

		  //标准偏差
		Double clRVariance = varianceSum/varianceList.size();
		Double clXRange  = getDouble(groupSum/meanNumberList.size());
		Double uclXVariance = clXRange+coeffic.getA3()*clRVariance;
		Double lclXVariance = clXRange-coeffic.getA3()*clRVariance;


		Double uclRVariance = coeffic.getB4()*clRVariance;
		Double lclRVariance = coeffic.getB3()*clRVariance;
		//*************标准偏差



		Double sigmaSt =getDouble(clRRange/coeffic.getD2());



		Double usl = Double.parseDouble(limit);
		Double lsl = Double.parseDouble(floor);

		//n 样本大小   d1 d2  不良数目   p1  不良率

		Double Cp = getDouble((usl-lsl)/(6*sigmaSt)); //过程能力指数
		Double Cr = getDouble((6*sigmaSt)/(usl-lsl));//过程能力比值
		Double Ca = getDouble((Math.abs(((usl+lsl)/2)-meanNumber))/((usl-lsl)/2));//偏移系数   k
		Double Cpu = getDouble((usl-meanNumber)/(3*sigmaSt));//上限过程能力指数
		Double Cpl = getDouble((meanNumber-lsl)/(3*sigmaSt));//下限过程能力指数
		Double Cpk = getDouble(Cp*(1-Ca));//修正的过程能力指数
		Double Cpm = getDouble((usl-lsl)/(6*(Math.sqrt(sigmaSt*sigmaSt+(meanNumber-(usl-lsl))*(meanNumber-(usl-lsl))))));





		Double Pp = getDouble((usl-lsl)/(6*sigma));//过程性能指数
		Double Pr = getDouble((6*sigma)/(usl-lsl));//过程性能比值
		Double Ppu = getDouble((usl-meanNumber)/(3*sigma));//上限过程性能指数
		Double Ppl = getDouble((meanNumber-lsl)/(3*sigma));//下限过程性能指数
		Double Ppk = getDouble(Pp*(1-Ca));//修正的过程性能指数



		List<Double> testList = new ArrayList<>();

		for (int i = 0; i < TList.size(); i++) {
			testList.add(TList.get(i));
		}

		Collections.sort(testList);

		int getL = (int)Math.ceil(max);
		int getF = (int)Math.floor(min);

		String str1 = "";
		String str2 = "";
		String str3 = "";
		String getStringSomeValue = "";

		//*******************************************************************

		if(getL-getF>5){
		int[] getViews = new int[(getL-getF)];
		List<Integer> getTList = new ArrayList<>();
		for (int i = 0; i < getViews.length; i++) {
			getTList.add(getF+i);
		}

		for (int i = 0; i <= getViews.length; i++) {
			for (Double dou: testList) {

				if(dou>getF+i&&dou<getF+i+1){
					getViews[i]=(getViews[i]+1);
				}
			}
		}

		if(Double.parseDouble(floor)>=getTList.get(0)
			&&Double.parseDouble(floor)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

			str1 = "{"
			+ "xAxis: '"+floor.toString()+"'"+
			","+
	    "symbol: 'none',"+
	    "label: {"+
	        "normal: {"+
	            "show: true,"+
	            "formatter: '下限:{c}'"+
	             "   }"+
	            "},"+
	    " lineStyle: {"+
	           " color: '#ff0000',"+
	           " width: 2,"+
	           " type: 'solid'"+
	       " }   "+
	    " },";
		}

		if(Double.parseDouble(limit)>=getTList.get(0)
				&&Double.parseDouble(limit)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

				str2 = "{"
				+ "xAxis: '"+limit.toString()+"'"+
				","+
		    "symbol: 'none',"+
		    "label: {"+
		        "normal: {"+
		            "show: true,"+
		            "formatter: '上限:{c}'"+
		             "   }"+
		            "},"+
		    " lineStyle: {"+
		           " color: '#ff0000',"+
		           " width: 2,"+
		           " type: 'solid'"+
		       " }   "+
		    " },";
			}
		if(Double.parseDouble(targetValue)>=getTList.get(0)
				&&Double.parseDouble(targetValue)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

			str3 = "{"
					+ "xAxis: '"+targetValue.toString()+"'"+
					","+
					"symbol: 'none',"+
					"label: {"+
					"normal: {"+
					"show: true,"+
					"formatter: '目标值:{c}'"+
					"   }"+
					"},"+
					" lineStyle: {"+
					" color: '#ff0000',"+
					" width: 2,"+
					" type: 'solid'"+
					" }   "+
					" },";
		}

		int getNumber = 0;
		for (int i = 0; i < getTList.size(); i++) {

			if(getTList.get(i)-1<meanNumber&&getTList.get(i)>meanNumber){

				getNumber = i;
			}
		}


		 getStringSomeValue ="["+ str1+
		     str2+str3+

		     "  { xAxis: "+(getNumber-1)+","+
		      "  symbol: 'none',"+
		     "   label: {"+
		     "       normal: {"+
		     "           show: true,"+
		     "           formatter: '平均值:"+meanNumber+"'"+
		     "       }"+
		     "   },"+
		     "   lineStyle: {"+
		     "       color: '#ff0000',"+
		     "       width: 2,"+
		     "       type: 'solid'"+
		     "   }"+
		   " }]";

		 spcControlChart.put("getTList",getTList);
		 spcControlChart.put("getViews",getViews);

		}else{
			Double LFNumber=(double)(getL-getF)/10;
			List<Double> getTList = new ArrayList<>();
			int[] getViews = new int[10];
			for (int i = 0; i < 10; i++) {
				getTList.add(getF+LFNumber*i);//添加x坐标
			}

			for (int i = 0; i <= getViews.length; i++) {
				for (Double dou: testList) {

					if(dou>getF+i*LFNumber&&dou<getF+(i+1)*LFNumber){
						getViews[i]=(getViews[i]+1);
					}
				}
			}

			if(Double.parseDouble(floor)>=getTList.get(0)
				&&Double.parseDouble(floor)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

				str1 = "{"
				+ "xAxis: '"+floor.toString()+"'"+
				","+
		    "symbol: 'none',"+
		    "label: {"+
		        "normal: {"+
		            "show: true,"+
		            "formatter: '下限:{c}'"+
		             "   }"+
		            "},"+
		    " lineStyle: {"+
		           " color: '#ff0000',"+
		           " width: 2,"+
		           " type: 'solid'"+
		       " }   "+
		    " },";
			}

			if(Double.parseDouble(limit)>=getTList.get(0)
					&&Double.parseDouble(limit)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

					str2 = "{"
					+ "xAxis: '"+limit.toString()+"'"+
					","+
			    "symbol: 'none',"+
			    "label: {"+
			        "normal: {"+
			            "show: true,"+
			            "formatter: '上限:{c}'"+
			             "   }"+
			            "},"+
			    " lineStyle: {"+
			           " color: '#ff0000',"+
			           " width: 2,"+
			           " type: 'solid'"+
			       " }   "+
			    " },";
				}
			if(Double.parseDouble(targetValue)>=getTList.get(0)
					&&Double.parseDouble(targetValue)<=getTList.get(getTList.size()-1)){//如果下限值大于最小的扭力值

				str3 = "{"
						+ "xAxis: '"+targetValue.toString()+"'"+
						","+
						"symbol: 'none',"+
						"label: {"+
						"normal: {"+
						"show: true,"+
						"formatter: '目标值:{c}'"+
						"   }"+
						"},"+
						" lineStyle: {"+
						" color: '#ff0000',"+
						" width: 2,"+
						" type: 'solid'"+
						" }   "+
						" },";
			}

			int getNumber = 0;
			for (int i = 0; i < getTList.size(); i++) {

				if(getTList.get(i)-1<meanNumber&&getTList.get(i)>meanNumber){

					getNumber = i;
				}
			}


			 getStringSomeValue ="["+ str1+
			     str2+str3+

			     "  { xAxis: "+(getNumber-1)+","+
			      "  symbol: 'none',"+
			     "   label: {"+
			     "       normal: {"+
			     "           show: true,"+
			     "           formatter: '平均值:"+meanNumber+"'"+
			     "       }"+
			     "   },"+
			     "   lineStyle: {"+
			     "       color: '#ff0000',"+
			     "       width: 2,"+
			     "       type: 'solid'"+
			     "   }"+
			   " }]";
			 spcControlChart.put("getTList",getTList);
			 spcControlChart.put("getViews",getViews);

		}







		String getSomeTBody = "<table "
				+ "style=\"margin: auto; font-family:'仿宋','微软雅黑','文泉驿正黑','黑体'; font-weight: bold; font-size: 15px;width: 350px; height: 20px;top: -50px;\">"
				+ "<tr>"
				+ "<td>样本容量:"+getSampleSize+"</td>"
				+ "<td>样本数量:"+getReallySampleNumber+"</td>"
				+ "<td>样本组数:"+getSampleGroup+"</td>"
				+ "</tr></table>";
		String getSpcControlChartTBody = "<table "
				+ "style=\"margin: auto; font-family:'仿宋','微软雅黑','文泉驿正黑','黑体';font-weight: bold; font-size: 17px;width: 200px;\">"
				+"<tr><td> &nbsp; </td></tr>"
				+"<tr><td> &nbsp; </td></tr>"
				+"<tr><td> &nbsp; </td></tr>"
				+"<tr><td> &nbsp; </td></tr>"
				+"<tr><td> &nbsp; </td></tr>"
				+ "<tr>"
				+ "<td>样本容量:"+getSampleSize+"</td>"
				+ "</tr><tr>"
				+ "<td>样本数量:"+getReallySampleNumber+"</td>"
				+ "</tr><tr>"
				+ "<td>样本组数:"+getSampleGroup+"</td>"
				+ "</tr><tr><td>&nbsp;</td>"
				+ "</tr><tr><td>&nbsp;</td>"
				+ "</tr><tr>"
				+ "<td>下限:"+floor+"</td>"
				+ "</tr><tr>"
				+ "<td>目标值:"+targetValue+"</td>"
				+ "</tr><tr>"
				+ "<td>上限:"+limit+"</td>"
				+ "</tr>"
				+ "<tr><td>  &nbsp;  </td></tr>"
				+ "<tr><td>  &nbsp;  </td></tr>"
				+ "<tr>"
				+ "<td>最小值:"+min+"</td>"
				+ "</tr><tr>"
				+ "<td>最大值:"+max+"</td>"
				+ "</tr><tr>"
				+ "<td>平均值:"+meanNumber+"</td>"
				+ "</tr><tr>"

				+ "<tr><td>  &nbsp;  </td></tr>"
				+ "<tr><td>  &nbsp;  </td></tr>"

				+ "<td>σ(LT):"+getDouble(sigma)+"</td>"
				+ "</tr><tr>"
				+ "<td>σ(ST):"+getDouble(sigmaSt)+"</td>"
				+ "</tr><tr>"
				+ "<td>+3σ:"+getDouble(uclx)+"</td>"
				+ "</tr><tr>"
				+ "<td>-3σ:"+getDouble(lclx)+"</td>"
				+ "</tr></table>";


		String getSpcControlChartTBody2 ="<table "
				+"style=\"margin: auto; font-family:'仿宋','微软雅黑','文泉驿正黑','黑体';font-weight: bold; font-size: 17px;width: 850px;\">"
				+ "<tr>"
		+ "<td>PP:"+Pp+"</td>"
		+ "<td>PPU:"+Ppu+"</td>"
		+ "<td>PPL:"+Ppl+"</td>"
		+ "<td>PPK:"+Ppk+"</td>"
		+ "</tr><tr>"
		+ "<td>CP:"+Cp+"</td>"
		+ "<td>CPU:"+Cpu+"</td>"
		+ "<td>CPL:"+Cpl+"</td>"
		+ "<td>CPK:"+Cpk+"</td>"
		+ "</tr></table>";




		spcControlChart.put("getSpcControlChartTBody", getSpcControlChartTBody);
		spcControlChart.put("getSpcControlChartTBody2", getSpcControlChartTBody2);

		//单点极值图
		Map<String,Object> spcPointDiagramMap = new HashMap<>();

		List<Double> singleVarianceList = new ArrayList<>();//单点极差
		singleVarianceList=GetMR(TList);
		Double sumSingleSumVariance=0.0;
		for (Double s: singleVarianceList) {
			sumSingleSumVariance+=s;
		}
		double E2 = 2.659;
		double D4 = 3.267;
		Double clXSingleSumVariance = meanNumber;
		Double clRSingleSumVariance = sumSingleSumVariance/singleVarianceList.size();
		Double uclXSingleSumVariance =meanNumber + E2*clRSingleSumVariance;
		Double lclXSingleSumVariance =meanNumber - E2*clRSingleSumVariance;
		Double uclRSingleSumVariance = D4*clRSingleSumVariance;
		List<Integer> spcPointDiagramNumberList = new ArrayList<>();//显示数量
		for (int i = 0; i <singleVarianceList.size(); i++) {
			spcPointDiagramNumberList.add(i+1);
		}
		spcControlChart.put("getReallySampleNumber",getReallySampleNumber);
		//*******************平均数
		spcRangeChartMap.put("meanNumberList", meanNumberList);//每小组平均数的集合
		spcRangeChartMap.put("rangeList", rangeList);//每小组极差的集合
		spcRangeChartMap.put("uclXRange", getDouble(uclXRange));
		spcRangeChartMap.put("clXRange", getDouble(groupSum/meanNumberList.size()));//统计数据的平均数
		spcRangeChartMap.put("lclXRange", getDouble(lclXRange));
		//***************极差
		spcRangeChartMap.put("uclRRange", getDouble(uclRRange));
		spcRangeChartMap.put("clRRange",getDouble(clRRange));//统计数据极差的平均数
		spcRangeChartMap.put("lclRRange", getDouble(lclRRange));
		spcRangeChartMap.put("getGroupNumber", getGroupNumber);//每小组平均方差的集合

		//平均数
		varianceMap.put("varianceList", varianceList);//添加标准偏差
		varianceMap.put("getGroupNumber",getGroupNumber);//添加小组数
		varianceMap.put("varianceList", varianceList);//每小组平均偏差
		varianceMap.put("meanNumberList", meanNumberList);
		varianceMap.put("uclXRange", getDouble(uclXVariance));
		varianceMap.put("clXRange", getDouble(clXRange));//统计数据的平均数
		varianceMap.put("lclXRange", getDouble(lclXVariance));
		varianceMap.put("clRVariance", getDouble(clRVariance));
		varianceMap.put("uclRVariance", getDouble(uclRVariance));
		varianceMap.put("lclRVariance", getDouble(lclRVariance));

		//单点平均数数据
		spcPointDiagramMap.put("singleMeanList", TList);//单点平均数图
		spcPointDiagramMap.put("uclXSingleSumVariance", getDouble(uclXSingleSumVariance)); //单点极值平均数上限
		spcPointDiagramMap.put("clXSingleSumVariance", getDouble(clXSingleSumVariance)); //单点极值平均数
		spcPointDiagramMap.put("lclXSingleSumVariance", getDouble(lclXSingleSumVariance)); //单点极值平均数下限
		/**
		 * 单点极值数据
		 */
		spcPointDiagramMap.put("singleVarianceList", singleVarianceList);//单点极差数值
		spcPointDiagramMap.put("uclRSingleSumVariance", getDouble(uclRSingleSumVariance));//单点极差数值上限
		spcPointDiagramMap.put("clRSingleSumVariance", getDouble(clRSingleSumVariance));//单点极差数值平均数
		spcPointDiagramMap.put("spcPointDiagramNumberList",spcPointDiagramNumberList);

		jo.put("spcControlChart", spcControlChart);
		jo.put("getSomeTBody",getSomeTBody);
		jo.put("getStringSomeValue", getStringSomeValue);
		jo.put("spcRangeChartMap", spcRangeChartMap);
		jo.put("spcPointDiagramMap", spcPointDiagramMap);
		jo.put("varianceMap",varianceMap);
		jo.put("head", head);//添加表头
		jo.put("body", sb);//添加

		return jo;

	}

	public List<Double> GetMR(List<Double> xList){
			List<Double> mrList = new ArrayList<Double>();
			for (int i = 1; i < xList.size(); i++) {
				mrList.add(Math.abs(xList.get(i)-xList.get(i-1)));
			}
		return mrList;
	}

    public static Double getDouble(Double num){
    	DecimalFormat dForamt = new DecimalFormat("#0.0000");
    	String str = dForamt.format(num);
    	Double temp = Double.valueOf(str);
    	return temp;
	}
}
