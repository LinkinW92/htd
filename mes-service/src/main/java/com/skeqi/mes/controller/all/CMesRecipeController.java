package com.skeqi.mes.controller.all;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("Allotment")
/***
 *
 * @author ENS  配方管理 1
 *
 */
public class CMesRecipeController {

	@Autowired
	CMesRecipeTService recipeService;

	@Autowired
	CMesProductionTService productionService;

	@Autowired
	CMesStationTService stationService;

	// 根据Id查询
	@RequestMapping("findById")
	@ResponseBody
	public  JSONObject findById(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesProductionRecipeT findProRecipeByid = recipeService.findProRecipeByid(Integer.parseInt(id));
			json.put("Allotment", findProRecipeByid);
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



	// 删除配方
	@RequestMapping("deleteAllotment")
	@ResponseBody
	public JSONObject deleteAllotment(Integer id) {
		JSONObject json=new JSONObject();
		try {
			recipeService.delRecipe(id);
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



	// 删除解绑
	@RequestMapping("deleteUntie")
	@ResponseBody
	public JSONObject deleteUntie(Integer id) {
		JSONObject json=new JSONObject();
		try {
			recipeService.delProRecipe(id);
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



	@RequestMapping("updateRecipe")
	@ResponseBody
	public JSONObject updateRecipe(Model model,HttpServletRequest request,CMesStationT cMesStationT,CMesProductionT cMesProductionT)
	{
		JSONObject json=new JSONObject();
		int id=Integer.parseInt(request.getParameter("id"));

		CMesRecipeT cMesRecipeT = new CMesRecipeT();
		String recipeName=request.getParameter("recipeName").trim();
		String productionId=request.getParameter("productionId").trim();
		String stationId=request.getParameter("stationId").trim();
		String recipeDiscription=request.getParameter("recipeDiscription").trim();
		cMesRecipeT.setId(id);
		cMesRecipeT.setRecipeName(recipeName);
		cMesRecipeT.setRecipeDiscription(recipeDiscription);
		try {
			recipeService.updateRecipe(cMesRecipeT);  //修改配方表
			CMesProductionRecipeT cMesProductionRecipeT = new CMesProductionRecipeT();
			cMesProductionRecipeT.setRecipeId(id);
			Integer prid = recipeService.findAllProRecipe(cMesProductionRecipeT).get(0).getId();
			cMesProductionRecipeT.setId(prid);
			cMesProductionRecipeT.setStationId(Integer.parseInt(stationId));
			cMesProductionRecipeT.setProductionId(Integer.parseInt(productionId));
			recipeService.updateProRecipe(cMesProductionRecipeT);
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


	@RequestMapping("addRecipe")
	@ResponseBody
	public  JSONObject addRecipe(HttpServletRequest request){
		JSONObject json=new JSONObject();
		//配方名称
		String recipeName=request.getParameter("recipeName").trim();
		//产品id
		String productionId=request.getParameter("productionId1").trim();
		//所属工位
		String stationId=request.getParameter("stationId1").trim();
		//详情
		String recipeDiscription = request.getParameter("recipeDiscription").trim();

		CMesRecipeT cMesRecipeT = new CMesRecipeT();
		cMesRecipeT.setRecipeName(recipeName);
		cMesRecipeT.setRecipeDiscription(recipeDiscription);
		CMesProductionRecipeT cMesProductionRecipeT = new CMesProductionRecipeT();
		cMesProductionRecipeT.setStationId(Integer.parseInt(stationId));
		cMesProductionRecipeT.setProductionId(Integer.parseInt(productionId));

		try {
			recipeService.addRecipe(cMesRecipeT);
			Integer id = recipeService.findAllRecipe(cMesRecipeT).get(0).getId();

			cMesProductionRecipeT.setRecipeId(id);
			recipeService.addProRecipe(cMesProductionRecipeT);
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





	@RequestMapping("findAll")
	public String findAll(HttpServletRequest request,Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
			@RequestParam(required = false,defaultValue = "1",value = "pages")Integer pages) {
		try {
			CMesRecipeT CMesRecipeT = new CMesRecipeT();
			PageHelper.startPage(page,5);
			List<CMesRecipeT> list = recipeService.findAllRecipe(CMesRecipeT);
			PageInfo pageInfo = new PageInfo<>(list,5);
			System.err.println("list==="+list.size());
			model.addAttribute("pageInfo", pageInfo);

			PageHelper.startPage(pages,8);
			List<CMesProductionRecipeT> listAllotment = recipeService.findAllProRecipe(null);
			System.err.println("listAllotment==="+listAllotment.size());
			PageInfo pageInfo1=new PageInfo<>(listAllotment,5);
			model.addAttribute("listAll", pageInfo1);

			List<CMesProductionT> listProduction = productionService.findAllProduction(null);
			System.err.println("listProduction==="+listProduction.size());

			model.addAttribute("findProduction", listProduction);
			List<CMesStationT> listStation=stationService.findAllStation(null);
			System.err.println("listStation==="+listStation.size());

			model.addAttribute("findStation", listStation);
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "recipe_control/recipeManager";
	}


}
