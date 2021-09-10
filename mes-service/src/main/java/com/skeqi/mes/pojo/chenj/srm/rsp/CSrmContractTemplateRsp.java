package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/7/12
 * @Classname CSrmContractTemplate
 * @Description ${Description}
 */



/**
 * 合同模板记录表 出参
 */
public class CSrmContractTemplateRsp {

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
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "CSrmContractTemplateRsp{" +
                "id=" + id +
                ", templateNumber='" + templateNumber + '\'' +
                ", templateName='" + templateName + '\'' +
                ", adapterCompany='" + adapterCompany + '\'' +
                ", formWorkAccessory='" + formWorkAccessory + '\'' +
                ", isStart='" + isStart + '\'' +
                ", templateType='" + templateType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
