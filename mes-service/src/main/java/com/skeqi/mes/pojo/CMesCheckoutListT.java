package com.skeqi.mes.pojo;

import java.io.Serializable;

/**
 * 入库检验项
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2019年10月30日 下午4:02:12
 */
public class CMesCheckoutListT implements Serializable {

	private Integer id;
	private Integer orderNumber;
	private String projectName;
	private String quailty;
	private String methodId;
	private String methodName;
	private String record;
	private Integer status;
	private String productionId;
	private String productionName;
	private String productionCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getQuailty() {
		return quailty;
	}
	public void setQuailty(String quailty) {
		this.quailty = quailty;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getProductionId() {
		return productionId;
	}
	public void setProductionId(String productionId) {
		this.productionId = productionId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getProductionCode() {
		return productionCode;
	}
	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}
	public String getMethodId() {
		return methodId;
	}
	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public CMesCheckoutListT(Integer id, Integer orderNumber, String projectName, String quailty, String methodId,
			String methodName, String record, Integer status, String productionId, String productionName,
			String productionCode) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.projectName = projectName;
		this.quailty = quailty;
		this.methodId = methodId;
		this.methodName = methodName;
		this.record = record;
		this.status = status;
		this.productionId = productionId;
		this.productionName = productionName;
		this.productionCode = productionCode;
	}
	public CMesCheckoutListT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesCheckoutListT [id=" + id + ", orderNumber=" + orderNumber + ", projectName=" + projectName
				+ ", quailty=" + quailty + ", methodId=" + methodId + ", methodName=" + methodName + ", record="
				+ record + ", status=" + status + ", productionId=" + productionId + ", productionName="
				+ productionName + ", productionCode=" + productionCode + "]";
	}

}
