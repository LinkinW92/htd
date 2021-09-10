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
import com.skeqi.mes.service.fqz.IQCCheckService;
import com.skeqi.mes.util.ExcelUtil;

@RequestMapping("material")
@Controller
public class SQECheckController {

	@Autowired
	private IQCCheckService service;

//	@RequestMapping("/sqelist")
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
//		String starttime = request.getParameter("act_start_time");
//		String endtime = request.getParameter("act_stop_time");
//		String status = request.getParameter("status");
//		String mname = request.getParameter("mname");
//		request.setAttribute("mname", mname);
//		request.setAttribute("factoryNo", factoryNo);
//		request.setAttribute("materialVoucher", materialVoucher);
//		request.setAttribute("checkBatch", checkBatch);
//		request.setAttribute("otigin", otigin);
//		request.setAttribute("materialNo", materialNo);
//		request.setAttribute("supplierName", supplierName);
//		request.setAttribute("emp", emp);
//		request.setAttribute("checkPerson", checkPerson);
//		request.setAttribute("productionHandie", productionHandie);
//		if(productionHandie==null || productionHandie=="" ||productionHandie.endsWith("0")){
//			productionHandie=null;
//		}
//		request.setAttribute("status", status);
//		if(status==null || status.equals("")){
//			status="2";
//		}
//		request.setAttribute("starttime", starttime);
//		request.setAttribute("endtime", endtime);
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
//		map.put("starttimes", starttime);
//		map.put("endtimes", endtime);
//		List<CMesIqcCheckT> findAll = service.findAll(map);
//		PageInfo<CMesIqcCheckT> pageinfo = new PageInfo<CMesIqcCheckT>(findAll,5);
//		request.setAttribute("pageInfo",pageinfo);
//		return "materiel_control/SQEManager";
//	}
//
//	@RequestMapping("/excelTwo")
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
//		String mname = request.getParameter("mname");
//		if(mname.equals("undefined")){
//			mname=null;
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
//		if(productionHandie.equals("0") || productionHandie.equals("undefined")){
//			productionHandie=null;
//		}
//		String starttime = request.getParameter("act_start_time");
//		if(starttime.equals("undefined")){
//			starttime=null;
//		}
//		String endtime = request.getParameter("act_stop_time");
//		if(endtime.equals("undefined")){
//			endtime=null;
//		}
//		map.put("factoryNo", factoryNo);
//		map.put("materialVoucher", materialVoucher);
//		map.put("checkBatch", checkBatch);
//		map.put("otigin", otigin);
//		map.put("materialNo", materialNo);
//		map.put("supplierName", supplierName);
//		map.put("emp", emp);
//		map.put("checkPerson", checkPerson);
//		map.put("productionHandie", productionHandie);
//		map.put("status", "2");
//		map.put("starttimes", starttime);
//		map.put("endtimes", endtime);
//		map.put("materialName", mname);
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
}
