package com.skeqi.mes.controller.fqz;

import java.util.ArrayList;
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
import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.fqz.DeviceRepairService;
import com.skeqi.mes.util.ToolUtils;

/**
 * 设备维修
 * @author : FQZ
 * @Package: com.skeqi.commen.dgl
 * @date   : 2019年10月10日 下午3:05:37
 */

@Controller
@RequestMapping("devices")
public class DeciveRepairController {

	@Autowired
	private DeviceRepairService service;
	@RequestMapping("/devicerepairlist")
	public String devicelist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception{
		PageHelper.startPage(page,8);
		Map<String,Object> map = new HashMap<>();
		String dname = request.getParameter("dname");
		String lid = request.getParameter("lid");
		if(dname!=null && !dname.equals("")){
/*			String name1 = new String(dname.getBytes("ISO-8859-1"),"utf-8");*/
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
		List<CMesDeviceRepairT> findAll = service.findAll(map);
		PageInfo<CMesDeviceRepairT> pageinfo = new PageInfo<CMesDeviceRepairT>(findAll,5);
		List<CMesDeviceT> findDevice = service.findDevice();
		List<CMesLineT> findLine = service.findLine();
		request.setAttribute("findDevice",findDevice);
		request.setAttribute("findLine",findLine);
		request.setAttribute("pageInfo", pageinfo);
		return "deviceManager_control/devicerepair";
	}

	@RequestMapping("/insertdr")
	@ResponseBody
	public Map<String,Object> insertdr(HttpServletRequest request){
		CMesDeviceRepairT c = new CMesDeviceRepairT();
		Map<String,Object> map = new HashMap<>();
		String lineid = request.getParameter("lineid");
		String deviceid = request.getParameter("deviceid");
		String repairperson = request.getParameter("repairperson").trim();
		String emp = request.getParameter("emp").trim();
		String note = request.getParameter("note").trim();
		String reason = request.getParameter("reason").trim();
		c.setLineId(Integer.parseInt(lineid));
		c.setDeviceName(deviceid);
		c.setRepairPerson(repairperson);
		c.setEmp(emp);
		c.setNote(note);
		c.setReason(reason);
		try {
			service.insertRepair(c);
			map.put("msg", 1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/deldr")
	@ResponseBody
	public Map<String,Object> deldr(HttpServletRequest request){
		String id = request.getParameter("id");
		Map<String,Object> map = new HashMap<>();
		try {
			service.deleteRepair(id);
			map.put("msg", 1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public CMesDeviceRepairT edit(HttpServletRequest request){
		String id = request.getParameter("id");
		CMesDeviceRepairT findByid = service.findByid(id);
		return findByid;
	}

	@RequestMapping("/editdr")
	@ResponseBody
	public Map<String,Object> editdr(HttpServletRequest request){
		CMesDeviceRepairT c = new CMesDeviceRepairT();
		Map<String,Object> map = new HashMap<>();
		String lineid = request.getParameter("lineids");
		String id = request.getParameter("id");
		String deviceid = request.getParameter("deviceids");
		String repairperson = request.getParameter("repairpersons").trim();
		String emp = request.getParameter("emps").trim();
		String note = request.getParameter("notes").trim();
		String reason = request.getParameter("reasons").trim();
		c.setLineId(Integer.parseInt(lineid));
		c.setDeviceName(deviceid);
		c.setRepairPerson(repairperson);
		c.setEmp(emp);
		c.setNote(note);
		c.setReason(reason);
		c.setId(Integer.parseInt(id));
		try {
			service.updateRepair(c);
			map.put("msg", 1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			map.put("msg",2);
		}
		return map;
	}

	@RequestMapping("/getdevices")
	@ResponseBody
	public List<CMesDeviceT> getdevices(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		List<CMesDeviceT> findDevice = service.findDevices(id);
		return findDevice;
	}
}
