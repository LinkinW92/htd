package com.skeqi.finance.pojo.bo.voucher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import com.skeqi.common.core.domain.BaseEntity;

/**
 * 凭证分录现金流量分页查询对象 t_gl_voucher_entry_cash
 *
 * @author toms
 * @date 2021-07-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("凭证分录现金流量分页查询对象")
public class TGlVoucherEntryCashQueryBo extends BaseEntity {

	/** 分页大小 */
	@ApiModelProperty("分页大小")
	private Integer pageSize;
	/** 当前页数 */
	@ApiModelProperty("当前页数")
	private Integer pageNum;
	/** 排序列 */
	@ApiModelProperty("排序列")
	private String orderByColumn;
	/** 排序的方向desc或者asc */
	@ApiModelProperty(value = "排序的方向", example = "asc,desc")
	private String isAsc;


	/** 账簿ID */
	@ApiModelProperty("账簿ID")
	private Integer bookId;
	/** 对方科目ID */
	@ApiModelProperty("对方科目ID")
	private Integer acctId;
	/** 凭证分录ID */
	@ApiModelProperty("凭证分录ID")
	private Integer voucherEntryId;
	/** 主表ID */
	@ApiModelProperty("主表ID")
	private Integer mainTableId;
	/** 附表ID */
	@ApiModelProperty("附表ID")
	private Integer attachTableId;
	/** 本位币金额 */
	@ApiModelProperty("本位币金额")
	private BigDecimal amount;

	/** 现金科目 */
	@ApiModelProperty("现金科目")
	private Integer cashAccountId;

	/** 币别 */
	@ApiModelProperty("币别")
	private Integer currencyId;
}
