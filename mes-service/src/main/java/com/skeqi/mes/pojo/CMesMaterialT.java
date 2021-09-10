package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesMaterialT {
	private Integer id;//                          NUMBER not null,
	private String graphNumber;//                NVARCHAR2(50),
	private String materialNo;//                 NVARCHAR2(50),
	private String materialName;//               NVARCHAR2(50),
	private String materialNames;//               NVARCHAR2(50),
	private Integer materialTypeId;//
	private String materialEdition;//            NVARCHAR2(50),
	private Integer assemblyTypeId;//                 NUMBER,
	private String printLable;//                 NVARCHAR2(50),
	private String productionCommodityNumber;// NVARCHAR2(50),
	private String productionLicence;//          NVARCHAR2(50),
	private String proctionSecurityCode;//      NVARCHAR2(50),
	private String samplingType;//               NVARCHAR2(50),
	private String transferMaterial;//           NVARCHAR2(50),
	private String boxedNumber;//                NVARCHAR2(50),
	private Integer batchNumber;//                NUMBER,
	private String materialVr;//                 NVARCHAR2(50),
	private Integer standardNumber;//             NUMBER,
	private Date dt;//                          TIMESTAMP(6)
	private String assemblyTypeName;
	private String assemblyName;
	private String materialTypeName;//               NVARCHAR2(50),
	private String materialSeriesName;
	private Integer materialId;
	private String path;  //图片路径

	private String materialLength;//物料长度，单位m
	private String materialWidth;//物料宽度，单位m
	private String materialHight;//物料高度，单位m
	private String materialVolume;//物料体积。单位m3
	private String materialWeight;//物料重量，单位KG
	private String materialLt;//存放库位类型，0：立库；1：平库；2：other
	private String materialIncomType;//来料方式，0：单个；1：批次
	private String materialLowLimitmaterial;//物料库存下限
	private String materialBatch;//物料批次数量
	private String daysOfFailure;//失效天数

	private String tracesType;//追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)

	public String getTracesType() {
		return tracesType;
	}

	public void setTracesType(String tracesType) {
		this.tracesType = tracesType;
	}

	public String getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(String materialLength) {
		this.materialLength = materialLength;
	}
	public String getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(String materialWidth) {
		this.materialWidth = materialWidth;
	}
	public String getMaterialHight() {
		return materialHight;
	}
	public void setMaterialHight(String materialHight) {
		this.materialHight = materialHight;
	}
	public String getMaterialVolume() {
		return materialVolume;
	}
	public void setMaterialVolume(String materialVolume) {
		this.materialVolume = materialVolume;
	}
	public String getMaterialWeight() {
		return materialWeight;
	}
	public void setMaterialWeight(String materialWeight) {
		this.materialWeight = materialWeight;
	}
	public String getMaterialLt() {
		return materialLt;
	}
	public void setMaterialLt(String materialLt) {
		this.materialLt = materialLt;
	}
	public String getMaterialIncomType() {
		return materialIncomType;
	}
	public void setMaterialIncomType(String materialIncomType) {
		this.materialIncomType = materialIncomType;
	}
	public String getMaterialLowLimitmaterial() {
		return materialLowLimitmaterial;
	}
	public void setMaterialLowLimitmaterial(String materialLowLimitmaterial) {
		this.materialLowLimitmaterial = materialLowLimitmaterial;
	}
	public String getMaterialBatch() {
		return materialBatch;
	}
	public void setMaterialBatch(String materialBatch) {
		this.materialBatch = materialBatch;
	}
	public String getDaysOfFailure() {
		return daysOfFailure;
	}
	public void setDaysOfFailure(String daysOfFailure) {
		this.daysOfFailure = daysOfFailure;
	}
	public String getMaterialNames() {
		return materialNames;
	}
	public void setMaterialNames(String materialNames) {
		this.materialNames = materialNames;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	private String ceslabelmanagertName;
	public String getCeslabelmanagertName() {
		return ceslabelmanagertName;
	}
	public void setCeslabelmanagertName(String ceslabelmanagertName) {
		this.ceslabelmanagertName = ceslabelmanagertName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGraphNumber() {
		return graphNumber;
	}
	public void setGraphNumber(String graphNumber) {
		this.graphNumber = graphNumber;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getMaterialTypeId() {
		return materialTypeId;
	}
	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	public String getMaterialEdition() {
		return materialEdition;
	}
	public void setMaterialEdition(String materialEdition) {
		this.materialEdition = materialEdition;
	}
	public Integer getAssemblyTypeId() {
		return assemblyTypeId;
	}
	public void setAssemblyTypeId(Integer assemblyTypeId) {
		this.assemblyTypeId = assemblyTypeId;
	}
	public String getPrintLable() {
		return printLable;
	}
	public void setPrintLable(String printLable) {
		this.printLable = printLable;
	}
	public String getProductionCommodityNumber() {
		return productionCommodityNumber;
	}
	public void setProductionCommodityNumber(String productionCommodityNumber) {
		this.productionCommodityNumber = productionCommodityNumber;
	}
	public String getProductionLicence() {
		return productionLicence;
	}
	public void setProductionLicence(String productionLicence) {
		this.productionLicence = productionLicence;
	}
	public String getProctionSecurityCode() {
		return proctionSecurityCode;
	}
	public void setProctionSecurityCode(String proctionSecurityCode) {
		this.proctionSecurityCode = proctionSecurityCode;
	}
	public String getSamplingType() {
		return samplingType;
	}
	public void setSamplingType(String samplingType) {
		this.samplingType = samplingType;
	}
	public String getTransferMaterial() {
		return transferMaterial;
	}
	public void setTransferMaterial(String transferMaterial) {
		this.transferMaterial = transferMaterial;
	}
	public String getBoxedNumber() {
		return boxedNumber;
	}
	public void setBoxedNumber(String boxedNumber) {
		this.boxedNumber = boxedNumber;
	}
	public Integer getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(Integer batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getMaterialVr() {
		return materialVr;
	}
	public void setMaterialVr(String materialVr) {
		this.materialVr = materialVr;
	}
	public Integer getStandardNumber() {
		return standardNumber;
	}
	public void setStandardNumber(Integer standardNumber) {
		this.standardNumber = standardNumber;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getAssemblyTypeName() {
		return assemblyTypeName;
	}
	public void setAssemblyTypeName(String assemblyTypeName) {
		this.assemblyTypeName = assemblyTypeName;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	public String getMaterialTypeName() {
		return materialTypeName;
	}
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getMaterialSeriesName() {
		return materialSeriesName;
	}
	public void setMaterialSeriesName(String materialSeriesName) {
		this.materialSeriesName = materialSeriesName;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public CMesMaterialT() {
		super();
	}
	public CMesMaterialT(Integer id, String graphNumber, String materialNo, String materialName, String materialNames,
			Integer materialTypeId, String materialEdition, Integer assemblyTypeId, String printLable,
			String productionCommodityNumber, String productionLicence, String proctionSecurityCode,
			String samplingType, String transferMaterial, String boxedNumber, Integer batchNumber, String materialVr,
			Integer standardNumber, Date dt, String assemblyTypeName, String assemblyName, String materialTypeName,
			String materialSeriesName, Integer materialId, String path, String ceslabelmanagertName) {
		super();
		this.id = id;
		this.graphNumber = graphNumber;
		this.materialNo = materialNo;
		this.materialName = materialName;
		this.materialNames = materialNames;
		this.materialTypeId = materialTypeId;
		this.materialEdition = materialEdition;
		this.assemblyTypeId = assemblyTypeId;
		this.printLable = printLable;
		this.productionCommodityNumber = productionCommodityNumber;
		this.productionLicence = productionLicence;
		this.proctionSecurityCode = proctionSecurityCode;
		this.samplingType = samplingType;
		this.transferMaterial = transferMaterial;
		this.boxedNumber = boxedNumber;
		this.batchNumber = batchNumber;
		this.materialVr = materialVr;
		this.standardNumber = standardNumber;
		this.dt = dt;
		this.assemblyTypeName = assemblyTypeName;
		this.assemblyName = assemblyName;
		this.materialTypeName = materialTypeName;
		this.materialSeriesName = materialSeriesName;
		this.materialId = materialId;
		this.path = path;
		this.ceslabelmanagertName = ceslabelmanagertName;
	}
	@Override
	public String toString() {
		return "CMesMaterialT [id=" + id + ", graphNumber=" + graphNumber + ", materialNo=" + materialNo
				+ ", materialName=" + materialName + ", materialNames=" + materialNames + ", materialTypeId="
				+ materialTypeId + ", materialEdition=" + materialEdition + ", assemblyTypeId=" + assemblyTypeId
				+ ", printLable=" + printLable + ", productionCommodityNumber=" + productionCommodityNumber
				+ ", productionLicence=" + productionLicence + ", proctionSecurityCode=" + proctionSecurityCode
				+ ", samplingType=" + samplingType + ", transferMaterial=" + transferMaterial + ", boxedNumber="
				+ boxedNumber + ", batchNumber=" + batchNumber + ", materialVr=" + materialVr + ", standardNumber="
				+ standardNumber + ", dt=" + dt + ", assemblyTypeName=" + assemblyTypeName + ", assemblyName="
				+ assemblyName + ", materialTypeName=" + materialTypeName + ", materialSeriesName=" + materialSeriesName
				+ ", materialId=" + materialId + ", path=" + path + ", ceslabelmanagertName=" + ceslabelmanagertName
				+ "]";
	}


}
