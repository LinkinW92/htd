package com.skeqi.mes.controller.lcy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.service.lcy.UpdataBoltViewSomeDataService;

@Controller
@RequestMapping("skq")
public class UpdataBoltViewSomeDataController {

	@Autowired
	private UpdataBoltViewSomeDataService ubv;

	@ResponseBody
	@RequestMapping("queryRecipeDatilBolt")
	public JSONObject queryRecipeDatil(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		if(id==null||id==""){
			jo.put("flag",false);
			jo.put("msg", "非法进入螺栓配置页面");
			return jo;
		}
		CMesRecipeDatilT rd = ubv.queryRecipeDatil(Integer.parseInt(id));
		jo.put("rd",rd);
		return jo;
	}
	@ResponseBody
	@RequestMapping("updataViewBoltData")
	public JSONObject updataViewBoltData(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		String jsonStr = request.getParameter("jsonStr");

		ubv.updataViewBoltData(id,jsonStr);
		jo.put("code", 0);
		jo.put("msg","保存成功");
		return jo;
	}



}
