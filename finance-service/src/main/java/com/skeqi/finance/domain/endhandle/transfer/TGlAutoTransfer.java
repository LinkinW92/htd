package com.skeqi.finance.domain.endhandle.transfer;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 自动转账主对象 t_gl_auto_transfer
 *
 * @author toms
 * @date 2021-07-26
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_gl_auto_transfer")
public class TGlAutoTransfer implements Serializable {

    private static final long serialVersionUID=1L;


    /** 内码 */
    @TableId(value = "f_transfer_id")
    private Integer fTransferId;

    /** 编码 */
    private String fNumber;

	/** 编码 */
	private String fName;

    /** 账簿内码 */
    private Integer fBookId;

    /** 转账类型 */
    private String fTransferType;

    /** 凭证字 */
    private Integer fVoucherGroupId;

    /** 转账期间 */
    private String fPeriodRange;

    /** 最近一次转账时间 */
    private Date fLastDate;

    /** 禁用状态 */
    private String fForbidStatus;

    /** 禁用人 */
    private Integer fForbidderId;

    /** 禁用日期 */
    private Date fForbidDate;

	private String fOptWay;

	private String fOptFrequency;

}
