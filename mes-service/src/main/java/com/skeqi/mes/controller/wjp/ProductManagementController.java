package com.skeqi.mes.controller.wjp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.wjp.ProductManagementService;
import com.skeqi.mes.service.yin.DeviceService;

@Controller
@RequestMapping("product")
public class ProductManagementController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	private ProductManagementService productManagementService;
	/*
	 * // 产品列表
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("findAll") public String finAll(Model
	 * model,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page) {
	 *
	 * Map<String, Object> map = new HashMap<>(); List<CMesLabelManagerT>
	 * labelManagerList = deviceService.listLabelManager(map);
	 * PageHelper.startPage(page,5); List<CMesProductionT> list =
	 * productManagementService.findAll(); PageInfo pageInfo = new
	 * PageInfo<>(list,5); model.addAttribute("productList", list);
	 * model.addAttribute("pageInfo", pageInfo);
	 * model.addAttribute("labelManagerList", labelManagerList); return
	 * "recipe_control/proManager"; }
	 *
	 * // 产品新增
	 *
	 * @SuppressWarnings("unused")
	 *
	 * @RequestMapping(value="addAll",method = RequestMethod.POST, consumes =
	 * "multipart/form-data") public @ResponseBody Map<String, Object>
	 * addAll(@RequestParam(required=false)MultipartFile file,CMesProductionT
	 * cMesProductionT,HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String path = null; String url = null; if(!file.isEmpty()){
	 * String pictures = file.getOriginalFilename(); //得到上传时的文件名 String name = new
	 * SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	 * //获取当前时间并转换为string类型 String extension = FilenameUtils.getExtension(pictures);
	 * //获取文件后缀 path = name+"."+extension; if
	 * (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("jpeg"
	 * )) { map.put("msg", 3); return map; } url = "E:\\upload"; //要保存的路径 File dir =
	 * new File(url); if(!dir.exists()) { //判断这个路径不存在 dir.mkdirs(); //如果不存在就创建 }
	 * cMesProductionT.setPath(path); } if(cMesProductionT != null){ String
	 * productionName=request.getParameter("productionName").trim(); String
	 * productionType=request.getParameter("productionType").trim();
	 * cMesProductionT.setProductionName(productionName);
	 * cMesProductionT.setProductionType(productionType); int
	 * i=productManagementService.NoRepeat(cMesProductionT); if(i>0){ map.put("msg",
	 * 2); } else { String productionVr = cMesProductionT.getProductionVr(); try {
	 * productManagementService.addProduction(cMesProductionT); if(!file.isEmpty()){
	 * file.transferTo(new File(url+"/"+path)); } map.put("msg", 0); } catch
	 * (Exception e) { } } } return map; }
	 *
	 * // 根据id查询
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("findProductById") public @ResponseBody Map
	 * findProductById(HttpSession session,HttpServletRequest request,Model model) {
	 * String id = request.getParameter("id"); Map<String, Object> map = new
	 * HashMap<>(); CMesProductionT cMesProductionT =
	 * productManagementService.findProductionById(Integer.parseInt(id));
	 * map.put("cMesProductionT", cMesProductionT);
	 * session.setAttribute("cMesProductionT", cMesProductionT); return map; }
	 *
	 * // 产品修改
	 *
	 * @RequestMapping("updateProduct") public @ResponseBody Map
	 * updateProduct(HttpServletRequest request, CMesProductionT cMesProductionT) {
	 * String id = request.getParameter("id"); Map<String, Object> map = new
	 * HashMap<>(); map.put("productionName", cMesProductionT.getProductionName());
	 * map.put("productionSeries", cMesProductionT.getProductionSeries());
	 * map.put("productionType", cMesProductionT.getProductionType());
	 * map.put("productionSte", cMesProductionT.getProductionSte());
	 * map.put("productionVr", cMesProductionT.getProductionVr());
	 * map.put("productionDiscription", cMesProductionT.getProductionDiscription());
	 * map.put("labelTypeId", cMesProductionT.getProductionPrintId()); map.put("id",
	 * Integer.parseInt(id)); String
	 * productionName=request.getParameter("productionName").trim(); String
	 * productionType=request.getParameter("productionType").trim();
	 * cMesProductionT.setProductionName(productionName);
	 * cMesProductionT.setProductionType(productionType); CMesProductionT
	 * cMesProduction =
	 * productManagementService.findProductionById(Integer.parseInt(id)); String
	 * name=cMesProduction.getProductionName(); String
	 * type=cMesProduction.getProductionType(); if(!name.equals(productionName) ||
	 * !type.equals(productionType)){ int
	 * i=productManagementService.NoRepeat(cMesProductionT); if(i>0) {
	 * map.put("msg", 2); } else { productManagementService.updateProduction(map);
	 * map.put("msg", 0); } } else { try {
	 * productManagementService.updateProduction(map); } catch (Exception e) {
	 * e.printStackTrace(); } } return map; }
	 *
	 * // 产品删除
	 *
	 * @RequestMapping("delectById")
	 *
	 * @ResponseBody public Map<String, Object> delectById(Model model, Integer id)
	 * { Map<String, Object> map = new HashMap<>(); map.put("id", id); StringBuilder
	 * str = new StringBuilder(); //验证工艺配置是否正关联此产品 int
	 * countProductionProcessByProductionId =
	 * productManagementService.countProductionProcessByProductionId(map);
	 * //验证BOM管理是否正关联此产品 int countBomByProductionId =
	 * productManagementService.countBomByProductionId(map); //验证产品配方中间表是否正关联此产品 int
	 * countRecipeByProductionId =
	 * productManagementService.countRecipeByProductionId(map); //验证计划配置是否正关联此产品 int
	 * countPlanByProductionId =
	 * productManagementService.countPlanByProductionId(map); //验证生成条码是否正关联此产品 int
	 * countBarCodeByProduction =
	 * productManagementService.countBarCodeByProduction(map); if
	 * (countProductionProcessByProductionId>0) { str.append("工艺配置,"); } if
	 * (countBomByProductionId>0) { str.append("BOM管理,"); } if
	 * (countRecipeByProductionId>0) { str.append("配方管理,"); } if
	 * (countPlanByProductionId>0) { str.append("计划配置,"); } if
	 * (countBarCodeByProduction>0) { str.append("生成条码,"); } if
	 * (countProductionProcessByProductionId>0||countBomByProductionId>0||
	 * countRecipeByProductionId>0||
	 * countPlanByProductionId>0||countBarCodeByProduction>0) { map.put("msg", -1);
	 * map.put("str", str); return map; } try { map.put("msg", 0);
	 * productManagementService.deleteProduction(id); } catch (Exception e) { }
	 * return map; }
	 *
	 * // 分页
	 *
	 * @SuppressWarnings({ "rawtypes", "unused" })
	 *
	 * @RequestMapping("productionList") public Object planList(HttpServletRequest
	 * request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page, Model model) { Map map = new HashMap<>(); PageHelper.startPage(page,5);
	 * List<CMesProductionT> rplanList = productManagementService.findAll();
	 * PageInfo pageInfo = new PageInfo<>(rplanList, 5);
	 * request.setAttribute("pageInfo", pageInfo); List<CMesProductionT> list =
	 * productManagementService.findAll(); model.addAttribute("productList", list);
	 * return "recipe_control/proManager"; }
	 */

}
