package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessRecordH
 * @Description ${Description}
 */

/**
 * 已收评估结果查询出参
 */
public class CSrmAssessRecordHRsp {

    /**
     * 档案编号
     */
    private String fileNumber;

    /**
     * 档案名称
     */
    private String fileName;

    /**
     * 评估起始时间
     */
    private String evaluationStartTime;

    /**
     * 评估截止时间
     */
    private String evaluationCutoffTime;

    /**
     * 指标
     */
    private String index;

    /**
     * 指标值
     */
    private String indexValue;


    /**
     * 状态(1.新建2.评分中3.待发布4.已完成)
     */
    private String status;




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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CSrmAssessRecordHRsp{" +
                "fileNumber='" + fileNumber + '\'' +
                ", fileName='" + fileName + '\'' +
                ", evaluationStartTime='" + evaluationStartTime + '\'' +
                ", evaluationCutoffTime='" + evaluationCutoffTime + '\'' +
                ", index='" + index + '\'' +
                ", indexValue='" + indexValue + '\'' +
                ", status='" + status + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", evaluationTemplate='" + evaluationTemplate + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", evaluationPeriod='" + evaluationPeriod + '\'' +
                ", appraisalWay='" + appraisalWay + '\'' +
                ", appraisalLeader='" + appraisalLeader + '\'' +
                ", evaluationRuleExplain='" + evaluationRuleExplain + '\'' +
                ", evaluationExplain='" + evaluationExplain + '\'' +
                '}';
    }
}
