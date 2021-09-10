package com.skeqi.mes.pojo.project;

public class CMesAndonFault {

	private Integer id;
	private String lineName;// 产线名称
	private String stationName;// 工位名称
	private String establishDt;// 创建时间
	private String responseDt;// 响应时间
	private String solveDt;// 解决时间
	private String lossType;// 损失类型（组织损失、技术损失、质量损失、物料损失）
	private Integer status;// 状态（0：已创建，1：已响应，2：已解决）
	private String faultType;// 故障类型（设备故障、物料故障）
	private String emp;// 处理人员
	private String note;// 问题详情
	private String FaultName;
	private String lossName;
	private Integer toolId;// 设备id
	private String reason;
	private String reasonName;
	private String toolName;// 设备名称
	private Integer faultLevelId;// 故障等级Id
	private Integer faultLevel;// 故障等级
	private String faultLevelExplain;// 故障等级说明
	private CMesToolManager tool;

	public String getFaultLevelExplain() {
		return faultLevelExplain;
	}

	public void setFaultLevelExplain(String faultLevelExplain) {
		this.faultLevelExplain = faultLevelExplain;
	}

	public Integer getId() {
		return id;
	}

	public Integer getFaultLevel() {
		return faultLevel;
	}

	public void setFaultLevel(Integer faultLevel) {
		this.faultLevel = faultLevel;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEstablishDt() {
		return establishDt;
	}

	public void setEstablishDt(String establishDt) {
		this.establishDt = establishDt;
	}

	public String getResponseDt() {
		return responseDt;
	}

	public void setResponseDt(String responseDt) {
		this.responseDt = responseDt;
	}

	public String getSolveDt() {
		return solveDt;
	}

	public void setSolveDt(String solveDt) {
		this.solveDt = solveDt;
	}

	public String getLossType() {
		return lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFaultName() {
		return FaultName;
	}

	public void setFaultName(String faultName) {
		FaultName = faultName;
	}

	public String getLossName() {
		return lossName;
	}

	public void setLossName(String lossName) {
		this.lossName = lossName;
	}

	public Integer getToolId() {
		return toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonName() {
		return reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public Integer getFaultLevelId() {
		return faultLevelId;
	}

	public void setFaultLevelId(Integer faultLevelId) {
		this.faultLevelId = faultLevelId;
	}

	public CMesToolManager getTool() {
		return tool;
	}

	public void setTool(CMesToolManager tool) {
		this.tool = tool;
	}

}
