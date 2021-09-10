package com.skeqi.mes.controller.crm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.crm.SalesContractManagementHServiceImpl;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "crm", produces = MediaType.APPLICATION_JSON)
@Api(value = "销售合同管理页面", description = "销售合同管理页面", produces = MediaType.APPLICATION_JSON)
public class SalesContractManagementHController {

	@Autowired
	private SalesContractManagementHServiceImpl service;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	SimpleDateFormat dfex = new SimpleDateFormat("yyyyMMddHHmmss");//单号设置日期格式


	@RequestMapping(value = "/showContractInfoH", method = RequestMethod.POST)
	@ApiOperation(value = "查询销售合同管理头信息", notes = "查询销售合同管理头信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "contractNo", value = "销售合同记录号", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "companyCode", value = "公司代码", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "customerID", value = "客户ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "materialCode", value = "物料名称", required = false, paramType = "query", dataType = "string"),
	})
	public Rjson showContractInfoH(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
//		System.out.println(map);
		Integer pageNum = Integer.parseInt(map.get("pageNum").toString());
		Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
		String contractNo;
		if (map.get("contractNo") == null) {
			contractNo = "";
		} else {
			contractNo = String.valueOf(map.get("contractNo"));
		}
		String customerID;
		if (map.get("customerID") == null) {
			customerID = "";
		} else {
			customerID = String.valueOf(map.get("customerID"));
		}
		String companyCode;
		if (map.get("companyCode") == null) {
			companyCode = "";
		} else {
			companyCode = String.valueOf(map.get("companyCode"));
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> listH = service.showContractHInfo(contractNo, companyCode, customerID);
		PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(listH);
		return Rjson.success(pageInfo);
	}


	@RequestMapping(value = "/showContractInfoHR", method = RequestMethod.POST)
	@ApiOperation(value = "查询销售合同管理头+行信息", notes = "查询销售合同管理头+行信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "contractNo", value = "销售合同记录号", required = false, paramType = "query", dataType = "string"),
	})
	public Rjson showContractInfoHR(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
		System.out.println(map);

		String contractNo;
		if (map.get("contractNo") == null) {
			contractNo = "";
		} else {
			contractNo = String.valueOf(map.get("contractNo"));
		}
		List<Map<String, Object>> listH = service.showContractHInfoByCode(contractNo);
		List<Map<String, Object>> listR = service.showContractRInfoByCode(contractNo);
		Map<String, Object> infoDataByCode = new HashMap<>();
		infoDataByCode.put("listH", listH);
		infoDataByCode.put("listR", listR);
		System.out.println("listH:" + listH + "," + "listR:" + listR);
		return Rjson.success(infoDataByCode);
	}


	@RequestMapping(value = "/addContractHRInfo", method = RequestMethod.POST)
	@ApiOperation(value = "新增销售合同管理（头+行）数据", notes = "新增销售合同管理")
	@ApiImplicitParams({
	})
	public Rjson addContractHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
		String customerID = String.valueOf(map.get("customerID"));
		String companyCode = String.valueOf(map.get("companyCode"));
//        System.out.println("123===================================");

//		System.out.println("状态:"+map.get("status"));
		String contractNo = "";
		String creationTime = "";
		String revisionTime = "";
		String founder = "";
		String reviser = "";
		String lineNumber = "1";
		String materialCode = "";
		String materialName = "";

		String moldAmortization = "";
		String amortizationAmount = "";
		String amortizationUnitPrice = "";
		String productPrice = "";
		String demandQuantity = "";
		String quantityDelivered = "";
