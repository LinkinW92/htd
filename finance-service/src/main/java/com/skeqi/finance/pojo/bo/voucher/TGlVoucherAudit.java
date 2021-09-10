package com.skeqi.finance.pojo.bo.voucher;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 凭证录入主编辑对象 t_gl_voucher
 *
 * @author toms
 * @date 2021-07-09
 */
@Data
@ApiModel("凭证录入审核")
public class TGlVoucherAudit {


	/**
	 * 内码
	 */
	@ApiModelProperty("内码")
	@NotNull(message = "唯一ID不能为空")
	private Integer fVoucherId;

	private String fDocumentStatus;

	private String fInvalid;
}
