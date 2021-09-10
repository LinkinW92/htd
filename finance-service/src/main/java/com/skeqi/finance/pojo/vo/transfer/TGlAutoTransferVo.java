package com.skeqi.finance.pojo.vo.transfer;

import com.skeqi.common.annotation.Excel;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 自动转账主视图对象 t_gl_auto_transfer
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账主视图对象")
public class TGlAutoTransferVo {

	private static final long serialVersionUID = 1L;

	/** 内码 */
	@ApiModelProperty("内码")
	private Integer fTransferId;

	/** 编码 */
	@Excel(name = "编码")
	@ApiModelProperty("编码")
	private String fNumber;

	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String fName;

	/** 账簿内码 */
	@Excel(name = "账簿内码")
	@ApiModelProperty("账簿内码")
	private Integer fBookId;

	private String bookName;

	private String bookNumber;

	/** 转账类型 */
	@Excel(name = "转账类型")
	@ApiModelProperty("转账类型")
	private String fTransferType;

	/** 凭证字 */
	@Excel(name = "凭证字")
	@ApiModelProperty("凭证字")
	private Integer fVoucherGroupId;

	/** 转账期间 */
	@Excel(name = "转账期间")
	@ApiModelProperty("转账期间")
	private String fPeriodRange;

	/** 最近一次转账时间 */
	@Excel(name = "最近一次转账时间" , width = 30, dateFormat = "yyyy-MM-dd")
	@ApiModelProperty("最近一次转账时间")
	private Date fLastDate;

	/** 禁用状态 */
	@Excel(name = "禁用状态")
	@ApiModelProperty("禁用状态")
	private String fForbidStatus;


	@ApiModelProperty("操作方式")
	private String fOptWay;

	@ApiModelProperty("操作频率")
	private String fOptFrequency;

	private String fVoucherWords;

	List<TGlAutoTransferEntryVo> transferEntries;


}
