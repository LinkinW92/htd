package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 总配方表
 *
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date : 2020年3月27日 上午10:55:51
 */
public class CMesTotalRecipeT {

    @ApiModelProperty(value = "配方id", required = false)
    private Integer id;
    @ApiModelProperty(value = "配方名称", required = true)
    private String totalRecipeName;   //配方名称
    @ApiModelProperty(value = "配方描述", required = true)
    private String totalRecipeDescribe;  //配方描述
    @ApiModelProperty(value = "产线ID", required = true)
    private Integer lineId;  //产线ID
    @ApiModelProperty(value = "产线编号", required = true)
    private String lineCode;  //产线编号
    @ApiModelProperty(value = "产线名称", required = false)
    private String lineName;   //产线名称
    @ApiModelProperty(value = "产品ID", required = true)
    private Integer productionId;  //产品ID
    @ApiModelProperty(value = "产品Code", required = true)
    private String productionCode;  //产品Code
    @ApiModelProperty(value = "产品名称", required = false)
    private String productionName;   //产品名称
    @ApiModelProperty(value = "是否默认", required = false)
    private Integer status;   //是否默认

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalRecipeName() {
        return totalRecipeName;
    }

    public void setTotalRecipeName(String totalRecipeName) {
        this.totalRecipeName = totalRecipeName;
    }

    public String getTotalRecipeDescribe() {
        return totalRecipeDescribe;
    }

    public void setTotalRecipeDescribe(String totalRecipeDescribe) {
        this.totalRecipeDescribe = totalRecipeDescribe;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CMesTotalRecipeT [id=" + id + ", totalRecipeName=" + totalRecipeName + ", totalRecipeDescribe="
                + totalRecipeDescribe + ", lineId=" + lineId + ", lineName=" + lineName + ", productionId="
                + productionId + ", productionName=" + productionName + ", status=" + status + ",lineCode=" + lineCode + ",productionCode=" + productionCode + "]";
    }

    public CMesTotalRecipeT(Integer id, String totalRecipeName, String totalRecipeDescribe, Integer lineId, String lineCode,
                            String lineName, Integer productionId, String productionName, Integer status, String productionCode) {
        super();
        this.id = id;
        this.totalRecipeName = totalRecipeName;
        this.totalRecipeDescribe = totalRecipeDescribe;
        this.lineId = lineId;
        this.lineCode = lineCode;
        this.lineName = lineName;
        this.productionId = productionId;
        this.productionCode = productionCode;
        this.productionName = productionName;
        this.status = status;
    }

    public CMesTotalRecipeT() {
        super();
    }


}
