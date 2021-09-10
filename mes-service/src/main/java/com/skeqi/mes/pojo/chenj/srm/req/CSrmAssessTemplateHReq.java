package com.skeqi.mes.pojo.chenj.srm.req;

 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 评估模板头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmAssessTemplateHReq {


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
        return "CSrmAssessTemplateHReq{" +
                "templateCode='" + templateCode + '\'' +
                ", templateName='" + templateName + '\'' +
                ", type='" + type + '\'' +
                ", adaptTheVendorCode='" + adaptTheVendorCode + '\'' +
                ", indexCode='" + indexCode + '\'' +
                ", version='" + version + '\'' +
                ", updateSign='" + updateSign + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
