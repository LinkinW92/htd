package com.skeqi.mes.pojo.wms;

/**
 * 入库
 * @author yinp
 *
 */
public class K3ImportNotifydetail {

	Integer id;
	//单据编号 入库单ID
	String importId;
	//物料长代码
	String importGoodsCode;
	//入库数量 内箱
	Integer importPackQuantity;
	//仓库ID
	Integer importWarehouseId;
	//备注
	String importRemark;
	//物料名称
	String importMaterialName;
	//项目号 物料所属项目
	String importProjectNo;
	//已入库数量
	Integer receivedNumber;
	//是否全部入库 0否 1是
	Integer result;
	Integer projectId;
	Integer materialId;



	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getReceivedNumber() {
		return receivedNumber;
	}
	public void setReceivedNumber(Integer receivedNumber) {
		this.receivedNumber = receivedNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImportId() {
		return importId;
	}
	public void setImportId(String importId) {
		this.importId = importId;
	}
	public String getImportGoodsCode() {
		return importGoodsCode;
	}
	public void setImportGoodsCode(String importGoodsCode) {
		this.importGoodsCode = importGoodsCode;
	}
	public Integer getImportPackQuantity() {
		return importPackQuantity;
	}
	public void setImportPackQuantity(Integer importPackQuantity) {
		this.importPackQuantity = importPackQuantity;
	}
	public Integer getImportWarehouseId() {
		return importWarehouseId;
	}
	public void setImportWarehouseId(Integer importWarehouseId) {
		this.importWarehouseId = importWarehouseId;
	}
	public String getImportRemark() {
		return importRemark;
	}
	public void setImportRemark(String importRemark) {
		this.importRemark = importRemark;
	}
	public String getImportMaterialName() {
		return importMaterialName;
	}
	public void setImportMaterialName(String importMaterialName) {
		this.importMaterialName = importMaterialName;
	}
	public String getImportProjectNo() {
		return importProjectNo;
	}
	public void setImportProjectNo(String importProjectNo) {
		this.importProjectNo = importProjectNo;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "K3ImportNotifydetail [id=" + id + ", importId=" + importId + ", importGoodsCode=" + importGoodsCode
				+ ", importPackQuantity=" + importPackQuantity + ", importWarehouseId=" + importWarehouseId
				+ ", importRemark=" + importRemark + ", importMaterialName=" + importMaterialName + ", importProjectNo="
				+ importProjectNo + ", receivedNumber=" + receivedNumber + ", result=" + result + "]";
	}

}
