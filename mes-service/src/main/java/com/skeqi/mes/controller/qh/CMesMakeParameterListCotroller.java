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
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/makeParameterList", produces = MediaType.APPLICATION_JSON)
@Api(value = "制造参数清单管理", description = "制造参数清单管理", produces = MediaType.APPLICATION_JSON)
public class CMesMakeParameterListCotroller {

	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesMaterialService materialService;
	@Autowired
	CMesStationTService stationService;
	@Autowired
	CMesLineTService lineService;

		// 查询制造参数清单列表
		@RequestMapping(value = "/findMakeParameterList", method = RequestMethod.POST)
		@ApiOperation(value = "查询制造参数清单列表", notes = "查询制造参数清单列表")
		@ApiImplicitParams({
					@ApiImplicitParam(paramType = "query", name = "lid", value = "产线ID", required = false, dataType = "String"),
					@ApiImplicitParam(paramType = "query", name = "lno", value = "制造参数清单编号", required = false, dataType = "String") })
		@ResponseBody
		public JSONObject findMakeParameterList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
			@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize) {
			JSONObject json = new JSONObject();
			try {
			String lid = request.getParameter("lid");
			String lno = request.getParameter("lno");
			System.out.println("*************************************lid:"+lid);
			System.out.println("*************************************lno:"+lno);

			CMesStationT st = new CMesStationT();
			List<CMesStationT> stationList = stationService.findAllStation(st);
			CMesManufactureParametersT mfp = new CMesManufactureParametersT();
			if(lid!=null && lid!="" && !lid.equals("") ) {
				mfp.setLineId(Integer.parseInt(lid));
			}
			if(lno!=null&&lno!=""  && !lno.equals("")) {
				mfp.setListNo(lno);
			}

			PageHelper.startPage(pageNum, pageSize);
			List<CMesManufactureParametersT> manuParameterLists = bomService.findAllMFG(mfp);
			PageInfo<CMesManufactureParametersT> pageInfo = new PageInfo<>(manuParameterLists, 5);// 制造参数清单列表
			json.put("pageInfo", pageInfo);
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
			return json;
		}

		/**
		 * 查询制造参数明细列表
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "查询制造参数明细列表", notes = "查询制造参数明细列表")
		@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "料单编号", required = false, paramType = "query"),
			 @ApiImplicitParam(name = "stationId", value = "工位Id", required = false, paramType = "query")})
		@RequestMapping(value = "findMakeParameterDetailList", method = RequestMethod.POST)
		public JSONObject findMakeParameterDetailList(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
				@RequestParam(required = false, defaultValue = "1", value = "pageSize") Integer pageSize)
			throws IOException, ServicesException {
			JSONObject json = new JSONObject();
				try {
					CMesManufactureParametersT mfp = new CMesManufactureParametersT();
					String listNo = request.getParameter("listNo");
					String stationId = request.getParameter("stationId");
					CMesMfParametersDetailT mf = new CMesMfParametersDetailT();
					if(listNo!=null&&listNo!="" && !listNo.equals("")) {
						mf.setParameterNo(listNo);
					}
//					if(staName!=null&&staName!="") {
//						mf.setStationId(Integer.parseInt(staName));
//					}
					if(stationId!=null && stationId!="" && !stationId.equals("")) {
						// materialListDetail.setStationName(staname);
						mf.setStationId(Integer.parseInt(stationId));
					}
					PageHelper.startPage(pageNum, pageSize);
					List<CMesMfParametersDetailT> mfParametersDetailList = bomService.findAllMFGDetail(mf);
					PageInfo<CMesMfParametersDetailT> pageInfo = new PageInfo<>(mfParametersDetailList, 5);// 制造参数清单明细列表
					request.setAttribute("pageInfo", pageInfo);
					json.put("code", 0);
					json.put("pageInfo", pageInfo);
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
		 * 根据制造参数清单id查询所属产线下的工位
		 *
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "根据制造参数清单id查询所属产线下的工位", notes = "根据制造参数清单id查询所属产线下的工位")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "制造参数清单id", required = true, paramType = "query") })
		@RequestMapping(value = "findStationById", method = RequestMethod.POST)
		public JSONObject findStationById(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			String idStr = request.getParameter("id");
			Integer id = 0;
			if(idStr != null) {
				id= Integer.parseInt(idStr);
			}

			JSONObject json = new JSONObject();
			try {
				List<CMesStationT> stationList = null;
				if(id != 0) {
					stationList = bomService.findStationById(id);
				}
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
		 * 查询所有物料
		 *
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "查询所有物料", notes = "查询所有物料")
		@RequestMapping(value = "findAllMaterial", method = RequestMethod.POST)
		public JSONObject findAllMaterial(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
			JSONObject json = new JSONObject();
				try {
					CMesJlMaterialT jl = new CMesJlMaterialT();
					List<CMesJlMaterialT> findJT = materialService.findAllMaterial(jl);
					json.put("code", 0);
					json.put("findJt", findJT);
				}catch(ServicesException e) {
					json.put("code", 1);
					json.put("msg", e.getMessage());
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			return json;
		}
			/**
			 * 查询所有制造参数清单
			 *
			 *
			 * @param request
			 * @param response
			 * @throws IOException
			 * @throws ServicesException
			 */
			@ApiOperation(value = "查询所有物料", notes = "查询所有物料")
			@RequestMapping(value = "findAllMakeParameter", method = RequestMethod.POST)
			public JSONObject findAllMakeParameter(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
				JSONObject json = new JSONObject();
					try {
						CMesManufactureParametersT mfp = new CMesManufactureParametersT();
						List<CMesManufactureParametersT> manuParameterLists = bomService.findAllMFG(mfp);
						json.put("code", 0);
						json.put("makeParameterList", manuParameterLists);
					}catch(ServicesException e) {
						e.getMessage();
						json.put("code", 1);
						json.put("msg", e.getMessage());
//						e.printStackTrace();
					}
				return json;
			}
		/**
		 * 新增制造参数清单
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 * @throws ParseException
		 */
		@ApiOperation(value = "新增制造参数清单", notes = "新增制造参数清单")
		@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "制造参数清单编号", required = true, paramType = "query"),
				@ApiImplicitParam(name = "listName", value = "制造参数清单名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "lineId", value = "产线名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "effectiveTime", value = "有效时间", required = false, paramType = "query"),
				@ApiImplicitParam(name = "invalidTime", value = "失效时间", required = false, paramType = "query"),
				@ApiImplicitParam(name = "listVersion", value = "版本", required = false, paramType = "query")})
