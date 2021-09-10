package com.skeqi.htd.controller;

import com.alibaba.fastjson.JSON;
import com.skeqi.htd.common.Result;
import com.skeqi.htd.controller.vo.*;
import com.skeqi.htd.service.HtdServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.skeqi.htd.common.Constants.API_VERSION;
import static com.skeqi.htd.common.Constants.BASE_API;

/**
 * 高驱进销存
 *
 * @author linkin
 */
@Api("高驱进销存")
@RestController
@Slf4j
@RequestMapping(value = BASE_API + API_VERSION)
public class HtdController {

	private final HtdServiceFacade facade;

	@Autowired
	public HtdController(HtdServiceFacade facade) {
		this.facade = facade;
	}

	@ApiOperation("获取采购订单列表")
	@PostMapping(value = "/purchaser/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<PurchaseOrderVO.ItemVO>> queryPurchaserOrderList(@RequestBody QueryVO.QueryPurchaserOrdersVO vo) {
		log.info("获取采购订单列表入参：{}", JSON.toJSONString(vo));
		return Result.succeed(facade.queryPurchaserOrderList(vo));
	}

	@ApiOperation("获取采购订单详情")
	@GetMapping(value = "/purchaser/order", consumes = MediaType.ALL_VALUE)
	public Result<Map<String, PurchaseOrderVO.DetailVO>> getPurchaserOrderDetail(@RequestParam String exOrderNo) {
		return Result.succeed(facade.doGetPurchaserOrderDetails(Arrays.asList(exOrderNo)));
	}

	@ApiOperation("新增采购订单")
	@PostMapping(value = "/purchaser/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> createPurchaserOrder(@RequestBody PurchaseOrderVO.CreateVO vo) {
		log.info("新增销售订单入参：{}", JSON.toJSONString(vo));
		facade.createPurchaserOrder(vo);
		return Result.succeed(true);
	}

	@ApiOperation("获取销售订单列表")
	@PostMapping(value = "/sale/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<List<SaleOrderVO.ItemVO>> querySaleOrderList(@RequestBody QueryVO.QuerySaleOrdersVO vo) {
		log.info("获取销售订单列表入参：{}", JSON.toJSONString(vo));
		return Result.succeed(facade.querySaleOrderList(vo));
	}

	@ApiOperation("获取销售订单详情")
	@GetMapping(value = "/sale/order", consumes = MediaType.ALL_VALUE)
	public Result<PurchaseOrderVO.DetailVO> getSaleOrderDetail(@RequestParam String exOrderNo) {

		return Result.succeed(facade.doGetSaleOrderDetail(exOrderNo));
	}

	@ApiOperation("新增销售订单")
	@PostMapping(value = "/sale/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Result<Boolean> createSaleOrder(@RequestBody SaleOrderVO.CreateVO vo) {
		log.info("新增销售订单入参：{}", JSON.toJSONString(vo));
		facade.createSaleOrder(vo);
		return Result.succeed(true);
	}


	@ApiOperation("获取所有的客户名称")
	@GetMapping(value = "/customer/names", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllCustomerNames() {
		return Result.succeed(facade.getAllCustomerNames());
	}

	@ApiOperation("获取客户下的联系人信息")
	@GetMapping(value = "/customer/contacts", consumes = MediaType.ALL_VALUE)
	public Result<List<CustomerVO>> getCustomersByName(@RequestParam String customerName) {
		return Result.succeed(facade.getCustomersByName(customerName));
	}

	@ApiOperation("获取所有的供应商名称")
	@GetMapping(value = "/supplier/names", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllSupplierNames() {
		return Result.succeed(facade.getAllSupplierNames());
	}

	@ApiOperation("获取对应供应商的联系人信息")
	@GetMapping(value = "/supplier/contacts", consumes = MediaType.ALL_VALUE)
	public Result<List<SupplierVO>> getSuppliersByName(@RequestParam String supplierName) {
		return Result.succeed(facade.getSuppliersByName(supplierName));
	}

	@ApiOperation("订单审批")
	@PutMapping(value = "/order/audit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void auditOrder(@RequestBody AuditVO vo) {
		facade.auditOrder(vo);
	}

	@ApiOperation("获取产品列表")
	@GetMapping(value = "/products", consumes = MediaType.ALL_VALUE)
	public Result<PaginationVO<ProductVO.ProductBase>> listProducts(@RequestParam(required = false) @ApiParam("产品名称") String product,
																	@RequestParam(required = false) @ApiParam("产品编号") String code,
																	@RequestParam(required = false) @ApiParam("产品规格") String productSku,
																	@RequestParam(required = false) @ApiParam("页码，默认0") Integer pageNo,
																	@RequestParam(required = false) @ApiParam("页大小，默认10") Integer pageSize) {
		return Result.succeed(facade.listProducts(product, code, productSku, pageNo, pageSize));
	}

	@ApiOperation("获取仓库下拉列表")
	@GetMapping(value = "/subWarehouses", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllSubWarehouses() {
		return Result.succeed(Arrays.asList("宁德仓"));
	}

	@ApiOperation("获取所有的采购员")
	@GetMapping(value = "/purchasers", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllPurchasers() {
		return Result.succeed(Arrays.asList("采购员1", "采购员2"));
	}

	@ApiOperation("获取所有的销售员")
	@GetMapping(value = "/sellers", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllSellers() {
		return Result.succeed(Arrays.asList("销售员1", "销售员2"));
	}

	@ApiOperation("获取所有员工")
	@GetMapping(value = "/employees", consumes = MediaType.ALL_VALUE)
	public Result<List<String>> getAllEmployees() {
		return Result.succeed(Arrays.asList("吴春辉"));
	}
}
