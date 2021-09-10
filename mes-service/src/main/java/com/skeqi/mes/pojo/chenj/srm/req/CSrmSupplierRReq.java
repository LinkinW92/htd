package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierR
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 供应商升降级申请行表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierRReq {


    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 行号
     */
    private String lineNumber;
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

    /**
     * 评分人员/评分人信息
     */
    private String gradingStaff;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
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

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getGradingStaff() {
        return gradingStaff;
    }

    public void setGradingStaff(String gradingStaff) {
        this.gradingStaff = gradingStaff;
    }

    @Override
    public String toString() {
        return "CSrmSupplierRReq{" +
                "requestCode='" + requestCode + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", evaluationItemNo='" + evaluationItemNo + '\'' +
                ", evaluationItem='" + evaluationItem + '\'' +
                ", evaluationCriterion='" + evaluationCriterion + '\'' +
                ", scoreIs='" + scoreIs + '\'' +
                ", scoreStart='" + scoreStart + '\'' +
                ", scoreStop='" + scoreStop + '\'' +
                ", score='" + score + '\'' +
                ", weight='" + weight + '\'' +
                ", gradingStaff='" + gradingStaff + '\'' +
                '}';
    }
}
