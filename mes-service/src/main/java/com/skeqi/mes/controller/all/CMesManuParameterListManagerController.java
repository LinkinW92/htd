package com.skeqi.mes.controller.all;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 制造参数清单管理 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesManuParameterListManagerController {

	@Autowired
	CMesMaterialService materialService;
	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;

	/**
	 * 制造参数清单列表
	 */
	@RequestMapping("manuParameterListManager")
	public String manuParameterListManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
			@RequestParam(required = false, defaultValue = "1", value = "page1") Integer page1) {
		JSONObject json = new JSONObject();
		PageHelper.startPage(page, 8);
		String lid = request.getParameter("lid");
		String lno = request.getParameter("lno");
		CMesManufactureParametersT mfp = new CMesManufactureParametersT();
		if(lid!=null&&lid!="") {
			mfp.setLineId(Integer.parseInt(lid));
		}
		if(lno!=null&&lno!="") {
			mfp.setListNo(lno);
		}
		String listNo = request.getParameter("listNo");
		String staName = request.getParameter("staName");
		CMesMfParametersDetailT mf = new CMesMfParametersDetailT();
		if(listNo!=null&&listNo!="") {
			mf.setParameterNo(listNo);
		}
		if(staName!=null&&staName!="") {
			mf.setStationId(Integer.parseInt(staName));
		}


		try {
		List<CMesManufactureParametersT> manuParameterLists = bomService.findAllMFG(mfp);
		PageInfo<CMesManufactureParametersT> pageInfo = new PageInfo<>(manuParameterLists, 5);// 制造参数清单列表

		PageHelper.startPage(page1, 5);
		List<CMesMfParametersDetailT> mfParametersDetailList = bomService.findAllMFGDetail(mf);
		PageInfo<CMesMfParametersDetailT> pageInfo1 = new PageInfo<>(mfParametersDetailList, 5);// 制造参数清单明细列表
		CMesLineT line = new CMesLineT();
		List<CMesLineT> lineList = lineService.findAllLine(line);
		CMesStationT station = new CMesStationT();
		List<CMesStationT> findStation = stationService.findAllStation(station);
		request.setAttribute("lid", lid);
		request.setAttribute("lno", lno);
		request.setAttribute("findStation", findStation);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageInfo1", pageInfo1);
		request.setAttribute("lineList", lineList);
		request.setAttribute("listNo", listNo);
		request.setAttribute("staName", staName);
		}catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "materiel_control/manuParameterListManager";
	}

	/**
	 * 添加制造参数清单
	 *
	 * @throws Exception
	 */
	@RequestMapping("addManuParameterList")
	@ResponseBody
	public  JSONObject addManuParameterList(HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String listNo = request.getParameter("listNo");
		String listName = request.getParameter("listName").trim();
		String lineId = request.getParameter("lineId");
		String effectiveTime = request.getParameter("effectiveTime");
		effectiveTime = effectiveTime + ":00";
		String invalidTime = request.getParameter("invalidTime");
		invalidTime = invalidTime + ":00";
		String listVersion = request.getParameter("listVersion");
		Date starttime = sim.parse(effectiveTime); // 开始时间
		Date endtime = sim.parse(invalidTime); // 开始时间
		boolean before = starttime.before(endtime);
		if (before == false) {
			json.put("code", 99);
			json.put("msg", "开始时间后于结束时间");
			return json;
		}
	try {
		CMesManufactureParametersT mfp = new CMesManufactureParametersT();
		mfp.setListNo(listNo);
		mfp.setLineId(Integer.parseInt(lineId));
		mfp.setListName(listName);
		mfp.setEffectiveTime(sim.parse(effectiveTime));
		mfp.setInvalidTime(sim.parse(invalidTime));
		mfp.setListVersion(Double.parseDouble(listVersion));
		bomService.addMFG(mfp);
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
	 * 通过ID 查询制造参数清单
	 */
	@RequestMapping("toEditManuParameterList")
	@ResponseBody
	public  JSONObject toEditManuParameterList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		try {
		CMesManufactureParametersT manuParameterLists = bomService.findMFGByid(Integer.parseInt(id));
		String effectiveTime = sdf.format(manuParameterLists.getEffectiveTime());
		String invalidTime = sdf.format(manuParameterLists.getInvalidTime());
		json.put("manuParameterList", manuParameterLists);
		json.put("effectiveTime", effectiveTime);
		json.put("invalidTime", invalidTime);
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
	 * 修改制造清单
	 *
	 * @throws Exception
	 */
	@RequestMapping("editManuParameterList")
	@ResponseBody
	public  JSONObject editManuParameterList(HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = request.getParameter("id");
		String listNo = request.getParameter("listNo");
		String listName = request.getParameter("listName").trim();
		String lineId = request.getParameter("lineId");
		String effectiveTime = request.getParameter("effectiveTime");
		effectiveTime = effectiveTime + ":00";
		String invalidTime = request.getParameter("invalidTime");
		invalidTime = invalidTime + ":00";
		String listVersion = request.getParameter("listVersion");
		String hiverson = request.getParameter("hiverson");
		Date starttime = sim.parse(effectiveTime); // 开始时间
		Date endtime = sim.parse(invalidTime); // 开始时间
		boolean before = starttime.before(endtime);
		if (before == false) {
			json.put("code", 99);
			json.put("msg", "开始时间后于结束时间");
			return json;
		}
	try {
		CMesManufactureParametersT mfp = new CMesManufactureParametersT();
		mfp.setId(Integer.parseInt(id));
		mfp.setListNo(listNo);
		mfp.setLineId(Integer.parseInt(lineId));
		mfp.setListName(listName);
		mfp.setEffectiveTime(sim.parse(effectiveTime));
		mfp.setInvalidTime(sim.parse(invalidTime));
		mfp.setListVersion(Double.parseDouble(listVersion));
		bomService.addMFG(mfp);
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
	 * 删除制造清单信息
	 */
	@RequestMapping("delManuParameterList")
	@ResponseBody
	public  JSONObject delManuParameterList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delMFG(Integer.parseInt(id));
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
	 * 添加制造参数清单明细
	 */
	@RequestMapping("addManuParameterListDetail")
	@ResponseBody
	public  Map<String, Object> addManuParameterListDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String mfParametersId = request.getParameter("mfParametersId");
		String parameterNo = request.getParameter("parameterNo");
		String parameterName = request.getParameter("parameterName");
		String parameterMainFlag = request.getParameter("parameterMainFlag");
		String parameterCheck = request.getParameter("parameterCheck");
		String screwNumber = request.getParameter("screwNumber");
		String normalT = request.getParameter("normalT");
		String tUpperLimit = request.getParameter("tUpperLimit");
		String tLowerLimit = request.getParameter("tLowerLimit");
		String normalA = request.getParameter("normalA");
		String aUpperLimit = request.getParameter("aUpperLimit");
		String aLowerLimit = request.getParameter("aLowerLimit");
		String otherMormalT = request.getParameter("otherMormalT");
		String otherUpperLimit = request.getParameter("otherUpperLimit");
		String otherLowerLimit = request.getParameter("otherLowerLimit");
		String parameterReplace = request.getParameter("parameterReplace");
		String staid = request.getParameter("staName");
		CMesMfParametersDetailT mpd = new CMesMfParametersDetailT();
		mpd.setParameterName(parameterName);
		mpd.setMfParametersId(Integer.parseInt(mfParametersId));
		mpd.setParameterNo(parameterNo);
		mpd.setParameterMainFlag(Integer.parseInt(parameterMainFlag));
		mpd.setStationId(Integer.parseInt(staid));
		mpd.setMfParametersId(Integer.parseInt(mfParametersId));
		mpd.setParameterNo(parameterNo);
		mpd.setParameterName(parameterName);
		mpd.setParameterCheck(Integer.parseInt(parameterCheck));
		mpd.setParameterMainFlag(Integer.parseInt(parameterMainFlag));
		mpd.setScrewNumber(Integer.parseInt(screwNumber));
		mpd.setNormalT(normalT);
		mpd.settUpperLimit(tUpperLimit);
		mpd.settLowerLimit(tLowerLimit);
		mpd.setNormalA(normalA);
		mpd.setaLowerLimit(aLowerLimit);
		mpd.setaUpperLimit(aUpperLimit);
		mpd.setOtherLowerLimit(otherLowerLimit);
		mpd.setOtherMormalT(otherMormalT);
		mpd.setOtherUpperLimit(otherUpperLimit);
		mpd.setParameterReplace(parameterReplace);
		try {
			bomService.addMFGDetail(mpd);
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
	 * 通过ID 查询制造参数清单
	 */
	@RequestMapping("toEditManuParameterListDetail")
	@ResponseBody
	public  JSONObject toEditManuParameterListDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesMfParametersDetailT mfParametersDetailList = bomService.findMFGDetailByid(Integer.parseInt(id));
			json.put("mfParametersDetail", mfParametersDetailList);
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
	 * 修改制造清单明细
	 */
	@RequestMapping("editManuParameterListDetail")
	@ResponseBody
	public  JSONObject editManuParameterListDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String mfParametersId = request.getParameter("mfParametersId");
		String parameterNo = request.getParameter("parameterNo");
		String parameterName = request.getParameter("parameterName");
		String parameterMainFlag = request.getParameter("parameterMainFlag");
		String parameterCheck = request.getParameter("parameterCheck");
		String screwNumber = request.getParameter("screwNumber");
		String normalT = request.getParameter("normalT");
		String tUpperLimit = request.getParameter("tUpperLimit");
		String tLowerLimit = request.getParameter("tLowerLimit");
		String normalA = request.getParameter("normalA");
		String aUpperLimit = request.getParameter("aUpperLimit");
		String aLowerLimit = request.getParameter("aLowerLimit");
		String otherMormalT = request.getParameter("otherMormalT");
		String otherUpperLimit = request.getParameter("otherUpperLimit");
		String otherLowerLimit = request.getParameter("otherLowerLimit");
		String parameterReplace = request.getParameter("parameterReplace");
		String staid = request.getParameter("staName");
		CMesMfParametersDetailT mpd = new CMesMfParametersDetailT();
		mpd.setParameterName(parameterName);
		mpd.setMfParametersId(Integer.parseInt(mfParametersId));
		mpd.setParameterNo(parameterNo);
		mpd.setParameterMainFlag(Integer.parseInt(parameterMainFlag));
		mpd.setStationId(Integer.parseInt(staid));
		mpd.setMfParametersId(Integer.parseInt(mfParametersId));
		mpd.setParameterNo(parameterNo);
		mpd.setParameterName(parameterName);
		mpd.setParameterCheck(Integer.parseInt(parameterCheck));
		mpd.setParameterMainFlag(Integer.parseInt(parameterMainFlag));
		mpd.setScrewNumber(Integer.parseInt(screwNumber));
		mpd.setNormalT(normalT);
		mpd.settUpperLimit(tUpperLimit);
		mpd.settLowerLimit(tLowerLimit);
		mpd.setNormalA(normalA);
		mpd.setaLowerLimit(aLowerLimit);
		mpd.setaUpperLimit(aUpperLimit);
		mpd.setOtherLowerLimit(otherLowerLimit);
		mpd.setOtherMormalT(otherMormalT);
		mpd.setOtherUpperLimit(otherUpperLimit);
		mpd.setParameterReplace(parameterReplace);
		mpd.setId(Integer.parseInt(id));
		try {
			bomService.updateMFGDetail(mpd);
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
	 * 删除制造清单明细
	 */
	@RequestMapping("delManuParameterListDetail")
	@ResponseBody
	public  JSONObject delManuParameterListDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delMFGDetail(Integer.parseInt(id));
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
