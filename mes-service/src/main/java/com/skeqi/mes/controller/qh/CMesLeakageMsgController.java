package com.skeqi.mes.controller.qh;

import java.io.Console;
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
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
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
@RequestMapping(value = "api/airtightMsgontroller", produces = MediaType.APPLICATION_JSON)
@Api(value = "气密性信息管理", description = "气密性信息管理", produces = MediaType.APPLICATION_JSON)
public class CMesLeakageMsgController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesStationTService stationService;

		// 查询气密性信息列表
		@RequestMapping(value = "/findLeakagelMsgList", method = RequestMethod.POST)
		@ApiOperation(value = "查询气密性信息列表", notes = "查询气密性信息列表")
		@ApiImplicitParams({
					@ApiImplicitParam(paramType = "query", name = "station", value = "工位", required = false, dataType = "String")})
		@ResponseBody
		public JSONObject findLeakagelMsgList(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
				@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize
				){
			JSONObject json = new JSONObject();
			CMesLeakageInfoT leakageInfo = new CMesLeakageInfoT();
			try {

			String station = request.getParameter("station");
			System.out.println(station + "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
			System.out.println("sdddddddddddddddddddddddddddddddddddddddddddddd");
			if( station!=null && !station.equals("")) {
				leakageInfo.setStationName(station);
			}
			PageHelper.startPage(pageNum, pageSize);
			List<CMesLeakageInfoT> leakageList = bomService.findAllLeakage(leakageInfo);
			PageInfo<CMesLeakageInfoT> pageInfo = new PageInfo<>(leakageList, 5);
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
			}catch (ServicesException e) {
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
		 * 新增气密性信息
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 * @throws ParseException
		 */
		@ApiOperation(value = "新增气密性信息", notes = "新增气密性信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "leakageName", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pvLimit", value = "气压范围", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lvLimit", value = "泄漏率范围", required = true, paramType = "query"),
			@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = false, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query")})
		@Transactional
		@RequestMapping(value = "addLeakageMsg", method = RequestMethod.POST)
		public JSONObject addLeakageMsg(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
			JSONObject json = new JSONObject();
			String leakageName = request.getParameter("leakageName").trim();
			String st = request.getParameter("st").trim();
			String pvLimit = request.getParameter("pvLimit").trim();
			String lvLimit = request.getParameter("lvLimit").trim();
			String uploadCode = request.getParameter("uploadCode").trim();
			String dis = request.getParameter("dis");
			CMesLeakageInfoT leakage = new CMesLeakageInfoT();
			leakage.setDis(dis);
			leakage.setLeakageName(leakageName);
			leakage.setLvLimit(lvLimit);
			leakage.setPvLimit(pvLimit);
			leakage.setSt(Integer.parseInt(st));
			leakage.setUploadCode(uploadCode);
			try {
				bomService.addLeakage(leakage);
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
		@ApiOperation(value = "修改气密性信息", notes = "修改气密性信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			 @ApiImplicitParam(name = "leakageName", value = "名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
				@ApiImplicitParam(name = "pvLimit", value = "气压范围", required = false, paramType = "query"),
				@ApiImplicitParam(name = "lvLimit", value = "泄漏率范围", required = true, paramType = "query"),
				@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = false, paramType = "query"),
				@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query")})
		@Transactional
		@RequestMapping(value = "updateLeakageMsg", method = RequestMethod.POST)
		public JSONObject updateLeakageMsg(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String leakageName = request.getParameter("leakageName").trim();
			String st = request.getParameter("st").trim();
			String pvLimit = request.getParameter("pvLimit").trim();
			String lvLimit = request.getParameter("lvLimit").trim();
			String uploadCode = request.getParameter("uploadCode").trim();
			String dis = request.getParameter("dis");
			String id = request.getParameter("id");
			CMesLeakageInfoT leakage = new CMesLeakageInfoT();
			leakage.setDis(dis);
			leakage.setId(Integer.parseInt(id));
			leakage.setLeakageName(leakageName);
			leakage.setLvLimit(lvLimit);
			leakage.setPvLimit(pvLimit);
			leakage.setSt(Integer.parseInt(st));
			leakage.setUploadCode(uploadCode);
			try {
				bomService.updateLeakage(leakage);
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
		 * 删除气密性
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id删除气密性信息", notes = "通过id删除气密性信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "delLeakage", method = RequestMethod.POST)
		public JSONObject delLeakage(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			try {
				bomService.delLeakage(Integer.parseInt(id));
				json.put("code", 0);
			}catch (ServicesException e) {
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
		 * 通过id查询气密性信息
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id查询气密性信息", notes = "通过id查询气密性信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "findLeakageMsgById", method = RequestMethod.POST)
		public JSONObject findLeakageMsgById(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			try {
				CMesLeakageInfoT leakage = bomService.findLeakageByid(Integer.parseInt(id));
				json.put("leakage", leakage);
				json.put("code", 0);
			}catch (ServicesException e) {
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
