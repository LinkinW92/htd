package com.skeqi.htd.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.htd.common.Asserts;
import com.skeqi.htd.common.AuditState;
import com.skeqi.htd.common.BizException;
import com.skeqi.htd.common.OrderType;
import com.skeqi.htd.controller.vo.*;
import com.skeqi.htd.po.entity.*;
import com.skeqi.htd.service.audit.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.skeqi.htd.common.Constants.DATETIME_FORMATTER;
import static com.skeqi.htd.common.Constants.DATE_FORMATTER;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * 业务门户类
 *
 * @author linkin
 */
@Service
@Slf4j
public class HtdServiceFacade {
	private final CustomerService customerService;
	private final PurchaseOrderService purchaseOrderService;
	private final SaleOrderService saleOrderService;
	private final SubWarehouseService subWarehouseService;
	private final SupplierService supplierService;
	private final ProductService productService;
	private final FlowService flowService;


	@Autowired
	public HtdServiceFacade(CustomerService customerService,
							@Qualifier("htdPurchaseOrderServiceImpl") PurchaseOrderService purchaseOrderService,
							SaleOrderService saleOrderService,
							SubWarehouseService subWarehouseService,
							SupplierService supplierService,
							ProductService productService,
							FlowService flowService) {
		this.customerService = customerService;
		this.purchaseOrderService = purchaseOrderService;
		this.saleOrderService = saleOrderService;
		this.subWarehouseService = subWarehouseService;
		this.supplierService = supplierService;
		this.productService = productService;
		this.flowService = flowService;
	}

	/**
	 * 创建采购订单
	 *
	 * @param vo
	 */
	public void createPurchaserOrder(PurchaseOrderVO.CreateVO vo) {
		String exOrderNo = vo.getExOrderNo();
		// 1. 保存订单消息
		this.purchaseOrderService.createOrders(vo.toEntities(this.generateOrderNo()));
		// 2. 如果订单提交审批，进入审批流
		if (AuditState.TO_AUDIT.name().equals(vo.getAuditState())) {
			flowService.initialize(exOrderNo, OrderType.PURCHASE);
		}
	}

	/**
	 * 采购订单查询
	 *
	 * @return
	 */
	public List<PurchaseOrderVO.ItemVO> queryPurchaserOrderList(QueryVO.QueryPurchaserOrdersVO vo) {
		// 1.根据查询条件获取订单的主体信息
		List<PurchaseOrder> orders = this.purchaseOrderService.queryBy(vo);
		return orders.parallelStream().filter(Objects::nonNull)
			.map(o -> {
				PurchaseOrderVO.ItemVO item = new PurchaseOrderVO.ItemVO();
				BeanUtils.copyProperties(o, item);
				item.setLatestAuditTime(o.getUpdateTime().format(DATETIME_FORMATTER));
				item.setOrderTime(o.getOrderTime().format(DATE_FORMATTER));
				item.setDeliveryTime(o.getDeliveryTime().format(DATE_FORMATTER));
				return item;
			}).collect(Collectors.toList());
	}

