package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/8/10
 * @Classname CSrmFileUploading
 * @Description ${Description}
 */
public class CSrmFileUploading {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 标记(1.采购文件,2供应商文件)
     */
    private Integer flag;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 供应商代码
     */
    private String supplierCode;




    /**
     * 仅采购可看权限( 0.不可见、1.可见)
     */
    private Integer onlyPurShow;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public Integer getOnlyPurShow() {
        return onlyPurShow;
    }
    public void setOnlyPurShow(Integer onlyPurShow) {
        this.onlyPurShow = onlyPurShow;
    }
    @Override
    public String toString() {
        return "CSrmFileUploading{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", flag=" + flag +
                ", orderNumber='" + orderNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", supplierCode='" + supplierCode + '\'' +
                ", onlyPurShow=" + onlyPurShow +
                '}';
    }



}
