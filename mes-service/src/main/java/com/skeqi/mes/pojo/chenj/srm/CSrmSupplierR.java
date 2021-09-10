package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmSupplierR
 * @Description ${Description}
 */

/**
 * 供应商升降级申请行表
 */
public class CSrmSupplierR {
    /**
     * 供应商升降级申请行表id
     */
    private Integer id;

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 评分指标
     */
    private String gradingIndex;

    /**
     * 指标值
     */
    private String indexValue;

    /**
     * 评分人员
     */
    private String gradingStaff;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 供应商升降级申请头表id
     */
    private Integer requestCodeId;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 评价项目编号
     */
    private String evaluationItemNo;

    /**
     * 评价项目
     */
    private String evaluationItem;

    /**
     * 评价标准
     */
    private String evaluationCriterion;

    /**
     * 评分方式(1.手工评分2.系统评分)
     */
    private String scoreIs;

    /**
     * 分值从
     */
    private String scoreStart;

    /**
     * 分值至
     */
    private String scoreStop;

    /**
     * 得分
     */
    private String score;

    /**
     * 权重(%)
     */
    private String weight;

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

    public String getGradingIndex() {
        return gradingIndex;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setGradingIndex(String gradingIndex) {
        this.gradingIndex = gradingIndex;
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

    public void setGradingStaff(String gradingStaff) {
        this.gradingStaff = gradingStaff;
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

    public Integer getRequestCodeId() {
        return requestCodeId;
    }

    public void setRequestCodeId(Integer requestCodeId) {
        this.requestCodeId = requestCodeId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getEvaluationItemNo() {
        return evaluationItemNo;
    }

    public void setEvaluationItemNo(String evaluationItemNo) {
        this.evaluationItemNo = evaluationItemNo;
    }

    public String getEvaluationItem() {
        return evaluationItem;
    }

    public void setEvaluationItem(String evaluationItem) {
        this.evaluationItem = evaluationItem;
    }

    public String getEvaluationCriterion() {
        return evaluationCriterion;
    }

    public void setEvaluationCriterion(String evaluationCriterion) {
        this.evaluationCriterion = evaluationCriterion;
    }

    public String getScoreIs() {
        return scoreIs;
    }

    public void setScoreIs(String scoreIs) {
        this.scoreIs = scoreIs;
    }

    public String getScoreStart() {
        return scoreStart;
    }

    public void setScoreStart(String scoreStart) {
        this.scoreStart = scoreStart;
    }

    public String getScoreStop() {
        return scoreStop;
    }

    public void setScoreStop(String scoreStop) {
        this.scoreStop = scoreStop;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CSrmSupplierR{" +
                "id=" + id +
                ", lineNumber='" + lineNumber + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", gradingIndex='" + gradingIndex + '\'' +
                ", indexValue='" + indexValue + '\'' +
                ", gradingStaff='" + gradingStaff + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", requestCodeId=" + requestCodeId +
                ", isDelete=" + isDelete +
                ", evaluationItemNo='" + evaluationItemNo + '\'' +
                ", evaluationItem='" + evaluationItem + '\'' +
                ", evaluationCriterion='" + evaluationCriterion + '\'' +
                ", scoreIs='" + scoreIs + '\'' +
                ", scoreStart='" + scoreStart + '\'' +
                ", scoreStop='" + scoreStop + '\'' +
                ", score='" + score + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
