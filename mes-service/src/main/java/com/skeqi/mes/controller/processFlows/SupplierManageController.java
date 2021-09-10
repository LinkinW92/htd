package com.skeqi.mes.controller.processFlows;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.processFlows.SupplierManageService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "供应商管理", description = "供应商管理", produces = MediaType.APPLICATION_JSON)
public class SupplierManageController {

	@Autowired
	private SupplierManageService service;
//	展示供应商
	@RequestMapping(value = "/showSuplierInfo", method = RequestMethod.POST)
	@ApiOperation(value = "展示供应商管理", notes = "展示供应商管理")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson showRouteLine(HttpServletRequest request) throws ServicesException {
		try {

			Integer pageNum = null;
			Integer pageSize = null;

			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if (request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			if(pageNum!=null||pageSize!=null){
				PageHelper.startPage(pageNum, pageSize);
				List<Map<String,Object>> list =service.showAllSupplierInfos();
				PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
				return Rjson.success(200,pageInfo);
			}else{
				List<Map<String,Object>> list =service.showAllSupplierInfos();
				return Rjson.success(200,list);
			}

			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


//	新增供应商
	@RequestMapping(value = "/addSuplierInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增供应商管理", notes = "新增供应商管理")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson addSuplierInfo(HttpServletRequest request) throws ServicesException {
		try {

		String supplier = request.getParameter("supplierName");
			service.addSupplier(supplier);
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

//	删除供应商
	@RequestMapping(value = "/delSuplierInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除供应商管理", notes = "删除供应商管理")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delSuplierInfo(HttpServletRequest request) throws ServicesException {
		try {

		String id = request.getParameter("id");
			service.delSupplier(Integer.parseInt(id));
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


//	删除供应商
	@RequestMapping(value = "/updateSuplierInfo", method = RequestMethod.POST)
	@ApiOperation(value = "编辑供应商管理", notes = "编辑供应商管理")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson updateSuplierInfo(HttpServletRequest request) throws ServicesException {
		try {

		String id = request.getParameter("id");
		String supplier = request.getParameter("supplierName");
			service.updateSupplier(supplier, id);
			return Rjson.success();
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
