package com.skeqi.mes.controller.fqz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.service.fqz.IQCCheckService;
import com.skeqi.mes.service.fqz.JlMaterialService;
import com.skeqi.mes.util.ExcelUtil;

@RequestMapping("material")
@Controller
public class IQCCheckController {
//
//	@Autowired
//	private IQCCheckService service;
//
//	@Autowired
//	private JlMaterialService jlservice;
//
//	@RequestMapping("/iqclist")
//	public String iqclist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page,15);
//		Map<String,Object> map = new HashMap<>();
//		String factoryNo = request.getParameter("factoryNo");
//		String materialVoucher = request.getParameter("materialVoucher");
//		String checkBatch = request.getParameter("checkBatch");
//		String otigin = request.getParameter("otigin");
//		String materialNo = request.getParameter("materialNo");
//		String supplierName = request.getParameter("supplierName");
//		String emp = request.getParameter("emp");
//		String checkPerson = request.getParameter("checkPerson");
//		String productionHandie = request.getParameter("productionHandie");
//		String mname = request.getParameter("mname");
//		if(productionHandie==null || productionHandie.endsWith("")){
//			productionHandie="1";
//		}
//		String starttime = request.getParameter("act_start_time");
//		String endtime = request.getParameter("act_stop_time");
//		String status = request.getParameter("status");
//		if(status==null || status.equals("")){
//			status="1";
//		}
//		request.setAttribute("factoryNo", factoryNo);
//		request.setAttribute("materialVoucher", materialVoucher);
//		request.setAttribute("checkBatch", checkBatch);
//		request.setAttribute("otigin", otigin);
//		request.setAttribute("materialNo", materialNo);
//		request.setAttribute("supplierName", supplierName);
//		request.setAttribute("emp", emp);
//		request.setAttribute("checkPerson", checkPerson);
//		request.setAttribute("productionHandie", productionHandie);
//		request.setAttribute("status", status);
//		request.setAttribute("starttime", starttime);
//		request.setAttribute("endtime", endtime);
//		request.setAttribute("mname",mname);
//		map.put("factoryNo", factoryNo);
//		map.put("materialName", mname);
//		map.put("materialVoucher", materialVoucher);
//		map.put("checkBatch", checkBatch);
//		map.put("otigin", otigin);
//		map.put("materialNo", materialNo);
//		map.put("supplierName", supplierName);
//		map.put("emp", emp);
//		map.put("checkPerson", checkPerson);
//		map.put("productionHandie", productionHandie);
//		map.put("status", status);
//		map.put("starttime", starttime);
//		map.put("endtime", endtime);
//		List<CMesIqcCheckT> findAll = service.findAll(map);
//		PageInfo<CMesIqcCheckT> pageinfo = new PageInfo<CMesIqcCheckT>(findAll,5);
//		Map<String,Object> maps = new HashMap<>();
//		List<CMesJlMaterialT> findAll2 = jlservice.findAll(maps);
//		request.setAttribute("findMaterial", findAll2);
//		request.setAttribute("pageInfo",pageinfo);
//		return "materiel_control/IQCManager";
//	}
//
//	@RequestMapping("/insertIQC")
//	@ResponseBody
//	public Map<String,Object> insertIQC(HttpServletRequest request){
//		CMesIqcCheckT c = new CMesIqcCheckT();
//		Map<String,Object> map = new HashMap<>();
//		String addmaterialVoucher = request.getParameter("addmaterialVoucher");
//		String addcheckBatch = request.getParameter("addcheckBatch");
//		String addotigin = request.getParameter("addotigin");
//		String addmaterialNo = request.getParameter("addmaterialNo");
//		String addcheckNum = request.getParameter("addcheckNum");
//		String addngNum = request.getParameter("addngNum");
//		String addfactoryNo = request.getParameter("addfactoryNo");
//		String addseqNgNum = request.getParameter("addseqNgNum");
//		String addcalculateUnit = request.getParameter("addcalculateUnit");
//		String addsupplierName = request.getParameter("addsupplierName");
//		String addemp = request.getParameter("addemp");
//		String addcheckPerson = request.getParameter("addcheckPerson");
//		String addmaterialDescribe = request.getParameter("addmaterialDescribe");
//		String materialName = request.getParameter("mid");
//		c.setMaterialDescribe(addmaterialDescribe);
//		c.setMaterialName(materialName);
//		c.setMaterialVoucher(addmaterialVoucher);
//		c.setCheckBatch(addcheckBatch);
//		c.setOtigin(addotigin);
//		c.setMaterialNo(addmaterialNo);
//		c.setCheckNum(Integer.parseInt(addcheckNum));
//		c.setNgNum(Integer.parseInt(addngNum));
//		c.setFactoryNo(addfactoryNo);
//		c.setSeqNgNum(Integer.parseInt(addseqNgNum));
//		c.setCalculateUnit(addcalculateUnit);
//		c.setSupplierName(addsupplierName);
//		c.setEmp(addemp);
//		c.setCheckPerson(addcheckPerson);
//		try {
//			service.insertIQC(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/delIQC")
//	@ResponseBody
//	public Map<String,Object> delIQC(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		try {
//			service.deleteIQC(id);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/findByid")
//	@ResponseBody
//	public CMesIqcCheckT findByid(HttpServletRequest request){
//		String id = request.getParameter("id");
//		CMesIqcCheckT findByid = service.findByid(id);
//		return findByid;
//	}
//
//	@RequestMapping("/updateIQC")
//	@ResponseBody
//	public Map<String,Object> updateIQC(HttpServletRequest request){
//		CMesIqcCheckT c = new CMesIqcCheckT();
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String addmaterialVoucher = request.getParameter("addmaterialVouchers");
//		String addcheckBatch = request.getParameter("addcheckBatchs");
//		String addotigin = request.getParameter("addotigins");
//		String addmaterialNo = request.getParameter("addmaterialNos");
//		String addcheckNum = request.getParameter("addcheckNums");
//		String addngNum = request.getParameter("addngNums");
//		String addfactoryNo = request.getParameter("addfactoryNos");
//		String addseqNgNum = request.getParameter("addseqNgNums");
//		String addcalculateUnit = request.getParameter("addcalculateUnits");
//		String addsupplierName = request.getParameter("addsupplierNames");
//		String addemp = request.getParameter("addemps");
//		String addcheckPerson = request.getParameter("addcheckPersons");
//		String addmaterialDescribe = request.getParameter("addmaterialDescribes");
//		String materialName = request.getParameter("mids");
//		c.setMaterialName(materialName);
//		c.setMaterialDescribe(addmaterialDescribe);
//		c.setMaterialVoucher(addmaterialVoucher);
//		c.setCheckBatch(addcheckBatch);
//		c.setOtigin(addotigin);
//		c.setMaterialNo(addmaterialNo);
//		c.setCheckNum(Integer.parseInt(addcheckNum));
//		c.setNgNum(Integer.parseInt(addngNum));
//		c.setFactoryNo(addfactoryNo);
//		c.setSeqNgNum(Integer.parseInt(addseqNgNum));
//		c.setCalculateUnit(addcalculateUnit);
//		c.setSupplierName(addsupplierName);
//		c.setEmp(addemp);
//		c.setCheckPerson(addcheckPerson);
//		c.setId(Integer.parseInt(id));
//		try {
//			service.updateIQC(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/excelOne")
//	@ResponseBody
//	public void excelOne(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		Map<String,Object> map = new HashMap<>();
//		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//		String factoryNo = request.getParameter("factoryNo");
//		if(factoryNo.equals("undefined")){
//			factoryNo=null;
//		}
//		String materialVoucher = request.getParameter("materialVoucher");
//		if(materialVoucher.equals("undefined")){
//			materialVoucher=null;
//		}
//		String checkBatch = request.getParameter("checkBatch");
//		if(checkBatch.equals("undefined")){
//			checkBatch=null;
//		}
//		String otigin = request.getParameter("otigin");
//		if(otigin.equals("undefined")){
//			otigin=null;
//		}
//		String materialNo = request.getParameter("materialNo");
//		if(materialNo.equals("undefined")){
//			materialNo=null;
//		}
//		String supplierName = request.getParameter("supplierName");
//		if(supplierName.equals("undefined")){
//			supplierName=null;
//		}
//		String emp = request.getParameter("emp");
//		if(emp.equals("undefined")){
//			emp=null;
//		}
//		String checkPerson = request.getParameter("checkPerson");
//		if(checkPerson.equals("undefined")){
//			checkPerson=null;
//		}
//		String productionHandie = request.getParameter("productionHandie");
//		if(productionHandie.equals("undefined")){
//			productionHandie="1";
//		}
//		String starttime = request.getParameter("act_start_time");
//		if(starttime.equals("undefined")){
//			starttime=null;
//		}
//		String endtime = request.getParameter("act_stop_time");
//		if(endtime.equals("undefined")){
//			endtime=null;
//		}
//		String status = request.getParameter("status");
//		if(status.equals("undefined")){
//			status="1";
//		}
//		String mname = request.getParameter("mname");
//		if(mname.equals("undefined")){
//			mname=null;
//		}
//		map.put("materialName", mname);
//		map.put("factoryNo", factoryNo);
//		map.put("materialVoucher", materialVoucher);
//		map.put("checkBatch", checkBatch);
//		map.put("otigin", otigin);
//		map.put("materialNo", materialNo);
//		map.put("supplierName", supplierName);
//		map.put("emp", emp);
//		map.put("checkPerson", checkPerson);
//		map.put("productionHandie", productionHandie);
//		map.put("status", status);
//		map.put("starttime", starttime);
//		map.put("endtime", endtime);
//		try {
//			List<CMesIqcCheckT> findAll = service.findAll(map);
//			HSSFWorkbook  book = new HSSFWorkbook();
//			String headers[] = {"工厂编号","物料名称","物料凭证号","校验批次","校验批来源","物料编号","物料描述","校验批数量","不合格数量","SQE不合格数量",
//					"计量单位","供应商名称","创建人","检验时间","复检时间","检验人","产品处置","状态"};
//			ExcelUtil.fileExcel(findAll,book,headers);
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	@RequestMapping("/fuhe")
//	@ResponseBody
//	public Map<String,Object> fuhe(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		if(name.equals("入库")){
//			name="3";
//		}else{
//			name="2";
//		}
//		try {
//			service.fuhe(id, name);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/freezes")
//	@ResponseBody
//	public Map<String,Object> freezes(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String status = request.getParameter("status");
//		try {
//			service.freezes(id, status);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
}
