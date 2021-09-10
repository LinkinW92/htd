package com.skeqi.mes.controller.qh;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/MaterialMsgMController", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料信息管理", description = "物料信息管理", produces = MediaType.APPLICATION_JSON)
public class CMesMaterialMsgMController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesStationTService stationService;

	// 查询物料信息列表
	@RequestMapping(value = "/findMaterialMsgList", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料信息列表", notes = "查询物料信息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "station", value = "工位", required = false, dataType = "Integer") })
	@ResponseBody
	public JSONObject findMaterialMsgList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize) {
		JSONObject json = new JSONObject();
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		try {

			String station = request.getParameter("station");
			if (station != null && !station.equals("")) {
				msg.setStationName(station);
			}
			PageHelper.startPage(pageNum, pageSize);
			List<CMesMaterialMsgT> materialList = bomService.findAllMaterialMsg(msg);
			PageInfo<CMesMaterialMsgT> pageInfo = new PageInfo<>(materialList, 5);
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	/**
	 * 查询所有工位
	 *
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询所有工位", notes = "查询所有工位")
	@RequestMapping(value = "findAllStation", method = RequestMethod.POST)
	public JSONObject findAllStation(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		JSONObject json = new JSONObject();
		try {

			CMesStationT st = new CMesStationT();
			List<CMesStationT> stationList = stationService.findAllStation(st);
			json.put("code", 0);
			json.put("stationList", stationList);
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	/**
	 * 新增物料信息
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 * @throws ParseException
	 */
	@ApiOperation(value = "新增物料信息", notes = "新增物料信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "materialName", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialType", value = "类型0-一般物料1-虚拟总成2-二级总成", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialVr", value = "校验规则", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialComeType", value = "来料类型 0-外购2-自给3-其他", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialSupplier", value = "供应商", required = false, paramType = "query"),
			@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "addMaterialMsg", method = RequestMethod.POST)
	public JSONObject addMaterialMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException, ParseException {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("materialName").trim();
		String st = request.getParameter("st");
		String materialType = request.getParameter("materialType");
		String materialVr = request.getParameter("materialVr");
		String materialComeType = request.getParameter("materialComeType");
		String materialSupplier = request.getParameter("materialSupplier");
		String uploadCode = request.getParameter("uploadCode");
		String dis = request.getParameter("dis");
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		msg.setDis(dis);
		msg.setMaterialComeType(materialComeType);
		msg.setMaterialName(materialName);
		msg.setMaterialSupplier(materialSupplier);
		msg.setMaterialType(Integer.parseInt(materialType));
		msg.setMaterialVr(materialVr);
		msg.setSt(Integer.parseInt(st));
		msg.setUploadCode(uploadCode);
		try {
			bomService.addMaterialMsg(msg);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;

	}

	/**
	 * 修改
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "修改物料信息", notes = "修改物料信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialName", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialType", value = "类型0-一般物料1-虚拟总成2-二级总成", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialVr", value = "校验规则0-外购2-自给3-其他", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialComeType", value = "来料类型", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialSupplier", value = "供应商", required = false, paramType = "query"),
			@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
	@Transactional
	@RequestMapping(value = "updateMaterialMsg", method = RequestMethod.POST)
	public JSONObject updateMaterialMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("materialName").trim();
		String st = request.getParameter("st");
		String materialType = request.getParameter("materialType");
		String materialVr = request.getParameter("materialVr");
		String materialComeType = request.getParameter("materialComeType");
		String materialSupplier = request.getParameter("materialSupplier");
		String uploadCode = request.getParameter("uploadCode");
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		msg.setId(Integer.parseInt(id));
		msg.setDis(dis);
		msg.setMaterialComeType(materialComeType);
		msg.setMaterialName(materialName);
		msg.setMaterialSupplier(materialSupplier);
		msg.setMaterialType(Integer.parseInt(materialType));
		msg.setMaterialVr(materialVr);
		msg.setSt(Integer.parseInt(st));
		msg.setUploadCode(uploadCode);

		try {
			bomService.updateMaterialMsg(msg);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;

	}

	/**
	 * 删除物料
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "通过id删除物料信息", notes = "通过id删除物料信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "delMaterialMsg", method = RequestMethod.POST)
	public JSONObject delMaterialMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delMaterialMsg(Integer.parseInt(id));
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;

	}

	/**
	 * 通过id查询物料信息
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "通过id查询物料信息", notes = "通过id查询物料信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "findMaterialMsgById", method = RequestMethod.POST)
	public JSONObject findMaterialMsgById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		CMesMaterialMsgT material;
		try {
			material = bomService.findMaterialMsgByid(Integer.parseInt(id));
			json.put("material", material);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.getMessage();
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;

	}
}
