package com.skeqi.mes.controller.qh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.service.wjp.AllotmentManagementService;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.CMesRecipeTService;
import com.skeqi.mes.service.all.CMesStationTService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "配方管理", description = "配方管理", produces = MediaType.APPLICATION_JSON)
public class CMesRecipeControllerL {
	@Autowired
	private CMesRecipeService service;

	@Autowired
	private CMesProductionTService proservice;

	@Autowired
	private CMesLineTService lineservice;

	@Autowired
	private CMesStationTService staservice;

	@Autowired
	CMesProductionService cMesProductionService;

	@Autowired
	CMesRecipeService cMesRecipeService;
	@Autowired
	CMesStationTService cMesStationTService;
	@Autowired
	AllotmentManagementService allotmentManagementService;
	/**
	 * 进入配方页面
	 *
	 * @author FQZ
	 * @date 2020年3月27日下午4:14:58
	 */
	// 总配方列表
	@RequestMapping(value = "/findAllTotal", method = RequestMethod.POST)
	@ApiOperation(value = "总配方列表", notes = "总配方列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "productionId", value = "产品id", required = false, paramType = "query", dataType = "int") })
	public JSONObject findAllTotal(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, Integer productionId, Integer lineId) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		CMesTotalRecipeT cMesTotalRecipeT = new CMesTotalRecipeT();
		cMesTotalRecipeT.setProductionId(productionId);
		cMesTotalRecipeT.setLineId(lineId);
		List<CMesTotalRecipeT> TotalRecipeAll = service.findAllTotalRecipe(cMesTotalRecipeT); // 总配方列表
		PageInfo<CMesTotalRecipeT> pageInfo = new PageInfo<>(TotalRecipeAll);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}




