package com.skeqi.mes.pojo.project;

/**
 * 更新安灯故障
 * @author : FQZ
 * @Package: com.skeqi.mes.pojo.project
 * @date   : 2020年4月20日 下午2:35:05
 */
public class UpdateAndon{

	private Integer id;
	private String lossName;
	private String emp;
	private String note;
	private Integer toolId;
	private String reason;

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLossName() {
		return lossName;
	}
	public void setLossName(String lossName) {
		this.lossName = lossName;
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
	public Integer getToolId() {
		return toolId;
	}
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	public UpdateAndon(Integer id, String lossName, String emp, String note, Integer toolId, String reason) {
		super();
		this.id = id;
		this.lossName = lossName;
		this.emp = emp;
		this.note = note;
		this.toolId = toolId;
		this.reason = reason;
	}
	public UpdateAndon() {
		super();
	}



}
