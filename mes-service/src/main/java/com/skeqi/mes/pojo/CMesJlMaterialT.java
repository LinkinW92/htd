package com.skeqi.mes.pojo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class CMesJlMaterialT {

    private Integer id;
    private String bomId;  //物料编码
    private String materialName;  //物料名称
    private String description;  //描述
    private String specifications;  //规格
    private String materialGroup;  //物料组
    private String materialType;  //物料类型(BOM,物料)
    private Integer tracesType;  //追溯方式(0.混合追溯，1.批次追溯，2.精确追溯)
    private String typeName;
    private String stockUnit;  //库存单位
    private String inventoryModelGroup;  //库存模型组
    private String inventoryDimensionGroup;  //库存维组
    private String release;  //发放（y:自动发送，n:工单发送
    private String inspection;  //检验等级
    private String fictitious;  //虚拟
    private String salesUnit;  //销售单位
    private String secrecy;  //保密否
    private String purchasingUnit;  //采购单位
    private String productionTeam;  //生产组
    private String mininumberOfPackages;  //最小包装数量
    private String termOfValidity;  //有效期
    private String typenum;  //型号
    private String voltage;  //电压容量
    private String partCounts;  //子件数
    private String cellCapacity;  //电芯容量
    private String scan;  //是否扫描(Y:是,N:否)
    private String cellSpecification;  //电芯规格
    private String customerPartCode;  //客户零件编码(SD新增字段)

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

    //需要 自定义属性所需参数 才添加这个参数
    private Object custom;
    // 外部物料编码--API脚本专用渠道
    private String bomCode;

    public Integer getTracesType() {
        return tracesType;
    }

    public void setTracesType(Integer tracesType) {
        this.tracesType = tracesType;
    }

    public Object getCustom() {
        return custom;
    }

    public void setCustom(Object custom) {
        this.custom = custom;
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


    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getBomId() {
        return bomId;
    }


    public void setBomId(String bomId) {
        this.bomId = bomId;
    }


	public String getBomCode() {
		return bomCode;
	}


	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}

    public String getMaterialName() {
        return materialName;
    }


    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getSpecifications() {
        return specifications;
    }


    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }


    public String getMaterialGroup() {
        return materialGroup;
    }


    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }


    public String getMaterialType() {
        return materialType;
    }


    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }


    public String getStockUnit() {
        return stockUnit;
    }


    public void setStockUnit(String stockUnit) {
        this.stockUnit = stockUnit;
    }


    public String getInventoryModelGroup() {
        return inventoryModelGroup;
    }


    public void setInventoryModelGroup(String inventoryModelGroup) {
        this.inventoryModelGroup = inventoryModelGroup;
    }


    public String getInventoryDimensionGroup() {
        return inventoryDimensionGroup;
    }


    public void setInventoryDimensionGroup(String inventoryDimensionGroup) {
        this.inventoryDimensionGroup = inventoryDimensionGroup;
    }


    public String getRelease() {
        return release;
    }


    public void setRelease(String release) {
        this.release = release;
    }


    public String getInspection() {
        return inspection;
    }


    public void setInspection(String inspection) {
        this.inspection = inspection;
    }


    public String getFictitious() {
        return fictitious;
    }


    public void setFictitious(String fictitious) {
        this.fictitious = fictitious;
    }


    public String getSalesUnit() {
        return salesUnit;
    }


    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }


    public String getSecrecy() {
        return secrecy;
    }


    public void setSecrecy(String secrecy) {
        this.secrecy = secrecy;
    }


    public String getPurchasingUnit() {
        return purchasingUnit;
    }


    public void setPurchasingUnit(String purchasingUnit) {
        this.purchasingUnit = purchasingUnit;
    }


    public String getProductionTeam() {
        return productionTeam;
    }


    public void setProductionTeam(String productionTeam) {
        this.productionTeam = productionTeam;
    }


    public String getMininumberOfPackages() {
        return mininumberOfPackages;
    }


    public void setMininumberOfPackages(String mininumberOfPackages) {
        this.mininumberOfPackages = mininumberOfPackages;
    }


    public String getTermOfValidity() {
        return termOfValidity;
    }


    public void setTermOfValidity(String termOfValidity) {
        this.termOfValidity = termOfValidity;
    }


    public String getTypenum() {
        return typenum;
    }


    public void setTypenum(String typenum) {
        this.typenum = typenum;
    }


    public String getVoltage() {
        return voltage;
    }


    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }


    public String getPartCounts() {
        return partCounts;
    }


    public void setPartCounts(String partCounts) {
        this.partCounts = partCounts;
    }


    public String getCellCapacity() {
        return cellCapacity;
    }


    public void setCellCapacity(String cellCapacity) {
        this.cellCapacity = cellCapacity;
    }


    public String getScan() {
        return scan;
    }


    public void setScan(String scan) {
        this.scan = scan;
    }


    public String getCellSpecification() {
        return cellSpecification;
    }


    public void setCellSpecification(String cellSpecification) {
        this.cellSpecification = cellSpecification;
    }




    public String getCustomerPartCode() {
		return customerPartCode;
	}

	public void setCustomerPartCode(String customerPartCode) {
		this.customerPartCode = customerPartCode;
	}



	public CMesJlMaterialT(Integer id, String bomId, String materialName, String description, String specifications,
			String materialGroup, String materialType, Integer tracesType, String typeName, String stockUnit,
			String inventoryModelGroup, String inventoryDimensionGroup, String release, String inspection,
			String fictitious, String salesUnit, String secrecy, String purchasingUnit, String productionTeam,
			String mininumberOfPackages, String termOfValidity, String typenum, String voltage, String partCounts,
			String cellCapacity, String scan, String cellSpecification, String customerPartCode, String materialLength,
			String materialWidth, String materialHight, String materialVolume, String materialWeight, String materialLt,
			String materialIncomType, String materialLowLimitmaterial, String materialBatch, String daysOfFailure,
			Object custom, String bomCode) {
		super();
		this.id = id;
		this.bomId = bomId;
		this.materialName = materialName;
		this.description = description;
		this.specifications = specifications;
		this.materialGroup = materialGroup;
		this.materialType = materialType;
		this.tracesType = tracesType;
		this.typeName = typeName;
		this.stockUnit = stockUnit;
		this.inventoryModelGroup = inventoryModelGroup;
		this.inventoryDimensionGroup = inventoryDimensionGroup;
		this.release = release;
		this.inspection = inspection;
		this.fictitious = fictitious;
		this.salesUnit = salesUnit;
		this.secrecy = secrecy;
		this.purchasingUnit = purchasingUnit;
		this.productionTeam = productionTeam;
		this.mininumberOfPackages = mininumberOfPackages;
		this.termOfValidity = termOfValidity;
		this.typenum = typenum;
		this.voltage = voltage;
		this.partCounts = partCounts;
		this.cellCapacity = cellCapacity;
		this.scan = scan;
		this.cellSpecification = cellSpecification;
		this.customerPartCode = customerPartCode;
		this.materialLength = materialLength;
		this.materialWidth = materialWidth;
		this.materialHight = materialHight;
		this.materialVolume = materialVolume;
		this.materialWeight = materialWeight;
		this.materialLt = materialLt;
		this.materialIncomType = materialIncomType;
		this.materialLowLimitmaterial = materialLowLimitmaterial;
		this.materialBatch = materialBatch;
		this.daysOfFailure = daysOfFailure;
		this.custom = custom;
		this.bomCode = bomCode;
	}

	public CMesJlMaterialT(String bomId, String materialName, String description, String specifications,
                           String materialGroup, String materialType, String stockUnit, String inventoryModelGroup,
                           String inventoryDimensionGroup, String release, String inspection, String fictitious, String salesUnit,
                           String secrecy, String purchasingUnit, String productionTeam, String mininumberOfPackages,
                           String termOfValidity, String typenum, String voltage, String partCounts, String cellCapacity, String scan,
                           String cellSpecification, String materialLength, String materialWidth, String materialHight,
                           String materialVolume, String materialWeight, String materialLt, String materialIncomType,
                           String materialLowLimitmaterial, String materialBatch, String daysOfFailure, String bomCode) {
        this.bomId = bomId;
        this.bomCode = bomCode;
        this.materialName = materialName;
        this.description = description;
        this.specifications = specifications;
        this.materialGroup = materialGroup;
        this.materialType = materialType;
        this.stockUnit = stockUnit;
        this.inventoryModelGroup = inventoryModelGroup;
        this.inventoryDimensionGroup = inventoryDimensionGroup;
        this.release = release;
        this.inspection = inspection;
        this.fictitious = fictitious;
        this.salesUnit = salesUnit;
        this.secrecy = secrecy;
        this.purchasingUnit = purchasingUnit;
        this.productionTeam = productionTeam;
        this.mininumberOfPackages = mininumberOfPackages;
        this.termOfValidity = termOfValidity;
        this.typenum = typenum;
        this.voltage = voltage;
        this.partCounts = partCounts;
        this.cellCapacity = cellCapacity;
        this.scan = scan;
        this.cellSpecification = cellSpecification;
        this.materialLength = materialLength;
        this.materialWidth = materialWidth;
        this.materialHight = materialHight;
        this.materialVolume = materialVolume;
        this.materialWeight = materialWeight;
        this.materialLt = materialLt;
        this.materialIncomType = materialIncomType;
        this.materialLowLimitmaterial = materialLowLimitmaterial;
        this.materialBatch = materialBatch;
        this.daysOfFailure = daysOfFailure;
    }

    public CMesJlMaterialT(Integer id, String bomId, String materialName, String description, String specifications,
                           String materialGroup, String materialType, String stockUnit, String inventoryModelGroup,
                           String inventoryDimensionGroup, String release, String inspection, String fictitious, String salesUnit,
                           String secrecy, String purchasingUnit, String productionTeam, String mininumberOfPackages,
                           String termOfValidity, String typenum, String voltage, String partCounts, String cellCapacity, String scan,
                           String cellSpecification, String materialLength, String materialWidth, String materialHight,
                           String materialVolume, String materialWeight, String materialLt, String materialIncomType,
                           String materialLowLimitmaterial, String materialBatch, String daysOfFailure, String bomCode) {
        super();
        this.id = id;
        this.bomId = bomId;
        this.bomCode = bomCode;
        this.materialName = materialName;
        this.description = description;
        this.specifications = specifications;
        this.materialGroup = materialGroup;
        this.materialType = materialType;
        this.stockUnit = stockUnit;
        this.inventoryModelGroup = inventoryModelGroup;
        this.inventoryDimensionGroup = inventoryDimensionGroup;
        this.release = release;
        this.inspection = inspection;
        this.fictitious = fictitious;
        this.salesUnit = salesUnit;
        this.secrecy = secrecy;
        this.purchasingUnit = purchasingUnit;
        this.productionTeam = productionTeam;
        this.mininumberOfPackages = mininumberOfPackages;
        this.termOfValidity = termOfValidity;
        this.typenum = typenum;
        this.voltage = voltage;
        this.partCounts = partCounts;
        this.cellCapacity = cellCapacity;
        this.scan = scan;
        this.cellSpecification = cellSpecification;
        this.materialLength = materialLength;
        this.materialWidth = materialWidth;
        this.materialHight = materialHight;
        this.materialVolume = materialVolume;
        this.materialWeight = materialWeight;
        this.materialLt = materialLt;
        this.materialIncomType = materialIncomType;
        this.materialLowLimitmaterial = materialLowLimitmaterial;
        this.materialBatch = materialBatch;
        this.daysOfFailure = daysOfFailure;
    }


