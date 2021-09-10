package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * 工单条码
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2019年10月9日 上午11:55:00
 */
public class CMesWorkBarcodeT {

	private Integer planId;  //计划id
	private String planName;  //计划name

	private Integer workId;  //工单id
	private String workName;  //工单name;

	private Integer printId;  //条码id
	private String sn;  //总成
	private Date dt;  //打印时间

	private Integer lineId;  //产线id
	private String lineName;  //产线name
	public CMesWorkBarcodeT() {
		super();
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Integer getWorkId() {
		return workId;
	}
	public void setWorkId(Integer workId) {
		this.workId = workId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Integer getPrintId() {
		return printId;
	}
	public void setPrintId(Integer printId) {
		this.printId = printId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public CMesWorkBarcodeT(Integer planId, String planName, Integer workId, String workName, Integer printId,
			String sn, Date dt, Integer lineId, String lineName) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.workId = workId;
		this.workName = workName;
		this.printId = printId;
		this.sn = sn;
		this.dt = dt;
		this.lineId = lineId;
		this.lineName = lineName;
	}
	@Override
	public String toString() {
		return "CMesWorkBarcodeT [planId=" + planId + ", planName=" + planName + ", workId=" + workId + ", workName="
				+ workName + ", printId=" + printId + ", sn=" + sn + ", dt=" + dt + ", lineId=" + lineId + ", lineName="
				+ lineName + "]";
	}


}
