package com.skeqi.mes.pojo.api;

import java.io.Serializable;

public class InitializeDataPT implements Serializable {

	private Integer id;
	private Integer tempStationRecipeId;
	private String tempParamartersName;
	private String tempExceptionMsg;
	private String tempStationName;
	private String cStationsStId;
	private String cRecipesStepCategory;
	private String cRecipesMaterialName;
	private String cRecipesMaterialpn;
	private String cRecipesNumbers;
	private String cRecipesTLimit;
	private String cRecipesALimit;

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

	public String getcRecipesMaterialpn() {
		return cRecipesMaterialpn;
	}

	public void setcRecipesMaterialpn(String cRecipesMaterialpn) {
		this.cRecipesMaterialpn = cRecipesMaterialpn;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTempStationRecipeId() {
		return tempStationRecipeId;
	}

	public void setTempStationRecipeId(Integer tempStationRecipeId) {
		this.tempStationRecipeId = tempStationRecipeId;
	}

	public String getTempParamartersName() {
		return tempParamartersName;
	}

	public void setTempParamartersName(String tempParamartersName) {
		this.tempParamartersName = tempParamartersName;
	}

	public String getTempExceptionMsg() {
		return tempExceptionMsg;
	}

	public void setTempExceptionMsg(String tempExceptionMsg) {
		this.tempExceptionMsg = tempExceptionMsg;
	}

	public String getTempStationName() {
		return tempStationName;
	}

	public void setTempStationName(String tempStationName) {
		this.tempStationName = tempStationName;
	}

	public String getcStationsStId() {
		return cStationsStId;
	}

	public void setcStationsStId(String cStationsStId) {
		this.cStationsStId = cStationsStId;
	}
}
