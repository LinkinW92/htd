package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.service.wms.MaterialAdditionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 物料追加
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/materialAddition", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料追加", description = "物料追加", produces = MediaType.APPLICATION_JSON)
public class MaterialAdditionController {

	@Autowired
	MaterialAdditionService service;


	/**
	 * 新增出库记录
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws ServicesException
	 */
	@ApiOperation(value = "新增出库记录", notes = "新增出库记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "materialNumberId", value = "物料库存id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "query"),
		@ApiImplicitParam(name = "feedingData", value = "加料信息", required = true, paramType = "query")
		})
	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Rjson add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Integer materialNumberId = EqualsUtil.integer(request, "materialNumberId", "物料库存id", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			String feedingData = EqualsUtil.string(request, "feedingData", "加料信息", true);

			boolean res = service.addMaterialAddition(feedingData, materialNumberId,userId);
			if(!res){
				throw new Exception("操作失败，未知异常");
			}
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询session
	 * @throws Exception
	 */
	@ApiOperation(value = "查询session", notes = "查询session")
	@ApiImplicitParams({})
	@RequestMapping(value = "findSession", method = RequestMethod.POST)
	public void findSession(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {

			List<JSONObject> list = (List<JSONObject>) session.getAttribute("MaterialAdditionData");
			if(list==null){
				list = new ArrayList<JSONObject>();
			}
			data.put("msg", "操作成功！");
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

	/**
	 * 新增session
	 * @throws Exception
	 */
	@ApiOperation(value = "新增session", notes = "新增session")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialId", value = "物料id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialNumber", value = "物料数量", required = true, paramType = "query")
		})
	@RequestMapping(value = "addSession", method = RequestMethod.POST)
	public void addSession(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			// 项目
			Integer projectId = null;
			String projectName = null;
			// 物料
			Integer materialId = null;
			String materialName = null;
			// 数量
			Integer materialNumber = null;
			try {
				projectId = Integer.parseInt(request.getParameter("projectId"));
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'项目id'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'项目id'参数类型有误");
			}
			try {
				projectName = request.getParameter("projectName");
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'项目名称'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'项目名称'参数类型有误");
			}
			try {
				materialId = Integer.parseInt(request.getParameter("materialId"));
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料id'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料id'参数类型有误");
			}
			try {
				materialName = request.getParameter("materialName");
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料名称'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料名称'参数类型有误");
			}
			try {
				materialNumber = Integer.parseInt(request.getParameter("materialNumber"));
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料数量'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'物料数量'参数类型有误");
			}

			Boolean res = service.addSession(session, projectId, projectName, materialId, materialName, materialNumber);

			if(!res){
				throw new Exception("操作失败,未知异常");
			}

			data.put("msg", "操作成功！");
			data.put("code", true);
			result.put("result", data);
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

	/**
	 * 删除session
	 * @throws Exception
	 */
	@ApiOperation(value = "删除session", notes = "删除session")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "index", value = "session下标", required = true, paramType = "query")
		})
	@RequestMapping(value = "deleteSession", method = RequestMethod.POST)
	public void deleteSession(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			int index = 0;
			try {
				index = Integer.parseInt(request.getParameter("index"));
			}catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'session下标'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'session下标'参数类型有误");
			}
			Boolean res = service.deleteSession(session, index);

			if(!res){
				throw new Exception("操作失败");
			}

			data.put("msg", "操作成功！");
			data.put("code", 0);
			result.put("result", data);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", -1);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	/**
	 * 查询所有项目ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有项目ID、NAME", notes = "查询所有项目ID、NAME")
	@ApiImplicitParams({
		})
	@RequestMapping(value = "findProjectAll", method = RequestMethod.POST)
	public void findProjectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			List<CWmsProject> list = service.findProjectAll();

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

	/**
	 * 查询所有物料ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有物料ID、NAME", notes = "查询所有物料ID、NAME")
	@ApiImplicitParams({
		})
	@RequestMapping(value = "findMaterialAll", method = RequestMethod.POST)
	public void findMaterialAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			List<CMesJlMaterialT> list = service.findMaterialAll();

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

	/**
	 * 查询是否有盘点记录未审批或者未执行
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询是否有盘点记录未审批或者未执行", notes = "查询是否有盘点记录未审批或者未执行")
	@ApiImplicitParams({
		})
	@Transactional
	@RequestMapping(value = "queryWhetherThereIsInventory", method = RequestMethod.POST)
	public void queryWhetherThereIsInventory(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {

			boolean res = service.queryWhetherThereIsInventory();
			data.put("msg", "查询成功");
			data.put("code", res);
			result.put("result", data);
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

}
