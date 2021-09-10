package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSurvey
 * @Description ${Description}
 */

/**
 * 调查表查询及答复出参
 */
public class CSrmSurveyRsp {


    /**
     * 调查表单号
     */
    private String surveyFormNumber;


    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private String createTime;

    public String getSurveyFormNumber() {
        return surveyFormNumber;
    }

    public void setSurveyFormNumber(String surveyFormNumber) {
        this.surveyFormNumber = surveyFormNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    @Override
    public String toString() {
        return "CSrmSurveyRsp{" +
                "surveyFormNumber='" + surveyFormNumber + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
