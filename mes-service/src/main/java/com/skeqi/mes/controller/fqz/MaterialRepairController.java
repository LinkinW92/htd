package com.skeqi.mes.controller.fqz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.fqz.JlMaterialService;
import com.skeqi.mes.service.fqz.MaterialRepairService;
/**
 * 物料维修
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.fqz
 * @date   : 2019年10月9日 上午11:53:16
 */
@Controller
@RequestMapping("material")
public class MaterialRepairController {


	@Autowired
	private MaterialRepairService service;
	@Autowired
	private JlMaterialService jlservice;

	/*
	 * @RequestMapping("/materialReList") public String
	 * materialReList(HttpServletRequest request,@RequestParam(required =
	 * false,defaultValue = "1",value = "page")Integer page) throws Exception{
	 * PageHelper.startPage(page, 8); Map<String,Object> map= new HashMap<>();
	 * String materialName = request.getParameter("materialName"); //物料name String
	 * status = request.getParameter("status"); //状态 map.put("status",status);
	 * request.setAttribute("status",status); String stationId =
	 * request.getParameter("stationId"); //工位id if(materialName!=null &&
	 * !materialName.equals("")){ map.put("materialName",materialName);
	 * request.setAttribute("materialName", materialName); } if(stationId!=null &&
	 * !stationId.equals("")){ map.put("stationId",Integer.parseInt(stationId));
	 * request.setAttribute("stationId", stationId); } List<CMESMaterialRepairT>
	 * findAll = service.findAll(map); List<CMesStationT> findStation =
	 * service.findStation(); List<CMesMaterialT> findMateial =
	 * service.findMateial(); Map<String,Object> maps= new HashMap<>();
	 * List<CMesJlMaterialT> findAll2 = jlservice.findAll(maps);
	 * PageInfo<CMESMaterialRepairT> pageInfo = new
	 * PageInfo<CMESMaterialRepairT>(findAll,5); request.setAttribute("findMateial",
	 * findAll2); request.setAttribute("findStation",findStation);
	 * request.setAttribute("pageInfo",pageInfo); return
	 * "materiel_control/materialrelist"; }
	 *
	 * @ResponseBody
	 *
	 * @RequestMapping("/insertM") public Map<String,Object>
	 * insertM(HttpServletRequest request){ Map<String,Object> map = new
	 * HashMap<>(); CMESMaterialRepairT c = new CMESMaterialRepairT(); String mid =
	 * request.getParameter("mid-hidden"); if(mid==null || mid.equals("")){
	 * map.put("msg",0); return map; } String sid = request.getParameter("sid");
	 * String ename = request.getParameter("ename").trim(); String note =
	 * request.getParameter("note").trim(); c.setMaterialId(Integer.parseInt(mid));
	 * c.setStationId(Integer.parseInt(sid)); c.setEmp(ename); c.setNote(note); try
	 * { service.insertM(c); map.put("msg",1); } catch (Exception e) { // TODO:
	 * handle exception map.put("msg",2); } return map; }
	 *
	 * @RequestMapping("/updatestatus")
	 *
	 * @ResponseBody public Map<String,Object> updatestatus(HttpServletRequest
	 * request){ Map<String,Object> map = new HashMap<>(); CMESMaterialRepairT c =
	 * new CMESMaterialRepairT(); String id = request.getParameter("id"); String
	 * rname = request.getParameter("rname"); String reason =
	 * request.getParameter("reason"); c.setId(Integer.parseInt(id));
	 * c.setRepairEmp(rname); c.setReason(reason); try { service.updatestatus(c);
	 * map.put("msg",1); } catch (Exception e) { // TODO: handle exception
	 * map.put("msg",2); } return map; }
	 *
	 * @RequestMapping("/delmaterial")
	 *
	 * @ResponseBody public Map<String,Object> delmaterial(HttpServletRequest
	 * request){ Map<String,Object> map = new HashMap<>(); String id =
	 * request.getParameter("id"); try { service.delmaterial(id); map.put("msg",1);
	 * } catch (Exception e) { // TODO: handle exception map.put("msg",2); } return
	 * map; }
	 */
}
