package com.skeqi.mes.controller.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.service.fqz.IQCCheckService;
import com.skeqi.mes.service.fqz.JlMaterialService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS iqc检测
 *
 */

@RequestMapping("material")
@Controller
public class CMesIQCManagerController {


	@Autowired
	QualityService qualityService;

	@Autowired
	CMesMaterialService materialService;

	@RequestMapping("/iqclist")
	public String iqclist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,15);

		Map<String, Object> map = new HashMap<String,Object>();

		String factoryNo = request.getParameter("factoryNo");
		String materialVoucher = request.getParameter("materialVoucher");
		String checkBatch = request.getParameter("checkBatch");
		String otigin = request.getParameter("otigin");
		String materialNo = request.getParameter("materialNo");
		String supplierName = request.getParameter("supplierName");
		String emp = request.getParameter("emp");
		String checkPerson = request.getParameter("checkPerson");
		String productionHandie = request.getParameter("productionHandie");
		String mname = request.getParameter("mname");
		if(productionHandie==null || productionHandie.endsWith("")){
			productionHandie="1";
		}
		String starttime = request.getParameter("act_start_time");
		String endtime = request.getParameter("act_stop_time");
		String status = request.getParameter("status");
		if(status==null || status.equals("")){
			status="1";
		}
		request.setAttribute("factoryNo", factoryNo);
		request.setAttribute("materialVoucher", materialVoucher);
		request.setAttribute("checkBatch", checkBatch);
		request.setAttribute("otigin", otigin);
		request.setAttribute("materialNo", materialNo);
		request.setAttribute("supplierName", supplierName);
		request.setAttribute("emp", emp);
		request.setAttribute("checkPerson", checkPerson);
		request.setAttribute("productionHandie", productionHandie);
		request.setAttribute("status", status);
		request.setAttribute("starttime", starttime);
		request.setAttribute("endtime", endtime);
		request.setAttribute("mname",mname);
		CMesIqcCheckT iqc = new CMesIqcCheckT();
		iqc.setFactoryNo(factoryNo);
		iqc.setMaterialName(mname);
		iqc.setMaterialVoucher(materialVoucher);
		iqc.setCheckBatch(checkBatch);
		iqc.setOtigin(otigin);
		iqc.setMaterialNo(materialNo);
		iqc.setSupplierName(supplierName);
		iqc.setEmp(emp);
		iqc.setCheckPerson(checkPerson);
		iqc.setProductionHandie(productionHandie);
		iqc.setStatus(Integer.parseInt(status));

		if(starttime!=null&&endtime!=null) {

			iqc.setStarttime(starttime);
			iqc.setEndtime(endtime);

		}
		map.put("iqc", iqc);
		try {
		List<CMesIqcCheckT> findAll = qualityService.findAllIQC(map);


		PageInfo<CMesIqcCheckT> pageinfo = new PageInfo<CMesIqcCheckT>(findAll,5);
		CMesJlMaterialT jl = new CMesJlMaterialT();
		List<CMesJlMaterialT> findAll2 = materialService.findAllMaterial(jl);
		request.setAttribute("findMaterial", findAll2);
		request.setAttribute("pageInfo",pageinfo);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "materiel_control/IQCManager";
	}

	@RequestMapping("/insertIQC")
	@ResponseBody
	public JSONObject insertIQC(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMesIqcCheckT c = new CMesIqcCheckT();
		String addmaterialVoucher = request.getParameter("addmaterialVoucher");
		String addcheckBatch = request.getParameter("addcheckBatch");
		String addotigin = request.getParameter("addotigin");
		String addmaterialNo = request.getParameter("addmaterialNo");
		String addcheckNum = request.getParameter("addcheckNum");
		String addngNum = request.getParameter("addngNum");
		String addfactoryNo = request.getParameter("addfactoryNo");
		String addseqNgNum = request.getParameter("addseqNgNum");
		String addcalculateUnit = request.getParameter("addcalculateUnit");
		String addsupplierName = request.getParameter("addsupplierName");
		String addemp = request.getParameter("addemp");
		String addcheckPerson = request.getParameter("addcheckPerson");
		String addmaterialDescribe = request.getParameter("addmaterialDescribe");
		String materialName = request.getParameter("mid");
		c.setMaterialDescribe(addmaterialDescribe);
		c.setMaterialName(materialName);
		c.setMaterialVoucher(addmaterialVoucher);
		c.setCheckBatch(addcheckBatch);
		c.setOtigin(addotigin);
		c.setMaterialNo(addmaterialNo);
		c.setCheckNum(Integer.parseInt(addcheckNum));
		c.setNgNum(Integer.parseInt(addngNum));
		c.setFactoryNo(addfactoryNo);
		c.setSeqNgNum(Integer.parseInt(addseqNgNum));
		c.setCalculateUnit(addcalculateUnit);
		c.setSupplierName(addsupplierName);
		c.setEmp(addemp);
		c.setCheckPerson(addcheckPerson);
		try {
			qualityService.addIQC(c);
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



	@RequestMapping("/findByid")
	@ResponseBody
	public JSONObject findByid(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesIqcCheckT findByid = qualityService.findIQCByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("findByid", findByid);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/updateIQC")
	@ResponseBody
	public JSONObject updateIQC(HttpServletRequest request){
		CMesIqcCheckT c = new CMesIqcCheckT();
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String addmaterialVoucher = request.getParameter("addmaterialVouchers");
		String addcheckBatch = request.getParameter("addcheckBatchs");
		String addotigin = request.getParameter("addotigins");
		String addmaterialNo = request.getParameter("addmaterialNos");
		String addcheckNum = request.getParameter("addcheckNums");
		String addngNum = request.getParameter("addngNums");
		String addfactoryNo = request.getParameter("addfactoryNos");
		String addseqNgNum = request.getParameter("addseqNgNums");
		String addcalculateUnit = request.getParameter("addcalculateUnits");
		String addsupplierName = request.getParameter("addsupplierNames");
		String addemp = request.getParameter("addemps");
		String addcheckPerson = request.getParameter("addcheckPersons");
		String addmaterialDescribe = request.getParameter("addmaterialDescribes");
		String materialName = request.getParameter("mids");
		c.setMaterialName(materialName);
		c.setMaterialDescribe(addmaterialDescribe);
		c.setMaterialVoucher(addmaterialVoucher);
		c.setCheckBatch(addcheckBatch);
		c.setOtigin(addotigin);
		c.setMaterialNo(addmaterialNo);
		c.setCheckNum(Integer.parseInt(addcheckNum));
		c.setNgNum(Integer.parseInt(addngNum));
		c.setFactoryNo(addfactoryNo);
		c.setSeqNgNum(Integer.parseInt(addseqNgNum));
		c.setCalculateUnit(addcalculateUnit);
		c.setSupplierName(addsupplierName);
		c.setEmp(addemp);
		c.setCheckPerson(addcheckPerson);
		c.setId(Integer.parseInt(id));
		try {
			qualityService.updateIQC(c);
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

	@RequestMapping("/fuhe")
	@ResponseBody
	public JSONObject fuhe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		if(name.equals("入库")){
			name="3";
		}else{
			name="2";
		}
			try {
				qualityService.updateStatus(Integer.parseInt(id), name);
				json.put("code", 0);
			}catch (ServicesException e) {
				json.put("code", e.getCode());
				json.put("msg", e.getMessage());
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			} catch (Exception e) {
				json.put("code", -1);
				json.put("msg", e.getMessage());
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
		return json;
	}

	@RequestMapping("/freezes")
	@ResponseBody
	public JSONObject freezes(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		try {
			qualityService.updateFreeze(Integer.parseInt(id), Integer.parseInt(status));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

}
