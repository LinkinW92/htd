package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/***
 *
 *  sqe复核
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "SQE复核", description = "SQE复核", produces = MediaType.APPLICATION_JSON)
public class CMesSqeController {

	@Autowired
	QualityService qualityService;

	@RequestMapping(value = "/sqe/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询sqe复核信息", notes = "可根据多条件查询对应sqe复核信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "factoryNo", value = "工厂编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "materialVoucher", value = "物料凭证号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "checkBatch", value = "校验批次", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "otigin", value = "校验批来源", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "materialNo", value = "物料号", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "emp", value = "创建人", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "checkPerson", value = "校验人", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "productionHandie", value = "产品处置", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "act_start_time", value = "开始时间", required = false, paramType = "query", dataType = "String") ,
		@ApiImplicitParam(name = "act_stop_time", value = "结束时间", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize,String factoryNo,String materialVoucher,
			String checkBatch,String otigin,String materialNo,String supplierName,String emp,String status
			,String checkPerson,String materialName,String act_start_time,String act_stop_time,String productionHandie)throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		if(productionHandie==null || productionHandie=="" ||productionHandie.endsWith("0")){
			productionHandie=null;
		}

		if(status==null || status.equals("")){
			status="2";
		}
		map.put("factoryNo", factoryNo);
		map.put("materialName", materialName);
		map.put("materialVoucher", materialVoucher);
		map.put("checkBatch", checkBatch);
		map.put("otigin", otigin);
		map.put("materialNo", materialNo);
		map.put("supplierName", supplierName);
		map.put("emp", emp);
		map.put("checkPerson", checkPerson);
		map.put("productionHandie", productionHandie);
		map.put("status", status);
		map.put("act_start_time", act_start_time);
		map.put("act_stop_time", act_stop_time);

		try {
			List<CMesIqcCheckT> findAll = qualityService.findAllIQC(map);
			PageInfo<CMesIqcCheckT> pageinfo = new PageInfo<CMesIqcCheckT>(findAll);

			json.put("code", 0);
			json.put("msg", pageinfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;


	}

}
