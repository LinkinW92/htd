package com.skeqi.mes.pojo;



public class CMesIqcCheckT {

	private Integer id;
	private String factoryNo;  //工厂编号
	private String checkBatch; //物料凭证号
	private String materialVoucher;  //校验批次
	private String otigin;  //校验批来源
	private String materialNo; //物料编号
	private String materialDescribe;  //物料描述
	private Integer checkNum;  //校验批数量
	private Integer NgNum;  //不合格数量
	private Integer seqNgNum;  //SQE不合格数量
	private String calculateUnit;  //计量单位
	private String supplierName;  //供应商名称
	private String emp;  //创建人
	private String dt;  // 送检时间
	private String checkDt;  //复检时间
	private String checkPerson;  //检验人
	private String productionHandie;  //产品处置
	private Integer status;  //状态，1：未处理，2：已处理，3：已复核
	private String materialName;//物料名称
	private Integer freeze;//冻结状态
	private String starttime;
	private String endtime;
	private String starttimes;
	private String endtimes;





	public String getStarttimes() {
		return starttimes;
	}
	public void setStarttimes(String starttimes) {
		this.starttimes = starttimes;
	}
	public String getEndtimes() {
		return endtimes;
	}
	public void setEndtimes(String endtimes) {
		this.endtimes = endtimes;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getCheckDt() {
		return checkDt;
	}
	public void setCheckDt(String checkDt) {
		this.checkDt = checkDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFactoryNo() {
		return factoryNo;
	}
	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	public String getCheckBatch() {
		return checkBatch;
	}
	public void setCheckBatch(String checkBatch) {
		this.checkBatch = checkBatch;
	}
	public String getMaterialVoucher() {
		return materialVoucher;
	}
	public void setMaterialVoucher(String materialVoucher) {
		this.materialVoucher = materialVoucher;
	}
	public String getOtigin() {
		return otigin;
	}
	public void setOtigin(String otigin) {
		this.otigin = otigin;
	}
	public String getMaterialNo() {
		return materialNo;
	}
	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}
	public String getMaterialDescribe() {
		return materialDescribe;
	}
	public void setMaterialDescribe(String materialDescribe) {
		this.materialDescribe = materialDescribe;
	}
	public Integer getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}
	public Integer getNgNum() {
		return NgNum;
	}
	public void setNgNum(Integer ngNum) {
		NgNum = ngNum;
	}
	public Integer getSeqNgNum() {
		return seqNgNum;
	}
	public void setSeqNgNum(Integer seqNgNum) {
		this.seqNgNum = seqNgNum;
	}
	public String getCalculateUnit() {
		return calculateUnit;
	}
	public void setCalculateUnit(String calculateUnit) {
		this.calculateUnit = calculateUnit;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getProductionHandie() {
		return productionHandie;
	}
	public void setProductionHandie(String productionHandie) {
		this.productionHandie = productionHandie;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFreeze() {
		return freeze;
	}
	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}


	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public CMesIqcCheckT() {
		super();
	}
	public CMesIqcCheckT(Integer id, String factoryNo, String checkBatch, String materialVoucher, String otigin,
			String materialNo, String materialDescribe, Integer checkNum, Integer ngNum, Integer seqNgNum,
			String calculateUnit, String supplierName, String emp, String dt, String checkDt, String checkPerson,
			String productionHandie, Integer status, String materialName, Integer freeze, String starttime,
			String endtime, String starttimes, String endtimes) {
		super();
		this.id = id;
		this.factoryNo = factoryNo;
		this.checkBatch = checkBatch;
		this.materialVoucher = materialVoucher;
		this.otigin = otigin;
		this.materialNo = materialNo;
		this.materialDescribe = materialDescribe;
		this.checkNum = checkNum;
		NgNum = ngNum;
		this.seqNgNum = seqNgNum;
		this.calculateUnit = calculateUnit;
		this.supplierName = supplierName;
		this.emp = emp;
		this.dt = dt;
		this.checkDt = checkDt;
		this.checkPerson = checkPerson;
		this.productionHandie = productionHandie;
		this.status = status;
		this.materialName = materialName;
		this.freeze = freeze;
		this.starttime = starttime;
		this.endtime = endtime;
		this.starttimes = starttimes;
		this.endtimes = endtimes;
	}
	@Override
	public String toString() {
		return "CMesIqcCheckT [id=" + id + ", factoryNo=" + factoryNo + ", checkBatch=" + checkBatch
				+ ", materialVoucher=" + materialVoucher + ", otigin=" + otigin + ", materialNo=" + materialNo
				+ ", materialDescribe=" + materialDescribe + ", checkNum=" + checkNum + ", NgNum=" + NgNum
				+ ", seqNgNum=" + seqNgNum + ", calculateUnit=" + calculateUnit + ", supplierName=" + supplierName
				+ ", emp=" + emp + ", dt=" + dt + ", checkDt=" + checkDt + ", checkPerson=" + checkPerson
				+ ", productionHandie=" + productionHandie + ", status=" + status + ", materialName=" + materialName
				+ ", freeze=" + freeze + ", starttime=" + starttime + ", endtime=" + endtime + ", starttimes="
				+ starttimes + ", endtimes=" + endtimes + "]";
	}



}
