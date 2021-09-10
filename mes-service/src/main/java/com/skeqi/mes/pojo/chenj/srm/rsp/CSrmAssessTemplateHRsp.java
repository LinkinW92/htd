package com.skeqi.mes.pojo.chenj.srm.rsp;

 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateH
 * @Description ${Description}
 */

/**
 * 评估模板出参
 */
public class CSrmAssessTemplateHRsp {


    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

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
     * 适配供应商代码
     */
    private String adaptTheVendorCode;



    /**
     * 指标编码
     */
    private String indexCode;


    /**
     * 版本号
     */
    private String version;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *  修改标记
     */
    private String updateSign;



    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    /**
     * 状态
     */
    private String status;
    /**
     * 权重式计算
     */
    private String weightCalculation;
    /**
     * 优先级
     */
    private String priority;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWeightCalculation() {
        return weightCalculation;
    }

    public void setWeightCalculation(String weightCalculation) {
        this.weightCalculation = weightCalculation;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

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

    public String getUpdateSign() {
        return updateSign;
    }

    public void setUpdateSign(String updateSign) {
        this.updateSign = updateSign;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
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

    public String getAdaptTheVendorCode() {
        return adaptTheVendorCode;
    }

    public void setAdaptTheVendorCode(String adaptTheVendorCode) {
        this.adaptTheVendorCode = adaptTheVendorCode;
    }


    @Override
    public String toString() {
        return "CSrmAssessTemplateHRsp{" +
                "templateCode='" + templateCode + '\'' +
                ", templateName='" + templateName + '\'' +
                ", type='" + type + '\'' +
                ", adaptTheVendorCode='" + adaptTheVendorCode + '\'' +
                ", indexCode='" + indexCode + '\'' +
                ", version='" + version + '\'' +
                ", updateSign='" + updateSign + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", status='" + status + '\'' +
                ", weightCalculation='" + weightCalculation + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
