package com.skeqi.mes.pojo.chenj.srm.req;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ChenJ
 * @date 2021/7/12
 * @Classname CSrmContractTemplate
 * @Description ${Description}
/

/**
 * 合同模板记录表 入参
 */
 @JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmContractTemplateReq {

    /**
     * 主键id
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
     * 分配适用公司--String类型数组
     */
    private String adapterCompany;


    /**
     * 分配适用公司String
     */
    private String adapterCompanyString;



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
      * 当前页码
      * @return
      */
     private Integer pageNum;

     /**
      * 条数
      * @return
      */
     private Integer pageSize;






     public String getTemplateNumber() {
        return templateNumber;
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

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getAdapterCompanyString() {
        return adapterCompanyString;
    }

    public void setAdapterCompanyString(String adapterCompanyString) {
        this.adapterCompanyString = adapterCompanyString;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

     @Override
     public String toString() {
         return "CSrmContractTemplateReq{" +
                 "id=" + id +
                 ", templateNumber='" + templateNumber + '\'' +
                 ", templateName='" + templateName + '\'' +
                 ", adapterCompany='" + adapterCompany + '\'' +
                 ", adapterCompanyString='" + adapterCompanyString + '\'' +
                 ", formWorkAccessory='" + formWorkAccessory + '\'' +
                 ", isStart='" + isStart + '\'' +
                 ", templateType='" + templateType + '\'' +
                 ", pageNum=" + pageNum +
                 ", pageSize=" + pageSize +
                 '}';
     }


 }
