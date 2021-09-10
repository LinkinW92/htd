package com.skeqi.mes.pojo.wms;

/**
 * 出库
 *
 * @author yinp
 *
 */
public class K3ExportNotifydetall {

	Integer id;
	// 单据编号 出库单ID
	String exportId;
	//物料长代码
	String bomId;
	// 订单号
	String exportLotNo;
	// 出库数量
	Integer exportPackQuantity;
	// 仓库ID
	Integer exportWaerhouseId;
	// 物料id
	Integer materialId;
	// 物料名称
	String materialName;
	// 物料规格型号
	String materialGoodsModel;
	// 项目id
	Integer projectId;
	// 项目名称
	String projectName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExportId() {
		return exportId;
	}
	public void setExportId(String exportId) {
		this.exportId = exportId;
	}
	public String getBomId() {
		return bomId;
	}
	public void setBomId(String bomId) {
		this.bomId = bomId;
	}
	public String getExportLotNo() {
		return exportLotNo;
	}
	public void setExportLotNo(String exportLotNo) {
		this.exportLotNo = exportLotNo;
	}
	public Integer getExportPackQuantity() {
		return exportPackQuantity;
	}
	public void setExportPackQuantity(Integer exportPackQuantity) {
		this.exportPackQuantity = exportPackQuantity;
	}
	public Integer getExportWaerhouseId() {
		return exportWaerhouseId;
	}
	public void setExportWaerhouseId(Integer exportWaerhouseId) {
		this.exportWaerhouseId = exportWaerhouseId;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialGoodsModel() {
		return materialGoodsModel;
	}
	public void setMaterialGoodsModel(String materialGoodsModel) {
		this.materialGoodsModel = materialGoodsModel;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public String toString() {
		return "K3ExportNotifydetall [id=" + id + ", exportId=" + exportId + ", bomId=" + bomId + ", exportLotNo="
				+ exportLotNo + ", exportPackQuantity=" + exportPackQuantity + ", exportWaerhouseId="
				+ exportWaerhouseId + ", materialId=" + materialId + ", materialName=" + materialName
				+ ", materialGoodsModel=" + materialGoodsModel + ", projectId=" + projectId + ", projectName="
				+ projectName + "]";
	}

}
