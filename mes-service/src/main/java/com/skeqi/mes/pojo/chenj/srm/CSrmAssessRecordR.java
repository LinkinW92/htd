package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmAssessRecordR
 * @Description ${Description}
 */

/**
 * 评估档案行表
 */
public class CSrmAssessRecordR {
    /**
     * 评估档案行id
     */
    private Integer id;

    /**
     * 档案编号
     */
    private String fileNumber;

    /**
     * 指标
     */
    private String index;

    /**
     * 指标值
     */
    private String indexValue;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 评估档案头id
     */
    private Integer assessId;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 评分项
     */
    private String scoringItems;

    /**
     * 分值
     */
    private String score;

    /**
     * 评分人员
     */
    private String gradingStaff;

    /**
     * 行号
     */
    private String lineNumber;

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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public Integer getAssessId() {
        return assessId;
    }

    public void setAssessId(Integer assessId) {
        this.assessId = assessId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getScoringItems() {
        return scoringItems;
    }

    public void setScoringItems(String scoringItems) {
        this.scoringItems = scoringItems;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGradingStaff() {
        return gradingStaff;
    }

    public void setGradingStaff(String gradingStaff) {
        this.gradingStaff = gradingStaff;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileNumber=").append(fileNumber);
        sb.append(", index=").append(index);
        sb.append(", indexValue=").append(indexValue);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", assessId=").append(assessId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", scoringItems=").append(scoringItems);
        sb.append(", score=").append(score);
        sb.append(", gradingStaff=").append(gradingStaff);
        sb.append(", lineNumber=").append(lineNumber);
        sb.append("]");
        return sb.toString();
    }
}