	/**
	 * 采购订单查询
	 *
	 * @param exOrderNoList
	 * @return
	 * @apiNote 采购订单在子订单号上保证唯一
	 */
	@SuppressWarnings("Duplicates")
	public Map<String/*exOrderNo*/, List<PurchaseOrderVO.DetailVO>> doGetPurchaserOrderDetails(List<String> exOrderNoList) {
		// 1.根据查询条件获取订单的主体信息
		List<PurchaseOrder> orders = this.purchaseOrderService.queryBy(QueryVO.QueryPurchaserOrdersVO.with(exOrderNoList));

		List<Integer> productIds = orders.parallelStream()
			.filter(Objects::nonNull)
			.map(PurchaseOrder::getProductId)
			.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(productIds)) {
			return null;
		}
		// 2.转为普通订单对象
		List<CommonOrder> co = new ArrayList<>();
		for (PurchaseOrder order : orders) {
			co.add(order);
		}
		// 3.获取客户联系方式等信息
		Map<String, Supplier> suppliers = this.supplierService.getSupplierByNames(
			orders.parallelStream()
				.map(PurchaseOrder::getSupplier)
				.collect(Collectors.toList()))
			.parallelStream()
			.filter(Objects::nonNull)
			.collect(Collectors.toMap(Supplier::getName, s -> s));
		// 4.关联的销售订单号, 目前的业务模式下，一笔销售订单对应多比采购订单，但一个采购只会有一个销售订单
		return orders.parallelStream().filter(Objects::nonNull)
			.map(e ->
				PurchaseOrderVO.DetailVO.build(e, mergeProductInfo(productIds, co), suppliers.get(e.getSupplier())))
			.collect(Collectors.groupingBy(PurchaseOrderVO.DetailVO::getExOrderNo));
	}


	/**
	 * 新增销售单
	 *
	 * @return
	 */
	public void createSaleOrder(SaleOrderVO.CreateVO vo) {
		Asserts.checkArgs(null != vo && !isEmpty(vo.getExOrderNo()), "订单创建请求参数缺失");
		if (this.saleOrderService.checkExists(vo.getExOrderNo())) {
			throw new BizException("销售订单已存在,不可重复添加");
		}
		String exOrderNo = vo.getExOrderNo();
		// 1.保存订单信息 TODO 考虑更新的场景  草稿功能的优化】
		this.saleOrderService.createOrders(vo.toEntities(this.generateOrderNo()));
		// 2. 如果订单提交审批，进入审批流
		if (AuditState.TO_AUDIT.name().equals(vo.getAuditState())) {
			flowService.initialize(exOrderNo, OrderType.SALE);
		}
	}

	/**
	 * 销售订单查询
	 *
	 * @param exOrderNo
	 * @return
	 */
	@SuppressWarnings("Duplicates")
	public SaleOrderVO.DetailVO doGetSaleOrderDetail(String exOrderNo) {
		// 1.根据查询条件获取订单的主体信息
		List<SaleOrder> orders = this.saleOrderService.queryBy(QueryVO.QuerySaleOrdersVO.with(exOrderNo));
		List<Integer> productIds = orders.parallelStream()
			.filter(Objects::nonNull)
			.map(SaleOrder::getId)
			.collect(Collectors.toList());
		if (CollectionUtils.isEmpty(productIds)) {
			return null;
		}
		// 2.转为普通订单对象
		List<CommonOrder> co = new ArrayList<>();
		for (SaleOrder order : orders) {
			CommonOrder c = new CommonOrder();
			BeanUtils.copyProperties(order, c);
			co.add(c);
		}
		// 3.获取客户联系方式等信息
		Customer customer = this.customerService.getCustomerById(orders.get(0).getCustomerId());
		// 4. 获取关联订单
//		PurchaseOrderVO.DetailVO purchaserOrderDetails = this.doGetPurchaserOrderDetails(exOrderNo);
		// 5.关联的销售订单号 目前的业务模式下，一笔销售订单对应多比采购订单，但一个采购但只会有一个销售订单
		return SaleOrderVO.DetailVO.build(orders.get(0), this.mergeProductInfo(productIds, co), customer);
	}


	/**
	 * 获取销售订单列表
	 *
	 * @param vo
	 * @return
	 */
	public List<SaleOrderVO.ItemVO> querySaleOrderList(QueryVO.QuerySaleOrdersVO vo) {
		List<SaleOrder> orders = this.saleOrderService.queryBy(vo);
		// 归类销售单下的产品信息
		Map<String/*exOrderNo*/, List<ProductVO>> productMap = orders.parallelStream().map(order -> {
			ProductVO product = new ProductVO();
			BeanUtils.copyProperties(order, product);
			ProductVO.ProductExt ext = JSON.parseObject(order.getProductExt(), ProductVO.ProductExt.class);
			BeanUtils.copyProperties(ext, product);
			return product;
		}).collect(Collectors.groupingBy(ProductVO::getExOrderNo));
		// 批量获取销售单关联的采购订单信息
		List<String> exOrderNoList = orders.parallelStream().map(SaleOrder::getExOrderNo).collect(Collectors.toList());
		Map<String, List<PurchaseOrderVO.DetailVO>> relativeOrderMap = this.doGetPurchaserOrderDetails(exOrderNoList);
		Map<String, SaleOrder> distinctOrderMap = orders.parallelStream()
			.collect(Collectors.toMap(SaleOrder::getExOrderNo, e -> e, (v1, v2) -> v1));
		return distinctOrderMap.entrySet().parallelStream().filter(Objects::nonNull)
			.map(entry -> {
				SaleOrder o = entry.getValue();
				SaleOrderVO.ItemVO item = new SaleOrderVO.ItemVO();
				BeanUtils.copyProperties(o, item);
				item.setLatestAuditTime(o.getUpdateTime().format(DATETIME_FORMATTER));
				item.setOrderTime(o.getOrderTime().format(DATE_FORMATTER));
				item.setDeliveryTime(o.getDeliveryTime().format(DATE_FORMATTER));
				item.setProducts(productMap.get(o.getExOrderNo()));
				item.setChildren(relativeOrderMap.get(o.getExOrderNo()));
				return item;
			}).collect(Collectors.toList());
	}

	/**
	 * 获取所有的客户名称
	 *
	 * @return
	 */
	public List<String> getAllCustomerNames() {
		return this.customerService.getAllCustomerNames();
	}

	/**
	 * 根据客户名称获取对应客户的联系人列表
	 *
	 * @param customerName
	 * @return
	 */
	public List<CustomerVO> getCustomersByName(String customerName) {
		return this.customerService.getCustomersByName(customerName).parallelStream()
			.filter(Objects::nonNull)
			.map(CustomerVO::toVO).collect(Collectors.toList());
	}


	/**
	 * 获取所有的供应商名称
	 *
	 * @return
	 */
	public List<String> getAllSupplierNames() {
		return this.supplierService.getAllSupplierNames();
	}

	/**
	 * 获取对应供应商的联系信息
	 *
	 * @param supplierName
	 * @return
	 */
	public List<SupplierVO> getSuppliersByName(String supplierName) {
		return this.supplierService.getSuppliersByName(supplierName).parallelStream()
			.filter(Objects::nonNull)
			.map(SupplierVO::toVO).collect(Collectors.toList());
	}


	/**
	 * 订单审核
	 *
	 * @param vo
	 */
	public void auditOrder(AuditVO vo) {
		Asserts.checkArgs(null != vo && !isEmpty(vo.getAuditor())
			&& !isEmpty(vo.getAuditState())
			&& !isEmpty(vo.getExOrderNo())
			&& !isEmpty(vo.getOrderType()), "审核参数缺失");

		// 如果订单驳回，整个流程终止，更新数据库的auditState即可。
		if (AuditState.REJECT.name().equals(vo.getAuditState())) {
			this.modifyOrderAuditState(vo.getExOrderNo(), vo.getOrderType(), vo.getAuditState());
			// 是否需要插入一条终止阀门
			return;
		}
		// 如果订单审批通过，需要判断是否是最后一级的审批通过，如果是则更新订单最后的状态为审批通过，否则还是进行中
		boolean isLastValve = this.flowService.checkLastValve(vo.getExOrderNo(), vo.getOrderType(), vo.getAuditor(), vo.getRemark());
		if (isLastValve) {
			this.modifyOrderAuditState(vo.getExOrderNo(), vo.getOrderType(), AuditState.PASS.getDescription());
		}
	}

	public PaginationVO<ProductVO.ProductBase> listProducts(String product,
															String code,
															String productSku,
															Integer pageNo,
															Integer pageSize) {
		pageNo = Optional.ofNullable(pageNo).orElse(0);
		pageSize = Optional.ofNullable(pageSize).orElse(10);
		List<Product> products = this.productService.selectPage(product, code, productSku, pageNo, pageSize);
		Integer total = this.productService.getTotal(product, code, productSku);
		return PaginationVO.with(products.parallelStream()
			.filter(Objects::nonNull)
			.map(e -> {
				ProductVO.ProductBase base = new ProductVO.ProductBase();
				BeanUtils.copyProperties(e, base);
				base.setProductId(e.getId());
				base.setProductSku(e.getSku());
				return base;
			}).collect(Collectors.toList()), total);
	}

	public List<ProductVO> mergeProductInfo(List<Integer> productIds, List<CommonOrder> orders) {
		Map<Integer, Product> products = this.productService.getProductByIds(productIds)
			.parallelStream()
			.filter(Objects::nonNull)
			.collect(Collectors.toMap(Product::getId, e -> e));
		return orders.parallelStream()
			.filter(Objects::nonNull)
			.map(order -> {
				ProductVO pv = JSONObject.parseObject(order.getProductExt(), ProductVO.class);
				BeanUtils.copyProperties(products.get(order.getProductId()), pv);
				return pv;
			}).collect(Collectors.toList());
	}


	/**
	 * 生成系统内部的主订单号
	 *
	 * @return
	 */
	private String generateOrderNo() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	private void modifyOrderAuditState(String exOrderNo, String orderType, String auditState) {
		int update = OrderType.PURCHASE.name().equals(orderType) ?
			this.purchaseOrderService.updateAuditStateByExOrderNo(exOrderNo, auditState)
			: this.saleOrderService.updateAuditStateByExOrderNo(exOrderNo, auditState);
		log.info("订单审核影响订单-{},条数-{}, 影响的状态为-{}", exOrderNo, update, auditState);
	}
}
