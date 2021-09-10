package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @date 2020年1月11日
 * @author yinp
 *
 */
public class InitializeReworkDataPT implements Serializable{

	private Integer id;
	private String cStationsStName;
	private String cRecipesStepCategory;
	private String cRecipesMaterialName;
	private String cRecipesNumbers;
	private String cRecipesTLimit;
	private String cRecipesALimit;
	private String tempStationRecipeId;

	public String getTempStationRecipeId() {
		return tempStationRecipeId;
	}
	public void setTempStationRecipeId(String tempStationRecipeId) {
		this.tempStationRecipeId = tempStationRecipeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getcStationsStName() {
		return cStationsStName;
	}
	public void setcStationsStName(String cStationsStName) {
		this.cStationsStName = cStationsStName;
	}
	public String getcRecipesStepCategory() {
		return cRecipesStepCategory;
	}
	public void setcRecipesStepCategory(String cRecipesStepCategory) {
		this.cRecipesStepCategory = cRecipesStepCategory;
	}
	public String getcRecipesMaterialName() {
		return cRecipesMaterialName;
	}
	public void setcRecipesMaterialName(String cRecipesMaterialName) {
		this.cRecipesMaterialName = cRecipesMaterialName;
	}
	public String getcRecipesNumbers() {
		return cRecipesNumbers;
	}
	public void setcRecipesNumbers(String cRecipesNumbers) {
		this.cRecipesNumbers = cRecipesNumbers;
	}
	public String getcRecipesTLimit() {
		return cRecipesTLimit;
	}
	public void setcRecipesTLimit(String cRecipesTLimit) {
		this.cRecipesTLimit = cRecipesTLimit;
	}
	public String getcRecipesALimit() {
		return cRecipesALimit;
	}
	public void setcRecipesALimit(String cRecipesALimit) {
		this.cRecipesALimit = cRecipesALimit;
	}

}
