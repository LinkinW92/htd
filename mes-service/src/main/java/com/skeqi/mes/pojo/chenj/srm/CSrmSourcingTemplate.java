package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmSourcingTemplate
 * @Description ${Description}
 */

/**
 * 寻源模板表
 */
public class CSrmSourcingTemplate {
    /**
     * 寻源模板id
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
     * 类型(1.询报价2.招投标)
     */
    private String type;

    /**
     * 状态(1.已禁用2.已启用)
     */
    private String status;

    /**
     * 版本
     */
    private String version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


    /**
     *  特殊操作标记
     */
    private String operationSign;

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
    }

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
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
