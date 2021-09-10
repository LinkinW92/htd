package com.skeqi.htd.controller.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.htd.common.*;
import com.skeqi.htd.po.entity.PurchaseOrder;
import com.skeqi.htd.po.entity.Supplier;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.skeqi.htd.common.Constants.*;

/**
 * 采购单VO
 *
 * @author linkin
 */
@Data
public class PurchaseOrderVO {

	@Data
	public static class CreateVO extends PurchaseOrderVO {
		@ApiModelProperty("外部订单号")
		private String exOrderNo;
		@ApiModelProperty("下单时间")
		private String orderTime;
		@ApiModelProperty("交货时间")
		private String deliveryTime;
		@ApiModelProperty("供应商")
		private String supplier;
		@ApiModelProperty("采购员")
		private String purchaser;
		@ApiModelProperty("附件")
		private List<String> materials;
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
		public List<PurchaseOrder> toEntities(String orderNo) {
			if (CollectionUtils.isEmpty(products)) {
				throw new BizException("产品信息缺失");
			}
			List<PurchaseOrder> orders = new ArrayList<>();
			boolean hasTax = this.products.parallelStream().filter(Objects::nonNull).filter(e -> null != e.getTaxRate()).count() > 0;
			int i = 0;
			for (ProductVO p : products) {
				PurchaseOrder order = new PurchaseOrder();
				BeanUtils.copyProperties(this, order);
				BeanUtils.copyProperties(p, order);
				order.setProduct(p.getName());
				order.setDeliveryTime(LocalDate.parse(this.getDeliveryTime(), DATE_FORMATTER).atTime(LocalTime.MIN));
				order.setOrderTime(LocalDate.parse(this.getOrderTime(), DATE_FORMATTER).atTime(LocalTime.MIN));
				order.setProductExt(p.stringifyExt());
				order.setOrderNo(orderNo);
				order.setSubOrderNo(JOINER.join(orderNo, i++));
				order.setMaterials(COMMA_JOINER.join(materials));
				order.setOrderState(OrderState.RUNNING.getDescription());
				order.setAuditState(auditState);
				order.setStockState(StockState.NONE_IN.getDescription());
				// TODO 接入登入系统，获取creator信息
				order.setCreator(this.purchaser);
				order.setHasTax(hasTax);
				orders.add(order);
			}
			return orders;
		}
	}

	@Data
	public static class DetailVO extends PurchaseOrderVO {
		@ApiModelProperty("数据库主键")
		private Integer id;
		@ApiModelProperty("订单号")
		private String exOrderNo;
		@ApiModelProperty("单据日期")
		private String orderTime;
		@ApiModelProperty("交货日期")
		private String deliveryTime;
		@ApiModelProperty("供应商名称")
		private String supplier;
		@ApiModelProperty("采购员名称")
		private String purchaser;
		@ApiModelProperty("产品信息")
		private List<ProductVO> products;
		@ApiModelProperty("附件信息")
		private List<String> materialsList;
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
		@ApiModelProperty("订单已付款")
		private String paidMoney;
		@ApiModelProperty("创建人")
		private String creator;
		@ApiModelProperty("创建时间")
		private String createTime;
		@ApiModelProperty("单据备注")
		private String remark;

		public static DetailVO build(PurchaseOrder order, List<ProductVO> product, Supplier supplier) {
			if (null == supplier) {
				throw new BizException(ResultCode.DATA_EXCEPTION);
			}
			DetailVO vo = new DetailVO();
			BeanUtils.copyProperties(order, vo);
			BeanUtils.copyProperties(supplier, vo);
			vo.setOrderTime(DATE_FORMATTER.format(order.getOrderTime()));
			vo.setDeliveryTime(DATE_FORMATTER.format(order.getDeliveryTime()));
			vo.setProducts(product);
			vo.setMaterialsList(StringUtils.isEmpty(order.getMaterials()) ? new ArrayList<>() : Arrays.asList(order.getMaterials().split(Constants.COMMA)));
			return vo;
		}
	}

	@Data
	public static class ItemVO extends PurchaseOrderVO {
		@ApiModelProperty("数据库主键")
		private Integer id;
		@ApiModelProperty("外部订单号")
		private String exOrderNo;
		@ApiModelProperty("单据日期")
		private String orderTime;
		@ApiModelProperty("交货日期")
		private String deliveryTime;
		@ApiModelProperty("供应商名称")
		private String supplier;
		@ApiModelProperty("采购员名称")
		private String purchaser;
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
		@ApiModelProperty("入库状态")
		private String stockState;
		@ApiModelProperty("单据备注")
		private String remark;
		@ApiModelProperty("关联销售单")
		private List<PurchaseOrderVO.DetailVO> children = JSONObject.parseArray("[{\"contact\":\"哎哟\",\"exOrderNo\":\"302109302\",\"id\":3,\"orderTime\":\"2021-08-03\"},{\"contact\":\"嘿嘿\",\"exOrderNo\":\"302109302\",\"id\":2,\"orderTime\":\"2021-08-03\"}]",
			PurchaseOrderVO.DetailVO.class);
		@ApiModelProperty("产品信息")
		private List<ProductVO> products = JSONObject.parseArray("[{\"amount\":2,\"brand\":\"1\",\"code\":\"1234\"},{\"amount\":2,\"brand\":\"1\",\"code\":\"1234\"}]", ProductVO.class);

		public static void main(String[] args) {
			ProductVO vo = new ProductVO();
			vo.setBrand("1");
			vo.setAmount(2);
			vo.setCode("1234");

			ProductVO vo1 = new ProductVO();
			vo1.setBrand("1");
			vo1.setAmount(2);
			vo1.setCode("1234");


			System.out.println(JSON.toJSONString(Arrays.asList(vo1, vo)));
		}
	}
}
