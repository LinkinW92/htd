package com.skeqi.mes.pojo;

import io.swagger.annotations.ApiModelProperty;

public class CMesRecipeDatilT {
    private Integer id;
    @ApiModelProperty(value = "步序", required = false)
    private String stepno;
    @ApiModelProperty(value = "类别", required = false)
    private String stepCategory;
    @ApiModelProperty(value = "名称", required = false)
    private String materialName;
    @ApiModelProperty(value = "数量", required = false)
    private String numbers;
    @ApiModelProperty(value = "枪号", required = false)
    private String gunNo;
    @ApiModelProperty(value = "程序号", required = false)
    private String programNo;
    @ApiModelProperty(value = "相机号", required = false)
    private String photoNo;
    @ApiModelProperty(value = "套筒号", required = false)
    private String sleeveNo;
    @ApiModelProperty(value = "返工次数", required = false)
    private String reworktimes;
    @ApiModelProperty(value = "校验规则", required = false)
    private String feacode;
    @ApiModelProperty(value = "是否校验", required = false)
    private String chekorno;
    @ApiModelProperty(value = "是否追溯", required = false)
    private String revieworno;
    @ApiModelProperty(value = "物料类别", required = false)
    private String exactorno;
    @ApiModelProperty(value = "物料PN", required = false)
    private String materialpn;
    @ApiModelProperty(value = "工位节拍", required = false)
    private String bolteqs;
    @ApiModelProperty(value = "角度上限", required = false)
    private String aLimit;
    @ApiModelProperty(value = "扭矩上限", required = false)
    private String tLimit;
    @ApiModelProperty(value = "配方Id", required = false)
    private Integer recipeId;
    @ApiModelProperty(value = "上传代码", required = false)
    private String uploadCode;
    @ApiModelProperty(value = "产品id", required = false)
    private Integer productionId;
    @ApiModelProperty(value = "工位id", required = false)
    private Integer stationId;
    @ApiModelProperty(value = "配方名称", required = false)
    private String recipeName;
    @ApiModelProperty(value = "图片路径", required = false)
    private String picturnPath;
    @ApiModelProperty(value = "螺栓json数据", required = false)
    private String boltjson;
    @ApiModelProperty(value = "产品名称", required = false)
    private String productionName;
    @ApiModelProperty(value = "产品校验规则", required = false)
    private String productionVr;
    @ApiModelProperty(value = "工位名称", required = false)
    private String stationName;
    @ApiModelProperty(value = "类别名称", required = false)
    private String recipeTypeName;
    @ApiModelProperty(value = "物料id", required = false)
    private String materialId;
    @ApiModelProperty(value = "图片二进制", required = false)
    private String pathBinary;
    @ApiModelProperty(value = "旧的步序", required = false)
    private Integer oldstepNo;
    @ApiModelProperty(value = "添加对API脚本的重放支持", required = false)
    private String apiAnnotation;
    @ApiModelProperty(value = "配方Code", required = false)
    private String recipeCode;
    @ApiModelProperty(value = "总配方Code", required = false)
    private String totalRecipeCode;
    @ApiModelProperty(value = "工单id", required = false)
    private Integer workorderId;


	public Integer getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(Integer workorderId) {
		this.workorderId = workorderId;
	}

	public String getPathBinary() {
        return pathBinary;
    }

