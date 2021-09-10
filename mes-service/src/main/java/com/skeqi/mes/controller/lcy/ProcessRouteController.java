package com.skeqi.mes.controller.lcy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.lcy.ProcessRouteService;
import com.skeqi.mes.util.ToolUtils;


@Controller
@RequestMapping("skq")
public class ProcessRouteController {



	@Autowired
	ProcessRouteService service;

	//初始化产线
	@RequestMapping("processRouteLine")
	@ResponseBody
	public JSONObject getInitProcessRouteLine(){
		JSONObject jo = new JSONObject();
		List<CMesLineT> lineList = service.getLineList();
		jo.put("lineList",lineList);
		return jo;
	}

	//初始化产品
	@RequestMapping("getProduction")
	@ResponseBody
	public JSONObject getInitProcessProduction() {
		JSONObject jo = new JSONObject();

		List<CMesProductionT> productionList=service.getProductionByLineId();
		jo.put("productionList",productionList);
		return jo;
	}

	//根据产线id  获取对应的工艺路线名称和id
	@RequestMapping("processRouteName")
	@ResponseBody
	public JSONObject getRoutingByLineID(String processLineID,String productionId){
		JSONObject jo = new JSONObject();
		List<CMesRoutingT> routingList = service.getRoutingByLineID(processLineID,productionId);
		jo.put("routingList", routingList);

		return jo;
	}



	@RequestMapping("queryProcessRoute")
	@ResponseBody
	public JSONObject queryProcessRoute(String lineID,String routingID,String productionId) {
		JSONObject jo = new JSONObject();

		//根据产线和产品查询有无工艺路线
		//查询工艺路线 routing  表
		int routingNumber = service.queryProcessRouteRoutingNumber(lineID,routingID,productionId);
		//查询工艺路线  production_way  表
		int wayNumber = service.queryProcessRouteWayNumber(routingID);

		List<CMesStationT> stationList = service.queryStationList(lineID);

		if(stationList.size()==0) {

			jo.put("flag",2);
			jo.put("msg","该产品没有工位,请添加后再配置");

			return jo;

		}

		if(routingNumber!=0&&wayNumber!=0) {

			jo.put("flag",1);
			CMesRoutingT data = service.queryProcessRouteRoutingData(lineID,routingID);
			jo.put("data",data);

		}else if(routingNumber==0||wayNumber==0) {

			jo.put("flag",0);
			jo.put("msg","该产品没有配置工艺路线");
			CMesRoutingT data =new CMesRoutingT();
			data.setRoute(getProcessRouteJson(stationList));
			jo.put("data",data);
		}else {

			jo.put("flag",0);
			jo.put("msg","该产品工艺路线存在问题,请重新配置");
			jo.put("data",getProcessRouteJson(stationList));
		}
		return jo;
	}

	@SuppressWarnings("unused")
	@RequestMapping("saveProcessRoute")
	@ResponseBody
	public JSONObject saveProcessRoute(HttpServletRequest request, String lineID,String routingID,String routingName,String processRouteData,String productionId,String defaultRoute) {
		JSONObject jo = new JSONObject();
		try {
			if(routingID==null||routingID==""){

				//添加routing 的工艺路线
				routingID = service.saveProcessRouting(lineID,routingName,processRouteData,productionId,defaultRoute)+"";
				//添加way 工艺路线
				jo = service.saveProcessWay(lineID, routingID, processRouteData);

					if(!"1".equals(jo.get("flag").toString().trim())){

//						service.deleteProcessRouteRoutingData(lineID, routingID);

					}
				}else{

				jo = service.saveProcessWay(lineID, routingID, processRouteData);

				if("1".equals(jo.get("flag").toString().trim())){
					//编辑工艺路线的方法
					service.updateProcessRouteRoutingData(lineID,routingID,processRouteData);
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("flag",0);
			jo.put("msg", "连接异常");

		}

		return jo;
	}


	@RequestMapping("deleteProcessRoute")
	@ResponseBody
	public JSONObject deleteProcessRoute(String lineID,String routingID) {
		JSONObject jo = new JSONObject();
		try {

//			service.deleteProcessRouteRoutingData(lineID, routingID);
		jo.put("flag",1);
		jo.put("msg", "清空工艺路线成功");

		}catch(Exception e) {

			jo.put("flag", 0);
			jo.put("msg","清空工艺路线失败");

		}
		return jo;
	}


	//获取工位初始化字符串
		public static String getProcessRouteJson(List<CMesStationT> stationList) {

			StringBuilder states = new StringBuilder("{states:{rect1:{type:'start',text:{text:'开始'}, attr:{ x:50, y:260, width:50, height:50}, props:{text:{value:'开始'},temp1:{value:'start'},temp2:{value:'工艺路线的开始'}}},");

			for(int i=1;i<=stationList.size();i++) {


					states.append("rect"+(i+1)+":{type:'task',text:{text:'"+stationList.get(i-1).getStationName()+"'}, attr:{ x:"+(150+(((i-1)%7)*150))+", y:"+(260+(((i-1)/7)*100))+", width:100, height:50}, props:{text:{value:'"+stationList.get(i-1).getStationName()+"'},assignee:{value:'"+stationList.get(i-1).getId()+"'},form:{value:'"+stationList.get(i-1).getStationIndex()+"'},desc:{value:'普通工位'}}},");


			}
			states.append("rect"+(stationList.size()+2)+":{type:'end',text:{text:'结束'}, attr:{ x:50, y:360, width:50, height:50}, props:{text:{value:'结束'},temp1:{value:'end'},temp2:{value:'工艺路线的结束'}}}},paths:{},props:{props:{}}}");

			return states.toString();
		}


		@RequestMapping("updateDefaultProcess")
		@ResponseBody
		public JSONObject updateDefaultProcess(String routingId,String lineId,String productionId) {


			JSONObject jo = new JSONObject();

			try {

				if(routingId!=null&&!"".equals(routingId)) {
					service.updateDefaultProcess( routingId, lineId, productionId);
					jo.put("flag",1);
					jo.put("msg", "更新默认路线成功");
				}else {

					jo.put("flag", 0);
					jo.put("msg","更新默认路线失败,请重新查询后再进行更新");

				}

			}catch(Exception e) {

				jo.put("flag", 0);
				jo.put("msg","更新默认路线失败");

			}


			return jo;
		}

}
