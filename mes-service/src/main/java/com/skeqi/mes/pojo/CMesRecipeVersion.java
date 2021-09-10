package com.skeqi.mes.pojo;

public class CMesRecipeVersion {

	private Integer recipeId;
	private Double  recipeName;
	private Integer productionId;
	private String productionName;
	private String productionVr;
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getProductionVr() {
		return productionVr;
	}
	public void setProductionVr(String productionVr) {
		this.productionVr = productionVr;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public Double getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(Double recipeName) {
		this.recipeName = recipeName;
	}
	@Override
	public String toString() {
		return "CMesRecipeVersion [recipeId=" + recipeId + ", recipeName=" + recipeName + ", productionId="
				+ productionId + ", productionName=" + productionName + ", productionVr=" + productionVr + "]";
	}
	public CMesRecipeVersion(Integer recipeId, Double recipeName, Integer productionId, String productionName,
			String productionVr) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.productionId = productionId;
		this.productionName = productionName;
		this.productionVr = productionVr;
	}
	public CMesRecipeVersion() {
		super();
	}



}
