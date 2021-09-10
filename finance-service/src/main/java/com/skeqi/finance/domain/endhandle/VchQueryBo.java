package com.skeqi.finance.domain.endhandle;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VchQueryBo {

	@NotBlank(message = "凭证来源不能为空")
	private String executeType;

	@NotNull(message = "生成凭证方案ID不能为空")
	private Integer executeId;


	private Integer fPeriod;

	private Integer fYear;

	private Integer pageNum;

	private Integer pageSize;
}
