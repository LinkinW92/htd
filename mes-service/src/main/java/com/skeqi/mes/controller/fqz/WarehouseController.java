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
import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesWarehouseListT;
import com.skeqi.mes.service.fqz.WarehouseListService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

	/**
	 * 进入入库信息页面
	* @author FQZ
	* @date 2019年11月5日下午5:17:09
	 */
	@Autowired
	private WarehouseListService service;

	@RequestMapping("/warehouselist")
	public String warehouselist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,10);
		Map<String,Object> map = new HashMap<String,Object>();
		String sn = request.getParameter("sn");
		map.put("sn",sn);
		request.setAttribute("sn", sn);
		List<CMesWarehouseListT> findAll = service.findAll(map);
		PageInfo<CMesWarehouseListT> pageInfo = new PageInfo<CMesWarehouseListT>(findAll,5);
		request.setAttribute("pageInfo", pageInfo);
		return "warehouse_control/PutWarehouse";
	}

	@RequestMapping("/getenter")
	@ResponseBody
	public List<CMesCheckoutEnterT> getenter(HttpServletRequest request){
		String sn = request.getParameter("sn");
		List<CMesCheckoutEnterT> listEnter = service.listEnter(sn);
		return listEnter;
	}
}
