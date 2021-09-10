package com.skeqi.mes.pojo.alarm;

/**
 * 问题等级
 * @author yinp
 *
 */
public class CAlarmProblemLevel {

	private Integer id;
	private Integer level;
	private String explain;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public CAlarmProblemLevel(Integer id, Integer level, String explain) {
		super();
		this.id = id;
		this.level = level;
		this.explain = explain;
	}
	public CAlarmProblemLevel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CAlarmProblemLevel [id=" + id + ", level=" + level + ", explain=" + explain + "]";
	}

}