    public void setPathBinary(String pathBinary) {
        this.pathBinary = pathBinary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStepno() {
        return stepno;
    }

    public void setStepno(String stepno) {
        this.stepno = stepno;
    }

    public String getStepCategory() {
        return stepCategory;
    }

    public void setStepCategory(String stepCategory) {
        this.stepCategory = stepCategory;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getGunNo() {
        return gunNo;
    }

    public void setGunNo(String gunNo) {
        this.gunNo = gunNo;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public String getPhotoNo() {
        return photoNo;
    }

    public void setPhotoNo(String photoNo) {
        this.photoNo = photoNo;
    }

    public String getSleeveNo() {
        return sleeveNo;
    }

    public void setSleeveNo(String sleeveNo) {
        this.sleeveNo = sleeveNo;
    }

    public String getReworktimes() {
        return reworktimes;
    }

    public void setReworktimes(String reworktimes) {
        this.reworktimes = reworktimes;
    }

    public String getFeacode() {
        return feacode;
    }

    public void setFeacode(String feacode) {
        this.feacode = feacode;
    }

    public String getChekorno() {
        return chekorno;
    }

    public void setChekorno(String chekorno) {
        this.chekorno = chekorno;
    }

    public String getRevieworno() {
        return revieworno;
    }

    public void setRevieworno(String revieworno) {
        this.revieworno = revieworno;
    }

    public String getexactorno() {
        return exactorno;
    }

    public void setexactorno(String exactorno) {
        this.exactorno = exactorno;
    }

    public Integer getOldstepNo() {
        return oldstepNo;
    }

    public void setOldstepNo(Integer oldstepNo) {
        this.oldstepNo = oldstepNo;
    }

    public String getMaterialpn() {
        return materialpn;
    }

    public void setMaterialpn(String materialpn) {
        this.materialpn = materialpn;
    }

    public String getBolteqs() {
        return bolteqs;
    }

    public void setBolteqs(String bolteqs) {
        this.bolteqs = bolteqs;
    }

    public String getaLimit() {
        return aLimit;
    }

    public void setaLimit(String aLimit) {
        this.aLimit = aLimit;
    }

    public String gettLimit() {
        return tLimit;
    }

    public void settLimit(String tLimit) {
        this.tLimit = tLimit;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public String getTotalRecipeCode() {
        return totalRecipeCode;
    }

    public void setTotalRecipeCode(String totalRecipeCode) {
        this.totalRecipeCode = totalRecipeCode;
    }

    public String getUploadCode() {
        return uploadCode;
    }

    public void setUploadCode(String uploadCode) {
        this.uploadCode = uploadCode;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getPicturnPath() {
        return picturnPath;
    }

    public void setPicturnPath(String picturnPath) {
        this.picturnPath = picturnPath;
    }

    public String getBoltjson() {
        return boltjson;
    }

    public void setBoltjson(String boltjson) {
        this.boltjson = boltjson;
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

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getRecipeTypeName() {
        return recipeTypeName;
    }

    public void setRecipeTypeName(String recipeTypeName) {
        this.recipeTypeName = recipeTypeName;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getApiAnnotation() {
        return apiAnnotation;
    }

    public void setApiAnnotation(String apiAnnotation) {
        this.apiAnnotation = apiAnnotation;
    }

    public CMesRecipeDatilT(Integer id, String stepno, String stepCategory, String materialName, String numbers,
                            String gunNo, String programNo, String photoNo, String sleeveNo, String reworktimes, String feacode,
                            String chekorno, String revieworno, String exactorno, String materialpn, String bolteqs, String aLimit,
                            String tLimit, Integer recipeId, String recipeCode, String totalRecipeCode, String uploadCode, Integer productionId, Integer stationId,
                            String recipeName, String picturnPath, String boltjson, String productionName, String productionVr,
                            String stationName, String recipeTypeName, String materialId, String apiAnnotation) {
        super();
        this.id = id;
        this.stepno = stepno;
        this.stepCategory = stepCategory;
        this.materialName = materialName;
        this.numbers = numbers;
        this.gunNo = gunNo;
        this.programNo = programNo;
        this.photoNo = photoNo;
        this.sleeveNo = sleeveNo;
        this.reworktimes = reworktimes;
        this.feacode = feacode;
        this.chekorno = chekorno;
        this.revieworno = revieworno;
        this.exactorno = exactorno;
        this.materialpn = materialpn;
        this.bolteqs = bolteqs;
        this.aLimit = aLimit;
        this.tLimit = tLimit;
        this.recipeId = recipeId;
        this.recipeCode = recipeCode;
        this.totalRecipeCode = totalRecipeCode;
        this.uploadCode = uploadCode;
        this.productionId = productionId;
        this.stationId = stationId;
        this.recipeName = recipeName;
        this.picturnPath = picturnPath;
        this.boltjson = boltjson;
        this.productionName = productionName;
        this.productionVr = productionVr;
        this.stationName = stationName;
        this.recipeTypeName = recipeTypeName;
        this.materialId = materialId;
        this.apiAnnotation = apiAnnotation;
    }

    public CMesRecipeDatilT() {
        super();
    }

    @Override
    public String toString() {
        return "CMesRecipeDatilT [id=" + id + ", stepno=" + stepno + ", stepCategory=" + stepCategory
                + ", materialName=" + materialName + ", numbers=" + numbers + ", gunNo=" + gunNo + ", programNo="
                + programNo + ", photoNo=" + photoNo + ", sleeveNo=" + sleeveNo + ", reworktimes=" + reworktimes
                + ", feacode=" + feacode + ", chekorno=" + chekorno + ", revieworno=" + revieworno + ", exactorno="
                + exactorno + ", materialpn=" + materialpn + ", bolteqs=" + bolteqs + ", aLimit=" + aLimit + ", tLimit="
                + tLimit + ", recipeId=" + recipeId + ", uploadCode=" + uploadCode + ", productionId=" + productionId
                + ", stationId=" + stationId + ", recipeName=" + recipeName + ", picturnPath=" + picturnPath
                + ", boltjson=" + boltjson + ", productionName=" + productionName + ", productionVr=" + productionVr
                + ", stationName=" + stationName + ", recipeTypeName=" + recipeTypeName + ", materialId=" + materialId + ", apiAnnotation=" + apiAnnotation
                + ", recipeCode=" + recipeCode + ",totalRecipeCode=" + totalRecipeCode + "]";
    }


}
