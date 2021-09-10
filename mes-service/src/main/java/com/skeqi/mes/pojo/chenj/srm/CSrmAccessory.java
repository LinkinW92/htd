package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmAccessory
 * @Description ${Description}
 */

/**
 * 附件信息表
 */
public class CSrmAccessory {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 附件描述
     */
    private String theAttachmentDescribe;

    /**
     * 附件上传
     */
    private String attachmentUploading;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 公司编码
     */
    private String companyCode;



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

    public String getTheAttachmentDescribe() {
        return theAttachmentDescribe;
    }

    public void setTheAttachmentDescribe(String theAttachmentDescribe) {
        this.theAttachmentDescribe = theAttachmentDescribe;
    }

    public String getAttachmentUploading() {
        return attachmentUploading;
    }

    public void setAttachmentUploading(String attachmentUploading) {
        this.attachmentUploading = attachmentUploading;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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
        return "CSrmAccessory{" +
                "id=" + id +
                ", theAttachmentDescribe='" + theAttachmentDescribe + '\'' +
                ", attachmentUploading='" + attachmentUploading + '\'' +
                ", isDelete=" + isDelete +
                ", supplierCode='" + supplierCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
