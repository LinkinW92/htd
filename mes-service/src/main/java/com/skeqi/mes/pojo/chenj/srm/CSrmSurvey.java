package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmSurvey
 * @Description ${Description}
 */

/**
 * 调查表记录主表
 */
public class CSrmSurvey {
    /**
     * 调查表记录id
     */
    private Integer id;

    /**
     * 调查表单号
     */
    private String surveyFormNumber;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 状态(1.待发布2.已拒绝3.已完成)
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", surveyFormNumber=").append(surveyFormNumber);
        sb.append(", companyName=").append(companyName);
        sb.append(", creator=").append(creator);
        sb.append(", templateCode=").append(templateCode);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
