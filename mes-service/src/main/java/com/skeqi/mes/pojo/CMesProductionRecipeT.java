package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CMesProductionRecipeT {
	@ApiModelProperty(name = "总配方Id", value = "false")
	private Integer id;// 总配方id
	@ApiModelProperty(name = "/产品id", value = "false")
	private Integer productionId;// 产品id
	@ApiModelProperty(name = "配方Id", value = "false")
	private Integer recipeId;// 配方id
	@ApiModelProperty(name = "工位id", value = "false")
	private Integer stationId;// 工位id
	@ApiModelProperty(name = "产品名称", value = "false")
	private String productionName;// 产品名称
	@ApiModelProperty(name = "配方名称", value = "false")
	private String recipeName;// 配方名称
	@ApiModelProperty(name = "工位名称", value = "false")
	private String stationName;// 工位名称
	@ApiModelProperty(name = "配方介绍", value = "false")
	private String productionDiscription;// 配方介绍

	public String getProductionName() {
		return productionName;
	}

	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getProductionDiscription() {
		return productionDiscription;
	}

	public void setProductionDiscription(String productionDiscription) {
		this.productionDiscription = productionDiscription;
	}

	public CMesProductionRecipeT(Integer id, Integer productionId, Integer recipeId, Integer stationId) {
		super();
		this.id = id;
		this.productionId = productionId;
		this.recipeId = recipeId;
		this.stationId = stationId;
	}

	public CMesProductionRecipeT() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductionId() {
		return productionId;
	}

	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	@Override
	public String toString() {
		return "CMesProductionRecipeT [productionRecipeId=" + id + ", productionId=" + productionId + ", recipeId="
				+ recipeId + ", stationId=" + stationId + ", productionName=" + productionName + ", recipeName="
				+ recipeName + ", stationName=" + stationName + ", productionDiscription=" + productionDiscription
				+ "]";
	}
}
