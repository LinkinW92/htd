package com.skeqi.mes.controller.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS BOM明细 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesBomDetailsController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;



	/**
	 * BOM明细
	 */
	@RequestMapping("bomDetailsList")
	public String bomDetailsList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		try {
			CMesMaterialMsgT msg = new CMesMaterialMsgT();
			List<CMesMaterialMsgT> materialList = bomService.findAllMaterialMsg(msg);

			String bomName = request.getParameter("bomName");
			String bomId = request.getParameter("bomId");
			CMesBomDetailT bom = new CMesBomDetailT();
			System.out.println("*********************************bomId"+bomId);
			if(bomId!=null&&bomId!="") {
				bom.setBomId(Integer.parseInt(bomId));
			}
			if(bomName!=null&&bomName!="") {

				bom.setBomName(bomName);
			}
			PageHelper.startPage(page, 8);
			List<CMesBomDetailT> bomDetailList = bomService.findAllBomDetail(bom);
			PageInfo<CMesBomDetailT> pageInfo = new PageInfo<>(bomDetailList, 5);
			CMesBomT bomT = new CMesBomT();
			CMesLineT line = new CMesLineT();
			CMesStationT station = new CMesStationT();
			List<CMesBomT> bomList = bomService.findAllBom(bomT);
			List<CMesLineT> findLine = lineService.findAllLine(line);
			List<CMesStationT> findStation = stationService.findAllStation(station);
			request.setAttribute("lineList", findLine);
			request.setAttribute("stationList", findStation);
			request.setAttribute("bomId", bomId);
			request.setAttribute("bomName", bomName);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("bomList", bomList);



			request.setAttribute("materialList", materialList);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "bom_control/bomDetails";
	}

	@RequestMapping("getBomDetail")
	@ResponseBody
	public  JSONObject getBomDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		CMesBoltInfomationT boltInfomation = new CMesBoltInfomationT();
		CMesLeakageInfoT leakageInfo = new CMesLeakageInfoT();
		CMesOtherDataT otherData = new CMesOtherDataT();
		try {
		List<CMesMaterialMsgT> materialList = bomService.findAllMaterialMsg(msg);
		List<CMesBoltInfomationT> boltList = bomService.findAllBolt(boltInfomation);
		List<CMesLeakageInfoT> leakageList = bomService.findAllLeakage(leakageInfo);
		List<CMesOtherDataT> otherDataList = bomService.findAllOther(otherData);
		json.put("otherDataList", otherDataList);
		json.put("materialList", materialList);
		json.put("boltList", boltList);
		json.put("leakageList", leakageList);
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

	/**
	 * 添加BOM明细
	 */
	@RequestMapping("addBomDetails")
	@ResponseBody
	public  JSONObject addBomDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String dataType = request.getParameter("dataType").trim(); // bom类型
		String dataId = request.getParameter("dataId"); // 物料。螺栓。气密id
		String reciveNumber = request.getParameter("reciveNumber").trim(); // 数量
		String traceFlag = request.getParameter("traceFlag"); // 是否追溯
		String bomId = request.getParameter("bomId"); // bom id
		String lineId = request.getParameter("lineId");
		String stationId = request.getParameter("stationId");
		CMesBomDetailT bomDetail = new CMesBomDetailT();
		bomDetail.setBomId(Integer.parseInt(bomId));
		bomDetail.setDataType(Integer.parseInt(dataType));
		bomDetail.setDataId(Integer.parseInt(dataId));
		bomDetail.setReciveNumber(Integer.parseInt(reciveNumber));
		bomDetail.setTraceFlag(traceFlag);
		bomDetail.setLineId(Integer.parseInt(lineId));
		bomDetail.setStationId(Integer.parseInt(stationId));


		try {
			bomService.addBomDetail(bomDetail);
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

	@RequestMapping("toEditBomDetails")
	@ResponseBody
	public  JSONObject toEditBomDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		CMesBoltInfomationT boltInfomation = new CMesBoltInfomationT();
		CMesLeakageInfoT leakageInfo = new CMesLeakageInfoT();
		CMesOtherDataT otherData = new CMesOtherDataT();
		CMesStationT station = new CMesStationT();
		try {
			List<CMesBoltInfomationT> boltList = bomService.findAllBolt(boltInfomation);
			List<CMesLeakageInfoT> leakageList = bomService.findAllLeakage(leakageInfo);
			List<CMesOtherDataT> otherDataList = bomService.findAllOther(otherData);
			List<CMesMaterialMsgT> materialList = bomService.findAllMaterialMsg(msg);
			List<CMesStationT> findStation = stationService.findAllStation(station);
			json.put("otherDataList", otherDataList);
			json.put("materialList", materialList);
			json.put("boltList", boltList);
			json.put("leakageList", leakageList);
			json.put("stationList", findStation);

			String id = request.getParameter("id");
			CMesBomDetailT bomDetail = bomService.findBomDetailByid(Integer.parseInt(id));
			json.put("bomDetail", bomDetail);
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

	/**
	 * 修改BOM明细
	 */
	@RequestMapping("editBomDetails")
	@ResponseBody
	public  JSONObject editBomDetails(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String dataType = request.getParameter("dataType").trim();
		String dataId = request.getParameter("dataId");
		String reciveNumber = request.getParameter("reciveNumber").trim();
		String traceFlag = request.getParameter("traceFlag");
		String bomId = request.getParameter("bomId");
		String id = request.getParameter("id");
		String lineId = request.getParameter("lineId");
		String stationId = request.getParameter("stationId");
		CMesBomDetailT bomDetail = new CMesBomDetailT();
		bomDetail.setId(Integer.parseInt(id));
		bomDetail.setBomId(Integer.parseInt(bomId));
		bomDetail.setDataType(Integer.parseInt(dataType));
		bomDetail.setDataId(Integer.parseInt(dataId));
		bomDetail.setReciveNumber(Integer.parseInt(reciveNumber));
		bomDetail.setTraceFlag(traceFlag);
		bomDetail.setLineId(Integer.parseInt(lineId));
		bomDetail.setStationId(Integer.parseInt(stationId));

		try {
			bomService.updateBomDetail(bomDetail);
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



	/**
	 * 删除BOM明细
	 */
	@RequestMapping("delBomDetails")
	@ResponseBody
	public  JSONObject delBomDetails(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delBomDetail(Integer.parseInt(id));
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

}
