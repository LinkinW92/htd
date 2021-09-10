package com.skeqi.mes.controller.wjp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.wjp.AllotmentManagementService;

@Controller
@RequestMapping("Allotment")

public class AllotmentManagementController {

	@Autowired
	private AllotmentManagementService allotmentManagementService;

	/*
	 * // 查询所有
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("findAll") public String findAll(HttpServletRequest
	 * request,Model model,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page,
	 *
	 * @RequestParam(required = false,defaultValue = "1",value = "pages")Integer
	 * pages) { Map<String, Object> map = new HashMap<>();
	 * PageHelper.startPage(page,5); List<CMesRecipeT> list =
	 * allotmentManagementService.findAll(map); PageInfo pageInfo = new
	 * PageInfo<>(list,5); model.addAttribute("pageInfo", pageInfo);
	 *
	 * Map<String,Object> map1=new HashMap<>(); PageHelper.startPage(pages,8);
	 * List<CMesProductionRecipeT> listAllotment =
	 * allotmentManagementService.findAllAllotment(map1); PageInfo pageInfo1=new
	 * PageInfo<>(listAllotment,5); model.addAttribute("listAll", pageInfo1);
	 *
	 * List<CMesProductionT> listProduction =
	 * allotmentManagementService.findProduction();
	 * model.addAttribute("findProduction", listProduction);
	 *
	 * List<CMesStationT> listStation=allotmentManagementService.findStation();
	 * model.addAttribute("findStation", listStation); return
	 * "recipe_control/recipeManager"; }
	 */
	/*
	 * // 根据Id查询
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("findById") public @ResponseBody Map
	 * findById(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("recipeId", id);
	 * List<CMesProductionRecipeT> prs = null; try { prs =
	 * allotmentManagementService.productionRecipe(map); } catch (Exception e) { }
	 * if (prs!=null&&prs.size()>0) { map.put("msg", 1); map.put("recipeId", id);
	 * List<CMesProductionRecipeT> prList =
	 * allotmentManagementService.findAllAllotmentByrId(map); map.put("pr",
	 * prList.get(0)); } CMesRecipeT cMesRecipeT =
	 * allotmentManagementService.findById(Integer.parseInt(id));
	 * map.put("Allotment", cMesRecipeT); return map; }
	 */


	// 修改配方
	/*
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("updateRecipe") public @ResponseBody Map updateRecipe(Model
	 * model,HttpServletRequest request,CMesStationT cMesStationT,CMesProductionT
	 * cMesProductionT,CMesRecipeT cMesRecipeT) { Map<String, Object> map = new
	 * HashMap<>(); int id=Integer.parseInt(request.getParameter("id")); String
	 * recipeName=request.getParameter("recipeName").trim(); String
	 * productionId=request.getParameter("productionId").trim(); String
	 * stationId=request.getParameter("stationId").trim(); String
	 * recipeDiscription=request.getParameter("recipeDiscription").trim();
	 * cMesRecipeT.setRecipeName(recipeName);
	 * cMesRecipeT.setRecipeDiscription(recipeDiscription); map.put("id", id);
	 * map.put("recipeId", id); List<CMesProductionRecipeT> prList =
	 * allotmentManagementService.productionRecipe(map); CMesRecipeT
	 * list=allotmentManagementService.findById(id); String
	 * NameRecipe=list.getRecipeName().trim(); if(request.getParameter("stationId")
	 * != "" && request.getParameter("productionId") != "") //判断是否选择了所属工位和所属名称 {
	 * map.put("stationId", Integer.parseInt(stationId)); map.put("productionId",
	 * Integer.parseInt(productionId)); if(!NameRecipe.equals(recipeName))
	 * //判断是否改了配方名称 { int i=allotmentManagementService.NotRepeatable(recipeName);
	 * if(i>0) //大于0说明配方名称已存在 { map.put("msg", 1); } else { map.put("msg", 0);
	 * if(prList.size()<1) //判断原来是否有配方清单 {
	 * allotmentManagementService.addProRec(map); } else {
	 * allotmentManagementService.updateAllotment(cMesRecipeT);
	 * allotmentManagementService.updateproductionRecipe(map); } } } else {
	 * map.put("msg", 0); if(prList.size()<1) {
	 * allotmentManagementService.addProRec(map); } else {
	 * allotmentManagementService.updateAllotment(cMesRecipeT);
	 * allotmentManagementService.updateproductionRecipe(map); } } } else {
	 * if(prList.size()>0) //判断原来是否有配方清单，有则不能选择空的工位和产品 { map.put("msg", 2); } else {
	 * map.put("msg", 0); allotmentManagementService.updateAllotment(cMesRecipeT); }
	 * } return map; }
	 */

	/*
	 * // 删除配方
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("deleteAllotment") public @ResponseBody Map
	 * deleteAllotment(Integer id) { Map<String, Object> map=new HashMap<String,
	 * Object>(); try { int i=allotmentManagementService.deleteAllotmentAs(id);
	 * if(i>0){ map.put("msg", 1); }else{
	 * allotmentManagementService.deleteAllotment(id); } } catch (Exception e) { }
	 * return map; }
	 */

	/*
	 * // 删除解绑
	 *
	 * @RequestMapping("deleteUntie") public String deleteUntie(Integer id) { try {
	 * allotmentManagementService.deleteUntie(id); } catch (Exception e) { } return
	 * "redirect:/Allotment/findAll"; }
	 */

	/*
	 * //新增配方
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("addRecipe") public @ResponseBody Map addRecipe(CMesRecipeT
	 * cMesRecipeT,CMesProductionRecipeT cMesProductionRecipeT,HttpServletRequest
	 * request){ Map<String, Object> map=new HashMap<>(); String
	 * recipeName=request.getParameter("recipeName").trim(); int
	 * i=allotmentManagementService.NotRepeatable(recipeName); if(cMesRecipeT !=
	 * null){ //System.out.println(i); if(i>0) { map.put("msg", 1); }else {
	 * map.put("msg", 0); allotmentManagementService.addRectipe(cMesRecipeT); }
	 * //allotmentManagementService.addRectipe(cMesRecipeT);
	 * if(cMesProductionRecipeT != null){ if(request.getParameter("stationId1")!=""
	 * &&
	 * request.getParameter("productionId1")!=""&&request.getParameter("stationId1")
	 * !=null && request.getParameter("productionId1")!=null) { if(i>0){
	 * map.put("msg", 1); } else{ String
	 * productionId=request.getParameter("productionId1").trim(); int recipeId=
	 * allotmentManagementService.getMaxNumber(); String
	 * stationId=request.getParameter("stationId1").trim(); map.put("productionId",
	 * productionId); map.put("recipeId", recipeId); map.put("stationId",
	 * stationId); map.put("msg", 0); allotmentManagementService.addProRec(map); } }
	 * } } return map; }
	 */
}




