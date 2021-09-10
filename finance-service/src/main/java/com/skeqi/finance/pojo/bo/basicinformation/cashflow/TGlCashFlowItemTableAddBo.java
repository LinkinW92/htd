package com.skeqi.finance.pojo.bo.basicinformation.cashflow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 现金流量项目-1添加对象 t_gl_cash_flow_item_table
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("现金流量项目-1添加对象")
public class TGlCashFlowItemTableAddBo {


    /** 编码 */
	@NotBlank(message = "编码不能为空")
    @ApiModelProperty("编码")
    private String fNumber;

    /** 名称 */
	@NotBlank(message = "名称不能为空")
    @ApiModelProperty("名称")
    private String fName;

    /** 会计要素表ID */
	@NotNull(message = "会计要素表不能为空")
    @ApiModelProperty("会计要素表ID")
    private Integer fAcctGroupTblid;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private Integer fCreatorid;

    /** 创建日期 */
    @ApiModelProperty("创建日期")
    private Date fCreateDate;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private Integer fModifierid;

    /** 修改日期 */
    @ApiModelProperty("修改日期")
    private Date fModifyDate;}