//    @Override
//    public String toString() {
//        return "CMesJlMaterialT [id=" + id + ", bomId=" + bomId + ", materialName=" + materialName + ", description="
//                + description + ", specifications=" + specifications + ", materialGroup=" + materialGroup
//                + ", materialType=" + materialType + ", stockUnit=" + stockUnit + ", inventoryModelGroup="
//                + inventoryModelGroup + ", inventoryDimensionGroup=" + inventoryDimensionGroup + ", release=" + release
//                + ", inspection=" + inspection + ", fictitious=" + fictitious + ", salesUnit=" + salesUnit
//                + ", secrecy=" + secrecy + ", purchasingUnit=" + purchasingUnit + ", productionTeam=" + productionTeam
//                + ", mininumberOfPackages=" + mininumberOfPackages + ", termOfValidity=" + termOfValidity + ", typenum="
//                + typenum + ", voltage=" + voltage + ", partCounts=" + partCounts + ", cellCapacity=" + cellCapacity
//                + ", scan=" + scan + ", cellSpecification=" + cellSpecification + ",bomCode=" + bomCode + "]";
//    }



    public CMesJlMaterialT(Integer id, String bomId, String materialName, String description, String specifications,
                           String materialGroup, String materialType, String typeName, String stockUnit, String inventoryModelGroup,
                           String inventoryDimensionGroup, String release, String inspection, String fictitious, String salesUnit,
                           String secrecy, String purchasingUnit, String productionTeam, String mininumberOfPackages,
                           String termOfValidity, String typenum, String voltage, String partCounts, String cellCapacity, String scan,
                           String cellSpecification, String bomCode) {
        super();
        this.id = id;
        this.bomId = bomId;
        this.bomCode = bomCode;
        this.materialName = materialName;
        this.description = description;
        this.specifications = specifications;
        this.materialGroup = materialGroup;
        this.materialType = materialType;
        this.typeName = typeName;
        this.stockUnit = stockUnit;
        this.inventoryModelGroup = inventoryModelGroup;
        this.inventoryDimensionGroup = inventoryDimensionGroup;
        this.release = release;
        this.inspection = inspection;
        this.fictitious = fictitious;
        this.salesUnit = salesUnit;
        this.secrecy = secrecy;
        this.purchasingUnit = purchasingUnit;
        this.productionTeam = productionTeam;
        this.mininumberOfPackages = mininumberOfPackages;
        this.termOfValidity = termOfValidity;
        this.typenum = typenum;
        this.voltage = voltage;
        this.partCounts = partCounts;
        this.cellCapacity = cellCapacity;
        this.scan = scan;
        this.cellSpecification = cellSpecification;
    }


    @Override
	public String toString() {
		return "CMesJlMaterialT [id=" + id + ", bomId=" + bomId + ", materialName=" + materialName + ", description="
				+ description + ", specifications=" + specifications + ", materialGroup=" + materialGroup
				+ ", materialType=" + materialType + ", tracesType=" + tracesType + ", typeName=" + typeName
				+ ", stockUnit=" + stockUnit + ", inventoryModelGroup=" + inventoryModelGroup
				+ ", inventoryDimensionGroup=" + inventoryDimensionGroup + ", release=" + release + ", inspection="
				+ inspection + ", fictitious=" + fictitious + ", salesUnit=" + salesUnit + ", secrecy=" + secrecy
				+ ", purchasingUnit=" + purchasingUnit + ", productionTeam=" + productionTeam
				+ ", mininumberOfPackages=" + mininumberOfPackages + ", termOfValidity=" + termOfValidity + ", typenum="
				+ typenum + ", voltage=" + voltage + ", partCounts=" + partCounts + ", cellCapacity=" + cellCapacity
				+ ", scan=" + scan + ", cellSpecification=" + cellSpecification + ", customerPartCode="
				+ customerPartCode + ", materialLength=" + materialLength + ", materialWidth=" + materialWidth
				+ ", materialHight=" + materialHight + ", materialVolume=" + materialVolume + ", materialWeight="
				+ materialWeight + ", materialLt=" + materialLt + ", materialIncomType=" + materialIncomType
				+ ", materialLowLimitmaterial=" + materialLowLimitmaterial + ", materialBatch=" + materialBatch
				+ ", daysOfFailure=" + daysOfFailure + ", custom=" + custom + ", bomCode=" + bomCode + "]";
	}

	public CMesJlMaterialT() {
        super();
    }


}
