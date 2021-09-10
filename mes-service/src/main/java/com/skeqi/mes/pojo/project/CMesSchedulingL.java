package com.skeqi.mes.pojo.project;



//查询排版数据表
public class CMesSchedulingL {

	private int id;// 排版ID
	// private Integer lineId;//产线ID
	// private Integer shiftid;//班次ID
	// private String shiftName;//班次名称
	private String lineName;// 产线名称
 private String teamName;//班组名称
	// private Integer teamId;
	private String dt;
	 private Integer planNumber;
	private Integer ActualNumber;
	private String shiftName;
	private String planName;
	// private List<CMesPlan> plans;
   private String ProName;//产品名称

	public String getTeamName() {
	return teamName;
}

public void setTeamName(String teamName) {
	this.teamName = teamName;
}

public Integer getPlanNumber() {
	return planNumber;
}

public void setPlanNumber(Integer planNumber) {
	this.planNumber = planNumber;
}

public String getShiftName() {
	return shiftName;
}

public void setShiftName(String shiftName) {
	this.shiftName = shiftName;
}

	public String getProName() {
	return ProName;
}

public void setProName(String proName) {
	ProName = proName;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public Integer getActualNumber() {
		return ActualNumber;
	}

	public void setActualNumber(Integer actualNumber) {
		ActualNumber = actualNumber;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
