package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 配方表
 *
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date : 2020年3月27日 上午10:50:48
 */
public class CMesRecipe {

    @ApiModelProperty(value = "id", required = false)
    private Integer id;
    @ApiModelProperty(value = "总配方id", required = true)
    private Integer totalId;   //总配方id
    @ApiModelProperty(value = "总配方Code", required = true)
    private String totalCode;   //总配方Code
    @ApiModelProperty(value = "总配方名称", required = false)
    private String totalRecipeName;    //总配方名称
    @ApiModelProperty(value = "配方名称", required = true)
    private String recipeName;     //配方名称
    @ApiModelProperty(value = "配方描述", required = true)
    private String recipeDiscription;    //配方描述
    @ApiModelProperty(value = "工位id", required = true)
    private Integer stationId;    //工位id
    @ApiModelProperty(value = "工位Code", required = true)
    private String stationCode;    //工位Code
    @ApiModelProperty(value = "工位名称", required = false)
    private String stationName;    //工位名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalId() {
        return totalId;
    }

    public void setTotalId(Integer totalId) {
        this.totalId = totalId;
    }

    public String getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(String totalCode) {
        this.totalCode = totalCode;
    }

    public String getTotalRecipeName() {
        return totalRecipeName;
    }

    public void setTotalRecipeName(String totalRecipeName) {
        this.totalRecipeName = totalRecipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDiscription() {
        return recipeDiscription;
    }

    public void setRecipeDiscription(String recipeDiscription) {
        this.recipeDiscription = recipeDiscription;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public String toString() {
        return "CMesRecipe [id=" + id + ", totalId=" + totalId + ", totalCode=" + totalCode + ", totalRecipeName=" + totalRecipeName
                + ", recipeName=" + recipeName + ", recipeDiscription=" + recipeDiscription + ", stationId=" + stationId + ", stationCode=" + stationCode
                + ", stationName=" + stationName + "]";
    }

    public CMesRecipe(Integer id, Integer totalId, String totalCode, String totalRecipeName, String recipeName, String recipeDiscription,
                      Integer stationId, String stationCode, String stationName) {
        super();
        this.id = id;
        this.totalId = totalId;
        this.totalCode = totalCode;
        this.totalRecipeName = totalRecipeName;
        this.recipeName = recipeName;
        this.recipeDiscription = recipeDiscription;
        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
    }

    public CMesRecipe() {
        super();
    }


}
