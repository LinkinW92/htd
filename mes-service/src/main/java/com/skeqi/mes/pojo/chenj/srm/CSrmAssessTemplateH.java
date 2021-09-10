package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateH
 * @Description ${Description}
 */

/**
 * 评估模板头表
 */
public class CSrmAssessTemplateH {
    /**
     * 评估模板头id
     */
    private Integer id;

    /**
     * 模板编号
     */
    private String templateCode;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 类型(1.供应商考核2.供应商升级评审)
     */
    private String type;

    /**
     * 状态(1.新建2.禁用3.启用)
     */
    private String status;

    /**
     * 版本
     */
    private String version;

    /**
     * 适配供应商代码
     */
    private String adaptTheVendorCode;

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

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAdaptTheVendorCode() {
        return adaptTheVendorCode;
    }

    public void setAdaptTheVendorCode(String adaptTheVendorCode) {
        this.adaptTheVendorCode = adaptTheVendorCode;
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
        sb.append(", templateCode=").append(templateCode);
        sb.append(", templateName=").append(templateName);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", version=").append(version);
        sb.append(", adaptTheVendorCode=").append(adaptTheVendorCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
