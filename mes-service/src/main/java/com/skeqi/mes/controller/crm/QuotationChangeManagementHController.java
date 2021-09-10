package com.skeqi.mes.controller.crm;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.crm.QuotationChangeManagementHServiceImpl;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;




import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "报价管理页面", description = "报价管理页面", produces = MediaType.APPLICATION_JSON)
public class QuotationChangeManagementHController {

	@Autowired
	private QuotationChangeManagementHServiceImpl service;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	SimpleDateFormat dfex = new SimpleDateFormat("yyyyMMddHHmmss");//单号设置日期格式



	@RequestMapping(value = "/showQuotationChangeHInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询报价管理(头+分页)信息", notes = "查询报价管理(头+分页)信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "quotationRecordNo", value = "报价记录号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "companyCode", value = "公司代码", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "customerID", value = "客户ID", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "materialCode", value = "物料名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showQuotationChangeHInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
//		System.out.println(map);
		Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
		Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
		String companyCode = String.valueOf(map.get("companyCode"));
		String applicant = String.valueOf(map.get("applicant"));
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> listH =service.showQuotationChangeHInfo(companyCode, applicant);
		PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(listH);
		return Rjson.success(pageInfo);
	}


	@RequestMapping(value = "/showQuotationChangeHRInfo", method = RequestMethod.POST)
	@ApiOperation(value = "查询报价变更管理（头+行）信息", notes = "查询报价变更管理（头+行）信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "quotationRecordNo", value = "报价记录号", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "companyCode", value = "公司代码", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "customerID", value = "客户ID", required = false, paramType = "query", dataType = "string"),
			@ApiImplicitParam(name = "materialCode", value = "物料名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showQuotationChangeHRInfo(HttpServletRequest request,@RequestBody Map<String, Object> map) throws ServicesException {
		String changeRecordNo;
		if (map.get("changeRecordNo") == null) {
			changeRecordNo = "";
		} else {
			changeRecordNo = String.valueOf(map.get("changeRecordNo"));
		}
		List<Map<String, Object>> listH = service.showQuotationChangeHInfoEx(changeRecordNo);
		List<Map<String, Object>> listR = service.showQuotationChangeRInfoEx(changeRecordNo);
		Map<String, Object> infoDataByCode = new HashMap<>();
		infoDataByCode.put("listH", listH);
		infoDataByCode.put("listR", listR);
		System.out.println("listH:" + listH + "," + "listR:" + listR);
		return Rjson.success(infoDataByCode);
	}



	@RequestMapping(value = "/addQuotationChangeHRInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增报价管理（头+行）数据", notes = "新增报价管理（头+行）数据")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson addQuotationChangeHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
		String customerID = String.valueOf(map.get("customerID"));
		String companyCode = String.valueOf(map.get("companyCode"));
		String reasonForChange = String.valueOf(map.get("reasonForChange"));

//		H表
		String applicant="";
		String applicationTime = "";


//		R表
		String changeRecordNo = "";
		String lineNumber="";
		String materialCode = "";
		String materialName = "";
		String originalQuotation="";
		String newOffer = "";
		String unit = "";





		List<Map<String,Object>> list =  (List)map.get("listR");
		List<Map<String,Object>> list1 = service.showQuotationChangeRInfoByCode(String.valueOf(map.get("changeRecordNo")));
		Integer countNum = 0;
		for (Map map1:list1
		) {
			countNum = 0;
			for (Map map2:list
			) {
				if(map2.get("lineNumber")==null){
					countNum++;
					continue;
				}else{
					if(!String.valueOf(map1.get("lineNumber")).equals(String.valueOf(map2.get("lineNumber")))){
						countNum++;
					}
				}
			}
			if(countNum==list.size()){
//                System.out.println("存在查找不到的数据，输出："+String.valueOf(map1.get("lineNumber")));
				service.delQuotationChangeRDataByLineNum(String.valueOf(map1.get("changeRecordNo")),String.valueOf(map1.get("lineNumber")));
			}
		}
		if (map.get("status") == null) {
			changeRecordNo = "BJDC" + dfex.format(new Date());
			applicationTime = df.format(new Date());
			applicant = ToolUtils.getCookieUserName(request);

			map.put("changeRecordNo", changeRecordNo);
			map.put("applicationTime", applicationTime);
			map.put("applicant", applicant);
			map.put("status", "1");
			service.addQuotationChangeHInfo(changeRecordNo, customerID, applicant, applicationTime, reasonForChange, companyCode);

		} else if (map.get("status").equals("1")||map.get("status").equals("2")) {
			applicationTime = df.format(new Date());
			applicant = ToolUtils.getCookieUserName(request);
			map.remove(applicationTime);//删除原有的key
			map.remove(applicant);//删除原有的key
			map.put("applicationTime",applicationTime);//为旧的key重新赋值
			map.put("applicant",applicant);//为旧的key重新赋值
			changeRecordNo = String.valueOf(map.get("changeRecordNo"));
			//更新头表修改时间
			String status = String.valueOf(map.get("status"));
			service.updateQuotationChangeHInfoH(applicant, applicationTime, changeRecordNo, customerID, companyCode, status, reasonForChange);

			for (Map<String, Object> mapList : list) {
				changeRecordNo = String.valueOf(mapList.get("changeRecordNo"));
				if(service.showLineNumberChange(changeRecordNo)==null){
					lineNumber="1";
					materialCode = String.valueOf(mapList.get("materialCode"));
					materialName = String.valueOf(mapList.get("materialName"));
					originalQuotation = String.valueOf(mapList.get("originalQuotation"));
					newOffer = String.valueOf(mapList.get("newOffer"));
					unit = String.valueOf(mapList.get("unit"));
					service.addQuotationChangeRInfo(changeRecordNo, lineNumber, materialCode, materialName, originalQuotation, newOffer, unit);
					mapList.put("effectiveDate",df.format(new Date()));
				}else{
					if(mapList.get("lineNumber")==null||mapList.get("lineNumber")==""){
						Integer num =  Integer.parseInt(service.showLineNumberChange(changeRecordNo)) + 1;
						lineNumber =  String.valueOf((num));
						materialCode = String.valueOf(mapList.get("materialCode"));
						materialName = String.valueOf(mapList.get("materialName"));
						originalQuotation = String.valueOf(mapList.get("originalQuotation"));
						newOffer = String.valueOf(mapList.get("newOffer"));
						unit = String.valueOf(mapList.get("unit"));
						service.addQuotationChangeRInfo(changeRecordNo, lineNumber, materialCode, materialName, originalQuotation, newOffer, unit);
						mapList.put("effectiveDate",df.format(new Date()));
					}else{
						lineNumber = String.valueOf(mapList.get("lineNumber"));
						materialCode = String.valueOf(mapList.get("materialCode"));
						materialName = String.valueOf(mapList.get("materialName"));
						originalQuotation = String.valueOf(mapList.get("originalQuotation"));
						newOffer = String.valueOf(mapList.get("newOffer"));
						unit = String.valueOf(mapList.get("unit"));
						changeRecordNo = String.valueOf(mapList.get("changeRecordNo"));

						service.updateQuotationChangeHInfoR(materialCode, materialName, originalQuotation, newOffer, unit, changeRecordNo, lineNumber);
					}
				}
				mapList.put("lineNumber",lineNumber);
			}

			map.put("listR",list);
		}
		return Rjson.success(map);

	}

	@RequestMapping(value = "/delQuotationChangeHRInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除报价变更管理（头+行）数据", notes = "删除报价管理")
	@ApiImplicitParams({
	})
	@ResponseBody
	public Rjson delQuotationChangeHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
		String changeRecordNo = String.valueOf(map.get("changeRecordNo"));
		String changeRecordNos="";
		service.delQuotationChangeHData(changeRecordNo);//删除头数据
		List<Map<String,Object>> list = service.showQuotationChangeRInfoByCode(changeRecordNo);
		for (Map mapx:list
		) {
			changeRecordNos=String.valueOf(mapx.get("changeRecordNo"));//删除行表数据
			service.delQuotationChangeRData(changeRecordNos);
		}
		return Rjson.success();
	}





}
