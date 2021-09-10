package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

//配方表
public class CMesRecipeT {
	@ApiModelProperty(name="配方ID",value="false")
	private Integer id;//配方ID
	@ApiModelProperty(name="配方名称",value="true")
	private String recipeName;//配方名称
	@ApiModelProperty(name="配方介绍",value="true")
	private String recipeDiscription;//配方介绍
	@ApiModelProperty(name="总配方ID",value="true")
	private Integer totald;//总配方ID
	@ApiModelProperty(name="工位ID",value="true")
	private Integer stationId;//工位ID
	@ApiModelProperty(name="总配方名称",value="false")
	private String totalRecipeName;//总配方名称


	public Integer getTotald() {
		return totald;
	}
	public void setTotald(Integer totald) {
		this.totald = totald;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTotalRecipeName() {
		return totalRecipeName;
	}
	public void setTotalRecipeName(String totalRecipeName) {
		this.totalRecipeName = totalRecipeName;
	}
	public CMesRecipeT(Integer id, String recipeName, String recipeDiscription) {
		super();
		this.id = id;
		this.recipeName = recipeName;
		this.recipeDiscription = recipeDiscription;
	}
	@Override
	public String toString() {
		return "CMesRecipeT [id=" + id + ", recipeName=" + recipeName + ", recipeDiscription=" + recipeDiscription+",totalRecipeName="+totalRecipeName
				+ "]";
	}
	public CMesRecipeT() {
		super();
	}



}
