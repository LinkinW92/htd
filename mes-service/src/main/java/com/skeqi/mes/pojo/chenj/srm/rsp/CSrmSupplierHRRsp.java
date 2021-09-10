package com.skeqi.mes.pojo.chenj.srm.rsp;



/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierH
 * @Description ${Description}
 */

import com.skeqi.mes.pojo.chenj.srm.CSrmSupplierR;
import com.skeqi.mes.util.PageInfoUtil;

/**
 * 供应商升降级申请头表
 */
public class CSrmSupplierHRRsp {

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 当前阶段
     */
    private String currentGeneration;

    /**
     * 目标阶段
     */
    private String targetPhase;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 状态(0,申请中1,申请成功,2申请失败)
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 黑名单标记(0.黑名单1.正常)
     */
    private String blacklistMark;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 总得分
     */
    private String sumScore;


    /**
     * 等级
     */
    private String grade;


    /**
     * 供应商名称
     */
    private String name;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 申请单状态(1.已创建2.申请中3.申请成功4.申请失败)
     */
    private String requestStatus;


    /**
     * 档案行数据
     */
    private PageInfoUtil<CSrmSupplierR> rspList;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public PageInfoUtil<CSrmSupplierR> getRspList() {
        return rspList;
    }

    public void setRspList(PageInfoUtil<CSrmSupplierR> rspList) {
        this.rspList = rspList;
    }

    @Override
    public String toString() {
        return "CSrmSupplierHRRsp{" +
                "requestCode='" + requestCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", currentGeneration='" + currentGeneration + '\'' +
                ", targetPhase='" + targetPhase + '\'' +
                ", creator='" + creator + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", blacklistMark='" + blacklistMark + '\'' +
                ", attachment='" + attachment + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", sumScore='" + sumScore + '\'' +
                ", grade='" + grade + '\'' +
                ", name='" + name + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                ", rspList=" + rspList +
                '}';
    }
}
