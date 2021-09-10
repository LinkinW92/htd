package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 物料表
 * @author FQZ
 *
 */
public class CWmsMaterialT {

	private Integer id;
	private String modifyTime;  //修改时间
	private String materialNo; //物料编号
	private String materialPartno; //物料图号
	private String materialName; //物料名称
	private Integer materialType; //物料类别，物料类型表id
	private Integer materialUnit; //物料单位，物料单位表id
	private Double materialLength; //物料长度
	private Double materialWidth; //物料宽度
	private Double materialHight; //物料高度
	private Double materialVolume; //物料体积
	private Double materialWeight; //物料重量
	private Integer materialLt; //存放库位类型，0：立库，1：平库，2：other
	private Integer materialIncomType; //来料方式，0：单个，1：批次
	private String materialModel;  //物料型号
	private String materialCode;  //物料条码
	private Integer materialLowLimitmaterial; //物料库存下限；
	private String materialSc;  //物料防伪码
	private Integer materialBatch; //物料批次数量
	private String materialVr;  //物料校验规则
	private Integer daysOfFailure;//失效天数
	private String note;  //备注
	private Integer materialCost;//物料成本

	public CWmsMaterialT() {
		// TODO Auto-generated constructor stub
	}
	public CWmsMaterialT(Integer id, String modifyTime, String materialNo, String materialPartno, String materialName,
			Integer materialType, Integer materialUnit, Double materialLength, Double materialWidth,
			Double materialHight, Double materialVolume, Double materialWeight, Integer materialLt,
			Integer materialIncomType, String materialModel, String materialCode, Integer materialLowLimitmaterial,
			String materialSc, Integer materialBatch, String materialVr, Integer daysOfFailure, String note,
			Integer materialCost) {
		this.id = id;
		this.modifyTime = modifyTime;
		this.materialNo = materialNo;
		this.materialPartno = materialPartno;
		this.materialName = materialName;
		this.materialType = materialType;
		this.materialUnit = materialUnit;
		this.materialLength = materialLength;
		this.materialWidth = materialWidth;
		this.materialHight = materialHight;
		this.materialVolume = materialVolume;
		this.materialWeight = materialWeight;
		this.materialLt = materialLt;
		this.materialIncomType = materialIncomType;
		this.materialModel = materialModel;
		this.materialCode = materialCode;
		this.materialLowLimitmaterial = materialLowLimitmaterial;
		this.materialSc = materialSc;
		this.materialBatch = materialBatch;
		this.materialVr = materialVr;
		this.daysOfFailure = daysOfFailure;
		this.note = note;
		this.materialCost = materialCost;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMaterialPartno() {
		return materialPartno;
	}
	public void setMaterialPartno(String materialPartno) {
		this.materialPartno = materialPartno;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Integer getMaterialType() {
		return materialType;
	}
	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}
	public Integer getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(Integer materialUnit) {
		this.materialUnit = materialUnit;
	}
	public Double getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(Double materialLength) {
		this.materialLength = materialLength;
	}
	public Double getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(Double materialWidth) {
		this.materialWidth = materialWidth;
	}
	public Double getMaterialHight() {
		return materialHight;
	}
	public void setMaterialHight(Double materialHight) {
		this.materialHight = materialHight;
	}
	public Double getMaterialVolume() {
		return materialVolume;
	}
	public void setMaterialVolume(Double materialVolume) {
		this.materialVolume = materialVolume;
	}
	public Double getMaterialWeight() {
		return materialWeight;
	}
	public void setMaterialWeight(Double materialWeight) {
		this.materialWeight = materialWeight;
	}
	public Integer getMaterialLt() {
		return materialLt;
	}
	public void setMaterialLt(Integer materialLt) {
		this.materialLt = materialLt;
	}
	public Integer getMaterialIncomType() {
		return materialIncomType;
	}
	public void setMaterialIncomType(Integer materialIncomType) {
		this.materialIncomType = materialIncomType;
	}
	public String getMaterialModel() {
		return materialModel;
	}
	public void setMaterialModel(String materialModel) {
		this.materialModel = materialModel;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public Integer getMaterialLowLimitmaterial() {
		return materialLowLimitmaterial;
	}
	public void setMaterialLowLimitmaterial(Integer materialLowLimitmaterial) {
		this.materialLowLimitmaterial = materialLowLimitmaterial;
	}
	public String getMaterialSc() {
		return materialSc;
	}
	public void setMaterialSc(String materialSc) {
		this.materialSc = materialSc;
	}
	public Integer getMaterialBatch() {
		return materialBatch;
	}
	public void setMaterialBatch(Integer materialBatch) {
		this.materialBatch = materialBatch;
	}
	public String getMaterialVr() {
		return materialVr;
	}
	public void setMaterialVr(String materialVr) {
		this.materialVr = materialVr;
	}
	public Integer getDaysOfFailure() {
		return daysOfFailure;
	}
	public void setDaysOfFailure(Integer daysOfFailure) {
		this.daysOfFailure = daysOfFailure;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(Integer materialCost) {
		this.materialCost = materialCost;
	}
	@Override
	public String toString() {
		return "CWmsMaterialT [id=" + id + ", modifyTime=" + modifyTime + ", materialNo=" + materialNo
				+ ", materialPartno=" + materialPartno + ", materialName=" + materialName + ", materialType="
				+ materialType + ", materialUnit=" + materialUnit + ", materialLength=" + materialLength
				+ ", materialWidth=" + materialWidth + ", materialHight=" + materialHight + ", materialVolume="
				+ materialVolume + ", materialWeight=" + materialWeight + ", materialLt=" + materialLt
				+ ", materialIncomType=" + materialIncomType + ", materialModel=" + materialModel + ", materialCode="
				+ materialCode + ", materialLowLimitmaterial=" + materialLowLimitmaterial + ", materialSc=" + materialSc
				+ ", materialBatch=" + materialBatch + ", materialVr=" + materialVr + ", daysOfFailure=" + daysOfFailure
				+ ", note=" + note + ", materialCost=" + materialCost + "]";
	}



}
