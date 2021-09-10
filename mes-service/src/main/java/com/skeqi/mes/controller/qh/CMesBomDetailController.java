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
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesOtherDataT;
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
@RequestMapping(value = "api/bomDetailManage", produces = MediaType.APPLICATION_JSON)
@Api(value = "bom明细管理", description = "bom明细管理", produces = MediaType.APPLICATION_JSON)
public class CMesBomDetailController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;

	// 查询bom明细列表
	@RequestMapping(value = "/findBomDetailList", method = RequestMethod.POST)
	@ApiOperation(value = "查询bom明细列表", notes = "查询bom明细列表")
		@ApiImplicitParams({
					@ApiImplicitParam(paramType = "query", name = "bomId", value = "bomId", required = false, dataType = "Integer")})
	@ResponseBody
	public JSONObject findMakeParameterList(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize){
		String bomId = request.getParameter("bomId");
		CMesBomDetailT bom = new CMesBomDetailT();
		if(bomId!=null && bomId!="" && !bomId.equals("")) {
			bom.setBomId(Integer.parseInt(bomId));
		}
		JSONObject json = new JSONObject();
		try {
			PageHelper.startPage(pageNum,pageSize);
			List<CMesBomDetailT> bomDetailList = bomService.findAllBomDetail(bom);
			PageInfo<CMesBomDetailT> pageInfo = new PageInfo<>(bomDetailList, 5);
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	/**
	 * 查询所有bom
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询所有bom", notes = "查询所有bom")
	@RequestMapping(value = "findAllBom", method = RequestMethod.POST)
	public JSONObject findAllBom(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServicesException {
		JSONObject json = new JSONObject();
			try {

				CMesBomT bomT = new CMesBomT();
				List<CMesBomT> bomList = bomService.findAllBom(bomT);
				json.put("code", 0);
				json.put("bomList", bomList);
			}catch(ServicesException e) {
				json.put("code", 1);
				json.put("msg", e.getMessage());
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
		return json;
	}

	/**
	 * 查询所有产线
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询所有产线", notes = "查询所有产线")
	@RequestMapping(value = "findAllLine", method = RequestMethod.POST)
	public JSONObject findAllLine(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServicesException {
		JSONObject json = new JSONObject();
			try {

				CMesLineT line = new CMesLineT();
				List<CMesLineT> lineList = lineService.findAllLine(line);
				System.out.println(lineList);
				json.put("code", 0);
				json.put("lineList", lineList);
			}catch(ServicesException e) {
				json.put("code", 1);
				json.put("msg", e.getMessage());
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
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
			}catch(ServicesException e) {
				json.put("code", 1);
				json.put("msg", e.getMessage());
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
		return json;
	}
	/**
	 * 查询所有的物料
	 *
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询所有物料信息", notes = "查询所有物料信息")
	@RequestMapping(value = "getBomDetail", method = RequestMethod.POST)
	public JSONObject getBomDetail(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServicesException {
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
		 * 新增bom明细
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 * @throws ParseException
		 */
		@ApiOperation(value = "新增bom明细", notes = "新增bom明细")
		@ApiImplicitParams({ @ApiImplicitParam(name = "dataType", value = "类别1-物料信息2-螺栓信息3-气密性信息4-其他信息", required = true, paramType = "query"),
				@ApiImplicitParam(name = "dataId", value = "物料、螺栓、气密id", required = true, paramType = "query"),
				@ApiImplicitParam(name = "reciveNumber", value = "数量", required = false, paramType = "query"),
				@ApiImplicitParam(name = "traceFlag", value = "是否追溯1-是2-否", required = true, paramType = "query"),
				@ApiImplicitParam(name = "bomId", value = "bomId", required = true, paramType = "query"),
				@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
				@ApiImplicitParam(name = "stationId", value = "工位id", required = false, paramType = "query")
		})
		@Transactional
		@RequestMapping(value = "addBomDetail", method = RequestMethod.POST)
		public JSONObject addBomDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
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
		/**
		 * 修改
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "修改bom", notes = "修改bom")
		@ApiImplicitParams({  @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dataType", value = "类别1-物料信息2-螺栓信息3-气密性信息4-其他信息", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dataId", value = "物料、螺栓、气密id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "reciveNumber", value = "数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "traceFlag", value = "是否追溯1-是2-否", required = true, paramType = "query"),
			@ApiImplicitParam(name = "bomId", value = "bomId", required = true, paramType = "query"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "stationId", value = "工位id", required = false, paramType = "query") })
		@Transactional
		@RequestMapping(value = "updateBomDetail", method = RequestMethod.POST)
		public JSONObject updateBomDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
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
		 * 删除bom明细
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id删除bom明细", notes = "通过id删除bom明细")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "deleteBomDetail", method = RequestMethod.POST)
		public JSONObject deleteBomDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
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
		/**
		 * 通过id查询bom
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id查询bom", notes = "通过id查询bom")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "findBomById", method = RequestMethod.POST)
		public JSONObject findBomById(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");

			CMesBomT bom;
			try {
				CMesBomDetailT bomDetail = bomService.findBomDetailByid(Integer.parseInt(id));
				json.put("bomList", bomDetail);
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
