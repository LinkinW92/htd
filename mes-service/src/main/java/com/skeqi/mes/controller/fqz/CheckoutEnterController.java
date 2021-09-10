package com.skeqi.mes.controller.fqz;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesFindT;
import com.skeqi.mes.pojo.CMesWarehouseListT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesEolT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PMesPlanPrintT;
import com.skeqi.mes.service.fqz.CheckoutEnterService;
import com.skeqi.mes.service.fqz.CheckoutlistService;
import com.skeqi.mes.util.ToolUtils;

@RequestMapping("/checkoutenter")
@Controller
public class CheckoutEnterController {

	@Autowired
	private CheckoutEnterService service;

	@Autowired
	private CheckoutlistService listservice;

	/**
	 * 进入页面
	* @author FQZ
	* @date 2019年11月2日下午3:18:00
	 */
	@RequestMapping("/checkoutenterlist")
	public String checkoutenterlist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		Map<String,Object> map = new HashMap<>();
		String sn = request.getParameter("sn");  //sn
		Integer findSN = 0;
		if(sn!=null && sn!=""){   //如果sn不为空
			 findSN = service.findSN(sn);   //在总成表里查询该SN是否下线
		}else{   //如果sn为空（进页面的时候）
			 findSN = service.findSN("01");   //在总成表里查询该SN是否下线
		}

		if(findSN==1){   //如果已下线
			Integer findHouseSN = service.findHouseSN(sn);  //查询仓库表有没有该pack
			if(findHouseSN>0){
				request.setAttribute("msg",3);
				request.setAttribute("sn",sn);
			}else{
				request.setAttribute("sn",sn);
				CMesFindT findPro = service.findPro(sn);   //查询产品名称和产品编码
				request.setAttribute("findPro",findPro);  //保存
				List<CMesCheckoutListT> findCheckList = service.findCheckList(sn);   //查询入库检验项
				Integer findBoltCount = service.findBoltCount(sn);  //查询螺栓表是否有未完成的数据
				Integer findKeypartCount = service.findKeypartCount(sn);   //查询物料表是否有未完成的数据
				Integer findLeakageCount = service.findLeakageCount(sn);    //查询气密表是否有未完成的数据
				Integer findEolCount = service.findEolCount(sn);  //查询eol表是否有成功的
				String s = "0";  //所有检验项的序号拼接
				for (CMesCheckoutListT cMesCheckoutListT : findCheckList) {
						s = s+";"+cMesCheckoutListT.getOrderNumber().toString();
			}
			request.setAttribute("findBoltCount", findBoltCount);
			request.setAttribute("findKeypartCount", findKeypartCount);
			request.setAttribute("findLeakageCount",findLeakageCount);
			request.setAttribute("findEolCount",findEolCount);
			request.setAttribute("str",s);
			request.setAttribute("findCheckList", findCheckList);  //保存
			}
		}else if(findSN>1){   //如果存在两条SN记录
			request.setAttribute("sn",sn);
			request.setAttribute("msg",2);
		}else if(findSN==0){   //如果没有该总成
			request.setAttribute("sn",sn);
			request.setAttribute("msg",4);
		}
		return "warehouse_control/enterReceiptDoc";
	}

	/**
	 * 物料信息
	* @author FQZ
	* @date 2019年11月2日下午3:30:34
	 */
	@RequestMapping("/findKeypart")
	@ResponseBody
	public List<PMesKeypartT> findKeypart(HttpServletRequest request){
		String sn = request.getParameter("sn");
		List<PMesKeypartT> findKeypart=null;
		try {
			findKeypart = service.findKeypart(sn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findKeypart;
	}

	/**
	 * 螺栓信息
	* @author FQZ
	* @date 2019年11月2日下午3:30:41
	 */
	@RequestMapping("/findBolt")
	@ResponseBody
	public List<PMesBoltT> findBolt(HttpServletRequest request){
		String sn = request.getParameter("sn");
		List<PMesBoltT> findBolt=null;
		try {
			 findBolt = service.findBolt(sn);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findBolt;
	}

	/**
	 * 气密信息
	* @author FQZ
	* @date 2019年11月2日下午3:30:47
	 */
	@RequestMapping("/findLeakage")
	@ResponseBody
	public List<PMesLeakageT> findLeakage(HttpServletRequest request){
		String sn = request.getParameter("sn");
		List<PMesLeakageT> findLeakage=null;
		try {
			 findLeakage = service.findLeakage(sn);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findLeakage;
	}

	@RequestMapping("/findEOl")
	@ResponseBody
	public List<PMesEolT> findEol(HttpServletRequest request){
		String sn = request.getParameter("sn");
		List<PMesEolT> findEol =  null;
		try {
			findEol = service.findEol(sn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findEol;
	}

	/**
	 * 入库
	* @author FQZ
	* @date 2019年11月5日上午9:39:54
	 */
	@RequestMapping("/insertenter")
	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	public Map<String,Object> insertenter(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sn = request.getParameter("sn");
		String emp = request.getParameter("emp");
		String str = request.getParameter("str");
		String note = request.getParameter("note");
		str=str.substring(2);
		String code = request.getParameter("code");  //产品id
		String act_start_time = request.getParameter("act_start_time");
		act_start_time=act_start_time+":00";
		try {
			String[] split = str.split(";");   //检验项的所有序号
			for (String string : split) {
				CMesCheckoutEnterT c = new CMesCheckoutEnterT();
				CMesCheckoutListT findByNo = service.findByNo(code, string);  //根据序号和产品id查询入库检验表
				c.setProductionId(Integer.parseInt(code));
				c.setSn(sn);
				c.setMethod(findByNo.getMethodName());
				c.setOrderNumber(findByNo.getOrderNumber());
				c.setProjectName(findByNo.getProjectName());
				c.setQuailty(findByNo.getQuailty());
				service.insertEnter(c);  //添加录入表
			}
			CMesWarehouseListT house = new CMesWarehouseListT();
			PMesPlanPrintT findPlan = service.findPlan(sn);  //根据sn查询订单和计划id
			house.setDt(sim.parse(act_start_time));
			house.setEmp(emp);
			house.setOrderId(findPlan.getWorkOrderId());
			house.setPlanId(findPlan.getPlanId());
			house.setProductionId(Integer.parseInt(code));
			house.setSn(sn);
			house.setNote(note);
			service.inserWareHouse(house);
			map.put("msg",1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
              map.put("msg",2);
              throw new RuntimeException();
		}
		return map;
	}
}