	// 配方列表
	@RequestMapping(value = "/findAllRecipe", method = RequestMethod.POST)
	@ApiOperation(value = "配方列表", notes = "配方列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "tid", value = "总配方id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "name", value = "配方名称", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "sid", value = "工位id", required = false, paramType = "query", dataType = "int")})
	public JSONObject findAllRecipe(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, Integer productionId,Integer tid,Integer sid,String name) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		CMesRecipe cMesRecipe = new CMesRecipe();
		cMesRecipe.setTotalId(tid);
		cMesRecipe.setStationId(sid);
		cMesRecipe.setRecipeName(name);
		List<CMesRecipe> TotalRecipeAll = service.findAllRecipe(cMesRecipe); // 配方列表
		PageInfo<CMesRecipe> pageInfo = new PageInfo<>(TotalRecipeAll);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findRecipeById",method = RequestMethod.POST)
	@ApiOperation(value = "按id查询配方",notes = "按id查询配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "totalRecipeId", value = "总配方id", required = true, paramType = "query", dataType = "int")
	})
	public JSONObject findRecipeById(HttpServletRequest request){
		JSONObject json = new JSONObject();
		try {
			Integer totalRecipeId = EqualsUtil.integer(request, "totalRecipeId", "总配方id", true);
			List<CMesTotalRecipeT> totalRecipe = service.findTotalRecipeByParam(null,totalRecipeId);
			json.put("code", 0);
			json.put("totalRecipe", totalRecipe);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}

		return json;
	}

	/**
	 * 添加总配方
	 *
	 * @author FQZ
	 * @date 2020年3月27日下午4:14:17
	 */

	@RequestMapping(value = "/addtotalRecipe", method = RequestMethod.POST)
	@ApiOperation(value = "添加总配方", notes = "添加总配方")
	@OptionalLog(module="生产", module2="配方管理", method="添加总配方")
	public JSONObject addtotalRecipe(HttpServletRequest request, @ModelAttribute CMesTotalRecipeT cMesTotalRecipeT) {
		JSONObject json = new JSONObject();
		try {
			HttpSession session=request.getSession();
			// 产品Code
			String proCode=cMesTotalRecipeT.getProductionCode();
			if (StringUtil.eqNu(proCode)) {
				CMesProductionT productionT = cMesProductionService.findProIdAndName(null, proCode);
				if(null!=productionT){
					cMesTotalRecipeT.setProductionId(productionT.getId());
					session.setAttribute("productionCode", proCode);
				}else {
					json.put("code", 1);
					json.put("msg", "产品不存在");
					return json;
				}

			} else {
				CMesProductionT productionT = cMesProductionService.findProIdAndName(cMesTotalRecipeT.getProductionId(), null);
				if(null!=productionT){
				session.setAttribute("productionCode", productionT.getProductionName());
				}else {
					json.put("code", 1);
					json.put("msg", "产品不存在");
					return json;
				}
			}
			CMesLineT line =null;
			// 产线Code
			String lineCode=cMesTotalRecipeT.getLineCode()	;
			if (StringUtil.eqNu(lineCode)) {
				line = new CMesLineT();
				line.setName(lineCode);
				Map<String, Object> lineMap = lineservice.getLineByName(line);
				if(lineMap.size()>0){
					cMesTotalRecipeT.setLineId((Integer) lineMap.get("ID"));
					session.setAttribute("lineCode", lineCode);
				}else {
					json.put("code", 1);
					json.put("msg", "产线不存在");
					return json;
				}
			} else {
				line = new CMesLineT();
				line.setId(cMesTotalRecipeT.getLineId());
				List<CMesLineT> allLine = lineservice.findAllLine(line);
				if(allLine.size()>0){
					session.setAttribute("lineCode", allLine.get(0).getName());
				}else {
					json.put("code", 1);
					json.put("msg", "产线不存在");
					return json;
				}

			}

			service.addTotalRecipe(cMesTotalRecipeT);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 总配方回显
	 *
	 * @author FQZ
	 * @throws ServicesException
	 * @throws NumberFormatException
	 * @date 2020年3月28日上午9:39:29
	 */
	@RequestMapping(value="/findByidRecipeTotal",method=RequestMethod.POST)
	@ApiOperation(value = "总配方回显", notes = "总配方回显")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "总配方id", required = true, paramType = "query", dataType = "int") })
	public JSONObject findByidRecipeTotal(Integer id)throws NumberFormatException, ServicesException {
		List<CMesTotalRecipeT> findTotalRecipeByParam = service.findTotalRecipeByParam(null,id);
		JSONObject json = new JSONObject();
        try {
        	json.put("code", 0);
			json.put("msg", findTotalRecipeByParam.get(0));
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 修改总配方
	 *
	 * @author FQZ
	 * @date 2020年3月28日上午10:25:03
	 */
	@RequestMapping(value="/updatetotalRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "修改总配方", notes = "修改总配方")
	@OptionalLog(module = "生产", module2 = "配方管理", method = "修改总配方")
	public JSONObject updatetotalRecipe(HttpServletRequest request, @ModelAttribute CMesTotalRecipeT cMesTotalRecipeT) {
		JSONObject json = new JSONObject();
		try {
			service.updateTotalRecipe(cMesTotalRecipeT);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 修改默认值
	 *
	 * @author FQZ
	 * @date 2020年4月10日下午4:09:41
	 */
	@RequestMapping(value="/editstatus",method=RequestMethod.POST)
	@ApiOperation(value = "修改默认值", notes = "修改默认值")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "总配方id", required = true, paramType = "query", dataType = "int") })
	public JSONObject editstatus(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			Integer editStatus = service.editStatus(id, 1);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "修改失败");
			// TODO: handle exception
		}
		return json;
	}

	/**
	 * 根据产线查询工位
	 *
	 * @author FQZ
	 * @date 2020年4月10日下午4:39:18
	 */
	@RequestMapping(value="/getstation",method=RequestMethod.POST)
	@ApiOperation(value = "根据总配方ID查询工位", notes = "根据总配方ID查询工位")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "总配方id", required = true, paramType = "query", dataType = "int") })
	public JSONObject getstation(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			List<CMesTotalRecipeT> findTotalRecipeByParam = service.findTotalRecipeByParam(null, id);
			CMesStationT station = new CMesStationT();
			station.setLineId(findTotalRecipeByParam.get(0).getLineId());
			List<CMesStationT> findAllStation = staservice.findAllStation(station);
			json.put("code", 0);
			json.put("msg", findAllStation);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 添加配方
	 *
	 * @author FQZ
	 * @date 2020年3月28日上午11:32:23
	 */
	@RequestMapping(value="/addRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "添加配方", notes = "添加配方")
	@OptionalLog(module="生产", module2="配方管理", method="添加工位配方")
	public JSONObject addRecipe(HttpServletRequest request, @ModelAttribute CMesRecipe cMesRecipe) {
		JSONObject json = new JSONObject();
		try {
			HttpSession session=request.getSession();
              // 配方Code
			if (StringUtil.eqNu(cMesRecipe.getTotalCode())) {
				List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(cMesRecipe.getTotalCode(), null);
				if(totalRecipeByParam.size()>0){
					cMesRecipe.setTotalId(totalRecipeByParam.get(0).getId());
					session.setAttribute("totalCode", totalRecipeByParam.get(0).getTotalRecipeName());
				}else {
					json.put("code", 1);
					json.put("msg", "总配方不存在");
					return json;
				}

			} else {
				List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(null, cMesRecipe.getTotalId());
				if(totalRecipeByParam.size()>0){
					session.setAttribute("totalCode", totalRecipeByParam.get(0).getTotalRecipeName());
				}else {
					json.put("code", 1);
					json.put("msg", "总配方不存在");
					return json;
				}

			}
			// 工位Code
			if (StringUtil.eqNu(cMesRecipe.getStationCode())) {
				CMesStationT cMesStationT=new CMesStationT();
				cMesStationT.setStationName(cMesRecipe.getStationCode());
				List<CMesStationT> stationNameAndId = cMesStationTService.findStationNameAndId(cMesStationT);
				if(stationNameAndId.size()>0){
					cMesRecipe.setStationId(stationNameAndId.get(0).getId());
					session.setAttribute("stationCode", stationNameAndId.get(0).getStationName());
				}else {
					json.put("code", 1);
					json.put("msg", "工位不存在");
					return json;
				}

			} else {
				CMesStationT cMesStationT=new CMesStationT();
				cMesStationT.setId(cMesRecipe.getStationId());
				List<CMesStationT> stationNameAndId = cMesStationTService.findStationNameAndId(cMesStationT);
				if(stationNameAndId.size()>0){
					session.setAttribute("stationCode", stationNameAndId.get(0).getStationName());
				}else {
					json.put("code", 1);
					json.put("msg", "工位不存在");
					return json;
				}

			}
			service.addRecipe(cMesRecipe);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 配方回显
	 *
	 * @author FQZ
	 * @throws ServicesException
	 * @throws NumberFormatException
	 * @date 2020年3月28日下午1:36:23
	 */
	@RequestMapping(value="/findByIdRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "配方回显", notes = "配方回显")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "配方id", required = true, paramType = "query", dataType = "int") })
	public JSONObject findByIdRecipe(HttpServletRequest request, Integer id) throws NumberFormatException, ServicesException {
		List<CMesRecipe> findRecipeByParam = service.findRecipeByParam(null, id, null);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("msg", findRecipeByParam.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "失败");
			// TODO: handle exception
		}
		return  json;
	}

	/**
	 * 修改配方
	 *
	 * @author FQZ
	 * @date 2020年3月28日下午1:54:07
	 */
	@RequestMapping(value="/updateRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "修改配方", notes = "修改配方")
	public JSONObject updateRecipe(HttpServletRequest request, @ModelAttribute CMesRecipe recipe) {
		JSONObject json = new JSONObject();
		try {
			service.updateRecipe(recipe);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 删除总配方
	 *
	 * @author FQZ
	 * @date 2020年3月28日下午2:09:59
	 */
	@RequestMapping(value="/delTotalRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "删除总配方", notes = "删除总配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "总配方id", required = true, paramType = "query", dataType = "int") })
	@OptionalLog(module="生产", module2="配方管理", method="删除总配方")
	public JSONObject delTotalRecipe(Integer  id,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			HttpSession session=request.getSession();
			// totalRecipeName
			String totalRecipeName=request.getParameter("totalRecipeName");
			if (StringUtil.eqNu(totalRecipeName)) {
				// 根据总配方名称获取总配方id
				List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(totalRecipeName, null);
				if(totalRecipeByParam.size()>0){
					id=totalRecipeByParam.get(0).getId();
					session.setAttribute("totalRecipeName",totalRecipeName);
				}else {
					json.put("code", 1);
					json.put("msg", "总配方不存在");
					return json;
				}

			} else {
				// 根据总配方id获取总配方名称
				List<CMesTotalRecipeT> totalRecipeByParam = cMesRecipeService.findTotalRecipeByParam(null, id);
				if(totalRecipeByParam.size()>0){
				session.setAttribute("totalRecipeName",totalRecipeByParam.get(0).getTotalRecipeName());
				}else {
					json.put("code", 1);
					json.put("msg", "总配方不存在");
					return json;
				}
			}
			service.delTotalRecipe(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	/**
	 * 删除配方
	 *
	 * @author FQZ
	 * @date 2020年3月28日下午2:13:54
	 */
	@RequestMapping(value="/delRecipe",method=RequestMethod.POST)
	@ApiOperation(value = "删除配方", notes = "删除配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "配方id", required = true, paramType = "query", dataType = "int") })
	@OptionalLog(module="生产", module2="配方管理", method="删除工位配方")
	public JSONObject delRecipe(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			HttpSession session=request.getSession();
			// totalRecipeName recipeName
			String totalRecipeName=request.getParameter("totalRecipeName");
			String recipeName=request.getParameter("recipeName");
			if (StringUtil.eqNu(totalRecipeName)&&StringUtil.eqNu(recipeName)) {
				// 根据总配方id获取总配方名称和配方名称
				CMesRecipeT cMesRecipeT=new CMesRecipeT();
				cMesRecipeT.setRecipeName(recipeName);
				cMesRecipeT.setTotalRecipeName(totalRecipeName);
				CMesRecipeT recipeT = allotmentManagementService.findByIdToAndR(cMesRecipeT);
				if(null!=recipeT){
					id=recipeT.getId();
					session.setAttribute("totalRecipeName",totalRecipeName);
					session.setAttribute("recipeName",recipeName);
				}else {
					json.put("code", 1);
					json.put("msg", "配方不存在");
					return json;
				}

			} else {
				// 根据总配方id获取总配方名称和配方名称
				CMesRecipeT cMesRecipeT=new CMesRecipeT();
				cMesRecipeT.setId(id);
				CMesRecipeT recipeT = allotmentManagementService.findByIdToAndR(cMesRecipeT);
				if(null!=recipeT){
					session.setAttribute("totalRecipeName",recipeT.getTotalRecipeName());
					session.setAttribute("recipeName",recipeT.getRecipeName());
				}else {
					json.put("code", 1);
					json.put("msg", "配方不存在");
					return json;
				}

			}

			service.delRecipe(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}
