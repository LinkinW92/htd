package com.skeqi.mes.pojo.chenj.srm.rsp;


 /**
 * @author ChenJ
 * @date 2021/7/20
 * @Classname CSrmAssessRecordR
 * @Description ${Description}
 */

/**
 * 评估档案行表
 */
public class CSrmAssessRecordRRsp {
    /**
     * 评估档案行id
     */
    private Integer id;

    /**
     * 行号
     */
    private String lineNumber;

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
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

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

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public void setUpdateTime(String updateTime) {
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

    @Override
    public String toString() {
        return "CSrmAssessRecordRReq{" +
                "id=" + id +
                ", lineNumber='" + lineNumber + '\'' +
                ", fileNumber='" + fileNumber + '\'' +
                ", index='" + index + '\'' +
                ", indexValue='" + indexValue + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", assessId=" + assessId +
                ", isDelete=" + isDelete +
                ", scoringItems='" + scoringItems + '\'' +
                ", score='" + score + '\'' +
                ", gradingStaff='" + gradingStaff + '\'' +
                '}';
    }
}
