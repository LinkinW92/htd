package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessRecordH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 评估档案头表
 */
public class CSrmAssessRecordHRRsp {


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
    @JsonProperty("evaluationCutoffime")
    private String evaluationCutoffTime;

    /**
     * 指标
     */
    private String index;

    /**
     * 指标值
     */
    private String indexValue;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 状态(1.新建2.评分中3.待发布4.已完成)
     */
    private String status;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 建档时间
     */
    private String createTime;

    /**
     * 操作标记(1.新增2.修改3.变更状态)
     */
    private String operationSign;

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;



    /**
     * 档案描述
     */
    private String fileDescription;

    /**
     * 考评模板
     */
    private String evaluationTemplate;

    /**
     * 考评公司编码
     */
    private String companyCode;

    /**
     * 考评公司
     */
    private String companyName;
    /**
     * 考评对象(供应商名称)
     */
    private String name;


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
     * 档案行数据
     */
    private List<CSrmAssessRecordRRsp> reqList;


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

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public List<CSrmAssessRecordRRsp> getReqList() {
        return reqList;
    }

    public void setReqList(List<CSrmAssessRecordRRsp> reqList) {
        this.reqList = reqList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

    @Override
    public String toString() {
        return "CSrmAssessRecordHRRsp{" +
                "fileNumber='" + fileNumber + '\'' +
                ", fileName='" + fileName + '\'' +
                ", evaluationStartTime='" + evaluationStartTime + '\'' +
                ", evaluationCutoffTime='" + evaluationCutoffTime + '\'' +
                ", index='" + index + '\'' +
                ", indexValue='" + indexValue + '\'' +
                ", status='" + status + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", createTime='" + createTime + '\'' +
                ", operationSign='" + operationSign + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", fileDescription='" + fileDescription + '\'' +
                ", evaluationTemplate='" + evaluationTemplate + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", name='" + name + '\'' +
                ", evaluationPeriod='" + evaluationPeriod + '\'' +
                ", appraisalWay='" + appraisalWay + '\'' +
                ", appraisalLeader='" + appraisalLeader + '\'' +
                ", evaluationRuleExplain='" + evaluationRuleExplain + '\'' +
                ", evaluationExplain='" + evaluationExplain + '\'' +
                ", reqList=" + reqList +
                '}';
    }
}