//		moldAmortization, amortizationAmount, amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered

		List<Map<String, Object>> list = (List) map.get("listR");
		List<Map<String, Object>> list1 = service.showContractRInfoByCode(String.valueOf(map.get("contractNo")));
		Integer countNum = 0;
		for (Map map1 : list1
		) {
			countNum = 0;
			for (Map map2 : list
			) {
				if (map2.get("lineNumber") == null) {
					countNum++;
					continue;
				} else {
//                   System.out.println(map1.get("lineNumber"));
//                   System.out.println(map2.get("lineNumber"));
					if (!String.valueOf(map1.get("lineNumber")).equals(String.valueOf(map2.get("lineNumber")))) {
						countNum++;
					}
				}
			}
			if (countNum == list.size()) {
//                System.out.println("存在查找不到的数据，输出："+String.valueOf(map1.get("lineNumber")));
				service.delContractRDataByLineNum(String.valueOf(map1.get("contractNo")), String.valueOf(map1.get("lineNumber")));
			}
		}
		if (map.get("status") == null) {
			contractNo = "XSHT" + dfex.format(new Date());
			creationTime = df.format(new Date());
			founder = ToolUtils.getCookieUserName(request);
			reviser = "";
			map.put("contractNo", contractNo);
			map.put("creationTime", creationTime);
			map.put("founder", founder);
			map.put("status", "1");
			service.addContractHInfo(contractNo, customerID, creationTime, founder, reviser, companyCode);

		} else if (map.get("status").equals("1") || map.get("status").equals("2")) {
			revisionTime = df.format(new Date());
			reviser = ToolUtils.getCookieUserName(request);
			map.put("revisionTime", revisionTime);
			map.put("reviser", reviser);
			contractNo = String.valueOf(map.get("contractNo"));
			//更新头表修改时间
			String status = String.valueOf(map.get("status"));
			if(status.equals("1")){
				service.updateContractHInfoH(reviser, revisionTime, contractNo, customerID, companyCode, status);
			}else if(status.equals("2")){
				String effectiveDate = df.format(new Date());
				service.updateContractHInfoH1(reviser, revisionTime, contractNo, customerID, companyCode, status,effectiveDate);
			}


			for (Map<String, Object> mapList : list) {
				contractNo = String.valueOf(mapList.get("contractNo"));
				if (service.showLineNumber(contractNo) == null) {
					lineNumber = "1";
					materialCode = String.valueOf(mapList.get("materialCode"));
					materialName = String.valueOf(mapList.get("materialName"));
//					moldAmortization, amortizationAmount, amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered
					moldAmortization = String.valueOf(mapList.get("moldAmortization"));
					amortizationAmount = String.valueOf(mapList.get("amortizationAmount"));
					amortizationUnitPrice = String.valueOf(mapList.get("amortizationUnitPrice"));

					productPrice = String.valueOf(mapList.get("productPrice"));
					demandQuantity = String.valueOf(mapList.get("demandQuantity"));
					quantityDelivered = String.valueOf(mapList.get("quantityDelivered"));
					service.addContractRInfo(contractNo, lineNumber, materialCode, materialName, moldAmortization, amortizationAmount, amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered);
					mapList.put("effectiveDate", df.format(new Date()));
				} else {
					if (mapList.get("lineNumber") == null || mapList.get("lineNumber") == "") {
						Integer num = Integer.parseInt(service.showLineNumber(contractNo)) + 1;
						lineNumber = String.valueOf((num));
						materialCode = String.valueOf(mapList.get("materialCode"));
						materialName = String.valueOf(mapList.get("materialName"));
						moldAmortization = String.valueOf(mapList.get("moldAmortization"));
						amortizationAmount = String.valueOf(mapList.get("amortizationAmount"));
						amortizationUnitPrice = String.valueOf(mapList.get("amortizationUnitPrice"));

						productPrice = String.valueOf(mapList.get("productPrice"));
						demandQuantity = String.valueOf(mapList.get("demandQuantity"));
						quantityDelivered = String.valueOf(mapList.get("quantityDelivered"));
						service.addContractRInfo(contractNo, lineNumber, materialCode, materialName, moldAmortization, amortizationAmount, amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered);
						mapList.put("effectiveDate", df.format(new Date()));
					} else {
						lineNumber = String.valueOf(mapList.get("lineNumber"));
						materialCode = String.valueOf(mapList.get("materialCode"));
						materialName = String.valueOf(mapList.get("materialName"));
						moldAmortization = String.valueOf(mapList.get("moldAmortization"));
						amortizationAmount = String.valueOf(mapList.get("amortizationAmount"));
						amortizationUnitPrice = String.valueOf(mapList.get("amortizationUnitPrice"));

						productPrice = String.valueOf(mapList.get("productPrice"));
						demandQuantity = String.valueOf(mapList.get("demandQuantity"));
						quantityDelivered = String.valueOf(mapList.get("quantityDelivered"));
						contractNo = String.valueOf(mapList.get("contractNo"));

						service.updateContractHInfoR(materialCode, materialName, moldAmortization, amortizationAmount,amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered, contractNo, lineNumber);
					}
				}
				mapList.put("lineNumber", lineNumber);
			}

			map.put("listR", list);
		}
		return Rjson.success(map);

	}


	@RequestMapping(value = "/delContractHRInfo", method = RequestMethod.POST)
	@ApiOperation(value = "删除销售合同管理（头+行）数据", notes = "删除销售合同管理")
	@ApiImplicitParams({
	})
	public Rjson delContractHRInfo(HttpServletRequest request, @RequestBody Map<String, Object> map) throws ServicesException {
		String contractNo = String.valueOf(map.get("contractNo"));
		String contractNos = "";
		service.delContractHData(contractNo);//删除头数据
		List<Map<String, Object>> list = service.showContractRInfoByCode(contractNo);
		for (Map mapx : list
		) {
			contractNos = String.valueOf(mapx.get("contractNo"));//删除行表数据
			service.delContractRData(contractNos);
		}
		return Rjson.success();
	}

}
