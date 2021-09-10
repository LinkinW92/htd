package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.ProcessType;
import com.skeqi.mes.service.wms.ProcessTypeService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 2020年3月13日
 * @author yinp
 * 审批类型
 */
@RestController
@RequestMapping(value ="wms/processType", produces = MediaType.APPLICATION_JSON)
@Api(value = " 审批类型", description = " 审批类型", produces = MediaType.APPLICATION_JSON)
public class ProcessTypeController {

	@Autowired
	ProcessTypeService service;

	/**
	 * 修改viewMode状态 用于删除
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@RequestMapping(value = "viewMode", method = RequestMethod.POST)
	@ApiOperation(value = "修改viewMode状态 用于删除", notes = "修改viewMode状态 用于删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "processTypeId", value = "审批类型id", required = true, paramType = "query") })
	public void viewMode(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		Integer processTypeId = json.getInteger("processTypeId");

		ProcessType dx = new ProcessType();
		dx.setId(processTypeId);
		dx.setViewMode(0);

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();*/
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		ProcessType dx = new ProcessType();
		try {
			if(request.getParameter("processTypeId")!=null
					&& !request.getParameter("processTypeId").equals("")
					&& !request.getParameter("processTypeId").equals("0")){
				try {
					dx.setId(Integer.parseInt(request.getParameter("processTypeId")));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'processTypeId'参数类型有误");
				}
			}
			dx.setViewMode(0);
			boolean res = service.updateProcessType(dx);
			if (res) {
				data.put("msg", "删除成功！");
				data.put("code", true);
			} else {
				data.put("msg", "删除失败！");
				data.put("code", false);
			}

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
	 * 查询所有ID、NAME
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询所有ID、NAME", notes = "查询所有ID、NAME")
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
/*
		// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);*/

		try {
			List<ProcessType> list = service.findProcessTypeAll();

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
	 * 查询集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询集合", notes = "查询集合")
	@ApiImplicitParams({@ApiImplicitParam(name = "processType", value = "审批类型名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dis", value = "说明", required = false, paramType = "query"),
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query")})
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public void findList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		String processType = json.getString("processTypeName"); //审批类型名称
		String dis = json.getString("dis"); //说明

		Integer pageNumber = json.getInteger("pageNumber");
		if (pageNumber == null || pageNumber.equals("") || pageNumber == 0) {
			pageNumber = 1;
		}

		ProcessType dx = new ProcessType();
		dx.setProcessType(processType);
		dx.setDis(dis);

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();*/

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		ProcessType dx = new ProcessType();
		try {
			String processType = null; //审批类型名称
			String dis = null; //说明
			if(request.getParameter("processTypeName")!=null
					&& !request.getParameter("processTypeName").equals("")){
				try {
					dx.setProcessType(request.getParameter("processTypeName"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			if(request.getParameter("dis")!=null
					&& !request.getParameter("dis").equals("")){
				try {
					dx.setDis(request.getParameter("dis"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			Integer pageNumber =Integer.parseInt(request.getParameter("pageNumber"));
			if (request.getParameter("pageNumber") == null || request.getParameter("pageNumber").equals("")
					|| Integer.parseInt(request.getParameter("pageNumber")) == 0) {
					pageNumber = 1;
			}
			PageHelper.startPage(pageNumber, 8);
			List<ProcessType> lineList = service.findProcessTypeList(dx);
			PageInfo<ProcessType> pageInfo = new PageInfo<ProcessType>(lineList, 5);

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", pageInfo);
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
	 * 通过ID查询
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "通过审批类型ID查询", notes = "通过审批类型ID查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "processTypeId", value = "审批类型ID", required = true, paramType = "query")})
	@RequestMapping(value = "findById", method = RequestMethod.POST)
	public void findById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		Integer processTypeId = json.getInteger("processTypeId");

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();*/

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {

				Integer processTypeId = null; //审批类型ID
				if(request.getParameter("processTypeId")!=null
						&& !request.getParameter("processTypeId").equals("")
						&& !request.getParameter("processTypeId").equals("0")){
					try {
						processTypeId =  Integer.parseInt(request.getParameter("processTypeId"));
					}catch (NumberFormatException e) {
						e.printStackTrace();
						ToolUtils.errorLog(this, e, request);
						throw new Exception("'processTypeId'参数类型有误");
					}
				}
			ProcessType dx = service.findProcessTypeById(processTypeId);

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", dx);
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
	 * 通过NAME查询
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "通过审批类型NAME查询", notes = "通过审批类型查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "processTypeName", value = "processTypeName", required = true, paramType = "query")})
	@RequestMapping(value = "findByName", method = RequestMethod.POST)
	public void findByName(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
	/*	// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		String processTypeName = json.getString("processTypeName");

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
*/
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();

		try {
			String processTypeName = null; //项目名称
			if(request.getParameter("processTypeName")!=null
					&& !request.getParameter("processTypeName").equals("")){
				try {
					processTypeName = request.getParameter("processTypeName");
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			ProcessType dx = service.findProcessTypeByName(processTypeName);

			if (dx == null) {
				data.put("leng", 0);
			} else {
				data.put("leng", -1);
			}

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", dx);
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
	 * 新增
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */

	@ApiOperation(value = "新增", notes = "新增")
	@ApiImplicitParams({@ApiImplicitParam(name = " processType", value = "审批类型名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dis", value = "说明", required = false, paramType = "query")})
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void addWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		String processType = json.getString("processTypeName"); //审批类型名称
		String dis = json.getString("dis"); //说明

		ProcessType dx = new ProcessType();
		dx.setProcessType(processType);
		dx.setDis(dis);

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();*/
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		ProcessType dx = new ProcessType();
		try {
			if(request.getParameter("processTypeName")!=null
					&& !request.getParameter("processTypeName").equals("")){
				try {
					dx.setProcessType(request.getParameter("processTypeName"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			if(request.getParameter("dis")!=null
					&& !request.getParameter("dis").equals("")){
				try {
					dx.setDis(request.getParameter("dis"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			boolean res = service.addProcessType(dx);
			if (res) {
				data.put("msg", "新增成功！");
				data.put("code", true);
			} else {
				data.put("msg", "新增失败！");
				data.put("code", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", -1);
		} finally {
			result.put("result", data);
			response.getWriter().append(result.toJSONString());
		}

	}

	/**
	 * 更新
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "更新", notes = "更新")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query"),
		@ApiImplicitParam(name = " processType", value = "审批类型名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "dis", value = "说明", required = false, paramType = "query")})

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		Integer id = json.getInteger("id"); // id
		String processType = json.getString("processTypeName"); //审批类型名称
		String dis = json.getString("dis"); //说明

		ProcessType dx = new ProcessType();
		dx.setId(id);
		dx.setProcessType(processType);
		dx.setDis(dis);

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
*/
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		ProcessType dx = new ProcessType();
		try {
			if(request.getParameter("id")!=null
					&& !request.getParameter("id").equals("")
					&& !request.getParameter("id").equals("0")){
				try {
					dx.setId(Integer.parseInt(request.getParameter("processTypeId")));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'processTypeId'参数类型有误");
				}
			}
			if(request.getParameter("processTypeName")!=null
					&& !request.getParameter("processTypeName").equals("")){
				try {
					dx.setProcessType(request.getParameter("processTypeName"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			if(request.getParameter("dis")!=null
					&& !request.getParameter("dis").equals("")){
				try {
					dx.setDis(request.getParameter("dis"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			boolean res = service.updateProcessType(dx);
			if (res) {
				data.put("msg", "更新成功！");
				data.put("code", true);
			} else {
				data.put("msg", "更新失败！");
				data.put("code", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg",e.getMessage());
			data.put("code", false);
		} finally {
			result.put("result", data);
			response.getWriter().append(result.toJSONString());
		}

	}

	/**
	 * 删除
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "删除", notes = "删除")
	@ApiImplicitParams({@ApiImplicitParam(name = " processTypeId", value = "审批类型ID", required = false, paramType = "query")})
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void deleteWarehouse(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
		/*// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		Integer processTypeId = json.getInteger("processTypeId");

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();*/

		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		ProcessType dx = new ProcessType();
		try {
			Integer processTypeId = null;
			if(request.getParameter("processTypeId")!=null
					&& !request.getParameter("processTypeId").equals("")
					&& !request.getParameter("processTypeId").equals("0")){
				try {
					processTypeId = Integer.parseInt(request.getParameter("processTypeId"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'processTypeId'参数类型有误");
				}
			}
			boolean res = service.deleteProcessType(processTypeId);
			if (res) {
				data.put("msg", "删除成功！");
				data.put("code", true);
			} else {
				data.put("msg", "删除失败！");
				data.put("code", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", false);
		} finally {
			result.put("result", data);
			response.getWriter().append(result.toJSONString());
		}

	}

}
