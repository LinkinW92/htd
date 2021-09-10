package com.skeqi.mes.pojo.chenj.srm.req;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
* @author ChenJ
* @date 2021/8/10
* @Classname CSrmFileUploading
* @Description ${Description}
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmFileUploadingReq {
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
     * 文件删除集合
     */
    private List<Integer> delList;

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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setLineNumber(String lineNumber) {
       this.lineNumber = lineNumber;
   }

    public List<Integer> getDelList() {
        return delList;
    }

    public void setDelList(List<Integer> delList) {
        this.delList = delList;
    }

    public Boolean getIsDelete() {
       return isDelete;
   }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setIsDelete(Boolean isDelete) {
       this.isDelete = isDelete;
   }

    public Integer getOnlyPurShow() {
        return onlyPurShow;
    }

    public void setOnlyPurShow(Integer onlyPurShow) {
        this.onlyPurShow = onlyPurShow;
    }

    @Override
    public String toString() {
        return "CSrmFileUploadingReq{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", flag=" + flag +
                ", orderNumber='" + orderNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", isDelete=" + isDelete +
                ", delList=" + delList +
                ", supplierCode='" + supplierCode + '\'' +
                ", onlyPurShow=" + onlyPurShow +
                '}';
    }
}
