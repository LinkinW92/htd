package com.skeqi.mes.pojo.qh;

//结存量
public class CMesEndStocks {

	private Integer pid;//产品id
	private  String proName;//产品名称
	private  String lineName;//产线名称
	private  String stationName;//工位名称
	private Integer lineRegion;//产线区域
	private Integer sum;//产量、
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getLineRegion() {
		return lineRegion;
	}
	public void setLineRegion(Integer lineRegion) {
		this.lineRegion = lineRegion;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "CMesEndStocks [pid=" + pid + ", proName=" + proName + ", lineName=" + lineName + ", stationName="
				+ stationName + ", lineRegion=" + lineRegion + ", sum=" + sum + "]";
	}
	public CMesEndStocks(Integer pid, String proName, String lineName, String stationName, Integer lineRegion,
			Integer sum) {
		super();
		this.pid = pid;
		this.proName = proName;
		this.lineName = lineName;
		this.stationName = stationName;
		this.lineRegion = lineRegion;
		this.sum = sum;
	}
	public CMesEndStocks() {
		super();
	}

}
