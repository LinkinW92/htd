package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CMesLineT {
	@ApiModelProperty(value="ID")
	private Integer id;
	@ApiModelProperty(value="时间",required=false)
	private String Dt;
	@ApiModelProperty(value="产线名",required=false)
	private String name;
	@ApiModelProperty(value="产线描述",required=false)
	private String dsc;
	@ApiModelProperty(value="产线条码生成方式",required=false)
	private Integer codeType;
	@ApiModelProperty(value="状态",required=false)
	private Integer status;
	@ApiModelProperty(value="理论产量",required=false)
	private Integer yieldNumber;
	@ApiModelProperty(value="区域",required=false)
	private Integer region;
	@ApiModelProperty(value="计数方式",required=false)
	private Integer countType;
	@ApiModelProperty(value="产品标记",required=false)
	private String productMark;
	@ApiModelProperty(value="是否排版",required=false)
	private Integer paibanStatus;
	@ApiModelProperty(value="是否安灯",required=false)
	private Integer andengStatus;
	@ApiModelProperty(value="产线编号",required=false)
	private String code;


	/*以下为条件查询所需参数*/
	@ApiModelProperty(value="所属公司名称",required=false)
	private String companyName;
	@ApiModelProperty(value="所属工厂名称",required=false)
	private String factoryName;
	@ApiModelProperty(value="所属区域名称",required=false)
	private String areaName;
	@ApiModelProperty(value="所属车间名称",required=false)
	private String plantName;
	@ApiModelProperty(value="所属公司编码",required=false)
	private String companyCode;
	@ApiModelProperty(value="所属工厂编码",required=false)
	private String factoryCode;
	@ApiModelProperty(value="所属区域编码",required=false)
	private String areaCode;
	@ApiModelProperty(value="所属车间编码",required=false)
	private String plantCode;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}
	@ApiModelProperty(value="自定义属性",required=false)
	private Object custom;

	public Object getCustom() {
		return custom;
	}

	public void setCustom(Object custom) {
		this.custom = custom;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public Integer getPaibanStatus() {
		return paibanStatus;
	}
	public void setPaibanStatus(Integer paibanStatus) {
		this.paibanStatus = paibanStatus;
	}
	public Integer getAndengStatus() {
		return andengStatus;
	}
	public void setAndengStatus(Integer andengStatus) {
		this.andengStatus = andengStatus;
	}
	public String getProductMark() {
		return productMark;
	}
	public void setProductMark(String productMark) {
		this.productMark = productMark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDsc() {
		return dsc;
	}
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
	public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getYieldNumber() {
		return yieldNumber;
	}
	public void setYieldNumber(Integer yieldNumber) {
		this.yieldNumber = yieldNumber;
	}
	public Integer getRegion() {
		return region;
	}
	public void setRegion(Integer region) {
		this.region = region;
	}
	public Integer getCountType() {
		return countType;
	}
	public void setCountType(Integer countType) {
		this.countType = countType;
	}
	public String getDt() {
		return Dt;
	}
	public void setDt(String dt) {
		Dt = dt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CMesLineT(Integer id, String dt, String name, String dsc, Integer codeType, Integer status, Integer yieldNumber, Integer region, Integer countType, String productMark, Integer paibanStatus, Integer andengStatus, String code, String companyName, String factoryName, String areaName, String plantName) {
		super();
		this.id = id;
		Dt = dt;
		this.name = name;
		this.dsc = dsc;
		this.codeType = codeType;
		this.status = status;
		this.yieldNumber = yieldNumber;
		this.region = region;
		this.countType = countType;
		this.productMark = productMark;
		this.paibanStatus = paibanStatus;
		this.andengStatus = andengStatus;
		this.code = code;
		this.companyName = companyName;
		this.factoryName = factoryName;
		this.areaName = areaName;
		this.plantName = plantName;
	}
	public CMesLineT() {
		super();
	}


}
