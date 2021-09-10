package com.skeqi.finance.pojo.bo.transfer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 自动转账主编辑对象 t_gl_auto_transfer
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@ApiModel("自动转账主编辑对象")
public class TGlAutoTransferEditBo {


    /** 内码 */
    @ApiModelProperty("内码")
    private Integer fTransferId;

    /** 编码 */
    @ApiModelProperty("编码")
    private String fNumber;

	@ApiModelProperty("名称")
	@NotBlank(message = "名称不能为空")
	private String fName;

    /** 账簿内码 */
    @ApiModelProperty("账簿内码")
    private Integer fBookId;

    /** 转账类型 */
    @ApiModelProperty("转账类型")
    private String fTransferType;

    /** 凭证字 */
    @ApiModelProperty("凭证字")
    @NotNull(message = "凭证字不能为空")
    private Integer fVoucherGroupId;

    /** 转账期间 */
    @ApiModelProperty("转账期间")
    private String fPeriodRange;

    /** 最近一次转账时间 */
    @ApiModelProperty("最近一次转账时间")
    private Date fLastDate;

    /** 禁用状态 */
    @ApiModelProperty("禁用状态")
    private String fForbidStatus;

    /** 禁用人 */
    @ApiModelProperty("禁用人")
    private Integer fForbidderId;

    /** 禁用日期 */
    @ApiModelProperty("禁用日期")
    private Date fForbidDate;

	@ApiModelProperty("操作方式")
	private String fOptWay;

	@ApiModelProperty("操作频率")
	private String fOptFrequency;

	@ApiModelProperty("转账分录")
	@Valid
	private List<TGlAutoTransferEntryAddBo> transferEntry;
}
