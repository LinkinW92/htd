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
@RequestMapping(value = "api/boltMsgontroller", produces = MediaType.APPLICATION_JSON)
@Api(value = "螺栓信息管理", description = "螺栓信息管理", produces = MediaType.APPLICATION_JSON)
public class CMesBoltMsgController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesStationTService stationService;

		// 查询螺栓信息列表
		@RequestMapping(value = "/findBoltMsgList", method = RequestMethod.POST)
		@ApiOperation(value = "查询螺栓信息列表", notes = "查询螺栓信息列表")
		@ApiImplicitParams({
					@ApiImplicitParam(paramType = "query", name = "station", value = "工位", required = false, dataType = "Integer")})
		@ResponseBody
		public JSONObject findBoltMsgList(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
				@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize
				){
			JSONObject json = new JSONObject();
			CMesBoltInfomationT boltInfomation = new CMesBoltInfomationT();
			try {

			String station = request.getParameter("station");
			if(station!=null&&station!="") {
				boltInfomation.setStationName(station);
			}
			PageHelper.startPage(pageNum, pageSize);
			List<CMesBoltInfomationT> boltList = bomService.findAllBolt(boltInfomation);
			PageInfo<CMesBoltInfomationT> pageInfo = new PageInfo<>(boltList, 5);
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
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
		 * 新增螺栓信息
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 * @throws ParseException
		 */
		@ApiOperation(value = "新增螺栓信息", notes = "新增螺栓信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "boltName", value = "名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
				@ApiImplicitParam(name = "boltNo", value = "螺栓编号", required = false, paramType = "query"),
				@ApiImplicitParam(name = "aLimit", value = "角度范围", required = true, paramType = "query"),
				@ApiImplicitParam(name = "tLimit", value = "扭矩范围", required = true, paramType = "query"),
				@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = false, paramType = "query"),
				@ApiImplicitParam(name = "programNo", value = "程序号", required = true, paramType = "query"),
				@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query")})
		@Transactional
		@RequestMapping(value = "addBoltMsg", method = RequestMethod.POST)
		public JSONObject addBoltMsg(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
			JSONObject json = new JSONObject();
			String boltName = request.getParameter("boltName").trim();
			String boltNo = request.getParameter("boltNo").trim();
			String st = request.getParameter("st").trim();
			String aLimit = request.getParameter("aLimit").trim();
			String tLimit = request.getParameter("tLimit").trim();
			String uploadCode = request.getParameter("uploadCode").trim();
			String programNo = request.getParameter("programNo").trim();
			String dis = request.getParameter("dis");
			CMesBoltInfomationT bolt = new CMesBoltInfomationT();
			bolt.setaLimit(aLimit);
			bolt.setBoltName(boltName);
			bolt.setBoltNo(Integer.parseInt(boltNo));
			bolt.setDis(dis);
			bolt.setProgramNo(Integer.parseInt(programNo));
			bolt.setSt(Integer.parseInt(st));
			bolt.settLimit(tLimit);
			bolt.setUploadCode(uploadCode);
			try {
				bomService.addBolt(bolt);
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
		@ApiOperation(value = "修改螺栓信息", notes = "修改螺栓信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			 @ApiImplicitParam(name = "boltName", value = "名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "st", value = "工位", required = true, paramType = "query"),
				@ApiImplicitParam(name = "boltNo", value = "螺栓编号", required = false, paramType = "query"),
				@ApiImplicitParam(name = "aLimit", value = "角度范围", required = true, paramType = "query"),
				@ApiImplicitParam(name = "tLimit", value = "扭矩范围", required = true, paramType = "query"),
				@ApiImplicitParam(name = "uploadCode", value = "上传代码", required = false, paramType = "query"),
				@ApiImplicitParam(name = "programNo", value = "程序号", required = true, paramType = "query"),
				@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query")})
		@Transactional
		@RequestMapping(value = "updateBoltMsg", method = RequestMethod.POST)
		public JSONObject updateBoltMsg(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String boltName = request.getParameter("boltName").trim();
			String boltNo = request.getParameter("boltNo").trim();
			String st = request.getParameter("st").trim();
			String aLimit = request.getParameter("aLimit").trim();
			String tLimit = request.getParameter("tLimit").trim();
			String uploadCode = request.getParameter("uploadCode").trim();
			String programNo = request.getParameter("programNo").trim();
			String dis = request.getParameter("dis");
			String id = request.getParameter("id");

			CMesBoltInfomationT bolt = new CMesBoltInfomationT();
			bolt.setId(Integer.parseInt(id));
			bolt.setaLimit(aLimit);
			bolt.setBoltName(boltName);
			bolt.setBoltNo(Integer.parseInt(boltNo));
			bolt.setDis(dis);
			bolt.setProgramNo(Integer.parseInt(programNo));
			bolt.setSt(Integer.parseInt(st));
			bolt.settLimit(tLimit);
			bolt.setUploadCode(uploadCode);

			try {
				bomService.updateBolt(bolt);
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
		 * 删除螺栓
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id删除螺栓信息", notes = "通过id删除螺栓信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "delBoltMsg", method = RequestMethod.POST)
		public JSONObject delBoltMsg(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			try {
				bomService.delBolt(Integer.parseInt(id));
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
		 * 通过id查询螺栓信息
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id查询螺栓信息", notes = "通过id查询螺栓信息")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "findBoltMsgById", method = RequestMethod.POST)
		public JSONObject findBoltMsgById(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			CMesBoltInfomationT bolt ;
			try {
				 bolt = bomService.findBoltByid(Integer.parseInt(id));
				json.put("bolt", bolt);
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
