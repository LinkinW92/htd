package com.skeqi.mes.controller.fqz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.fqz.DeviceRepairService;
import com.skeqi.mes.service.fqz.DeviceUpkeepService;
import com.skeqi.mes.util.ToolUtils;


/**
 * 设备保养
 * @author : FQZ
 * @Package: com.skeqi.commen.dgl
 * @date   : 2019年10月10日 下午3:05:37
 */

@Controller
@RequestMapping("devices")
public class DeciveUpkeepController {

	@Autowired
	private DeviceRepairService rservice;

	@Autowired
	private DeviceUpkeepService uservice;

	@RequestMapping("/deviceupkeeplist")
	public String devicelist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,ModelAndView mo) throws Exception{
			PageHelper.startPage(page,8);
			Map<String,Object> map = new HashMap<>();
			String dname = request.getParameter("dname");
			String lid = request.getParameter("lid");
			if(dname!=null && !dname.equals("")){
/*				String name1 = new String(dname.getBytes("ISO-8859-1"),"utf-8");*/
				map.put("deviceName", dname);
				request.setAttribute("dname",dname);
			}
			if(lid!=null && lid!="0" && !lid.equals("")){
				map.put("lineId", lid);
				request.setAttribute("lid", lid);
			}else{
				map.put("lineId","0");
				request.setAttribute("lid", "0");
			}
			List<CMesDeviceUpkeep> findAll = uservice.findAll(map);
			for (CMesDeviceUpkeep cMesDeviceUpkeep : findAll) {
				Integer findTime = uservice.findTime(cMesDeviceUpkeep.getId());  //计算剩余寿命
				if(findTime<=1){
					cMesDeviceUpkeep.setSurplusMaintain(1);
				}else{
					cMesDeviceUpkeep.setSurplusMaintain(0);
				}
			}
			PageInfo<CMesDeviceUpkeep> pageinfo = new PageInfo<CMesDeviceUpkeep>(findAll,5);
			List<CMesLineT> findLine = rservice.findLine();
			List<CMesDeviceT> findDevice = rservice.findDevice();
			request.setAttribute("pageInfo", pageinfo);
			request.setAttribute("findLine", findLine);
			request.setAttribute("findDevice", findDevice);
		return "deviceManager_control/deviceupkeep";
	}

	@RequestMapping("/insertupkeep")
	@ResponseBody
	public Map<String,Object> insertu(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		CMesDeviceUpkeep c = new CMesDeviceUpkeep();
		String lineId = request.getParameter("lineid");
		String mname = request.getParameter("mname");
		String period = request.getParameter("period");
		String upkeepperson = request.getParameter("upkeepperson").trim();
		String emp = request.getParameter("emp").trim();
		String note = request.getParameter("note").trim();
		c.setLineId(Integer.parseInt(lineId));
		c.setDeviceName(mname);
		c.setUpkeepPerson(upkeepperson);
		c.setMaintainCycle(Integer.parseInt(period));
		c.setEmp(emp);
		c.setNote(note);
		try {
			uservice.insertUpkeep(c);
			map.put("msg",1);
		} catch (Exception e) {
			map.put("msg",2);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("/orverupkeep")
	@ResponseBody
	public Map<String,Object> orverupkeep(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String id = request.getParameter("id");
		String value = request.getParameter("value");
		System.out.println(value+ "  ::::::::::::::::::::::::::::");
		try {
			uservice.updateDate(id, value);
			map.put("msg",1);
		} catch (Exception e) {
			map.put("msg",2);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}



	@RequestMapping("/getupkeep")
	@ResponseBody
	public CMesDeviceUpkeep getupkeep(HttpServletRequest request){
		String id = request.getParameter("id");
		CMesDeviceUpkeep findByid = uservice.findByid(id);
		return findByid;
	}

	@RequestMapping("/editupkeep")
	@ResponseBody
	public Map<String,Object> editupkeep(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		CMesDeviceUpkeep c = new CMesDeviceUpkeep();
		String id = request.getParameter("id");
		String lineId = request.getParameter("lineids");
		String mnames = request.getParameter("mnames");
		String upkeepperson = request.getParameter("upkeeppersons");
		String emp = request.getParameter("emps").trim();
 		String note = request.getParameter("notes").trim();
 		String periods = request.getParameter("periods").trim();
		c.setId(Integer.parseInt(id));
		c.setLineId(Integer.parseInt(lineId));
		c.setDeviceName(mnames);
		c.setUpkeepPerson(upkeepperson);
		c.setEmp(emp);
		c.setNote(note);
		c.setMaintainCycle(Integer.parseInt(periods));
		try {
			uservice.updateupkeep(c);
			map.put("msg",1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/delupkeeps")
	@ResponseBody
	public Map<String,Object> delupkeeps(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String id = request.getParameter("id");
		try {
			uservice.deleteupkeep(id);
			map.put("msg",1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}
}