//		@Transactional
		@RequestMapping(value = "addMakeParameter", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="新增制造参数清单")
		public JSONObject addMakeParameter(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
			JSONObject json = new JSONObject();

			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String listNo = request.getParameter("listNo");
			String listName = request.getParameter("listName").trim();
			String lineId = request.getParameter("lineId");
			String effectiveTime = request.getParameter("effectiveTime");
			String invalidTime = request.getParameter("invalidTime");
			String listVersion = request.getParameter("listVersion");
			Date starttime = sim.parse(effectiveTime); // 开始时间
			Date endtime = sim.parse(invalidTime); // 开始时间
			boolean before = starttime.before(endtime);
			if (before == false) {
				json.put("code", 99);
				json.put("msg", "生效时间不可以大于失效时间");
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
					e.getMessage();
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
		@ApiOperation(value = "修改制造参数清单", notes = "修改制造参数清单")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "listNo", value = "制造参数清单编号", required = true, paramType = "query"),
				@ApiImplicitParam(name = "listName", value = "制造参数清单名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "lineId", value = "产线名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "effectiveTime", value = "有效时间", required = true, paramType = "query"),
				@ApiImplicitParam(name = "invalidTime", value = "失效时间", required = true, paramType = "query"),
				@ApiImplicitParam(name = "listVersion", value = "版本", required = true, paramType = "query")})
		@Transactional
		@RequestMapping(value = "updateMakeParameter", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="编辑制造参数清单")
		public JSONObject updateMakeParameter(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
			JSONObject json = new JSONObject();

			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String id = request.getParameter("id");
				String listNo = request.getParameter("listNo").trim();
				String listName = request.getParameter("listName").trim();
				String lineId = request.getParameter("lineId");
				String effectiveTime = request.getParameter("effectiveTime");
//				effectiveTime = effectiveTime + ":00";
				String invalidTime = request.getParameter("invalidTime");
//				invalidTime = invalidTime + ":00";
				String listVersion = request.getParameter("listVersion");
			    Date starttime = sim.parse(effectiveTime); // 开始时间
			    Date endtime = sim.parse(invalidTime); // 开始时间
			    boolean before = starttime.after(endtime);
			    if (before) {
			     	json.put("code", -1);
				    json.put("msg", "失效时间不可以小于生效时间");
				    return json;
			    }else if(starttime.getTime() == endtime.getTime()){
					json.put("code", -1);
					json.put("msg", "失效时间必须大于生效时间");
					return json;
				}

			CMesMaterialListT material = new CMesMaterialListT();
				material.setListNo(listNo);
				material.setDt(new Date());
				material.setEffectiveTime(effectiveTime);
				material.setLineId(Integer.parseInt(lineId));
				material.setListName(listName);
				material.setListVersion(listVersion);
				material.setInvalidTime(invalidTime);
				material.setId(Integer.parseInt(id));

				try {
					CMesManufactureParametersT mfp = new CMesManufactureParametersT();
					mfp.setId(Integer.parseInt(id));
					mfp.setListNo(listNo);
					mfp.setLineId(Integer.parseInt(lineId));
					mfp.setListName(listName);
					mfp.setEffectiveTime(sim.parse(effectiveTime));
					mfp.setInvalidTime(sim.parse(invalidTime));
					mfp.setListVersion(Double.parseDouble(listVersion));
//					bomService.addMFG(mfp);
					bomService.updateMFG(mfp);
					json.put("code", 0);
//					json.put("code", 0);
				}catch (ServicesException e) {
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
		 * 删除
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id删除制造清单", notes = "删除制造清单")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "料单id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "deleteMakeParameter", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="删除制造参数清单")
		public JSONObject deleteMakeParameter(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
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
		 *添加制造参数清单明细
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "添加制造参数清单明细", notes = "添加制造参数清单明细")
		@ApiImplicitParams({
			@ApiImplicitParam(name = "mfParametersId", value = "所属料单", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterNo", value = "明细编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterName", value = "明细名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterMainFlag", value = "主制造参数1-主参数，0-非主参数", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterCheck", value = "完整性标记1-检查2-不检查", required = false, paramType = "query"),
			@ApiImplicitParam(name = "screwNumber", value = "拧紧次数", required = false, paramType = "query"),
			@ApiImplicitParam(name = "normalT", value = "标准扭矩值", required = false, paramType = "query"),
			@ApiImplicitParam(name = "tUpperLimit", value = "扭矩值上限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "tLowerLimit", value = "扭矩值下限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "normalA", value = "标准角度值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "aUpperLimit", value = "角度值上限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "aLowerLimit", value = "角度值下限", required = false, paramType = "query"),
			@ApiImplicitParam(name = "otherMormalT", value = "其他种类标准值", required = false, paramType = "query"),
			@ApiImplicitParam(name = "otherUpperLimit", value = "其他种类上限值", required = false, paramType = "query"),
			@ApiImplicitParam(name = "otherLowerLimit", value = "其他种类下限值", required = false, paramType = "query"),
			@ApiImplicitParam(name = "parameterReplace", value = "替换值", required = false, paramType = "query"),
			@ApiImplicitParam(name = "staid", value = "所属工位", required = true, paramType = "query")})
		@RequestMapping(value = "addMakeParameterDetail", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="新增制造参数清单明细")
		public JSONObject addMakeParameterDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			CMesMfParametersDetailT mpd = new CMesMfParametersDetailT();

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
			String staid = request.getParameter("staid");


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
				e.getMessage();
				json.put("code", -1);
				json.put("msg", e.getMessage());
			}
			return json;
		}

		/**
		 * 修改制造清单明细
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "修改制造清单明细", notes = "修改制造清单明细")
		@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "mfParametersId", value = "所属料单", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterNo", value = "明细编号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterName", value = "明细名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterMainFlag", value = "主制造参数1-主参数，0-非主参数", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterCheck", value = "完整性标记1-检查2-不检查", required = false, paramType = "query"),
			@ApiImplicitParam(name = "screwNumber", value = "拧紧次数", required = true, paramType = "query"),
			@ApiImplicitParam(name = "normalT", value = "标准扭矩值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "tUpperLimit", value = "扭矩值上限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "tLowerLimit", value = "扭矩值下限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "normalA", value = "标准角度值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "aUpperLimit", value = "角度值上限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "aLowerLimit", value = "角度值下限", required = true, paramType = "query"),
			@ApiImplicitParam(name = "otherMormalT", value = "其他种类标准值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "otherUpperLimit", value = "其他种类上限值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "otherLowerLimit", value = "其他种类下限值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "parameterReplace", value = "替换值", required = true, paramType = "query"),
			@ApiImplicitParam(name = "staid", value = "所属工位", required = true, paramType = "query")})
		@RequestMapping(value = "updateMakeParameterDetail", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="编辑制造参数清单明细")
		public JSONObject updateMakeParameterDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
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
			String staid = request.getParameter("staid");
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
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = " 删除制造清单明细", notes = " 删除制造清单明细")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "制造清单明细id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "delMakeParameterDetail", method = RequestMethod.POST)
		@OptionalLog(module="生产", module2="制造参数清单", method="删除制造参数清单明细")
		public JSONObject delMakeParameterDetail(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			try {
				bomService.delMFGDetail(Integer.parseInt(id));
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
