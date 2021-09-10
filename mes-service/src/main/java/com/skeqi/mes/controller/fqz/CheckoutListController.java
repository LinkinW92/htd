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
import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesCheckoutMethodT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.fqz.CheckoutlistService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;

/**
 * 入库检验项
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.fqz
 * @date   : 2019年10月30日 下午4:09:07
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutListController {

	@Autowired
	private CheckoutlistService service;

	@RequestMapping("/checkoutlist")
	public String checkoutlist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,10);
		Map<String,Object> map = new HashMap<>();
		String pname = request.getParameter("pname");
		String proname = request.getParameter("proname");
		map.put("projectName", pname);
		map.put("pid",proname);
		List<CMesCheckoutListT> findAll = service.findAll(map);
		PageInfo<CMesCheckoutListT> pageInfo = new PageInfo<CMesCheckoutListT>(findAll,5);
		List<CMesProductionT> findPro = service.findPro();
		List<CMesCheckoutMethodT> findMethod = service.findMethod();
		request.setAttribute("pid", proname);
		request.setAttribute("findMethod",findMethod);
		request.setAttribute("pname", pname);
		request.setAttribute("findPro",findPro);
		request.setAttribute("pageInfo", pageInfo);
		return "warehouse_control/checkoutlist";
	}

	@RequestMapping("/insertcheckout")
	@ResponseBody
	public Map<String,Object> insertcheckout(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		CMesCheckoutListT c = new CMesCheckoutListT();
		String ordernumber = request.getParameter("ordernumber");
		String pro = request.getParameter("pro");
		String method = request.getParameter("method");
		String projectname = request.getParameter("projectname");
		String quailty = request.getParameter("quailty");
		String findCode = service.findCode(pro);
		c.setOrderNumber(Integer.parseInt(ordernumber));
		c.setProductionId(pro);
		c.setProductionCode(findCode);
		c.setMethodId(method);
		c.setQuailty(quailty);
		c.setProjectName(projectname);
		Integer findOrdernumber = service.findOrdernumber(ordernumber,pro);
		if(findOrdernumber!=0){
			map.put("msg",2);
			return map;
		}
		if(!"1".toString().equals("method")){
			Integer findMethod = service.findMethodId(method,pro);
			if(findMethod!=0){
				map.put("msg",3);
				return map;
			}
		}
		try {
			service.insertcheckout(c);
			map.put("msg",1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/findbyidcheckout")
	@ResponseBody
	public CMesCheckoutListT findbyidcheckout(HttpServletRequest request){
		String id = request.getParameter("id");
		CMesCheckoutListT findByid = service.findByid(id);
		return findByid;
	}

	@RequestMapping("/updatecheckout")
	@ResponseBody
	public Map<String,Object> updatecheckout(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		CMesCheckoutListT c = new CMesCheckoutListT();
		String ordernumber = request.getParameter("ordernumbers");
		String pro = request.getParameter("pros");
		String method = request.getParameter("methods");
		String projectname = request.getParameter("projectnames");
		String quailty = request.getParameter("quailtys");
		String id = request.getParameter("id");
		String oldorder = request.getParameter("oldorder");
		c.setId(Integer.parseInt(id));
		c.setOrderNumber(Integer.parseInt(ordernumber));
/*		c.setProductionId(pro);*/
		c.setMethodId(method);
		c.setQuailty(quailty);
		c.setProjectName(projectname);
		try {
			Integer findOrdernumber = service.findOrdernumber(ordernumber,pro);
			if(findOrdernumber==0 || ordernumber.equals(oldorder)){  //如果该产品没有该序号
				service.updatecheckout(c);
				map.put("msg",1);
			}else{
				Map<String,Object> maps = new HashMap<>();
				maps.put("pid",pro);
				List<CMesCheckoutListT> findAll = service.findAll(maps);  //查询该产品下所有内容
				if(Integer.parseInt(ordernumber)>Integer.parseInt(oldorder)){   //如果修改的序号比原序号大
					for (CMesCheckoutListT cMesCheckoutListT : findAll) {
						//查询在新序号和旧序号之间的数据
						if(cMesCheckoutListT.getOrderNumber()>Integer.parseInt(oldorder) && cMesCheckoutListT.getOrderNumber()<=Integer.parseInt(ordernumber)){
							cMesCheckoutListT.setOrderNumber(cMesCheckoutListT.getOrderNumber()-1);   //在这个区间的序号都减1
							service.updatecheckout(cMesCheckoutListT);
						}
					}
				}else{   //如果修改的序号比原序号小
					for (CMesCheckoutListT cMesCheckoutListT : findAll) {
						//查询在新序号和旧序号之间的数据
						if(cMesCheckoutListT.getOrderNumber()>=Integer.parseInt(ordernumber) && cMesCheckoutListT.getOrderNumber()<Integer.parseInt(oldorder)){
							cMesCheckoutListT.setOrderNumber(cMesCheckoutListT.getOrderNumber()+1);   //在这个区间的序号都减1
							service.updatecheckout(cMesCheckoutListT);
						}
					}
				}
				service.updatecheckout(c);   //修改此条数据
				map.put("msg",1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/delcheckout")
	@ResponseBody
	public Map<String,Object> delcheckout(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String id = request.getParameter("id");
		try {
			service.deletecheckout(id);
			map.put("msg",1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/excelcheckout")
	@ResponseBody
	public void excelcheckout(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		String pname = request.getParameter("pname");
		String proname = request.getParameter("proname");
		if(pname.equals("undefined")){
			pname=null;
		}
		if(proname.equals("undefined")){
			proname=null;
		}
		map.put("projectName", pname);
		map.put("pid",proname);
		try {
			List<CMesCheckoutListT> findAll = service.findAll(map);
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"序号","项目名称","质量要求","检测方法","所属产品"};
			ExcelUtil.checkoutExcel(findAll, book, headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}
}
