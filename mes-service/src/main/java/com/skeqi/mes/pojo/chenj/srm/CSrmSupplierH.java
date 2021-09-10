package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplierH
 * @Description ${Description}
 */

/**
 * 供应商升降级申请头表
 */
public class CSrmSupplierH {
    private Integer id;

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
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 总得分
     */
    private String sumScore;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;


    /**
     * 等级
     */
    private String grade;

    /**
     * 申请状态(1.已创建2.申请中3.申请成功4.申请失败)
     */
    private String requestStatus;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmSupplierH{" +
                "id=" + id +
                ", requestCode='" + requestCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", currentGeneration='" + currentGeneration + '\'' +
                ", targetPhase='" + targetPhase + '\'' +
                ", creator='" + creator + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", blacklistMark='" + blacklistMark + '\'' +
                ", attachment='" + attachment + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sumScore='" + sumScore + '\'' +
                ", isDelete=" + isDelete +
                ", grade='" + grade + '\'' +
                ", requestStatus='" + requestStatus + '\'' +
                '}';
    }
}
