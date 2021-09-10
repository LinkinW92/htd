package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 供应商升降级申请头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierHReq {

    /**
     * 申请单编号
     */
    private String requestCode;
    /**
     * 供应商代码
     */
    private String supplierCode;


    /**
     * 申请状态
     */
    private String requestStatus;

    /**
     * 企业名称/公司名称
     */
    private String companyName;

    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证)
     */
    private String status;
    /**
     * 所处阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String inPhase;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系方式
     */
    private String contactNumber;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 供应商名称
     */
    private String name;


    /**
     * 当前阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String currentGeneration;

    /**
     * 目标阶段(1.注册、2.潜在、3.合格、4.淘汰)
     */
    private String targetPhase;

    /**
     * 创建人
     */
    private String creator;


    /**
     * 备注/说明
     */
    private String remark;

    /**
     * 黑名单标记(0.黑名单,1正常)
     */
    private String blacklistMark;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 指标值
     */
    private String indexValue;

    /**
     * 评分人员
     */
    private String gradingStaff;

    /**
     * 总得分
     */
    private String sumScore;


    /**
     * 等级
     */
    private String grade;

    /**
     * 升降级申请行数据
     */
    private List<CSrmSupplierRReq> reqList;


    /**
     * 操作标识
     * @return
     */
    private String operationSign;

    /**
     * 服务类型(1.K3(10000))
     */
    private Integer serviceType;


	/**
	 * 是否推送服务开关(true：推送，false：不推送)
	 */
	private Boolean push;


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(String currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public String getTargetPhase() {
        return targetPhase;
    }

    public void setTargetPhase(String targetPhase) {
        this.targetPhase = targetPhase;
    }

    public String getCreator() {
        return creator;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInPhase() {
        return inPhase;
    }

    public void setInPhase(String inPhase) {
        this.inPhase = inPhase;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public List<CSrmSupplierRReq> getReqList() {
        return reqList;
    }

    public void setReqList(List<CSrmSupplierRReq> reqList) {
        this.reqList = reqList;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getBlacklistMark() {
        return blacklistMark;
    }

    public void setBlacklistMark(String blacklistMark) {
        this.blacklistMark = blacklistMark;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getSumScore() {
        return sumScore;
    }

    public void setSumScore(String sumScore) {
        this.sumScore = sumScore;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getGradingStaff() {
        return gradingStaff;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public void setGradingStaff(String gradingStaff) {
        this.gradingStaff = gradingStaff;
    }

	public Boolean getPush() {
		return push;
	}

	public void setPush(Boolean push) {
		this.push = push;
	}

	@Override
	public String toString() {
		return "CSrmSupplierHReq{" +
			"requestCode='" + requestCode + '\'' +
			", supplierCode='" + supplierCode + '\'' +
			", requestStatus='" + requestStatus + '\'' +
			", companyName='" + companyName + '\'' +
			", status='" + status + '\'' +
			", inPhase='" + inPhase + '\'' +
			", contactPerson='" + contactPerson + '\'' +
			", contactNumber='" + contactNumber + '\'' +
			", contactEmail='" + contactEmail + '\'' +
			", companyCode='" + companyCode + '\'' +
			", createTime='" + createTime + '\'' +
			", name='" + name + '\'' +
			", currentGeneration='" + currentGeneration + '\'' +
			", targetPhase='" + targetPhase + '\'' +
			", creator='" + creator + '\'' +
			", remark='" + remark + '\'' +
			", blacklistMark='" + blacklistMark + '\'' +
			", attachment='" + attachment + '\'' +
			", indexValue='" + indexValue + '\'' +
			", gradingStaff='" + gradingStaff + '\'' +
			", sumScore='" + sumScore + '\'' +
			", grade='" + grade + '\'' +
			", reqList=" + reqList +
			", operationSign='" + operationSign + '\'' +
			", serviceType=" + serviceType +
			", push=" + push +
			'}';
	}
}
