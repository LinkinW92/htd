package com.skeqi.mes.controller.all;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("skq")
/***
 *
 * @author ENS 工位管理 1
 *
 */
public class CMesStationController {

	@Autowired
	CMesStationTService stationService;

	@Autowired
	CMesLineTService cMesLineService;

	/**
	 * 查询所有INDEX、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "station/findAll", method = RequestMethod.POST)
	@ResponseBody
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {
			List<CMesStationT> list = stationService.findStationAll();

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", false);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}



	//根据id查询工位信息
	@RequestMapping("getStationByLine")
	@ResponseBody
	public  JSONObject getStationByLine(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String lineId = request.getParameter("lineId");
		CMesStationT station = new CMesStationT();
		station.setLineId(Integer.parseInt(lineId));
		try {
			List<CMesStationT> stationList = stationService.findAllStation(station);
			json.put("stationList", stationList);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}





	@RequestMapping("delStation")
	@ResponseBody
	public  JSONObject deleteStationById(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			stationService.delStation(Integer.parseInt(id));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}


	//根据id查询工位信息
	@RequestMapping("toEditStation")
	@ResponseBody
	public  JSONObject findStationById(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesStationT station = stationService.findStationByid(Integer.parseInt(id));
			json.put("station", station);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}


	@RequestMapping("/editStation")
	@ResponseBody
	public  JSONObject editStation(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id5");
		String stationName = request.getParameter("stationName5").trim();// 工位名称
		String stationIndex = request.getParameter("stationIndex5").trim();// 工位下标
		String stationTime = request.getParameter("stationTime5").trim();// 工位节拍
		String lineId = request.getParameter("lineId5");// �?属产�?
		String stationType = request.getParameter("stationType5");// 工位类型�?1.线内站，2线外站）
		String stationReviewornot = request.getParameter("stationReviewornot5");// 是否追溯 �?1.�? �?0.否）
		String stationPrintornot = request.getParameter("stationPrintornot5");// 是否打印
		String stationEndornot = request.getParameter("stationEndornot5");// 是否末站
		String stationAutoornot = request.getParameter("stationAutoornot5");// 站业务属性（1.手动站，2.自动站，3.测试站）
		String stationProcessok = request.getParameter("stationProcessok5");// 是否流程�?�?
		String stationDataok = request.getParameter("stationDataok5");// 是否数据�?�?
		String stationRecipeornot = request.getParameter("stationRecipeornot5");// 是否�?要配�?
		String stationAgvornot = request.getParameter("stationAgvornot5");// 是否�?要AGV
		String stationRequstoutline = request.getParameter("stationRequstoutline5");// 是否出站校验
		String stationRequstin = request.getParameter("stationRequstin5");// 是否请求进站
		String stationUploadmes = request.getParameter("stationUploadmes5");// 是否上传MES
		String stationGunornot = request.getParameter("stationGunornot5");// 是否有拧紧枪
		String stationLightornot = request.getParameter("stationLightornot5");// 是否点亮放行�?
		String stationScanderFlag = request.getParameter("stationScanderFlag5");
		String stationCcdFlag = request.getParameter("stationCcdFlag5");
		String stationRubberFlag = request.getParameter("stationRubberFlag5");
		String stationLeakageFlag = request.getParameter("stationLeakageFlag5");
		String stationEolFlag = request.getParameter("stationEolFlag5");
		String stationAdamFlag = request.getParameter("stationAdamFlag5");
		String stationPlcFlag = request.getParameter("stationPlcFlag5");
		CMesStationT station = new CMesStationT();
		station.setId(Integer.parseInt(id));
		station.setStationName(stationName);
		station.setStationIndex(Integer.parseInt(stationIndex));
		station.setStationTime(Integer.parseInt(stationTime));
		station.setLineId(Integer.parseInt(lineId));
		station.setStationType(Integer.parseInt(stationType));
		station.setStationReviewornot(Integer.parseInt(stationReviewornot));
		station.setStationPrintornot(Integer.parseInt(stationPrintornot));
		station.setStationEndornot(Integer.parseInt(stationEndornot));
		station.setStationAutoornot(Integer.parseInt(stationAutoornot));
		station.setStationProcessok(Integer.parseInt(stationProcessok));
		station.setStationDataok(Integer.parseInt(stationDataok));
		station.setStationRecipeornot(Integer.parseInt(stationRecipeornot));
		station.setStationAgvornot(Integer.parseInt(stationAgvornot));
		station.setStationRequstoutline(Integer.parseInt(stationRequstoutline));
		station.setStationRequstin(Integer.parseInt(stationRequstin));
		station.setStationUploadmes(Integer.parseInt(stationUploadmes));
		station.setStationGunornot(Integer.parseInt(stationGunornot));
		station.setStationLightornot(Integer.parseInt(stationLightornot));
		station.setStationScanderFlag(Integer.parseInt(stationScanderFlag));
		station.setStationCcdFlag(Integer.parseInt(stationCcdFlag));
		station.setStationRubberFlag(Integer.parseInt(stationRubberFlag));
		station.setStationLeakageFlag(Integer.parseInt(stationLeakageFlag));
		station.setStationEolFlag(Integer.parseInt(stationEolFlag));
		station.setStationAdamFlag(Integer.parseInt(stationAdamFlag));
		station.setStationPlcFlag(Integer.parseInt(stationPlcFlag));


		try {
			stationService.updateStationold(station);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;

	}

	@RequestMapping("addStation")
	@ResponseBody
	public  JSONObject addStation(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String stationName = request.getParameter("stationName").trim();// 工位名称
		String stationIndex = request.getParameter("stationIndex").trim();// 工位下标
		String stationTime = request.getParameter("stationTime").trim();// 工位节拍
		String lineId = request.getParameter("lineId");// �?属产�?
		String stationType = request.getParameter("stationType");// 工位类型�?1.线内站，2线外站）
		String stationReviewornot = request.getParameter("stationReviewornot");// 是否追溯 �?1.�? �?0.否）
		String stationPrintornot = request.getParameter("stationPrintornot");// 是否打印
		String stationEndornot = request.getParameter("stationEndornot");// 是否末站
		String stationAutoornot = request.getParameter("stationAutoornot");// 站业务属性（1.手动站，2.自动站，3.测试站）
		String stationProcessok = request.getParameter("stationProcessok");// 是否流程�?�?
		String stationDataok = request.getParameter("stationDataok");// 是否数据�?�?
		String stationRecipeornot = request.getParameter("stationRecipeornot");// 是否�?要配�?
		String stationAgvornot = request.getParameter("stationAgvornot");// 是否�?要AGV
		String stationRequstoutline = request.getParameter("stationRequstoutline");// 是否出站校验
		String stationRequstin = request.getParameter("stationRequstin");// 是否请求进站
		String stationUploadmes = request.getParameter("stationUploadmes");// 是否上传MES
		String stationGunornot = request.getParameter("stationGunornot");// 是否有拧紧枪
		String stationLightornot = request.getParameter("stationLightornot");// 是否点亮放行�?
		String stationScanderFlag = request.getParameter("stationScanderFlag");
		String stationCcdFlag = request.getParameter("stationCcdFlag");
		String stationRubberFlag = request.getParameter("stationRubberFlag");
		String stationLeakageFlag = request.getParameter("stationLeakageFlag");
		String stationEolFlag = request.getParameter("stationEolFlag");
		String stationAdamFlag = request.getParameter("stationAdamFlag");
		String stationPlcFlag = request.getParameter("stationPlcFlag");
		CMesStationT station = new CMesStationT();
		station.setStationName(stationName);
		station.setStationIndex(Integer.parseInt(stationIndex));
		station.setStationTime(Integer.parseInt(stationTime));
		station.setLineId(Integer.parseInt(lineId));
		station.setStationType(Integer.parseInt(stationType));
		station.setStationReviewornot(Integer.parseInt(stationReviewornot));
		station.setStationPrintornot(Integer.parseInt(stationPrintornot));
		station.setStationEndornot(Integer.parseInt(stationEndornot));
		station.setStationAutoornot(Integer.parseInt(stationAutoornot));
		station.setStationProcessok(Integer.parseInt(stationProcessok));
		station.setStationDataok(Integer.parseInt(stationDataok));
		station.setStationRecipeornot(Integer.parseInt(stationRecipeornot));
		station.setStationAgvornot(Integer.parseInt(stationAgvornot));
		station.setStationRequstoutline(Integer.parseInt(stationRequstoutline));
		station.setStationRequstin(Integer.parseInt(stationRequstin));
		station.setStationUploadmes(Integer.parseInt(stationUploadmes));
		station.setStationGunornot(Integer.parseInt(stationGunornot));
		station.setStationLightornot(Integer.parseInt(stationLightornot));
		station.setStationScanderFlag(Integer.parseInt(stationScanderFlag));
		station.setStationCcdFlag(Integer.parseInt(stationCcdFlag));
		station.setStationRubberFlag(Integer.parseInt(stationRubberFlag));
		station.setStationLeakageFlag(Integer.parseInt(stationLeakageFlag));
		station.setStationEolFlag(Integer.parseInt(stationEolFlag));
		station.setStationAdamFlag(Integer.parseInt(stationAdamFlag));
		station.setStationPlcFlag(Integer.parseInt(stationPlcFlag));

		try {
			stationService.addStation(station);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}



	@RequestMapping("productionLine")
	public String productionLineList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {

		JSONObject json = new JSONObject();
		CMesStationT station = new CMesStationT();
		String lineId = request.getParameter("lineId");
		CMesLineT line = new CMesLineT();
		try {

			List<CMesLineT> lineList = cMesLineService.findAllLine(line);
			if(lineId!=null) {
				station.setLineId(Integer.parseInt(lineId));
				line.setId(Integer.parseInt(lineId));
			}else {
				if(lineList.size()!=0) {
					station.setLineId(lineList.get(0).getId());

				}

			}
			PageHelper.startPage(page, 8);
			List<CMesStationT> stationList = stationService.findAllStation(station);
			PageInfo<CMesStationT> pageInfo = new PageInfo<>(stationList, 5);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("lineList", lineList);
			if(lineId!=null && lineId!=""){
				request.setAttribute("lineId", lineId);
			}else{
				request.setAttribute("lineId", lineList.get(0).getId());
			}
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());

		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return "base_control/productionLine";
	}


}
