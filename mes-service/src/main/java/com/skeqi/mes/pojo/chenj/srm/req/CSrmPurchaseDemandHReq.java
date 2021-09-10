package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmPurchaseDemandRReq;


import java.util.List;

/**
 * 采购需求头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmPurchaseDemandHReq {

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 状态(1.新建2.审批中3.待分配4.已分配5.已删除)
     */
    private String status;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 申请日期
     */
    private String applicationDate;

    /**
     * 项目号
     */
    private String projectCode;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 申请部门
     */
    private String applyForDepartment;

    /**
     * 申请部门名称
     */
    private String applyForDepartmentName;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 采购员名称
     */
    private String buyerName;


    /**
     * 制单人
     */
    private String preparedBy;

    /**
     * 采购行数据
     */
    private List<CSrmPurchaseDemandRReq> purList;


    /**
     * 操作标识(1.创建 2.修改 2.状态变更)
     */
    private String operationSign;


    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;


    /**
     * 业务类型
     */
    private String businessType;


    /**
     * 申请单号集合
     */
    private List<String> requestCodeList;



    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public List<String> getRequestCodeList() {
        return requestCodeList;
    }

    public void setRequestCodeList(List<String> requestCodeList) {
        this.requestCodeList = requestCodeList;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposer() {
        return proposer;
    }

    public List<CSrmPurchaseDemandRReq> getPurList() {
        return purList;
    }

    public void setPurList(List<CSrmPurchaseDemandRReq> purList) {
        this.purList = purList;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getBusinessType() {

        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getApplyForDepartment() {
        return applyForDepartment;
    }

    public void setApplyForDepartment(String applyForDepartment) {
        this.applyForDepartment = applyForDepartment;
    }

    public String getBuyer() {
        return buyer;
    }

	public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public String getApplyForDepartmentName() {
		return applyForDepartmentName;
	}

	public void setApplyForDepartmentName(String applyForDepartmentName) {
		this.applyForDepartmentName = applyForDepartmentName;
	}

	@Override
	public String toString() {
		return "CSrmPurchaseDemandHReq{" +
			"requestCode='" + requestCode + '\'' +
			", status='" + status + '\'' +
			", proposer='" + proposer + '\'' +
			", applicationDate='" + applicationDate + '\'' +
			", projectCode='" + projectCode + '\'' +
			", projectName='" + projectName + '\'' +
			", applyForDepartment='" + applyForDepartment + '\'' +
			", applyForDepartmentName='" + applyForDepartmentName + '\'' +
			", buyer='" + buyer + '\'' +
			", buyerName='" + buyerName + '\'' +
			", preparedBy='" + preparedBy + '\'' +
			", purList=" + purList +
			", operationSign='" + operationSign + '\'' +
			", pageNum=" + pageNum +
			", pageSize=" + pageSize +
			", businessType='" + businessType + '\'' +
			", requestCodeList=" + requestCodeList +
			'}';
	}
}
