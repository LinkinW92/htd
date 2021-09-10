package com.skeqi.mes.pojo.chenj.srm.rsp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
* @author ChenJ
* @date 2021/8/10
* @Classname CSrmFileUploading
* @Description ${Description}
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmFileUploadingRsp {
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
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


    /**
     * 仅采购可看权限( 0.不可见、1.可见)
     */
    private Integer onlyPurShow;

    /**
     * 统计字段
     */
    private Integer count;



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

    public String getOrderNumber() {
       return orderNumber;
   }

   public void setOrderNumber(String orderNumber) {
       this.orderNumber = orderNumber;
   }

   public String getLineNumber() {
       return lineNumber;
   }

    public Integer getOnlyPurShow() {
        return onlyPurShow;
    }

    public void setOnlyPurShow(Integer onlyPurShow) {
        this.onlyPurShow = onlyPurShow;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setLineNumber(String lineNumber) {
       this.lineNumber = lineNumber;
   }

    @Override
    public String toString() {
        return "CSrmFileUploadingRsp{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", flag=" + flag +
                ", orderNumber='" + orderNumber + '\'' +
                ", lineNumber='" + lineNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", onlyPurShow=" + onlyPurShow +
                ", count=" + count +
                '}';
    }
}
