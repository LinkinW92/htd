package com.skeqi.htd.controller.vo;

import com.skeqi.htd.common.BizException;
import com.skeqi.htd.common.OrderState;
import com.skeqi.htd.common.StockState;
import com.skeqi.htd.po.entity.Customer;
import com.skeqi.htd.po.entity.SaleOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.skeqi.htd.common.Constants.*;

/**
 * 销售订单vo
 *
 * @author linkin
 */
@Data
public class SaleOrderVO {
	@Data
	public static class CreateVO {
		@ApiModelProperty("外部订单号")
		private String exOrderNo;
		@ApiModelProperty("下单时间")
		private String orderTime;
		@ApiModelProperty("交货时间")
		private String deliveryTime;
		@ApiModelProperty("客户")
		private String customer;
		@ApiModelProperty("销售员")
		private String seller;
		@ApiModelProperty("附件")
		private List<String> materialList;
		@ApiModelProperty("优惠率")
		private String favorableRate;
		@ApiModelProperty("优惠金额")
		private String favorableAmount;
		@ApiModelProperty("应付款")
		private String dueAccount;
		@ApiModelProperty("联系人")
		private String contact;
		@ApiModelProperty("产品明细")
		private List<ProductVO> products;
		@ApiModelProperty("审核状态，新建订单时有两种，1：保存为草稿(DRAFT)  2：保存并提交审批(TO_AUDIT)")
		private String auditState;

		@SuppressWarnings("Duplicates")
		public List<SaleOrder> toEntities(String orderNo) {
			if (CollectionUtils.isEmpty(products)) {
				throw new BizException("产品信息缺失");
			}
			List<SaleOrder> orders = new ArrayList<>();
			int i = 0;
			boolean hasTax = this.products.parallelStream().filter(Objects::nonNull).filter(e -> null != e.getTaxRate()).count() > 0;
			for (ProductVO p : products) {
				SaleOrder order = new SaleOrder();
				BeanUtils.copyProperties(this, order);
				BeanUtils.copyProperties(p, order);
				order.setProduct(p.getName());
				order.setOrderTime(LocalDate.parse(this.orderTime, DATE_FORMATTER).atTime(LocalTime.MIN));
				order.setDeliveryTime(LocalDate.parse(this.deliveryTime, DATE_FORMATTER).atTime(LocalTime.MIN));
				order.setProductExt(p.stringifyExt());
				order.setOrderNo(orderNo);
				order.setSubOrderNo(JOINER.join(orderNo, i++));
				if (!CollectionUtils.isEmpty(materialList)) {
					order.setMaterials(COMMA_JOINER.join(materialList));
				}
				order.setOrderState(OrderState.RUNNING.getDescription());
				order.setAuditState(auditState);
				order.setStockState(StockState.NONE_OUT.getDescription());
				// TODO 接入登入系统，获取creator信息
				order.setCreator(this.seller);
				order.setHasTax(hasTax);
				orders.add(order);
			}
			return orders;
		}
	}

	@Data
	public static class DetailVO extends SaleOrderVO {
		@ApiModelProperty("订单号")
		private String exOrderNo;
		@ApiModelProperty("单据日期")
		private String orderTime;
		@ApiModelProperty("交货日期")
		private String deliveryTime;
		@ApiModelProperty("客户名称")
		private String customer;
		@ApiModelProperty("销售名称")
		private String seller;
		@ApiModelProperty("产品信息")
		private List<ProductVO> products;
		@ApiModelProperty("附件信息")
		private List<String> materials;
		@ApiModelProperty("应付款")
		private String dueAccount;
		@ApiModelProperty("联系人")
		private String contact;
		@ApiModelProperty("联系人手机")
		private String contactMobile;
		@ApiModelProperty("联系人电话")
		private String contactTel;
		@ApiModelProperty("联系人地址")
		private String contactAddress;
		@ApiModelProperty("订单已收款")
		private String collectedMoney;
		@ApiModelProperty("创建人")
		private String creator;
		@ApiModelProperty("创建时间")
		private String createTime;
		@ApiModelProperty("单据备注")
		private String remark;

		public static DetailVO build(SaleOrder saleOrder, List<ProductVO> productVOList, Customer customer) {
			DetailVO vo = new DetailVO();
			return vo;
		}
	}

	@Data
	public static class ItemVO extends SaleOrderVO {
		@ApiModelProperty("数据库主键")
		private Integer id;
		@ApiModelProperty("订单号")
		private String exOrderNo;
		@ApiModelProperty("单据日期")
		private String orderTime;
		@ApiModelProperty("交货日期")
		private String deliveryTime;
		@ApiModelProperty("客户名称")
		private String customer;
		@ApiModelProperty("销售人员")
		private String seller;
		@ApiModelProperty("优惠金额")
		private String favourableMoney;
		@ApiModelProperty("应付款")
		private String dueAccount;
		@ApiModelProperty("订单状态")
		private String orderState;
		@ApiModelProperty("审核状态")
		private String auditState;
		@ApiModelProperty("当前审核人")
		private String auditor;
		@ApiModelProperty("最近一次审核时间")
		private String latestAuditTime;
		@ApiModelProperty("出库状态")
		private String stockState;
		@ApiModelProperty("单据备注")
		private String remark;
		@ApiModelProperty("产品信息列表")
		private List<ProductVO> products;
		@ApiModelProperty("关联的采购单信息")
		private List<PurchaseOrderVO.DetailVO> children;
	}
}

