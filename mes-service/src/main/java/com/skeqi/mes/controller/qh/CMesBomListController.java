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
@RequestMapping(value = "api/bomListManage", produces = MediaType.APPLICATION_JSON)
@Api(value = "bom管理", description = "bom管理", produces = MediaType.APPLICATION_JSON)
public class CMesBomListController {

	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesProductionTService productionService;

		// 查询制造参数清单列表
		@RequestMapping(value = "/findBomList", method = RequestMethod.POST)
		@ApiOperation(value = "查询bom清单列表", notes = "查询bom清单列表")
//		@ApiImplicitParams({
//					@ApiImplicitParam(paramType = "query", name = "PageNum", value = "PageNum", required = true, dataType = "Integer"),
//					@ApiImplicitParam(paramType = "query", name = "PageSize", value = "pageSize", required = true, dataType = "Integer")})
		@ResponseBody
		public JSONObject findMakeParameterList(HttpServletRequest request,HttpServletResponse response,
				@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
				@RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize
				){
			JSONObject json = new JSONObject();
//			Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
//			System.out.println(pageNum);
//			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
//			System.err.println(pageSize);
			try {
				PageHelper.startPage(pageNum,pageSize);
				CMesBomT bom = new CMesBomT();
				List<CMesBomT> bomList = bomService.findAllBom(bom);
				PageInfo<CMesBomT> pageInfo = new PageInfo<>(bomList,5);
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
		 * 查询所有产品
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "查询所有产品", notes = "查询所有产品")
		@RequestMapping(value = "findAllProduction", method = RequestMethod.POST)
		public JSONObject findAllProduction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {
			JSONObject json = new JSONObject();
				try {

					CMesProductionT production = new CMesProductionT();
					List<CMesProductionT> productionList = productionService.findAllProduction(production);
					json.put("code", 0);
					json.put("productionList", productionList);
				}catch(ServicesException e) {
					json.put("code", 1);
					json.put("msg", e.getMessage());
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			return json;
		}
		/**
		 * 新增bom
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 * @throws ParseException
		 */
		@ApiOperation(value = "新增bom", notes = "新增bom")
		@ApiImplicitParams({ @ApiImplicitParam(name = "bomName", value = "名称", required = true, paramType = "query"),
				@ApiImplicitParam(name = "productionId", value = "所属产品", required = true, paramType = "query"),
				@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
		@Transactional
		@RequestMapping(value = "addBom", method = RequestMethod.POST)
		public JSONObject addBom(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException, ParseException {
			JSONObject json = new JSONObject();
			String bomName = request.getParameter("bomName").trim();
			String productionId = request.getParameter("productionId");
			String dis = request.getParameter("dis");
			CMesBomT bom = new CMesBomT();

			bom.setDis(dis);
			bom.setBomName(bomName);
			bom.setProductionId(Integer.parseInt(productionId));
			try {
				bomService.addBom(bom);
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
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "bomName", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionId", value = "所属产品", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "描述", required = false, paramType = "query") })
		@Transactional
		@RequestMapping(value = "updateBom", method = RequestMethod.POST)
		public JSONObject updateBom(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			String bomName = request.getParameter("bomName").trim();
			String productionId = request.getParameter("productionId");
			String dis = request.getParameter("dis");
			CMesBomT bom = new CMesBomT();
			bom.setId(Integer.parseInt(id));
			bom.setDis(dis);
			bom.setBomName(bomName);
			bom.setProductionId(Integer.parseInt(productionId));

			try {
				bomService.updateBom(bom);
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
		 * 删除bom
		 *
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServicesException
		 */
		@ApiOperation(value = "通过id删除bom", notes = "通过id删除bom")
		@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
		@Transactional
		@RequestMapping(value = "deleteBom", method = RequestMethod.POST)
		public JSONObject deleteBom(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServicesException {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			try {
				bomService.delBom(Integer.parseInt(id));
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
				bom = bomService.findBomByid(Integer.parseInt(id));
				json.put("bomList", bom);
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
