package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmAssessRecordH
 * @Description ${Description}
 */

/**
 * 评估档案头表
 */
public class CSrmAssessRecordH {
    /**
     * 评估档案头id
     */
    private Integer id;

    /**
     * 档案编号
     */
    private String fileNumber;

    /**
     * 档案名称
     */
    private String fileName;

    /**
     * 评估起始时间/考评时间从
     */
    private String evaluationStartTime;

    /**
     * 评估截止时间/考评时间至
     */
    private String evaluationCutoffTime;

    /**
     * 状态(1.新建2.评分中3.待发布4.已完成)
     */
    private String status;

    /**
     * 创建时间/建档时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 档案描述
     */
    private String fileDescription;

    /**
     * 考评模板
     */
    private String evaluationTemplate;

    /**
     * 考评公司
     */
    private String companyCode;

    /**
     * 考评周期(1.月度2.季度3.半年度4.年度)
     */
    private String evaluationPeriod;

    /**
     * 考评方式
     */
    private String appraisalWay;

    /**
     * 考评负责人
     */
    private String appraisalLeader;

    /**
     * 考评规则说明
     */
    private String evaluationRuleExplain;

    /**
     * 考评说明
     */
    private String evaluationExplain;

    /**
     * 考评对象
     */
    private String supplierCode;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEvaluationStartTime() {
        return evaluationStartTime;
    }

    public void setEvaluationStartTime(String evaluationStartTime) {
        this.evaluationStartTime = evaluationStartTime;
    }

    public String getEvaluationCutoffTime() {
        return evaluationCutoffTime;
    }

    public void setEvaluationCutoffTime(String evaluationCutoffTime) {
        this.evaluationCutoffTime = evaluationCutoffTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getEvaluationTemplate() {
        return evaluationTemplate;
    }

    public void setEvaluationTemplate(String evaluationTemplate) {
        this.evaluationTemplate = evaluationTemplate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEvaluationPeriod() {
        return evaluationPeriod;
    }

    public void setEvaluationPeriod(String evaluationPeriod) {
        this.evaluationPeriod = evaluationPeriod;
    }

    public String getAppraisalWay() {
        return appraisalWay;
    }

    public void setAppraisalWay(String appraisalWay) {
        this.appraisalWay = appraisalWay;
    }

    public String getAppraisalLeader() {
        return appraisalLeader;
    }

    public void setAppraisalLeader(String appraisalLeader) {
        this.appraisalLeader = appraisalLeader;
    }

    public String getEvaluationRuleExplain() {
        return evaluationRuleExplain;
    }

    public void setEvaluationRuleExplain(String evaluationRuleExplain) {
        this.evaluationRuleExplain = evaluationRuleExplain;
    }

    public String getEvaluationExplain() {
        return evaluationExplain;
    }

    public void setEvaluationExplain(String evaluationExplain) {
        this.evaluationExplain = evaluationExplain;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public String toString() {
        return "CSrmAssessRecordH{" +
                "id=" + id +
                ", fileNumber='" + fileNumber + '\'' +
                ", fileName='" + fileName + '\'' +
                ", evaluationStartTime='" + evaluationStartTime + '\'' +
                ", evaluationCutoffTime='" + evaluationCutoffTime + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", fileDescription='" + fileDescription + '\'' +
                ", evaluationTemplate='" + evaluationTemplate + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", evaluationPeriod='" + evaluationPeriod + '\'' +
                ", appraisalWay='" + appraisalWay + '\'' +
                ", appraisalLeader='" + appraisalLeader + '\'' +
                ", evaluationRuleExplain='" + evaluationRuleExplain + '\'' +
                ", evaluationExplain='" + evaluationExplain + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                '}';
    }
}
