package com.skeqi.mes.pojo;

public class CMesRecipeVersionDetail {

	private Integer DetailId;  //主键ID
	private Integer versionId;	//版本表ID
	private String stepCategory;	//操作类别
	private String materialName;	//工序名称
	private String numbers; 	//数量
	private String gunNo;  	//枪号
	private String programNo;  //程序号
	private String materialpn;  //物料pn
	private String sleeveNo;  //套筒号
	private Integer stepno; // 工序
	private String uploadCode;  //上传代码
	private String feacode;  //校验规则
	private String bolteqs; //工位节拍
	private String stationName;  //工位名称
	private Integer stationId;   //工位ID
	private String productionName;  //产品名称
	private Integer productionId;  //产品id
	private String productionVr;  //产品规则
	private String photoNo; //相加号
	private String reworktimes; //返工次数
	private String chekorno; //是否校验
	private String revieworno;  //是否追溯
	private String exactorno; //物料类别，0：批次追溯 1：精确追溯
	private String aLimit; //角度上限
	private String tLimit; //扭矩上限
	private String picturnPath; //图片路径
	private String  pathBinary;   //二进制图片
	private String boltjson; //螺栓json数据


	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getProductionId() {
		return productionId;
	}
	public void setProductionId(Integer productionId) {
		this.productionId = productionId;
	}
	public String getPathBinary() {
		return pathBinary;
	}
	public void setPathBinary(String pathBinary) {
		this.pathBinary = pathBinary;
	}
	public String getPhotoNo() {
		return photoNo;
	}
	public void setPhotoNo(String photoNo) {
		this.photoNo = photoNo;
	}
	public String getReworktimes() {
		return reworktimes;
	}
	public void setReworktimes(String reworktimes) {
		this.reworktimes = reworktimes;
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
	public String getExactorno() {
		return exactorno;
	}
	public void setExactorno(String exactorno) {
		this.exactorno = exactorno;
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
	public String getPicturnPath() {
		return picturnPath;
	}
	public void setPicturnPath(String picturnPath) {
		this.picturnPath = picturnPath;
	}
	public Integer getDetailId() {
		return DetailId;
	}
	public void setDetailId(Integer detailId) {
		DetailId = detailId;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
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
	public String getMaterialpn() {
		return materialpn;
	}
	public void setMaterialpn(String materialpn) {
		this.materialpn = materialpn;
	}
	public String getSleeveNo() {
		return sleeveNo;
	}
	public void setSleeveNo(String sleeveNo) {
		this.sleeveNo = sleeveNo;
	}
	public Integer getStepno() {
		return stepno;
	}
	public void setStepno(Integer stepno) {
		this.stepno = stepno;
	}
	public String getUploadCode() {
		return uploadCode;
	}
	public void setUploadCode(String uploadCode) {
		this.uploadCode = uploadCode;
	}
	public String getFeacode() {
		return feacode;
	}
	public void setFeacode(String feacode) {
		this.feacode = feacode;
	}
	public String getBolteqs() {
		return bolteqs;
	}
	public void setBolteqs(String bolteqs) {
		this.bolteqs = bolteqs;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public String getBoltjson() {
		return boltjson;
	}
	public void setBoltjson(String boltjson) {
		this.boltjson = boltjson;
	}

}
