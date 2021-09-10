package com.skeqi.mes.controller.all;

import java.util.List;

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
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

@RequestMapping("recipe")
@Controller
public class CMesRecipeTController {

	@Autowired
	private CMesRecipeService service;

	@Autowired
	private CMesProductionTService proservice;

	@Autowired
	private CMesLineTService lineservice;

	@Autowired
	private CMesStationTService staservice;

	/**
	 * 进入配方页面
	* @author FQZ
	* @date 2020年3月27日下午4:14:58
	 */
	@RequestMapping("/RecipeAll")
	public String RecipeAll(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
													   @RequestParam(required = false,defaultValue = "1",value = "page1")Integer page1) throws Exception{

		String selectpro = request.getParameter("selectpro");  //总配方名称
		String flag = request.getParameter("flag");

		PageHelper.startPage(page,8);
		CMesTotalRecipeT totalRecipe = new CMesTotalRecipeT();
		if(selectpro!=null && selectpro!=""){
			totalRecipe.setProductionId(Integer.parseInt(selectpro));
		}
		List<CMesTotalRecipeT> findAllTotalRecipe = service.findAllTotalRecipe(totalRecipe);   //总配方列表
		PageInfo<CMesTotalRecipeT> pageInfo = new PageInfo<CMesTotalRecipeT>(findAllTotalRecipe,5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("selectpro", selectpro);
		request.setAttribute("flag",flag);


		String recipeName = request.getParameter("recipeName");
		String staName = request.getParameter("staName");
		String tname = request.getParameter("tname");
		CMesRecipe c = new CMesRecipe();
		c.setRecipeName(recipeName);
		if(staName!=null && staName!=""){
			c.setStationId(Integer.parseInt(staName));
			c.setTotalId(Integer.parseInt(tname));
		}

		PageHelper.startPage(page1,8);
		List<CMesRecipe> findAllRecipe = service.findAllRecipe(c);
		PageInfo<CMesRecipe> pageInfo1 = new PageInfo<CMesRecipe>(findAllRecipe,5);
		List<CMesStationT> findStation = service.findStation(null);

		List<CMesProductionT> findAllProduction = proservice.findAllProduction(null);
		List<CMesLineT> findAllLine = lineservice.findAllLine(null);
		CMesStationT station = new CMesStationT();
		station.setLineId(findAllLine.get(0).getId());
		List<CMesStationT> StationAll = staservice.findAllStation(station);

		List<CMesTotalRecipeT> TotalRecipeAll = service.findAllTotalRecipe(null);   //总配方列表

		request.setAttribute("tname",tname);
		request.setAttribute("TotalRecipeAll", TotalRecipeAll);
		request.setAttribute("StationAll", StationAll);
		request.setAttribute("findAllProduction", findAllProduction);
		request.setAttribute("findAllLine",findAllLine);
		request.setAttribute("recipeName", recipeName);
		request.setAttribute("staName", staName);
		request.setAttribute("findStation",findStation);
		request.setAttribute("pageInfo1",pageInfo1);
		return "recipe_control/recipeManagers";
	}

	/**
	 * 添加总配方
	* @author FQZ
	* @date 2020年3月27日下午4:14:17
	 */
	@RequestMapping("/addtotalRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="添加总配方")
	public JSONObject addtotalRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String totalName = request.getParameter("totalName");
		String addrecipedescribe = request.getParameter("addrecipedescribe");
		String addpro = request.getParameter("addpro");
		String addline = request.getParameter("addline");
		CMesTotalRecipeT recipe = new CMesTotalRecipeT();
		recipe.setTotalRecipeName(totalName);
		recipe.setTotalRecipeDescribe(addrecipedescribe);
		recipe.setLineId(Integer.parseInt(addline));
		recipe.setProductionId(Integer.parseInt(addpro));
		try {
			service.addTotalRecipe(recipe);
			json.put("code", 1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
			// TODO: handle exception
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}

	/**
	 * 总配方回显
	* @author FQZ
	 * @throws ServicesException
	 * @throws NumberFormatException
	* @date 2020年3月28日上午9:39:29
	 */
	@RequestMapping("/findByidRecipeTotal")
	@ResponseBody
	public CMesTotalRecipeT findByidRecipeTotal(HttpServletRequest request) throws NumberFormatException, ServicesException{
		String parameter = request.getParameter("id");
		List<CMesTotalRecipeT> findTotalRecipeByParam = service.findTotalRecipeByParam(null, Integer.parseInt(parameter));
		return findTotalRecipeByParam.get(0);
	}

	/**
	 * 修改总配方
	* @author FQZ
	* @date 2020年3月28日上午10:25:03
	 */
	@RequestMapping("/updatetotalRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="修改总配方")
	public JSONObject updatetotalRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String updateRecipeDescribe = request.getParameter("updateRecipeDescribe");
		String updateRecipeName = request.getParameter("updateRecipeName");
		String updatepro = request.getParameter("updatepro");
		String updateline = request.getParameter("updateline");
		String id = request.getParameter("id");
		CMesTotalRecipeT recipe = new CMesTotalRecipeT();
		recipe.setTotalRecipeName(updateRecipeName);
		recipe.setTotalRecipeDescribe(updateRecipeDescribe);
		recipe.setId(Integer.parseInt(id));
		recipe.setLineId(Integer.parseInt(updateline));
		recipe.setProductionId(Integer.parseInt(updatepro));
		try {
			service.updateTotalRecipe(recipe);
			json.put("code",1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}

	/**
	 * 修改默认值
	* @author FQZ
	* @date 2020年4月10日下午4:09:41
	 */
	@RequestMapping("/editstatus")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="修改默认值")
	public JSONObject editstatus(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String parameter = request.getParameter("id");
		try {
			Integer editStatus = service.editStatus(Integer.parseInt(parameter), 1);
			json.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code",1);
			// TODO: handle exception
		}
		return json;
	}

	/**
	 * 根据产线查询工位
	* @author FQZ
	* @date 2020年4月10日下午4:39:18
	 */
	@RequestMapping("/getstation")
	@ResponseBody
	public List<CMesStationT> getstation(HttpServletRequest request){
		String parameter = request.getParameter("id");
		try {
			List<CMesTotalRecipeT> findTotalRecipeByParam = service.findTotalRecipeByParam(null, Integer.parseInt(parameter));
			CMesStationT station = new CMesStationT();
			station.setLineId(findTotalRecipeByParam.get(0).getLineId());
			List<CMesStationT> findAllStation = staservice.findAllStation(station);
			return findAllStation;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return null;
	}
	/**
	 * 添加配方
	* @author FQZ
	* @date 2020年3月28日上午11:32:23
	 */
	@RequestMapping("/addRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="添加配方")
	public JSONObject addRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String addRecipeName = request.getParameter("addRecipeName");
		String addRecipeTotal = request.getParameter("addRecipeTotal");
		String addstaName = request.getParameter("addstaName");
		String addrecipedescribe = request.getParameter("addrecipedescribe");
		CMesRecipe recipe = new CMesRecipe();
		recipe.setRecipeName(addRecipeName);
		recipe.setRecipeDiscription(addrecipedescribe);
		recipe.setTotalId(Integer.parseInt(addRecipeTotal));
		recipe.setStationId(Integer.parseInt(addstaName));
		try {
			service.addRecipe(recipe);
			json.put("code",1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}

	/**
	 * 配方回显
	* @author FQZ
	 * @throws ServicesException
	 * @throws NumberFormatException
	* @date 2020年3月28日下午1:36:23
	 */
	@RequestMapping("/findByIdRecipe")
	@ResponseBody
	public CMesRecipe findByIdRecipe(HttpServletRequest request) throws NumberFormatException, ServicesException{
		String id = request.getParameter("id");
		List<CMesRecipe> findRecipeByParam = service.findRecipeByParam(null,Integer.parseInt(id),null);
		return findRecipeByParam.get(0);
	}

	/**
	 * 修改配方
	* @author FQZ
	* @date 2020年3月28日下午1:54:07
	 */
	@RequestMapping("/updateRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="修改配方")
	public JSONObject updateRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String updateRecipeNames = request.getParameter("updateRecipeNames");
		String updatestaName = request.getParameter("updatestaName");
		String updateRecipeTotal = request.getParameter("updateRecipeTotal");
		String updateescribe = request.getParameter("updateescribe");
		String recipeId = request.getParameter("recipeId");
		CMesRecipe recipe = new CMesRecipe();
		recipe.setRecipeName(updateRecipeNames);
		recipe.setStationId(Integer.parseInt(updatestaName));
		recipe.setTotalId(Integer.parseInt(updateRecipeTotal));
		recipe.setRecipeDiscription(updateescribe);
		recipe.setId(Integer.parseInt(recipeId));
		try {
			service.updateRecipe(recipe);
			json.put("code",1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}

	/**
	 * 删除总配方
	* @author FQZ
	* @date 2020年3月28日下午2:09:59
	 */
	@RequestMapping("/delTotalRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="删除总配方")
	public JSONObject delTotalRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String id = request.getParameter("id");
		try {
			service.delTotalRecipe(Integer.parseInt(id));
			json.put("code",1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}

	/**
	 * 删除配方
	* @author FQZ
	* @date 2020年3月28日下午2:13:54
	 */
	@RequestMapping("/delRecipe")
	@ResponseBody
	@OptionalLog(module="生产", module2="配方管理", method="删除配方")
	public JSONObject delRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String id = request.getParameter("id");
		try {
			service.delRecipe(Integer.parseInt(id));
			json.put("code",1);
		} catch (ServicesException e) {
			json.put("msg",e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
		}
		return json;
	}
}

