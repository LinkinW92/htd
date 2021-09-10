package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/12
 * @Classname CSrmContractTemplate
 * @Description ${Description}
 */

/**
 * 合同模板记录表
 */
public class CSrmContractTemplate {
    /**
     * 合同模板记录id
     */
    private Integer id;

    /**
     * 合同模板编号
     */
    private String templateNumber;

    /**
     * 合同模板名称
     */
    private String templateName;

    /**
     * 分配适用公司
     */
    private String adapterCompany;

    /**
     * 模板文件
     */
    private String formWorkAccessory;

    /**
     * 是否启用(1.禁用2.启用)
     */
    private String isStart;

    /**
     * 合同类型(1.普通合同)
     */
    private String templateType;

    /**
     * 创建时间
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAdapterCompany() {
        return adapterCompany;
    }

    public void setAdapterCompany(String adapterCompany) {
        this.adapterCompany = adapterCompany;
    }

    public String getFormWorkAccessory() {
        return formWorkAccessory;
    }

    public void setFormWorkAccessory(String formWorkAccessory) {
        this.formWorkAccessory = formWorkAccessory;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateNumber=").append(templateNumber);
        sb.append(", templateName=").append(templateName);
        sb.append(", adapterCompany=").append(adapterCompany);
        sb.append(", formWorkAccessory=").append(formWorkAccessory);
        sb.append(", isStart=").append(isStart);
        sb.append(", templateType=").append(templateType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append("]");
        return sb.toString();
    }
}
