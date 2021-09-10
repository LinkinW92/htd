package com.skeqi.mes.controller.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesStationTService;

/***
 *
 * @author ENS 物料维修  1
 *
 */
@Controller
@RequestMapping("material")
public class CMesMaterialReController {

	@Autowired
	CMesMaterialService materialService;

	@Autowired
	CMesStationTService stationService;


	@RequestMapping("/materialReList")
	public String materialReList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception{
		PageHelper.startPage(page, 8);
		Map<String,Object> map=  new HashMap<>();
		String materialName = request.getParameter("materialName"); //物料name
		String status = request.getParameter("status");  //状态
		CMESMaterialRepairT materialRepair = new CMESMaterialRepairT();
		map.put("status",status);
		request.setAttribute("status",status);
		if(status!=null && status!=""){
			materialRepair.setStatus(Integer.parseInt(status));
		}
		String stationId = request.getParameter("stationId");  //工位id
		CMesJlMaterialT jlMaterial = new CMesJlMaterialT();
		if(materialName!=null && !materialName.equals("")){
			map.put("materialName",materialName);
			request.setAttribute("materialName", materialName);
			materialRepair.setMaterialName(materialName);
			jlMaterial.setMaterialName(materialName);
		}
		if(stationId!=null  && !stationId.equals("")){
				map.put("stationId",Integer.parseInt(stationId));
				request.setAttribute("stationId", stationId);
				materialRepair.setStationId(Integer.parseInt(stationId));
		}
		List<CMESMaterialRepairT> findAll = materialService.findAllMaterialRepair(materialRepair);
		CMesStationT station = new CMesStationT();
		List<CMesStationT> findStation = stationService.findAllStation(station);
		List<CMesJlMaterialT> findAll2 = materialService.findAllMaterial(jlMaterial);
		PageInfo<CMESMaterialRepairT> pageInfo = new PageInfo<CMESMaterialRepairT>(findAll,5);
		request.setAttribute("findMateial", findAll2);
		request.setAttribute("findStation",findStation);
		request.setAttribute("pageInfo",pageInfo);
		return "materiel_control/materialrelist";
	}

	@ResponseBody
	@RequestMapping("/insertM")
	public JSONObject insertM(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMESMaterialRepairT c = new CMESMaterialRepairT();
		String mid = request.getParameter("mid-hidden");
		if(mid==null||"".equals(mid)) {
			json.put("code", -2);
			json.put("msg", "请选择物料后再进行添加");
			return json;
		}

		String sid = request.getParameter("sid");
		String ename = request.getParameter("ename").trim();
		String note = request.getParameter("note").trim();
		c.setMaterialId(Integer.parseInt(mid));
		c.setStationId(Integer.parseInt(sid));
		c.setEmp(ename);
		c.setNote(note);


		try {
			materialService.addMaterialRepair(c);
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

	@RequestMapping("/updatestatus")
	@ResponseBody
	public JSONObject updatestatus(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMESMaterialRepairT c = new CMESMaterialRepairT();
		String id = request.getParameter("id");
		String rname = request.getParameter("rname");
		String reason = request.getParameter("reason");
		c.setId(Integer.parseInt(id));
		c.setRepairEmp(rname);
		c.setReason(reason);
		try {
			materialService.updateMaterialRepair(c);
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

	@RequestMapping("/delmaterial")
	@ResponseBody
	public JSONObject delmaterial(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			materialService.delMaterialRepair(Integer.parseInt(id));
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
